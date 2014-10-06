package Store_InventoryManagement.RetailInventoryControl;

import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import EntityManager.ItemEntity;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "POSWebServiceBean")
@Stateless
public class POSWebServiceBean {

    @EJB
    ItemManagementBeanLocal ItemManagementBeanLocal;

    @WebMethod(operationName = "getItemBySKU")
    public ItemEntity getItemBySKU(@WebParam(name = "SKU") String SKU) {
        ItemEntity itemEntity = ItemManagementBeanLocal.getItemBySKU(SKU);
        return itemEntity;
    }

}
