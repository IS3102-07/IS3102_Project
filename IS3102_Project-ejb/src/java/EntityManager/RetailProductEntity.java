package EntityManager;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class RetailProductEntity extends ItemEntity implements Serializable {

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
    private Integer lotSize;
    private Integer leadTime;

    public RetailProductEntity() {
    }

    public RetailProductEntity(String SKU, String name, String category, String description, String imageURL, Integer _length, Integer width, Integer height, Integer lotSize, Integer leadTime, Double price) {
        super(SKU, _length, width, height, price);
        this.name = name;
        super.setName(name);
        this.category = category;
        this.description = description;
        this.imageURL = imageURL;
        this.lotSize = lotSize;
        this.leadTime = leadTime;
        super.setType("Retail Product");
        super.setIsDeleted(false);
    }

    public Boolean getIsDeleted() {
        return super.getIsDeleted();
    }

    public void setIsDeleted(Boolean isDeleted) {
        super.setIsDeleted(isDeleted);
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
        if (!(object instanceof RetailProductEntity)) {
            return false;
        }
        RetailProductEntity other = (RetailProductEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityManagerBean.RetailProduct[ id=" + id + " ]";
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

    /**
     * @return the imageURL
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * @param imageURL the imageURL to set
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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
     * @return the lotSize
     */
    public Integer getLotSize() {
        return lotSize;
    }

    /**
     * @param lotSize the lotSize to set
     */
    public void setLotSize(Integer lotSize) {
        this.lotSize = lotSize;
    }

    /**
     * @return the leadTime
     */
    public Integer getLeadTime() {
        return leadTime;
    }

    /**
     * @param leadTime the leadTime to set
     */
    public void setLeadTime(Integer leadTime) {
        this.leadTime = leadTime;
    }

}
