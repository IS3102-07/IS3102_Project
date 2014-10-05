package EntityManager;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class MemberEntity implements Serializable {

    private static long serialVersionUID = 1L;
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    private String name;
    @Lob
    private String address;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DOB;
    @Lob
    private String email;
    @Lob
    private String phone;
    @Lob
    private String city;
    @Lob
    private String zipCode;
    private String passwordSalt;
    private String passwordHash;
    private Integer loyaltyPoints;
    private Boolean accountActivationStatus;
    private String activationCode;
    private Boolean accountLockStatus;
    private String unlockCode;
    private String passwordReset;
    
    @OneToOne
    CountryEntity country;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    //private FeedbackEntity feedback;
    //@OneToMany(cascade = CascadeType.ALL)
    //private WishListEntity wishlist;
    //@OneToMany
    //private RedemptionOrderEntity redemptionOrder;
    //@OneToMany
    //private SalesRecordEntity salesRecord;
    //TODO Subscription
    public void create(String name, String address, Date DOB, String email, String phone, CountryEntity country, String city, String zipCode, String passwordHash, String passwordSalt) {
        this.setName(name);
        this.setAddress(address);
        this.setDOB(DOB);
        this.setEmail(email);
        this.setPhone(phone);
        this.setCountry(country);
        this.setCity(city);
        this.setZipCode(zipCode);
        this.setPasswordSalt(passwordSalt);
        this.setPasswordHash(passwordHash);
        this.setLoyaltyPoints(0);
        setAccountActivationStatus(false);
        setActivationCode();
        setAccountLockStatus(false);
        setUnlockCode();
        setPasswordReset();
    }

    public Long getMemberID() {
        return getId();
    }

    public void setId(Long id) {
        this.setId((long) id);
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
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

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MemberEntity)) {
            return false;
        }
        MemberEntity other = (MemberEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
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
     * @return the DOB
     */
    public Date getDOB() {
        return DOB;
    }

    /**
     * @param DOB the DOB to set
     */
    public void setDOB(Date DOB) {
        this.DOB = DOB;
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
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the points
     */
    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    /**
     * @param points the points to set
     */
    public void setLoyaltyPoints(Integer points) {
        this.loyaltyPoints = points;
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

    /**
     * @return the feedback
     */
//    public Collection<FeedbackEntity> getFeedback() {
//        return feedback;
//    }
//
//    /**
//     * @param feedback the feedback to set
//     */
//    public void setFeedback(Collection<FeedbackEntity> feedback) {
//        this.feedback = feedback;
//    }
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

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

}
