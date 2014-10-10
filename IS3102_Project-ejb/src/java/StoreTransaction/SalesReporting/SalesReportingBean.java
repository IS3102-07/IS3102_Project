package StoreTransaction.SalesReporting;

import EntityManager.LineItemEntity;
import EntityManager.SalesRecordEntity;
import HelperClasses.ReturnHelper;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SalesReportingBean implements SalesReportingBeanLocal {

    @PersistenceContext
    private EntityManager em;

    public ReturnHelper submitSalesRecord(String email, String password, Long storeID, String posName, List<LineItemEntity> itemsPurchased) {
        //MemberEntity memberEntity = em.createQuery(email);
        //SalesRecordEntity salesRecordEntity = new SalesRecordEntity(member, paymentAmount, currency, posName, itemsPurchased);
        
        return new ReturnHelper(true, "Sales record created successfully.");
    }
}
