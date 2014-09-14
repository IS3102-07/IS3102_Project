/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CorporateManagement.FacilityManagement;

import EntityManager.ManufacturingFacilityEntity;
import EntityManager.RegionalOfficeEntity;
import EntityManager.StoreEntity;
import EntityManager.WarehouseEntity;
import static java.util.Collections.list;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remove;

/**
 *
 * @author Loi Liang Yang
 */
@Local
public interface FacilityManagementBeanLocal {

    public boolean addRegionalOffice(String regionalOfficeName);

    public boolean removeRegionalOffice(String regionalOfficeName);

    public RegionalOfficeEntity viewRegionalOffice(String regionalOfficeName);

    public List<RegionalOfficeEntity> viewListOfRegionalOffice();

    public ManufacturingFacilityEntity createManufacturingFacility(String manufacturingFacilityName);

    public boolean removeManufacturingFacility(String manufacturingFacilityName);

    public ManufacturingFacilityEntity viewManufacturingFacility(String manufacturingFacilityEntity);

    public List<ManufacturingFacilityEntity> viewListOfManufacturingFacility();

    public StoreEntity createStore(String storeName);

    public boolean removeStore(String storeName);

    public StoreEntity viewStoreEntity(String storeEntity);

    public List<StoreEntity> viewListOfStorey();

    public WarehouseEntity createWarehouse(String warehouseName);        
    
    public Boolean deleteWarehouse(Long id);
    
    public WarehouseEntity getWarehouseByName(String warehouseName);
    
    public List<WarehouseEntity> getWarehouseList();
    
    @Remove
    public void remove();
}
