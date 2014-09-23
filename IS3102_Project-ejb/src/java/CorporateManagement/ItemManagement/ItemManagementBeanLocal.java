package CorporateManagement.ItemManagement;

import EntityManager.FurnitureEntity;
import EntityManager.ItemEntity;
import EntityManager.BillOfMaterialEntity;
import EntityManager.LineItemEntity;
import EntityManager.RawMaterialEntity;
import EntityManager.ProductGroupEntity;
import EntityManager.ProductGroupLineItemEntity;
import EntityManager.RetailProductEntity;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remove;

@Local
public interface ItemManagementBeanLocal {

    public ItemEntity getItemBySKU(String SKU);
    public boolean checkSKUExists(String SKU);
    public boolean addRawMaterial(String SKU, String name, String category, String description, Integer _length, Integer width, Integer height);
    public boolean editRawMaterial(String id, String SKU, String name, String category, String description);
    public boolean removeRawMaterial(String SKU);
    public RawMaterialEntity viewRawMaterial(String SKU);
    public List<RawMaterialEntity> listAllRawMaterials();

    public boolean addFurniture(String SKU, String name, String category, String description, String imageURL, Integer _length, Integer width, Integer height);
    public boolean editFurniture(String id, String SKU, String name, String category, String description, String imageURL);
    public boolean removeFurniture(String SKU);
    public FurnitureEntity viewFurniture(String SKU);
    public List<FurnitureEntity> listAllFurniture();
    
    public boolean addRetailProduct(String SKU, String name, String category, String description, String imageURL, Integer _length, Integer width, Integer height);
    public boolean editRetailProduct(String id, String SKU, String name, String category, String description, String imageURL);
    public boolean removeRetailProduct(String SKU);
    public RetailProductEntity viewRetailProduct(String SKU);
    public List<RetailProductEntity> listAllRetailProduct();
   
    public boolean createBOM(String name, String description);
    public boolean editBOM(Long BOMId, String name, String description);
    public boolean deleteBOM(Long BOMId);
    public BillOfMaterialEntity viewSingleBOM(Long BOMId);
    public List<BillOfMaterialEntity> listAllBOM();
    
    public boolean addLineItemToBOM(String SKU, Integer qty, Long BOMId);
    public boolean deleteLineItemFromBOM(Long lineItemId, Long BOMId);
    public boolean linkBOMAndFurniture(Long BOMId, Long FurnitureId);
    /*
     public boolean addItem(String name, String materialID, String description, String imageURL);
     public boolean editItem(String name, String materialID, String description, String imageURL);
     public boolean removeItem(String itemName);
     public ItemEntity viewItem(String itemName);
     */
    
    public ProductGroupEntity createProductGroup(String name, Integer workhours);
    public Boolean editProductGroup(Long productGroupID, String name, Integer workhours);
    public ProductGroupEntity getProductGroup(Long id);
    public List<ProductGroupEntity> getAllProductGroup();    
    public ProductGroupLineItemEntity createProductGroupLineItem(String furnitureSKU, double percent);    
    public Boolean editProductGroupLineItem(Long productGroupLineItemID, String SKU, double percent);
    public Boolean addLineItemToProductGroup(Long productGroupId, Long lineItemId);
    public Boolean removeLineItemFromProductGroup(Long productGroupId, Long lineItemId);
    
    public Boolean checkIfSKUIsFurniture(String SKU);

    @Remove
    public void remove();
}
