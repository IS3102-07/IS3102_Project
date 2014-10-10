package StoreTransaction.RetailInventoryControl;

import EntityManager.FurnitureEntity;
import EntityManager.ItemEntity;
import EntityManager.RawMaterialEntity;
import EntityManager.RetailProductEntity;
import java.util.List;
import javax.ejb.Local;

@Local
public interface RetailInventoryControlBeanLocal {

    public ItemEntity getItemBySKU(String SKU);
    public boolean checkSKUExists(String SKU);
    public RawMaterialEntity viewRawMaterial(String SKU);
    public List<RawMaterialEntity> listAllRawMaterials();

    public FurnitureEntity viewFurniture(String SKU);
    public List<FurnitureEntity> listAllFurniture();
    
    public RetailProductEntity viewRetailProduct(String SKU);
    public List<RetailProductEntity> listAllRetailProduct();
    
    public Boolean checkIfSKUIsFurniture(String SKU);
}
