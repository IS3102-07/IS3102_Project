package OperationalCRM.PromotionalSales;

import EntityManager.PromotionEntity;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PromotionalSalesBeanLocal {
        public List<PromotionEntity> getAllPromotions();
}
