package CommonInfrastructure.Workspace;

import EntityManager.MessageEntity;
import EntityManager.ToDoEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface WorkspaceBeanLocal {
    //Check staff exists via AccountManagementBean first before doing anything in this bean

    public boolean sendMessage(Long senderStaffID, Long receiverStaffID, String message);

    public boolean sendMessageToMultipleReceiver(Long senderStaffID, List<Long> staffIDs, String message);

    public List<MessageEntity> listAllInboxMessages(Long staffID);

    public List<MessageEntity> listAllUnreadInboxMessages(Long staffID);

    public List<MessageEntity> listAllSentMessages(Long staffID);

    public MessageEntity readInboxMessage(Long staffID, Long messageID); // returns null if staff or message not found
    //Following returns true if operation suceeds

    public boolean deleteInboxMessage(Long staffID, Long messageID);

    public boolean deleteSentMessage(Long staffID, Long messageID);

    public boolean makeAnnouncement(String sender, String title, String message, Date expiryDate); //annoucement is just a message, added to all the staffEntity, with the annoucement flag set

    public boolean addToDoList(String description, boolean isDone);

    public boolean removeToDoList(Long id);

    public boolean editToDoList(String description);

    public List<ToDoEntity> getAllToDoList();

    public boolean markToDoListAsDone(Long id);

    public boolean markToDoListAsUndone(Long id);

    public String getStaffEmail(Long staffID);

    public String getStaffName(Long staffID);
}
