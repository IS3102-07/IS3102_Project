package WebServices;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import EntityManager.StaffEntity;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "WebServiceBean")
@Stateless
public class WebServiceBean {

    @EJB
    AccountManagementBeanLocal AccountManagementBeanLocal;

    @WebMethod
    public Long loginStaff(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        StaffEntity staffEntity = AccountManagementBeanLocal.loginStaff(email, password);
        Long staffID = staffEntity.getId();
        return staffID;
    }

}
