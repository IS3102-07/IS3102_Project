/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SCM.ManufacturingInventoryControl;

import EntityManager.FurnitureEntity;
import EntityManager.ItemEntity;
import EntityManager.StorageBinEntity;
import EntityManager.WarehouseEntity;
import SCM.ManufacturingWarehouseManagement.ManufacturingWarehouseManagementBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ManufacturingInventoryControlBean implements ManufacturingInventoryControlBeanLocal {

    @EJB
    private ManufacturingWarehouseManagementBeanLocal manufacturingWarehouseManagementBean;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<StorageBinEntity> getEmptyStorageBins(Long warehouseID, ItemEntity itemEntity) {
        System.out.println("getEmptyStorageBins() called with ItemEntity:" + itemEntity);

        WarehouseEntity warehouseEntity = em.getReference(WarehouseEntity.class, warehouseID);

        List<StorageBinEntity> listOfAppropriateEmptyStorageBins = new ArrayList<>();
        String storageBinType = "";
        if (itemEntity.getFurniture() != null) {
            storageBinType = "Pallet";
        }
        if (itemEntity.getRawMaterial() != null) {
            storageBinType = "Pallet";
        }
        if (itemEntity.getRetailProduct() != null) {
            storageBinType = "Shelf";
        }
        try {
            List<StorageBinEntity> storageBinEntities = warehouseEntity.getStorageBin();
            for (StorageBinEntity binEntity : storageBinEntities) {
                if (binEntity.getItems().size() == 0 && binEntity.getType().equals(storageBinType)) {
                    listOfAppropriateEmptyStorageBins.add(binEntity);
                }
            }
            System.out.println("Returned listOfAppropriateEmptyStorageBins.");
            return listOfAppropriateEmptyStorageBins;
        } catch (EntityNotFoundException ex) {
            System.out.println("Failed to return listOfAppropriateEmptyStorageBins. Warehouse or bin not found.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed return listOfAppropriateEmptyStorageBins:\n" + ex);
            return null;
        }
    }

    @Override
    public boolean addItemToReceivingBin(Long warehouseID, String SKU) {
        System.out.println("addItemToStorageBin() called with SKU:" + SKU);
        StorageBinEntity inboundBin = manufacturingWarehouseManagementBean.getInboundStorageBin(warehouseID);
        if (inboundBin == null) {
            System.out.println("Failed to add item to receiving bin, receiving bin not found.");
            return false;
        }
        List<ItemEntity> itemsInInBoundBin = inboundBin.getItems();
        try {
            Query q = em.createQuery("SELECT t FROM ItemEntity t WHERE t.SKU=:SKU");
            q.setParameter("SKU", SKU);
            ItemEntity itemEntity = (ItemEntity) q.getSingleResult();
            itemsInInBoundBin.add(itemEntity);
            inboundBin.setItems(itemsInInBoundBin);
            em.merge(inboundBin);
            return true;
        } catch (EntityNotFoundException ex) {
            System.out.println("Failed to add item to receiving bin, item not found.");
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to move items between bins:\n" + ex);
            return false;
        }
    }

    @Override
    public boolean moveSingleItemBetweenStorageBins(String SKU, StorageBinEntity source, StorageBinEntity destination) {
        System.out.println("moveItemBetweenStorageBins() called with SKU:" + SKU);
        List<ItemEntity> itemsInSourceBin = source.getItems();
        List<ItemEntity> itemsInDestinationBin = destination.getItems();
        try {
            for (int i = 0; i < itemsInSourceBin.size(); i++) {
                if (itemsInSourceBin.get(i).getSKU().equals(SKU)) {
                    ItemEntity itemRemovedFromSource = itemsInSourceBin.remove(i);
                    itemsInDestinationBin.add(itemRemovedFromSource);
                    source.setFreeVolume(source.getFreeVolume() + itemRemovedFromSource.getVolume());
                    destination.setFreeVolume(destination.getFreeVolume() - itemRemovedFromSource.getVolume());
                    break;
                }
            }
            em.merge(itemsInSourceBin);
            em.merge(itemsInDestinationBin);
            System.out.println("The item is moved successfully between bins.");
            return true;
        } catch (EntityNotFoundException ex) {
            System.out.println("Failed to move the item between bins, item not found.");
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to move the item between bins:\n" + ex);
            return false;
        }
    }

    @Override
    public Integer checkItemQty(Long warehouseId, String SKU) {
        System.out.println("checkItemQty() called with SKU:" + SKU);
        Integer qty = 0;
        try {
            Query q = em.createQuery("Select sb from StorageBinEntity sb where sb.warehouse.id=:id");
            q.setParameter("id", warehouseId);
            List<StorageBinEntity> storageBins = q.getResultList();
            for (StorageBinEntity currentBin : storageBins) {
                for (int i = 0; i < currentBin.getItems().size(); i++) {
                    if (currentBin.getItems().get(i).getSKU().equals(SKU)) {
                        qty++;
                    }
                }
            }
            System.out.println("Returned qty:" + qty);
            return qty;
        } catch (EntityNotFoundException ex) {
            System.out.println("Failed checkItemQty, warehouse or item not found.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to checkItemQty:\n" + ex);
            return null;
        }
    }

    public List<StorageBinEntity> findStorageBinsThatContainsItem(Long warehouseId, String SKU) {
        try {
            Query q = em.createQuery("Select sb from StorageBinEntity sb where sb.warehouse.id=:id and sb.items.SKU=:SKU");
            q.setParameter("id", warehouseId);
            q.setParameter("SKU", SKU);
            List<StorageBinEntity> storageBins = q.getResultList();
            return storageBins;
        } catch (EntityNotFoundException ex) {
            System.out.println("Failed findStorageBinThatContainsItem, warehouse or item not found.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to findStorageBinThatContainsItem:\n" + ex);
            return null;
        }
    }
}
