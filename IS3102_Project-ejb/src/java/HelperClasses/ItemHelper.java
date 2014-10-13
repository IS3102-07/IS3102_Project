package HelperClasses;

public class ItemHelper {

    private Long id;
    private String itemSKU;
    private String itemName;

    public ItemHelper() {
    }

    public ItemHelper(Long id, String itemSKU, String itemName) {
        this.id = id;
        this.itemSKU = itemSKU;
        this.itemName = itemName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemSKU() {
        return itemSKU;
    }

    public void setItemSKU(String itemSKU) {
        this.itemSKU = itemSKU;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
