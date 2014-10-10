package OperationalCRM.SalesRecording;

import EntityManager.LineItemEntity;
import HelperClasses.ReturnHelper;
import java.util.List;
import javax.ejb.Local;

@Local
public interface SalesRecordingBeanLocal {
    public ReturnHelper createSalesRecord(String staffEmail, String staffPassword, Long storeID, String posName, List<LineItemEntity> itemsPurchased, Double paymentAmount, String memberEmail);
}
