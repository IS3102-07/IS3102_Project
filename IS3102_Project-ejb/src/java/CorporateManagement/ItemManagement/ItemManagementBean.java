/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CorporateManagement.ItemManagement;

import javax.ejb.Stateless;
import EntityManager.ItemEntity;
import EntityManager.RawMaterialEntity;
import EntityManager.ProductionGroupEntity;
import EntityManager.RetailProductEntity;
import EntityManager.FurnitureEntity;
import EntityManager.BillOfMaterialEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Loi Liang Yang
 */
@Stateless
public class ItemManagementBean implements ItemManagementBeanLocal {

    @PersistenceContext
    private EntityManager em;
    
    public boolean addRawMaterial(String name) {
        System.out.println("addRawMaterial() called with raw material:" + name);

        Long id;
        try {
            RawMaterialEntity rawMaterialEntity = new RawMaterialEntity();
            rawMaterialEntity.create(name);
            em.persist(rawMaterialEntity);
            id = rawMaterialEntity.getId();
            System.out.println("Raw Material Name \"" + name + "\" registered successfully as id:" + id);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to register raw material:\n" + ex);
            return false;
        }
    }

    public boolean editRawMaterial(String name) {
        System.out.println("editRawMaterial() called with name:" + name);

        Long id;
        try {
            Query q = em.createQuery("SELECT t FROM RawMaterialEntity t");

            for (Object o : q.getResultList()) {
                RawMaterialEntity i = (RawMaterialEntity) o;
                if (i.getName().equalsIgnoreCase(name)) {
                    i.setName(name);
                    em.flush();
                    System.out.println("\nServer updated raw material:\n" + name);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to update raw material:\n" + ex);
            return false;
        }
    }
    
    public boolean removeRawMaterial(String name) {
        System.out.println("removeRawMaterial() called with name:" + name);
        try {
            Query q = em.createQuery("SELECT t FROM RawMaterialEntity t");

            for (Object o : q.getResultList()) {
                RawMaterialEntity i = (RawMaterialEntity) o;
                if (i.getName().equalsIgnoreCase(name)) {
                    em.remove(i);
                    em.flush();
                    System.out.println("\nServer removed raw material:\n" + name);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove raw material:\n" + ex);
            return false;
        }
    }
    
    public RawMaterialEntity viewRawMaterial(String name) {
        System.out.println("viewRawMaterial() called with itemName:" + name);
        try {
            Query q = em.createQuery("SELECT t FROM RawMaterialEntity t");

            for (Object o : q.getResultList()) {
                RawMaterialEntity i = (RawMaterialEntity) o;
                if (i.getName().equalsIgnoreCase(name)) {
                    System.out.println("\nServer returns raw material:\n" + name);
                    return i;
                }
            }
            return null; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to view raw material:\n" + ex);
            return null;
        }
    }
    
    public boolean addFurniture(String name) {
        System.out.println("addFurniture() called with furniture:" + name);

        Long id;
        try {
            FurnitureEntity furnitureEntity = new FurnitureEntity();
            furnitureEntity.create(name);
            em.persist(furnitureEntity);
            id = furnitureEntity.getId();
            System.out.println("Furniture Name \"" + name + "\" registered successfully as id:" + id);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to register furniture:\n" + ex);
            return false;
        }
    }

    public boolean editFurniture(String name) {
        System.out.println("editFurniture() called with name:" + name);

        Long id;
        try {
            Query q = em.createQuery("SELECT t FROM FurnitureEntity t");

            for (Object o : q.getResultList()) {
                FurnitureEntity i = (FurnitureEntity) o;
                if (i.getName().equalsIgnoreCase(name)) {
                    i.setName(name);
                    em.flush();
                    System.out.println("\nServer updated furniture:\n" + name);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to update furniture:\n" + ex);
            return false;
        }
    }
    
    public boolean removeFurniture(String name) {
        System.out.println("removeFurniture() called with name:" + name);
        try {
            Query q = em.createQuery("SELECT t FROM FurnitureEntity t");

            for (Object o : q.getResultList()) {
                FurnitureEntity i = (FurnitureEntity) o;
                if (i.getName().equalsIgnoreCase(name)) {
                    em.remove(i);
                    em.flush();
                    System.out.println("\nServer removed furniture:\n" + name);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove furniture:\n" + ex);
            return false;
        }
    }
    
    public FurnitureEntity viewFurniture(String name) {
        System.out.println("viewFurniture() called with name:" + name);
        try {
            Query q = em.createQuery("SELECT t FROM FurnitureEntity t");

            for (Object o : q.getResultList()) {
                FurnitureEntity i = (FurnitureEntity) o;
                if (i.getName().equalsIgnoreCase(name)) {
                    System.out.println("\nServer returns furniture:\n" + name);
                    return i;
                }
            }
            return null; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to view furniture:\n" + ex);
            return null;
        }
    }
    
    public boolean addRetailProduct(String name) {
        System.out.println("addRetailProduct() called with name:" + name);

        Long id;
        try {
            RetailProductEntity retailProductEntity = new RetailProductEntity();
            retailProductEntity.create(name);
            em.persist(retailProductEntity);
            id = retailProductEntity.getId();
            System.out.println("Retail Product Name \"" + name + "\" registered successfully as id:" + id);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to register retail product name:\n" + ex);
            return false;
        }
    }

    public boolean editRetailProduct(String name) {
        System.out.println("editRetailProduct() called with name:" + name);

        Long id;
        try {
            Query q = em.createQuery("SELECT t FROM RetailProductEntity t");

            for (Object o : q.getResultList()) {
                RetailProductEntity i = (RetailProductEntity) o;
                if (i.getName().equalsIgnoreCase(name)) {
                    i.setName(name);
                    em.flush();
                    System.out.println("\nServer updated retail product:\n" + name);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to update retail product:\n" + ex);
            return false;
        }
    }
    
    public boolean removeRetailProduct(String name) {
        System.out.println("removeRetailProduct() called with name:" + name);
        try {
            Query q = em.createQuery("SELECT t FROM RetailProductEntity t");

            for (Object o : q.getResultList()) {
                RetailProductEntity i = (RetailProductEntity) o;
                if (i.getName().equalsIgnoreCase(name)) {
                    em.remove(i);
                    em.flush();
                    System.out.println("\nServer removed retail product:\n" + name);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove retail product:\n" + ex);
            return false;
        }
    }
    
    public RetailProductEntity viewRetailProduct(String name) {
        System.out.println("viewRetailProduct() called with name:" + name);
        try {
            Query q = em.createQuery("SELECT t FROM RetailProductEntity t");

            for (Object o : q.getResultList()) {
                RetailProductEntity i = (RetailProductEntity) o;
                if (i.getName().equalsIgnoreCase(name)) {
                    System.out.println("\nServer returns retail product:\n" + name);
                    return i;
                }
            }
            return null; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to view retail product:\n" + ex);
            return null;
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    /*
    public boolean addItem(String name, String materialID, String description, String imageURL) {
        System.out.println("addItem() called with item:" + name + materialID + description + imageURL);

        Long id;
        try {
            ItemEntity itemEntity = new ItemEntity();
            itemEntity.create(name, materialID, description, imageURL);
            em.persist(itemEntity);
            id = itemEntity.getId();
            System.out.println("Item Name \"" + name + "\" registered successfully as id:" + id);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to register item:\n" + ex);
            return false;
        }
    }

    public boolean editItem(String name, String materialID, String description, String imageURL) {
        System.out.println("editItem() called with item:" + name);

        Long id;
        try {
            Query q = em.createQuery("SELECT t FROM ItemEntity t");

            for (Object o : q.getResultList()) {
                ItemEntity i = (ItemEntity) o;
                if (i.getName().equalsIgnoreCase(name)) {
                    i.setDescription(description);
                    i.setName(name);
                    i.setImageURL(imageURL);
                    em.flush();
                    System.out.println("\nServer updated item:\n" + name);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to update item:\n" + ex);
            return false;
        }
    }
    
    public boolean removeItem(String itemName) {
        System.out.println("removeItem() called with itemName:" + itemName);
        try {
            Query q = em.createQuery("SELECT t FROM ItemEntity t");

            for (Object o : q.getResultList()) {
                ItemEntity i = (ItemEntity) o;
                if (i.getName().equalsIgnoreCase(itemName)) {
                    em.remove(i);
                    em.flush();
                    System.out.println("\nServer removed item:\n" + itemName);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove item:\n" + ex);
            return false;
        }
    }
    
    public ItemEntity viewItem(String itemName) {
        System.out.println("viewItem() called with itemName:" + itemName);
        try {
            Query q = em.createQuery("SELECT t FROM ItemEntity t");

            for (Object o : q.getResultList()) {
                ItemEntity i = (ItemEntity) o;
                if (i.getName().equalsIgnoreCase(itemName)) {
                    System.out.println("\nServer returns item:\n" + itemName);
                    return i;
                }
            }
            return null; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove item:\n" + ex);
            return null;
        }
    }
    */
    public boolean createBillOfMaterial(String name) {
        System.out.println("createBillOfMaterial() called with name:" + name);

        Long id;
        try {
            BillOfMaterialEntity billOfMaterialEntity = new BillOfMaterialEntity();
            billOfMaterialEntity.create(name);
            em.persist(billOfMaterialEntity);
            id = billOfMaterialEntity.getId();
            System.out.println("Bill Of Material Name \"" + name + "\" registered successfully as id:" + id);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to register bill of material:\n" + ex);
            return false;
        }
    }
    
    public boolean editBillOfMaterial(String name) {
        System.out.println("editBillOfMaterial() called with bill of material name:" + name);

        Long id;
        try {
            Query q = em.createQuery("SELECT t FROM BillOfMaterialEntity t");

            for (Object o : q.getResultList()) {
                BillOfMaterialEntity i = (BillOfMaterialEntity) o;
                if (i.getName().equalsIgnoreCase(name)) {
                    i.setName(name);
                    em.flush();
                    System.out.println("\nServer updated bill of material:\n" + name);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to update bill of material:\n" + ex);
            return false;
        }
    }
    
    public boolean deleteBillOfMaterial(String bomName) {
        System.out.println("deleteBillOfMaterial() called with bomName:" + bomName);
        try {
            Query q = em.createQuery("SELECT t FROM BillOfMaterialEntity t");

            for (Object o : q.getResultList()) {
                BillOfMaterialEntity i = (BillOfMaterialEntity) o;
                if (i.getName().equalsIgnoreCase(bomName)) {
                    em.remove(i);
                    em.flush();
                    System.out.println("\nServer removed bill of material:\n" + bomName);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove bill of material:\n" + ex);
            return false;
        }
    }
    
    public BillOfMaterialEntity viewBillOfMaterial(String name) {
        System.out.println("viewBillOfMaterial() called with name:" + name);
        try {
            Query q = em.createQuery("SELECT t FROM BillOfMaterialEntity t");

            for (Object o : q.getResultList()) {
                BillOfMaterialEntity i = (BillOfMaterialEntity) o;
                if (i.getName().equalsIgnoreCase(name)) {
                    System.out.println("\nServer returns bill of material:\n" + name);
                    return i;
                }
            }
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
            ProductionGroupEntity productionGroupEntity = new ProductionGroupEntity();
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
                ProductionGroupEntity i = (ProductionGroupEntity) o;
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
                ProductionGroupEntity i = (ProductionGroupEntity) o;
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
    
    public ProductionGroupEntity viewProductionGroup(String name) {
        System.out.println("viewProductionGroup() called with name:" + name);
        try {
            Query q = em.createQuery("SELECT t FROM ProductionGroupEntity t");

            for (Object o : q.getResultList()) {
                ProductionGroupEntity i = (ProductionGroupEntity) o;
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
}
