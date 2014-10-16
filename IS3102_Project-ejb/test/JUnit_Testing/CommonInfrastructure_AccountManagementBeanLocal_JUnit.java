package JUnit_Testing;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import EntityManager.AccessRightEntity;
import EntityManager.CountryEntity;
import EntityManager.MemberEntity;
import EntityManager.RoleEntity;
import EntityManager.StaffEntity;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CommonInfrastructure_AccountManagementBeanLocal_JUnit {
    AccountManagementBeanLocal accountManagementBean = lookupAccountManagementBeanLocal();
    

    public CommonInfrastructure_AccountManagementBeanLocal_JUnit() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
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
    public void loginStaff() {
        System.out.println("loginStaff");        
        StaffEntity result = accountManagementBean.loginStaff("admin@if.com", "admin");
        System.out.println(result.getId());
        assertFalse(result.equals(null));
        assertEquals(new Long(12L), result.getId());
    }

    @Test
    public void listAllStaff() {
        System.out.println("listAllStaff");        
        List result = accountManagementBean.listAllStaff();
        assertFalse(result.isEmpty());
        assertEquals(result.size(), result.size());
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
        System.out.println("checkIfRoleExists");
        Boolean result = accountManagementBean.checkIfRoleExists("Administrator");
        assertFalse(result == false);
        assertTrue(result);
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

    private AccountManagementBeanLocal lookupAccountManagementBeanLocal() {
        try {
            Context c = new InitialContext();
            return (AccountManagementBeanLocal) c.lookup("java:global/IS3102_Project/IS3102_Project-ejb/AccountManagementBean!CommonInfrastructure.AccountManagement.AccountManagementBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }


}
