package EntityManager;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class FurnitureEntity extends ItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    private String name;
    @Lob
    private String category;
    @Lob
    private String description;
    @Lob
    private String imageURL;
    @OneToOne(cascade = {CascadeType.REMOVE}, mappedBy = "furniture")
    private ProductGroupLineItemEntity productGroupLineItemEntity;
    @OneToOne(cascade = {CascadeType.ALL}, mappedBy = "furniture")
    public BillOfMaterialEntity BOM;

    public FurnitureEntity() {
    }

    public FurnitureEntity(String SKU, String name, String category, String description, String imageURL, Integer _length, Integer width, Integer height) {
        super(SKU, _length, width, height);
        this.name = name;
        super.setName(name);
        this.category = category;
        this.description = description;
        this.imageURL = imageURL;
        super.setType("Furniture");
    }

    public ProductGroupLineItemEntity getProductGroupLineItemEntity() {
        return productGroupLineItemEntity;
    }

    public void setProductGroupLineItemEntity(ProductGroupLineItemEntity productGroupLineItemEntity) {
        this.productGroupLineItemEntity = productGroupLineItemEntity;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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
        if (!(object instanceof FurnitureEntity)) {
            return false;
        }
        FurnitureEntity other = (FurnitureEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityManagerBean.Furniture[ id=" + id + " ]";
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

    public BillOfMaterialEntity getBOM() {
        return BOM;
    }

    public void setBOM(BillOfMaterialEntity BOM) {
        this.BOM = BOM;
    }

}
