package OperationalCRM.CustomerService;

import EntityManager.SalesRecordEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CustomerServiceBean implements CustomerServiceBeanLocal {

    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;

    public List<SalesRecordEntity> viewSalesRecord(Long storeId) {
        System.out.println("View sales record is called()" + storeId);
        try{
        Query q = em.createQuery("select sr from SalesRecordEntity sr where s.store.id = ?1").setParameter(1, storeId);
            List<SalesRecordEntity> salesRecords = q.getResultList();
                   return salesRecords;
        }
        catch(Exception ex) {
            System.out.println("\nServer failed to retrieve sales record:\n" + ex);
            return null;
        }
    }
}
