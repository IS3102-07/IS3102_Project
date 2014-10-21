package AnalyticalCRM.SegmentationMarketing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class SegmentationMarketingBean implements SegmentationMarketingBeanLocal {
    @PersistenceContext
    private EntityManager em;
    
    public void sendMonthlyNewsletter() {
        
    }
    
}
