package StoreTransaction.SalesReporting;

import HelperClasses.ReturnHelper;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.WebServiceRef;
import test.SalesRecordingWebService_Service;

@Stateless
public class SalesReportingBean implements SalesReportingBeanLocal {

    @WebServiceRef(wsdlLocation = "META-INF/wsdl/localhost_8080/SalesRecordingWebService/SalesRecordingWebService.wsdl")
    private SalesRecordingWebService_Service service;
    @PersistenceContext
    private EntityManager em;

    public ReturnHelper submitSalesRecord(String email, String password, Long storeID, String posName, List<String> itemsPurchasedSKU, List<Integer> itemsPurchasedQty, Double paymentAmount, String memberEmail) {
        System.out.println("submitSalesRecord() called;");
        //TODO Consume HQ Web service to recordSales

        return new ReturnHelper(true, "Sales record created successfully.");
    }

    private test.ReturnHelper createSalesRecord(java.lang.String staffEmail, java.lang.String password, java.lang.Long storeID, java.lang.String posName, java.util.List<test.LineItemEntity> itemsPurchased, java.lang.Double amountDue, java.lang.Double amountPaid, java.lang.Double amountPaidUsingPoints, java.lang.Integer loyaltyPointsDeducted, java.lang.String memberEmail) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        test.SalesRecordingWebService port = service.getSalesRecordingWebServicePort();
        return port.createSalesRecord(staffEmail, password, storeID, posName, itemsPurchased, amountDue, amountPaid, amountPaidUsingPoints, loyaltyPointsDeducted, memberEmail);
    }

}
