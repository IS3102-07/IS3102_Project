package JUnit_Testing;

import CommonInfrastructure.SystemSecurity.SystemSecurityBeanRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CommonInfrastructure_SystemSecurityBeanRemote_JUnit {
    SystemSecurityBeanRemote systemSecurityBean = lookupSystemSecurityBeanRemote();

    public CommonInfrastructure_SystemSecurityBeanRemote_JUnit() {
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
    public void sendActivationEmailForStaff() {
    }

    @Test
    public void sendActivationEmailForMember() {
    }

    @Test
    public void sendPasswordResetEmailForStaff() {
    }

    @Test
    public void sendPasswordResetEmailForMember() {
    }

    @Test
    public void activateStaffAccount() {
    }

    @Test
    public void activateMemberAccount() {
    }

    @Test
    public void validatePasswordResetForStaff() {
    }

    @Test
    public void validatePasswordResetForMember() {
    }

    private SystemSecurityBeanRemote lookupSystemSecurityBeanRemote() {
        try {
            Context c = new InitialContext();
            return (SystemSecurityBeanRemote) c.lookup("java:global/IS3102_Project/IS3102_Project-ejb/SystemSecurityBean!CommonInfrastructure.SystemSecurity.SystemSecurityBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
