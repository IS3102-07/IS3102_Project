package CommonInfrastructure.AccountManagement;

import EntityManager.RoleEntity;
import EntityManager.StaffEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "AccountManagementWebService")
@Stateless
public class AccountManagementWebService {

    @EJB
    AccountManagementBeanLocal AccountManagementBeanLocal;

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
}
