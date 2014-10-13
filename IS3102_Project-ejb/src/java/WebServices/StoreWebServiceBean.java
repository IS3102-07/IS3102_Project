package WebServices;

import CorporateManagement.FacilityManagement.FacilityManagementBeanLocal;
import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import EntityManager.CountryEntity;
import EntityManager.ItemEntity;
import EntityManager.Item_CountryEntity;
import EntityManager.LineItemEntity;
import EntityManager.StoreEntity;
import HelperClasses.ItemHelper;
import HelperClasses.ReturnHelper;
import OperationalCRM.LoyaltyAndRewards.LoyaltyAndRewardsBeanLocal;
import StoreTransaction.RetailInventoryControl.RetailInventoryControlBeanLocal;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
    public ItemHelper getItemBySKU(@WebParam(name = "SKU") String SKU) {
        ItemEntity itemEntity = ItemManagementBeanLocal.getItemBySKU(SKU);
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
            StoreEntity storeEntity = FacilityManagementBeanLocal.getStoreByID(storeID);
            if (storeEntity == null) {
                return null;
            }
            CountryEntity countryEntity = storeEntity.getCountry();

            // Retrieve the item_CountryEntity for that country
            Item_CountryEntity item_CountryEntity = new Item_CountryEntity();
            item_CountryEntity = ItemManagementBeanLocal.getItemPricing(countryEntity.getId(), SKU);
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
    public Boolean alertSupervisor(@WebParam(name = "posName") String posName, @WebParam(name = "supervisorTel") Integer telNo) {
        try {
            StringBuffer response;
            String smsMessage = "[Island Furniture] POS:\"" + posName + "\" requires assistance.";
            System.out.println("Sending SMS: " + telNo + ": " + smsMessage);

            String RequestURL = "http://www.redoxygen.net/sms.dll?Action=SendSMS";

            String Data = ("AccountId=" + URLEncoder.encode("CI00136959", "UTF-8"));
            Data += ("&Email=" + URLEncoder.encode("lyg@nus.edu.sg", "UTF-8"));
            Data += ("&Password=" + URLEncoder.encode("6nruJnM4", "UTF-8"));
            Data += ("&Recipient=" + URLEncoder.encode(telNo + "", "UTF-8"));
            Data += ("&Message=" + URLEncoder.encode(smsMessage, "UTF-8"));

            int Result = -1;
            URL Address = new URL(RequestURL);

            HttpURLConnection Connection = (HttpURLConnection) Address.openConnection();
            Connection.setRequestMethod("POST");
            Connection.setDoInput(true);
            Connection.setDoOutput(true);

            DataOutputStream Output;
            Output = new DataOutputStream(Connection.getOutputStream());
            Output.writeBytes(Data);
            Output.flush();
            Output.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
