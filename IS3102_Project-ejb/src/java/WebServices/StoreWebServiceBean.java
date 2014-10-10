package WebServices;

import CorporateManagement.FacilityManagement.FacilityManagementBeanLocal;
import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import EntityManager.CountryEntity;
import EntityManager.ItemEntity;
import EntityManager.Item_CountryEntity;
import EntityManager.LineItemEntity;
import EntityManager.StoreEntity;
import HelperClasses.ReturnHelper;
import OperationalCRM.LoyaltyAndRewards.LoyaltyAndRewardsBeanLocal;
import StoreTransaction.RetailInventoryControl.RetailInventoryControlBeanLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "StoreWebServiceBean")
@Stateless
public class StoreWebServiceBean {

    @EJB
    ItemManagementBeanLocal ItemManagementBeanLocal;
    @EJB
    RetailInventoryControlBeanLocal RetailInventoryControlLocal;
    @EJB
    LoyaltyAndRewardsBeanLocal LoyaltyAndRewardsBeanLocal;
    @EJB
    FacilityManagementBeanLocal FacilityManagementBeanLocal;

    @WebMethod(operationName = "getItemBySKU")
    public ItemEntity getItemBySKU(@WebParam(name = "SKU") String SKU) {
        ItemEntity itemEntity = ItemManagementBeanLocal.getItemBySKU(SKU);
        return itemEntity;
    }

    @WebMethod
    public Item_CountryEntity getItemCountryBySKU(@WebParam(name = "SKU") String SKU, @WebParam(name = "storeID") Long storeID) {
        System.out.println("getItemCountryBySKU() called.");
        try {
            ItemEntity itemEntity = RetailInventoryControlLocal.getItemBySKU(SKU);
            if (itemEntity == null) {
                return null;
            }
            // Check the store in which country
            StoreEntity storeEntity = FacilityManagementBeanLocal.getStoreByID(storeID);
            if (storeEntity == null) {
                return null;
            }
            CountryEntity countryEntity = storeEntity.getCountry();

            // Retrieve the item_CountryEntity for that country
            Item_CountryEntity item_CountryEntity = new Item_CountryEntity();
            item_CountryEntity = ItemManagementBeanLocal.getItemPricing(countryEntity.getId(), SKU);
            return item_CountryEntity;
        } catch (Exception ex) {
            System.out.println("getItemCountryBySKU(): Failed");
            ex.printStackTrace();
            return null;
        }

    }

    @WebMethod
    public Integer getMemberLoyaltyPointsAmount(@WebParam(name = "email") String email) {
        Integer loyaltyPointsAmt = LoyaltyAndRewardsBeanLocal.getMemberLoyaltyPointsAmount(email);
        return loyaltyPointsAmt;
    }

    @WebMethod
    public ReturnHelper submitSalesRecord(@WebParam(name = "staffEmail") String staffEmail, @WebParam(name = "password") String staffPasword, @WebParam(name = "storeID") Long storeID, @WebParam(name = "posName") String posName, @WebParam(name = "itemsPurchased") List<LineItemEntity> itemsPurchased, @WebParam(name="amountPaid") Double paymentAmount, @WebParam(name="memberEmail") String memberEmail) {
        //TODO
        return new ReturnHelper(true, "Checkout successful.");
    }

    @WebMethod
    public Boolean callSupervisor(@WebParam(name = "contactNo") String contactNo) {
        //TODO
        return true;
    }

    @WebMethod
    public Boolean alertSupervisor(@WebParam(name = "posName") String posName, @WebParam(name = "supervisorTel") Integer telNo) {
        try {
            //send SMS code TODO
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
