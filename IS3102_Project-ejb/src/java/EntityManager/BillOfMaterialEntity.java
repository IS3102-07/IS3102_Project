package EntityManager;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class BillOfMaterialEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    
    @OneToOne
    FurnitureEntity furniture;
    @OneToMany
    List<LineItemEntity> listOfLineItems;

    public BillOfMaterialEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FurnitureEntity getFurniture() {
        return furniture;
    }

    public void setFurniture(FurnitureEntity furniture) {
        this.furniture = furniture;
    }

    public List<LineItemEntity> getListOfLineItems() {
        return listOfLineItems;
    }

    public void setListOfLineItems(List<LineItemEntity> listOfLineItems) {
        this.listOfLineItems = listOfLineItems;
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
        if (!(object instanceof BillOfMaterialEntity)) {
            return false;
        }
        BillOfMaterialEntity other = (BillOfMaterialEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityManagerBean.BillOfMaterial[ id=" + id + " ]";
    }

}
