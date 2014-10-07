package ECommerce;

import EntityManager.FeedbackEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ECommerceBean implements ECommerceBeanLocal {

    @PersistenceContext
    private EntityManager em;
    FeedbackEntity feedback;

    public boolean addFeedback(String subject, String name, String email, String message) {
        System.out.println("addFeedback() called");
        try {
            feedback = new FeedbackEntity(subject, name, email, message);
            em.persist(feedback);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to add new feedback:\n" + ex);
            return false;
        }
    }

}
