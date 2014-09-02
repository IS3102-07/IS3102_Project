package CommonInfrastructureModule;

import EntityManager.CountryEntity;
import EntityManager.MemberEntity;
import EntityManager.RoleEntity;
import EntityManager.StaffEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CommonInfrastructureBeanLocal {

    public boolean checkMemberUsernameExists(String username);
    public boolean checkMemberEmailExists(String email);
    public boolean registerMember(String name, String address, Date DOB, String email, Integer phone, String country, String city, Integer zipCode, String username, String password);
    public MemberEntity loginMember(String username, String password);

    public boolean checkStaffUsernameExists(String username);
    public boolean checkStaffEmailExists(String email);
    public StaffEntity registerStaff(String identificationNo, String name, Integer phone, String email, String address, String username, String password);
    public StaffEntity loginStaff(String username, String password);
    
    public List<RoleEntity> listAllRoles();
    public RoleEntity searchRole(String name, String accessLevel);
    public boolean addStaffRole(Long staffID, Long roleID);
    public boolean removeStaffRole(Long staffID, Long roleID);
    
    public CountryEntity getCountry(String countryName);
}
