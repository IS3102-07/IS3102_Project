package JUnit_Testing;

import SCM.InboundAndOutboundLogistics.InboundAndOutboundLogisticsBeanRemote;
import EntityManager.ShippingOrderEntity;
import EntityManager.WarehouseEntity;
import java.util.Date;
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
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SCM_InboundAndOutboundLogisticsBeanRemote_JUnit {

    InboundAndOutboundLogisticsBeanRemote inboundAndOutboundLogisticsBean = lookupInboundAndOutboundLogisticsBeanRemote();

    static Long shippingOrderId = null; //no need to change

    public SCM_InboundAndOutboundLogisticsBeanRemote_JUnit() {
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
    public void test01CreateShippingOrderBasicInfo() {
        System.out.println("test01CreateShippingOrderBasicInfo()");
        Date testdata_expectedReceivedDate = new Date();
        Long testdata_destinationWarehouseID = 1000L;
        Long testdata_sourceWarehouseD = 1200L;
        ShippingOrderEntity result = inboundAndOutboundLogisticsBean.createShippingOrderBasicInfo(testdata_expectedReceivedDate, testdata_destinationWarehouseID, testdata_sourceWarehouseD);
        assertNull(result);

        //    public ShippingOrderEntity createShippingOrderBasicInfo(Date expectedReceivedDate, Long sourceWarehouseID, Long destinationWarehouseID);
    }

    @Test
    public void testCheckSKUExists() {
        System.out.println("testCheckSKUExists()");
        boolean result = inboundAndOutboundLogisticsBean.checkSKUExists("F10000000");
        assertFalse(result);

        //public boolean checkSKUExists(String SKU);
    }

    @Test
    public void testUpdateShippingOrder() {
        System.out.println("testUpdateShippingOrder");
        Long testdata_shippingOrderID = 1000L;
        Long testdata_sourceWarehouseID = 1000L;
        Long testdata_destinationWarehouseID = 1000L;
        Date testdata_expectedReceivedDate = new Date();

        boolean result = inboundAndOutboundLogisticsBean.updateShippingOrder(testdata_shippingOrderID, testdata_sourceWarehouseID, testdata_destinationWarehouseID, testdata_expectedReceivedDate);

        //    public Boolean updateShippingOrder(Long shippingOrderID, Long sourceWarehouseID, Long destinationWarehouseID, Date expectedReceivedDate);
    }

    @Test
    public void testUpdateShippingOrderStatus() {
        System.out.println("testUpdateShippingOrderStatus");
        Long testdata_shippingOrderID = 1000L;
        String testdata_status = "";
        String testdata_submittedBy = "";

        boolean result = inboundAndOutboundLogisticsBean.updateShippingOrderStatus(testdata_shippingOrderID, testdata_status, testdata_submittedBy);

        //    public Boolean updateShippingOrderStatus(Long shippingOrderID, String status, String submittedBy);
    }

    @Test
    public void testAddLineItemToShippingOrder() {
        System.out.println("testAddLineItemToShippingOrder");
        Long testdata_shippingOrderID = 1000L;
        String testdata_SKU = "";
        int testdata_qty = 1;

        boolean result = inboundAndOutboundLogisticsBean.addLineItemToShippingOrder(testdata_shippingOrderID, testdata_SKU, testdata_qty);
        //public Boolean addLineItemToShippingOrder(Long shippingOrderID, String SKU, Integer qty);
    }

    @Test
    public void testRemoveLineItemFromShippingOrder() {
        System.out.println("testRemoveLineItemFromShippingOrder");
        Long testdata_shippingOrderID = 1000L;
        Long testdata_lineItemID = 1000L;
        boolean result = inboundAndOutboundLogisticsBean.removeLineItemFromShippingOrder(testdata_shippingOrderID, testdata_lineItemID);
        //    public Boolean removeLineItemFromShippingOrder(Long shippingOrderID, Long lineItemID);
    }

    @Test
    public void testUpdateLineItemFromShippingOrder() {
        System.out.println("testUpdateLineItemFromShippingOrder");
        Long testdata_shippingOrderID = 1000L;
        Long testdata_lineItemID = 1000L;
        String testdata_SKU = "";
        int testdata_qty = 1;
        boolean result = inboundAndOutboundLogisticsBean.updateLineItemFromShippingOrder(testdata_shippingOrderID, testdata_lineItemID, testdata_SKU, testdata_qty);
        //    public Boolean updateLineItemFromShippingOrder(Long shippingOrderID, Long lineItemID, String SKU, Integer qty);
    }

    @Test
    public void test02GetShippingOrderList() {
        System.out.println("testGetShippingOrderList");
        WarehouseEntity testdata_orgin_warehouse = null; //???????
        Date testdata_shippedDate = new Date();
        List<ShippingOrderEntity> result = inboundAndOutboundLogisticsBean.getShippingOrderList(testdata_orgin_warehouse, testdata_shippedDate);
        shippingOrderId = result.get(0).getId();
        //    public List<ShippingOrderEntity> getShippingOrderList(WarehouseEntity origin, Date shippedDate);
    }

    @Test
    public void test03GetShippingOrderById() {
        System.out.println("testGetShippingOrderById");
        ShippingOrderEntity result = inboundAndOutboundLogisticsBean.getShippingOrderById(shippingOrderId);
        //    public ShippingOrderEntity getShippingOrderById(Long id);
    }

    @Test
    public void testGetShippingOrderListByWarehouseId() {
        System.out.println("testGetShippingOrderListByWarehouseId");
        Long testdata_warehouseId = 1000L;
        List<ShippingOrderEntity> result = inboundAndOutboundLogisticsBean.getShippingOrderListByWarehouseId(testdata_warehouseId);

        //    public List<ShippingOrderEntity> getShippingOrderListByWarehouseId(Long warehouseId);
    }

    private InboundAndOutboundLogisticsBeanRemote lookupInboundAndOutboundLogisticsBeanRemote() {
        try {
            Context c = new InitialContext();
            return (InboundAndOutboundLogisticsBeanRemote) c.lookup("java:global/IS3102_Project/IS3102_Project-ejb/InboundAndOutboundLogisticsBean!SCM.InboundAndOutboundLogistics.InboundAndOutboundLogisticsBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
