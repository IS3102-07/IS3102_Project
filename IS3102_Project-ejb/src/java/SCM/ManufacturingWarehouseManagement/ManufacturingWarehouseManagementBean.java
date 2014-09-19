package SCM.ManufacturingWarehouseManagement;

import EntityManager.FurnitureEntity;
import EntityManager.ItemEntity;
import EntityManager.LineItemEntity;
import EntityManager.RawMaterialEntity;
import EntityManager.RetailProductEntity;
import EntityManager.StorageBinEntity;
import EntityManager.TransferOrderEntity;
import EntityManager.WarehouseEntity;
import SCM.ManufacturingInventoryControl.ManufacturingInventoryControlBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

@Stateless
public class ManufacturingWarehouseManagementBean implements ManufacturingWarehouseManagementBeanLocal {

    @EJB
    private ManufacturingInventoryControlBeanLocal manufacturingInventoryControlBean;
    @Resource
    private EJBContext context;
    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;
    StorageBinEntity storageBin;
    WarehouseEntity warehouse;
    TransferOrderEntity transferOrder;
    List<LineItemEntity> lineItems;

    @Override
    public boolean createStorageBin(Long warehouseID, String type, Integer _length, Integer width, Integer height) {
        try {
            switch (type.toLowerCase()) {
                case "inbound":
                    StorageBinEntity inbound = getInboundStorageBin(warehouseID);
                    if (inbound != null) {
                        return false;
                    }
                    break;
                case "outbound":
                    StorageBinEntity outbound = getOutboundStorageBin(warehouseID);
                    if (outbound != null) {
                        return false;
                    }
                    break;
            }
            WarehouseEntity warehouseEntity = em.getReference(WarehouseEntity.class, warehouseID);
            storageBin = new StorageBinEntity(warehouseEntity, type, _length, width, height);
            em.persist(storageBin);
            System.out.println("Created storage bin successfully.");
            return true;
        } catch (EntityNotFoundException ex) {
            System.out.println("Failed to createStorageBin, cannot find warehouse.");
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to createStorageBin:\n" + ex);
            return false;
        }
    }

    @Override
    public
            boolean updateStorageBin(Long storageBinId, Integer length, Integer width, Integer height) {
        System.out.println("updateStorageBin() called.");
        System.out.println("length: " + length);
        System.out.println("width: " + width);
        System.out.println("height: " + height);

        try {
            storageBin = em.getReference(StorageBinEntity.class, storageBinId);
            if (storageBin == null || !storageBin.getItems().isEmpty()) {
                System.out.println("Cannot find storage bin or storage bin contains items");
                return false;
            }
            storageBin.setHeight(height);
            storageBin.setLength(length);
            storageBin.setWidth(width);
            storageBin.setFreeVolume(storageBin.getVolume());
            em.merge(storageBin);
            System.out.println("updateStorageBin() updated successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to updateStorageBin:\n" + ex);
            return false;
        }
    }

