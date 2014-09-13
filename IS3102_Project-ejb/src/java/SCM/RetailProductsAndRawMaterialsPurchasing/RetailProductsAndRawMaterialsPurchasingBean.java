package SCM.RetailProductsAndRawMaterialsPurchasing;

import EntityManager.LineItemEntity;
import EntityManager.PurchaseOrderEntity;
import EntityManager.SupplierEntity;
import EntityManager.WarehouseEntity;
import java.sql.Time;
import java.util.Date;
import javax.ejb.Stateful;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateful
public class RetailProductsAndRawMaterialsPurchasingBean implements RetailProductsAndRawMaterialsPurchasingBeanLocal {
@PersistenceContext
private EntityManager em;

public RetailProductsAndRawMaterialsPurchasingBean() {
}
  
@Override
public Long createPurchaseOrder(Date shippedDate, Date expectedReceivedDate,Time deliveryTime,SupplierEntity origin, WarehouseEntity destination, String contactName, Integer contactNumber, String status){
     PurchaseOrderEntity purchaseOrder = new PurchaseOrderEntity(shippedDate,expectedReceivedDate,deliveryTime,origin, destination,contactName,contactNumber,status);
     try {
         em.persist(purchaseOrder);
         System.out.println("PurchaseOrder with id: "+ purchaseOrder.getId() + " is created successfully");
         return purchaseOrder.getId();
     }
     catch(EntityExistsException ex){
         ex.printStackTrace();
         return (long) -1;
     }
}

  @Override
    public Boolean addLineItemToPurchaseOrder(Long id, LineItemEntity lineItem) {
        try{
            Query query = em.createQuery("select p from PurchaseOrderEntity p where p.id = ?1").setParameter(1, id);
            PurchaseOrderEntity purchaseOrder = (PurchaseOrderEntity) query.getSingleResult();
            purchaseOrder.getLineItems().add(lineItem);
            em.merge(purchaseOrder);
            em.flush();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    @Override
    public PurchaseOrderEntity getPurchaseOrderById(Long id) {
        try{
            return em.find(PurchaseOrderEntity.class, id);
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }        
    }
    @Override
    public Boolean updatePurchaseOrder(Long id, String status) {
        try{
            PurchaseOrderEntity purchaseOrder = em.find(PurchaseOrderEntity.class, id);
            purchaseOrder.setStatus(status);     
            em.persist(purchaseOrder);
            return true;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }                
    } 
}

         


