package JUnit_Testing;

import CorporateManagement.FacilityManagement.FacilityManagementBeanRemote;
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
    public void addRegionalOffice() {
    }

    @Test
    public void editRegionalOffice() {
    }

    @Test
    public void removeRegionalOffice() {
    }

    @Test
    public void viewRegionalOffice() {
    }

    @Test
    public void viewListOfRegionalOffice() {
    }

    @Test
    public void checkNameExistsOfRegionalOffice() {
    }

    @Test
    public void createManufacturingFacility() {
    }

    @Test
    public void editManufacturingFacility() {
    }

    @Test
    public void removeManufacturingFacility() {
    }

    @Test
    public void viewManufacturingFacility() {
    }

    @Test
    public void viewListOfManufacturingFacility() {
    }

    @Test
    public void checkNameExistsOfManufacturingFacility() {
    }

    @Test
    public void getManufacturingFacilityByName() {
    }

    @Test
    public void getManufacturingFacilityHelper() {
    }

    @Test
    public void getManufacturingFacilityHelperList() {
    }

    @Test
    public void addManufacturingFacilityToRegionalOffice() {
    }

    @Test
    public void updateManufacturingFacilityToRegionalOffice() {
    }

    @Test
    public void createStore() {
    }

    @Test
    public void editStore() {
    }

    @Test
    public void viewStoreEntity() {
    }

    @Test
    public void viewListOfStore() {
    }

    @Test
    public void checkNameExistsOfStore() {
    }

    @Test
    public void getStoreByName() {
    }

    @Test
    public void getStoreByID() {
    }

    @Test
    public void removeStore() {
    }

    @Test
    public void addStoreToRegionalOffice() {
    }

    @Test
    public void updateStoreToRegionalOffice() {
    }

    @Test
    public void getStoreHelperClass() {
    }

    @Test
    public void getStoreHelperList() {
    }

    @Test
    public void getStoreListByRegionalOffice() {
    }

    @Test
    public void createWarehouse() {
    }

    @Test
    public void editWarehouse() {
    }

    @Test
    public void deleteWarehouse() {
    }

    @Test
    public void getWarehouseByName() {
    }

    @Test
    public void checkNameExistsOfWarehouse() {
    }

    @Test
    public void checkIfWarehouseContainsItem() {
    }

    @Test
    public void getWarehouseById() {
    }

    @Test
    public void getWarehouseList() {
    }

    @Test
    public void getMFWarehouseList() {
    }

    @Test
    public void getStoreWarehouseList() {
    }

    @Test
    public void getListOfCountries() {
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
