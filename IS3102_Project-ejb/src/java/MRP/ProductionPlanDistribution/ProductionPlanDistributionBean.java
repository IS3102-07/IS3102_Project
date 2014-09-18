/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MRP.ProductionPlanDistribution;

import EntityManager.ManufacturingFacilityEntity;
import EntityManager.StoreEntity;
import java.util.ArrayList;
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
        try{
            Query q = em.createQuery("select s from StoreEntity s where s.regionalOffice.id = ?1").setParameter(1, regionalOfficeId);
            return q.getResultList();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<ManufacturingFacilityEntity> getManufacturingFacilityListByRegionalOffice(Long regionalOfficeId) {
        try{
            Query q = em.createQuery("select mf from ManufacturingFacilityEntity mf where mf.regionalOffice.id = ?1").setParameter(1, regionalOfficeId);
            return q.getResultList();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    @Override
    public Boolean addStoreToManufacturingFacilityAllocationList(Long storeId, Long manufacturingFacilityId) {
        try{
            StoreEntity store = em.find(StoreEntity.class, storeId);
            ManufacturingFacilityEntity manufacturingFacility = em.find(ManufacturingFacilityEntity.class, manufacturingFacilityId);
            store.getManufacturingFacilityList().add(manufacturingFacility);
            em.merge(store);
            em.merge(manufacturingFacility);
            em.flush();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void remove() {
        System.out.println("Production Plan Distribution Plan has been removed.");
    }
    
    
    
}