    @Override
    public
            boolean deleteStorageBin(Long id) {

        try {
            storageBin = em.find(StorageBinEntity.class, id);
            if (storageBin == null || !storageBin.getItems().isEmpty()) {
                System.out.println("Unable to delete. Storage bin either not found or not empty.");
                return false;
            } else {
                em.merge(storageBin);
                em.remove(storageBin);
                return true;
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to deleteStorageBin:\n" + ex);
            return false;
        }
    }

    @Override
    public StorageBinEntity
            viewStorageBin(Long id) {
        try {
            if (em.getReference(StorageBinEntity.class, id) == null) {

                return null;
            }

            return em.getReference(StorageBinEntity.class, id);
        } catch (Exception ex) {
            System.out.println("\nServer failed to viewStorageBin:\n" + ex);
            return null;
        }
    }

    @Override
    public List<StorageBinEntity> viewAllStorageBin(Long warehouseID) {
        try {
            Query q = em.createQuery("Select sb from StorageBinEntity sb where sb.warehouse.id=:id");
            q.setParameter("id", warehouseID);
            List<StorageBinEntity> storageBins = q.getResultList();
            return storageBins;
        } catch (Exception ex) {
            System.out.println("\nServer failed to viewAllStorageBin:\n" + ex);
            return null;
        }
    }

    @Override
    public TransferOrderEntity
            viewTransferOrder(Long id) {
        try {
            if (em.getReference(TransferOrderEntity.class, id) == null) {

                return null;
            }

            return em.getReference(TransferOrderEntity.class, id);
        } catch (Exception ex) {
            System.out.println("\nServer failed to viewTransferOrder:\n" + ex);
            return null;
        }
    }

    @Override
    public
            boolean deleteTransferOrder(Long id) {
        try {
            if (em.getReference(TransferOrderEntity.class, id) == null) {

                return false;
            }
            em
                    .remove(em.getReference(TransferOrderEntity.class, id));

            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to deleteTransferOrder:\n" + ex);
            return false;
        }
    }

    @Override
    public StorageBinEntity getInboundStorageBin(Long warehouseID) {
        try {
            WarehouseEntity warehouseEntity = em.getReference(WarehouseEntity.class, warehouseID);
            Long id = warehouseEntity.getId();
            Query q = em.createQuery("Select sb from StorageBinEntity sb where sb.type='Inbound' AND sb.warehouse.id=:id");
            q.setParameter("id", id);
            storageBin = (StorageBinEntity) q.getSingleResult();
            return storageBin;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getInboundStorageBin:\n" + ex);
            return null;
        }
    }

    @Override
    public StorageBinEntity getOutboundStorageBin(Long warehouseID) {
        try {
            WarehouseEntity warehouseEntity = em.getReference(WarehouseEntity.class, warehouseID);
            Long id = warehouseEntity.getId();
            Query q = em.createQuery("Select sb from StorageBinEntity sb where sb.type='Outbound' AND sb.warehouse.id=:id");
            q.setParameter("id", id);
            storageBin = (StorageBinEntity) q.getSingleResult();
            return storageBin;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getOutboundStorageBin:\n" + ex);
            return null;
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public
            boolean markTransferOrderAsCompleted(Long transferOrderId) {
        try {
            transferOrder = em.getReference(TransferOrderEntity.class, transferOrderId);
            Integer numberOfQuantityToMove = transferOrder.getLineItem().getQuantity();
            for (int i = 0;
                    i < numberOfQuantityToMove;
                    i++) {
                boolean isPass = manufacturingInventoryControlBean.moveSingleItemBetweenStorageBins(transferOrder.getLineItem().getItem().getSKU(), transferOrder.getOrigin(), transferOrder.getTarget());
                if (!isPass) {
                    UserTransaction ut = context.getUserTransaction();
                    ut.rollback();
                    throw new Exception();
                }
            }

            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to markTransferOrderAsCompleted:\n" + ex);
            return false;
        }
    }

    @Override
    public
            boolean cancelTransferOrder(Long transferOrderId) {
        try {
            transferOrder = em.getReference(TransferOrderEntity.class, transferOrderId);
            transferOrder.setStatus(
                    "Cancelled");

            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to cancelTransferOrder:\n" + ex);
            return false;
        }
    }

    @Override
    public List<TransferOrderEntity> viewAllTransferOrderByWarehouseId(Long warehouseId) {
        System.out.println("viewAllTransferOrderByWarehouseId() called.");
        try {
            Query q = em.createQuery("Select t from TransferOrderEntity t where t.warehouse.id=:id");
            q.setParameter("id", warehouseId);
            System.out.println("viewAllTransferOrderByWarehouseId() is successful.");
            return q.getResultList();
        } catch (Exception ex) {
            System.out.println("\nServer failed to viewAllTransferOrderByWarehouseId():\n" + ex);
            return null;
        }
    }

    @Override
    public boolean markTransferOrderAsUnfulfilled(Long transferOrderId) {
        System.out.println("markTransferOrderAsUnfulfilled() called.");
        try {
            transferOrder = em.getReference(TransferOrderEntity.class, transferOrderId);
            transferOrder.setStatus(
                    "Unfulfillable");

            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to markTransferOrderAsUnfulfilled:\n" + ex);
            return false;
        }
    }

    @Override
    public boolean createTransferOrder(Long warehouseID, Long originStorageBinID, Long targetStorageBinID, LineItemEntity lineItem) {
        System.out.println("createTransferOrder() called.");
        try {
            WarehouseEntity warehouseEntity = em.getReference(WarehouseEntity.class, warehouseID);
            StorageBinEntity originStorageBin = em.getReference(StorageBinEntity.class, originStorageBinID);

            StorageBinEntity target = em.getReference(StorageBinEntity.class, targetStorageBinID);
            transferOrder = new TransferOrderEntity(warehouseEntity, lineItem, originStorageBin, target);
            em.persist(transferOrder);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to createStorageBin:\n" + ex);
            return false;
        }
    }

    @Override
    public ItemEntity searchItemBySKU(String SKU) {
        try {
            ItemEntity itemEntity = new FurnitureEntity();
            Query q = em.createQuery("SELECT t FROM FurnitureEntity t where t.SKU=:SKU");
            q.setParameter("SKU", SKU);
            itemEntity = (ItemEntity) q.getSingleResult();
            return itemEntity;
        } catch (EntityNotFoundException ex1) {
            try {
                ItemEntity itemEntity = new RawMaterialEntity();
                Query q = em.createQuery("SELECT t FROM RawMaterialEntity t where t.SKU=:SKU");
                q.setParameter("SKU", SKU);
                itemEntity = (ItemEntity) q.getSingleResult();
                return itemEntity;
            } catch (EntityNotFoundException ex2) {
                try {
                    ItemEntity itemEntity = new RetailProductEntity();
                    Query q = em.createQuery("SELECT t FROM RetailProductEntity t where t.SKU=:SKU");
                    q.setParameter("SKU", SKU);
                    itemEntity = (ItemEntity) q.getSingleResult();
                    return itemEntity;
                } catch (EntityNotFoundException ex3) {
                    return null;
                }
            }
        }
    }

    @Override
    public Boolean addLineItemToTransferOrder(Long transferOrderID, String SKU, Integer quantity) {
        System.out.println("addLineItemToTransferOrder() called.");
        try {
            ItemEntity itemEntity = searchItemBySKU(SKU);
            if (itemEntity == null)
                return false;//cannot find item
            LineItemEntity lineItem = new LineItemEntity(itemEntity, quantity, null);
            TransferOrderEntity transferOrderEntity = em.getReference(TransferOrderEntity.class, transferOrderID);
            transferOrderEntity.setLineItem(lineItem);
            em.persist(transferOrder);
            System.out.println("Item added to transfer order.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to addLineItemToTransferOrder:\n" + ex);
            return false;
        }

    }

    @Override
    public Boolean removeLineItemFromTransferOrder(Long transferOrderID) {
        System.out.println("removeLineItemFromTransferOrder() called.");
        try {
            TransferOrderEntity transferOrderEntity = em.getReference(TransferOrderEntity.class, transferOrderID);
            transferOrderEntity.setLineItem(null);
            em.persist(transferOrder);
            System.out.println("Item remove from transfer order.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to removeLineItemFromTransferOrder:\n" + ex);
            return false;
        }
    }
//TODO NEED TO TEST

    @Override
    public List<TransferOrderEntity> createOutboundTransferOrder(Long warehouseID, List<LineItemEntity> lineItems) {
        int qtyNeededToTransfer = 0;
        int qtyToTransferOutFromBin = 0;
        String SKUtoFind;
        ItemEntity currentItem = null;
        StorageBinEntity outboundStorageBin = getOutboundStorageBin(warehouseID);
        List<TransferOrderEntity> transferOrdersCreated = new ArrayList<>();
        try {
            for (LineItemEntity lineItem1 : lineItems) {
                qtyNeededToTransfer = lineItem1.getQuantity();
                List<StorageBinEntity> binsThatMatchesSKU = manufacturingInventoryControlBean.findStorageBinsThatContainsItem(outboundStorageBin.getWarehouse().getId(), lineItem1.getItem().getSKU());
                for (StorageBinEntity eachBinThatMatchesSKU : binsThatMatchesSKU) {
                    qtyToTransferOutFromBin = 0;
                    SKUtoFind = lineItem1.getItem().getSKU();
                    List<ItemEntity> itemsInEachBinThatMatchesSKU = eachBinThatMatchesSKU.getItems();
                    for (ItemEntity eachItem : itemsInEachBinThatMatchesSKU) {
                        currentItem = eachItem;
                        if (currentItem.getSKU().equals(SKUtoFind)) {
                            qtyNeededToTransfer--;
                            qtyToTransferOutFromBin++;
                        }
                        if (qtyNeededToTransfer <= 0) {
                            break;
                        }
                    }
                    if (qtyNeededToTransfer > 0) {
                        LineItemEntity lineItem = new LineItemEntity(currentItem, qtyToTransferOutFromBin, "");
                        WarehouseEntity warehouseEntity = em.getReference(WarehouseEntity.class, warehouseID);
                        transferOrder = new TransferOrderEntity(warehouseEntity, lineItem, eachBinThatMatchesSKU, outboundStorageBin);

                        em.persist(transferOrder);

                        transferOrdersCreated.add(transferOrder);
                    }
                    if (qtyNeededToTransfer <= 0) {
                        break;
                    }
                }
            }
            return transferOrdersCreated;
        } catch (Exception ex) {
            System.out.println("\nServer failed to createOutboundTransferOrder:\n" + ex);
            return null;
        }

    }

}
