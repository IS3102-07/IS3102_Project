package HelperClasses;

public class ItemStorageBinHelper {
    private String SKU;
    private String itemName;
    private Long storageBinID;
    private Integer itemQty;
    private String itemType;

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getStorageBinID() {
        return storageBinID;
    }

    public void setStorageBinID(Long storageBinID) {
        this.storageBinID = storageBinID;
    }

    public Integer getItemQty() {
        return itemQty;
    }

    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
        
}
