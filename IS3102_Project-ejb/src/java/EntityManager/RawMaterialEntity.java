package EntityManager;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RawMaterialEntity extends ItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "rawMaterial")
    private List<Supplier_RawMaterialEntity> listOfSupplier_RawMaterialInfo;
    private String name;
    private String category;
    private String description;
    @OneToOne(cascade = {CascadeType.ALL}, mappedBy = "rawMaterial")
    private ItemEntity item;

    public RawMaterialEntity() {
    }

    public RawMaterialEntity(String name, String category, String description, ItemEntity item) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.item = item;
    }

    public void create(String name) {
        setName(name);
    }

    public List<Supplier_RawMaterialEntity> getListOfSupplier_RawMaterialInfo() {
        return listOfSupplier_RawMaterialInfo;
    }

    public void setListOfSupplier_RawMaterialInfo(List<Supplier_RawMaterialEntity> listOfSupplier_RawMaterialInfo) {
        this.listOfSupplier_RawMaterialInfo = listOfSupplier_RawMaterialInfo;
    }

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RawMaterialEntity)) {
            return false;
        }
        RawMaterialEntity other = (RawMaterialEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityManagerBean.RawMaterial[ id=" + id + " ]";
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
