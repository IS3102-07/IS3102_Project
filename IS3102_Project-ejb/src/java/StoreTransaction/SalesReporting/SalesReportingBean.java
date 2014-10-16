package StoreTransaction.SalesReporting;

import HelperClasses.ReturnHelper;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.WebServiceRef;

@Stateless
public class SalesReportingBean implements SalesReportingBeanLocal {
    @PersistenceContext
    private EntityManager em;

    public ReturnHelper submitSalesRecord(String email, String password, Long storeID, String posName, List<String> itemsPurchasedSKU, List<Integer> itemsPurchasedQty, Double paymentAmount, String memberEmail) {
        System.out.println("submitSalesRecord() called;");
        //TODO Consume HQ Web service to recordSales
        
        return new ReturnHelper(true, "Sales record created successfully.");
    }
}
