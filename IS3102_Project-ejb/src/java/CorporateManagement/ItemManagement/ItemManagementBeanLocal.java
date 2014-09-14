package CorporateManagement.ItemManagement;

import EntityManager.FurnitureEntity;
import EntityManager.ItemEntity;
import EntityManager.BillOfMaterialEntity;
import EntityManager.RawMaterialEntity;
import EntityManager.ProductionGroupEntity;
import EntityManager.RetailProductEntity;
import javax.ejb.Local;
import javax.ejb.Remove;

@Local
public interface ItemManagementBeanLocal {

    public ItemEntity getItemBySKU(String SKU);

    public boolean addRawMaterial(String SKU, String name, String category, String description, Integer _length, Integer width, Integer height);

    public boolean editRawMaterial(String SKU, String name, String category, String description);

    public boolean removeRawMaterial(String SKU);

    public RawMaterialEntity viewRawMaterial(String SKU);

    public boolean addFurniture(String SKU, String name, String category, String description, String imageURL, Integer _length, Integer width, Integer height);

    public boolean editFurniture(String SKU, String name, String category, String description, String imageURL);

    public boolean removeFurniture(String SKU);

    public FurnitureEntity viewFurniture(String SKU);

    public boolean addRetailProduct(String SKU, String name, String category, String description, String imageURL, Integer _length, Integer width, Integer height);

    public boolean editRetailProduct(String internalItemCode, String name, String category, String description, String imageURL);

    public boolean removeRetailProduct(String SKU);

    public RetailProductEntity viewRetailProduct(String SKU);

    public boolean createBillOfMaterial(String name);

    public boolean editBillOfMaterial(String name);

    public boolean deleteBillOfMaterial(String bomName);

    public BillOfMaterialEntity viewBillOfMaterial(String name);

    public boolean createProductionGroup(String name);

    public boolean editProductionGroup(String name);

    public boolean deleteProductionGroup(String bomName);

    public ProductionGroupEntity viewProductionGroup(String name);
    /*
     public boolean addItem(String name, String materialID, String description, String imageURL);
     public boolean editItem(String name, String materialID, String description, String imageURL);
     public boolean removeItem(String itemName);
     public ItemEntity viewItem(String itemName);
     */

    @Remove
    public void remove();

}
