package StoreTransaction.RetailInventory;

import EntityManager.CountryEntity;
import EntityManager.ItemEntity;
import EntityManager.Item_CountryEntity;
import EntityManager.StoreEntity;
import HelperClasses.ItemHelper;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "RetailInventoryWebService")
@Stateless
public class RetailInventoryWebService {

    @EJB
    RetailInventoryBeanLocal rib;
  
    @WebMethod
    public List<String> getStoreAddressByID(@WebParam(name = "storeID") Long storeID) {
        try {
            StoreEntity storeEntity = rib.getStoreByID(storeID);
            List<String> result = new ArrayList<>();
            result.add(storeEntity.getAddress());
            result.add(storeEntity.getPostalCode());
            result.add(storeEntity.getCountry().getName());
            return result;
        } catch (Exception ex) {
            return null;
        }
    }

    @WebMethod
    public String getCountryCode(@WebParam(name = "storeID") Long storeID) {
        try {
            StoreEntity storeEntity = rib.getStoreByID(storeID);
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
        ItemEntity itemEntity = rib.getItemBySKU(SKU);
        ItemHelper ih = new ItemHelper(itemEntity.getId(), itemEntity.getSKU(), itemEntity.getName());
        return ih;
    }

    @WebMethod
    public Double getItemCountryPriceBySKU(@WebParam(name = "SKU") String SKU, @WebParam(name = "storeID") Long storeID) {
        System.out.println("getItemCountryPriceBySKU() called.");
        try {
            ItemEntity itemEntity = rib.getItemBySKU(SKU);
            if (itemEntity == null) {
                return null;
            }
            // Check the store in which country
            StoreEntity storeEntity = rib.getStoreByID(storeID);
            if (storeEntity == null) {
                return null;
            }
            CountryEntity countryEntity = storeEntity.getCountry();

            // Retrieve the item_CountryEntity for that country
            Item_CountryEntity item_CountryEntity = new Item_CountryEntity();
            item_CountryEntity = rib.getItemPricing(countryEntity.getId(), SKU);
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
    public Boolean alertSupervisor(@WebParam(name = "posName") String posName, @WebParam(name = "supervisorTel") String telNo) {
        //TODO not connecting to the URL dunno why
        try {
            String smsMessage = "[Island Furniture] POS:\"" + posName + "\" requires assistance.";
            System.out.println("Sending SMS: " + telNo + ": " +  URLEncoder.encode(smsMessage));

            String requestURL = "http://smsc.vianett.no/v3/send.ashx?";
            requestURL += ("username=" + "lee_yuan_guang@hotmail.com");
            requestURL += ("&SenderAddress="+"IF");//11char max
            requestURL += ("&SenderAddressType="+"5");
            requestURL += ("&password=" + "r0b16");
            requestURL += ("&tel=" + telNo);
            requestURL += ("&msg=" + URLEncoder.encode(smsMessage));

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
    
    @WebMethod
    public Boolean checkIfCustomerNeedToWaitForPicker(@WebParam(name = "receiptNo") String receiptNo) {
        return rib.checkIfCustomerNeedToWaitForPicker(receiptNo);
    }
}
