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
public class WarehouseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "warehouse")
    private List<StorageBinEntity> storageBin;
    @OneToOne (mappedBy = "destination")
    private PurchaseOrderEntity purchaseOrder;
    @OneToMany
    private List<ItemEntity> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<StorageBinEntity> getStorageBin() {
        return storageBin;
    }

    public void setStorageBin(List<StorageBinEntity> storageBin) {
        this.storageBin = storageBin;
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
        if (!(object instanceof WarehouseEntity)) {
            return false;
        }
        WarehouseEntity other = (WarehouseEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityManager.WarehouseEntity[ id=" + id + " ]";
    }

    /**
     * @return the purchaseOrder
     */
    public PurchaseOrderEntity getPurchaseOrder() {
        return purchaseOrder;
    }

    /**
     * @param purchaseOrder the purchaseOrder to set
     */
    public void setPurchaseOrder(PurchaseOrderEntity purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    /**
     * @return the items
     */
    public List<ItemEntity> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

}
