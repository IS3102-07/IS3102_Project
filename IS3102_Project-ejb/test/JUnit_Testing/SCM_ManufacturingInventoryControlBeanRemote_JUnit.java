package JUnit_Testing;

import SCM.ManufacturingInventoryControl.ManufacturingInventoryControlBeanRemote;
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

public class SCM_ManufacturingInventoryControlBeanRemote_JUnit {
    ManufacturingInventoryControlBeanRemote manufacturingInventoryControlBean = lookupManufacturingInventoryControlBeanRemote();

    public SCM_ManufacturingInventoryControlBeanRemote_JUnit() {
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
    public void getEmptyStorageBins() {
    } 

    @Test
    public void moveInboundPurchaseOrderItemsToReceivingBin() {
    }

    @Test
    public void moveInboundShippingOrderItemsToReceivingBin() {
    }

    @Test
    public void removeItemFromOutboundBinForShipping() {
    }

    @Test
    public void addItemToReceivingBin() {
    }


    @Test
    public void moveSingleItemBetweenStorageBins() {
    }

    @Test
    public void checkItemQty() {
    }

    @Test
    public void findStorageBinsThatContainsItem() {
    }

    @Test
    public void getTotalVolumeOfInboundStorageBin() {
    }

    @Test
    public void getTotalVolumeOfOutboundStorageBin() {
    }

    @Test
    public void getTotalVolumeOfShelfStorageBin() {
    }

    @Test
    public void getTotalVolumeOfPalletStorageBin() {
    }

    @Test
    public void getTotalFreeVolumeOfInboundStorageBin() {
    }

    @Test
    public void getTotalFreeVolumeOfOutboundStorageBin() {
    }

    @Test
    public void getTotalFreeVolumeOfPalletStorageBin() {
    }

    @Test
    public void getItemList() {
    }

    @Test
    public void getOutboundBinItemList() {
    }

    @Test
    public void getBinItemList() {
    }

    @Test
    public void emptyStorageBin() {
    }

    @Test
    public void checkIfItemExistInsideStorageBin() {
    }

    @Test
    public void checkIfStorageBinIsOfAppropriateItemType() {
    }

    private ManufacturingInventoryControlBeanRemote lookupManufacturingInventoryControlBeanRemote() {
        try {
            Context c = new InitialContext();
            return (ManufacturingInventoryControlBeanRemote) c.lookup("java:global/IS3102_Project/IS3102_Project-ejb/ManufacturingInventoryControlBean!SCM.ManufacturingInventoryControl.ManufacturingInventoryControlBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
