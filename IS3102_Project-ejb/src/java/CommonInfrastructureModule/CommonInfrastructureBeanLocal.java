package CommonInfrastructureModule;

import em.CountryEntity;
import java.util.Date;
import javax.ejb.Local;

@Local
public interface CommonInfrastructureBeanLocal {
    
    public void registerMember(String name, String address, Date DOB, String email, Integer phone, String country, String city, Integer zipCode, String username, String password);
    //public MemberData loginMember(String username, String password);
    
    //public CountryEntity getCountry(String countryName);
}
