package JUnit_Testing;

import SCM.SupplierManagement.SupplierManagementBeanRemote;
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

public class SCM_SupplierManagementBeanRemote_JUnit {
    SupplierManagementBeanRemote supplierManagementBean = lookupSupplierManagementBeanRemote();

    public SCM_SupplierManagementBeanRemote_JUnit() {
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
    public void addSupplier() {
    }

    @Test
    public void deleteSupplier() {
    }

    @Test
    public void editSupplier() {
    }

    @Test
    public void getSupplier() {
    }

    @Test
    public void viewAllSupplierList() {
    }

    @Test
    public void checkSupplierExists() {
    }

    @Test
    public void getListOfCountries() {
    }

    @Test
    public void getSupplierItemList() {
    }

    @Test
    public void getSupplierListOfRO() {
    }

    private SupplierManagementBeanRemote lookupSupplierManagementBeanRemote() {
        try {
            Context c = new InitialContext();
            return (SupplierManagementBeanRemote) c.lookup("java:global/IS3102_Project/IS3102_Project-ejb/SupplierManagementBean!SCM.SupplierManagement.SupplierManagementBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
