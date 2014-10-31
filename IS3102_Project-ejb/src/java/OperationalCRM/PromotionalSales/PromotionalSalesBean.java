package OperationalCRM.PromotionalSales;

import EntityManager.CountryEntity;
import EntityManager.ItemEntity;
import EntityManager.PromotionEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
    public CountryEntity getCountryByCountryId(Long countryID) {
        System.out.println("getCountryByCountryId() called with id: " + countryID);
        try {
            Query q = em.createQuery("Select c from CountryEntity c where c.id=:id");
            q.setParameter("id", countryID);
            CountryEntity country = (CountryEntity) q.getSingleResult();
            System.out.println("getCountryByCountryId() is successful.");
            return country;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getCountryByCountryId():\n" + ex);
            return null;
        }
    }

    public Boolean createPromotion(ItemEntity item, CountryEntity country, Double discountRate, Date startDate, Date endDate, String imageURL, String description) {
        System.out.println("createPromotion() called");
        try {
            PromotionEntity promotion = new PromotionEntity(item, country, discountRate, startDate, endDate, imageURL, description);
            em.persist(promotion);
            System.out.println("Promotion added successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to add new promotion:\n" + ex);
            return false;
        }
    }

    public Boolean deletePromotion(Long id) {
        System.out.println("deletePromotion() called with id:" + id);
        try {
            PromotionEntity promotion = em.find(PromotionEntity.class, id);
            em.remove(promotion);
            System.out.println("deletePromotion() is successful.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove promotion():\n" + ex);
            return false;
        }
    }

    @Override
    public Boolean checkIfPromotionCreated(String sku, Long countryId, Date date) {
        System.out.println("checkIfPromotionCreated() called");
        try {
            List<PromotionEntity> promotions = getAllPromotions();
            for (int i = 0; i < promotions.size(); i++) {
                if (promotions.get(i).getItem().getSKU().equals(sku) && promotions.get(i).getCountry().getId() == countryId && promotions.get(i).getEndDate().after(date)) {
                    return false;
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to check if promotion created before");
            return true;
        }
        return true;
    }

    public Boolean editPromotion(Long id, ItemEntity item, CountryEntity country, Double discountRate, Date startDate, Date endDate, String imageURL, String description) {
        System.out.println("editPromotion is called with promotion id" + id);
        try {
            PromotionEntity promotion = em.find(PromotionEntity.class, id);
            promotion.setItem(item);
            promotion.setCountry(country);
            promotion.setDiscountRate(discountRate);
            promotion.setStartDate(startDate);
            promotion.setEndDate(endDate);
            promotion.setImageURL(imageURL);
            promotion.setDescription(description);
            em.flush();
            System.out.println("\nServer edited promotion:\n" + id);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to edit promotion");
            return false;
        }
    }

    @Override
    public Boolean checkIfPromotionCreated(Long id, String sku, Long countryId, Date date) {
        System.out.println("checkIfPromotionCreated() called" + id);
        try {
            List<PromotionEntity> promotions = getAllPromotions();
            for (int i = 0; i < promotions.size(); i++) {
                if (!Objects.equals(promotions.get(i).getId(), id) && promotions.get(i).getItem().getSKU().equals(sku) && Objects.equals(promotions.get(i).getCountry().getId(), countryId) && promotions.get(i).getEndDate().after(date)) {
                    System.out.println(promotions.get(i).getId());
                    return false;
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to check if promotion created before");
            return true;
        }
        return true;

    }
}
