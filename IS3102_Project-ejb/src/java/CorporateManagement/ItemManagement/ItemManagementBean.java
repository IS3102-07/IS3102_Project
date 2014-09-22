package CorporateManagement.ItemManagement;

import javax.ejb.Stateless;
import EntityManager.ItemEntity;
import EntityManager.RawMaterialEntity;
import EntityManager.ProductGroupEntity;
import EntityManager.RetailProductEntity;
import EntityManager.FurnitureEntity;
import EntityManager.BillOfMaterialEntity;
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

    public boolean createBOM(String name, String description) {//
        System.out.println("createBillOfMaterial() called with name:" + name);

        Long id;
        try {
//            BillOfMaterialEntity billOfMaterialEntity = new BillOfMaterialEntity();
//            billOfMaterialEntity.create(name);
//            em.persist(billOfMaterialEntity);
//            id = billOfMaterialEntity.getId();
            //System.out.println("Bill Of Material Name \"" + name + "\" registered successfully as id:" + id);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to register bill of material:\n" + ex);
            return false;
        }
    }

    public boolean editBOM(String name) {//
        System.out.println("editBillOfMaterial() called with bill of material name:" + name);

        Long id;
        try {
            Query q = em.createQuery("SELECT t FROM BillOfMaterialEntity t");

//            for (Object o : q.getResultList()) {
//                BillOfMaterialEntity i = (BillOfMaterialEntity) o;
//                if (i.getName().equalsIgnoreCase(name)) {
//                    i.setName(name);
//                    em.flush();
//                    System.out.println("\nServer updated bill of material:\n" + name);
//                    return true;
//                }
//            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to update bill of material:\n" + ex);
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
    public BillOfMaterialEntity viewSingleBOM(String name) {
        System.out.println("viewBillOfMaterial() called with name:" + name);
        try {
            Query q = em.createQuery("SELECT t FROM BillOfMaterialEntity t");

//            for (Object o : q.getResultList()) {
//                BillOfMaterialEntity i = (BillOfMaterialEntity) o;
//                if (i.getName().equalsIgnoreCase(name)) {
//                    System.out.println("\nServer returns bill of material:\n" + name);
//                    return i;
//                }
//            }
            return null; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to view bill of material:\n" + ex);
            return null;
        }
    }

    public boolean createProductionGroup(String name) {
        System.out.println("createProductionGroup() called with name:" + name);

        Long id;
        try {
            ProductGroupEntity productionGroupEntity = new ProductGroupEntity();
            productionGroupEntity.create(name);
            em.persist(productionGroupEntity);
            id = productionGroupEntity.getId();
            System.out.println("Production Group Name \"" + name + "\" registered successfully as id:" + id);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to register production group:\n" + ex);
            return false;
        }
    }

    public boolean editProductionGroup(String name) {
        System.out.println("editProductionGroup() called with production group name:" + name);

        Long id;
        try {
            Query q = em.createQuery("SELECT t FROM ProductionGroupEntity t");

            for (Object o : q.getResultList()) {
                ProductGroupEntity i = (ProductGroupEntity) o;
                if (i.getName().equalsIgnoreCase(name)) {
                    i.setName(name);
                    em.flush();
                    System.out.println("\nServer updated production group:\n" + name);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to update production group:\n" + ex);
            return false;
        }
    }

    public boolean deleteProductionGroup(String name) {
        System.out.println("deleteProductionGroup() called with bomName:" + name);
        try {
            Query q = em.createQuery("SELECT t FROM ProductionGroupEntity t");

            for (Object o : q.getResultList()) {
                ProductGroupEntity i = (ProductGroupEntity) o;
                if (i.getName().equalsIgnoreCase(name)) {
                    em.remove(i);
                    em.flush();
                    System.out.println("\nServer removed production group:\n" + name);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove production group:\n" + ex);
            return false;
        }
    }

    public ProductGroupEntity viewProductionGroup(String name) {
        System.out.println("viewProductionGroup() called with name:" + name);
        try {
            Query q = em.createQuery("SELECT t FROM ProductionGroupEntity t");

            for (Object o : q.getResultList()) {
                ProductGroupEntity i = (ProductGroupEntity) o;
                if (i.getName().equalsIgnoreCase(name)) {
                    System.out.println("\nServer returns production group:\n" + name);
                    return i;
                }
            }
            return null; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to view production group:\n" + ex);
            return null;
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

}
