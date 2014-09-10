package EntityManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class MessageEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private StaffEntity sender;
    private List<StaffEntity> receiver;
    private String message;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date sentDate;
    private Boolean messageRead;
    private Boolean isAnnouncement;
    private String announcer;

    //1-1 message
    public void create(StaffEntity sender, StaffEntity receiver, String message){
        this.setSender(sender);
        List<StaffEntity> receivers = new ArrayList();
        receivers.add(receiver);
        this.setReceiver(receivers);
        this.setMessage(message);
        this.setIsAnnouncement(false);
    }
    //1-m message
    public void create(StaffEntity sender, List<StaffEntity> receivers, String message){
        this.setSender(sender);
        this.setReceiver(receivers);
        this.setMessage(message);
        this.setIsAnnouncement(false);
    }
    //annoucement
    public void create(String announcer, String message){
        this.setAnnouncer(announcer);
        this.setMessage(message);
        this.setIsAnnouncement(true);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessageEntity)) {
            return false;
        }
        MessageEntity other = (MessageEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityManager.MessageEntity[ id=" + id + " ]";
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the sentDate
     */
    public Date getSentDate() {
        return sentDate;
    }

    /**
     * @param sentDate the sentDate to set
     */
    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    /**
     * @return the messageRead
     */
    public Boolean getMessageRead() {
        return messageRead;
    }

    /**
     * @param messageRead the messageRead to set
     */
    public void setMessageRead(Boolean messageRead) {
        this.messageRead = messageRead;
    }

    /**
     * @return the isAnnouncement
     */
    public Boolean getIsAnnouncement() {
        return isAnnouncement;
    }

    /**
     * @param isAnnouncement the isAnnouncement to set
     */
    public void setIsAnnouncement(Boolean isAnnouncement) {
        this.isAnnouncement = isAnnouncement;
    }

    /**
     * @return the sender
     */
    public StaffEntity getSender() {
        return sender;
    }

    /**
     * @param sender the sender to set
     */
    public void setSender(StaffEntity sender) {
        this.sender = sender;
    }

    /**
     * @return the receiver
     */
    public List<StaffEntity> getReceiver() {
        return receiver;
    }

    /**
     * @param receiver the receiver to set
     */
    public void setReceiver(List<StaffEntity> receiver) {
        this.receiver = receiver;
    }
    public String getAnnouncer() {
        return announcer;
    }

    public void setAnnouncer(String announcer) {
        this.announcer = announcer;
    }

}