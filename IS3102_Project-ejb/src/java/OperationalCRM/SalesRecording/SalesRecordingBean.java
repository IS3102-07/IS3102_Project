package OperationalCRM.SalesRecording;

import EntityManager.LineItemEntity;
import EntityManager.MemberEntity;
import EntityManager.SalesRecordEntity;
import EntityManager.StoreEntity;
import HelperClasses.ReturnHelper;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SalesRecordingBean implements SalesRecordingBeanLocal {

    @PersistenceContext
    private EntityManager em;

    public ReturnHelper createSalesRecord(String staffEmail, String staffPassword, Long storeID, String posName, List<LineItemEntity> itemsPurchased, Double paymentAmount, String memberEmail) {
        System.out.println("submitSalesRecord() called;");
        StoreEntity storeEntity = null;
        MemberEntity memberEntity = null;
        String currency = "";
        Double loyaltyPaymentAmount = 0.0;
        //Retrieve member
        if (memberEmail != null && memberEmail.length() >= 0) {
            try {
                Query q = em.createQuery("SELECT t FROM MemberEntity t WHERE t.email=:email");
                q.setParameter("email", memberEmail);
                memberEntity = (MemberEntity) q.getSingleResult();
            } catch (NoResultException ex) {
                System.out.println("submitSalesRecord(): Member does not exist:");
                return new ReturnHelper(false, "Member details could not be retrieved based on the card provided. Contact customer service.");
            } catch (Exception ex) {
                System.out.println("submitSalesRecord(): Failed to retrieve member based on email:");
                ex.printStackTrace();
                return new ReturnHelper(false, "System error in retriving membership information.");
            }
        }
        //Retrieve country for currency & exchange rate
        try {
            storeEntity = em.getReference(StoreEntity.class, storeID);
            currency = storeEntity.getCountry().getCurrency();
            loyaltyPaymentAmount = paymentAmount / storeEntity.getCountry().getExchangeRate();
        } catch (Exception ex) {
            System.out.println("submitSalesRecord(): Error in retriving country");
            return new ReturnHelper(false, "System error in retriving country information.");
        }
        try {
            SalesRecordEntity salesRecordEntity = new SalesRecordEntity(memberEntity, paymentAmount, loyaltyPaymentAmount, currency, posName, itemsPurchased);
            em.persist(salesRecordEntity);
            storeEntity.getSalesRecords().add(salesRecordEntity);
            em.merge(storeEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ReturnHelper(false, "System error in creating sales record.");
        }
        return new ReturnHelper(true, "Sales record created successfully.");
    }
}
