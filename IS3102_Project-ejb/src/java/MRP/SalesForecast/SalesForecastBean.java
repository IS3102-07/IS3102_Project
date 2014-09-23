package MRP.SalesForecast;

import EntityManager.ItemEntity;
import EntityManager.SalesFigureEntity;
import EntityManager.StoreEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

@Stateless
public class SalesForecastBean implements SalesForecastBeanLocal {

    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;

    @Override
    public SalesFigureEntity createSalesFigure(Date month, Integer quantity, StoreEntity store, ItemEntity item) {
        SalesFigureEntity SalesFigure = new SalesFigureEntity(month, quantity, store, item);
        try {
            em.persist(SalesFigure);
            System.out.println("SalesFigure with id: " + SalesFigure.getId() + " is created successfully");
            return SalesFigure;
        } catch (EntityExistsException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SalesFigureEntity> getSalesFigureList(StoreEntity store, Date month) {
        try {
            Query query = em.createQuery("select s from SalesFigureEntity s where s.store = ?1 and s.month = ?2").setParameter(1, store).setParameter(2, month, TemporalType.DATE);
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<SalesFigureEntity>();
        }
    }

    @Override
    public List<SalesFigureEntity> getAllSalesFigureList() {
        try {
            Query query = em.createQuery("select s from SalesFigureEntity s");
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<SalesFigureEntity>();
        }
    }

    @Override
    public SalesFigureEntity getSalesFigure(StoreEntity store, Date month, ItemEntity item) {
        try {
            Query query = em.createQuery("select s from SalesFigureEntity s where s.store = ?1 and s.month = ?2 and s.item = ?3").setParameter(1, store).setParameter(2, month, TemporalType.DATE).setParameter(3, item);
            SalesFigureEntity SalesFigure = (SalesFigureEntity) query.getSingleResult();
            return SalesFigure;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public SalesFigureEntity getSalesFigure(Long id) {
        try {
            SalesFigureEntity SalesFigure = (SalesFigureEntity) em.find(SalesFigureEntity.class, id);
            return SalesFigure;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
