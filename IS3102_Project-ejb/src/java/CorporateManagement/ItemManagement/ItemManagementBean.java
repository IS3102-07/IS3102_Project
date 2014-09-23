package CorporateManagement.ItemManagement;

import javax.ejb.Stateless;
import EntityManager.ItemEntity;
import EntityManager.RawMaterialEntity;
import EntityManager.ProductGroupEntity;
import EntityManager.RetailProductEntity;
import EntityManager.FurnitureEntity;
import EntityManager.BillOfMaterialEntity;
import EntityManager.LineItemEntity;
import EntityManager.ProductGroupLineItemEntity;
import EntityManager.StorageBinEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remove;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ItemManagementBean implements ItemManagementBeanLocal {
    
    @PersistenceContext
    private EntityManager em;
    
    public boolean addRawMaterial(String SKU, String name, String category, String description, Integer _length, Integer width, Integer height) {
        System.out.println("addRawMaterial() called with SKU:" + SKU);
        try {
            RawMaterialEntity rawMaterialEntity = new RawMaterialEntity(SKU, name, category, description, _length, width, height);
            em.persist(rawMaterialEntity);
            System.out.println("Raw Material name \"" + name + "\" added successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to add new raw material:\n" + ex);
            return false;
        }
    }
    
    public boolean editRawMaterial(String id, String SKU, String name, String category, String description) {
        System.out.println("editRawMaterial() called with SKU:" + SKU);
        try {
            RawMaterialEntity i = em.find(RawMaterialEntity.class, Long.valueOf(id));
            i.setName(name);
            i.setCategory(category);
            i.setDescription(description);
            em.merge(i);
            em.flush();
            System.out.println("\nServer updated raw material:\n" + name);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to update raw material:\n" + ex);
            return false;
        }
    }
    
    public boolean removeRawMaterial(String SKU) {
        System.out.println("removeRawMaterial() called with SKU:" + SKU);
        try {
            em.remove(em.getReference(RawMaterialEntity.class, Long.valueOf(SKU)));
            System.out.println("Furniture removed succesfully");
            return false; //Could not find to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove raw material:\n" + ex);
            return false;
        }
    }
    
    public RawMaterialEntity viewRawMaterial(String SKU) {
        System.out.println("viewRawMaterial() called with SKU:" + SKU);
        try {
            Query q = em.createQuery("SELECT t FROM RawMaterialEntity t");
            
            for (Object o : q.getResultList()) {
                RawMaterialEntity i = (RawMaterialEntity) o;
                if (i.getSKU().equalsIgnoreCase(SKU)) {
                    System.out.println("\nServer returns raw material:\n" + SKU);
                    return i;
                }
            }
            return null; //Could not find
        } catch (Exception ex) {
            System.out.println("\nServer failed to view raw material:\n" + ex);
            return null;
        }
    }
    
    public boolean addFurniture(String SKU, String name, String category, String description, String imageURL, Integer _length, Integer width, Integer height) {
        System.out.println("addFurniture() called with SKU:" + SKU);
        try {
            FurnitureEntity furnitureEntity = new FurnitureEntity(SKU, name, category, description, imageURL, _length, width, height);
            em.persist(furnitureEntity);
            System.out.println("Furniture name \"" + name + "\" added successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to add new furniture:\n" + ex);
            return false;
        }
    }
    
    public boolean editFurniture(String id, String SKU, String name, String category, String description, String imageURL) {
        System.out.println("editFurniture() called with SKU:" + SKU + " id : " + id);
        try {
            FurnitureEntity i = em.find(FurnitureEntity.class, Long.valueOf(id));
            System.out.println("i name is " + i.getName() + " image url is : " + imageURL);
            i.setName(name);
            i.setCategory(category);
            i.setDescription(description);
            i.setImageURL(imageURL);
            em.merge(i);
            em.flush();
            System.out.println("\nServer updated furniture:\n" + name);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to update furniture:\n" + ex);
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean removeFurniture(String SKU) {
        System.out.println("removeFurniture() called with SKU:" + SKU);
        try {
            em.remove(em.getReference(FurnitureEntity.class, Long.valueOf(SKU)));
            System.out.println("Furniture removed succesfully");
            return false; //Could not find to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove furniture:\n" + ex);
            return false;
        }
    }
    
    public FurnitureEntity viewFurniture(String SKU) {
        System.out.println("viewRawMaterial() called with SKU:" + SKU);
        try {
            Query q = em.createQuery("SELECT t FROM FurnitureEntity t");
            
            for (Object o : q.getResultList()) {
                FurnitureEntity i = (FurnitureEntity) o;
                if (i.getSKU().equalsIgnoreCase(SKU)) {
                    System.out.println("\nServer returns furniture:\n" + SKU);
                    return i;
                }
            }
            return null; //Could not find
        } catch (Exception ex) {
            System.out.println("\nServer failed to view furniture:\n" + ex);
            return null;
        }
    }
    
    public boolean addRetailProduct(String SKU, String name, String category, String description, String imageURL, Integer _length, Integer width, Integer height) {
        System.out.println("addRetailProduct() called with SKU:" + SKU);
        try {
            RetailProductEntity retailProductEntity = new RetailProductEntity(SKU, name, category, description, imageURL, _length, width, height);
            em.persist(retailProductEntity);
            System.out.println("Retail product name \"" + name + "\" added successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to add new furniture:\n" + ex);
            return false;
        }
    }
    
    public boolean editRetailProduct(String id, String SKU, String name, String category, String description, String imageURL) {
        System.out.println("editRetailProduct() called with SKU:" + SKU);
        try {
            RetailProductEntity i = em.find(RetailProductEntity.class, Long.valueOf(id));
            i.setName(name);
            i.setCategory(category);
            i.setDescription(description);
            i.setImageURL(imageURL);
            em.merge(i);
            em.flush();
            System.out.println("\nServer updated retail product:\n" + name);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to update retail product:\n" + ex);
            return false;
        }
    }
    
    public boolean removeRetailProduct(String SKU) {
        System.out.println("removeRetailProduct() called with SKU:" + SKU);
        try {
            em.remove(em.getReference(RetailProductEntity.class, Long.valueOf(SKU)));
            System.out.println("Retail product removed succesfully");
            return false; //Could not find to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove retail product:\n" + ex);
            return false;
        }
    }
    
    public RetailProductEntity viewRetailProduct(String SKU) {
        System.out.println("viewRetailProduct() called with SKU:" + SKU);
        try {
            Query q = em.createQuery("SELECT t FROM RetailProductEntity t");
            
            for (Object o : q.getResultList()) {
                RetailProductEntity i = (RetailProductEntity) o;
                if (i.getSKU().equalsIgnoreCase(SKU)) {
                    System.out.println("\nServer returns retail product:\n" + SKU);
                    return i;
                }
            }
            return null; //Could not find
        } catch (Exception ex) {
            System.out.println("\nServer failed to view retail product:\n" + ex);
            return null;
        }
    }
    
    @Override
    public boolean createBOM(String name, String description) {//
        System.out.println("createBillOfMaterial() called with name:" + name);
        try {
            BillOfMaterialEntity BOM = new BillOfMaterialEntity();
            BOM.setDescription(description);
            BOM.setName(name);
            em.persist(BOM);
            
            System.out.println("Bill Of Material Name \"" + name + "\" registered successfully as id:" + BOM.getId());
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to createBOM():\n" + ex);
            return false;
        }
    }
    
    @Override
    public boolean editBOM(Long BOMId, String name, String description) {//
        System.out.println("editBOM() called with bill of material name:" + name + "and description: " + description);
        try {
            BillOfMaterialEntity BOM = em.find(BillOfMaterialEntity.class, BOMId);
            BOM.setName(name);
            BOM.setDescription(description);
            em.persist(BOM);
            System.out.println("editBOM() is successful.");
            return true;            
        } catch (Exception ex) {
            System.out.println("\nServer failed to editBOM():\n" + ex);
            return false;
        }
    }
    
    @Override
    public boolean deleteBOM(Long BOMId) {
        System.out.println("deleteBillOfMaterial() called with bomName:" + BOMId);
        try {
            BillOfMaterialEntity BOM = em.find(BillOfMaterialEntity.class, BOMId);
            em.remove(BOM);
            System.out.println("deleteBillOfMaterial() is successful.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove bill of material:\n" + ex);
            return false;
        }
    }
    
    @Override
    public BillOfMaterialEntity viewSingleBOM(Long BOMId) {
        System.out.println("viewBillOfMaterial() called with id:" + BOMId);
        try {
            BillOfMaterialEntity BOM = em.find(BillOfMaterialEntity.class, BOMId);
            System.out.println("viewSingleBOM() is successful.");
            return BOM;
        } catch (Exception ex) {
            System.out.println("\nServer failed to view bill of material:\n" + ex);
            return null;
        }
    }
    
    @Override
    public boolean addLineItemToBOM(String SKU, Integer qty, Long BOMId) {
        System.out.println("addLineItemToBOM() called with id:" + BOMId);
        try {
            LineItemEntity lineItem = new LineItemEntity(getItemBySKU(SKU), qty, "");
            BillOfMaterialEntity BOM = em.find(BillOfMaterialEntity.class, BOMId);
            BOM.getListOfLineItems().add(lineItem);
            System.out.println("addLineItemToBOM() is successful.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to addLineItemToBOM():\n" + ex);
            return false;
        }
    }
    
    @Override
    public boolean deleteLineItemFromBOM(Long lineItemId, Long BOMId) {
        System.out.println("deleteLineItemFromBOM() called with id:" + BOMId);
        try {
            LineItemEntity lineItem = em.find(LineItemEntity.class, lineItemId);
            BillOfMaterialEntity BOM = em.find(BillOfMaterialEntity.class, BOMId);
            BOM.getListOfLineItems().remove(lineItem);
            System.out.println("Line item id:" + lineItemId + " is removed from BOM");
            em.remove(lineItem);
            System.out.println("Line item id:" + lineItemId + " is removed from Line Item");
            System.out.println("deleteLineItemFromBOM() is successful.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to deleteLineItemFromBOM():\n" + ex);
            return false;
        }
    }
    
    @Override
    public boolean linkBOMAndFurniture(Long BOMId, Long FurnitureId) {
        System.out.println("linkBOMAndFurniture() called with id:" + BOMId);
        try {
            BillOfMaterialEntity BOM = em.find(BillOfMaterialEntity.class, BOMId);
            FurnitureEntity furniture = em.find(FurnitureEntity.class, FurnitureId);
            furniture.setBOM(BOM);
            System.out.println("linkBOMAndFurniture() is successful.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to linkBOMAndFurniture():\n" + ex);
            return false;
        }
    }
    
    public List<RawMaterialEntity> listAllRawMaterials() {
        System.out.println("listAllRawMaterials() called.");
        try {
            Query q = em.createQuery("SELECT t FROM RawMaterialEntity t");
            List<RawMaterialEntity> rawMaterialEntity = q.getResultList();
            return rawMaterialEntity;
        } catch (Exception ex) {
            System.out.println("\nServer failed to list all raw materials:\n" + ex);
            return null;
        }
    }
    
    public List<FurnitureEntity> listAllFurniture() {
        System.out.println("listAllFurniture() called.");
        try {
            Query q = em.createQuery("SELECT t FROM FurnitureEntity t");
            List<FurnitureEntity> furnitureEntity = q.getResultList();
            return furnitureEntity;
        } catch (Exception ex) {
            System.out.println("\nServer failed to list all furniture:\n" + ex);
            return null;
        }
    }
    
    public List<RetailProductEntity> listAllRetailProduct() {
        System.out.println("listAllRetailProduct() called.");
        try {
            Query q = em.createQuery("SELECT t FROM RetailProductEntity t");
            List<RetailProductEntity> retailProductEntity = q.getResultList();
            return retailProductEntity;
        } catch (Exception ex) {
            System.out.println("\nServer failed to list all retail product:\n" + ex);
            return null;
        }
    }
    
    public List<BillOfMaterialEntity> listAllBOM() {
        System.out.println("listAllBOM() called.");
        try {
            Query q = em.createQuery("SELECT b FROM BillOfMaterialEntity b");
            List<BillOfMaterialEntity> billOfMaterialEntity = q.getResultList();
            System.out.println("listAllBOM() is successful.");
            return billOfMaterialEntity;
        } catch (Exception ex) {
            System.out.println("\nServer failed to list all BOM:\n" + ex);
            return null;
        }
    }
    
    public List<ProductGroupEntity> listAllProductionGroup() {
        System.out.println("listAllProductionGroup() called.");
        try {
            Query q = em.createQuery("SELECT t FROM ProductionGroupEntity t");
            List<ProductGroupEntity> productionGroupEntity = q.getResultList();
            return productionGroupEntity;
        } catch (Exception ex) {
            System.out.println("\nServer failed to list all production groups:\n" + ex);
            return null;
        }
    }
    
    @Override
    public ItemEntity getItemBySKU(String SKU) {
//        Query q = em.createQuery("Select i from ItemEntity i where i.SKU=:SKU");
//        q.setParameter("SKU", SKU);
//        if(q.getSingleResult() instanceof ItemEntity){
//            
//        }
        return null;
    }
    
    @Override
    public boolean checkSKUExists(String SKU) {
        try {
            Query q = em.createQuery("Select i from ItemEntity i where i.SKU=:SKU");
            q.setParameter("SKU", SKU);
            q.getSingleResult();
            return true;
        } catch (NoResultException n) {
            System.out.println("\nServer return no result:\n" + n);
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to perform checkSKUExists:\n" + ex);
            return false;
        }
    }
    
    @Override
    @Remove
    public void remove() {
        System.out.println("Item Management Bean is removed.");
    }
    
    @Override
    public ProductGroupEntity createProductGroup(String name, Integer workhours) {
        try {
            Query q = em.createQuery("select pg from ProductGroupEntity pg where pg.productGroupName = ?1").setParameter(1, name);
            if (q.getResultList().isEmpty()) {
                ProductGroupEntity prouductGroup = new ProductGroupEntity(name, workhours);
                em.persist(name);
                return prouductGroup;
            } else {
                return (ProductGroupEntity) q.getResultList().get(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public ProductGroupEntity getProductGroup(Long id) {
        try {
            return em.find(ProductGroupEntity.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<ProductGroupEntity> getAllProductGroup() {
        try {
            Query q = em.createQuery("select pg from ProductGroupEntity pg");
            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    @Override
    public ProductGroupLineItemEntity createProductGroupLineItem(String SKU, double percent) {
        System.out.println("createProductGroupLineItem() called");
        try {
            Query q = em.createQuery("Select f from FurnitureEntity f where f.SKU=:SKU");
            q.setParameter("SKU", SKU);
            FurnitureEntity furniture = (FurnitureEntity) q.getSingleResult();
            ProductGroupLineItemEntity lineItem = new ProductGroupLineItemEntity();
            lineItem.setFurniture(furniture);
            lineItem.setPercent(percent);
            em.persist(lineItem);
            return lineItem;
        } catch (NoResultException ex) {
            System.out.println("Cuold not find furniture with SKU.");
        }catch (Exception ex) {
            System.out.println("Failed to createProductGroupLineItem()");
            ex.printStackTrace();
        }
        return null;
    }
    
    public Boolean deleteProductGroupLineItem(Long id) {
        try {
            em.remove(em.find(ProductGroupLineItemEntity.class, id));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    @Override
    public Boolean editProductGroupLineItem(Long id, double percent) {
        try {
            ProductGroupLineItemEntity lineItem = em.find(ProductGroupLineItemEntity.class, id);
            lineItem.setPercent(percent);
            em.merge(lineItem);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    @Override
    public Boolean addLineItemToProductGroup(Long productGroupId, Long lineItemId) {
        try {
            ProductGroupEntity productGroup = em.find(ProductGroupEntity.class, productGroupId);
            ProductGroupLineItemEntity lineItem = em.find(ProductGroupLineItemEntity.class, lineItemId);
            lineItem.setProductGroup(productGroup);
            productGroup.getLineItemList().add(lineItem);
            em.merge(productGroup);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    @Override
    public Boolean removeLineItemFromProductGroup(Long productGroupId, Long lineItemId) {
        try {
            ProductGroupEntity productGroup = em.find(ProductGroupEntity.class, productGroupId);
            ProductGroupLineItemEntity lineItem = em.find(ProductGroupLineItemEntity.class, lineItemId);
            productGroup.getLineItemList().remove(lineItem);
            em.merge(productGroup);
            this.deleteProductGroupLineItem(lineItemId);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
}
