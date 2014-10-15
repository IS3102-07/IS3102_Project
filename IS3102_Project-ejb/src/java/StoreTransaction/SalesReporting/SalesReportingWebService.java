package StoreTransaction.SalesReporting;

import HelperClasses.ReturnHelper;
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
        //Convert into itementiy andd then into line item entity
        String staffPasswordString = new String(staffPasword);
        return new ReturnHelper(true, "Checkout successful.");
    }
    
}
