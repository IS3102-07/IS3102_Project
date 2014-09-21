package CommonInfrastructure.AccountManagement;

import EntityManager.CountryEntity;
import EntityManager.MemberEntity;
import EntityManager.RoleEntity;
import EntityManager.StaffEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface AccountManagementBeanLocal {

    public boolean checkMemberEmailExists(String email);

    public boolean registerMember(String name, String address, Date DOB, String email, String phone, CountryEntity country, String city, String zipCode, String password);
    
    public MemberEntity getMemberByEmail(String email);

    public boolean editMember(Long memberID, Date DOB, String name, String address, String email, String phone, CountryEntity country, String city, String zipCode, String password);

    public MemberEntity loginMember(String email, String password);

    public boolean checkStaffEmailExists(String email);

    public StaffEntity registerStaff(String identificationNo, String name, String phone, String email, String address, String password);
    
    public StaffEntity getStaffByEmail(String email);

    public boolean editStaff(Long staffID, String identificationNo, String name, String phone, String password, String address);

    public boolean editStaff(Long staffID, String phone, String password, String address);

    public boolean removeStaff(Long staffID);

    public boolean removeMember(Long memberID);

    public StaffEntity loginStaff(String email, String password);

    public List<StaffEntity> listAllStaff();

    //Creating the types of roles
    public RoleEntity createRole(String name, String accessLevel);

    public boolean updateRole(Long roleID, String accessLevel);

    public boolean deleteRole(Long roleID); //Returns true if role deleted successfully

    public boolean checkIfRoleExists(String name);

    public boolean roleHasMembersAssigned(Long roleID);

    public List<RoleEntity> listAllRoles();
    
    public List<RoleEntity> listRolesHeldByStaff(Long staffID);

    public RoleEntity searchRole(String name, String accessLevel);

    public boolean checkIfStaffHasRole(Long staffID, Long roleID);

    //Assign role to staffs. Returns true if operation is successful, false means either member have that role or role does not exist.
    public boolean addStaffRole(Long staffID, Long roleID);

    public boolean removeStaffRole(Long staffID, Long roleID);

    public boolean editStaffRole(Long staffID, List<Long> roleIDs);

    public CountryEntity getCountry(String countryName);

    public String generatePasswordSalt();

    public String generatePasswordHash(String salt, String password);

}
