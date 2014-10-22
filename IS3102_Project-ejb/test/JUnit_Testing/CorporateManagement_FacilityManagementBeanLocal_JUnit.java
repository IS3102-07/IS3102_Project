package JUnit_Testing;

import CorporateManagement.FacilityManagement.FacilityManagementBeanRemote;
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

public class CorporateManagement_FacilityManagementBeanLocal_JUnit {
    
    
    FacilityManagementBeanRemote facilityManagementBean = lookupFacilityManagementBeanRemote();
    
    
    public CorporateManagement_FacilityManagementBeanLocal_JUnit() {
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
    public void checkNameExistsOfRegionalOffice() {
        String str = "Asia Pacific Regional Office";
        Boolean result = facilityManagementBean.checkNameExistsOfRegionalOffice(str);
        assertTrue(result);
    }        

    private FacilityManagementBeanRemote lookupFacilityManagementBeanRemote() {
        try {
            Context c = new InitialContext();
            return (FacilityManagementBeanRemote) c.lookup("java:global/IS3102_Project/IS3102_Project-ejb/FacilityManagementBean!CorporateManagement.FacilityManagement.FacilityManagementBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
