package EntityManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public class ItemEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String internalItemCode;
    private String description;
    private String imageURL;
    @OneToMany(cascade={CascadeType.ALL})
    private Collection<ItemCountryEntity> itemCountryList = new ArrayList<ItemCountryEntity>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void create(String name, String materialID, String description, String imageURL) {
        this.name = name; //TODO: not inside class diagram yet
        this.setInternalItemCode(materialID);
        this.description = description;
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
        if (!(object instanceof ItemEntity)) {
            return false;
        }
        ItemEntity other = (ItemEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "em.ItemEntity[ id=" + id + " ]";
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<ItemCountryEntity> getItemCountryList() {
        return itemCountryList;
    }

    public void setItemCountryList(Collection<ItemCountryEntity> itemCountry) {
        this.itemCountryList = itemCountry;
    }

    /**
     * @return the internalItemCode
     */
    public String getInternalItemCode() {
        return internalItemCode;
    }

    /**
     * @param internalItemCode the internalItemCode to set
     */
    public void setInternalItemCode(String internalItemCode) {
        this.internalItemCode = internalItemCode;
    }
}
