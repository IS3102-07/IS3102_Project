package JUnit_Testing;

import CommonInfrastructure.Workspace.WorkspaceBeanRemote;
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
    public void sendMessage() {
    }

    @Test
    public void sendMessageToMultipleReceiver() {
    }

    @Test
    public void listAllInboxMessages() {
    }

    @Test
    public void listAllUnreadInboxMessages() {
    }

    @Test
    public void listAllOutboxMessages() {
    }

    @Test
    public void readInboxMessage() {
    }

    @Test
    public void readSentMessage() {
    }

    @Test
    public void deleteSingleInboxMessage() {
    }

    @Test
    public void deleteSingleOutboxMessage() {
    }

    @Test
    public void makeAnnouncement() {
    }
    @Test
    public void getListOfAllNotExpiredAnnouncement() {
    }

    @Test
    public void updateAnnouncement() {
    }

    @Test
    public void deleteAnnouncement() {
    }

    @Test
    public void addToDoList() {
    }

    @Test
    public void removeToDoList() {
    }

    @Test
    public void getAllToDoListOfAStaff() {
    }

    @Test
    public void markToDoListAsDone() {
    }

    @Test
    public void markToDoListAsUndone() {
    }

    @Test
    public void toggleToDoListIsDone() {
    }

    @Test
    public void getStaffEmail() {
    }

    @Test
    public void getStaffName() {
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
