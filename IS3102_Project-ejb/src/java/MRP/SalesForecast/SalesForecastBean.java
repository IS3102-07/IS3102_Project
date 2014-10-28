package MRP.SalesForecast;

import EntityManager.LineItemEntity;
import EntityManager.MonthScheduleEntity;
import EntityManager.ProductGroupEntity;
import EntityManager.SaleForecastEntity;
import EntityManager.SalesFigureEntity;
import EntityManager.SalesRecordEntity;
import EntityManager.StoreEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.math3.stat.regression.SimpleRegression;

@Stateless
public class SalesForecastBean implements SalesForecastBeanLocal {

    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;

    @Override
    public Boolean updateSalesFigureBySalesRecord(Long salesRecordId) {
        System.out.println("updateSalesFigureBySalesRecord is called");
        try {
            SalesRecordEntity saleRecord = em.find(SalesRecordEntity.class, salesRecordId);

            Calendar cal = Calendar.getInstance();
            cal.setTime(saleRecord.getCreatedDate());
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);

            Query query = em.createQuery("select s from MonthScheduleEntity s where s.year = ?1 and s.month = ?2")
                    .setParameter(1, year)
                    .setParameter(2, month + 1);
            MonthScheduleEntity schedule = (MonthScheduleEntity) query.getResultList().get(0);

            for (LineItemEntity lineItem : saleRecord.getItemsPurchased()) {
                System.out.println("saleRecord.getItemsPurchased().size(): "+ saleRecord.getItemsPurchased().size());
                System.out.println(lineItem.getItem().getSKU() + ": " + lineItem.getQuantity());
                Query q = em.createQuery("select l.productGroup from ProductGroupLineItemEntity l where l.item.SKU = ?1")
                        .setParameter(1, lineItem.getItem().getSKU());
                if (!q.getResultList().isEmpty()) {
                    ProductGroupEntity productGroup = (ProductGroupEntity) q.getResultList().get(0);
                    Query q1 = em.createQuery("select s from SalesFigureEntity s where s.store = ?1 and s.schedule.id = ?2 and s.productGroup.id = ?3")
                            .setParameter(1, saleRecord.getStore())
                            .setParameter(2, schedule.getId())
                            .setParameter(3, productGroup.getId());

                    if (!q1.getResultList().isEmpty()) {
                        SalesFigureEntity saleFigure = (SalesFigureEntity) q1.getResultList().get(0);
                        saleFigure.setQuantity(saleFigure.getQuantity() + lineItem.getQuantity());
                        em.merge(saleFigure);
                    } else {
                        SalesFigureEntity salesFigure = new SalesFigureEntity();
                        salesFigure.setStore(saleRecord.getStore());
                        salesFigure.setProductGroup(productGroup);
                        salesFigure.setSchedule(schedule);
                        salesFigure.setQuantity(lineItem.getQuantity());
                        em.persist(salesFigure);
                    }
                }
            }
            return true;
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

                MonthScheduleEntity lastSchedule = schedule;

                try {
                    int amount = 0;
                    for (int i = 0; i < 3; i++) {

                        System.out.println("debug...... i=" + i);

                        lastSchedule = this.getTheBeforeOne(lastSchedule);

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
