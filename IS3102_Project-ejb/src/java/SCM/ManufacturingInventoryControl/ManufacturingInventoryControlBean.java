package SCM.ManufacturingInventoryControl;

import EntityManager.FurnitureEntity;
import EntityManager.ItemEntity;
import EntityManager.RawMaterialEntity;
import EntityManager.RetailProductEntity;
import EntityManager.StorageBinEntity;
import EntityManager.WarehouseEntity;
import HelperClasses.ItemStorageBinHelper;
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
        if (itemEntity instanceof FurnitureEntity) {
            storageBinType = "Pallet";
        }
        if (itemEntity instanceof RawMaterialEntity) {
            storageBinType = "Pallet";
        }
        if (itemEntity instanceof RetailProductEntity) {
            storageBinType = "Shelf";
        }
        try {
            List<StorageBinEntity> storageBinEntities = warehouseEntity.getStorageBins();
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
        System.out.println("moveSingleItemBetweenStorageBins() called with SKU:" + SKU);
        em.refresh(source);
        em.refresh(destination);
        List<ItemEntity> itemsInSourceBin = source.getItems();
        List<ItemEntity> itemsInDestinationBin = destination.getItems();   
        boolean itemRemoved = false;
        try {
            for (int i = 0; i < itemsInSourceBin.size(); i++) {
                if (itemsInSourceBin.get(i).getSKU().equals(SKU)) {
                    itemRemoved=true;
                    ItemEntity itemRemovedFromSource = itemsInSourceBin.remove(i);
                    itemsInDestinationBin.add(itemRemovedFromSource);
                    source.setFreeVolume(source.getFreeVolume() + itemRemovedFromSource.getVolume());
                    destination.setFreeVolume(destination.getFreeVolume() - itemRemovedFromSource.getVolume());
                    break;
                }
            }
            if(itemRemoved) {
                System.out.println("The item is moved successfully between bins.");
            } else {
                System.out.println("Item was not moved. No item was found.");
            }
            return itemRemoved;
        } catch (EntityNotFoundException ex) {
            System.out.println("Failed to move the item between bins, item not found.");
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to move the item between bins:\n" + ex);
            ex.printStackTrace();
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
    
    @Override
    public List<StorageBinEntity> findStorageBinsThatContainsItem(Long warehouseId, String SKU) {
        System.out.println("findStorageBinsThatContainsItem() called");
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
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Integer getTotalVolumeOfInboundStorageBin(Long warehouseID) {
        System.out.println("getTotalVolumeOfInboundStorageBin() called");
        try {
            Query q = em.createQuery("Select sb from StorageBinEntity sb where sb.warehouse.id=:id");
            q.setParameter("id", warehouseID);
            List<StorageBinEntity> storageBins = q.getResultList();
            Integer volume = 0;
            for (StorageBinEntity storageBinEntity : storageBins) {
                if (storageBinEntity.getType().equals("Inbound")) {
                    volume += storageBinEntity.getVolume();
                }
            }
            System.out.println("Returned volume.");
            return volume;
        } catch (EntityNotFoundException ex) {
            System.out.println("Failed getTotalVolumeOfInboundStorageBin, warehouse not found.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getTotalVolumeOfInboundStorageBin:\n" + ex);
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Integer getTotalVolumeOfOutboundStorageBin(Long warehouseID) {
        System.out.println("getTotalVolumeOfOutboundStorageBin() called");
        try {
            Query q = em.createQuery("Select sb from StorageBinEntity sb where sb.warehouse.id=:id");
            q.setParameter("id", warehouseID);
            List<StorageBinEntity> storageBins = q.getResultList();
            Integer volume = 0;
            for (StorageBinEntity storageBinEntity : storageBins) {
                if (storageBinEntity.getType().equals("Outbound")) {
                    volume += storageBinEntity.getVolume();
                }
            }
            System.out.println("Returned volume.");
            return volume;
        } catch (EntityNotFoundException ex) {
            System.out.println("Failed getTotalVolumeOfOutboundStorageBin, warehouse not found.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getTotalVolumeOfOutboundStorageBin:\n" + ex);
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Integer getTotalVolumeOfShelfStorageBin(Long warehouseID) {
        System.out.println("getTotalVolumeOfShelfStorageBin() called");
        try {
            Query q = em.createQuery("Select sb from StorageBinEntity sb where sb.warehouse.id=:id");
            q.setParameter("id", warehouseID);
            List<StorageBinEntity> storageBins = q.getResultList();
            Integer volume = 0;
            for (StorageBinEntity storageBinEntity : storageBins) {
                if (storageBinEntity.getType().equals("Shelf")) {
                    volume += storageBinEntity.getVolume();
                }
            }
            System.out.println("Returned volume.");
            return volume;
        } catch (EntityNotFoundException ex) {
            System.out.println("Failed getTotalVolumeOfShelfStorageBin, warehouse not found.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getTotalVolumeOfShelfStorageBin:\n" + ex);
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Integer getTotalVolumeOfPalletStorageBin(Long warehouseID) {
        System.out.println("getTotalVolumeOfPalletStorageBin() called");
        try {
            Query q = em.createQuery("Select sb from StorageBinEntity sb where sb.warehouse.id=:id");
            q.setParameter("id", warehouseID);
            List<StorageBinEntity> storageBins = q.getResultList();
            Integer volume = 0;
            for (StorageBinEntity storageBinEntity : storageBins) {
                if (storageBinEntity.getType().equals("Shelf")) {
                    volume += storageBinEntity.getVolume();
                }
            }
            System.out.println("Returned volume.");
            return volume;
        } catch (EntityNotFoundException ex) {
            System.out.println("Failed getTotalVolumeOfPalletStorageBin, warehouse not found.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getTotalVolumeOfPalletStorageBin:\n" + ex);
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Integer getTotalFreeVolumeOfInboundStorageBin(Long warehouseID) {
        System.out.println("getTotalFreeVolumeOfInboundStorageBin() called");
        try {
            Query q = em.createQuery("Select sb from StorageBinEntity sb where sb.warehouse.id=:id");
            q.setParameter("id", warehouseID);
            List<StorageBinEntity> storageBins = q.getResultList();
            Integer volume = 0;
            for (StorageBinEntity storageBinEntity : storageBins) {
                if (storageBinEntity.getType().equals("Inbound")) {
                    volume += storageBinEntity.getFreeVolume();
                }
            }
            System.out.println("Returned volume.");
            return volume;
        } catch (EntityNotFoundException ex) {
            System.out.println("Failed getTotalFreeVolumeOfInboundStorageBin, warehouse not found.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getTotalFreeVolumeOfInboundStorageBin:\n" + ex);
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Integer getTotalFreeVolumeOfOutboundStorageBin(Long warehouseID) {
        System.out.println("getTotalFreeVolumeOfOutboundStorageBin() called");
        try {
            Query q = em.createQuery("Select sb from StorageBinEntity sb where sb.warehouse.id=:id");
            q.setParameter("id", warehouseID);
            List<StorageBinEntity> storageBins = q.getResultList();
            Integer volume = 0;
            for (StorageBinEntity storageBinEntity : storageBins) {
                if (storageBinEntity.getType().equals("Outbound")) {
                    volume += storageBinEntity.getFreeVolume();
                }
            }
            System.out.println("Returned volume.");
            return volume;
        } catch (EntityNotFoundException ex) {
            System.out.println("Failed getTotalFreeVolumeOfOutboundStorageBin, warehouse not found.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getTotalFreeVolumeOfOutboundStorageBin:\n" + ex);
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Integer getTotalFreeVolumeOfShelfStorageBin(Long warehouseID) {
        System.out.println("getTotalFreeVolumeOfShelfStorageBin() called");
        try {
            Query q = em.createQuery("Select sb from StorageBinEntity sb where sb.warehouse.id=:id");
            q.setParameter("id", warehouseID);
            List<StorageBinEntity> storageBins = q.getResultList();
            Integer volume = 0;
            for (StorageBinEntity storageBinEntity : storageBins) {
                if (storageBinEntity.getType().equals("Shelf")) {
                    volume += storageBinEntity.getFreeVolume();
                }
            }
            System.out.println("Returned volume.");
            return volume;
        } catch (EntityNotFoundException ex) {
            System.out.println("Failed getTotalFreeVolumeOfShelfStorageBin, warehouse not found.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getTotalFreeVolumeOfShelfStorageBin:\n" + ex);
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public Integer getTotalFreeVolumeOfPalletStorageBin(Long warehouseID) {
        System.out.println("getTotalFreeVolumeOfPalletStorageBin() called");
        try {
            Query q = em.createQuery("Select sb from StorageBinEntity sb where sb.warehouse.id=:id");
            q.setParameter("id", warehouseID);
            List<StorageBinEntity> storageBins = q.getResultList();
            Integer volume = 0;
            for (StorageBinEntity storageBinEntity : storageBins) {
                if (storageBinEntity.getType().equals("Pallet")) {
                    volume += storageBinEntity.getFreeVolume();
                }
            }
            System.out.println("Returned volume.");
            return volume;
        } catch (EntityNotFoundException ex) {
            System.out.println("Failed getTotalFreeVolumeOfPalletStorageBin, warehouse not found.");
            return null;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getTotalFreeVolumeOfPalletStorageBin:\n" + ex);
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<ItemStorageBinHelper> getItemList(Long warehouseID) {
        List<ItemStorageBinHelper> itemStorageBinHelperList = new ArrayList<ItemStorageBinHelper>();
        List<ItemEntity> itemsInWarehouse = new ArrayList<ItemEntity>();
        WarehouseEntity warehouseEntity = em.getReference(WarehouseEntity.class, warehouseID);
        List<StorageBinEntity> storageBins = warehouseEntity.getStorageBins();
        for(StorageBinEntity storageBin : storageBins) {
            storageBin.getItems();
        }
        return itemStorageBinHelperList;
    }
}
