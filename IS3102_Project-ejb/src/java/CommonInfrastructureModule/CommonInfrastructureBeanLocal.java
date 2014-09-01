package CommonInfrastructureModule;

import HelperClass.MemberData;
import HelperClass.RoleData;
import HelperClass.StaffData;
import EntityManager.RoleEntity;
import HelperClass.CountryData;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CommonInfrastructureBeanLocal {

    public boolean checkMemberUsernameExists(String username);
    public boolean checkMemberEmailExists(String email);
    public boolean registerMember(String name, String address, Date DOB, String email, Integer phone, String country, String city, Integer zipCode, String username, String password);
    public MemberData loginMember(String username, String password);

    public boolean checkStaffUsernameExists(String username);
    public boolean checkStaffEmailExists(String email);
    public StaffData registerStaff(String identificationNo, String name, Integer phone, String email, String address, String username, String password);
    public StaffData loginStaff(String username, String password);
    
    public List<RoleData> listAllRoles();
    public RoleData searchRole(String name, String accessLevel);
    public boolean addStaffRole(Long staffID, Long roleID);
    public boolean removeStaffRole(Long staffID, Long roleID);
    
    public CountryData getCountry(String countryName);
}
