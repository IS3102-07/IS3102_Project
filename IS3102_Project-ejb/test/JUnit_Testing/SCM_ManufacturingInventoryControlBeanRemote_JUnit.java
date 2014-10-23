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
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

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
    public void testGetEmptyStorageBins() {
    }

    @Test
    public void testMoveInboundPurchaseOrderItemsToReceivingBin() {
    }

    @Test
    public void testMoveInboundShippingOrderItemsToReceivingBin() {
    }

    @Test
    public void testRemoveItemFromOutboundBinForShipping() {
    }

    @Test
    public void testAddItemToReceivingBin() {
    }

    @Test
    public void testMoveSingleItemBetweenStorageBins() {
    }

    @Test
    public void testCheckItemQty() {
    }

    @Test
    public void testFindStorageBinsThatContainsItem() {
    }

    @Test
    public void testGetTotalVolumeOfInboundStorageBin() {
    }

    @Test
    public void testGetTotalVolumeOfOutboundStorageBin() {
    }

    @Test
    public void testGetTotalVolumeOfShelfStorageBin() {
    }

    @Test
    public void testGetTotalVolumeOfPalletStorageBin() {
    }

    @Test
    public void testGetTotalFreeVolumeOfInboundStorageBin() {
    }

    @Test
    public void testGetTotalFreeVolumeOfOutboundStorageBin() {
    }

    @Test
    public void testGetTotalFreeVolumeOfPalletStorageBin() {
    }

    @Test
    public void testGetItemList() {
    }

    @Test
    public void testGetOutboundBinItemList() {
    }

    @Test
    public void testGetBinItemList() {
    }

    @Test
    public void testEmptyStorageBin() {
    }

    @Test
    public void testCheckIfItemExistInsideStorageBin() {
    }

    @Test
    public void testCheckIfStorageBinIsOfAppropriateItemType() {
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
