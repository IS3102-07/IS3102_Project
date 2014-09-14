package EntityManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String SKU;
    private String batch;
    @OneToMany(cascade = {CascadeType.ALL})
    private Collection<Item_CountryEntity> itemCountryList;
    @ManyToOne
    private WarehouseEntity warehouses;
    @OneToOne
    private FurnitureEntity furniture;
    @OneToOne
    private RawMaterialEntity rawMaterial;
    @OneToOne
    private RawMaterialEntity retailProduct;
    private Integer _length;
    private Integer width;
    private Integer height;
    private Integer volume;

    public ItemEntity() {
        this.itemCountryList = new ArrayList<>();
    }

    public ItemEntity(String SKU, Integer _length, Integer width, Integer height) {
        this.SKU = SKU;
        this._length = _length;
        this.width = width;
        this.height = height;
        this.volume = _length * width * height;
    }

    public Integer getLength() {
        return _length;
    }

    public void setLength(Integer _length) {
        this._length = _length;
        this.volume = _length * width * height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
        this.volume = _length * width * height;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
        this.volume = _length * width * height;
    }

    public Integer getVolume() {
        return volume;
    }

    public int getEntity() {
        if (furniture != null) {
            return 1;
        } else if (rawMaterial != null) {
            return 2;
        } else {
            return -1;
        }
    }

    public FurnitureEntity getFurniture() {
        return furniture;
    }

    public void setFurniture(FurnitureEntity furniture) {
        this.furniture = furniture;
    }

    public RawMaterialEntity getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterialEntity rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (SKU != null ? SKU.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemEntity)) {
            return false;
        }
        ItemEntity other = (ItemEntity) object;
        if ((this.SKU == null && other.SKU != null) || (this.SKU != null && !this.SKU.equals(other.SKU))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "em.ItemEntity[ id=" + id + " ]";
    }

    public Collection<Item_CountryEntity> getItemCountryList() {
        return itemCountryList;
    }

    public void setItemCountryList(Collection<Item_CountryEntity> itemCountry) {
        this.itemCountryList = itemCountry;
    }

    /**
     * @return the internalItemCode
     */
    public String getSKU() {
        return SKU;
    }

    /**
     * @param SKU the internalItemCode to set
     */
    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    /**
     * @return the retailProduct
     */
    public RawMaterialEntity getRetailProduct() {
        return retailProduct;
    }

    /**
     * @param retailProduct the retailProduct to set
     */
    public void setRetailProduct(RawMaterialEntity retailProduct) {
        this.retailProduct = retailProduct;
    }

    /**
     * @return the warehouses
     */
    public WarehouseEntity getWarehouses() {
        return warehouses;
    }

    /**
     * @param warehouses the warehouses to set
     */
    public void setWarehouses(WarehouseEntity warehouses) {
        this.warehouses = warehouses;
    }

    /**
     * @return the batch
     */
    public String getBatch() {
        return batch;
    }

    /**
     * @param batch the batch to set
     */
    public void setBatch(String batch) {
        this.batch = batch;
    }
}
