package JUnit_Testing;

import EntityManager.PurchaseOrderEntity;
import SCM.RetailProductsAndRawMaterialsPurchasing.RetailProductsAndRawMaterialsPurchasingBeanRemote;
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

public class SCM_RetailProductsAndRawMaterialsPurchasingBeanRemote_JUnit {

    RetailProductsAndRawMaterialsPurchasingBeanRemote retailProductsAndRawMaterialsPurchasingBean = lookupRetailProductsAndRawMaterialsPurchasingBeanRemote();

    public SCM_RetailProductsAndRawMaterialsPurchasingBeanRemote_JUnit() {
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
    public void test01CreatePurchaseOrder() {
        System.out.println("testCreatePurchaseOrder");
        Long testdata_supplierId = 1L;
        Long testdata_receivingWarehouseId = 2L;
        Date testdata_expectedReceivedDate = new Date();
        PurchaseOrderEntity result = retailProductsAndRawMaterialsPurchasingBean.createPurchaseOrder(testdata_supplierId, testdata_receivingWarehouseId, testdata_expectedReceivedDate);
        assertTrue(result != null);
        assertFalse(result == null);
    }

    @Test
    public void test02UpdatePurchaseOrder() {
        System.out.println("testUpdatePurchaseOrder");
        Long testdata_purchaseOrderId = 1L;
        Long testdata_supplierId = 2L;
        Long testdata_receivingWarehouseId = 2L;
        Date testdata_expectedReceivedDate = new Date();
        Boolean result = retailProductsAndRawMaterialsPurchasingBean.updatePurchaseOrder(testdata_purchaseOrderId, testdata_supplierId, testdata_receivingWarehouseId, testdata_expectedReceivedDate);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void test03UpdatePurchaseOrderStatus() {
        System.out.println("testUpdatePurchaseOrderStatus");
        Long testdata_id = 1L;
        String testdata_status = "Shipped";
        String testdata_submittedBy = "Peter";
        Boolean result = retailProductsAndRawMaterialsPurchasingBean.updatePurchaseOrderStatus(testdata_id, testdata_status, testdata_submittedBy);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void test04AddLineItemToPurchaseOrder() {
        System.out.println("testAddLineItemToPurchaseOrder");
        Long testdata_purchaseOrderId = 1L;
        String testdata_SKU = "RM1";
        Integer testdata_qty = 100;
        Boolean result = retailProductsAndRawMaterialsPurchasingBean.addLineItemToPurchaseOrder(testdata_purchaseOrderId, testdata_SKU, testdata_qty);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void test06RemoveLineItemFromPurchaseOrder() {
        System.out.println("testRemoveLineItemFromPurchaseOrder");
        Long testdata_lineItemId = 2L;
        Long testdata_purchaseOrderId = 1L;
        Boolean result = retailProductsAndRawMaterialsPurchasingBean.removeLineItemFromPurchaseOrder(testdata_lineItemId, testdata_purchaseOrderId);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void test05UpdateLineItemFromPurchaseOrder() {
        System.out.println("testUpdateLineItemFromPurchaseOrder");
        Long testdata_lineItemId = 2L;
        Long testdata_purchaseOrderId = 1L;
        String testdata_SKU = "RM1";
        Integer testdata_qty = 100;
        Boolean result = retailProductsAndRawMaterialsPurchasingBean.updateLineItemFromPurchaseOrder(testdata_purchaseOrderId, testdata_lineItemId, testdata_SKU, testdata_qty);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void test07GetPurchaseOrderById() {
        System.out.println("testGetPurchaseOrderById");
        Long testdata_id = 1L;
        PurchaseOrderEntity result = retailProductsAndRawMaterialsPurchasingBean.getPurchaseOrderById(testdata_id);
        assertNotNull(result);
        assertNull(result);
    }

    @Test
    public void test08GetPurchaseOrderListByStatus() {
        System.out.println("testGetPurchaseOrderListByStatus");
        String testdata_status = "Submitted";
        List result = retailProductsAndRawMaterialsPurchasingBean.getPurchaseOrderListByStatus(testdata_status);
        assertTrue(!result.isEmpty());
        assertFalse(result.isEmpty());
    }

    @Test
    public void test09GetPurchaseOrderList() {
        System.out.println("testGetPurchaseOrderList");
        List result = retailProductsAndRawMaterialsPurchasingBean.getPurchaseOrderList();
        assertTrue(!result.isEmpty());
        assertFalse(result.isEmpty());
    }

    @Test
    public void test10CheckSKUExists() {
        System.out.println("testCheckSKUExists");
        String testdata_SKU = "F1";
        Boolean result = retailProductsAndRawMaterialsPurchasingBean.checkSKUExists(testdata_SKU);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void test11GetPurchaseOrderListByWarehouseId() {
        System.out.println("testGetPurchaseOrderListByWarehouseId");
        Long testdata_warehouseId = 1L;
        List result = retailProductsAndRawMaterialsPurchasingBean.getPurchaseOrderListByWarehouseId(testdata_warehouseId);
        assertTrue(!result.isEmpty());
        assertFalse(result.isEmpty());
    }

    private RetailProductsAndRawMaterialsPurchasingBeanRemote lookupRetailProductsAndRawMaterialsPurchasingBeanRemote() {
        try {
            Context c = new InitialContext();
            return (RetailProductsAndRawMaterialsPurchasingBeanRemote) c.lookup("java:global/IS3102_Project/IS3102_Project-ejb/RetailProductsAndRawMaterialsPurchasingBean!SCM.RetailProductsAndRawMaterialsPurchasing.RetailProductsAndRawMaterialsPurchasingBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
