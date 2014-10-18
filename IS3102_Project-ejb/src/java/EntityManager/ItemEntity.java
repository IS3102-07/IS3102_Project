package EntityManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    private String SKU;
    @Lob
    private String name;
    @Lob
    private String type;
    @OneToMany(mappedBy = "item", cascade = {CascadeType.ALL})
    private List<Item_CountryEntity> itemCountryList;
    @ManyToOne
    private WarehouseEntity warehouses;
    private Integer _length;
    private Integer width;
    private Integer height;
    private Integer volume;
    private Boolean isDeleted;
    private String description;
    private String category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    private List<Supplier_ItemEntity> suppliers;    

    public ItemEntity() {
        this.itemCountryList = new ArrayList<>();
    }

    public ItemEntity(String SKU, Integer _length, Integer width, Integer height) {
        this.SKU = SKU;
        this._length = _length;
        this.width = width;
        this.height = height;
        this.volume = _length * width * height;
        this.isDeleted = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getLength() {
        return _length;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getVolume() {
        return volume;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (SKU != null ? SKU.hashCode() : 0);
        return hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Item_CountryEntity> getItemCountryList() {
        return itemCountryList;
    }

    public void setItemCountryList(List<Item_CountryEntity> itemCountry) {
        this.itemCountryList = itemCountry;
    }

    /**
     * @return the internalItemCode
     */
    public String getSKU() {
        return SKU;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Supplier_ItemEntity> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier_ItemEntity> suppliers) {
        this.suppliers = suppliers;
    }
    
}
