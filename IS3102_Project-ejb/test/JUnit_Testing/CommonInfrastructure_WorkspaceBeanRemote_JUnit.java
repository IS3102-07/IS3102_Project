package JUnit_Testing;

import CommonInfrastructure.Workspace.WorkspaceBeanRemote;
import EntityManager.MessageInboxEntity;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CommonInfrastructure_WorkspaceBeanRemote_JUnit {

    WorkspaceBeanRemote workspaceBean = lookupWorkspaceBeanRemote();

    public CommonInfrastructure_WorkspaceBeanRemote_JUnit() {
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
    public void testListAllInboxMessages() {
        System.out.println("testListAllInboxMessages");
        Long testdata_staffID = 1L;
        List<MessageInboxEntity> result = workspaceBean.listAllInboxMessages(testdata_staffID);
        assertFalse(result.isEmpty());
        assertTrue(!result.isEmpty());
    }

    @Test
    public void testListAllUnreadInboxMessages() {
        System.out.println("testListAllUnreadInboxMessages");
        Long testdata_staffID = 1L;
        List result = workspaceBean.listAllUnreadInboxMessages(testdata_staffID);
        assertFalse(result.isEmpty());
        assertTrue(!result.isEmpty());
    }

    @Test
    public void testListAllOutboxMessages() {
        System.out.println("testListAllOutboxMessages");
        Long testdata_staffID = 1L;
        List result = workspaceBean.listAllOutboxMessages(testdata_staffID);
        assertFalse(result.isEmpty());
        assertTrue(!result.isEmpty());
    }

    @Test
    public void testDeleteSingleInboxMessage() {
        System.out.println("testDeleteSingleInboxMessage");
        Long testdata_staffID = 12L;
        Long testdata_messageID = 5L;
        Boolean result = workspaceBean.deleteSingleInboxMessage(testdata_staffID, testdata_messageID);
        assertTrue(result);
        assertFalse(result);
    }

    @Test
    public void testDeleteSingleOutboxMessage() {
        System.out.println("testDeleteSingleOutboxMessage");
        Long testdata_staffID = 3L;
        Long testdata_messageID = 251L;
        Boolean result = workspaceBean.deleteSingleOutboxMessage(testdata_staffID, testdata_messageID);
        assertTrue(result);
        assertFalse(result);
    }

    @Test
    public void testMakeAnnouncement() {
        System.out.println("testMakeAnnouncement");
        String testdata_callerStaffID = "12";
        String testdata_sender = "Administrator";
        String testdata_title = "Account registration";
        String testdata_message = "Please ensure that your account details are correct after registration";
        Date testdata_expiryDate = new Date();
        Boolean result = workspaceBean.makeAnnouncement(testdata_callerStaffID, testdata_sender, testdata_title, testdata_message, testdata_expiryDate);
        assertTrue(result);
        assertFalse(result);
    }

    @Test
    public void testGetListOfAllNotExpiredAnnouncement() {
        System.out.println("testGetListOfAllNotExpiredAnnouncement");
        List result = workspaceBean.getListOfAllNotExpiredAnnouncement();
        assertTrue(result.isEmpty());
        assertFalse(!result.isEmpty());
    }

    @Test
    public void testUpdateAnnouncement() {
        System.out.println("testUpdateAnnouncement");
        String testdata_callerStaffID = "12";
        Long testdata_announcementId = 1L;
        String testdata_message = "Please ensure that your account details are correct after registration";
        Date testdata_expiryDate = new Date();
        Boolean result = workspaceBean.updateAnnouncement(testdata_callerStaffID, testdata_announcementId, testdata_message, testdata_expiryDate);
        assertFalse(result);
        assertEquals(true, result);
    }

    @Test
    public void testDeleteAnnouncement() {
        System.out.println("testDeleteAnnouncement");
        String testdata_callerStaffID = "12";
        Long testdata_announcementId = 1L;
        Boolean result = workspaceBean.deleteAnnouncement(testdata_callerStaffID, testdata_announcementId);
        assertFalse(result);
        assertEquals(true, result);
    }

    @Test
    public void testAddToDoList() {
        System.out.println("testAddToDoList");
        Long testdata_staffId = 12L;
        String testdata_description = "Call Michael to buy parts from supplier for production.";
        Boolean result = workspaceBean.addToDoList(testdata_staffId, testdata_description);
        assertFalse(result);
        assertTrue(result);
    }

    @Test
    public void testRemoveToDoList() {
        System.out.println("testRemoveToDoList");
        Long testdata_staffId = 12L;
        Long testdata_toDoId = 3L;
        Boolean result = workspaceBean.removeToDoList(testdata_staffId, testdata_toDoId);
        assertTrue(result);
    }

    @Test
    public void testGetAllToDoListOfAStaff() {
        System.out.println("testGetAllToDoListOfAStaff");
        Long testdata_staffId = 12L;
        List result = workspaceBean.getAllToDoListOfAStaff(testdata_staffId);
        assertNull(result);
        assertTrue(result != null);
    }

    @Test
    public void testMarkToDoListAsDone() {
        System.out.println("testMarkToDoListAsDone");
        Long testdata_id = 40L;
        Boolean result = workspaceBean.markToDoListAsDone(testdata_id);
        assertFalse(!result);
        assertTrue(result);
    }

    @Test
    public void testMarkToDoListAsUndone() {
        System.out.println("testMarkToDoListAsDone");
        Long testdata_id = 53L;
        Boolean result = workspaceBean.markToDoListAsDone(testdata_id);
        assertFalse(!result);
        assertTrue(result);
    }

    @Test
    public void testToggleToDoListIsDone() {
        System.out.println("testToggleToDoListIsDone");
        Long testdata_id = 34L;
        Boolean result = workspaceBean.toggleToDoListIsDone(testdata_id);
        assertFalse(!result);
        assertTrue(result);
    }

    @Test
    public void testGetStaffEmail() {
        System.out.println("testGetStaffEmail");
        Long testdata_staffID = 12L;
        String result = workspaceBean.getStaffEmail(testdata_staffID);
        assertEquals("admin@if.com", result);
    }

    @Test
    public void testGetStaffName() {
        System.out.println("testGetStaffName");
        Long testdata_staffID = 12L;
        String result = workspaceBean.getStaffEmail(testdata_staffID);
        assertEquals("Administrator", result);
    }

    private WorkspaceBeanRemote lookupWorkspaceBeanRemote() {
        try {
            Context c = new InitialContext();
            return (WorkspaceBeanRemote) c.lookup("java:global/IS3102_Project/IS3102_Project-ejb/WorkspaceBean!CommonInfrastructure.Workspace.WorkspaceBeanRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
