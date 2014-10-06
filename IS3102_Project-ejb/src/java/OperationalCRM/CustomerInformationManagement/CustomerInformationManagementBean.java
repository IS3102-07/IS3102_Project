package OperationalCRM.CustomerInformationManagement;

import javax.ejb.Stateless;
import EntityManager.ShoppingListEntity;
import EntityManager.FurnitureEntity;
import EntityManager.MemberEntity;
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
        member.addToShoppingList(sku);

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
}
