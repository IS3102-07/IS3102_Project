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
    public boolean registerMember(String name, String address, Date DOB, String email, Integer phone, String country, String city, Integer zipCode, String password);
    //public boolean editMember(Long memberID, String DOB, String name, String address, String email, Integer phone, String country, String city, Integer zipCode, String password);
    public MemberEntity loginMember(String email, String password);

    public boolean checkStaffEmailExists(String email);
    public StaffEntity registerStaff(String identificationNo, String name, Integer phone, String email, String address, String password);
    //public boolean editStaff(Long staffID, String identificationNo, String name, String email, Integer phone, String password, String address);
    //public boolean editStaff(Long staffID, String email, Integer phone, String password, String address);
    public StaffEntity loginStaff(String email, String password);
    public List<StaffEntity> listAllStaff();
    
    //Creating the types of roles
    public RoleEntity createRole(String name, String accessLevel);
    public boolean deleteRole(Long roleID); //Returns true if role deleted successfully
    public boolean roleHasMembersAssigned(Long roleID);
    
    public List<RoleEntity> listAllRoles();
    public RoleEntity searchRole(String name, String accessLevel);
    public boolean checkIfStaffHasRole(Long staffID, Long roleID);
    //Assign role to staffs. Returns true if operation is successful, false means either member have that role or role does not exist.
    public boolean addStaffRole(Long staffID, Long roleID);
    public boolean removeStaffRole(Long staffID, Long roleID);
    public boolean editStaffRole(Long staffID, List<RoleEntity> roles);
    
    public CountryEntity getCountry(String countryName);
}
