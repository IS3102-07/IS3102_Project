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
import HelperClasses.ManufacturingFacilityHelper;
import HelperClasses.StoreHelper;
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
    public boolean checkNameExistsOfRegionalOffice(String name);

    public ManufacturingFacilityEntity createManufacturingFacility(String manufacturingFacilityName, String address, String telephone, String email, Integer capacity);
    public Boolean editManufacturingFacility(Long id, String manufacturingFacilityName, String address, String telephone, String email, Integer capacity);
    public boolean removeManufacturingFacility(String manufacturingFacilityName);
    public ManufacturingFacilityEntity viewManufacturingFacility(Long manufacturingFacilityEntityId);
    public List<ManufacturingFacilityEntity> viewListOfManufacturingFacility();    
    public boolean checkNameExistsOfManufacturingFacility(String name);
    
    public ManufacturingFacilityHelper getManufacturingFacilityHelper(Long manufacturingFacilityId);
    public List<ManufacturingFacilityHelper> getManufacturingFacilityHelperList();
    public Boolean addManufacturingFacilityToRegionalOffice(Long regionalOfficeId, Long MFid);
    public Boolean updateManufacturingFacilityToRegionalOffice(Long regionalOfficeId, Long MFid);
    public Boolean removeManufacturingFacility(Long Id);
    
    public StoreEntity createStore(String storeName, String address, String telephone, String email);    
    public Boolean editStore(Long id, String storeName, String address, String telephone, String email);  
    public StoreEntity viewStoreEntity(Long storeId);
    public List<StoreEntity> viewListOfStore();
    public boolean checkNameExistsOfStore(String name);
    
    public StoreEntity getStoreByName(String storeName);
    public Boolean removeStore(Long storeId);
    public Boolean addStoreToRegionalOffice(Long regionalOfficeId, Long storeId);
    public Boolean updateStoreToRegionalOffice(Long regionalOfficeId, Long storeId);
    public StoreHelper getStoreHelperClass(Long Id);
    public List<StoreHelper> getStoreHelperList();    
    public List<StoreEntity> getStoreListByRegionalOffice(Long regionalOfficeId);
    
    public WarehouseEntity createWarehouse(String warehouseName, String address, String telephone, String email, Long storeId, Long mfId);
    public Boolean editWarehouse(Long id, String warehouseName, String address, String telephone, String email);
    public Boolean deleteWarehouse(Long id);
    public WarehouseEntity getWarehouseByName(String warehouseName);
    public boolean checkNameExistsOfWarehouse(String name);
    public boolean checkIfWarehouseContainsItem(Long id);
    
    public WarehouseEntity getWarehouseById(Long Id);
    public List<WarehouseEntity> getWarehouseList();
    
    
    @Remove
    public void remove();
}
