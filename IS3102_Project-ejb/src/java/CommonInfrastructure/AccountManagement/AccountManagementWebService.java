package CommonInfrastructure.AccountManagement;

import CommonInfrastructure.SystemSecurity.SystemSecurityBeanLocal;
import Config.Config;
import EntityManager.CountryEntity;
import EntityManager.ItemEntity;
import EntityManager.LineItemEntity;
import EntityManager.MemberEntity;
import EntityManager.RoleEntity;
import EntityManager.StaffEntity;
import EntityManager.StoreEntity;
import HelperClasses.ItemHelper;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@WebService(serviceName = "AccountManagementWebService")
@Stateless
public class AccountManagementWebService {

    @EJB
    AccountManagementBeanLocal AccountManagementBeanLocal;

    @EJB
    SystemSecurityBeanLocal systemSecurityBean;

    @PersistenceContext
    private EntityManager em;

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
            ex.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public List<ItemHelper> getMemberShoppingList(@WebParam(name = "email") String email) {
        try {
            MemberEntity memberEntity = AccountManagementBeanLocal.getMemberByEmail(email);
            if (memberEntity == null) {
                return null;
            } else {
                List<LineItemEntity> shoppingListEntity = memberEntity.getShoppingList();
                List<ItemHelper> itemHelpers = new ArrayList<>();
                for (LineItemEntity curr : shoppingListEntity) {
                    itemHelpers.add(new ItemHelper(curr.getItem().getId(), curr.getItem().getSKU(), curr.getItem().getName(), curr.getQuantity()));
                }
                return itemHelpers;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
