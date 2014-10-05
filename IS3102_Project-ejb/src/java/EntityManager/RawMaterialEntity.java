package EntityManager;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RawMaterialEntity extends ItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    private String name;
    private String category;
    private String description;
    private Integer lotSize;
    private Integer leadTime;
    private Double price;
    @ManyToOne
    private SupplierEntity supplier;

    public RawMaterialEntity() {
    }

    public RawMaterialEntity(String SKU, String name, String category, String description, Integer _length, Integer width, Integer height, Integer lotSize, Integer leadTime, Double price) {
        super(SKU, _length, width, height);
        this.name = name;
        super.setName(name);
        this.category = category;
        this.description = description;
        this.lotSize = lotSize;
        this.leadTime = leadTime;
        this.price = price;
        super.setType("Raw Material");
    }

    public void create(String name) {
        this.name = name;
        super.setName(name);
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
        super.setName(name);
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SupplierEntity getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierEntity supplier) {
        this.supplier = supplier;
    }

    public Integer getLotSize() {
        return lotSize;
    }

    public void setLotSize(Integer lotSize) {
        this.lotSize = lotSize;
    }

    public Integer getLeadTime() {
        return leadTime;
    }

  
    public void setLeadTime(Integer leadTime) {
        this.leadTime = leadTime;
    }

   
    public Double getPrice() {
        return price;
    }

   
    public void setPrice(Double price) {
        this.price = price;
    }

}
