package SCM.ManufacturingWarehouseManagement;

import EntityManager.ItemEntity;
import EntityManager.LineItemEntity;
import EntityManager.StorageBinEntity;
import EntityManager.TransferOrderEntity;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ManufacturingWarehouseManagementBeanLocal {

    public void createStorageBin(String type, Integer _length, Integer width, Integer height, Long warehouseId);

    public boolean updateStorageBin(StorageBinEntity storageBin);

    public boolean deleteStorageBin(Long id);

    public StorageBinEntity viewStorageBin(Long id);

    public List<StorageBinEntity> viewAllStorageBin();

    public LineItemEntity createLineItem(ItemEntity item, Integer quantity, String packType);

    public void createTransferOrder(List<LineItemEntity> lineItems, StorageBinEntity origin, StorageBinEntity target);

    public boolean updateTransferOrder(TransferOrderEntity transferOrder);

    public TransferOrderEntity viewTransferOrder(Long id);

    public boolean deleteTransferOrder(Long id);

}
