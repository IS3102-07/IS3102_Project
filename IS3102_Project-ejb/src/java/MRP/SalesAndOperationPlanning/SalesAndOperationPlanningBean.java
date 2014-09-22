/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MRP.SalesAndOperationPlanning;

import EntityManager.MonthScheduleEntity;
import EntityManager.ProductGroupEntity;
import EntityManager.SaleAndOperationPlanEntity;
import EntityManager.SaleForcastEntity;
import EntityManager.StoreEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Lin Baoyu
 */
@Stateless
public class SalesAndOperationPlanningBean implements SalesAndOperationPlanningBeanLocal {
    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;    
    
    public MonthScheduleEntity createSchedule(Calendar schedule){
        try{
            Integer year = schedule.get(Calendar.YEAR);
            Integer month = schedule.get(Calendar.MONTH);
            Query q = em.createQuery("select s from MonthScheduleEntity s where s.year = ?1 and s.month = ?2")            
                        .setParameter(1, year)
                        .setParameter(2, month);
            if(q.getResultList().isEmpty()){
                MonthScheduleEntity scheduleEntity = new MonthScheduleEntity(schedule);
                em.persist(scheduleEntity);
                return scheduleEntity;
            }
            else 
                q.getResultList().get(0);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public SaleAndOperationPlanEntity createSOP(SaleForcastEntity saleForcast, StoreEntity store, Calendar schedule, Integer productionPlan, Integer currentInventoryLevel, Integer targetInventoryLevel){
        try{
            MonthScheduleEntity scheduleEntity = this.createSchedule(schedule);
            SaleAndOperationPlanEntity sop = new SaleAndOperationPlanEntity(saleForcast, store, productionPlan, currentInventoryLevel, targetInventoryLevel, scheduleEntity);
            em.persist(sop);
            return sop;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public Boolean deleteSOP(Long Id){
        try{
            em.remove(em.find(SaleAndOperationPlanEntity.class, Id));
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<SaleAndOperationPlanEntity> getSOPList() {
        try{
            Query q = em.createQuery("select sop from SaleAndOperationPlanEntity sop");
            return q.getResultList();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Boolean editSOP(Long Id, Integer productionPlan, Integer currentInventoryLevel, Integer targetInventoryLevel) {
        try{
            SaleAndOperationPlanEntity sop = em.find(SaleAndOperationPlanEntity.class, Id);
            sop.setProductionPlan(productionPlan);
            sop.setCurrentInventoryLevel(currentInventoryLevel);
            sop.setTargetInventoryLevel(targetInventoryLevel);
            em.merge(sop);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public SaleAndOperationPlanEntity getSOPbyId(Long id) {
        try{
            return em.find(SaleAndOperationPlanEntity.class, id);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SaleAndOperationPlanEntity> getSOPlistByYear(int year) {
        try{
            Query q = em.createQuery("select sop from SaleAndOperationPlanEntity sop where sop.year = ?1").setParameter(1, year);
            return q.getResultList();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    @Remove
    public void remove() {
        System.out.println("The Sale and Operation Planning Bean has been removed.");
    }

    @Override
    public List<MonthScheduleEntity> getScheduleList() {
        Query q = em.createQuery("select s from MonthScheduleEntity s");
        return q.getResultList();
    }    

    @Override
    public List<ProductGroupEntity> getUnplannedProductGroup(Long storeId, Long scheduleId) {
        try{
            Query q1 = em.createQuery("select sop.productGroup from SaleAndOperationPlanEntity sop where sop.store.id = ?1 and sop.schedule.id = ?2")
                        .setParameter(1, storeId)
                        .setParameter(2, scheduleId);
            List<ProductGroupEntity> plannedProductGroupList = q1.getResultList();                        
            Query q2 = em.createQuery("select p from ProductGroupEntity p where p not member of ?1").setParameter(1, plannedProductGroupList);
            return q2.getResultList();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }
        
    @Override
    public List<SaleAndOperationPlanEntity> getSaleAndOperationPlanList(Long storeId, Long scheduleId) {
        try{
            Query q = em.createQuery("select sop from SaleAndOperationPlanEntity sop where sop.store.id = ?1 and sop.schedule.id = ?2")
                        .setParameter(1, storeId)
                        .setParameter(2, scheduleId);
            return q.getResultList();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ArrayList<>();        
    }
}
