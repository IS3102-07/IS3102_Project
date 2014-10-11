package OperationalCRM.CustomerInformationManagement;

import javax.ejb.Stateless;
import EntityManager.ItemEntity;
import EntityManager.SubscriptionEntity;
import EntityManager.MemberEntity;
import EntityManager.ShoppingListEntity;
import EntityManager.StaffEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import EntityManager.SubscriptionEntity;
import javax.persistence.Query;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.NoResultException;

@Stateless
public class CustomerInformationManagementBean implements CustomerInformationManagementBeanLocal {

    @PersistenceContext
    private EntityManager em;

    String emailServerName = "mailauth.comp.nus.edu.sg";
    String emailFromAddress = "a0097735@comp.nus.edu.sg";
    String toEmailAddress = "a0097735@comp.nus.edu.sg";
    String mailer = "JavaMailer";

    public Boolean addFurnitureToList(String sku, Long memberId) {
        System.out.println("addFurnitureToList() sku is " + sku + " memberId is " + memberId);

        MemberEntity member = em.find(MemberEntity.class, memberId);

        Query q = em.createQuery("SELECT t FROM ItemEntity t where t.SKU=:sku");
        q.setParameter("sku", sku);
        ItemEntity item = (ItemEntity) q.getSingleResult();

        System.out.println("addFurnitureToList(): item found SKU is " + item.getSKU());
        ShoppingListEntity shoppingList = member.getShoppingList();

        Boolean itemExistInShoppingList = false;
        for (int i = 0; i < shoppingList.getItems().size(); i++) {
            if (shoppingList.getItems().get(i) == item) {
                itemExistInShoppingList = true;
            }
        }

        if (itemExistInShoppingList == true) {
            return false;
        } else {
            shoppingList.addItems(item);
            em.merge(member);
            return true;
        }
    }

    public Boolean removeFurnitureToList(String sku, Long memberId) {
        System.out.println("removeFurnitureToList() sku is " + sku + " memberId is " + memberId);

        MemberEntity member = em.find(MemberEntity.class, memberId);

        Query q = em.createQuery("SELECT t FROM ItemEntity t where t.SKU=:sku");
        q.setParameter("sku", sku);
        ItemEntity item = (ItemEntity) q.getSingleResult();

        System.out.println("addFurnitureToList(): item found SKU is " + item.getSKU());
        ShoppingListEntity shoppingList = member.getShoppingList();

        for (int i = 0; i < shoppingList.getItems().size(); i++) {
            if (shoppingList.getItems().get(i) == item) {
                shoppingList.getItems().remove(i);
            }
        }
        em.merge(member);
        return true;
    }

    @Override
    public Boolean addEmailToSubscription(String email) {
        System.out.println("addEmailToSubscription()");
        SubscriptionEntity subscription = new SubscriptionEntity();
        subscription.addToList(email);
        em.persist(subscription);
        return true;
    }

    public ShoppingListEntity shoppingList(String email) {
        System.out.println("shoppingList() called.");
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t where t.email=:email");
            q.setParameter("email", email);
            MemberEntity member = (MemberEntity) q.getSingleResult();

            return member.getShoppingList();
        } catch (Exception ex) {
            System.out.println("\nServer failed to list shopping list:\n" + ex);
            return null;
        }
    }

    public Boolean sendMonthlyNewsletter() {
        System.out.println("sendActivationEmailForStaff():");
        try {
            Query q = em.createQuery("SELECT t FROM SubscriptionEntity t");
            for (Object o : q.getResultList()) {
                SubscriptionEntity subscriber = (SubscriptionEntity) o;
                try {
                    Properties props = new Properties();
                    props.put("mail.transport.protocol", "smtp");
                    props.put("mail.smtp.host", emailServerName);
                    props.put("mail.smtp.port", "25");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.debug", "true");
                    javax.mail.Authenticator auth = new CommonInfrastructure.SystemSecurity.SMTPAuthenticator();
                    Session session = Session.getInstance(props, auth);
                    session.setDebug(true);
                    Message msg = new MimeMessage(session);
                    if (msg != null) {
                        msg.setFrom(InternetAddress.parse(emailFromAddress, false)[0]);
                        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(subscriber.getEmail(), false));
                        msg.setSubject("Island Furniture Staff Account Activation");
                        String messageText = "Greetings from Island Furniture... \n\n"
                                + "Here is your monthly enewsletter :\n\n"                                
                                + "Promotion for this week is as follow"
                                + "Click here to unsubscribe: http://localhost:8080/IS3102_Project-war/ECommerce_UnsubscribeServlet?email=" + subscriber.getEmail();
                        msg.setText(messageText);
                        msg.setHeader("X-Mailer", mailer);
                        Date timeStamp = new Date();
                        msg.setSentDate(timeStamp);
                        Transport.send(msg);
                    }
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to send monthly code :\n" + ex);
            return false;
        }
        return true;
    }
    
    public Boolean removeFromSubscription(String email) {
        Query q = em.createQuery("SELECT t FROM SubscriptionEntity t");
            for (Object o : q.getResultList()) {
                SubscriptionEntity subscriber = (SubscriptionEntity) o;
                if (subscriber.getEmail().equalsIgnoreCase(email)) {
                    em.remove(subscriber);
                    em.flush();
                    return true;
                }
            }
        return false;
    }
}
