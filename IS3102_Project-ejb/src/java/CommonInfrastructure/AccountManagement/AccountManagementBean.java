package CommonInfrastructure.AccountManagement;

import EntityManager.CountryEntity;
import CommonInfrastructure.SystemSecurity.SystemSecurityBeanLocal;
import EntityManager.MemberEntity;
import EntityManager.RoleEntity;
import EntityManager.StaffEntity;
import java.lang.reflect.Array;
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
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ejb.EJB;

@Stateful
public class AccountManagementBean implements AccountManagementBeanLocal {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private SystemSecurityBeanLocal systemSecurityBean;

    public AccountManagementBean() {
        System.out.println("\nCommonInfrastructure Server (EJB) created.");
    }

    @Override
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

    @Override
    public boolean registerMember(String name, String address, Date DOB, String email, String phone, CountryEntity country, String city, String zipCode, String password) {
        System.out.println("registerMember() called with email:" + email);
        Long memberID;
        String passwordSalt = generatePasswordSalt();
        String passwordHash = generatePasswordHash(passwordSalt, password);
        try {
            MemberEntity memberEntity = new MemberEntity();
            memberEntity.create(name, address, DOB, email, phone, country, city, zipCode, passwordHash, passwordSalt);
            em.persist(memberEntity);
            memberID = memberEntity.getMemberID();
            System.out.println("Email \"" + email + "\" registered successfully as id:" + memberID);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to register member:\n" + ex);
            return false;
        }
    }

    @Override
    public MemberEntity getMemberByEmail(String email) {
        System.out.println("getMemberByEmail() called.");
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t where t.email=:email");
            q.setParameter("email", email);
            MemberEntity memberEntity = (MemberEntity) q.getSingleResult();
            return memberEntity;
        } catch (NoResultException ex) {
            System.out.println("Failed to find member.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getMemberByEmail:\n" + ex);
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean editMember(Long memberID, Date DOB, String name, String address, String email, String phone, CountryEntity country, String city, String zipCode, String password) {
        System.out.println("editMember() called with memberID:" + memberID);

        String passwordSalt = generatePasswordSalt();
        String passwordHash = generatePasswordHash(passwordSalt, password);
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t where t.id=:id");
            q.setParameter("id", memberID);
            MemberEntity memberEntity = (MemberEntity) q.getSingleResult();
            if (memberEntity.getId() == memberID) {
                memberEntity.setDOB(DOB);
                memberEntity.setName(name);
                memberEntity.setAddress(address);
                memberEntity.setPhone(phone);
                memberEntity.setCountry(country);
                memberEntity.setCity(city);
                memberEntity.setZipCode(zipCode);
                if (!password.isEmpty()) {
                    memberEntity.setPasswordSalt(passwordSalt);
                }
                if (!password.isEmpty()) {
                    memberEntity.setPasswordHash(passwordHash);
                }
                em.merge(memberEntity);
                System.out.println("Server edited member details successfully.");
                return true;
            }

        } catch (NoResultException ex) {
            System.out.println("Failed to find member to edit.");
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to retrieve activationCode:\n" + ex);
            return false;
        }

        return true;
    }

