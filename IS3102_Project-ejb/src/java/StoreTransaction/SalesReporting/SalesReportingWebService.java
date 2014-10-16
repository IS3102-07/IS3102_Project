package StoreTransaction.SalesReporting;

import EntityManager.ItemEntity;
import EntityManager.LineItemEntity;
import HelperClasses.ReturnHelper;
import StoreTransaction.RetailInventory.RetailInventoryBeanLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "SalesReportingWebService")
@Stateless
public class SalesReportingWebService {
    
    @EJB
    RetailInventoryBeanLocal inventoryBean;

    @WebMethod
    public ReturnHelper submitSalesRecord(@WebParam(name = "staffEmail") String staffEmail, @WebParam(name = "password") char[] staffPasword, @WebParam(name = "storeID") Long storeID, @WebParam(name = "posName") String posName, @WebParam(name = "itemsPurchasedSKU") List<String> itemsPurchasedSKU, @WebParam(name = "itemsPurchasedQty") List<Integer> itemsPurchasedQty, @WebParam(name = "amountPaid") Double paymentAmount, @WebParam(name = "memberEmail") String memberEmail) {
        //TODO
        //Convert into itementiy andd then into line item entity
        List<LineItemEntity> itemsPurchased;
        for(String SKU:itemsPurchasedSKU){
            ItemEntity itemEntity = inventoryBean.getItemBySKU(SKU);
        }
        String staffPasswordString = new String(staffPasword);
        return new ReturnHelper(true, "Checkout successful.");
    }
    
}
