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
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String internalItemCode;
    @OneToMany(cascade = {CascadeType.ALL})
    private Collection<Item_CountryEntity> itemCountryList;
    @OneToOne
    private FurnitureEntity furniture;
    @OneToOne
    private RawMaterialEntity rawMaterial;
    @OneToOne
    private RawMaterialEntity retailProduct;
    

    public ItemEntity(){
        this.itemCountryList = new ArrayList<>();
    }

    public ItemEntity(String internalItemCode) {
        this.internalItemCode = internalItemCode;
        this.itemCountryList = new ArrayList<>();
    }
            
    public int getEntity(){
        if(furniture != null)
            return 1;
        else if(rawMaterial != null)
            return 2;        
        else
            return -1;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Collection<Item_CountryEntity> getItemCountryList() {
        return itemCountryList;
    }

    public void setItemCountryList(Collection<Item_CountryEntity> itemCountry) {
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
