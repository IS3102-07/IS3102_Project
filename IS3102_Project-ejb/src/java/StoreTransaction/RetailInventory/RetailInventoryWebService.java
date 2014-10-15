package StoreTransaction.RetailInventory;

import EntityManager.CountryEntity;
import EntityManager.ItemEntity;
import EntityManager.Item_CountryEntity;
import EntityManager.StoreEntity;
import HelperClasses.ItemHelper;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "RetailInventoryWebService")
@Stateless
public class RetailInventoryWebService {

    @EJB
    RetailInventoryBeanLocal RetailInventoryControlLocal;

    @WebMethod
    public String getCountryCode(@WebParam(name = "storeID") Long storeID) {
        try {
            StoreEntity storeEntity = RetailInventoryControlLocal.getStoreByID(storeID);
            if (storeEntity == null) {
                return null;
            }
            return "00" + storeEntity.getCountry().getCountryCode();
        } catch (Exception ex) {
            return null;
        }
    }

    @WebMethod
    public ItemHelper getItemBySKU(@WebParam(name = "SKU") String SKU) {
        ItemEntity itemEntity = RetailInventoryControlLocal.getItemBySKU(SKU);
        ItemHelper ih = new ItemHelper(itemEntity.getId(), itemEntity.getSKU(), itemEntity.getName());
        return ih;
    }

    @WebMethod
    public Double getItemCountryPriceBySKU(@WebParam(name = "SKU") String SKU, @WebParam(name = "storeID") Long storeID) {
        System.out.println("getItemCountryPriceBySKU() called.");
        try {
            ItemEntity itemEntity = RetailInventoryControlLocal.getItemBySKU(SKU);
            if (itemEntity == null) {
                return null;
            }
            // Check the store in which country
            StoreEntity storeEntity = RetailInventoryControlLocal.getStoreByID(storeID);
            if (storeEntity == null) {
                return null;
            }
            CountryEntity countryEntity = storeEntity.getCountry();

            // Retrieve the item_CountryEntity for that country
            Item_CountryEntity item_CountryEntity = new Item_CountryEntity();
            item_CountryEntity = RetailInventoryControlLocal.getItemPricing(countryEntity.getId(), SKU);
            return item_CountryEntity.getRetailPrice();
        } catch (NullPointerException ex) {
            System.out.println("getItemCountryPriceBySKU(): Pricing for this item is not available.");
            return null;
        } catch (Exception ex) {
            System.out.println("getItemCountryPriceBySKU(): Failed");
            ex.printStackTrace();
            return null;
        }

    }
}
