package SCM.RetailProductsAndRawMaterialsPurchasing;

import EntityManager.LineItemEntity;
import EntityManager.PurchaseOrderEntity;
import EntityManager.SupplierEntity;
import EntityManager.WarehouseEntity;
import java.util.Date;
import javax.ejb.Local;

@Local
public interface RetailProductsAndRawMaterialsPurchasingBeanLocal {
   public PurchaseOrderEntity createPurchaseOrder(SupplierEntity supplier, WarehouseEntity receivedWarehouse, Date expectedReceivedDate);
   public Boolean addLineItemToPurchaseOrder(Long id, LineItemEntity lineItem);
   public Boolean updatePurchaseOrder(Long id, String status);
   public PurchaseOrderEntity getPurchaseOrderById(Long id);

}