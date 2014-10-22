package JUnit_Testing;

import CommonInfrastructure.AccountManagement.AccountManagementBeanRemote;
import EntityManager.AccessRightEntity;
import EntityManager.CountryEntity;
import EntityManager.MemberEntity;
import EntityManager.RoleEntity;
import EntityManager.StaffEntity;
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

public class CommonInfrastructure_AccountManagementBeanRemote_JUnit implements Serializable {

    AccountManagementBeanRemote accountManagementBean = lookupAccountManagementBeanRemote();

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
    public void testCheckMemberEmailExists() {
        System.out.println("testCheckMemberEmailExists()");
        String testdata_email = "superman@hotmail.com";
        Boolean result = accountManagementBean.checkMemberEmailExists(testdata_email);
        assertTrue(result);
    }

    @Test
    public void testRegisterMember() {
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
    public void testGetMemberByEmail() {
        System.out.println("testGetMemberByEmail()");
        String testdata_email = "superman@hotmail.com";
        MemberEntity result = accountManagementBean.getMemberByEmail(testdata_email);
        assertNotNull(result);
    }

    @Test
    public void testLoginMember() {
        System.out.println("testLoginMember()");
        String testdata_email = "superman@hotmail.com";
        String testdata_password = "member";
        MemberEntity result = accountManagementBean.loginMember(testdata_email, testdata_password);
        assertNotNull(result);
    }

    @Test
    public void testCheckStaffEmailExists() {
        System.out.println("testCheckStaffEmailExists()");
        String testdata_email = "admin@if.com";
        Boolean result = accountManagementBean.checkStaffEmailExists(testdata_email);
        assertTrue(result);
    }

    @Test
    public void testRegisterStaff() {
        System.out.println("testRegisterStaff()");
        String testdata_callerStaffID = "98980000";
        String testdata_identificationNo = "S1717171";
        String testdata_email = "brandon@if.com";
        String testdata_name = "Zul";
        String testdata_address = "44 Tanglin Home, #04-12";
        String testdata_phone = "81234123";
        String testdata_password = "admin";
        StaffEntity result = accountManagementBean.registerStaff(testdata_callerStaffID, testdata_identificationNo, testdata_name, testdata_phone, testdata_email, testdata_address, testdata_password);
        assertNotNull(result);
    }

    @Test
    public void testGetStaffByEmail() {
        System.out.println("testGetStaffByEmail()");
        String testdata_email = "admin@if.com";
        StaffEntity result = accountManagementBean.getStaffByEmail(testdata_email);
        assertNotNull(result);
    }

    @Test
    public void testGetRoleById() {
        System.out.println("testGetRoleById()");
        RoleEntity result = accountManagementBean.getRoleById(1L);
        assertNotNull(result);
    }

    @Test
    public void testEditStaff() {
        System.out.println("testEditStaff()");
        String testdata_callerStaffID = "0000000";
        String testdata_address = "55 Tanglin Home, #04-12";
        String testdata_phone = "81234333";
        String testdata_password = "admin";
        Boolean result = accountManagementBean.editStaff(testdata_callerStaffID, Long.MIN_VALUE, testdata_phone, testdata_password, testdata_address);
        assertTrue(result);
    }

    @Test
    public void testResetStaffPassword() {
        System.out.println("testResetStaffPassword()");
        String testdata_email = "hello@if.com";
        String testdata_password = "12345678";
        Boolean result = accountManagementBean.resetStaffPassword(testdata_email, testdata_password);
        assertFalse(result);
    }

    @Test
    public void testRemoveStaff() {
        System.out.println("testRemoveStaff()");
        String testdata_callerStaffID = "abc";
        Long testdata_staffID = 1L;
        Boolean result = accountManagementBean.removeStaff(testdata_callerStaffID, testdata_staffID);
        assertFalse(result);
    }

    @Test
    public void testLoginStaff() {
        System.out.println("testLoginStaff");
        String testdata_email = "admin@if.com";
        String testdata_password = "admin";
        StaffEntity result = accountManagementBean.loginStaff(testdata_email, testdata_password);
        assertFalse(result.equals(null));
        assertNotNull(result);
    }

    @Test
    public void testListAllStaff() {
        System.out.println("testListAllStaff");
        List result = accountManagementBean.listAllStaff();
        assertFalse(result.isEmpty());
        assertTrue(!result.isEmpty());
    }

    @Test
    public void testCreateRole() {
        System.out.println("testCreateRole");
        String testdata_callerStaffID = "50";
        String testdata_name = "Supervisor";
        String testdata_accessLevel = "Store";
        RoleEntity result = accountManagementBean.createRole(testdata_callerStaffID, testdata_name, testdata_accessLevel);
        assertNotNull(result);
    }

    @Test
    public void testUpdateRole() {
        System.out.println("testUpdateRole");
        String testdata_callerStaffID = "50";
        Long testdata_roleID = 1L;
        String testdata_accessLevel = "Store";
        Boolean result = accountManagementBean.updateRole(testdata_callerStaffID, testdata_roleID, testdata_accessLevel);
        assertTrue(result);
    }

    @Test
    public void testDeleteRole() {
        System.out.println("testDeleteRole");
        String testdata_callerStaffID = "50";
        Long testdata_roleID = 100L;
        Boolean result = accountManagementBean.deleteRole(testdata_callerStaffID, testdata_roleID);
        assertFalse(result);
    }

    @Test
    public void testCheckIfRoleExists() {
        System.out.println("checkIfRoleExists");
        Boolean result = accountManagementBean.checkIfRoleExists("Administrator");
        assertFalse(result == false);
        assertTrue(result);
    }

    @Test
    public void testListAllRoles() {
        System.out.println("testListAllRoles");
        List result = accountManagementBean.listAllRoles();
        assertTrue(!result.isEmpty());
    }

    @Test
    public void testSearchRole() {
        System.out.println("testSearchRole");
        String testdata_name = "Regional Manager";
        RoleEntity result = accountManagementBean.searchRole(testdata_name);
        assertNotNull(result);
    }

    @Test
    public void testGetCountry() {
        System.out.println("testGetCountry");
        String testdata_countryName = "Singapore";
        CountryEntity result = accountManagementBean.getCountry(testdata_countryName);
        assertNotNull(result);
    }

    @Test
    public void testCheckStaffInvalidLoginAttempts() {
        System.out.println("testCheckStaffInvalidLoginAttempts");
        String testdata_email = "admin@if.com";
        Integer result = accountManagementBean.checkStaffInvalidLoginAttempts(testdata_email);
        assertTrue(result >= 0);
    }

    @Test
    public void testIsAccessRightExist() {
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
