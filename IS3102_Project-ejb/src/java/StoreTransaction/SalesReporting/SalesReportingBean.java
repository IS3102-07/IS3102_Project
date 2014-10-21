package StoreTransaction.SalesReporting;

import StoreTransaction.RetailInventory.RetailInventoryBeanLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.WebServiceRef;

@Stateless
public class SalesReportingBean implements SalesReportingBeanLocal {

    @EJB
    RetailInventoryBeanLocal inventoryBean;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Boolean submitSalesRecord(String staffEmail, String staffPassword, Long storeID, String posName, List<String> itemsPurchasedSKU, List<Integer> itemsPurchasedQty, Double amountDue, Double amountPaid, Double amountPaidUsingPoints, Integer loyaltyPointsDeducted, String memberEmail, String receiptNo) {
        System.out.println("submitSalesRecord() called;");
        // Caching/threading method should go here (if got time to do)
        // Rough check for any missing info before submitting it to the HQ
        if (staffEmail == null || staffPassword == null || storeID == null || posName == null) {
            return false;
        }
        //return createSalesRecord(staffEmail, staffPassword, storeID, posName, itemsPurchasedSKU, itemsPurchasedQty, amountDue, amountPaid, amountPaidUsingPoints, loyaltyPointsDeducted, memberEmail,receiptNo);
        return true;
    }
    
    //consume hq web service

}
