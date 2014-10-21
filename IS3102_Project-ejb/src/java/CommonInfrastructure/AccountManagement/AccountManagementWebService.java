package CommonInfrastructure.AccountManagement;

import Config.Config;
import EntityManager.RoleEntity;
import EntityManager.StaffEntity;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;
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
    public String posLoginStaff(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        Long staffID = null;
        try {
            StaffEntity staffEntity = AccountManagementBeanLocal.loginStaff(email, password);
            if (staffEntity == null) {
                return null;
            }
            // Check roles, only admin, cashier or store manager can login into POS
            List<RoleEntity> roles = staffEntity.getRoles();
            for (RoleEntity role : roles) {
                if (role.getId().equals(1L) || role.getId().equals(4L) || role.getId().equals(9L)) {
                    staffID = staffEntity.getId();
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(Config.logFilePath, true)));
                    out.println(new Date().toString() + ";" + staffID + ";posLoginStaff();" + staffID + ";");
                    out.close();
                    return staffEntity.getName();
                }
            }
            return null;
        } catch (Exception ex) {
            if (staffID != null) {
                return staffEntity.getName();
            } else {
                return null;
            }
        }
    }
}
