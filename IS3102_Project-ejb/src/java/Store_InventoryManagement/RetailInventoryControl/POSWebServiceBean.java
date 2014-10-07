package Store_InventoryManagement.RetailInventoryControl;

import CorporateManagement.FacilityManagement.FacilityManagementBeanLocal;
import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import EntityManager.CountryEntity;
import EntityManager.ItemEntity;
import EntityManager.Item_CountryEntity;
import EntityManager.StoreEntity;
import OperationalCRM.LoyaltyAndRewards.LoyaltyAndRewardsBeanLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "POSWebServiceBean")
@Stateless
public class POSWebServiceBean {

    @EJB
    ItemManagementBeanLocal ItemManagementBeanLocal;
    @EJB
    RetailInventoryControlLocal RetailInventoryControlLocal;
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
        ItemEntity itemEntity = RetailInventoryControlLocal.getItemBySKU(SKU);
        if (itemEntity == null) {
            return null;
        }
        // Check the store in which country
        StoreEntity storeEntity = FacilityManagementBeanLocal.getStoreByID(storeID);
        if (storeEntity == null) {
            return null;
        }
        //CountryEntity countryEntity = storeEntity.getCountry();
        
        // Retrieve the item_CountryEntity for that country
        Item_CountryEntity item_CountryEntity = new Item_CountryEntity();
        return item_CountryEntity;
    }

    @WebMethod
    public Integer getMemberLoyaltyPointsAmount(@WebParam(name = "email") String email) {
        Integer loyaltyPointsAmt = LoyaltyAndRewardsBeanLocal.getMemberLoyaltyPointsAmount(email);
        return loyaltyPointsAmt;
    }

}
