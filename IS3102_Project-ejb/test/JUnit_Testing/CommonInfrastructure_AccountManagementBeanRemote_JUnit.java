package JUnit_Testing;

import CommonInfrastructure.AccountManagement.AccountManagementBeanRemote;
import EntityManager.AccessRightEntity;
import EntityManager.CountryEntity;
import EntityManager.RoleEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import javax.naming.Context;
import javax.naming.NamingException;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommonInfrastructure_AccountManagementBeanRemote_JUnit implements Serializable {

    AccountManagementBeanRemote accountManagementBean = lookupAccountManagementBeanRemote();
    static Long memberId;
    static Long staffId;
    static Long roleId;

    public CommonInfrastructure_AccountManagementBeanRemote_JUnit() {
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
    public void test01RegisterMember() {
        System.out.println("testRegisterMember()");
        String testdata_name = "Paul";
        String testdata_address = "44 Tanglin Hill, #04-12";
        Date testdata_DOB = new Date();
        String testdata_email = "paul@hotmail.com";
        String testdata_phone = "81234123";
        CountryEntity testdata_country = null;
        String testdata_city = "Madham";
        String testdata_zipCode = "123456";
        String testdata_password = "password";
        Boolean result = accountManagementBean.registerMember(testdata_name, testdata_address, testdata_DOB, testdata_email, testdata_phone, testdata_country, testdata_city, testdata_zipCode, testdata_password);
        assertTrue(result);
    }


    @Test
    public void test04CheckMemberEmailExists() {
        System.out.println("testCheckMemberEmailExists()");
        String testdata_email = "superman@hotmail.com";
        Boolean result = accountManagementBean.checkMemberEmailExists(testdata_email);
        assertTrue(result);
    }

    @Test
    public void test06CheckStaffEmailExists() {
        System.out.println("testCheckStaffEmailExists()");
        String testdata_email = "admin@if.com";
        Boolean result = accountManagementBean.checkStaffEmailExists(testdata_email);
        assertTrue(result);
    }


    @Test
    public void test08EditStaff() {
        System.out.println("testEditStaff()");
        String testdata_callerStaffID = "1";
        Long testdata_staffID = staffId;
        System.out.println("test08EditStaff " + staffId);
        String testdata_address = "55 Tanglin Home, #04-12";
        String testdata_phone = "81234333";
        String testdata_password = "";
        Boolean result = accountManagementBean.editStaff(testdata_callerStaffID, testdata_staffID, testdata_phone, testdata_password, testdata_address);
        assertFalse(result);
    }

    @Test
    public void test10ResetStaffPassword() {
        System.out.println("testResetStaffPassword()");
        String testdata_email = "zul@if.com";
        String testdata_password = "12345678";
        Boolean result = accountManagementBean.resetStaffPassword(testdata_email, testdata_password);
        assertFalse(result);
    }

    @Test
    public void test11RemoveStaff() {
        System.out.println("testRemoveStaff()");
        String testdata_callerStaffID = "1";
        Long testdata_staffID = staffId;
        Boolean result = accountManagementBean.removeStaff(testdata_callerStaffID, testdata_staffID);
        assertFalse(result);
    }


    @Test
    public void test13CreateRole() {
        System.out.println("testCreateRole");
        String testdata_callerStaffID = "50";
        String testdata_name = "Supervisor";
        String testdata_accessLevel = "Store";
        RoleEntity result = accountManagementBean.createRole(testdata_callerStaffID, testdata_name, testdata_accessLevel);
        roleId = result.getId();
        System.out.println("test13CreateRole " + roleId);
        assertNotNull(result);
    }

    @Test
    public void test14GetRoleById() {
        System.out.println("testGetRoleById()");
        RoleEntity result = accountManagementBean.getRoleById(1L);
        assertNotNull(result);
    }

    @Test
    public void test15UpdateRole() {
        System.out.println("testUpdateRole");
        System.out.println("test15UpdateRole " + roleId);
        String testdata_callerStaffID = "50";
        Long testdata_roleID = roleId;
        String testdata_accessLevel = "Store";
        Boolean result = accountManagementBean.updateRole(testdata_callerStaffID, testdata_roleID, testdata_accessLevel);
        assertTrue(result);
    }

    @Test
    public void test16DeleteRole() {
        System.out.println("testDeleteRole");
        String testdata_callerStaffID = "50";
        Long testdata_roleID = roleId;
        Boolean result = accountManagementBean.deleteRole(testdata_callerStaffID, testdata_roleID);
        assertTrue(result);
    }

    @Test
    public void test17CheckIfRoleExists() {
        System.out.println("checkIfRoleExists");
        Boolean result = accountManagementBean.checkIfRoleExists("Administrator");
        assertTrue(result);
    }

    @Test
    public void test18ListAllRoles() {
        System.out.println("testListAllRoles");
        List result = accountManagementBean.listAllRoles();
        assertTrue(!result.isEmpty());
    }

    @Test
    public void test19SearchRole() {
        System.out.println("testSearchRole");
        String testdata_name = "Regional Manager";
        RoleEntity result = accountManagementBean.searchRole(testdata_name);
        assertNotNull(result);
    }

    @Test
    public void test20GetCountry() {
        System.out.println("testGetCountry");
        String testdata_countryName = "Singapore";
        CountryEntity result = accountManagementBean.getCountry(testdata_countryName);
        assertNotNull(result);
    }

    @Test
    public void test21CheckStaffInvalidLoginAttempts() {
        System.out.println("testCheckStaffInvalidLoginAttempts");
        String testdata_email = "admin@if.com";
        Integer result = accountManagementBean.checkStaffInvalidLoginAttempts(testdata_email);
        assertTrue(result >= 0);
    }

    @Test
    public void test22IsAccessRightExist() {
        System.out.println("testIsAccessRightExist");
        Long testdata_staffId = 10L;
        Long testdata_roleId = 5L;
        AccessRightEntity result = accountManagementBean.isAccessRightExist(testdata_staffId, testdata_roleId);
        assertNull(result);
    }

    private AccountManagementBeanRemote lookupAccountManagementBeanRemote() {
        try {
            Context c = new InitialContext();
            return (AccountManagementBeanRemote) c.lookup("java:global/IS3102_Project/IS3102_Project-ejb/AccountManagementBean!CommonInfrastructure.AccountManagement.AccountManagementBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
