package SCM.RetailProductsAndRawMaterialsPurchasing;

import EntityManager.ItemEntity;
import EntityManager.LineItemEntity;
import EntityManager.PurchaseOrderEntity;
import EntityManager.SupplierEntity;
import EntityManager.WarehouseEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class RetailProductsAndRawMaterialsPurchasingBean implements RetailProductsAndRawMaterialsPurchasingBeanLocal {

    @PersistenceContext
    private EntityManager em;

    public RetailProductsAndRawMaterialsPurchasingBean() {
    }

    @Override
    public Boolean createPurchaseOrder(Long supplierID, Long receivingWarehouseID, Date expectedReceivedDate) {
        System.out.println("createPurchaseOrder() called");
        try {
            SupplierEntity supplierEntity = em.getReference(SupplierEntity.class, supplierID);
            WarehouseEntity warehouseEntity = em.getReference(WarehouseEntity.class, receivingWarehouseID);
            PurchaseOrderEntity purchaseOrder = new PurchaseOrderEntity(supplierEntity, warehouseEntity, expectedReceivedDate);
            em.persist(purchaseOrder);
            System.out.println("PurchaseOrder with id: " + purchaseOrder.getId() + " is created successfully");
            return true;
        } catch (EntityExistsException ex) {
            ex.printStackTrace();
            System.out.println("Failed to create purchase order.");
            return false;
        }
    }

    @Override
    public Boolean updatePurchaseOrder(Long purchaseOrderID, Long supplierID, Long receivingWarehouseID, Date expectedReceivedDate) {
        System.out.println("updatePurchaseOrder() called");
        try {
            PurchaseOrderEntity purchaseOrderEntity = em.getReference(PurchaseOrderEntity.class, purchaseOrderID);
            SupplierEntity supplierEntity = em.getReference(SupplierEntity.class, supplierID);
            WarehouseEntity warehouseEntity = em.getReference(WarehouseEntity.class, receivingWarehouseID);
            purchaseOrderEntity.setSupplier(supplierEntity);
            purchaseOrderEntity.setReceivedWarehouse(warehouseEntity);
            purchaseOrderEntity.setExpectedReceivedDate(expectedReceivedDate);
            System.out.println("PurchaseOrder updated successfully");
            return true;
        } catch (EntityExistsException ex) {
            ex.printStackTrace();
            System.out.println("Failed to create purchase order.");
            return false;
        }
    }
    @Override
    public Boolean addLineItemToPurchaseOrder(Long purchaseOrderID, Long SKU, Integer qty) {
        try {
            Query query = em.createQuery("select p from PurchaseOrderEntity p where p.id = ?1").setParameter(1, purchaseOrderID);
            PurchaseOrderEntity purchaseOrder = (PurchaseOrderEntity) query.getSingleResult();
            query = em.createQuery("select p from ItemEntity p where p.SKU = ?1").setParameter(1, SKU);
            ItemEntity itemEntity = (ItemEntity) query.getSingleResult();
            LineItemEntity lineItem = new LineItemEntity(itemEntity, qty, null);
            purchaseOrder.getLineItems().add(lineItem);
            em.merge(purchaseOrder);
            em.flush();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public PurchaseOrderEntity getPurchaseOrderById(Long id) {
        try {
            return em.find(PurchaseOrderEntity.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean updatePurchaseOrder(Long id, String status) {
        try {
            PurchaseOrderEntity purchaseOrder = em.find(PurchaseOrderEntity.class, id);
            purchaseOrder.setStatus(status);
            em.persist(purchaseOrder);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<PurchaseOrderEntity> getPurchaseOrderListByStatus(String status) {
        try {
            Query q = em.createQuery("select p from PurchaseOrderEntity p where p.status = ?1").setParameter(1, status);
            return q.getResultList();
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<PurchaseOrderEntity> getPurchaseOrderList() {
        System.out.println("getPurchaseOrderList() called");
        try {
            Query q = em.createQuery("select p from PurchaseOrderEntity p");
            System.out.println("List returned.");
            return q.getResultList();
        } catch (Exception ex) {
            System.out.println("Failed to return list.");
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

}
