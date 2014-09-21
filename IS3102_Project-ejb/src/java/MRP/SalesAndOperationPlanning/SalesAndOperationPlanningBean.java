/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MRP.SalesAndOperationPlanning;

import EntityManager.MonthScheduleEntity;
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
    
    @Override
    public SaleAndOperationPlanEntity createSOP(SaleForcastEntity saleForcast, StoreEntity store, Calendar schedule, Integer productionPlan, Integer currentInventoryLevel, Integer targetInventoryLevel){
        try{
            MonthScheduleEntity scheduleEntity = new MonthScheduleEntity(schedule);
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
    public List<SaleAndOperationPlanEntity> getSOPlistByYear(Calendar year) {
        try{
            Query q = em.createQuery("select sop from SaleAndOperationPlanEntity sop where sop.year = ?1").setParameter(1, year.get(Calendar.YEAR));
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
    
    
}
