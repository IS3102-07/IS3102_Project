/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SCM.InboundAndOutboundLogistics;

import EntityManager.ItemEntity;
import EntityManager.LineItemEntity;
import EntityManager.ShippingOrderEntity;
import EntityManager.WarehouseEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

@Stateless
public class InboundAndOutboundLogisticsBean implements InboundAndOutboundLogisticsBeanLocal {
    
    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;        

    
    
    @Override
    public ShippingOrderEntity createShippingOrderBasicInfo(String ShippingType, Date shippedDate, Date expectedReceivedDate, WarehouseEntity origin, WarehouseEntity destination) {
        ShippingOrderEntity shippingOrder = new ShippingOrderEntity(ShippingType, shippedDate, expectedReceivedDate, origin, destination);
        try{
            em.persist(shippingOrder);
            System.out.println("ShippingOrder with id: " + shippingOrder.getId() + " is created successfully");
            return shippingOrder;
        }
        catch(EntityExistsException ex){
            ex.printStackTrace();
            return null;
        }        
    }   

    @Override
    public Boolean addLineItemToShippingOrder(Long id, LineItemEntity lineItem) {
        try{
            Query query = em.createQuery("select s from ShippingOrderEntity s where s.id = ?1").setParameter(1, id);
            ShippingOrderEntity shippingOrder = (ShippingOrderEntity) query.getSingleResult();
            lineItem.setShippingOrder(shippingOrder);
            shippingOrder.getLineItems().add(lineItem);
            em.merge(shippingOrder);
            em.flush();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    @Override
    public ShippingOrderEntity getShippingOrderById(Long id) {
        try{
            return em.find(ShippingOrderEntity.class, id);
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }        
    }

    @Override
    public Boolean updateShippingOrder(Long id, String status) {
        try{
            ShippingOrderEntity shippingOrder = em.find(ShippingOrderEntity.class, id);
            shippingOrder.setStatus(status);     
            em.persist(shippingOrder);
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }                
    }    

    @Override
    public List<ShippingOrderEntity> getShippingOrderList(WarehouseEntity origin, Date shippedDate) {
        try{
            Query query = em.createQuery("select s from ShippingOrderEntity s where s.origin = ?1 and s.shippedDate = ?2")
                    .setParameter(1, origin).setParameter(2, shippedDate, TemporalType.DATE);
                         
            return query.getResultList();
        }
        catch(Exception ex){
            ex.printStackTrace();
            return new ArrayList<ShippingOrderEntity>();
        }
    }

    @Override
    public List<ShippingOrderEntity> getShippingOrderList(Date expectedReceivedDate, WarehouseEntity destination) {
        try{
            Query query = em.createQuery("select s from ShippingOrderEntity s where s.destination = ?1 and s.expectedReceivedDate = ?2")
                    .setParameter(1, destination).setParameter(2, expectedReceivedDate, TemporalType.DATE);
                         
            return query.getResultList();
        }
        catch(Exception ex){
            ex.printStackTrace();
            return new ArrayList<ShippingOrderEntity>();
        }
    }
    
    @Override
    public List<ShippingOrderEntity> getShippingOrderList(ItemEntity item, Date shippedDate, Date expectedReceivedDate, WarehouseEntity origin, WarehouseEntity destination) {
        try{                        
            // to do
            Query query = em.createQuery("from s in ShippingOrderEntity s where s.shippedDate.getDay() = :shippedDate").setParameter("shippedDate", shippedDate.getDay());
            
            
            return null;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return new ArrayList<ShippingOrderEntity>();
        }
    }

    @Override
    @Remove
    public void remove() {
        System.out.println("Inbound and outbound logistics bean has bean removed.");
    }

    
}
