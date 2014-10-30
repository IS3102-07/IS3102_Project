package MRP.SalesForecast;

import EntityManager.LineItemEntity;
import EntityManager.MonthScheduleEntity;
import EntityManager.ProductGroupEntity;
import EntityManager.SaleForecastEntity;
import EntityManager.SalesFigureEntity;
import EntityManager.SalesFigureLineItemEntity;
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
                System.out.println("saleRecord.getItemsPurchased().size(): " + saleRecord.getItemsPurchased().size());
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

                        Query q2 = em.createQuery("select l from SalesFigureLineItemEntity l where l.saleFigure.id = ?1 and l.SKU = ?2 ")
                                .setParameter(1, saleFigure.getId())
                                .setParameter(2, lineItem.getItem().getSKU());
                        
                        if (!q2.getResultList().isEmpty()) {
                            SalesFigureLineItemEntity salesFigureLineItem = (SalesFigureLineItemEntity)q2.getResultList().get(0);
                            salesFigureLineItem.setQuantity(salesFigureLineItem.getQuantity() + lineItem.getQuantity());  
                            em.merge(salesFigureLineItem);
                        } else {
                            SalesFigureLineItemEntity salesFigureLineItem = new SalesFigureLineItemEntity();
                            salesFigureLineItem.setSaleFigure(saleFigure);;
                            salesFigureLineItem.setSKU(lineItem.getItem().getSKU());
                            salesFigureLineItem.setQuantity(lineItem.getQuantity());
                            em.persist(salesFigureLineItem);
                        }

                    } else {
                        SalesFigureEntity salesFigure = new SalesFigureEntity();
                        salesFigure.setStore(saleRecord.getStore());
                        salesFigure.setProductGroup(productGroup);
                        salesFigure.setSchedule(schedule);
                        salesFigure.setQuantity(lineItem.getQuantity());
                        em.persist(salesFigure);
                        
                        SalesFigureLineItemEntity salesFigureLineItem = new SalesFigureLineItemEntity();
                        salesFigureLineItem.setSaleFigure(salesFigure);;
                        salesFigureLineItem.setSKU(lineItem.getItem().getSKU());
                        salesFigureLineItem.setQuantity(lineItem.getQuantity());
                        em.persist(salesFigureLineItem);
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

            if (!q.getResultList().isEmpty()) {
                em.remove(q.getResultList().get(0));
                em.flush();
            }

            // if not exist, then create it
            MonthScheduleEntity schedule = em.find(MonthScheduleEntity.class, scheduleId);
            StoreEntity store = em.find(StoreEntity.class, storeId);
            ProductGroupEntity productGroup = em.find(ProductGroupEntity.class, productGroupId);

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

    public SaleForecastEntity getSalesForecastLinearRegression(Long storeId, Long productGroupId, Long scheduleId) {
        System.out.println("debug......" + "getSalesForecastLinearRegression is called.");
        try {
            Query q = em.createQuery("select sf from SaleForecastEntity sf where sf.productGroup.id = ?1 AND sf.store.id = ?2 AND sf.schedule.id = ?3")
                    .setParameter(1, productGroupId)
                    .setParameter(2, storeId)
                    .setParameter(3, scheduleId);

            if (!q.getResultList().isEmpty()) {
                em.remove(q.getResultList().get(0));
                em.flush();
            }

            // if not exist, then create it
            MonthScheduleEntity schedule = em.find(MonthScheduleEntity.class, scheduleId);
            StoreEntity store = em.find(StoreEntity.class, storeId);
            ProductGroupEntity productGroup = em.find(ProductGroupEntity.class, productGroupId);

            try {

                List<SalesFigureEntity> salesFigureList = new ArrayList<>();

                MonthScheduleEntity lastSchedule = schedule;
                System.out.println("debug...... lastSchedule.getYear(): " + lastSchedule.getYear());
                System.out.println("debug...... lastSchedule.getMonth(): " + lastSchedule.getMonth());

                Query q1 = em.createQuery("select sf from SalesFigureEntity sf where sf.productGroup.id = ?1 AND sf.store.id = ?2 AND sf.schedule.id = ?3")
                        .setParameter(1, productGroupId)
                        .setParameter(2, storeId)
                        .setParameter(3, lastSchedule.getId());

                if (!q1.getResultList().isEmpty()) {
                    SalesFigureEntity salesFigureEntity = (SalesFigureEntity) q1.getResultList().get(0);
                    salesFigureList.add(salesFigureEntity);
                }

                while (this.getTheBeforeOne(lastSchedule) != null) {
                    lastSchedule = this.getTheBeforeOne(lastSchedule);
                    System.out.println("debug...... lastSchedule.getYear(): " + lastSchedule.getYear());
                    System.out.println("debug...... lastSchedule.getMonth(): " + lastSchedule.getMonth());

                    Query q2 = em.createQuery("select sf from SalesFigureEntity sf where sf.productGroup.id = ?1 AND sf.store.id = ?2 AND sf.schedule.id = ?3")
                            .setParameter(1, productGroupId)
                            .setParameter(2, storeId)
                            .setParameter(3, lastSchedule.getId());

                    if (!q2.getResultList().isEmpty()) {
                        SalesFigureEntity salesFigureEntity = (SalesFigureEntity) q2.getResultList().get(0);
                        salesFigureList.add(salesFigureEntity);
                    }
                }

                SimpleRegression simpleRegression = new SimpleRegression();

                for (int i = 0; i < salesFigureList.size(); i++) {

                    System.out.println("debug...... salesFigureList.get(i).getSchedule().getYear(): " + salesFigureList.get(i).getSchedule().getYear());
                    System.out.println("debug...... salesFigureList.get(i).getSchedule().getMonth(): " + salesFigureList.get(i).getSchedule().getMonth());

                    simpleRegression.addData(salesFigureList.size() - i, salesFigureList.get(i).getQuantity());
                }

                double slope = simpleRegression.getSlope();
                double intercept = simpleRegression.getIntercept();
                double forecastQuantity = slope * (salesFigureList.size() + 1) + intercept;

                SaleForecastEntity saleForecast = new SaleForecastEntity(store, productGroup, schedule, Math.round((float) forecastQuantity));

                return saleForecast;

            } catch (Exception ex) {
                ex.printStackTrace();
                SaleForecastEntity saleForecast = new SaleForecastEntity(store, productGroup, schedule, 0);
                System.out.println("debug......" + " exception is catched");
                return saleForecast;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
