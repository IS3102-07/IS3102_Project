package SCM.ManufacturingInventoryControl;

import EntityManager.ItemEntity;
import EntityManager.StorageBinEntity;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ManufacturingInventoryControlBeanLocal {

    public List<StorageBinEntity> getEmptyStorageBins(ItemEntity itemEntity); //returns the appropirate type of empty storage bins
//    public Integer calculateQtyOfItemsStorageBinHolds(StorageBinEntity storageBin, Integer volumeOfItem);
//    
//    
//    public boolean compareStorageBinWithItem(StorageBinEntity storageBin, ItemEntity itemEntity);
//    
//    
//    
    public boolean addItemToReceivingBin(String SKU);
    public boolean moveItemBetweenStorageBins(String SKU, StorageBinEntity source, StorageBinEntity destination);
    public boolean addItemsToShippingBin(String SKU);
    public int checkItemQty(String SKU);
}
