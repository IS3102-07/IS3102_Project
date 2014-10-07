package ECommerce;

import javax.ejb.Local;

@Local
public interface ECommerceBeanLocal {
      public boolean addFeedback(String subject, String name, String email, String message);
}
