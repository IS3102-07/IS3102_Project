package EntityManager;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class MemberEntity implements Serializable {

    private static long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DOB;
    private String email;
    private Integer phone;
    private String country;
    private String city;
    private Integer zipCode;
    private String username;
    private String passwordSalt;
    private String passwordHash;
    private Integer loyaltyPoints;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    //private FeedbackEntity feedback;
    //@OneToMany(cascade = CascadeType.ALL)
    //private WishListEntity wishlist;
    //@OneToMany
    //private RedemptionOrderEntity redemptionOrder;
    //@OneToMany
    //private SalesRecordEntity salesRecord;
    //TODO Subscription
    public void create(String name, String address, Date DOB, String email, Integer phone, String country, String city, Integer zipCode, String username, String passwordHash) {
        this.setName(name);
        this.setAddress(address);
        this.setDOB(DOB);
        this.setEmail(email);
        this.setPhone(phone);
        this.setCountry(country);
        this.setCity(city);
        this.setZipCode(zipCode);
        this.setUsername(username);
        this.setPasswordSalt(passwordSalt);
        this.setPasswordHash(passwordHash);
        this.setLoyaltyPoints(0);
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
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
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
    public Integer getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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
}
