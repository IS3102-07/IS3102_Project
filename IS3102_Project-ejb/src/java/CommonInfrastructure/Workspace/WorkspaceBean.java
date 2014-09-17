package CommonInfrastructure.Workspace;

import EntityManager.AnnouncementEntity;
import EntityManager.MessageEntity;
import EntityManager.StaffEntity;
import EntityManager.ToDoEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class WorkspaceBean implements WorkspaceBeanLocal {

    @PersistenceContext
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean sendMessage(Long senderStaffID, Long receiverStaffID, String message) {
        System.out.println("sendMessage() called with senderStaffID:" + senderStaffID);
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t where t.id=:senderStaffID");
            q.setParameter("senderStaffID", senderStaffID);
            StaffEntity senderStaffEntity = (StaffEntity) q.getSingleResult();
            q = em.createQuery("SELECT t FROM StaffEntity t where t.id=:receiverStaffID");
            q.setParameter("receiverStaffID", receiverStaffID);
            StaffEntity receiverStaffEntity = (StaffEntity) q.getSingleResult();

            MessageEntity messageEntity = new MessageEntity();
            messageEntity.create(senderStaffEntity, receiverStaffEntity, message);
            messageEntity.setSentDate(new Date());
            messageEntity.setMessageRead(true); //sender sent items

            List<MessageEntity> senderSentMessages = senderStaffEntity.getSentMessages();
            senderSentMessages.add(messageEntity);
            senderStaffEntity.setSentMessages(senderSentMessages);
            em.merge(senderStaffEntity);

            messageEntity.setMessageRead(false);// receiver inbox
            List<MessageEntity> receiverInboxMessages = receiverStaffEntity.getInboxMessages();
            receiverInboxMessages.add(messageEntity);
            receiverStaffEntity.setInboxMessages(receiverInboxMessages);
            em.merge(receiverStaffEntity);

            System.out.println("Message sent.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to send a message:\n" + ex);
            return false;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean sendMessageToMultipleReceiver(Long senderStaffID, List<Long> receiverStaffID, String message) {
        System.out.println("sendMessageToMultipleReceiver() called with senderStaffID:" + senderStaffID);
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t where t.id=:senderStaffID");
            q.setParameter("senderStaffID", senderStaffID);
            StaffEntity senderStaffEntity = (StaffEntity) q.getSingleResult();

            //Get all the entity of the receiver
            List<StaffEntity> receivers = new ArrayList();
            for (Long currentStaffID : receiverStaffID) {
                q = em.createQuery("SELECT t FROM StaffEntity t where t.id=:receiverStaffID");
                q.setParameter("receiverStaffID", currentStaffID);
                StaffEntity currentStaff = (StaffEntity) q.getSingleResult();
                receivers.add(currentStaff);
            }

            //Add the message to all the receiver
            for (StaffEntity currentStaff : receivers) {
                MessageEntity messageEntity = new MessageEntity();
                messageEntity.create(senderStaffEntity, receivers, message);
                messageEntity.setSentDate(new Date());
                messageEntity.setMessageRead(false); //receiver inbox
                em.persist(messageEntity);
                List<MessageEntity> receiverInboxMessages = currentStaff.getInboxMessages();
                receiverInboxMessages.add(messageEntity);
                currentStaff.setInboxMessages(receiverInboxMessages);
                em.merge(currentStaff);
            }

            //Add message to sender sent items
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.create(senderStaffEntity, receivers, message);
            messageEntity.setSentDate(new Date());
            messageEntity.setMessageRead(true); //sender sent items

            List<MessageEntity> senderSentMessages = senderStaffEntity.getSentMessages();
            senderSentMessages.add(messageEntity);
            senderStaffEntity.setSentMessages(senderSentMessages);
            em.merge(senderStaffEntity);

            System.out.println("Message sent.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to send a message:\n" + ex);
            return false;
        }
    }

    public List<MessageEntity> listAllInboxMessages(Long staffID) {
        System.out.println("listAllInboxMessages() called with staffID:" + staffID);
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t where t.id=:staffID");
            q.setParameter("staffID", staffID);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
            List<MessageEntity> inboxMessages = staffEntity.getInboxMessages();
            System.out.println("Message list returned.");
            return inboxMessages;
        } catch (Exception ex) {
            System.out.println("\nServer error in listing all inbox messages:\n" + ex);
            return null;
        }
    }

    public List<MessageEntity> listAllUnreadInboxMessages(Long staffID) {
        System.out.println("listAllUnreadInboxMessages() called with staffID:" + staffID);
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t where t.id=:staffID");
            q.setParameter("staffID", staffID);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
            List<MessageEntity> inboxMessages = staffEntity.getInboxMessages();
            List<MessageEntity> unreadInboxMessages = new ArrayList<>();
            for (MessageEntity currentMessage : inboxMessages) {
                if (!currentMessage.getMessageRead()) {
                    unreadInboxMessages.add(currentMessage);
                }
            }
            System.out.println("Unread message list returned.");
            return inboxMessages;
        } catch (Exception ex) {
            System.out.println("\nServer error in listing all unread inbox messages:\n" + ex);
            return null;
        }
    }

    public List<MessageEntity> listAllSentMessages(Long staffID) {
        System.out.println("listAllSentMessages() called with staffID:" + staffID);
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t where t.id=:staffID");
            q.setParameter("staffID", staffID);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
            List<MessageEntity> sentMessages = staffEntity.getSentMessages();
            System.out.println("Message list returned.");
            return sentMessages;
        } catch (Exception ex) {
            System.out.println("\nServer error in listing all sent messages:\n" + ex);
            return null;
        }
    }

    public MessageEntity readInboxMessage(Long staffID, Long messageID) {
        System.out.println("readMessage() called with staffID:" + staffID + " & messageID: " + messageID);
        MessageEntity message = null;
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t where t.id=:staffID");
            q.setParameter("staffID", staffID);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
            List<MessageEntity> inboxMessages = staffEntity.getInboxMessages();
            for (MessageEntity currentMessage : inboxMessages) {
                if (currentMessage.getId().equals(messageID)) {
                    currentMessage.setMessageRead(true);
                    message = currentMessage;
                    break;
                }
            }
            staffEntity.setInboxMessages(inboxMessages);
            em.merge(staffEntity);
            System.out.println("Message returned.");
            return message;
        } catch (Exception ex) {
            System.out.println("\nServer error in reading a message:\n" + ex);
            return null;
        }
    }

    public boolean deleteInboxMessage(Long staffID, Long messageID) {
        System.out.println("deleteInboxMessage() called with staffID:" + staffID + " & messageID: " + messageID);
        MessageEntity message = null;
        int index = 0;
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t where t.id=:staffID");
            q.setParameter("staffID", staffID);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
            List<MessageEntity> inboxMessages = staffEntity.getInboxMessages();
            for (MessageEntity currentMessage : inboxMessages) {
                if (currentMessage.getId().equals(messageID)) {
                    inboxMessages.remove(index);
                    index++;
                    break;
                }
            }
            staffEntity.setInboxMessages(inboxMessages);
            em.merge(staffEntity);
            System.out.println("Message deleted.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer error in deleting a message:\n" + ex);
            return false;
        }
    }

    public boolean deleteSentMessage(Long staffID, Long messageID) {
        System.out.println("deleteSentMessage() called with staffID:" + staffID + " & messageID: " + messageID);
        MessageEntity message = null;
        int index = 0;
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t where t.id=:staffID");
            q.setParameter("staffID", staffID);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
            List<MessageEntity> sentMessages = staffEntity.getSentMessages();
            for (MessageEntity currentMessage : sentMessages) {
                if (currentMessage.getId().equals(messageID)) {
                    sentMessages.remove(index);
                    index++;
                    break;
                }
            }
            staffEntity.setSentMessages(sentMessages);
            em.merge(staffEntity);
            System.out.println("Message deleted.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer error in deleting a message:\n" + ex);
            return false;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean makeAnnouncement(String sender, String title, String message, Date expiryDate) {
        System.out.println("makeAnnouncement() called with sender:" + sender);
        try {
            AnnouncementEntity announcement = new AnnouncementEntity(sender, title, message, expiryDate);
            em.persist(announcement);
            System.out.println("Announcement broadcasted.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to broadcast annoucement:\n" + ex);
            return false;
        }
    }

    @Override
    public String getStaffEmail(Long staffID) {
        System.out.println("getStaffEmail() called with staffID:" + staffID);
        try {
            StaffEntity staffEntity = (StaffEntity) em.getReference(StaffEntity.class, staffID);
            System.out.println("Staff email returned succesfully");
            return staffEntity.getEmail();
        } catch (EntityNotFoundException ex) {
            System.out.println("Failed to get staff, staff not found.\n" + ex);
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getStaffEmail:\n" + ex);
            return null;
        }
    }

    @Override
    public String getStaffName(Long staffID) {
        System.out.println("getStaffEmail() called with staffID:" + staffID);
        try {
            StaffEntity staffEntity = (StaffEntity) em.getReference(StaffEntity.class, staffID);
            System.out.println("Staff email returned succesfully");
            return staffEntity.getName();
        } catch (EntityNotFoundException ex) {
            System.out.println("Failed to get staff, staff not found.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getStaffEmail:\n" + ex);
            return null;
        }
    }

    @Override
    public boolean addToDoList(String description) {
        System.out.println("addToDoList() called.");
        try {
            ToDoEntity toDo = new ToDoEntity(description, false);
            em.persist(toDo);
            System.out.println("addToDoList() successful.");
            return true;
        } catch (Exception ex) {
            System.out.println("failed to addToDoList():\n" + ex);
            return false;
        }
    }

    @Override
    public boolean removeToDoList(Long id) {
        System.out.println("removeToDoList() called.");
        try {
            ToDoEntity toDo = em.find(ToDoEntity.class, id);
            em.remove(toDo);
            System.out.println("removeToDoList() is successful.");
            return true;
        } catch (EntityNotFoundException ex) {
            System.out.println("failed to perform removeToDoList():\n" + ex);
            return false;
        }
    }

    @Override
    public boolean editToDoList(Long id, String description) {
        System.out.println("editToDoList() is called.");
        try {
            ToDoEntity toDo = em.find(ToDoEntity.class, id);
            toDo.setDescription(description);
            em.merge(toDo);
            System.out.println("editToDoList() is successful.");
            return true;
        } catch (EntityNotFoundException ex) {
            System.out.println("editToDoList() EntityNotFoundException:\n" + ex);
            return false;
        } catch (Exception ex) {
            System.out.println("failed to perform editToDoList():\n" + ex);
            return false;
        }
    }

    @Override
    public List<ToDoEntity> getAllToDoList() {
        System.out.println("getAllToDoList() is called.");
        try {
            Query q = em.createQuery("Select td from ToDoEntity td");
            List<ToDoEntity> toDoList = q.getResultList();
            System.out.println("getAllToDoList() is successful.");
            return toDoList;
        } catch (Exception ex) {
            System.out.println("failed to getAllToDoList().\n" + ex);
            return null;
        }
    }

    @Override
    public boolean markToDoListAsDone(Long id) {
        System.out.println("markToDoListAsDone() is called.");
        try {
            ToDoEntity toDo = em.find(ToDoEntity.class, id);
            toDo.setIsDone(true);
            em.merge(toDo);
            System.out.println("markToDoListAsDone() is successful.");
            return true;
        } catch (EntityNotFoundException ex) {
            System.out.println("markToDoListAsDone() EntityNotFoundException:\n" + ex);
            return false;
        } catch (Exception ex) {
            System.out.println("failed to perform markToDoListAsDone():\n" + ex);
            return false;
        }
    }

    @Override
    public boolean markToDoListAsUndone(Long id) {
        System.out.println("markToDoListAsUndone() is called.");
        try {
            ToDoEntity toDo = em.find(ToDoEntity.class, id);
            toDo.setIsDone(false);
            em.merge(toDo);
            System.out.println("markToDoListAsUndone() is successful.");
            return true;
        } catch (EntityNotFoundException ex) {
            System.out.println("markToDoListAsUndone() EntityNotFoundException:\n" + ex);
            return false;
        } catch (Exception ex) {
            System.out.println("failed to perform markToDoListAsUndone():\n" + ex);
            return false;
        }
    }
}
