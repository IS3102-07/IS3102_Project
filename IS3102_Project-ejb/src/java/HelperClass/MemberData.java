package HelperClass;

import EntityManager.LoyaltyTierEntity;
import java.io.Serializable;
import java.util.Date;

public class MemberData implements Serializable {
    private long id;
    private String name;
    private String address;
    private Date DOB;
    private String email;
    private Integer phone;
    private String country;
    private String city;
    private Integer zipCode;
    private String username;
    private Integer points;
    private LoyaltyTierEntity loyaltyTier;

    /* Creates a new instance  */
    public void create(Long id, String name, String address, Date DOB, String email, Integer phone, String country, String city, Integer zipCode, String username, Integer points, LoyaltyTierEntity loyaltyTier) {
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        this.setDOB(DOB);
        this.setEmail(email);
        this.setPhone(phone);
        this.setCountry(country);
        this.setCity(city);
        this.setZipCode(zipCode);
        this.setUsername(username);
        this.setPoints(points);
        this.setLoyaltyTier(loyaltyTier);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

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
    public Integer getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

    /**
     * @return the loyaltyTier
     */
    public LoyaltyTierEntity getLoyaltyTier() {
        return loyaltyTier;
    }

    /**
     * @param loyaltyTier the loyaltyTier to set
     */
    public void setLoyaltyTier(LoyaltyTierEntity loyaltyTier) {
        this.loyaltyTier = loyaltyTier;
    }

    /**
     * @return the phone
     */
    public Integer getPhone() {
        return phone;
    }

}
