package StoreTransaction.SalesReporting;

import EntityManager.ItemEntity;
import EntityManager.LineItemEntity;
import HelperClasses.ReturnHelper;
import StoreTransaction.RetailInventory.RetailInventoryBeanLocal;
import StoreTransaction.SalesRecordingWebService_Service;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceRef;

@WebService(serviceName = "SalesReportingWebService")
@Stateless
public class SalesReportingWebService {
    @WebServiceRef(wsdlLocation = "META-INF/wsdl/localhost_8080/SalesRecordingWebService/SalesRecordingWebService.wsdl")
    private SalesRecordingWebService_Service service;
    
    @EJB
    RetailInventoryBeanLocal inventoryBean;

    @WebMethod
    public ReturnHelper submitSalesRecord(@WebParam(name = "staffEmail") String staffEmail, @WebParam(name = "password") String staffPassword, @WebParam(name = "storeID") Long storeID, @WebParam(name = "posName") String posName, @WebParam(name = "itemsPurchasedSKU") List<String> itemsPurchasedSKU, @WebParam(name = "itemsPurchasedQty") List<Integer> itemsPurchasedQty, @WebParam(name = "amountDue") Double amountDue,@WebParam(name = "amountPaid") Double amountPaid,@WebParam(name = "amountPaidUsingPoints") Double amountPaidUsingPoints,@WebParam(name = "loyaltyPointsDeducted") Integer loyaltyPointsDeducted, @WebParam(name = "memberEmail") String memberEmail) {
        //TODO
        //Convert into itementiy andd then into line item entity
        List<LineItemEntity> itemsPurchased = new ArrayList();
        for(int i=0;i<itemsPurchasedSKU.size();i++){
            String SKU = itemsPurchasedSKU.get(i);
            ItemEntity itemEntity = inventoryBean.getItemBySKU(SKU);
            LineItemEntity lineItemEntity = new LineItemEntity(itemEntity, itemsPurchasedQty.get(i), "");
            itemsPurchased.add(lineItemEntity);
        }
        //createSalesRecord(staffEmail,staffPassword,storeID,posName,itemsPurchased,amountDue,)
        return new ReturnHelper(true, "Checkout successful.");
    }

    private StoreTransaction.ReturnHelper createSalesRecord(java.lang.String staffEmail, java.lang.String password, java.lang.Long storeID, java.lang.String posName, java.util.List<StoreTransaction.LineItemEntity> itemsPurchased, java.lang.Double amountDue, java.lang.Double amountPaid, java.lang.Double amountPaidUsingPoints, java.lang.Integer loyaltyPointsDeducted, java.lang.String memberEmail) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        StoreTransaction.SalesRecordingWebService port = service.getSalesRecordingWebServicePort();
        return port.createSalesRecord(staffEmail, password, storeID, posName, itemsPurchased, amountDue, amountPaid, amountPaidUsingPoints, loyaltyPointsDeducted, memberEmail);
    }
    
}
