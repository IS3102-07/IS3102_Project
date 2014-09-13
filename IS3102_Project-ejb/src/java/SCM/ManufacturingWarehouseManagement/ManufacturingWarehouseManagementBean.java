package SCM.ManufacturingWarehouseManagement;

import EntityManager.ItemEntity;
import EntityManager.LineItemEntity;
import EntityManager.StorageBinEntity;
import EntityManager.TransferOrderEntity;
import EntityManager.WarehouseEntity;
import SCM.ManufacturingInventoryControl.ManufacturingInventoryControlBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ManufacturingWarehouseManagementBean implements ManufacturingWarehouseManagementBeanLocal {

    @EJB
    private ManufacturingInventoryControlBeanLocal manufacturingInventoryControlBean;

    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;
    StorageBinEntity storageBin;
    WarehouseEntity warehouse;
    TransferOrderEntity transferOrder;
    List<LineItemEntity> lineItems;

    @Override
    public void createStorageBin(String type, Integer _length, Integer width, Integer height) {
        storageBin = new StorageBinEntity(type, _length, width, height);
        em.persist(storageBin);
    }

    @Override
    public boolean updateStorageBin(StorageBinEntity storageBin) {
        if (em.getReference(StorageBinEntity.class, storageBin.getId()) == null) {
            return false;
        }
        em.merge(storageBin);
        return true;
    }

    @Override
    public boolean deleteStorageBin(Long id) {
        if (em.getReference(StorageBinEntity.class, storageBin.getId()) == null) {
            return false;
        }
        em.remove(storageBin);
        return true;
    }

    @Override
    public StorageBinEntity viewStorageBin(Long id) {
        if (em.getReference(StorageBinEntity.class, storageBin.getId()) == null) {
            return null;
        }

        return em.getReference(StorageBinEntity.class, storageBin.getId());
    }

    @Override
    public List<StorageBinEntity> viewAllStorageBin() {
        Query q = em.createQuery("Select sb from StorageBinEntity sb");
        List<StorageBinEntity> storageBins = q.getResultList();
        return storageBins;
    }

    @Override
    public TransferOrderEntity viewTransferOrder(Long id) {
        if (em.getReference(TransferOrderEntity.class, id) == null) {
            return null;
        }
        return em.getReference(TransferOrderEntity.class, id);
    }

    @Override
    public boolean deleteTransferOrder(Long id) {
        if (em.getReference(TransferOrderEntity.class, id) == null) {
            return false;
        }
        em.remove(em.getReference(TransferOrderEntity.class, id));
        return true;
    }

    @Override
    public StorageBinEntity getInboundStorageBin() {
        Query q = em.createQuery("Select sb from StorageBinEntity sb where sb.type='Inbound'");
        storageBin = (StorageBinEntity) q.getSingleResult();
        return storageBin;
    }

    @Override
    public StorageBinEntity getOutboundStorageBin() {
        Query q = em.createQuery("Select sb from StorageBinEntity sb where sb.type='Outbound'");
        storageBin = (StorageBinEntity) q.getSingleResult();
        return storageBin;
    }

    @Override
    public boolean markTransferOrderAsCompleted(Long transferOrderId) {
        transferOrder = em.getReference(TransferOrderEntity.class, transferOrderId);
        return manufacturingInventoryControlBean.moveItemBetweenStorageBins(transferOrder.getLineItem().getItem().getSKU(), transferOrder.getOrigin(), transferOrder.getTarget());
    }

    @Override
    public boolean cancelTransferOrder(Long transferOrderId) {
        transferOrder = em.getReference(TransferOrderEntity.class, transferOrderId);
        transferOrder.setStatus("Cancelled");
        return true;
    }

    @Override
    public List<TransferOrderEntity> viewListOfAllTransferOrder() {
        Query q = em.createQuery("Select t from TransferOrderEntity t");
        return q.getResultList();
    }

    @Override
    public boolean markTransferOrderAsUnfulfilled(Long transferOrderId) {
        transferOrder = em.getReference(TransferOrderEntity.class, transferOrderId);
        transferOrder.setStatus("Unfulfillable");
        return true;
    }

    @Override
    public List<TransferOrderEntity> createTransferOrder(StorageBinEntity origin, List<StorageBinEntity> targets, List<LineItemEntity> lineItems) {
        List<TransferOrderEntity> listOfTransferOrdersCreated = new ArrayList<TransferOrderEntity>();
        for (int i = 0; i < lineItems.size(); i++) {
            transferOrder = new TransferOrderEntity(lineItems.get(i), origin, targets.get(i));
            em.persist(transferOrder);
            listOfTransferOrdersCreated.add(transferOrder);
        }
        return listOfTransferOrdersCreated;
    }


    //TODO NEED TO TEST
    @Override
    public List<TransferOrderEntity> createOutboundTransferOrder(List<LineItemEntity> lineItems) {
        int qtyOfItemsToTransfer = 0;
        int qtyOfItemsInCurrentBinAvailableToTransfer = 0;
        ItemEntity currentItem = new ItemEntity();
        StorageBinEntity outboundStorageBin = getOutboundStorageBin();
        List<TransferOrderEntity> transferOrdersCreated = new ArrayList<>();
        for (int i = 0; i < lineItems.size(); i++) {
            qtyOfItemsToTransfer = lineItems.get(i).getQuantity();

            List<StorageBinEntity> storageBinEntities = manufacturingInventoryControlBean.findStorageBinsThatContainsItem(outboundStorageBin.getWarehouse().getId(), lineItems.get(i).getItem().getSKU());

            for (int j = 0; j < storageBinEntities.size(); j++) {
                qtyOfItemsInCurrentBinAvailableToTransfer = 0;
                List<ItemEntity> itemsInCurrentBin = storageBinEntities.get(j).getItems();
                for (int k = 0; k < itemsInCurrentBin.size(); k++) {
                    currentItem = itemsInCurrentBin.get(k);
                    if (currentItem.getSKU().equals(lineItems.get(i).getItem().getSKU())) {
                        qtyOfItemsToTransfer--;
                        qtyOfItemsInCurrentBinAvailableToTransfer++;
                    }
                    if (qtyOfItemsToTransfer <= 0) {
                        break;
                    }
                }
                LineItemEntity lineItem = new LineItemEntity(currentItem, qtyOfItemsInCurrentBinAvailableToTransfer, "");
                transferOrder = new TransferOrderEntity(lineItem, storageBinEntities.get(j), outboundStorageBin);
                em.persist(transferOrder);
                transferOrdersCreated.add(transferOrder);
                if (qtyOfItemsToTransfer <= 0) {
                    break;
                }
            }
        }
        return transferOrdersCreated;
    }

}
