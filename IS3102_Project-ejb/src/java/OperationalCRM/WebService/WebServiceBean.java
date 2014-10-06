package OperationalCRM.WebService;

import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import EntityManager.ItemEntity;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@Stateless
@WebService(serviceName = "WebServiceBean")

public class WebServiceBean{

    @EJB
    ItemManagementBeanLocal ItemManagementBeanLocal;

    @WebMethod(operationName = "asdasd")
    public ItemEntity asdasd(@WebParam(name = "SKU") String SKU) {
        ItemEntity itemEntity = ItemManagementBeanLocal.getItemBySKU(SKU);
        return itemEntity;
    }

    @WebMethod(operationName = "testMethod")
    public String testMethod(String testInput) {
        return null;
    }

}
