package SCM.ManufacturingWarehouseManagement;

import EntityManager.LineItemEntity;
import EntityManager.StorageBinEntity;
import EntityManager.TransferOrderEntity;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ManufacturingWarehouseManagementBeanLocal {

    public boolean createStorageBin(Long warehouseID, String type, Integer _length, Integer width, Integer height); //types are inbound, outbound, shelf, pallet

    public boolean updateStorageBin(Long storageBinId, Integer length, Integer width, Integer height);

    public boolean deleteStorageBin(Long id);

    public StorageBinEntity viewStorageBin(Long storageBinID);

    public List<StorageBinEntity> viewAllStorageBin(Long warehouseID);

    public StorageBinEntity getInboundStorageBin(Long warehouseID); //look for the inbound storagebin

    public StorageBinEntity getOutboundStorageBin(Long warehouseID); //look for the inbound storagebin

    public List<TransferOrderEntity> createTransferOrder(Long warehouseID, StorageBinEntity origin, List<StorageBinEntity> targets, List<LineItemEntity> lineItems);

    public boolean markTransferOrderAsCompleted(Long transferOrderId);

    public boolean cancelTransferOrder(Long transferOrderId);

    public TransferOrderEntity viewTransferOrder(Long transferOrderId);

    public List<TransferOrderEntity> viewAllTransferOrderByWarehouseId(Long warehouseId);
    
    public boolean deleteTransferOrder(Long id);

    public boolean markTransferOrderAsUnfulfilled(Long transferOrderId);

    public List<TransferOrderEntity> createOutboundTransferOrder(Long warehouseID, List<LineItemEntity> lineItems);
}