    @Override
    public MemberEntity loginMember(String email, String password) {
        System.out.println("loginMember() called with email:" + email);
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t where t.email=:email");
            q.setParameter("email", email);
            MemberEntity memberEntity = (MemberEntity) q.getSingleResult();
            String passwordSalt = memberEntity.getPasswordSalt();
            String passwordHash = generatePasswordHash(passwordSalt, password);
            if (passwordHash.equals(memberEntity.getPasswordHash())) {
                System.out.println("Member with email:" + email + " logged in successfully.");
                return memberEntity;
            } else {
                System.out.println("Login credentials provided were incorrect, password wrong.");
                return null;
            }
        } catch (NoResultException ex) {//cannot find staff with that email
            System.out.println("Login credentials provided were incorrect, no such email found.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to login member:\n" + ex);
            return null;
        }
    }

    /*public boolean checkStaffUsernameExists(String username) {
     System.out.println("checkStaffUsernameExists() called with:" + username);
     Query q = em.createQuery("SELECT t FROM StaffEntity t WHERE t.username=:username");
     q.setParameter("username", username);
     try {
     StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
     } catch (NoResultException ex) {
     return false;
     }
     return true;
     }*/
    @Override
    public boolean checkStaffEmailExists(String email) {
        System.out.println("checkStaffEmailExists() called with:" + email);
        Query q = em.createQuery("SELECT t FROM StaffEntity t WHERE t.email=:email");
        q.setParameter("email", email);
        try {
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
        } catch (NoResultException ex) {
            return false;
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public StaffEntity registerStaff(String identificationNo, String name, String phone, String email, String address, String password) {
        System.out.println("registerStaff() called with name:" + name);
        Long staffID;
        String passwordSalt = generatePasswordSalt();
        String passwordHash = generatePasswordHash(passwordSalt, password);
        try {
            StaffEntity staffEntity = new StaffEntity();
            staffEntity.create(identificationNo, name, phone, email, address, passwordSalt, passwordHash);
            staffEntity.setRoles(new ArrayList());
            em.persist(staffEntity);
            em.flush();
            staffID = staffEntity.getId();
            System.out.println("Staff \"" + name + "\" registered successfully as id:" + staffID);

            return staffEntity;
        } catch (Exception ex) {
            System.out.println("\nServer failed to register staff:\n" + ex);
            return null;
        }
    }

    @Override
    public StaffEntity getStaffByEmail(String email) {
        System.out.println("getStaffByEmail() called.");
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t where t.email=:email");
            q.setParameter("email", email);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
            return staffEntity;
        } catch (NoResultException ex) {
            System.out.println("Failed to find staff.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getStaffByEmail:\n" + ex);
            ex.printStackTrace();
            return null;
        }

    }

    //For administrator to edit staff account.
    @Override
    public boolean editStaff(Long staffID, String identificationNo, String name, String phone, String password, String address) {
        System.out.println("editStaff() called with staffID:" + staffID);

        String passwordSalt = generatePasswordSalt();
        String passwordHash = generatePasswordHash(passwordSalt, password);
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t WHERE t.id=:staffID");
            q.setParameter("staffID", staffID);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();

            staffEntity.setIdentificationNo(identificationNo);
            staffEntity.setName(name);
            staffEntity.setAddress(address);
            staffEntity.setPhone(phone);
            if (!password.isEmpty()) {
                staffEntity.setPasswordSalt(passwordSalt);
            }
            if (!password.isEmpty()) {
                staffEntity.setPasswordHash(passwordHash);
            }
            //staffEntity.setEmail(email);
            em.merge(staffEntity);
            System.out.println("\nServer edited staff succeessfully");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to edit staff:\n" + ex);
            return false;
        }
    }

    //For staff to edit their own staff account.
    @Override
    public boolean editStaff(Long staffID, String phone, String password, String address) {
        System.out.println("editStaff() called with staffID:" + staffID);

        String passwordSalt = generatePasswordSalt();
        String passwordHash = generatePasswordHash(passwordSalt, password);
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t WHERE t.id=:staffID");
            q.setParameter("staffID", staffID);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();

            staffEntity.setAddress(address);
            staffEntity.setPhone(phone);
            if (!password.isEmpty()) {
                staffEntity.setPasswordSalt(passwordSalt);
            }
            if (!password.isEmpty()) {
                staffEntity.setPasswordHash(passwordHash);
            }
            em.merge(staffEntity);
            System.out.println("\nServer edited staff succeessfully");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to edit staff:\n" + ex);
            return false;
        }
    }

    @Override
    public boolean removeStaff(Long staffID) {
        System.out.println("removeStaff() called with staffID:" + staffID);

        try {
            em.remove(em.getReference(StaffEntity.class, staffID));
            System.out.println("Staff removed succesfully");
            return true;
        } catch (EntityNotFoundException ex) {
            System.out.println("Failed to remove staff, staff not found.");
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to staff:\n" + ex);
            return false;
        }
    }

    @Override
    public boolean removeMember(Long memberID) {
        System.out.println("removeMember() called with memberID:" + memberID);
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t where t.id=:id");
            q.setParameter("id", memberID);
            MemberEntity memberEntity = (MemberEntity) q.getSingleResult();
            em.remove(memberEntity);
            System.out.println("\nServer removed memberID:\n" + memberID);
            return true;
        } catch (NoResultException ex) {
            System.out.println("Member not found.");
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to member:\n" + ex);
            return false;
        }
    }

    @Override
    public StaffEntity loginStaff(String email, String password) {
        System.out.println("loginStaff() called with email:" + email);
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t where t.email=:email");
            q.setParameter("email", email);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();

            System.out.println("Staff activation status" + staffEntity.getAccountActivationStatus());
            if (staffEntity.getAccountActivationStatus() == false || staffEntity.getAccountLockStatus() == true) {
                System.out.println("Account not yet activated or locked.");
                return null;
            }
            String passwordSalt = staffEntity.getPasswordSalt();
            String passwordHash = generatePasswordHash(passwordSalt, password);
            if (passwordHash.equals(staffEntity.getPasswordHash())) {
                System.out.println("Staff with email:" + email + " logged in successfully.");
                staffEntity.setInvalidLoginAttempt(0);
                return staffEntity;
            } else {
                System.out.println("Login credentials provided were incorrect, password wrong.");
                Integer numOfInvalidAttempts = staffEntity.getInvalidLoginAttempt();
                System.out.println("numOfInvalidAttemmpts : " + numOfInvalidAttempts);
                if (numOfInvalidAttempts == 9) {
                    staffEntity.setAccountLockStatus(true);
                    systemSecurityBean.sendPasswordResetEmailForStaff(email);
                    System.out.println("Account locked and email sent");
                } else {
                    staffEntity.setInvalidLoginAttempt(numOfInvalidAttempts + 1);
                }
                return null;
            }
        } catch (NoResultException ex) {//cannot find staff with that email
            System.out.println("Login credentials provided were incorrect, no such email found.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to login staff:\n" + ex);
            return null;
        }
    }

    @Override
    public List<StaffEntity> listAllStaff() {
        System.out.println("listAllStaff() called.");
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t");
            List<StaffEntity> staffEntities = q.getResultList();
            return staffEntities;
        } catch (Exception ex) {
            System.out.println("\nServer failed to list all staff:\n" + ex);
            return null;
        }
    }

    @Override
    public RoleEntity createRole(String name, String accessLevel) {
        System.out.println("createRole() called with name: " + name);
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.create(name, accessLevel);
        em.persist(roleEntity);
        System.out.println("Role created.");
        return roleEntity;
    }

    @Override
    public boolean updateRole(Long roleID, String accessLevel) {
        System.out.println("updateRole() called with roleID:" + roleID);
        RoleEntity roleEntity;
        try {
            Query q = em.createQuery("SELECT t FROM RoleEntity t where t.id=:id");
            q.setParameter("id", roleID);
            roleEntity = (RoleEntity) q.getSingleResult();
            //roleEntity.setName(name);
            roleEntity.setAccessLevel(accessLevel);
            em.merge(roleEntity);
            System.out.println("Roles updated successfully.");
        } catch (NoResultException ex) {
            System.out.println("No roles found to be updated.");
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer error while updating a role.\n" + ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteRole(Long roleID) {
        System.out.println("deleteRole() called with roleID:" + roleID);
        RoleEntity roleEntity;
        try {
            Query q = em.createQuery("SELECT t FROM RoleEntity t where t.id=:id");
            q.setParameter("id", roleID);
            roleEntity = (RoleEntity) q.getSingleResult();
            em.remove(roleEntity);
        } catch (NoResultException ex) {
            System.out.println("No roles found to be deleted.");
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer error while deleting a role.\n" + ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean checkIfRoleExists(String name) {
        System.out.println("checkRoleExists() called with:" + name);
        Query q = em.createQuery("SELECT t FROM RoleEntity t WHERE t.name=:name");
        q.setParameter("name", name);
        try {
            RoleEntity roleEntity = (RoleEntity) q.getSingleResult();
        } catch (NoResultException ex) {
            return false;
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean roleHasMembersAssigned(Long roleID) {
        System.out.println("roleHasMembersAssigned() called with roleID:" + roleID);
        RoleEntity roleEntity;
        try {
            Query q = em.createQuery("SELECT t FROM RoleEntity t where t.id=:id");
            roleEntity = (RoleEntity) q.getSingleResult();
            if (roleEntity.getStaffs() == null || roleEntity.getStaffs().isEmpty()) {
                System.out.println("Role is unused.");
                return false;
            } else {
                System.out.println("Role still contain members.");
                return true;
            }
        } catch (NoResultException ex) {
            System.out.println("No such roles found.");
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer error while checking if role has members.\n" + ex);
            return false;
        }
    }

    @Override
    public List<RoleEntity> listAllRoles() {
        System.out.println("listAllRoles() called.");
        List<RoleEntity> roleEntities = new ArrayList();
        int result = 0;
        try {
            Query q = em.createQuery("SELECT t FROM RoleEntity t");
            roleEntities = q.getResultList();
            for (RoleEntity roleEntity : roleEntities) {
                em.refresh(roleEntity);
                result++;
            }                                                            
            System.out.println("Returned " + result + " roles.");
            return roleEntities;
        } catch (NoResultException ex) {
            System.out.println("No roles found to be returned.");
            roleEntities.clear();
            return roleEntities;
        } catch (Exception ex) {
            System.out.println("\nServer error while listing all roles.\n" + ex);
            return null;
        }
    }

    @Override
    public List<RoleEntity> listRolesHeldByStaff(Long staffID) {
        System.out.println("listRolesHeldByStaff() called.");
        List<RoleEntity> roleEntities = new ArrayList();
        int result = 0;
        try {
            StaffEntity staffEntity = em.getReference(StaffEntity.class, staffID);
            roleEntities = staffEntity.getRoles();
            for (RoleEntity roleEntity : roleEntities) {
                result++;
            }
            System.out.println("Returned " + result + " roles.");
            return roleEntities;
        } catch (NoResultException ex) {
            System.out.println("No roles found to be returned.");
            roleEntities.clear();
            return roleEntities;
        } catch (Exception ex) {
            System.out.println("\nServer error while listing all roles.\n" + ex);
            return null;
        }
    }

    @Override
    public RoleEntity searchRole(String name, String accessLevel) {
        System.out.println("searchRole() called with name:" + name);
        try {
            Query q = em.createQuery("SELECT t FROM RoleEntity t where t.name=:name AND t.accessLevel=:accessLevel");
            q.setParameter("name", name);
            q.setParameter("accessLevel", name);
            RoleEntity roleEntity = (RoleEntity) q.getSingleResult();
            System.out.println("Role:" + name + " ,Access level:" + accessLevel + " found.");
            return roleEntity;
        } catch (NoResultException ex) {
            System.out.println("Role:" + name + " ,Access level:" + accessLevel + " not found.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to search for role:\n" + ex);
            return null;
        }
    }

    @Override
    public boolean checkIfStaffHasRole(Long staffID, Long roleID) {
        System.out.println("checkIfStaffHasRole() called with staffID:" + staffID + ", roleID:" + roleID);
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t where t.id=:staffID");
            q.setParameter("staffID", staffID);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
            Collection<RoleEntity> roles = staffEntity.getRoles();
            for (RoleEntity currentRole : roles) {
                if (currentRole.getId().equals(staffID)) {
                    System.out.println("Staff has the role.");
                    return true;
                }
            }
            System.out.println("Staff do not have the role.");
            return false;
        } catch (NoResultException ex) {
            System.out.println("Staff not found.");
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to check if staff has a role:\n" + ex);
            return false;
        }
    }

    @Override
    public boolean addStaffRole(Long staffID, Long roleID) {
        System.out.println("addStaffRole() called with staffID:" + staffID + ", roleID:" + roleID);
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t where t.id=:id");
            q.setParameter("id", staffID);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
            q = em.createQuery("SELECT t FROM RoleEntity where t.id=:id");
            q.setParameter("id", roleID);
            RoleEntity roleEntity = (RoleEntity) q.getSingleResult();
            List<RoleEntity> roles = staffEntity.getRoles();
            roles.add(roleEntity);
            List<StaffEntity> staffs = roleEntity.getStaffs();
            staffs.add(staffEntity);
            em.merge(roleEntity);
            staffEntity.setRoles(roles);
            em.merge(staffEntity);
            System.out.println("Role:" + roleEntity.getName()
                    + " .Access level:" + roleEntity.getAccessLevel() + " added successfully to staff id:" + staffID);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove role for staff:\n" + ex);
            return false;
        }
    }

    @Override
    public boolean removeStaffRole(Long staffID, Long roleID) {
        System.out.println("removeStaffRole() called with staffID:" + staffID);
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t where t.id=:id");
            q.setParameter("id", staffID);
            StaffEntity staffEntity = (StaffEntity) q.getSingleResult();
            q = em.createQuery("SELECT t FROM RoleEntity where t.id=:id");
            q.setParameter("id", roleID);
            // Remove from roles side
            RoleEntity roleEntity = (RoleEntity) q.getSingleResult();
            List<StaffEntity> staffs = roleEntity.getStaffs();
            for (StaffEntity currentStaff : staffs) {
                if (currentStaff == staffEntity) {
                    staffs.remove(currentStaff);
                    roleEntity.setStaffs(staffs);
                    em.merge(roleEntity);
                    break;
                }
            }
            //Remove from staff side
            List<RoleEntity> roles = staffEntity.getRoles();
            for (RoleEntity currentRole : roles) {
                if (currentRole == roleEntity) {
                    roles.remove(currentRole);
                    staffEntity.setRoles(roles);
                    em.merge(staffEntity);
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
    private boolean assignStaffRoles(Long staffID, List<RoleEntity> roles) {
        System.out.println("assignStaffRoles() called with staffID:" + staffID);
        StaffEntity staffEntity = new StaffEntity();
        Query q = em.createQuery("SELECT t FROM StaffEntity t where t.id=:id");
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

    @Override
    public boolean editStaffRole(Long staffID, List<Long> roleIDs) {
        System.out.println("editStaffRole() called with staffID:" + staffID);
        try {

            StaffEntity staffEntity = em.find(StaffEntity.class, staffID);
            staffEntity.setRoles(new ArrayList());//blank their roles
            List<RoleEntity> roles = new ArrayList<RoleEntity>();
            for (int i = 0; i < roleIDs.size(); i++) {
                roles.add(em.getReference(RoleEntity.class, roleIDs.get(i)));

            }
            staffEntity.setRoles(roles);
            em.merge(staffEntity);            
            em.flush();
            System.out.println("Roles successfully updated for staff id:" + staffID);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to update roles for staff:\n" + ex);
            return false;
        }
    }

    @Override
    public CountryEntity getCountry(String countryName) {
        System.out.println("getCountry() called with:" + countryName);
        Query q = em.createQuery("SELECT t FROM CountryEntity t WHERE t.name=:name");
        q.setParameter("name", countryName);
        try {
            CountryEntity countryEntity = (CountryEntity) q.getSingleResult();
            System.out.println("Match found with countryID:" + countryEntity.getId());
            return countryEntity;
        } catch (NoResultException ex) {
            System.out.println("No matching country found.");
            return null;
        }
    }

    // Generate a random salt for use in hashing the password;
    public String generatePasswordSalt() {
        byte[] salt = new byte[16];
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.nextBytes(salt);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("\nServer failed to generate password salt.\n" + ex);
        }
        return Arrays.toString(salt);
    }

    public boolean resetStaffPassword(String email, String password) {
        System.out.println("resetStaffPassword() called with:" + email);
        String passwordSalt = generatePasswordSalt();
        String passwordHash = generatePasswordHash(passwordSalt, password);
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t");
            for (Object o : q.getResultList()) {
                StaffEntity i = (StaffEntity) o;
                if (i.getEmail().equalsIgnoreCase(email)) {
                    i.setPasswordHash(passwordHash);
                    i.setPasswordSalt(passwordSalt);
                    i.setAccountLockStatus(false);
                    em.merge(i);
                    System.out.println("Staff \"" + email + "\" changed password successful as id:");
                    return true;
                }
            }
            System.out.println("Staff \"" + email + "\" failed to change password as id:");
            return false;
        } catch (Exception ex) {
            System.out.println("Staff \"" + email + "\" failed to change password as id:");
            ex.printStackTrace();
            return false;
        }
    }

    // Uses supplied salt and password to generate a hashed password
    public String generatePasswordHash(String salt, String password) {
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
