package OperationalCRM.SalesRecording;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import EntityManager.CountryEntity;
import EntityManager.ItemEntity;
import EntityManager.LineItemEntity;
import EntityManager.MemberEntity;
import EntityManager.SalesRecordEntity;
import EntityManager.StaffEntity;
import EntityManager.StoreEntity;
import HelperClasses.ReturnHelper;
import InventoryManagement.StoreAndKitchenInventoryManagement.StoreAndKitchenInventoryManagementBeanLocal;
import MRP.SalesForecast.SalesForecastBeanLocal;
import OperationalCRM.LoyaltyAndRewards.LoyaltyAndRewardsBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SalesRecordingBean implements SalesRecordingBeanLocal {

    @EJB
    private SalesForecastBeanLocal salesForecastBean;

   @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;

    @EJB
    private AccountManagementBeanLocal accountManagementBean;
    @EJB
    private LoyaltyAndRewardsBeanLocal loyaltyAndRewardsBean;
    @EJB
    private StoreAndKitchenInventoryManagementBeanLocal storeInventoryManagementBean;
    @EJB
    private ItemManagementBeanLocal itemManagementBean;

    @Override
    public Boolean createSalesRecord(String staffEmail, String staffPassword, Long storeID, String posName, List<String> itemsPurchasedSKU, List<Integer> itemsPurchasedQty, Double amountDue, Double amountPaid, Double amountPaidUsingPoints, Integer loyaltyPointsDeducted, String memberEmail, String receiptNo) {
        System.out.println("createSalesRecord() called;");
        ReturnHelper rh = new ReturnHelper(false, "System Error");
        StoreEntity storeEntity = null;
        MemberEntity memberEntity = null;
        String currency = "";
        //Retrieve member
        if (memberEmail != null && memberEmail.length() > 0) {
            try {
                Query q = em.createQuery("SELECT t FROM MemberEntity t WHERE t.email=:email");
                q.setParameter("email", memberEmail);
                memberEntity = (MemberEntity) q.getSingleResult();
            } catch (NoResultException ex) {
                System.out.println("createSalesRecord(): Member does not exist:");
                return false;
                //return new ReturnHelper(false, "Member details could not be retrieved based on the card provided. Contact customer service.");
            } catch (Exception ex) {
                System.out.println("createSalesRecord(): Failed to retrieve member based on email:");
                ex.printStackTrace();
                return false;
                //return new ReturnHelper(false, "System error in retriving membership information.");
            }
        }
        //Retrieve country for currency & exchange rate to be stored in sales record
        try {
            storeEntity = em.getReference(StoreEntity.class, storeID);
            CountryEntity countryEntity = storeEntity.getCountry();
            currency = countryEntity.getCurrency();
        } catch (Exception ex) {
            System.out.println("createSalesRecord(): Error in retriving country");
            return false;
            //return new ReturnHelper(false, "System error in retriving country information.");
        }
        //Actual creating of sales record
        try {
            //Convert into itementiy andd then into line item entity
            List<LineItemEntity> itemsPurchased = new ArrayList();
            for (int i = 0; i < itemsPurchasedSKU.size(); i++) {
                String SKU = itemsPurchasedSKU.get(i);
                ItemEntity itemEntity = itemManagementBean.getItemBySKU(SKU);
                LineItemEntity lineItemEntity = new LineItemEntity(itemEntity, itemsPurchasedQty.get(i), "");
                itemsPurchased.add(lineItemEntity);
            }
            StaffEntity staffEntity = accountManagementBean.getStaffByEmail(staffEmail);
            SalesRecordEntity salesRecordEntity = new SalesRecordEntity(memberEntity, amountDue, amountPaid, amountPaidUsingPoints, loyaltyPointsDeducted, currency, posName, staffEntity.getName(), itemsPurchased, receiptNo);
            salesRecordEntity.setStore(storeEntity);//tie the store to record
            em.persist(salesRecordEntity);
            //update sales figures as well
            salesForecastBean.updateSalesFigureBySalesRecord(salesRecordEntity.getId());
            storeEntity.getSalesRecords().add(salesRecordEntity);//tie the record to the store
            em.merge(storeEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
            //return new ReturnHelper(false, "System error in creating sales record.");
        }
        //Update the member loyalty points
        try {
            rh = loyaltyAndRewardsBean.updateMemberLoyaltyPointsAndTier(memberEmail, loyaltyPointsDeducted, amountPaid, storeID);
        } catch (Exception ex) {
            System.out.println("Error in updating loyalty points");
            return false;
            //return new ReturnHelper(false, "System error in updating loyalty points, transaction has been cancelled. Please contact customer service.");
        }
        //Update inventory amount
        try {
            //TODO remove from retail outlet or furniture marketplace
            //storeInventoryManagementBean.removeItemFromInventory(SKU, quantity);
            //if kitchen just no updating of amount
        } catch (Exception ex) {
            ex.printStackTrace();
            //TODO log that inventory cannot be updated, continue to let customer checkout
        }
        return true;
    }
}
