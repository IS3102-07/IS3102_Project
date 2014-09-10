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
            Query q = em.createQuery("SELECT t FROM staffEntity where t.id=:senderStaffID");
            q.setParameter("senderStaffID", senderStaffID);
            StaffEntity senderStaffEntity = (StaffEntity) q.getSingleResult();
            q = em.createQuery("SELECT t FROM staffEntity where t.id=:receiverStaffID");
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
            Query q = em.createQuery("SELECT t FROM staffEntity where t.id=:senderStaffID");
            q.setParameter("senderStaffID", senderStaffID);
            StaffEntity senderStaffEntity = (StaffEntity) q.getSingleResult();

            //Get all the entity of the receiver
            List<StaffEntity> receivers = new ArrayList();
            for (Long currentStaffID : receiverStaffID) {
                q = em.createQuery("SELECT t FROM staffEntity where t.id=:receiverStaffID");
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
            Query q = em.createQuery("SELECT t FROM staffEntity where t.id=:staffID");
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
            Query q = em.createQuery("SELECT t FROM staffEntity where t.id=:staffID");
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
            Query q = em.createQuery("SELECT t FROM staffEntity where t.id=:staffID");
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
            Query q = em.createQuery("SELECT t FROM staffEntity where t.id=:staffID");
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
            Query q = em.createQuery("SELECT t FROM staffEntity where t.id=:staffID");
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
    public boolean makeAnnoucement(String announcer, String message) {
        System.out.println("makeAnnoucement() called with sender:" + announcer);
        try {
            MessageEntity annoucement = new MessageEntity();
            annoucement.create(announcer, message);

            Query q = em.createQuery("SELECT t FROM staffEntity");
            List<StaffEntity> staffEntities = q.getResultList();
            for (StaffEntity currentStaff : staffEntities) {
                List<MessageEntity> inbox = currentStaff.getInboxMessages();
                inbox.add(annoucement);
                currentStaff.setInboxMessages(inbox);
                em.persist(currentStaff);
            }
            System.out.println("Annoucement sent to all staff.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to broadcast annoucement:\n" + ex);
            return false;
        }
    }
}
