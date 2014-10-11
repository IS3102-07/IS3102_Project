package StoreTransaction.SalesReporting;

import EntityManager.LineItemEntity;
import HelperClasses.ReturnHelper;
import java.util.List;
import javax.ejb.Local;

@Local
public interface SalesReportingBeanLocal {
    public ReturnHelper submitSalesRecord(String email, String password, Long storeID, String posName, List<LineItemEntity> itemsPurchased, Double paymentAmount, String memberEmail);
}
