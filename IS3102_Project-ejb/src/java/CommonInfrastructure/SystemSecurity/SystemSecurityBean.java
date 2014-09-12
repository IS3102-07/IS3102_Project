package CommonInfrastructure.SystemSecurity;

import EntityManager.StaffEntity;
import javax.ejb.Stateless;
import javax.mail.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
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

    public boolean activateStaffAcccount(String email, String activationCode) {
        return true;
    }

    public void sendEmail(String username) {
        String eventText = "";
        try {
            Query q = em.createQuery("SELECT t FROM RawMaterialEntity t");

            for (Object o : q.getResultList()) {
                StaffEntity i = (StaffEntity) o;
                if (i.getName().equalsIgnoreCase(username)) {
                    
                    eventText += i.getActivationCode();
                    System.out.println("\nServer returns activation code of staff:\n" + eventText);
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to update raw material:\n" + ex);
        }
        System.out.println("Server called sendEmail():");
        
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
            System.out.println("After password authentication");
            session.setDebug(true);
            Message msg = new MimeMessage(session);
            if (msg != null) {
                msg.setFrom(InternetAddress.parse(emailFromAddress, false)[0]);
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress, false));
                msg.setSubject("Your Events");
                String messageText = "Greetings from Island Furniture... \n\nHere is your activation code to be keyed in in order to activate your staff account :\n\n" + eventText;
                msg.setText(messageText);
                msg.setHeader("X-Mailer", mailer);
                Date timeStamp = new Date();
                msg.setSentDate(timeStamp);
                Transport.send(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new EJBException(e.getMessage());
        }
    }
}
