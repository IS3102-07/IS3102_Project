package OperationalCRM.CustomerService;

import EntityManager.SalesRecordEntity;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CustomerServiceBeanLocal {
       public List<SalesRecordEntity> viewSalesRecord(Long storeId);

}
