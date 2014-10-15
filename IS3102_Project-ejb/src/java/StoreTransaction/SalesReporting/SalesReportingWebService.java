package StoreTransaction.SalesReporting;

import HelperClasses.ReturnHelper;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "SalesReportingWebService")
@Stateless
public class SalesReportingWebService {

    @WebMethod
    public ReturnHelper submitSalesRecord(@WebParam(name = "staffEmail") String staffEmail, @WebParam(name = "password") char[] staffPasword, @WebParam(name = "storeID") Long storeID, @WebParam(name = "posName") String posName, @WebParam(name = "itemsPurchasedSKU") List<String> itemsPurchasedSKU, @WebParam(name = "itemsPurchasedQyu") List<Integer> itemsPurchasedQty, @WebParam(name = "amountPaid") Double paymentAmount, @WebParam(name = "memberEmail") String memberEmail) {
        //TODO
        String staffPasswordString = new String(staffPasword);
        return new ReturnHelper(true, "Checkout successful.");
    }
    
    @WebMethod
    public Boolean alertSupervisor(@WebParam(name = "posName") String posName, @WebParam(name = "supervisorTel") String telNo) {
        //TODO not connecting to the URL dunno why
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
