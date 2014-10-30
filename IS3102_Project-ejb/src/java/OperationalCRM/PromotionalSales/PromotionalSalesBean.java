package OperationalCRM.PromotionalSales;

import EntityManager.CountryEntity;
import EntityManager.ItemEntity;
import EntityManager.PromotionEntity;
import java.util.ArrayList;
import java.util.Date;
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
     
    @Override
    public CountryEntity getCountryByCountryId(Long id) {
        System.out.println("getCountryByCountryId() called with id: " + id);
        try {
            Query q = em.createQuery("Select c from CountryEntity c where c.id=:id");
            q.setParameter("id", id);
            CountryEntity country = (CountryEntity) q.getSingleResult();
            System.out.println("getCountryByCountryId() is successful.");
            return country;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getCountryByCountryId():\n" + ex);
            return null;
        }
    }
    
    public Boolean createPromotion(ItemEntity item, CountryEntity country, Double discountRate, Date startDate, Date endDate, String imageURL, String description){
          System.out.println("createPromotion() called");
        try {
            PromotionEntity promotion = new PromotionEntity(item, country, discountRate, startDate, endDate, imageURL, description);
            em.persist(promotion);
            em.flush();
            em.merge(promotion);
            System.out.println("Promotion added successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to add new promotion:\n" + ex);
            return false;
        }         
    }
}
