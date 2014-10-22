package JUnit_Testing;

import CorporateManagement.FacilityManagement.FacilityManagementBeanRemote;
import EntityManager.ManufacturingFacilityEntity;
import EntityManager.RegionalOfficeEntity;
import EntityManager.StoreEntity;
import EntityManager.WarehouseEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CorporateManagement_FacilityManagementBeanRemote_JUnit {

    FacilityManagementBeanRemote facilityManagementBean = lookupFacilityManagementBeanRemote();

    public CorporateManagement_FacilityManagementBeanRemote_JUnit() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddRegionalOffice() {
        System.out.println("testAddRegionalOffice");
        String testdata_callerStaffID = "12";
        String testdata_regionalOfficeName = "West Asian Regional Office";
        String testdata_address = "56 West View Drive";
        String testdata_telephone = "95432112";
        String testdata_email = "waro@if.com";
        Boolean result = facilityManagementBean.addRegionalOffice(testdata_callerStaffID, testdata_regionalOfficeName, testdata_address, testdata_telephone, testdata_email);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testEditRegionalOffice() {
        System.out.println("testAddRegionalOffice");
        String testdata_callerStaffID = "12";
        Long testdata_id = 8L;
        String testdata_regionalOfficeName = "West Asian Regional Office";
        String testdata_address = "56 West View Drive";
        String testdata_telephone = "95432112";
        String testdata_email = "waro@if.com";
        Boolean result = facilityManagementBean.editRegionalOffice(testdata_callerStaffID, testdata_id, testdata_regionalOfficeName, testdata_address, testdata_telephone, testdata_email);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testRemoveRegionalOffice() {
        System.out.println("testRemoveRegionalOffice");
        String testdata_callerStaffID = "12";
        String testdata_regionalOfficeName = "West Asian Regional Office";
        Boolean result = facilityManagementBean.removeRegionalOffice(testdata_callerStaffID, testdata_regionalOfficeName);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testViewRegionalOffice() {
        System.out.println("testViewRegionalOffice");
        String testdata_regionalOfficeName = "Asia Pacific Regional Office";
        RegionalOfficeEntity result = facilityManagementBean.viewRegionalOffice(testdata_regionalOfficeName);
        assertNull(result);
        assertTrue(result != null);
    }

    @Test
    public void testViewListOfRegionalOffice() {
        System.out.println("testViewListOfRegionalOffice");
        List result = facilityManagementBean.viewListOfRegionalOffice();
        assertTrue(!result.isEmpty());
        assertFalse(result == null | result.isEmpty());
    }

    @Test
    public void testCheckNameExistsOfRegionalOffice() {
        System.out.println("testCheckNameExistsOfRegionalOffice");
        String testdata_regionalOfficeName = "Asia Pacific Regional Office";
        Boolean result = facilityManagementBean.checkNameExistsOfRegionalOffice(testdata_regionalOfficeName);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testCreateManufacturingFacility() {
        System.out.println("testCreateManufacturingFacility");
        String testdata_callerStaffID = "12";
        String testdata_manufacturingFacilityName = "Manufacturing Facility SG3";
        String testdata_address = "123 Teban Garden";
        String testdata_telephone = "67890123";
        String testdata_email = "mfsg3@if.com";
        Integer testdata_capacity = 1000;
        ManufacturingFacilityEntity result = facilityManagementBean.createManufacturingFacility(testdata_callerStaffID, testdata_manufacturingFacilityName, testdata_address, testdata_telephone, testdata_email, testdata_capacity);
        assertTrue(result != null);
        assertNull(result);
    }

    @Test
    public void testEditManufacturingFacility() {
        System.out.println("testEditManufacturingFacility");
        String testdata_callerStaffID = "12";
        Long testdata_id = 46L;
        String testdata_manufacturingFacilityName = "Manufacturing Facility SG3";
        String testdata_address = "123 Teban Garden";
        String testdata_telephone = "67890123";
        String testdata_email = "mfsg3@if.com";
        Integer testdata_capacity = 1000;
        Boolean result = facilityManagementBean.editManufacturingFacility(testdata_callerStaffID, testdata_id, testdata_manufacturingFacilityName, testdata_address, testdata_telephone, testdata_email, testdata_capacity);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testRemoveManufacturingFacility() {
        System.out.println("testRemoveManufacturingFacility");
        String testdata_callerStaffID = "12";
        String testdata_manufacturingFacilityName = "Manufacturing Facility SG1";
        Boolean result = facilityManagementBean.removeManufacturingFacility(testdata_callerStaffID, testdata_manufacturingFacilityName);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testViewManufacturingFacility() {
        System.out.println("testViewManufacturingFacility");
        Long testdata_manufacturingId = 46L;
        ManufacturingFacilityEntity result = facilityManagementBean.viewManufacturingFacility(testdata_manufacturingId);
        assertTrue(result != null);
        assertNull(result);
    }

    @Test
    public void testViewListOfManufacturingFacility() {
        System.out.println("testViewListOfManufacturingFacility");
        List result = facilityManagementBean.viewListOfManufacturingFacility();
        assertTrue(result != null);
        assertNull(result);
    }

    @Test
    public void testCheckNameExistsOfManufacturingFacility() {
        System.out.println("testCheckNameExistsOfManufacturingFacility");
        String testdata_name = "Middle East Regional Office";
        Boolean result = facilityManagementBean.checkNameExistsOfManufacturingFacility(testdata_name);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testGetManufacturingFacilityByName() {
        System.out.println("testGetManufacturingFacilityByName");
        String testdata_name = "Middle East Regional Office";
        ManufacturingFacilityEntity result = facilityManagementBean.getManufacturingFacilityByName(testdata_name);
        assertEquals("Middle East Regional Office", result.getName());
        assertNull(result);
    }

    @Test
    public void testAddManufacturingFacilityToRegionalOffice() {
        System.out.println("testAddManufacturingFacilityToRegionalOffice");
        String testdata_callerStaffID = "12";
        Long testdata_regionalOfficeId = 46L;
        Long testdata_MFid = 48L;
        Boolean result = facilityManagementBean.addManufacturingFacilityToRegionalOffice(testdata_callerStaffID, testdata_regionalOfficeId, testdata_MFid);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testUpdateManufacturingFacilityToRegionalOffice() {
        System.out.println("testUpdateManufacturingFacilityToRegionalOffice");
        String testdata_callerStaffID = "12";
        Long testdata_regionalOfficeId = 46L;
        Long testdata_MFid = 48L;
        Boolean result = facilityManagementBean.updateManufacturingFacilityToRegionalOffice(testdata_callerStaffID, testdata_regionalOfficeId, testdata_MFid);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testCreateStore() {
        System.out.println("testCreateStore");
        String testdata_callerStaffID = "12";
        String testdata_storeName = "Bugis Plus Store";
        String testdata_address = "30 Bugis Street";
        String testdata_telephone = "6789123";
        String testdata_email = "bugisstore@if.com";
        Long testdata_countryID = 19L;
        String testdata_postalCode = "654321";
        StoreEntity result = facilityManagementBean.createStore(testdata_callerStaffID, testdata_storeName, testdata_address, testdata_telephone, testdata_email, testdata_countryID, testdata_postalCode);
        assertTrue(result != null);
        assertFalse(result == null);
    }

    @Test
    public void testEditStore() {
        System.out.println("testEditStore");
        String testdata_callerStaffID = "12";
        Long testdata_id = 53L;
        String testdata_storeName = "Bugis Home Store";
        String testdata_address = "30 Bugis Street";
        String testdata_telephone = "64756666";
        String testdata_email = "bugisstore@if.com";
        Long testdata_countryID = 19L;
        Boolean result = facilityManagementBean.editStore(testdata_callerStaffID, testdata_id, testdata_storeName, testdata_address, testdata_telephone, testdata_email, testdata_countryID);
        assertTrue(result != null);
        assertFalse(result == null);
    }

    @Test
    public void testViewStoreEntity() {
        System.out.println("testViewStoreEntity");
        Long testdata_storeId = 46L;
        StoreEntity result = facilityManagementBean.viewStoreEntity(testdata_storeId);
        assertTrue(result != null);
        assertNull(result);
    }

    @Test
    public void testViewListOfStore() {
        System.out.println("testViewListOfStore");
        List result = facilityManagementBean.viewListOfStore();
        assertTrue(result != null);
        assertNull(result);
    }

    @Test
    public void testCheckNameExistsOfStore() {
        System.out.println("testCheckNameExistsOfStore");
        String testdata_name = "Queenstown Store";
        Boolean result = facilityManagementBean.checkNameExistsOfStore(testdata_name);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testGetStoreByName() {
        System.out.println("testGetStoreByName");
        String testdata_name = "Tampines Store";
        StoreEntity result = facilityManagementBean.getStoreByName(testdata_name);
        assertEquals("Tampines Store", result.getName());
        assertNull(result);
    }

    @Test
    public void testGetStoreByID() {
        System.out.println("testGetStoreByID");
        Long testdata_id = 53L;
        StoreEntity result = facilityManagementBean.getStoreByID(testdata_id);
        assertNotNull(result);
        assertNull(result);
    }

    @Test
    public void testRemoveStore() {
        System.out.println("testRemoveStore");
        String testdata_callerStaffID = "12";
        Long testdata_storeId = 53L;
        Boolean result = facilityManagementBean.removeStore(testdata_callerStaffID, testdata_storeId);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testAddStoreToRegionalOffice() {
        System.out.println("testAddStoreToRegionalOffice");
        String testdata_callerStaffID = "12";
        Long testdata_regionalOfficeId = 46L;
        Long testdata_storeId = 53L;
        Boolean result = facilityManagementBean.addStoreToRegionalOffice(testdata_callerStaffID, testdata_regionalOfficeId, testdata_storeId);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testUpdateStoreToRegionalOffice() {
        System.out.println("testUpdateStoreToRegionalOffice");
        String testdata_callerStaffID = "12";
        Long testdata_regionalOfficeId = 46L;
        Long testdata_storeId = 55L;
        Boolean result = facilityManagementBean.updateStoreToRegionalOffice(testdata_callerStaffID, testdata_regionalOfficeId, testdata_storeId);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testGetStoreListByRegionalOffice() {
        System.out.println("testGetStoreListByRegionalOffice");
        Long testdata_regionalOfficeId = 46L;
        List result = facilityManagementBean.getStoreListByRegionalOffice(testdata_regionalOfficeId);
        assertTrue(result != null);
        assertNull(result);
    }

    @Test
    public void testCreateWarehouse() {
        System.out.println("testCreateWarehouse");
        String testdata_callerStaffID = "12";
        String testdata_warehouseName = "Bugis Store Warehouse";
        String testdata_address = "30 Bugis Street";
        String testdata_telephone = "6789123";
        String testdata_email = "bugiswarehouse@if.com";
        Long testdata_storeID = 55L;
        Long testdata_MFid = 48L;
        WarehouseEntity result = facilityManagementBean.createWarehouse(testdata_callerStaffID, testdata_warehouseName, testdata_address, testdata_telephone, testdata_email, testdata_storeID, testdata_MFid);
        assertTrue(result != null);
        assertFalse(result == null);
    }

    @Test
    public void testEditWarehouse() {
        System.out.println("testEditWarehouse");
        String testdata_callerStaffID = "12";
        String testdata_warehouseName = "Bugis Store Warehouse";
        String testdata_address = "30 Bugis Street";
        String testdata_telephone = "6789123";
        String testdata_email = "bugiswarehouse@if.com";
        Long testdata_id = 54L;
        Boolean result = facilityManagementBean.editWarehouse(testdata_callerStaffID, testdata_id, testdata_warehouseName, testdata_address, testdata_telephone, testdata_email);
        assertTrue(result != null);
        assertFalse(result == null);
    }

    @Test
    public void testDeleteWarehouse() {
        System.out.println("testDeleteWarehouse");
        String testdata_callerStaffID = "12";
        Long testdata_id = 54L;
        Boolean result = facilityManagementBean.deleteWarehouse(testdata_callerStaffID, testdata_id);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testGetWarehouseByName() {
        System.out.println("testGetWarehouseByName");
        String testdata_name = "Tampines Store Warehouse";
        StoreEntity result = facilityManagementBean.getStoreByName(testdata_name);
        assertEquals("Tampines Store Warehouse", result.getName());
        assertNull(result);
    }

    @Test
    public void testCheckNameExistsOfWarehouse() {
        System.out.println("testCheckNameExistsOfWarehouse");
        String testdata_name = "Queenstown Store Warehouse";
        Boolean result = facilityManagementBean.checkNameExistsOfWarehouse(testdata_name);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testCheckIfWarehouseContainsItem() {
        System.out.println("testCheckIfWarehouseContainsItem");
        Long testdata_id = 51L;
        Boolean result = facilityManagementBean.checkIfWarehouseContainsItem(testdata_id);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testGetWarehouseById() {
        System.out.println("testGetWarehouseById");
        Long testdata_id = 49L;
        StoreEntity result = facilityManagementBean.getStoreByID(testdata_id);
        assertNotNull(result);
        assertNull(result);
    }

    @Test
    public void testGetWarehouseList() {
        System.out.println("testGetWarehouseList");
        List result = facilityManagementBean.getWarehouseList();
        assertTrue(!result.isEmpty());
        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetMFWarehouseList() {
        System.out.println("testGetMFWarehouseList");
        List result = facilityManagementBean.getMFWarehouseList();
        assertTrue(!result.isEmpty());
        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetStoreWarehouseList() {
        System.out.println("testGetStoreWarehouseList");
        List result = facilityManagementBean.getStoreWarehouseList();
        assertTrue(!result.isEmpty());
        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetListOfCountries() {
        System.out.println("testGetListOfCountries");
        List result = facilityManagementBean.getListOfCountries();
        assertTrue(!result.isEmpty());
        assertFalse(result.isEmpty());
    }

    private FacilityManagementBeanRemote lookupFacilityManagementBeanRemote() {
        try {
            Context c = new InitialContext();
            return (FacilityManagementBeanRemote) c.lookup("java:global/IS3102_Project/IS3102_Project-ejb/FacilityManagementBean!CorporateManagement.FacilityManagement.FacilityManagementBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
