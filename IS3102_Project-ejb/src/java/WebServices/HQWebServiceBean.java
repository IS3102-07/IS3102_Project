package WebServices;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import CorporateManagement.FacilityManagement.FacilityManagementBeanLocal;
import EntityManager.LineItemEntity;
import EntityManager.MemberEntity;
import EntityManager.RoleEntity;
import EntityManager.StaffEntity;
import EntityManager.StoreEntity;
import HelperClasses.ReturnHelper;
import OperationalCRM.LoyaltyAndRewards.LoyaltyAndRewardsBeanLocal;
import OperationalCRM.SalesRecording.SalesRecordingBeanLocal;
import StoreTransaction.RetailInventoryControl.RetailInventoryControlBeanLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "HQWebServiceBean")
@Stateless
public class HQWebServiceBean {

    @EJB
    AccountManagementBeanLocal AccountManagementBeanLocal;

    @EJB
    FacilityManagementBeanLocal FacilityManagementBeanLocal;

    @EJB
    RetailInventoryControlBeanLocal RetailInventoryControlLocal;

    @EJB
    SalesRecordingBeanLocal SalesRecordingBeanLocal;

    @EJB
    LoyaltyAndRewardsBeanLocal LoyaltyAndRewardsBeanLocal;

    @WebMethod
    public Long posLoginStaff(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        StaffEntity staffEntity = AccountManagementBeanLocal.loginStaff(email, password);
        if (staffEntity == null) {
            return null;
        }
        // Check roles, only admin, cashier or store manager can login into POS
        List<RoleEntity> roles = staffEntity.getRoles();
        for (RoleEntity role : roles) {
            if (role.getId().equals(1L) || role.getId().equals(4L) || role.getId().equals(9L)) {
                Long staffID = staffEntity.getId();
                return staffID;
            }
        }
        return null;
    }

    @WebMethod
    public StoreEntity getStoreByID(@WebParam(name = "storeID") Long storeID) {
        StoreEntity storeEntity = FacilityManagementBeanLocal.getStoreByID(storeID);
        return storeEntity;
    }

    @WebMethod
    public Integer getMemberLoyaltyPoints(@WebParam(name = "memberEmail") String memberEmail) {
        return LoyaltyAndRewardsBeanLocal.getMemberLoyaltyPointsAmount(memberEmail);
    }
    
    @WebMethod
    public MemberEntity getMember(@WebParam(name = "memberEmail") String memberEmail) {
        return LoyaltyAndRewardsBeanLocal.getMember(memberEmail);
    }

    @WebMethod
    public ReturnHelper createSalesRecord(@WebParam(name = "staffEmail") String staffEmail, @WebParam(name = "password") String staffPasword, @WebParam(name = "storeID") Long storeID, @WebParam(name = "posName") String posName, @WebParam(name = "itemsPurchased") List<LineItemEntity> itemsPurchased, @WebParam(name = "amountDue") Double amountDue, @WebParam(name = "amountPaid") Double amountPaid, @WebParam(name = "amountPaidUsingPoints") Double amountPaidUsingPoints, @WebParam(name = "loyaltyPointsDeducted") Integer loyaltyPointsDeducted, @WebParam(name = "memberEmail") String memberEmail) {
        return SalesRecordingBeanLocal.createSalesRecord(staffEmail, staffPasword, storeID, posName, itemsPurchased, amountDue, amountPaid, amountPaidUsingPoints, loyaltyPointsDeducted, memberEmail);
    }
}
