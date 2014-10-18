package JUnit_Testing;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import EntityManager.RoleEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.InitialContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.naming.Context;
import javax.naming.NamingException;

public class CommonInfrastructure_AccountManagementBeanLocal_JUnit {

    private static EJBContainer ejbContainer;
    private static Context ctx;
//    @EJB
//    private AccountManagementBeanLocal accountManagementBean;
//    AccountManagementBeanLocal accountManagementBean = lookupAccountManagementBeanLocal();

    public CommonInfrastructure_AccountManagementBeanLocal_JUnit() {
    }

    @BeforeClass
    public static void setUpClass() {
        ejbContainer = EJBContainer.createEJBContainer();
        System.out.println("Starting the container");
        ctx = ejbContainer.getContext();
    }

    @AfterClass
    public static void tearDownClass() {
        ejbContainer.close();
         System.out.println("Closing the container");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void checkMemberEmailExists() {
    }

    @Test
    public void registerMember() {
    }

    @Test
    public void getMemberByEmail() {
    }

    @Test
    public void editMember() {
    }

    @Test
    public void loginMember() {
    }

    @Test
    public void checkStaffEmailExists() {
    }

    @Test
    public void registerStaff() {
    }

    @Test
    public void getStaffByEmail() {
    }

    @Test
    public void getStaffById() {
    }

    @Test
    public void getRoleById() {
    }

    @Test
    public void editStaff() {
    }

    @Test
    public void resetStaffPassword() {
    }

    @Test
    public void resetMemberPassword() {
    }

    @Test
    public void removeStaff() {
    }

    @Test
    public void removeMember() {
    }

    @Test
    public void loginStaff() throws NamingException{
        AccountManagementBeanLocal  accountManagementBean = (AccountManagementBeanLocal)ctx.lookup("java:global/IS3102_Project/IS3102_Project-ejb/AccountManagementBean");
        
        System.out.println("loginStaff");
        RoleEntity result = accountManagementBean.searchRole("");
        System.out.println(result.getId());
//        assertFalse(result.equals(null));
//        assertEquals(new Long(12L), result.getId());
    }

    @Test
    public void listAllStaff() {
//        System.out.println("listAllStaff");        
//        List result = accountManagementBean.listAllStaff();
//        assertFalse(result.isEmpty());
//        assertEquals(result.size(), result.size());
    }

    @Test
    public void createRole() {
    }

    @Test
    public void updateRole() {
    }

    @Test
    public void deleteRole() {
    }

    @Test
    public void checkIfRoleExists() {
//        System.out.println("checkIfRoleExists");
//        Boolean result = accountManagementBean.checkIfRoleExists("Administrator");
//        assertFalse(result == false);
//        assertTrue(result);
    }

    @Test
    public void roleHasMembersAssigned() {
    }

    @Test
    public void listAllRoles() {
    }

    @Test
    public void listRolesHeldByStaff() {
    }

    @Test
    public void searchRole() {
    }

    @Test
    public void checkIfStaffHasRole() {
    }

    @Test
    public void addStaffRole() {
    }

    @Test
    public void removeStaffRole() {
    }

    @Test
    public void editStaffRole() {
    }

    @Test
    public void getCountry() {
    }

    @Test
    public void generatePasswordSalt() {
    }

    @Test
    public void generatePasswordHash() {
    }

    @Test
    public void checkStaffInvalidLoginAttempts() {
    }

    @Test
    public void createAccessRight() {
    }

    @Test
    public void isAccessRightExist() {
    }

    @Test
    public void canStaffAccessToTheRegionalOffice() {
    }

    @Test
    public void canStaffAccessToTheStore() {
    }

    @Test
    public void canStaffAccessToTheManufacturingFacility() {
    }

    @Test
    public void canStaffAccessToTheWarehouse() {
    }

//    private AccountManagementBeanLocal lookupAccountManagementBeanLocal() {
//        try {
//            Context c = new InitialContext();
//            return (AccountManagementBeanLocal) c.lookup("java:global/IS3102_Project/IS3102_Project-ejb/AccountManagementBean-92639301113872384");
//        } catch (NamingException ne) {
//            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
//            throw new RuntimeException(ne);
//        }
//    }

}
