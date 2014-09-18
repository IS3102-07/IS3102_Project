/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MRP.ProductionPlanDistribution;

import EntityManager.ManufacturingFacilityEntity;
import EntityManager.StoreEntity;
import javax.ejb.Local;
import javax.ejb.Remove;

/**
 *
 * @author Lin Baoyu
 */
@Local
public interface ProductionPlanDistributionBeanLocal {
    
    public Boolean addStoreToManufacturingFacilityAllocationList(Long storeId, Long manufacturingFacilityId);
    
    @Remove
    public void remove();
}
