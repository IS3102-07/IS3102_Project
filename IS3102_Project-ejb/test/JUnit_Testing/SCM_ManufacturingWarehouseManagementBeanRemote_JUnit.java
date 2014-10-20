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
    public void createStorageBin() {
    } 

    @Test
    public void updateStorageBin() {
    }

    @Test
    public void deleteStorageBin() {
    }

    @Test
    public void viewStorageBin() {
    }

    @Test
    public void viewAllStorageBin() {
    }

    @Test
    public void getInboundStorageBin() {
    } 

    @Test
    public void getOutboundStorageBin() {
    } //look for the inbound storagebin

    @Test
    public void createTransferOrder() {
    }

    @Test
    public void addLineItemToTransferOrder() {
    }

    @Test
    public void removeLineItemFromTransferOrder() {
    }

    @Test
    public void markTransferOrderAsCompleted() {
    }

    @Test
    public void cancelTransferOrder() {
    }

    @Test
    public void viewTransferOrder() {
    }

    @Test
    public void viewAllTransferOrderByWarehouseId() {
    }

    @Test
    public void deleteTransferOrder() {
    }

    @Test
    public void markTransferOrderAsUnfulfilled() {
    }

    @Test
    public void searchItemBySKU() {
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
