package CommonInfrastructure.SystemSecurity;

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

@Stateless
public class SystemSecurityBean implements SystemSecurityBeanLocal {

    String emailServerName = "mailauth.comp.nus.edu.sg";
// Replace with your real name and unix email address
    String emailFromAddress = "a0097735@comp.nus.edu.sg";
// Replace with your real name and NUSNET email address
    String toEmailAddress = "a0097735@comp.nus.edu.sg";
    String mailer = "JavaMailer";

    public SystemSecurityBean() {

    }

    public boolean activateStaffAcccount(String email, String activationCode) {
        return true;
    }

    public void sendEmail() {
        String eventText = "testing 123";
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
                String messageText = "Greetings from Event Booking System... Here are your events:\n\n" + eventText;
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
