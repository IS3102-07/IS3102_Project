/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MRP.DemandManagement;

import EntityManager.ManufacturingFacilityEntity;
import EntityManager.MasterProductionScheduleEntity;
import EntityManager.MonthScheduleEntity;
import EntityManager.ProductGroupLineItemEntity;
import EntityManager.SaleAndOperationPlanEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DemandManagementBean implements DemandManagementBeanLocal {

    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;

    @Override
    public Boolean generateMasterProductionSchedules(Long MfId) {
        try {
            Query q = em.createQuery("select s from MonthScheduleEntity s");
            List<MonthScheduleEntity> scheduleList = q.getResultList();
            if (!scheduleList.isEmpty()) {
                ManufacturingFacilityEntity mf = em.find(ManufacturingFacilityEntity.class, MfId);
                MonthScheduleEntity lastSchedule = scheduleList.get(scheduleList.size() - 1);
                Query q2 = em.createQuery("select sop from SaleAndOperationPlanEntity sop where sop.schedule.id = ?1 and sop.manufacturingFacility.id = ?2")
                        .setParameter(1, lastSchedule.getId())
                        .setParameter(2, MfId);
                List<SaleAndOperationPlanEntity> sopList = (List<SaleAndOperationPlanEntity>) q2.getResultList();
                for (SaleAndOperationPlanEntity sop : sopList) {
                    for (ProductGroupLineItemEntity lineitem : sop.getProductGroup().getLineItemList()) {
                        Query q3 = em.createQuery("select mps from MasterProductionScheduleEntity mps where mps.mf.id = ?1 and mps.schedule.id = ?2 and mps.furniture.SKU = ?3")
                                .setParameter(1, MfId)
                                .setParameter(2, lastSchedule.getId())
                                .setParameter(3, lineitem.getFurniture().getSKU());

                        MasterProductionScheduleEntity mps;

                        if (q3.getResultList().isEmpty()) {
                            mps = new MasterProductionScheduleEntity();
                            mps.setMf(mf);
                            mps.setSchedule(lastSchedule);
                            mps.setFurniture(lineitem.getFurniture());

                        } else {
                            mps = (MasterProductionScheduleEntity) q3.getResultList().get(0);
                        }

                        int days_month = lastSchedule.getWorkDays_firstWeek() + lastSchedule.getWorkDays_secondWeek() + lastSchedule.getWorkDays_thirdWeek()
                                + lastSchedule.getWorkDays_forthWeek() + lastSchedule.getWorkDays_fifthWeek();

                        int amount = (int) Math.round(sop.getProductionPlan() * lineitem.getPercent());
                        int amount_week1 = (int) Math.round(1.0 * amount * lastSchedule.getWorkDays_firstWeek() / days_month);
                        int amount_week2 = (int) Math.round(1.0 * amount * lastSchedule.getWorkDays_firstWeek() / days_month);
                        int amount_week3 = (int) Math.round(1.0 * amount * lastSchedule.getWorkDays_firstWeek() / days_month);
                        int amount_week4 = (int) Math.round(1.0 * amount * lastSchedule.getWorkDays_firstWeek() / days_month);

                        mps.setAmount_month(mps.getAmount_month() + amount);
                        mps.setAmount_week1(mps.getAmount_week1() + amount_week1);
                        mps.setAmount_week2(mps.getAmount_week2() + amount_week2);
                        mps.setAmount_week3(mps.getAmount_week3() + amount_week3);
                        if (lastSchedule.getWorkDays_fifthWeek() == 0) {
                            mps.setAmount_week4(mps.getAmount_week4() + amount - amount_week1 - amount_week2 - amount_week3);
                        } else {
                            mps.setAmount_week4(mps.getAmount_week4() + amount_week4);
                            mps.setAmount_week5(mps.getAmount_week5() + amount - amount_week1 - amount_week2 - amount_week3 - amount_week4);
                        }
                        em.merge(mps);
                    }
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    @Override
    public List<MasterProductionScheduleEntity> getMasterProductionSchedules(Long MfId) {
        try {
            Query q = em.createQuery("select s from MonthScheduleEntity s");
            List<MonthScheduleEntity> scheduleList = q.getResultList();
            if (!scheduleList.isEmpty()) {
                ManufacturingFacilityEntity mf = em.find(ManufacturingFacilityEntity.class, MfId);
                MonthScheduleEntity lastSchedule = scheduleList.get(scheduleList.size() - 1);
                Query q1 = em.createQuery("select mps from MasterProductionScheduleEntity mps where mps.mf.id = ?1 and mps.schedule.id = ?2")
                        .setParameter(1, MfId)
                        .setParameter(2, lastSchedule.getId());
                return (List<MasterProductionScheduleEntity>) q1.getResultList();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    
}
