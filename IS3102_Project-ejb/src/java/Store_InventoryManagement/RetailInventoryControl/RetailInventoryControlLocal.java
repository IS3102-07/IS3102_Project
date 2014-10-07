package Store_InventoryManagement.RetailInventoryControl;

import EntityManager.FurnitureEntity;
import EntityManager.ItemEntity;
import EntityManager.BillOfMaterialEntity;
import EntityManager.RawMaterialEntity;
import EntityManager.ProductGroupEntity;
import EntityManager.RetailProductEntity;
import java.util.List;
import javax.ejb.Local;

@Local
public interface RetailInventoryControlLocal {

    public ItemEntity getItemBySKU(String SKU);
    public boolean checkSKUExists(String SKU);
    public RawMaterialEntity viewRawMaterial(String SKU);
    public List<RawMaterialEntity> listAllRawMaterials();

    public FurnitureEntity viewFurniture(String SKU);
    public List<FurnitureEntity> listAllFurniture();
    
    public RetailProductEntity viewRetailProduct(String SKU);
    public List<RetailProductEntity> listAllRetailProduct();
   
    public BillOfMaterialEntity viewSingleBOM(Long BOMId);
    public List<BillOfMaterialEntity> listAllBOM();
    
    public ProductGroupEntity getProductGroup(Long id);
    public List<ProductGroupEntity> getAllProductGroup();    
    public Boolean checkIfSKUIsFurniture(String SKU);
}
