/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CorporateManagement.FacilityManagement;

import javax.ejb.Local;
import EntityManager.RegionalOfficeEntity;
import EntityManager.ManufacturingFacilityEntity;
import EntityManager.StoreEntity;
import EntityManager.WarehouseEntity;
import static java.util.Collections.list;
import java.util.List;

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

    public boolean createManufacturingFacility(String manufacturingFacilityName);

    public boolean removeManufacturingFacility(String manufacturingFacilityName);

    public ManufacturingFacilityEntity viewManufacturingFacility(String manufacturingFacilityEntity);

    public List<ManufacturingFacilityEntity> viewListOfManufacturingFacility();

    public boolean createStore(String storeName);

    public boolean removeStore(String storeName);

    public StoreEntity viewStoreEntity(String storeEntity);

    public List<StoreEntity> viewListOfStorey();

    public Long createWarehouse();
    
    public Boolean editWarehouse(Long id);
    
    public Boolean deleteWarehouse(Long id);
    
    public List<WarehouseEntity> getWarehouseList();
}
