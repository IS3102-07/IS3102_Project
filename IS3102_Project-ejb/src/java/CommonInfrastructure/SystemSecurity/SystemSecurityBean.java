package CommonInfrastructure.SystemSecurity;

import EntityManager.StaffEntity;
import EntityManager.MemberEntity;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.Properties;
import javax.ejb.EJBException;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SystemSecurityBean implements SystemSecurityBeanLocal {

    @PersistenceContext
    private EntityManager em;
    String emailServerName = "mailauth.comp.nus.edu.sg";
    String emailFromAddress = "a0097735@comp.nus.edu.sg";
    String toEmailAddress = "a0097735@comp.nus.edu.sg";
    String mailer = "JavaMailer";

    public SystemSecurityBean() {

    }

    //When staff user account is created, this function should be invoked
    public Boolean sendActivationEmailForStaff(String email) {
        System.out.println("Server called sendActivationEmailForStaff():" + email);
        String activationCode = "";
        StaffEntity staff = null;
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t");

            for (Object o : q.getResultList()) {
                StaffEntity i = (StaffEntity) o;
                if (i.getEmail().equalsIgnoreCase(email)) {
                    activationCode += i.getActivationCode();
                    staff = i;
                    System.out.println("\nServer returns activation code of staff:\n" + activationCode);
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to retrieve activationCode:\n" + ex);
            return false;
        }

        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", emailServerName);
            props.put("mail.smtp.port", "25");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.debug", "true");
            javax.mail.Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            session.setDebug(true);
            Message msg = new MimeMessage(session);
            if (msg != null) {
                msg.setFrom(InternetAddress.parse(emailFromAddress, false)[0]);
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
                msg.setSubject("Island Furniture Staff Account Activation");
                String messageText = "Greetings from Island Furniture... \n\nHere is your activation code to be keyed in in order to activate your staff account :\n\nEmail: "+ email +"\nActivation Code: " + activationCode;
                msg.setText(messageText);
                msg.setHeader("X-Mailer", mailer);
                Date timeStamp = new Date();
                msg.setSentDate(timeStamp);
                Transport.send(msg);
            }
            return true;
        } catch (Exception e) {
            
            e.printStackTrace();
            staff.setAccountActivationStatus(true);
            return false;
        }
    }

    //When member user account is created, this function should be invoked
    public Boolean sendActivationEmailForMember(String email) {
        System.out.println("Server called sendActivationEmailForMember():");
        String activationCode = "";
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");

            for (Object o : q.getResultList()) {
                MemberEntity i = (MemberEntity) o;
                if (i.getEmail().equalsIgnoreCase(email)) {
                    activationCode += i.getActivationCode();
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to retrieve activation code:\n" + ex);
            return false;
        }

        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", emailServerName);
            props.put("mail.smtp.port", "25");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.debug", "true");
            javax.mail.Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            session.setDebug(true);
            Message msg = new MimeMessage(session);
            if (msg != null) {
                msg.setFrom(InternetAddress.parse(emailFromAddress, false)[0]);
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
                msg.setSubject("Island Furniture Staff Account Activation");
                String messageText = "Greetings from Island Furniture... \n\n"
                        + "Here is your activation code to be keyed in in order to activate your member account :\n\n" + activationCode;
                msg.setText(messageText);
                msg.setHeader("X-Mailer", mailer);
                Date timeStamp = new Date();
                msg.setSentDate(timeStamp);
                Transport.send(msg);

            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new EJBException(e.getMessage());
        }
    }

    public Boolean sendPasswordResetEmailForStaff(String email) {
        System.out.println("Server called sendPasswordResetEmailForStaff():");
        String passwordReset = "";
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t");
            for (Object o : q.getResultList()) {
                StaffEntity i = (StaffEntity) o;
                if (i.getEmail().equalsIgnoreCase(email)) {
                    i.setPasswordReset();
                    em.merge(i);
                    passwordReset += i.getPasswordReset();
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to get activation code of staff:\n" + ex);
            return false;
        }

        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", emailServerName);
            props.put("mail.smtp.port", "25");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.debug", "true");
            javax.mail.Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            session.setDebug(true);
            Message msg = new MimeMessage(session);
            if (msg != null) {
                msg.setFrom(InternetAddress.parse(emailFromAddress, false)[0]);
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
                msg.setSubject("Island Furniture Staff Account Password Reset");
                String messageText = "Greetings from Island Furniture... \n\nHere is your reset password to be keyed in in order to reset your staff account password:\n\n" + passwordReset;
                msg.setText(messageText);
                msg.setHeader("X-Mailer", mailer);
                Date timeStamp = new Date();
                msg.setSentDate(timeStamp);
                Transport.send(msg);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new EJBException(e.getMessage());
        }
    }

    
    public Boolean sendPasswordResetEmailForMember(String email) {
        System.out.println("Server called sendPasswordResetEmailForMember():");
        String passwordReset = "";
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");

            for (Object o : q.getResultList()) {
                MemberEntity i = (MemberEntity) o;
                if (i.getEmail().equalsIgnoreCase(email)) {
                    i.setPasswordReset();
                    em.merge(i);
                    System.out.println("\nServer returns password reset code of member:\n" + passwordReset);
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to update raw material:\n" + ex);
        }

        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", emailServerName);
            props.put("mail.smtp.port", "25");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.debug", "true");
            javax.mail.Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            session.setDebug(true);
            Message msg = new MimeMessage(session);
            if (msg != null) {
                msg.setFrom(InternetAddress.parse(emailFromAddress, false)[0]);
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress, false));
                msg.setSubject("Island Furniture Staff Account Password Reset");
                String messageText = "Greetings from Island Furniture... \n\nHere is your reset password to be keyed in in order to activate your member account :\n\n" + passwordReset;
                msg.setText(messageText);
                msg.setHeader("X-Mailer", mailer);
                Date timeStamp = new Date();
                msg.setSentDate(timeStamp);
                Transport.send(msg);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new EJBException(e.getMessage());
        }
    }

    public Boolean activateStaffAccount(String email, String code) {
        System.out.println("Server called validateCodeForStaff():");
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t");

            for (Object o : q.getResultList()) {
                StaffEntity staffEntity = (StaffEntity) o;
                if (staffEntity.getEmail().equalsIgnoreCase(email)) {
                    if (staffEntity.getActivationCode().equals(code)) {
                        System.out.println("\nServer activation code valid for staff:\n" + email);
                        staffEntity.setAccountActivationStatus(true);
                        em.merge(staffEntity);
                        return true;
                    } else {
                        System.out.println("\nServer activation code invalid for staff:\n" + email);
                        return false;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to validate email for staff:\n" + ex);
            return false;
        }
        return false;
    }

    public Boolean activateMemberAccount(String username, String code) {
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");

            for (Object o : q.getResultList()) {
                MemberEntity memberEntity = (MemberEntity) o;
                if (memberEntity.getName().equalsIgnoreCase(username)) {

                    if (memberEntity.getActivationCode().equals(code)) {
                        System.out.println("\nServer activation code valid of member:\n" + username);
                        memberEntity.setAccountActivationStatus(true);
                        em.merge(memberEntity);
                        return true;
                    } else {
                        System.out.println("\nServer activation code invalid of member:\n" + username);
                        return false;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to validate email for member:\n" + ex);
            return false;
        }
        return false;
    }
    
    public Boolean validatePasswordResetForStaff(String email, String code) {
        System.out.println("Server called validatePasswordResetForStaff():");
        try {
            Query q = em.createQuery("SELECT t FROM StaffEntity t");

            for (Object o : q.getResultList()) {
                StaffEntity i = (StaffEntity) o;
                if (i.getEmail().equalsIgnoreCase(email)) {
                    if (i.getPasswordReset().equals(code)) {
                        System.out.println("\nReset Password valid of staff:\n" + email);
                        return true;
                    } else {
                        System.out.println("\nReset Password invalid of staff:\n" + email);
                        return false;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to validate email for member:\n" + ex);
            return false;
        }
        return false;
    }
public Boolean validatePasswordResetForMember(String email, String code) {
        System.out.println("Server called validatePasswordResetForStaff():");
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");

            for (Object o : q.getResultList()) {
                MemberEntity i = (MemberEntity) o;
                if (i.getEmail().equalsIgnoreCase(email)) {
                    if (i.getPasswordReset().equals(code)) {
                        System.out.println("\nServer activation code valid of member:\n" + email);
                        return true;
                    } else {
                        System.out.println("\nServer activation code invalid of member:\n" + email);
                        return false;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to validate email for member:\n" + ex);
            return false;
        }
        return false;
    }
}
