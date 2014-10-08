package JUnit_Testing;

import CommonInfrastructure.Workspace.WorkspaceBeanLocal;
import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import HelperClasses.ReturnHelper;
import javax.ejb.EJB;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class IslandFurniture_JUnit {

    @EJB
    ItemManagementBeanLocal itemManagementBean;
    @EJB
    WorkspaceBeanLocal workspaceBean;

    public IslandFurniture_JUnit() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {
        try {
//            ReturnHelper helper = itemManagementBean.addCountryItemPricing(100L, "F10", 10.0);
//            System.out.println(helper.getMessage());
//            System.out.println(helper.getIsSuccess());
            Boolean b = itemManagementBean.checkIfSKUIsFurniture("F1");
            System.out.println(b + "   asdasdad");
        } catch (Exception ex) {
            System.out.println("exception!!!");
            ex.printStackTrace();
        }
    }

}
