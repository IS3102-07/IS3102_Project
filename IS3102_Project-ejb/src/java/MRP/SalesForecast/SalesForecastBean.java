package MRP.SalesForecast;

import EntityManager.LineItemEntity;
import EntityManager.MonthScheduleEntity;
import EntityManager.ProductGroupEntity;
import EntityManager.SaleForecastEntity;
import EntityManager.SalesFigureEntity;
import EntityManager.SalesRecordEntity;
import EntityManager.StoreEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SalesForecastBean implements SalesForecastBeanLocal {

    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;

    // unsupport now
    public Boolean updateSalesFigureBySalesRecord(Long salesRecordId) {
        try {
            SalesRecordEntity saleRecord = em.find(SalesRecordEntity.class, salesRecordId);
            for (LineItemEntity lineItem : saleRecord.getItemsPurchased()) {
                if (lineItem.getItem().getType().equals("Furniture")) {
                    Query q = em.createQuery("select f from");
                    Query q1 = em.createQuery("select s from SalesFigureEntity s where s.store = ?1 and s.schedule.year = ?2 and s.schedule.month =?3 ")
                            .setParameter(1, saleRecord.getStore())
                            .setParameter(2, this)
                            .setParameter(3, this);
                }

                SalesFigureEntity salesFigure = new SalesFigureEntity();
                salesFigure.setStore(saleRecord.getStore());
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public MonthScheduleEntity getTheBeforeOne(MonthScheduleEntity schedule) {
        try {
            if (schedule.getMonth() == 1) {
                Query q = em.createQuery("select s from MonthScheduleEntity s where s.year = ?1 and s.month = ?2")
                        .setParameter(1, schedule.getYear() - 1)
                        .setParameter(2, 12);
                return (MonthScheduleEntity) q.getResultList().get(0);
            } else {
                Query q = em.createQuery("select s from MonthScheduleEntity s where s.year = ?1 and s.month = ?2")
                        .setParameter(1, schedule.getYear())
                        .setParameter(2, schedule.getMonth() - 1);
                return (MonthScheduleEntity) q.getResultList().get(0);
            }
        } catch (Exception ex) {
        }

        return null;
    }

    @Override
    public SaleForecastEntity getSalesForecast(Long storeId, Long productGroupId, Long scheduleId) {
        try {
            Query q = em.createQuery("select sf from SaleForecastEntity sf where sf.productGroup.id = ?1 AND sf.store.id = ?2 AND sf.schedule.id = ?3")
                    .setParameter(1, productGroupId)
                    .setParameter(2, storeId)
                    .setParameter(3, scheduleId);

            System.out.println("debug......" + "getSalesForecast is called.");

            if (q.getResultList().isEmpty()) {

                System.out.println("debug......" + "q.getResultList().isEmpty()");

                // if not exist, then create it
                MonthScheduleEntity schedule = em.find(MonthScheduleEntity.class, scheduleId);
                StoreEntity store = em.find(StoreEntity.class, storeId);
                ProductGroupEntity productGroup = em.find(ProductGroupEntity.class, productGroupId);

                System.out.println("debug......" + "schedule.getId(): " + schedule.getId());
                System.out.println("debug......" + "store.getId()" + store.getId());
                System.out.println("debug......" + "productGroup.getId()" + productGroup.getId());

                try {
                    int amount = 0;
                    for (int i = 0; i < 3; i++) {

                        System.out.println("debug...... i=" + i);

                        MonthScheduleEntity lastSchedule = this.getTheBeforeOne(schedule);

                        System.out.println("debug...... lastSchedule.getId: " + lastSchedule.getId());

                        Query q2 = em.createQuery("select sf from SalesFigureEntity sf where sf.productGroup.id = ?1 AND sf.store.id = ?2 AND sf.schedule.id = ?3")
                                .setParameter(1, productGroupId)
                                .setParameter(2, storeId)
                                .setParameter(3, lastSchedule.getId());

                        if (!q2.getResultList().isEmpty()) {

                            System.out.println("debug......" + "q2.getResultList() is not Empty()");

                            SalesFigureEntity salesFigureEntity = (SalesFigureEntity) q2.getResultList().get(0);
                            amount += salesFigureEntity.getQuantity();
                        }
                    }

                    SaleForecastEntity saleForecast = new SaleForecastEntity(store, productGroup, schedule, amount / 3);
                    System.out.println("debug......" + " saleForecast is returned ");
                    return saleForecast;

                } catch (Exception ex) {
                    ex.printStackTrace();
                    SaleForecastEntity saleForecast = new SaleForecastEntity(store, productGroup, schedule, 0);
                    System.out.println("debug......" + " exception is catched");
                    return saleForecast;
                }

            } else {
                return (SaleForecastEntity) q.getResultList().get(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // as no crm yet, return simulated data
    @Override
    public List<SalesFigureEntity> getYearlySalesFigureList(Long StoreId, Long productGroupId, Integer year) {
        try {
            Query q = em.createQuery("select s from SalesFigureEntity s where s.store.id = ?1 AND s.productGroup.id = ?2 AND s.schedule.year = ?3 ")
                    .setParameter(1, StoreId)
                    .setParameter(2, productGroupId)
                    .setParameter(3, year);
            return q.getResultList();
        } catch (Exception ex) {
        }
        return new ArrayList<>();
    }

}
