/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SCM.ManufacturingInventoryControl;

import EntityManager.ItemEntity;
import EntityManager.MemberEntity;
import EntityManager.StorageBinEntity;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Jason
 */
@Stateless
public class ManufacturingInventoryControlBean implements ManufacturingInventoryControlBeanLocal {
    public List<StorageBinEntity> getEmptyStorageBins(ItemEntity itemEntity){
        return null;
    }
    
    public boolean addItemToReceivingBin(String SKU){
        System.out.println("addItemToStorageBin() called with SKU:" + SKU);
        return true;
    }
    public boolean moveItemBetweenStorageBins(String SKU, StorageBinEntity source, StorageBinEntity destination){
        return true;
    }
    public boolean addItemsToShippingBin(String SKU) {
        return true;
    }
    public int checkItemQty(String SKU){
        return 0;
    }
    public StorageBinEntity findStorageBinThatContainsItem(String SKU){
        return null;
    }
}
