package CommonInfrastructureModule;

import EntityManager.RoleEntity;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Local;

@Local
public interface CommonInfrastructureBeanLocal {

    public boolean checkMemberUsernameExists(String username);
    public boolean checkMemberEmailExists(String email);
    public boolean registerMember(String name, String address, Date DOB, String email, Integer phone, String country, String city, Integer zipCode, String username, String password);
    //public MemberData loginMember(String username, String password);

    public boolean checkStaffUsernameExists(String username);
    public boolean checkStaffEmailExists(String email);
    public boolean registerStaff(String identificationNo, String name, String gender, Integer phone, String email, String address, String username, String password);
    //public boolean assignStaffRoles(Collection<RoleEntity> roles);
    //public StaffData loginStaff(String username, String password);
    
    //public CountryEntity getCountry(String countryName);
}
