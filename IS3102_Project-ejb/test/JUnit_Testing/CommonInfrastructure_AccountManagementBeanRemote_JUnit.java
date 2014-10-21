package JUnit_Testing;

import CommonInfrastructure.AccountManagement.AccountManagementBeanRemote;
import EntityManager.CountryEntity;
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

public class CommonInfrastructure_AccountManagementBeanRemote_JUnit implements Serializable{
    
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
    public void testRegisterMember(){
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
    }
    
    @Test
    public void testEditMember() {
    }
    
    @Test
    public void testLoginMember() {
        
    }
    
    @Test
    public void testCheckStaffEmailExists() {
    }
    
    @Test
    public void testRegisterStaff() {
    }
    
    @Test
    public void testGetStaffByEmail() {
    }
    
    @Test
    public void testGetStaffById() {
    }
    
    @Test
    public void testGetRoleById() {
    }
    
    @Test
    public void testEditStaff() {
    }
    
    @Test
    public void testResetStaffPassword() {
    }
    
    @Test
    public void testResetMemberPassword() {
    }
    
    @Test
    public void testRemoveStaff() {
    }
    
    @Test
    public void testRemoveMember() {
    }
    
    @Test
    public void testLoginStaff() {
        System.out.println("loginStaff");
        //StaffEntity result = accountManagementBean.loginStaff("admin@if.com", "admin");
        //assertFalse(result.equals(null));
    }
    
    @Test
    public void testListAllStaff() {
        System.out.println("listAllStaff");
//        List result = accountManagementBean.listAllStaff();
//        assertFalse(result.isEmpty());
//        assertEquals(result.size(), result.size());
    }
    
    @Test
    public void testCreateRole() {
    }
    
    @Test
    public void testUpdateRole() {
    }
    
    @Test
    public void testDeleteRole() {
    }
    
    @Test
    public void testCheckIfRoleExists() {
        System.out.println("checkIfRoleExists");
        Boolean result = accountManagementBean.checkIfRoleExists("Administrator");
        assertFalse(result == false);
        assertTrue(result);
    }
    
    @Test
    public void testRoleHasMembersAssigned() {
    }
    
    @Test
    public void testListAllRoles() {
        System.out.println("checkIfRoleExists");
        Boolean result = accountManagementBean.checkIfRoleExists("Administrator");
        assertFalse(result == false);
        assert (result);
    }
    
    @Test
    public void testListRolesHeldByStaff() {
    }
    
    @Test
    public void testSearchRole() {
    }
    
    @Test
    public void testCheckIfStaffHasRole() {
    }
    
    @Test
    public void testAddStaffRole() {
    }
    
    @Test
    public void testRemoveStaffRole() {
    }
    
    @Test
    public void testEditStaffRole() {
    }
    
    @Test
    public void testGetCountry() {
    }
    
    @Test
    public void testGeneratePasswordSalt() {
    }
    
    @Test
    public void testGeneratePasswordHash() {
    }
    
    @Test
    public void testCheckStaffInvalidLoginAttempts() {
    }
    
    @Test
    public void testCreateAccessRight() {
    }
    
    @Test
    public void testIsAccessRightExist() {
    }
    
    @Test
    public void testCanStaffAccessToTheRegionalOffice() {
    }
    
    @Test
    public void testCanStaffAccessToTheStore() {
    }
    
    @Test
    public void testCanStaffAccessToTheManufacturingFacility() {
    }
    
    @Test
    public void testCanStaffAccessToTheWarehouse() {
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
