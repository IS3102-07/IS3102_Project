package OperationalCRM.CustomerInformationManagement;

import javax.ejb.Stateless;
import EntityManager.ItemEntity;
import EntityManager.MemberEntity;
import EntityManager.ShoppingListEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import EntityManager.SubscriptionEntity;
import javax.persistence.Query;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.NoResultException;

@Stateless
public class CustomerInformationManagementBean implements CustomerInformationManagementBeanLocal {

    @PersistenceContext
    private EntityManager em;

    public Boolean addFurnitureToList(String sku, Long memberId) {
        System.out.println("addFurnitureToList() sku is " + sku + " memberId is " + memberId);

        MemberEntity member = em.find(MemberEntity.class, memberId);

        Query q = em.createQuery("SELECT t FROM ItemEntity t where t.SKU=:sku");
        q.setParameter("sku", sku);
        ItemEntity item = (ItemEntity) q.getSingleResult();

        System.out.println("addFurnitureToList(): item found SKU is " + item.getSKU());
        ShoppingListEntity shoppingList = member.getShoppingList();

        Boolean itemExistInShoppingList = false;
        for (int i = 0; i < shoppingList.getItems().size(); i++) {
            if (shoppingList.getItems().get(i) == item) {
                itemExistInShoppingList = true;
            }
        }

        if (itemExistInShoppingList == true) {
            return false;
        } else {
            shoppingList.addItems(item);
            em.merge(member);
            return true;
        }
    }

    public Boolean removeFurnitureToList(String sku, Long memberId) {
        System.out.println("removeFurnitureToList() sku is " + sku + " memberId is " + memberId);

        MemberEntity member = em.find(MemberEntity.class, memberId);

        Query q = em.createQuery("SELECT t FROM ItemEntity t where t.SKU=:sku");
        q.setParameter("sku", sku);
        ItemEntity item = (ItemEntity) q.getSingleResult();

        System.out.println("addFurnitureToList(): item found SKU is " + item.getSKU());
        ShoppingListEntity shoppingList = member.getShoppingList();

        for (int i = 0; i < shoppingList.getItems().size(); i++) {
            if (shoppingList.getItems().get(i) == item) {
                shoppingList.getItems().remove(i);
            }
        }
        em.merge(member);
        return true;
    }

    @Override
    public Boolean addEmailToSubscription(String email) {
        System.out.println("addEmailToSubscription()");
        SubscriptionEntity subscription = new SubscriptionEntity();
        subscription.addToList(email);
        em.persist(subscription);
        return true;
    }

    public ShoppingListEntity shoppingList(String email) {
        System.out.println("shoppingList() called.");
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t where t.email=:email");
            q.setParameter("email", email);
            MemberEntity member = (MemberEntity) q.getSingleResult();

            return member.getShoppingList();
        } catch (Exception ex) {
            System.out.println("\nServer failed to list shopping list:\n" + ex);
            return null;
        }
    }
}
