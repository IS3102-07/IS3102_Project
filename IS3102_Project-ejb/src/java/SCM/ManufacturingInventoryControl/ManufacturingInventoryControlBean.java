package SCM.ManufacturingInventoryControl;

import EntityManager.FurnitureEntity;
import EntityManager.ItemEntity;
import EntityManager.LineItemEntity;
import EntityManager.PurchaseOrderEntity;
import EntityManager.RawMaterialEntity;
import EntityManager.RetailProductEntity;
import EntityManager.ShippingOrderEntity;
import EntityManager.StorageBinEntity;
import EntityManager.StorageBin_ItemEntity;
import EntityManager.WarehouseEntity;
import HelperClasses.ItemStorageBinHelper;
import SCM.ManufacturingWarehouseManagement.ManufacturingWarehouseManagementBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
    public boolean removeSingleItemFromStorageBin(StorageBinEntity source, String SKU) {
        System.out.println("removeSingleItemFromStorageBin() called");
        try {
            em.refresh(source);
            boolean stop = false;
            //Get all the list of item in the storage bin
            List<StorageBin_ItemEntity> storageBin_ItemEntities = source.getItems();
            //For each storage bin-item relationship list
            for (StorageBin_ItemEntity storageBin_ItemEntity : storageBin_ItemEntities) {
                //For each item
                for (ItemEntity itemEntity : storageBin_ItemEntity.getItem()) {
                    if (itemEntity.getSKU().equals(SKU)) {
                        storageBin_ItemEntity.setQuantity(storageBin_ItemEntity.getQuantity() - 1);
                        if (storageBin_ItemEntity.getQuantity() == 0) {
                            storageBin_ItemEntities.remove(storageBin_ItemEntity);
                        }
                        stop = true;
                        break;
                    }

                }
                if (stop) {
                    break;
                }
            }
            System.out.println("removeSingleItemFromStorageBin(): Item removed");
            return true;
        } catch (Exception ex) {
            System.out.println("Failed to removeSingleItemFromStorageBin()");
            return false;
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Boolean removeOutboundBinToShipAShippingOrder(Long shippingOrderID) {
        System.out.println("removeOutboundBinToShipAShippingOrder called()");
        try {
            ShippingOrderEntity shippingOrderEntity = em.getReference(ShippingOrderEntity.class, shippingOrderID);
            WarehouseEntity warehouse = shippingOrderEntity.getOrigin();
            StorageBinEntity outbound = manufacturingWarehouseManagementBean.getOutboundStorageBin(warehouse.getId());
            List<LineItemEntity> itemsInShippingOrder = shippingOrderEntity.getLineItems();
            //For each item in shipping order
            for (LineItemEntity lineItemEntity : itemsInShippingOrder) {
                //Check if it's in outbound bin
                if (checkIfItemExistInsideStorageBin(outbound.getId(), lineItemEntity.getItem().getSKU())) {
                    //Check qty sufficient first before removing
                    List<StorageBin_ItemEntity> itemsInOutbound = outbound.getItems();
                    for (StorageBin_ItemEntity storageBin_ItemEntity : itemsInOutbound) {
                        //Find matching item
                        for (ItemEntity itemEntity : storageBin_ItemEntity.getItem()) {
                            if (itemEntity.getSKU().equals(lineItemEntity.getItem().getSKU())) {
                                //check quantity   
                                if (storageBin_ItemEntity.getQuantity() < lineItemEntity.getQuantity()) {
                                    System.out.println("Outbound bin does not have sufficient quantity to ship the order.");
                                    throw new Exception();
                                } else {
                                    //Actual removing
                                    for (int i = 0; i < lineItemEntity.getQuantity(); i++) {
                                        removeSingleItemFromStorageBin(outbound, lineItemEntity.getItem().getSKU());
                                    }
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("Item was not found in outbound bin.");
                    throw new Exception();
                }

            }
            return true;
        } catch (EntityNotFoundException ex) {
            System.out.println("Could not find either shipping order.");
            return false;
        } catch (Exception ex) {
            System.out.println("Failed to removeOutboundBinToShipAShippingOrder()");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean moveInboundPurchaseOrderItemsToReceivingBin(Long purchaseOrderID
    ) {
        System.out.println("moveInboundPurchaseOrderItemsToReceivingBin called()");
        try {
            PurchaseOrderEntity purchaseOrderEntity = em.getReference(PurchaseOrderEntity.class, purchaseOrderID);
            Long warehouseID = purchaseOrderEntity.getReceivedWarehouse().getId();
            List<LineItemEntity> lineItemsInPurchaseOrder = purchaseOrderEntity.getLineItems();
            for (LineItemEntity lineItemEntity : lineItemsInPurchaseOrder) {
                ItemEntity itemEntity = lineItemEntity.getItem();
                int quantity = lineItemEntity.getQuantity();
                for (int i = 0; i < quantity; i++) {
                    if (!addItemToReceivingBin(warehouseID, itemEntity.getSKU())) {
                        return false;
                    }
                }
            }
            return true;
        } catch (EntityNotFoundException ex) {
            System.out.println("Could not find either purchase order or the warehouse.");
            return false;
        } catch (Exception ex) {
            System.out.println("Failed to moveInboundPurchaseOrderItemsToReceivingBin()");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean moveInboundShippingOrderItemsToReceivingBin(Long shippingOrderID
    ) {
        System.out.println("moveInboundShippingOrderItemsToReceivingBin called()");
        try {
            ShippingOrderEntity shippingOrderEntity = em.getReference(ShippingOrderEntity.class, shippingOrderID);
            Long warehouseID = shippingOrderEntity.getDestination().getId();
            List<LineItemEntity> lineItemsInPurchaseOrder = shippingOrderEntity.getLineItems();
            for (LineItemEntity lineItemEntity : lineItemsInPurchaseOrder) {
                ItemEntity itemEntity = lineItemEntity.getItem();
                int quantity = lineItemEntity.getQuantity();
                for (int i = 0; i < quantity; i++) {
                    if (!addItemToReceivingBin(warehouseID, itemEntity.getSKU())) {
                        return false;
                    }
                }
            }
            return true;
        } catch (EntityNotFoundException ex) {
            System.out.println("Could not find either shipping order or the warehouse.");
            return false;
        } catch (Exception ex) {
            System.out.println("Failed to moveInboundShippingOrderItemsToReceivingBin()");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkIfItemExistInsideStorageBin(Long storageBinID, String SKU
    ) {
        StorageBinEntity storageBin = em.getReference(StorageBinEntity.class, storageBinID);
        List<StorageBin_ItemEntity> storageBin_ItemEntities = storageBin.getItems();
        for (StorageBin_ItemEntity storageBin_ItemEntity : storageBin_ItemEntities) {
            for (ItemEntity itemEntity : storageBin_ItemEntity.getItem()) {
                if (itemEntity.getSKU().equals(SKU)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean addItemToReceivingBin(Long warehouseID, String SKU
    ) {
        System.out.println("addItemToReceivingBin() called with SKU:" + SKU + " & wahouseID:" + warehouseID);
        StorageBinEntity inboundBin = manufacturingWarehouseManagementBean.getInboundStorageBin(warehouseID);
        em.refresh(inboundBin);
        if (inboundBin == null) {
            System.out.println("Failed to add item to receiving bin, receiving bin not found.");
            return false;
        }
        try {
            Query q = em.createQuery("SELECT t FROM ItemEntity t WHERE t.SKU=:SKU");
            q.setParameter("SKU", SKU);
            ItemEntity itemEntity = (ItemEntity) q.getSingleResult();
            //Check if storage bin have that type of item before
            if (checkIfItemExistInsideStorageBin(inboundBin.getId(), SKU)) {
                //If got, find that entry and update quantity
                List<StorageBin_ItemEntity> storageBin_ItemEntities = inboundBin.getItems();
                for (StorageBin_ItemEntity storageBin_ItemEntity : storageBin_ItemEntities) {
                    for (ItemEntity itemEntity1 : storageBin_ItemEntity.getItem()) {
                        if (itemEntity1.getSKU().equals(SKU)) {
                            storageBin_ItemEntity.setQuantity(storageBin_ItemEntity.getQuantity() + 1);
                            inboundBin.setFreeVolume(inboundBin.getFreeVolume() - itemEntity1.getVolume());
                            em.merge(storageBin_ItemEntity);
                            break;
                        }
                    }
                }
            } else {
                //If not, just add new item
                StorageBin_ItemEntity storageBin_ItemEntity = new StorageBin_ItemEntity(itemEntity, inboundBin, 1);
                inboundBin.getItems().add(storageBin_ItemEntity);
                em.merge(inboundBin);
                em.persist(storageBin_ItemEntity);
            }

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
    public boolean moveSingleItemBetweenStorageBins(String SKU, StorageBinEntity source, StorageBinEntity destination
    ) {
        System.out.println("moveSingleItemBetweenStorageBins() called with SKU:" + SKU);
        em.refresh(source);
        em.refresh(destination);
        List<StorageBin_ItemEntity> itemsInSourceBin = source.getItems();
        System.out.println("Size of items in source bin is : " + itemsInSourceBin.size());
        List<StorageBin_ItemEntity> itemsInDestinationBin = destination.getItems();
        System.out.println("Size of items in destination bin is : " + itemsInDestinationBin.size());

        ItemEntity itemEntity = null;
        boolean itemRemoved = false;
        try {
            //Search through the souce bin to see if it exist
            for (int i = 0; i < itemsInSourceBin.size(); i++) {
                for (ItemEntity itemEntity1 : itemsInSourceBin.get(i).getItem()) {
                    if (itemEntity1.getSKU().equals(SKU)) {
                        itemEntity = itemEntity1;
                        
                        //update the quantity of source bin
                        itemsInSourceBin.get(i).setQuantity(itemsInSourceBin.get(i).getQuantity() - 1);
                        System.out.println("Free volume of source bin before transfer: " + source.getFreeVolume());
                        source.setFreeVolume(source.getFreeVolume() + itemEntity1.getVolume());
                        System.out.println("Item entity volume to be transferred: " + itemEntity1.getVolume());
                        System.out.println("Free volume of source bin:" + source.getFreeVolume());
                        
                        
                        
                        //if last time, remove the row
                        if (itemsInSourceBin.get(i).getQuantity() == 0) {
                            itemsInSourceBin.remove(i);
                        }
                        itemRemoved = true;
                        
                        em.merge(itemsInSourceBin.get(i));
                    }
                }
            }
            if (!itemRemoved) {
                System.out.println("Item was not moved. Item was not found in the source bin.");
                return false;
            }
            //check if destination already has the item
            List<StorageBin_ItemEntity> storageBin_ItemEntities = destination.getItems();
            for (StorageBin_ItemEntity storageBin_ItemEntity : storageBin_ItemEntities) {
                for (ItemEntity itemEntity1 : storageBin_ItemEntity.getItem()) {
                    if (itemEntity1.getSKU().equals(SKU)) {
                        //update quantity if got
                        storageBin_ItemEntity.setQuantity(storageBin_ItemEntity.getQuantity() + 1);
                        destination.setFreeVolume(destination.getFreeVolume() - itemEntity1.getVolume());
                        break;
                    } else {//else create a new entity
                        itemsInDestinationBin.add(new StorageBin_ItemEntity(itemEntity, destination, 1));
                        em.merge(itemsInDestinationBin);
                    }
                }
            }
            System.out.println("The item is moved successfully between bins.");
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
    public Integer checkItemQty(Long warehouseId, String SKU
    ) {
        System.out.println("checkItemQty() called with SKU:" + SKU);
        Integer qty = 0;
        try {
            Query q = em.createQuery("Select sb from StorageBinEntity sb where sb.warehouse.id=:id");
            q.setParameter("id", warehouseId);
            List<StorageBinEntity> storageBins = q.getResultList();
            for (StorageBinEntity currentBin : storageBins) {
                for (int i = 0; i < currentBin.getItems().size(); i++) {
                    for (ItemEntity itemEntity : currentBin.getItems().get(i).getItem()) {
                        if (itemEntity.getSKU().equals(SKU)) {
                            qty++;
                        }
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
    public List<StorageBinEntity> findStorageBinsThatContainsItem(Long warehouseId, String SKU
    ) {
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
    public Integer getTotalVolumeOfInboundStorageBin(Long warehouseID
    ) {
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
    public Integer getTotalVolumeOfOutboundStorageBin(Long warehouseID
    ) {
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
    public Integer getTotalVolumeOfShelfStorageBin(Long warehouseID
    ) {
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
    public Integer getTotalVolumeOfPalletStorageBin(Long warehouseID
    ) {
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
    public Integer getTotalFreeVolumeOfInboundStorageBin(Long warehouseID
    ) {
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
    public Integer getTotalFreeVolumeOfOutboundStorageBin(Long warehouseID
    ) {
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
    public Integer getTotalFreeVolumeOfShelfStorageBin(Long warehouseID
    ) {
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
    public Integer getTotalFreeVolumeOfPalletStorageBin(Long warehouseID
    ) {
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

    //Assumes one type of item is in each storage bin, so just return the first one
    private List<StorageBin_ItemEntity> getItemInsideStorageBin(Long storageBinID) {
        System.out.println("getItemInsideStorageBin() called");

        try {
            StorageBinEntity storageBinEntity = em.getReference(StorageBinEntity.class, storageBinID);
            List<StorageBin_ItemEntity> itemEntitiesInStorageBin = storageBinEntity.getItems();
            if (itemEntitiesInStorageBin
                    == null || itemEntitiesInStorageBin.size()
                    == 0) {
                System.out.println("No items");
                return null;
            } else {
                System.out.println("Returned list of items & their quantity");
                return itemEntitiesInStorageBin;
            }
        } catch (Exception ex) {
            System.out.println("Failed to getItemInsideStorageBin()");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ItemStorageBinHelper> getItemList(Long warehouseID) {
        System.out.println("getItemList() called");
        try {
            List<ItemStorageBinHelper> itemStorageBinHelperList = new ArrayList<ItemStorageBinHelper>();
            WarehouseEntity warehouseEntity = em.getReference(WarehouseEntity.class, warehouseID);
            List<StorageBinEntity> storageBins = warehouseEntity.getStorageBins();

            ItemStorageBinHelper itemStorageBinHelper = new ItemStorageBinHelper();
            //For each bin in the warehouse
            for (StorageBinEntity storageBin : storageBins) {
                //Get all their contents
                List<StorageBin_ItemEntity> storageBin_ItemEntities = getItemInsideStorageBin(storageBin.getId());
                //If the bin is not empty
                if (storageBin_ItemEntities != null && storageBin_ItemEntities.size() > 0) {
                    //Add all the entries inside the bin to helper list
                    for (int i = 0; i < storageBin_ItemEntities.size(); i++) {
                        for (ItemEntity itemEntity : storageBin_ItemEntities.get(i).getItem()) {
                            itemStorageBinHelper = new ItemStorageBinHelper();
                            itemStorageBinHelper.setStorageBin_ItemID(storageBin_ItemEntities.get(i).getId());
                            itemStorageBinHelper.setSKU(itemEntity.getSKU());
                            itemStorageBinHelper.setItemName(itemEntity.getName());
                            itemStorageBinHelper.setStorageBinID(storageBin.getId());
                            itemStorageBinHelper.setItemQty(storageBin_ItemEntities.get(i).getQuantity());
                            itemStorageBinHelper.setItemType(itemEntity.getType());
                            itemStorageBinHelperList.add(itemStorageBinHelper);
                        }
                    }
                }
            }

            return itemStorageBinHelperList;
        } catch (EntityNotFoundException ex) {
            System.out.println("Warehouse could not be found.");
            return null;
        } catch (Exception ex) {
            System.out.println("System failed to getItemList()");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean emptyStorageBin_ItemEntity(Long storageBin_ItemID, Long storageBinID) {
        System.out.println("emptyStorageBin_ItemEntity() called");

        try {
            StorageBin_ItemEntity storageBin_ItemEntity = em.getReference(StorageBin_ItemEntity.class, storageBin_ItemID);

            StorageBinEntity storageBinEntity = em.getReference(StorageBinEntity.class, storageBinID);

            List<ItemEntity> itemsDeleted = storageBin_ItemEntity.getItem();
            int totalVolumeDeleted = 0;

            for (ItemEntity itemEntity : itemsDeleted) {
                totalVolumeDeleted += itemEntity.getVolume() * storageBin_ItemEntity.getQuantity();
            }
            storageBinEntity.setFreeVolume(storageBinEntity.getFreeVolume() + totalVolumeDeleted);
            em.remove(storageBin_ItemEntity);

            return true;
        } catch (EntityNotFoundException ex) {
            System.out.println("Storage Bin could not be found.");
            return false;
        } catch (Exception ex) {
            System.out.println("Failed to emptyStorageBin_ItemEntity()");
            ex.printStackTrace();
            return false;
        }
    }
}
