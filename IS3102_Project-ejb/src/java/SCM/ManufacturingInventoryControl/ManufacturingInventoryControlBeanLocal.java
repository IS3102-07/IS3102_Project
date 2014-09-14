package SCM.ManufacturingInventoryControl;

import EntityManager.ItemEntity;
import EntityManager.StorageBinEntity;
import EntityManager.WarehouseEntity;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ManufacturingInventoryControlBeanLocal {

    //Finds the list of suitable storage bin that can hold the item based on the kind of item passed in
    public List<StorageBinEntity> getEmptyStorageBins(Long warehouseID, ItemEntity itemEntity); //returns the appropirate type of empty storage bins
//    public Integer calculateQtyOfItemsStorageBinHolds(StorageBinEntity storageBin, Integer volumeOfItem);
//    
//    
//    public boolean compareStorageBinWithItem(StorageBinEntity storageBin, ItemEntity itemEntity);
//    
//    
//    
    public boolean addItemToReceivingBin(String SKU);
    //if you want to move multiple items, please call this method many times
    public boolean moveSingleItemBetweenStorageBins(String SKU, StorageBinEntity source, StorageBinEntity destination);
    
    public Integer checkItemQty(Long warehouseId, String SKU);
    public List<StorageBinEntity> findStorageBinsThatContainsItem(Long warehouseId, String SKU);
}
