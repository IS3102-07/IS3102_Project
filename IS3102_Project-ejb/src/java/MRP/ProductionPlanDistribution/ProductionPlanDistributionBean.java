/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MRP.ProductionPlanDistribution;

import EntityManager.ManufacturingFacilityEntity;
import EntityManager.StoreEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lin Baoyu
 */
@Stateless
public class ProductionPlanDistributionBean implements ProductionPlanDistributionBeanLocal {
    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;
    
    @Override
    public Boolean addStoreToManufacturingFacilityAllocationList(Long storeId, Long manufacturingFacilityId) {
        try{
            StoreEntity store = em.find(StoreEntity.class, storeId);
            ManufacturingFacilityEntity manufacturingFacility = em.find(ManufacturingFacilityEntity.class, manufacturingFacilityId);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void remove() {
        System.out.println("Production Plan Distribution Plan has been removed.");
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    
}
