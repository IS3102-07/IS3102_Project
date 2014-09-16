/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MRP.SalesAndOperationPlanning;

import EntityManager.SaleAndOperationPlanEntity;
import EntityManager.SaleForcastEntity;
import EntityManager.StoreEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
    public SaleAndOperationPlanEntity createSOP(SaleForcastEntity saleForcast, StoreEntity store, Calendar month, Integer productionPlan, Integer currentInventoryLevel, Integer targetInventoryLevel){
        try{
            SaleAndOperationPlanEntity sop = new SaleAndOperationPlanEntity(saleForcast, store, month, productionPlan, currentInventoryLevel, targetInventoryLevel);
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
    public Boolean editSOP(Integer productionPlan, Integer currentInventoryLevel, Integer targetInventoryLevel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SaleAndOperationPlanEntity getSOPbyId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SaleAndOperationPlanEntity> getSOPlistByYear(Calendar year) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.        
    }
    
    
}
