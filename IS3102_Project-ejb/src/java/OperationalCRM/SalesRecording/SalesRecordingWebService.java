package OperationalCRM.SalesRecording;

import CorporateManagement.FacilityManagement.FacilityManagementBeanLocal;
import EntityManager.LineItemEntity;
import EntityManager.StoreEntity;
import HelperClasses.ReturnHelper;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "SalesRecordingWebService")
@Stateless
public class SalesRecordingWebService {

    @EJB
    FacilityManagementBeanLocal FacilityManagementBeanLocal;

    @EJB
    SalesRecordingBeanLocal SalesRecordingBeanLocal;



    @WebMethod
    public StoreEntity getStoreByID(@WebParam(name = "storeID") Long storeID) {
        StoreEntity storeEntity = FacilityManagementBeanLocal.getStoreByID(storeID);
        return storeEntity;
    }

    @WebMethod
    public ReturnHelper createSalesRecord(@WebParam(name = "staffEmail") String staffEmail, @WebParam(name = "password") String staffPasword, @WebParam(name = "storeID") Long storeID, @WebParam(name = "posName") String posName, @WebParam(name = "itemsPurchased") List<LineItemEntity> itemsPurchased, @WebParam(name = "amountDue") Double amountDue, @WebParam(name = "amountPaid") Double amountPaid, @WebParam(name = "amountPaidUsingPoints") Double amountPaidUsingPoints, @WebParam(name = "loyaltyPointsDeducted") Integer loyaltyPointsDeducted, @WebParam(name = "memberEmail") String memberEmail) {
        return SalesRecordingBeanLocal.createSalesRecord(staffEmail, staffPasword, storeID, posName, itemsPurchased, amountDue, amountPaid, amountPaidUsingPoints, loyaltyPointsDeducted, memberEmail);
    }
}
