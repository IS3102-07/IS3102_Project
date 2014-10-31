package OperationalCRM.CustomerService;

import EntityManager.FeedbackEntity;
import EntityManager.PickRequestEntity;
import EntityManager.PickerEntity;
import EntityManager.SalesRecordEntity;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CustomerServiceBeanLocal {

    public List<SalesRecordEntity> viewSalesRecord(Long storeId);

    public List<FeedbackEntity> viewFeedback();
    
    // Check if staff is correct role, add him to the picker list, returns picker, null if invalid
    public PickerEntity pickerLoginStaff(String email, String password);
    
    public List<PickRequestEntity> getPickRequests(Long pickerID);
    
    public Boolean acceptPickRequest(Long pickerID, Long pickRequestID);
    
    public Boolean completePickRequest(Long pickRequestID);
    
    public Boolean pickerLogoff(Long pickerID);
    
    //Following methods are called by checkout process
    public Boolean addPickRequest(Long salesRecordID);
    
    //Following methods are for the main console showing status
    public List<PickRequestEntity> getAllPickRequestInStore(Long storeID);
}
