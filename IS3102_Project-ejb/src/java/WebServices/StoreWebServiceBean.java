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
import java.io.OutputStream;
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
        }catch (Exception ex) {
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
    public Boolean callSupervisor(@WebParam(name = "contactNo") String contactNo) {
        //TODO
        return true;
    }

    @WebMethod
    public Boolean alertSupervisor(@WebParam(name = "posName") String posName, @WebParam(name = "supervisorTel") Integer telNo) {
        try {
//            //send SMS code TODO
//            String smsMessage = "[Island Furniture] POS:\"" + posName + "\" requires assistance.";
//            System.out.println("Sending SMS: " + telNo + ": " + smsMessage);
//
//            String initString1 = "AT" + (char) 13;
//            String initString2 = "AT+CMGF=1" + (char) 13;
//            String cmdString1 = "AT+CMGS=" + telNo.toString() + (char) 13;
//            String cmdString2 = smsMessage + (char) 26;
//
//            System.out.println("initString1: " + initString1);
//            System.out.println("initString2: " + initString2);
//            System.out.println("cmdString1: " + cmdString1);
//            System.out.println("cmdString2: " + cmdString2);
//
//            CommPortIdentifier commPortIdentifier = CommPortIdentifier.getPortIdentifier("COM9");
//            SerialPort serialPort = (SerialPort) commPortIdentifier.open("SMS", 2000);
//            serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
//            OutputStream outputStream = serialPort.getOutputStream();
//
//            outputStream.write(initString1.getBytes());
//            outputStream.write(initString2.getBytes());
//            outputStream.write(cmdString1.getBytes());
//            outputStream.write(cmdString2.getBytes());
//
//            Thread.sleep(2000);
//
//            serialPort.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
