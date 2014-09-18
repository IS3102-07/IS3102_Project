/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityManager;

import java.io.Serializable;
import java.util.Calendar;
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
public class SaleAndOperationPlanEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    
    @OneToOne
    private SaleForcastEntity saleForcast;
    @ManyToOne
    private StoreEntity store;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar month;
    private int year;
    private Integer productionPlan;
    private Integer currentInventoryLevel;
    private Integer targetInventoryLevel;
    
    public SaleAndOperationPlanEntity(){}

    public SaleAndOperationPlanEntity(SaleForcastEntity saleForcast, StoreEntity store, Calendar month, Integer productionPlan, Integer currentInventoryLevel, Integer targetInventoryLevel) {
        this.saleForcast = saleForcast;
        this.store = store;
        this.month = month;
        this.year = month.get(Calendar.YEAR);
        this.productionPlan = productionPlan;
        this.currentInventoryLevel = currentInventoryLevel;
        this.targetInventoryLevel = targetInventoryLevel;
    }        

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }        
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getMonth() {
        return month;
    }

    public void setMonth(Calendar month) {
        this.month = month;
        this.year = month.get(Calendar.YEAR);
    }        
    
    public int getYear(){
        return this.year;
    }

    public SaleForcastEntity getSaleForcast() {
        return saleForcast;
    }

    public void setSaleForcast(SaleForcastEntity saleForcast) {
        this.saleForcast = saleForcast;
    }

    public Integer getProductionPlan() {
        return productionPlan;
    }

    public void setProductionPlan(Integer productionPlan) {
        this.productionPlan = productionPlan;
    }

    public Integer getCurrentInventoryLevel() {
        return currentInventoryLevel;
    }

    public void setCurrentInventoryLevel(Integer currentInventoryLevel) {
        this.currentInventoryLevel = currentInventoryLevel;
    }

    public Integer getTargetInventoryLevel() {
        return targetInventoryLevel;
    }

    public void setTargetInventoryLevel(Integer targetInventoryLevel) {
        this.targetInventoryLevel = targetInventoryLevel;
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
        if (!(object instanceof SaleAndOperationPlanEntity)) {
            return false;
        }
        SaleAndOperationPlanEntity other = (SaleAndOperationPlanEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityManager.SaleAndOperationPlanEntity[ id=" + id + " ]";
    }
    
}
