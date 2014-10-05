package OperationalCRM.WebService;

import EntityManager.MessageInboxEntity;
import javax.ejb.Local;

@Local
public interface WebServiceBeanLocal {

    MessageInboxEntity getItemBySKU(String SKU);

    String testMethod(String testInput);

}
