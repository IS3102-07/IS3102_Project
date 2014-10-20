package JUnit_Testing;

import SCM.InboundAndOutboundLogistics.InboundAndOutboundLogisticsBeanRemote;
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

public class SCM_InboundAndOutboundLogisticsBeanRemote_JUnit {
    InboundAndOutboundLogisticsBeanRemote inboundAndOutboundLogisticsBean = lookupInboundAndOutboundLogisticsBeanRemote();

    public SCM_InboundAndOutboundLogisticsBeanRemote_JUnit() {
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
    public void createShippingOrderBasicInfo() {
    }

    @Test
    public void checkSKUExists() {
    }

    @Test
    public void updateShippingOrder() {
    }

    @Test
    public void updateShippingOrderStatus() {
    }

    @Test
    public void addLineItemToShippingOrder() {
    }

    @Test
    public void removeLineItemFromShippingOrder() {
    }

    @Test
    public void updateLineItemFromShippingOrder() {
    }

    @Test
    public void getShippingOrderList() {
    }

    @Test
    public void getShippingOrderById() {
    }

    @Test
    public void getShippingOrderListByWarehouseId() {
    }

    private InboundAndOutboundLogisticsBeanRemote lookupInboundAndOutboundLogisticsBeanRemote() {
        try {
            Context c = new InitialContext();
            return (InboundAndOutboundLogisticsBeanRemote) c.lookup("java:global/IS3102_Project/IS3102_Project-ejb/InboundAndOutboundLogisticsBean!SCM.InboundAndOutboundLogistics.InboundAndOutboundLogisticsBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
