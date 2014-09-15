package SCM.ManufacturingWarehouseManagement;

import EntityManager.ItemEntity;
import EntityManager.LineItemEntity;
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
            boolean updateStorageBin(StorageBinEntity storageBin) {
        try {
            if (em.getReference(StorageBinEntity.class, storageBin.getId()) == null) {

                return false;
            }
            em.merge(storageBin);
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
            if (em.getReference(StorageBinEntity.class, storageBin.getId()) == null) {

                return false;
            }
            em.remove(storageBin);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to deleteStorageBin:\n" + ex);
            return false;
        }
    }

    @Override
    public StorageBinEntity
            viewStorageBin(Long id) {
        try {
            if (em.getReference(StorageBinEntity.class, storageBin.getId()) == null) {

                return null;
            }

            return em.getReference(StorageBinEntity.class, storageBin.getId());
        } catch (Exception ex) {
            System.out.println("\nServer failed to viewStorageBin:\n" + ex);
            return null;
        }
    }

    @Override
    public List<StorageBinEntity> viewAllStorageBin() {
        try {
            Query q = em.createQuery("Select sb from StorageBinEntity sb");
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
    public StorageBinEntity getInboundStorageBin() {
        try {
            Query q = em.createQuery("Select sb from StorageBinEntity sb where sb.type='Inbound'");
            storageBin = (StorageBinEntity) q.getSingleResult();
            return storageBin;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getInboundStorageBin:\n" + ex);
            return null;
        }
    }

    @Override
    public StorageBinEntity getOutboundStorageBin() {
        try {
            Query q = em.createQuery("Select sb from StorageBinEntity sb where sb.type='Outbound'");
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
    public List<TransferOrderEntity> viewListOfAllTransferOrder() {
        try {
            Query q = em.createQuery("Select t from TransferOrderEntity t");
            return q.getResultList();
        } catch (Exception ex) {
            System.out.println("\nServer failed to viewListOfAllTransferOrder:\n" + ex);
            return null;
        }
    }

    @Override
    public
            boolean markTransferOrderAsUnfulfilled(Long transferOrderId) {
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
    public List<TransferOrderEntity> createTransferOrder(StorageBinEntity origin, List<StorageBinEntity> targets, List<LineItemEntity> lineItems) {
        try {
            List<TransferOrderEntity> listOfTransferOrdersCreated = new ArrayList<TransferOrderEntity>();
            for (int i = 0; i < lineItems.size(); i++) {
                transferOrder = new TransferOrderEntity(lineItems.get(i), origin, targets.get(i));
                em.persist(transferOrder);
                listOfTransferOrdersCreated.add(transferOrder);
            }
            return listOfTransferOrdersCreated;
        } catch (Exception ex) {
            System.out.println("\nServer failed to createStorageBin:\n" + ex);
            return null;
        }
    }

    //TODO NEED TO TEST
    @Override
    public List<TransferOrderEntity> createOutboundTransferOrder(List<LineItemEntity> lineItems) {
        int qtyNeededToTransfer = 0;
        int qtyToTransferOutFromBin = 0;
        String SKUtoFind = "";
        ItemEntity currentItem = new ItemEntity();
        StorageBinEntity outboundStorageBin = getOutboundStorageBin();
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
                    LineItemEntity lineItem = new LineItemEntity(currentItem, qtyToTransferOutFromBin, "");
                    transferOrder = new TransferOrderEntity(lineItem, eachBinThatMatchesSKU, outboundStorageBin);
                    em.persist(transferOrder);
                    transferOrdersCreated.add(transferOrder);
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
