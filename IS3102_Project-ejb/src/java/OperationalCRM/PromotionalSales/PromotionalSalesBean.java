package OperationalCRM.PromotionalSales;

import EntityManager.PromotionEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PromotionalSalesBean implements PromotionalSalesBeanLocal {
    
    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;
    
    
    @Override
    public List<PromotionEntity> getAllPromotions() {
        System.out.println("getAllPromotions() called");
        try {
            Query q = em.createQuery("SELECT p FROM PromotionEntity p");
            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList();
        }
    }
}
