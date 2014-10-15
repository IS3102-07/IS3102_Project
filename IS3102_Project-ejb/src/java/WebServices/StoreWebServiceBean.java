package WebServices;

import EntityManager.CountryEntity;
import EntityManager.ItemEntity;
import EntityManager.Item_CountryEntity;
import EntityManager.LineItemEntity;
import EntityManager.StoreEntity;
import HelperClasses.ItemHelper;
import HelperClasses.ReturnHelper;
import StoreTransaction.RetailInventory.RetailInventoryBeanLocal;
import java.net.HttpURLConnection;
import java.net.URL;
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

    @WebMethod
    public ReturnHelper submitSalesRecord(@WebParam(name = "staffEmail") String staffEmail, @WebParam(name = "password") String staffPasword, @WebParam(name = "storeID") Long storeID, @WebParam(name = "posName") String posName, @WebParam(name = "itemsPurchased") List<LineItemEntity> itemsPurchased, @WebParam(name = "amountPaid") Double paymentAmount, @WebParam(name = "memberEmail") String memberEmail) {
        //TODO
        return new ReturnHelper(true, "Checkout successful.");
    }

    @WebMethod
    public Boolean alertSupervisor(@WebParam(name = "posName") String posName, @WebParam(name = "supervisorTel") String telNo) {
        try {
            String smsMessage = "[Island Furniture] POS:\"" + posName + "\" requires assistance.";
            System.out.println("Sending SMS: " + telNo + ": " + smsMessage);

            String requestURL = "http://smsc.vianett.no/v3/send.ashx?";
            requestURL += ("username=" + "lee_yuan_guang@hotmail.com");
            requestURL += ("&password=" + "r0b16");
            requestURL += ("&tel=" + telNo);
            requestURL += ("&msg=" + smsMessage);

            URL url = new URL(requestURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "text/plain");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            connection.getInputStream();
            connection.disconnect();
            System.out.println("?????????");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
