package JUnit_Testing;

import SCM.ManufacturingWarehouseManagement.ManufacturingWarehouseManagementBeanRemote;
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

public class SCM_ManufacturingWarehouseManagementBeanRemote_JUnit {

    ManufacturingWarehouseManagementBeanRemote manufacturingWarehouseManagementBean = lookupManufacturingWarehouseManagementBeanRemote();

    public SCM_ManufacturingWarehouseManagementBeanRemote_JUnit() {
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
    public void testCreateStorageBin() {
    }

    @Test
    public void testUpdateStorageBin() {
    }

    @Test
    public void testDeleteStorageBin() {
    }

    @Test
    public void testViewStorageBin() {
    }

    @Test
    public void testViewAllStorageBin() {
    }

    @Test
    public void testGetInboundStorageBin() {
    }

    @Test
    public void testGetOutboundStorageBin() {
    } //look for the inbound storagebin

    @Test
    public void testCreateTransferOrder() {
    }

    @Test
    public void testAddLineItemToTransferOrder() {
    }

    @Test
    public void testRemoveLineItemFromTransferOrder() {
    }

    @Test
    public void testMarkTransferOrderAsCompleted() {
    }

    @Test
    public void testCancelTransferOrder() {
    }

    @Test
    public void testViewTransferOrder() {
    }

    @Test
    public void testViewAllTransferOrderByWarehouseId() {
    }

    @Test
    public void testDeleteTransferOrder() {
    }

    @Test
    public void testMarkTransferOrderAsUnfulfilled() {
    }

    @Test
    public void testSearchItemBySKU() {
    }

    private ManufacturingWarehouseManagementBeanRemote lookupManufacturingWarehouseManagementBeanRemote() {
        try {
            Context c = new InitialContext();
            return (ManufacturingWarehouseManagementBeanRemote) c.lookup("java:global/IS3102_Project/IS3102_Project-ejb/ManufacturingWarehouseManagementBean!SCM.ManufacturingWarehouseManagement.ManufacturingWarehouseManagementBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
