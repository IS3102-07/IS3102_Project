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
    public void createPurchaseOrder() {
    }

    @Test
    public void updatePurchaseOrder() {
    }

    @Test
    public void updatePurchaseOrderStatus() {
    }

    @Test
    public void addLineItemToPurchaseOrder() {
    }

    @Test
    public void removeLineItemFromPurchaseOrder() {
    }

    @Test
    public void updateLineItemFromPurchaseOrder() {
    }

    @Test
    public void getPurchaseOrderById() {
    }

    @Test
    public void getPurchaseOrderListByStatus() {
    }

    @Test
    public void getPurchaseOrderList() {
    }

    @Test
    public void checkSKUExists() {
    }

    @Test
    public void getPurchaseOrderListByWarehouseId() {
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
