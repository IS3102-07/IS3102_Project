/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CorporateManagement.ItemManagement;

import javax.ejb.Stateless;
import EntityManager.ItemEntity;
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

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
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
}
