package AnalyticalCRM.SegmentationMarketing;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class SegmentationMarketingBean implements SegmentationMarketingBeanLocal {
    @PersistenceContext
    private EntityManager em;
    
    public void sendMonthlyNewsletter() {
        
    }
}
