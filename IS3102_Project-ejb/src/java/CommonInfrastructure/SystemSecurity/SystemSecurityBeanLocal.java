package CommonInfrastructure.SystemSecurity;

import javax.ejb.Local;


@Local
public interface SystemSecurityBeanLocal {
    public Boolean sendActivationEmailForStaff(String email);
    public Boolean sendActivationEmailForMember(String email);
    
    public Boolean sendPasswordResetEmailForStaff(String email);
    public Boolean sendPasswordResetEmailForMember(String email);
    
    public Boolean validateCodeForStaff(String email, String code);
    public Boolean validateCodeForMember(String email, String code);
    
    public Boolean validatePasswordResetForStaff(String email, String code);
    public Boolean validatePasswordResetForMember(String email, String code);
}
