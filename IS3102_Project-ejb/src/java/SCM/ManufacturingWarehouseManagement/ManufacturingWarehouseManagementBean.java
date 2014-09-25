package SCM.ManufacturingWarehouseManagement;

import EntityManager.FurnitureEntity;
import EntityManager.ItemEntity;
import EntityManager.LineItemEntity;
import EntityManager.StorageBinEntity;
import EntityManager.TransferOrderEntity;
import EntityManager.WarehouseEntity;
import SCM.ManufacturingInventoryControl.ManufacturingInventoryControlBeanLocal;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
            System.out.println("Size of storage bin to be updated is " + storageBin.getListOfLineItems().size());
            if (storageBin == null || !storageBin.getListOfLineItems().isEmpty()) {
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
    public boolean deleteStorageBin(Long id) {
        try {
            storageBin = em.find(StorageBinEntity.class, id);
            if (storageBin == null || !storageBin.getListOfLineItems().isEmpty()) {
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
        System.out.println("getInboundStorageBin() called");
        try {
            Query q = em.createQuery("Select sb from StorageBinEntity sb where sb.type='Inbound' AND sb.warehouse.id=:id");
            q.setParameter("id", warehouseID);
            storageBin = (StorageBinEntity) q.getSingleResult();
            return storageBin;
        } catch (NoResultException ex) {
            System.out.println("No inbound storage found.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getInboundStorageBin:\n" + ex);
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public StorageBinEntity getOutboundStorageBin(Long warehouseID) {
        System.out.println("getOutboundStorageBin() called");
        try {
            WarehouseEntity warehouseEntity = em.getReference(WarehouseEntity.class, warehouseID);
            Long id = warehouseEntity.getId();
            Query q = em.createQuery("Select sb from StorageBinEntity sb where sb.type='Outbound' AND sb.warehouse.id=:id");
            q.setParameter("id", id);
            storageBin = (StorageBinEntity) q.getSingleResult();
            return storageBin;
        } catch (NoResultException ex) {
            System.out.println("No outbound storage found.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getOutboundStorageBin:\n" + ex);
            return null;
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean markTransferOrderAsCompleted(Long transferOrderId) {
        System.out.println("markTransferOrderAsCompleted() called");
        try {
            transferOrder = em.getReference(TransferOrderEntity.class, transferOrderId);
            Integer numberOfQuantityToMove = transferOrder.getLineItem().getQuantity();
            System.out.println("The number of quantity to move is " + numberOfQuantityToMove);
            for (int i = 0; i < numberOfQuantityToMove; i++) {
                String SKU = transferOrder.getLineItem().getItem().getSKU();
                StorageBinEntity originBin = transferOrder.getOrigin();
                StorageBinEntity targetBin = transferOrder.getTarget();
                System.out.println("Moving in progress....");
                System.out.println("SKU number is " + SKU);
                System.out.println("originBin: " + originBin.getId() + " moving to targetBin: " + targetBin.getId());

                boolean isPass = manufacturingInventoryControlBean.moveSingleItemBetweenStorageBins(SKU, originBin, targetBin);
                if (!isPass) {
                    System.out.println("markTransferOrderAsCompleted() incompleted resulted in roll back. Item was not found in source bin or volume of destination bin is insufficient.");
                    throw new Exception();
                }
            }
            transferOrder.setStatus("Completed");
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
            Query q = em.createQuery("SELECT t FROM ItemEntity t where t.SKU=:SKU");
            q.setParameter("SKU", SKU);
            ItemEntity itemEntity = (ItemEntity) q.getSingleResult();
            return itemEntity;
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }

    @Override
    public Boolean addLineItemToTransferOrder(Long transferOrderID, String SKU, Integer quantity) {
        System.out.println("addLineItemToTransferOrder() called.");
        try {
            ItemEntity itemEntity = searchItemBySKU(SKU);
            System.out.println("asdad");
            if (itemEntity == null) {
                System.out.println("444s");
                return false;//cannot find item
            }
            LineItemEntity lineItem = new LineItemEntity(itemEntity, quantity, "");
            System.out.println("2");
            TransferOrderEntity transferOrderEntity = em.getReference(TransferOrderEntity.class, transferOrderID);
            transferOrderEntity.setLineItem(lineItem);
            em.merge(transferOrderEntity);
            System.out.println("Item added to transfer order.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to addLineItemToTransferOrder:\n" + ex);
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public Boolean removeLineItemFromTransferOrder(Long transferOrderID) {
        System.out.println("removeLineItemFromTransferOrder() called.");
        try {
            TransferOrderEntity transferOrderEntity = em.getReference(TransferOrderEntity.class, transferOrderID);
            Long lineItemID = transferOrderEntity.getLineItem().getId();
            transferOrderEntity.setLineItem(null);
            em.merge(transferOrderEntity);
            LineItemEntity lineItemEntity = em.getReference(LineItemEntity.class, lineItemID);
            em.remove(lineItemEntity);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to removeLineItemFromTransferOrder:\n" + ex);
            return false;
        }
    }
////TODO NEED TO TEST
//
//    @Override
//    public List<TransferOrderEntity> createOutboundTransferOrder(Long warehouseID, List<LineItemEntity> lineItems) {
//        int qtyNeededToTransfer = 0;
//        int qtyToTransferOutFromBin = 0;
//        String SKUtoFind;
//        LineItemEntity currentItem = null;
//        StorageBinEntity outboundStorageBin = getOutboundStorageBin(warehouseID);
//        List<TransferOrderEntity> transferOrdersCreated = new ArrayList<>();
//        try {
//            for (LineItemEntity lineItem1 : lineItems) {
//                qtyNeededToTransfer = lineItem1.getQuantity();
//                List<StorageBinEntity> binsThatMatchesSKU = manufacturingInventoryControlBean.findStorageBinsThatContainsItem(outboundStorageBin.getWarehouse().getId(), lineItem1.getItem().getSKU());
//                for (StorageBinEntity eachBinThatMatchesSKU : binsThatMatchesSKU) {
//                    qtyToTransferOutFromBin = 0;
//                    SKUtoFind = lineItem1.getItem().getSKU();
//                    List<StorageBin_ItemEntity> itemsInEachBinThatMatchesSKU = eachBinThatMatchesSKU.getItems();
//                    for (StorageBin_ItemEntity eachItem : itemsInEachBinThatMatchesSKU) {
//                        currentItem = eachItem;
//                        for (ItemEntity itemEntity : currentItem.getItem()) {
//                            if (itemEntity.getSKU().equals(SKUtoFind)) {
//                                qtyNeededToTransfer--;
//                                qtyToTransferOutFromBin++;
//                            }
//                        }
//                        if (qtyNeededToTransfer <= 0) {
//                            break;
//                        }
//                    }
//                    if (qtyNeededToTransfer > 0) {
//                        for (ItemEntity itemEntity : currentItem.getItem()) {
//                            LineItemEntity lineItem = new LineItemEntity(itemEntity, qtyToTransferOutFromBin, "");
//                            WarehouseEntity warehouseEntity = em.getReference(WarehouseEntity.class, warehouseID);
//                            transferOrder = new TransferOrderEntity(warehouseEntity, lineItem, eachBinThatMatchesSKU, outboundStorageBin);
//                            em.persist(transferOrder);
//                        }
//                        transferOrdersCreated.add(transferOrder);
//                    }
//                    if (qtyNeededToTransfer <= 0) {
//                        break;
//                    }
//                }
//            }
//            return transferOrdersCreated;
//        } catch (Exception ex) {
//            System.out.println("\nServer failed to createOutboundTransferOrder:\n" + ex);
//            return null;
//        }

//    }
}
