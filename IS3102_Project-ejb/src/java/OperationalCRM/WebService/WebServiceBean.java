package OperationalCRM.WebService;

import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import EntityManager.MessageInboxEntity;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@Stateless
@WebService

public class WebServiceBean implements WebServiceBeanLocal {

    @EJB
    private ItemManagementBeanLocal ItemManagementBean;

    @WebMethod(operationName = "getItemBySKU")
    public MessageInboxEntity getItemBySKU(@WebParam(name = "SKU") String SKU) {
        return new MessageInboxEntity();
    }

    @WebMethod(operationName = "testMethod")
    public String testMethod(String testInput) {
        return null;
    }

}
