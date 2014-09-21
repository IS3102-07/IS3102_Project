
package MRP.SalesForecast;

import EntityManager.ItemEntity;
import EntityManager.SalesFigureEntity;
import EntityManager.StoreEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface SalesForecastBeanLocal {
        public List<SalesFigureEntity> getSalesFigureList(StoreEntity store, Date month);
        public SalesFigureEntity getSalesFigure(StoreEntity store, Date month, ItemEntity item);
        public SalesFigureEntity createSalesFigure(Date month, Integer quantity, StoreEntity store, ItemEntity item);
        public SalesFigureEntity getSalesFigure(Long id);
       
}
