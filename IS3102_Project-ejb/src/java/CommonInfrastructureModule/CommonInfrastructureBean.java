package CommonInfrastructureModule;

import EntityManager.CountryEntity;

import HelperClass.MemberData;
import HelperClass.RoleData;
import HelperClass.StaffData;
import EntityManager.MemberEntity;
import EntityManager.RoleEntity;
import EntityManager.StaffEntity;
import HelperClass.CountryData;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
        System.out.println("registerMember() called with name:" + name);
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

    public MemberData loginMember(String username, String password) {
        System.out.println("loginMember() called with username:" + username);
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity where t.username=:username AND t.password=:password");
            q.setParameter("username", username);
            q.setParameter("password", password);
            MemberEntity memberEntity = (MemberEntity) q.getSingleResult();
            MemberData memberData = new MemberData();
            memberData.create(memberEntity.getId(), memberEntity.getName(), memberEntity.getAddress(), memberEntity.getDOB(), memberEntity.getEmail(), memberEntity.getPhone(), memberEntity.getCountry(), memberEntity.getCity(), memberEntity.getZipCode(), memberEntity.getUsername(), memberEntity.getLoyaltyPoints());
            System.out.println("Member with username:" + username + " logged in successfully.");
            return memberData;
        } catch (NoResultException ex) {
            System.out.println("Login credentials provided were incorrect.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to login member:\n" + ex);
            return null;
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

    public StaffData registerStaff(String identificationNo, String name, Integer phone, String email, String address, String username, String password) {
        System.out.println("registerStaff() called with name:" + name);
        Long staffID;
        StaffData staffData = new StaffData();
        String passwordSalt = generatePasswordSalt();
        String passwordHash = generatePasswordHash(passwordSalt, password);
        try {
            StaffEntity staffEntity = new StaffEntity();
            staffEntity.create(name, phone, email, address, username, passwordSalt, passwordHash);
            em.persist(staffEntity);
            staffID = staffEntity.getId();
            System.out.println("Staff \"" + name + "\" registered successfully as id:" + staffID);
            staffData.create(staffID, identificationNo, name, phone, email, address, username, null);
            return staffData;
        } catch (Exception ex) {
            System.out.println("\nServer failed to register staff:\n" + ex);
            return null;
        }
    }

    public StaffData loginStaff(String username, String password) {
        System.out.println("loginStaff() called with username:" + username);
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity where t.username=:username AND t.password=:password");
            q.setParameter("username", username);
            q.setParameter("password", password);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
            StaffData staffData = new StaffData();
            staffData.create(staffEntity.getId(), staffEntity.getIdentificationNo(), staffEntity.getName(), staffEntity.getPhone(), staffEntity.getEmail(), staffEntity.getAddress(), staffEntity.getUsername(), staffEntity.getRoles());
            System.out.println("Staff with username:" + username + " logged in successfully.");
            return staffData;
        } catch (NoResultException ex) {
            System.out.println("Login credentials provided were incorrect.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to login staff:\n" + ex);
            return null;
        }
    }

    public List<RoleData> listAllRoles() {
        System.out.println("listAllRoles() called.");
        List<RoleEntity> roleEntities;
        List<RoleData> roleDataList = new ArrayList();
        RoleData roleData;
        int result = 0;
        try {
            Query q = em.createQuery("SELECT t FROM RoleEntity t");
            roleEntities = q.getResultList();
            for (RoleEntity roleEntity : roleEntities) {
                roleData = new RoleData();
                roleData.create(roleEntity.getId(),roleEntity.getName(), roleEntity.getAccessLevel(), roleEntity.getMembers());
                roleDataList.add(roleData);
                result++;
            }
            System.out.println("Returned "+result+" roles.");
            return roleDataList;
        } catch (Exception ex) {
            System.out.println("\nServer error while listing all roles.\n" + ex);
            return null;
        }
    }
    public RoleData searchRole(String name, String accessLevel) {
        System.out.println("searchRole() called with name:" + name);
        try {
            Query q = em.createQuery("SELECT t FROM RoleEntity where t.name=:name AND t.accessLevel=:accessLevel");
            q.setParameter("name", name);
            q.setParameter("accessLevel", name);
            RoleEntity roleEntity = (RoleEntity) q.getSingleResult();
            RoleData roleData = new RoleData();
            roleData.create(roleEntity.getId(), roleEntity.getName(), roleEntity.getAccessLevel(), roleEntity.getMembers());
            System.out.println("Role:" + name + " ,Access level:" + accessLevel + " found.");
            return roleData;
        } catch (NoResultException ex) {
            System.out.println("Role:" + name + " ,Access level:" + accessLevel + " not found.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to search for role:\n" + ex);
            return null;
        }
    }

    public boolean addStaffRole(Long staffID, Long roleID) {
        System.out.println("addStaffRole() called with staffID:" + staffID+", roleID:"+roleID);
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity where t.id=:id");
            q.setParameter("id", staffID);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
            q = em.createQuery("SELECT t FROM RoleEntity where t.id=:id");
            q.setParameter("id", roleID);
            RoleEntity roleEntity = (RoleEntity) q.getSingleResult();
            Collection<RoleEntity> roles = staffEntity.getRoles();
            RoleEntity existingRole = new RoleEntity();
            for (RoleEntity currentRole : roles) {
                if (currentRole == roleEntity) {
                    System.out.println("Staff already has the role. Nothing is changed.");
                    return false; //Role already configured, shouldn't add
                }
            } // if cannot find the role inside the current list of roles for the member, then add it
            roles.add(roleEntity);
            staffEntity.setRoles(roles);
            em.persist(staffEntity);
            System.out.println("Role:" + existingRole.getName()
                    + " .Access level:" + existingRole.getAccessLevel() + " added successfully to staff id:" + staffID);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove role for staff:\n" + ex);
            return false;
        }
    }

    public boolean removeStaffRole(Long staffID, Long roleID) {
        System.out.println("removeStaffRole() called with staffID:" + staffID);
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity where t.id=:id");
            q.setParameter("id", staffID);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
            q = em.createQuery("SELECT t FROM RoleEntity where t.id=:id");
            q.setParameter("id", roleID);
            RoleEntity roleEntity = (RoleEntity) q.getSingleResult();
            Collection<RoleEntity> roles = staffEntity.getRoles();
            for (RoleEntity currentRole : roles) {
                if (currentRole == roleEntity) {
                    roles.remove(currentRole);
                    staffEntity.setRoles(roles);
                    em.persist(staffEntity);
                    System.out.println("Role:" + currentRole.getName()
                            + " .Access level:" + currentRole.getAccessLevel() + " removed successfully from staff id:" + staffID);
                    return true; //Found the role & removed it
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove role for staff:\n" + ex);
            return false;
        }
    }

    //This will overwrite ALL the current exisiting roles for the staff
    private boolean assignStaffRoles(Long staffID, Collection<RoleEntity> roles) {
        System.out.println("assignStaffRoles() called with staffID:" + staffID);
        StaffEntity staffEntity = new StaffEntity();
        Query q = em.createQuery("SELECT t FROM StaffEntity where t.id=:id");
        try {
            q.setParameter("id", staffID);
            staffEntity = (StaffEntity) q.getSingleResult();
            staffEntity.setRoles(roles);
            em.persist(staffEntity);
            System.out.println("Roles sucessfully added for staff id:" + staffID);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to assign roles for staff:\n" + ex);
            return false;
        }
    }

    public CountryData getCountry(String countryName) {
        System.out.println("getCountry() called with:" + countryName);
        Query q = em.createQuery("SELECT t FROM CountryEntity t WHERE t.name=:name");
        q.setParameter("name", countryName);
        try {
            CountryEntity countryEntity = (CountryEntity) q.getSingleResult();
            CountryData countryData = new CountryData();
            countryData.create(countryEntity.getId(), countryEntity.getName(), countryEntity.getCurrency(), countryEntity.getExchangeRate());
            System.out.println("Match found with countryID:"+countryEntity.getId());
            return countryData;
        } catch (NoResultException ex) {
            System.out.println("No matching country found.");
            return null;
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
