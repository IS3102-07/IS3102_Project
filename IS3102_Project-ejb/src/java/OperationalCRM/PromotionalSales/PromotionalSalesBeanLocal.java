package OperationalCRM.PromotionalSales;

import EntityManager.CountryEntity;
import EntityManager.ItemEntity;
import EntityManager.PromotionEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PromotionalSalesBeanLocal {

    public List<PromotionEntity> getAllPromotions();

    public CountryEntity getCountryByCountryId(Long id);

    public Boolean createPromotion(ItemEntity item, CountryEntity country, Double discountRate, Date startDate, Date endDate, String imageURL, String description);

    public Boolean deletePromotion(Long id);
    
    public Boolean checkIfPromotionCreated(String sku, Long countryId, Date date);

    public Boolean editPromotion(Long id,ItemEntity item, CountryEntity country, Double discountRate, Date startDate, Date endDate, String imageURL, String description);

    public Boolean checkIfPromotionCreated(Long id, String sku, Long countryId, Date date);

}
