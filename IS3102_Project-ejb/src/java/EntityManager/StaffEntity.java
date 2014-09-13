package EntityManager;

import java.math.BigInteger;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.security.SecureRandom;
@Entity
public class StaffEntity implements Serializable {

    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String identificationNo;
    private String name;
    private Integer phone;
    private String email;
    private String address;
    private String passwordSalt;
    private String passwordHash;
    private Boolean accountActivationStatus;
    private String activationCode;
    private Boolean accountLockStatus;
    private String unlockCode;
    private String passwordReset;
    
    @ManyToMany
    private Collection<RoleEntity> roles;
    @OneToMany
    private List<MessageEntity> inboxMessages;
    @OneToMany
    private List<MessageEntity> sentMessages;

    public StaffEntity() {
    }

    public void create(String identificationNo, String name, Integer phone, String email, String address, String passwordSalt, String passwordHash) {
        this.setIdentificationNo(identificationNo);
        this.setName(name);
        this.setPhone(phone);
        this.setEmail(email);
        this.setAddress(address);
        this.setPasswordSalt(passwordSalt);
        this.setPasswordHash(passwordHash);
        setAccountActivationStatus(false);
        setActivationCode();
        setAccountLockStatus(false);
        setUnlockCode();
        setPasswordReset();
    }
    
    public String getPasswordReset() {
        return passwordReset;
    }
    public void setPasswordReset() {
        SecureRandom random = new SecureRandom();
        passwordReset = new BigInteger(130, random).toString();
    }
    public Boolean getAccountActivationStatus() {
        return accountActivationStatus;
    }
    
    public void setAccountActivationStatus(Boolean status) {
        this.accountActivationStatus = status;
    }
    public String getActivationCode() {
        return activationCode;
    }
    public void setActivationCode() {
        SecureRandom random = new SecureRandom();
        activationCode = new BigInteger(130, random).toString();
    }
    public Boolean getAccountLockStatus() {
        return accountLockStatus;
    }
    
    public void setAccountLockStatus(Boolean status) {
        this.accountLockStatus = status;
    }
    public String getUnlockCode() {
        return unlockCode;
    }
    public void setUnlockCode() {
        SecureRandom random = new SecureRandom();
        unlockCode = new BigInteger(130, random).toString();
    }
    /**
     * @return the identificationNo
     */
    public String getIdentificationNo() {
        return identificationNo;
    }

    /**
     * @param identificationNo the identificationNo to set
     */
    public void setIdentificationNo(String identificationNo) {
        this.identificationNo = identificationNo;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the phone
     */
    public Integer getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the passwordSalt
     */
    public String getPasswordSalt() {
        return passwordSalt;
    }

    /**
     * @param passwordSalt the passwordSalt to set
     */
    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    /**
     * @return the passwordHash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * @param passwordHash the passwordHash to set
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StaffEntity)) {
            return false;
        }
        StaffEntity other = (StaffEntity) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the roles
     */
    public Collection<RoleEntity> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles;
    }

    /**
     * @return the inboxMessages
     */
    public List<MessageEntity> getInboxMessages() {
        return inboxMessages;
    }

    /**
     * @param inboxMessages the inboxMessages to set
     */
    public void setInboxMessages(List<MessageEntity> inboxMessages) {
        this.inboxMessages = inboxMessages;
    }

    /**
     * @return the sentMessages
     */
    public List<MessageEntity> getSentMessages() {
        return sentMessages;
    }

    /**
     * @param sentMessages the sentMessages to set
     */
    public void setSentMessages(List<MessageEntity> sentMessages) {
        this.sentMessages = sentMessages;
    }
}
