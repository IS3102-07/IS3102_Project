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
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remove;

/**
 *
 * @author Loi Liang Yang
 */
@Local
public interface FacilityManagementBeanLocal {

    public boolean addRegionalOffice(String regionalOfficeName, String address, String telephone, String email);
    public Boolean editRegionalOffice(Long id, String regionalOfficeName, String address, String telephone, String email);
    public boolean removeRegionalOffice(String regionalOfficeName);
    public RegionalOfficeEntity viewRegionalOffice(String regionalOfficeName);
    public List<RegionalOfficeEntity> viewListOfRegionalOffice();

    public ManufacturingFacilityEntity createManufacturingFacility(String manufacturingFacilityName, String address, String telephone, String email, Integer capacity);
    public Boolean editManufacturingFacility(Long id, String manufacturingFacilityName);
    public boolean removeManufacturingFacility(String manufacturingFacilityName);
    public ManufacturingFacilityEntity viewManufacturingFacility(String manufacturingFacilityEntity);
    public List<ManufacturingFacilityEntity> viewListOfManufacturingFacility();

    public StoreEntity createStore(String storeName, String address, String telephone, String email);
    public Boolean editStore(Long id, String storeName);
    public boolean removeStore(String storeName);
    public StoreEntity viewStoreEntity(String storeEntity);
    public List<StoreEntity> viewListOfStore();

    public WarehouseEntity createWarehouse(String warehouseName, String address, String telephone, String email);
    public Boolean editWarehouse(Long id, String warehouseName, String address, String telephone, String email);
    public Boolean deleteWarehouse(Long id);
    public WarehouseEntity getWarehouseByName(String warehouseName);
    
    public WarehouseEntity getWarehouseById(Long Id);
    public List<WarehouseEntity> getWarehouseList();
    public Boolean addStoreConnectionToManufacturingFacility(Long id, StoreEntity store);
    
    @Remove
    public void remove();
}
