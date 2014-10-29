package OperationalCRM.CustomerService;

import EntityManager.FeedbackEntity;
import EntityManager.SalesRecordEntity;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CustomerServiceBeanLocal {

    public List<SalesRecordEntity> viewSalesRecord(Long storeId);

    public List<FeedbackEntity> viewFeedback();

}
