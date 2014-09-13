package EntityManager;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class RetailProductEntity extends ItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy="retailProduct")
    private List<Supplier_RetailProductEntity> listOfSupplier_RetailProductInfo;
    private String name;
    private String category;
    private String description;
    private String imageURL;
    @OneToOne(cascade = {CascadeType.ALL}, mappedBy = "retailProduct")
    private ItemEntity item;

    public RetailProductEntity(){}
    public RetailProductEntity(String name, String category, String description, String imageURL, ItemEntity item){
        this.name = name;
        this.category = category;
        this.description = description;
        this.imageURL = imageURL;
        this.item = item;
    }
    
    public List<Supplier_RetailProductEntity> getListOfSupplier_RetailProductInfo() {
        return listOfSupplier_RetailProductInfo;
    }

    public void setListOfSupplier_RetailProductInfo(List<Supplier_RetailProductEntity> listOfSupplier_RetailProductInfo) {
        this.listOfSupplier_RetailProductInfo = listOfSupplier_RetailProductInfo;
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
     * @return the item
     */
    public ItemEntity getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(ItemEntity item) {
        this.item = item;
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

}
