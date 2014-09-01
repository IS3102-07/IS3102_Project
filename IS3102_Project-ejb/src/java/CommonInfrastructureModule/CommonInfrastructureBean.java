package CommonInfrastructureModule;


import EntityManager.MemberEntity;
import EntityManager.StaffEntity;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
public class CommonInfrastructureBean implements CommonInfrastructureBeanLocal {

    @PersistenceContext
    private EntityManager em;

    public CommonInfrastructureBean() {
        System.out.println("\nCommonInfrastructure Server (EJB) created.");

        /*
         //Test
         String msg;
         //Create country
         CountryEntity countryEntity = new CountryEntity();
         countryEntity.create("Singapore", "Singapore Dollars");
         em.persist(countryEntity);
         countryEntity = new CountryEntity();
         countryEntity.create("Malaysia", "Malaysian Ringget");
         em.persist(countryEntity);
         //Create item
         ItemEntity itemEntity = new ItemEntity();
         itemEntity.create("Chair", "materialID thingy", "This is a simple and cheap chair designed for sitting", "http://");
         em.persist(itemEntity);
        
         //Set the item price
         Query q;
         String country = "Singapore";
         Integer retailPrice = 5;
        
         q = em.createQuery("SELECT t FROM ItemEntity t WHERE t.id=:id");
         q.setParameter("id", 1);
         itemEntity = (ItemEntity) q.getSingleResult();
         q = em.createQuery("SELECT t FROM CountryEntity t where t.name=:countryName");
         q.setParameter("countryName", country);
         countryEntity = (CountryEntity) q.getSingleResult();
        
         country = "Malaysia";
         retailPrice = 10;
        
         q = em.createQuery("SELECT t FROM ItemEntity t WHERE t.id=:id");
         q.setParameter("id", 1);
         itemEntity = (ItemEntity) q.getSingleResult();
         q = em.createQuery("SELECT t FROM CountryEntity t where t.name=:countryName");
         q.setParameter("countryName", country);
         countryEntity = (CountryEntity) q.getSingleResult();
         try {
         // Create the country specific pricing entry
         ItemCountryEntity itemCountryEntity = new ItemCountryEntity();
         itemCountryEntity.create(countryEntity, retailPrice);
         em.persist(itemCountryEntity);
            
         // Add it into the item list & country list
         Collection<ItemCountryEntity> itemCountryList;
         itemCountryList = itemEntity.getItemCountryList();
         itemCountryList.add(itemCountryEntity);
         itemEntity.setItemCountryList(itemCountryList);
         em.persist(itemEntity);
           
         } catch (Exception ex) {
         msg = "\nServer error while adding a book.\n";
         System.out.println(msg);
         }*/
    }

    public boolean checkMemberUsernameExists(String username) {
        System.out.println("checkMemberUsernameExists() called with:" + username);
        Query q = em.createQuery("SELECT t FROM MemberEntity t WHERE t.username=:username");
        q.setParameter("username", username);
        try {
            MemberEntity memberEntity = (MemberEntity) q.getSingleResult();
        } catch (NoResultException ex) {
            return false;
        }
        return true;
    }

    public boolean checkMemberEmailExists(String email) {
        System.out.println("checkMemberEmailExists() called with:" + email);
        Query q = em.createQuery("SELECT t FROM MemberEntity t WHERE t.email=:email");
        q.setParameter("email", email);
        try {
            MemberEntity memberEntity = (MemberEntity) q.getSingleResult();
        } catch (NoResultException ex) {
            return false;
        }
        return true;
    }

    public boolean registerMember(String name, String address, Date DOB, String email, Integer phone, String country, String city, Integer zipCode, String username, String password) {
        System.out.println("registerMember() called with:" + name);
        Long memberID;
        String passwordSalt = generatePasswordSalt();
        String passwordHash = generatePasswordHash(passwordSalt, password);
        try {
            MemberEntity memberEntity = new MemberEntity();
            memberEntity.create(name, address, DOB, email, phone, country, city, zipCode, username, passwordHash);
            em.persist(memberEntity);
            memberID = memberEntity.getMemberID();
            System.out.println("Member \"" + name + "\" registered successfully as id:" + memberID);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to register member:\n" + ex);
            return false;
        }
    }

    public boolean checkStaffUsernameExists(String username) {
        System.out.println("checkStaffUsernameExists() called with:" + username);
        Query q = em.createQuery("SELECT t FROM StaffEntity t WHERE t.username=:username");
        q.setParameter("username", username);
        try {
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
        } catch (NoResultException ex) {
            return false;
        }
        return true;
    }

    public boolean checkStaffEmailExists(String email) {
        System.out.println("checkStaffEmailExists() called with:" + email);
        Query q = em.createQuery("SELECT t FROM StaffEntity t WHERE t.email=:email");
        q.setParameter("email", email);
        try {
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
        } catch (NoResultException ex) {
            return false;
        }
        return true;
    }

    public boolean registerStaff(String identificationNo, String name, String gender, Integer phone, String email, String address, String username, String password) {
        System.out.println("registerStaff() called with:" + name);
        Long staffID;
        String passwordSalt = generatePasswordSalt();
        String passwordHash = generatePasswordHash(passwordSalt, password);
        try {
            StaffEntity staffEntity = new StaffEntity();
            staffEntity.create(name, phone, email, address, username, passwordSalt, passwordHash);
            em.persist(staffEntity);
            staffID = staffEntity.getId();
            System.out.println("Staff \"" + name + "\" registered successfully as id:" + staffID);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to register staff:\n" + ex);
            return false;
        }
    }

    // Generate a random salt for use in hashing the password;
    private String generatePasswordSalt() {
        byte[] salt = new byte[16];
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.nextBytes(salt);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("\nServer failed to generate password salt.\n" + ex);
        }
        return Arrays.toString(salt);
    }

    // Uses supplied salt and password to generate a hashed password
    private String generatePasswordHash(String salt, String password) {
        String passwordHash = null;
        try {
            password = salt + password;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            passwordHash = sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("\nServer failed to hash password.\n" + ex);
        }
        return passwordHash;
    }
}
