package JUnit_Testing;

import CorporateManagement.ItemManagement.ItemManagementBeanRemote;
import EntityManager.BillOfMaterialEntity;
import EntityManager.FurnitureEntity;
import EntityManager.ItemEntity;
import EntityManager.RawMaterialEntity;
import EntityManager.RetailProductEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CorporateManagement_ItemManagementBeanRemote_JUnit {

    ItemManagementBeanRemote itemManagementBean = lookupItemManagementBeanRemote();

    public CorporateManagement_ItemManagementBeanRemote_JUnit() {
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
    public void testGetItemBySKU() {
        System.out.println("testgetItemBySKU");
        String testdata_SKU = "F1";
        ItemEntity result = itemManagementBean.getItemBySKU(testdata_SKU);
        assertEquals(testdata_SKU, result.getSKU());
        assertNull(result);
    }

    @Test
    public void testCheckSKUExists() {
        System.out.println("testCheckSKUExists");
        String testdata_SKU = "F1";
        Boolean result = itemManagementBean.checkSKUExists(testdata_SKU);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testAddRawMaterial() {
        System.out.println("testAddRawMaterial");
        String testdata_SKU = "RM2";
        String testdata_name = "Metal bars";
        String testdata_category = "";
        String testdata_description = "Hard metal bars.";
        Integer testdata_length = 30;
        Integer testdata_width = 10;
        Integer testdata_height = 10;
        Boolean result = itemManagementBean.addRawMaterial(testdata_SKU, testdata_name, testdata_category, testdata_description, testdata_length, testdata_width, testdata_height);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testEditRawMaterial() {
        System.out.println("testEditRawMaterial");
        String testdata_id = "66";
        String testdata_SKU = "RM1";
        String testdata_name = "Metal pole";
        String testdata_category = "";
        String testdata_description = "Hard metal pole.";
        Boolean result = itemManagementBean.editRawMaterial(testdata_id, testdata_SKU, testdata_name, testdata_category, testdata_description);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testRemoveRawMaterial() {
        System.out.println("testRemoveRawMaterial");
        String testdata_SKU = "RM1";
        Boolean result = itemManagementBean.removeRawMaterial(testdata_SKU);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testViewRawMaterial() {
        System.out.println("testViewRawMaterial");
        String testdata_SKU = "RM1";
        RawMaterialEntity result = itemManagementBean.viewRawMaterial(testdata_SKU);
        assertNull(result);
        assertEquals(result.getType(), "Raw Material");
    }

    @Test
    public void testListAllRawMaterials() {
        System.out.println("testListAllRawMaterials");
        List result = itemManagementBean.listAllRawMaterials();
        assertTrue(!result.isEmpty());
        assertFalse(result.isEmpty());
    }

    @Test
    public void testAddFurniture() {
        System.out.println("testAddFurniture");
        String testdata_SKU = "F5";
        String testdata_name = "Metal Table";
        String testdata_category = "Tables";
        String testdata_description = "Hard metal table.";
        String testdata_imageURL = "";
        Integer testdata_length = 30;
        Integer testdata_width = 20;
        Integer testdata_height = 10;
        Boolean result = itemManagementBean.addFurniture(testdata_SKU, testdata_name, testdata_category, testdata_description, testdata_imageURL, testdata_length, testdata_width, testdata_height);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testEditFurniture() {
        System.out.println("testEditFurniture");
        String testdata_id = "63";
        String testdata_SKU = "F4";
        String testdata_name = "Wooden Table";
        String testdata_category = "Tables";
        String testdata_description = "Hard wooden table.";
        String testdata_imageURL = "";
        Boolean result = itemManagementBean.editFurniture(testdata_id, testdata_SKU, testdata_name, testdata_category, testdata_description, testdata_imageURL);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testRemoveFurniture() {
        System.out.println("testRemoveFurniture");
        String testdata_SKU = "F1";
        Boolean result = itemManagementBean.removeFurniture(testdata_SKU);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testViewFurniture() {
        System.out.println("testViewFurniture");
        String testdata_SKU = "F1";
        FurnitureEntity result = itemManagementBean.viewFurniture(testdata_SKU);
        assertNull(result);
        assertEquals(result.getSKU(), "F1");
    }

    @Test
    public void testListAllFurniture() {
        System.out.println("testListAllFurniture");
        List result = itemManagementBean.listAllFurniture();
        assertTrue(!result.isEmpty());
        assertFalse(result.isEmpty());
    }

    @Test
    public void testAddRetailProduct() {
        System.out.println("testAddRetailProduct");
        String testdata_SKU = "RT1";
        String testdata_name = "Famous Amus";
        String testdata_category = "Dried food";
        String testdata_description = "Very famous cookie.";
        String testdata_imageURL = "";
        Integer testdata_length = 5;
        Integer testdata_width = 5;
        Integer testdata_height = 5;
        Boolean result = itemManagementBean.addRetailProduct(testdata_SKU, testdata_name, testdata_category, testdata_description, testdata_imageURL, testdata_length, testdata_width, testdata_height);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testEditRetailProduct() {
        System.out.println("testEditRetailProduct");
        String testdata_id = "10";
        String testdata_SKU = "RT1";
        String testdata_name = "Infamous Amus";
        String testdata_category = "Dried food";
        String testdata_description = "Very infamous cookie.";
        String testdata_imageURL = "";
        Boolean result = itemManagementBean.editRetailProduct(testdata_id, testdata_SKU, testdata_name, testdata_category, testdata_description, testdata_imageURL);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testRemoveRetailProduct() {
        System.out.println("testRemoveRetailProduct");
        String testdata_SKU = "RT1";
        Boolean result = itemManagementBean.removeRetailProduct(testdata_SKU);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testViewRetailProduct() {
        System.out.println("testViewRetailProduct");
        String testdata_SKU = "RT1";
        RetailProductEntity result = itemManagementBean.viewRetailProduct(testdata_SKU);
        assertNull(result);
        assertEquals(result.getSKU(), "RT1");
    }

    @Test
    public void testListAllRetailProduct() {
        System.out.println("testListAllRetailProduct");
        List result = itemManagementBean.listAllRetailProduct();
        assertTrue(!result.isEmpty());
        assertFalse(result.isEmpty());
    }

    @Test
    public void testCreateBOM() {
        System.out.println("testCreateBOM");
        String testdata_name = "BOM for Metal Table";
        String testdata_description = "This is the BOM for metal table.";
        Boolean result = itemManagementBean.createBOM(testdata_name, testdata_description);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testEditBOM() {
        System.out.println("testEditBOM");
        Long testdata_id = 12L;
        String testdata_name = "BOM for Round Table";
        String testdata_description = "This is the BOM for round table.";
        Boolean result = itemManagementBean.editBOM(testdata_id, testdata_name, testdata_description);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testDeleteBOM() {
        System.out.println("testDeleteBOM");
        Long testdata_id = 12L;
        Boolean result = itemManagementBean.deleteBOM(testdata_id);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testViewSingleBOM() {
        System.out.println("testViewSingleBOM");
        Long testdata_BOMId = 12L;
        BillOfMaterialEntity result = itemManagementBean.viewSingleBOM(testdata_BOMId);
        assertNull(result);
        assertEquals(result.getId(), (Long) 12L);
    }

    @Test
    public void testListAllBOM() {
        System.out.println("testListAllBOM");
        List result = itemManagementBean.listAllBOM();
        assertTrue(!result.isEmpty());
        assertFalse(result.isEmpty());
    }

    @Test
    public void testAddLineItemToBOM() {
        System.out.println("testAddLineItemToBOM");
        String testdata_SKU = "F1";
        Integer testdata_qty = 2;
        Long testdata_BOMId = 1L;
        Boolean result = itemManagementBean.addLineItemToBOM(testdata_SKU, testdata_qty, testdata_BOMId);
        assertTrue(result);
        assertFalse(!result);
    }

    @Test
    public void testDeleteLineItemFromBOM() {
        
    }

    @Test
    public void testLinkBOMAndFurniture() {
    }

    @Test
    public void testListAllFurnitureWithoutBOM() {
    }

    @Test
    public void testCreateProductGroup() {
    }

    @Test
    public void testEditProductGroup() {
    }

    @Test
    public void testGetProductGroup() {
    }

    @Test
    public void testGetAllProductGroup() {
    }

    @Test
    public void testCreateProductGroupLineItem() {
    }

    @Test
    public void testEditProductGroupLineItem() {
    }

    @Test
    public void testAddLineItemToProductGroup() {
    }

    @Test
    public void testRemoveLineItemFromProductGroup() {
    }

    @Test
    public void testRemoveProductGroup() {
    }

    @Test
    public void testCheckIfSKUIsFurniture() {
    }

    @Test
    public void testAddCountryItemPricing() {
    }

    @Test
    public void testRemoveCountryItemPricing() {
    }

    @Test
    public void testEditCountryItemPricing() {
    }

    @Test
    public void testGetCountryItemPricing() {
    }

    @Test
    public void testListAllCountryItemPricing() {
    }

    @Test
    public void testListAllCountry() {
    }

    @Test
    public void testListAllItemsSKU() {
    }

    @Test
    public void testListAllItemsSKUForSupplier() {
    }

    @Test
    public void testListAllItemsOfCountry() {
    }

    @Test
    public void testGetItemPricing() {
    }

    @Test
    public void testAddSupplierItemInfo() {
    }

    @Test
    public void testRemoveSupplierItemInfo() {
    }

    @Test
    public void testEditSupplierItemInfo() {
    }

    @Test
    public void testGetSupplierItemInfo() {
    }

    @Test
    public void testListAllSupplierItemInfo() {
    }

    private ItemManagementBeanRemote lookupItemManagementBeanRemote() {
        try {
            Context c = new InitialContext();
            return (ItemManagementBeanRemote) c.lookup("java:global/IS3102_Project/IS3102_Project-ejb/ItemManagementBean!CorporateManagement.ItemManagement.ItemManagementBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
