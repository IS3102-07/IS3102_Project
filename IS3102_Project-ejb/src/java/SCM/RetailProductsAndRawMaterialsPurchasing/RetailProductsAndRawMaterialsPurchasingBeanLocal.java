package SCM.RetailProductsAndRawMaterialsPurchasing;

import EntityManager.LineItemEntity;
import EntityManager.PurchaseOrderEntity;
import EntityManager.SupplierEntity;
import EntityManager.WarehouseEntity;
import java.sql.Time;
import java.util.Date;
import javax.ejb.Local;

@Local
public interface RetailProductsAndRawMaterialsPurchasingBeanLocal {
   public Long createPurchaseOrder(Date shippedDate, Date expectedReceivedDate,Time deliveryTime,SupplierEntity origin, WarehouseEntity destination, String contactName, Integer contactNumber, String status);
   public Boolean addLineItemToPurchaseOrder(Long id, LineItemEntity lineItem);
   public Boolean updatePurchaseOrder(Long id, String status);
   public PurchaseOrderEntity getPurchaseOrderById(Long id);

}