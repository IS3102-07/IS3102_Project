package EntityManager;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SalesFigureEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private MonthScheduleEntity schedule;    
    private Integer quantity;
    @ManyToOne
    private StoreEntity store;    
    @ManyToOne
    private ProductGroupEntity productGroup;
    @ManyToOne
    private MenuItemEntity menuItem;
    
    public SalesFigureEntity(){}
    
    
    public MonthScheduleEntity getSchedule() {
        return schedule;
    }

    public void setSchedule(MonthScheduleEntity schedule) {
        this.schedule = schedule;
    }        
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof SalesFigureEntity)) {
            return false;
        }
        SalesFigureEntity other = (SalesFigureEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityManager.SalesFigureEntity[ id=" + id + " ]";
    }    

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the store
     */
    public StoreEntity getStore() {
        return store;
    }

    /**
     * @param store the store to set
     */
    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public ProductGroupEntity getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroupEntity productGroup) {
        this.productGroup = productGroup;
    }
        
}
