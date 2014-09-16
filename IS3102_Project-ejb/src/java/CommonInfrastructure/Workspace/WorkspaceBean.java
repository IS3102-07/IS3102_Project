package CommonInfrastructure.Workspace;

import EntityManager.MessageEntity;
import EntityManager.StaffEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
            em.persist(senderStaffEntity);

            messageEntity.setMessageRead(false);// receiver inbox
            List<MessageEntity> receiverInboxMessages = receiverStaffEntity.getInboxMessages();
            receiverInboxMessages.add(messageEntity);
            receiverStaffEntity.setInboxMessages(receiverInboxMessages);
            em.persist(receiverStaffEntity);

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
                List<MessageEntity> receiverInboxMessages = currentStaff.getInboxMessages();
                receiverInboxMessages.add(messageEntity);
                currentStaff.setInboxMessages(receiverInboxMessages);
                em.persist(currentStaff);
            }

            //Add message to sender sent items
            MessageEntity messageEntity = new MessageEntity();
            messageEntity.create(senderStaffEntity, receivers, message);
            messageEntity.setSentDate(new Date());
            messageEntity.setMessageRead(true); //sender sent items

            List<MessageEntity> senderSentMessages = senderStaffEntity.getSentMessages();
            senderSentMessages.add(messageEntity);
            senderStaffEntity.setSentMessages(senderSentMessages);
            em.persist(senderStaffEntity);

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
            em.persist(staffEntity);
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
            em.persist(staffEntity);
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
            em.persist(staffEntity);
            System.out.println("Message deleted.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer error in deleting a message:\n" + ex);
            return false;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean makeAnnouncement(String announcer, String message) {
        System.out.println("makeAnnouncement() called with sender:" + announcer);
        try {

            Query q = em.createQuery("SELECT t FROM StaffEntity t");
            List<StaffEntity> staffEntities = q.getResultList();

            for (StaffEntity currentStaff : staffEntities) {
                MessageEntity announcement = new MessageEntity();
                announcement.create(announcer, message);
                em.persist(announcement);
                List<MessageEntity> inbox = currentStaff.getInboxMessages();
                inbox.add(announcement);
                currentStaff.setInboxMessages(inbox);
                em.merge(currentStaff);
            }
            System.out.println("Announcement sent to all staff.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to broadcast annoucement:\n" + ex);
            return false;
        }
    }

    public boolean addItemToToDoList(Long staffID, String item) {
        System.out.println("addItemToToDoList() called with staffID:" + staffID);
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t where t.id=:id");
            q.setParameter("id", staffID);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
            ArrayList<String> toDoListString = staffEntity.getToDoListItemString();
            ArrayList<Boolean> toDoListStatus = staffEntity.getToDoListItemStatus();
            toDoListString.add(item);
            toDoListStatus.add(false);
            staffEntity.setToDoListItemString(toDoListString);
            staffEntity.setToDoListItemStatus(toDoListStatus);
            em.persist(staffEntity);
            System.out.println("ToDO item added.");
            return true;
        } catch (NoResultException ex) {
            System.out.println("Staff not found.");
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to addItemToToDoList:\n" + ex);
            return false;
        }
    }

    public boolean removeItemFromToDoList(Long staffID, Integer index) {
        System.out.println("removeItemFromToDoList() called with staffID:" + staffID);
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t where t.id=:id");
            q.setParameter("id", staffID);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
            ArrayList<String> toDoListString = staffEntity.getToDoListItemString();
            ArrayList<Boolean> toDoListStatus = staffEntity.getToDoListItemStatus();
            toDoListString.remove(index);
            toDoListStatus.remove(index);
            staffEntity.setToDoListItemString(toDoListString);
            staffEntity.setToDoListItemStatus(toDoListStatus);
            em.merge(staffEntity);
            System.out.println("ToDO item removed.");
            return true;
        } catch (NoResultException ex) {
            System.out.println("Staff not found.");
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to removeItemFromToDoList:\n" + ex);
            return false;
        }
    }

    public boolean markItemInToDoList(Long staffID, Integer index, Boolean status) {
        System.out.println("removeItemFromToDoList() called with staffID:" + staffID);
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t where t.id=:id");
            q.setParameter("id", staffID);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
            ArrayList<Boolean> toDoListStatus = staffEntity.getToDoListItemStatus();
            toDoListStatus.set(index, status);
            staffEntity.setToDoListItemStatus(toDoListStatus);
            em.merge(staffEntity);
            System.out.println("ToDO item status updated.");
            return true;
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Index not found");
            return false;
        } catch (NoResultException ex) {
            System.out.println("Staff not found.");
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to markItemInToDoList:\n" + ex);
            return false;
        }
    }

    public ArrayList<String> getToDOListString(Long staffID) {
        System.out.println("getToDOListString() called with staffID:" + staffID);
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t where t.id=:id");
            q.setParameter("id", staffID);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
            ArrayList<String> toDoListItemString = staffEntity.getToDoListItemString();
            System.out.println("ToDO item string returned.");
            return toDoListItemString;
        } catch (NoResultException ex) {
            System.out.println("Staff not found.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getToDOListString:\n" + ex);
            return null;
        }
    }

    public ArrayList<Boolean> getToDOListStatus(Long staffID) {
        System.out.println("getToDOListStatus() called with staffID:" + staffID);
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t where t.id=:id");
            q.setParameter("id", staffID);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
            ArrayList<Boolean> toDoListItemStatus = staffEntity.getToDoListItemStatus();
            System.out.println("ToDO item status returned.");
            return toDoListItemStatus;
        } catch (NoResultException ex) {
            System.out.println("Staff not found.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getToDOListStatus:\n" + ex);
            return null;
        }
    }
}
