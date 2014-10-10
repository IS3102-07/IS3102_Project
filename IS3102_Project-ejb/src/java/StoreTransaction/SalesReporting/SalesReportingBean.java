package StoreTransaction.SalesReporting;

import EntityManager.LineItemEntity;
import HelperClasses.ReturnHelper;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SalesReportingBean implements SalesReportingBeanLocal {

    @PersistenceContext
    private EntityManager em;

    public ReturnHelper submitSalesRecord(String email, String password, Long storeID, String posName, List<LineItemEntity> itemsPurchased, Double paymentAmount, String memberEmail) {
        System.out.println("submitSalesRecord() called;");
        //Call HQ Web service to recordSales
        return new ReturnHelper(true, "Sales record created successfully.");
    }
}
