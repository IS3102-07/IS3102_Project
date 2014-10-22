package JUnit_Testing;

import SCM.RetailProductsAndRawMaterialsPurchasing.RetailProductsAndRawMaterialsPurchasingBeanRemote;
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
    public void testCreatePurchaseOrder() {
    }

    @Test
    public void testUpdatePurchaseOrder() {
    }

    @Test
    public void testUpdatePurchaseOrderStatus() {
    }

    @Test
    public void testAddLineItemToPurchaseOrder() {
    }

    @Test
    public void testRemoveLineItemFromPurchaseOrder() {
    }

    @Test
    public void testUpdateLineItemFromPurchaseOrder() {
    }

    @Test
    public void testGetPurchaseOrderById() {
    }

    @Test
    public void testGetPurchaseOrderListByStatus() {
    }

    @Test
    public void testGetPurchaseOrderList() {
    }

    @Test
    public void testCheckSKUExists() {
    }

    @Test
    public void testGetPurchaseOrderListByWarehouseId() {
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
