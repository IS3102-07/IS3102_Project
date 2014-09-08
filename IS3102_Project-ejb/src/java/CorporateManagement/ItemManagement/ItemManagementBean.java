/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CorporateManagement.ItemManagement;

import javax.ejb.Stateless;
import EntityManager.ItemEntity;
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
}
