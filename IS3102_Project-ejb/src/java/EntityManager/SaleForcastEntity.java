/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityManager;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Administrator
 */
@Entity
public class SaleForcastEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date month;
    private Integer quantity;
    @ManyToOne
    private StoreEntity store;
    @ManyToOne
    private ProductGroupEntity productGroup;
    
    public SaleForcastEntity(){}

    public SaleForcastEntity(Date month, Integer quantity, StoreEntity store, ProductGroupEntity productGroup) {
        this.month = month;
        this.quantity = quantity;
        this.store = store;
        this.productGroup = productGroup;
    }        
    
    public Date getMonth() {
        return month;
    }

    public void setMonth(Date month) {
        this.month = month;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public ProductGroupEntity getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroupEntity productGroup) {
        this.productGroup = productGroup;
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
        if (!(object instanceof SaleForcastEntity)) {
            return false;
        }
        SaleForcastEntity other = (SaleForcastEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityManager.SaleForcastEntity[ id=" + id + " ]";
    }
    
}
