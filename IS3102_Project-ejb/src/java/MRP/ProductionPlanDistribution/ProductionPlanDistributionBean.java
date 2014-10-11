/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRP.ProductionPlanDistribution;

import EntityManager.ManufacturingFacilityEntity;
import EntityManager.MonthScheduleEntity;
import EntityManager.SaleAndOperationPlanEntity;
import EntityManager.StoreEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Lin Baoyu
 */
@Stateless
public class ProductionPlanDistributionBean implements ProductionPlanDistributionBeanLocal {

    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;

    @Override
    public List<StoreEntity> getStoreListByRegionalOffice(Long regionalOfficeId) {
        try {
            Query q = em.createQuery("select s from StoreEntity s where s.regionalOffice.id = ?1").setParameter(1, regionalOfficeId);
            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<ManufacturingFacilityEntity> getManufacturingFacilityListByRegionalOffice(Long regionalOfficeId) {
        try {
            Query q = em.createQuery("select mf from ManufacturingFacilityEntity mf where mf.regionalOffice.id = ?1").setParameter(1, regionalOfficeId);
            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Boolean addStore_ManufacturingFacilityConnection(Long storeId, Long manufacturingFacilityId) {
        try {
            StoreEntity store = em.find(StoreEntity.class, storeId);
            ManufacturingFacilityEntity manufacturingFacility = em.find(ManufacturingFacilityEntity.class, manufacturingFacilityId);
            store.getManufacturingFacilityList().add(manufacturingFacility);
            manufacturingFacility.getStoreList().add(store);
            em.merge(store);
            em.merge(manufacturingFacility);
            em.flush();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Boolean removeStore_ManufacturingFacilityConnection(Long storeId, Long mfId) {
        try {
            StoreEntity store = em.find(StoreEntity.class, storeId);
            ManufacturingFacilityEntity manufacturingFacility = em.find(ManufacturingFacilityEntity.class, mfId);
            store.getManufacturingFacilityList().remove(manufacturingFacility);
            manufacturingFacility.getStoreList().remove(store);
            em.merge(store);
            em.merge(manufacturingFacility);
            em.flush();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void remove() {
        System.out.println("Production Plan Distribution Plan has been removed.");
    }

    @Override
    public Boolean distributeProductionPlan(Long regionalOfficeId) {
        try {
            Query q1 = em.createQuery("select s from MonthScheduleEntity s");
            List<MonthScheduleEntity> scheduleList = q1.getResultList();
            if (!scheduleList.isEmpty()) {
                MonthScheduleEntity lastSchedule = scheduleList.get(scheduleList.size() - 1);

                // clear former distribution
                try {
                    List<ManufacturingFacilityEntity> mfList = this.getManufacturingFacilityListByRegionalOffice(regionalOfficeId);
                    Collections.sort(mfList, new CustomeComparator_MF());
                    for (ManufacturingFacilityEntity mf : mfList) {                        
                        List<StoreEntity> storeList = mf.getStoreList();
                        Collections.sort(storeList, new CustomeComparator_Store());
                        for (StoreEntity store : storeList) {
                            Query q = em.createQuery("select sop from SaleAndOperationPlanEntity sop where sop.store.id = ?1 and sop.schedule.year = ?2 and sop.schedule.month= ?3")
                                    .setParameter(1, store.getId())
                                    .setParameter(2, lastSchedule.getYear())
                                    .setParameter(3, lastSchedule.getMonth());
                            List<SaleAndOperationPlanEntity> sopList = q.getResultList();
                            for (SaleAndOperationPlanEntity sop : sopList) {
                                sop.setManufacturingFacility(null);
                                em.merge(sop);
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // create new distribution plan
                List<ManufacturingFacilityEntity> manufacturingFacilityList = this.getManufacturingFacilityListByRegionalOffice(regionalOfficeId);
                Collections.sort(manufacturingFacilityList, new CustomeComparator_MF());
                for (ManufacturingFacilityEntity mf : manufacturingFacilityList) {
                    Integer residueCapacity = mf.getCapacity();
                    List<StoreEntity> storeList = mf.getStoreList();
                    Collections.sort(storeList, new CustomeComparator_Store());
                    for (StoreEntity store : storeList) {
                        Query q = em.createQuery("select sop from SaleAndOperationPlanEntity sop where sop.store.id = ?1 and sop.schedule.year = ?2 and sop.schedule.month= ?3")
                                .setParameter(1, store.getId())
                                .setParameter(2, lastSchedule.getYear())
                                .setParameter(3, lastSchedule.getMonth());
                        List<SaleAndOperationPlanEntity> sopList = q.getResultList();
                        for (SaleAndOperationPlanEntity sop : sopList) {
                            if (sop.getManufacturingFacility() == null) {
                                if (residueCapacity >= (sop.getProductGroup().getWorkHours() * sop.getProductionPlan())) {
                                    residueCapacity -= (sop.getProductGroup().getWorkHours() * sop.getProductionPlan());
                                    sop.setManufacturingFacility(mf);
                                    em.merge(sop);
                                    System.out.println(mf.getName()+" - residueCapacity: "+ residueCapacity + " after taking in "  + sop.getProductGroup().getName() + " with quantity " + sop.getProductionPlan());
                                }
                            }
                        }
                    }
                }
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private class CustomeComparator_MF implements Comparator<ManufacturingFacilityEntity> {

        @Override
        public int compare(ManufacturingFacilityEntity mf1, ManufacturingFacilityEntity mf2) {
            if (mf1.getStoreList().size() > mf2.getStoreList().size()) {
                return 1;
            } else if (mf1.getStoreList().size() == mf2.getStoreList().size()) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    private class CustomeComparator_Store implements Comparator<StoreEntity> {

        @Override
        public int compare(StoreEntity s1, StoreEntity s2) {
            if (s1.getManufacturingFacilityList().size() > s2.getManufacturingFacilityList().size()) {
                return 1;
            } else if (s1.getManufacturingFacilityList().size() == s2.getManufacturingFacilityList().size()) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    @Override
    public List<SaleAndOperationPlanEntity> getDistributedSOPList(Long regionalOfficeId) {
        try {
            Query q = em.createQuery("select s from MonthScheduleEntity s");
            List<MonthScheduleEntity> scheduleList = q.getResultList();
            if (!scheduleList.isEmpty()) {
                MonthScheduleEntity lastSchedule = scheduleList.get(scheduleList.size() - 1);
                Query q2 = em.createQuery("select sop from SaleAndOperationPlanEntity sop where sop.store.regionalOffice.id = ?1 and sop.schedule.id = ?2 and sop.manufacturingFacility is not null")
                        .setParameter(1, regionalOfficeId)
                        .setParameter(2, lastSchedule.getId());
                return q2.getResultList();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

}
