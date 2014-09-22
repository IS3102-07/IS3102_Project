/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EntityManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class ShippingOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String shippingType;    
    private String status;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date shippedDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expectedReceivedDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date receivedDate;
    @OneToMany(mappedBy="shippingOrder",cascade={CascadeType.ALL})
    private List<LineItemEntity> lineItems; 
    @ManyToOne
    private WarehouseEntity origin;
    @ManyToOne
    private WarehouseEntity destination;
    
    public ShippingOrderEntity(){
        this.lineItems = new ArrayList<>();
    }

    public ShippingOrderEntity(String ShippingType, Date shippedDate, Date expectedReceivedDate, WarehouseEntity origin, WarehouseEntity destination) {
        this.shippingType = ShippingType;
        this.createdDate = new Date();
        this.shippedDate = shippedDate;
        this.expectedReceivedDate = expectedReceivedDate;
        this.lineItems = new ArrayList<>();
        this.origin = origin;
        this.destination = destination;
        this.status = "Pending";
    }        
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String ShippingType) {
        this.shippingType = ShippingType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Date getExpectedReceivedDate() {
        return expectedReceivedDate;
    }

    public void setExpectedReceivedDate(Date expectedReceivedDate) {
        this.expectedReceivedDate = expectedReceivedDate;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public List<LineItemEntity> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItemEntity> lineItems) {
        this.lineItems = lineItems;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public WarehouseEntity getOrigin() {
        return origin;
    }

    public void setOrigin(WarehouseEntity origin) {
        this.origin = origin;
    }

    public WarehouseEntity getDestination() {
        return destination;
    }

    public void setDestination(WarehouseEntity destination) {
        this.destination = destination;
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
        if (!(object instanceof ShippingOrderEntity)) {
            return false;
        }
        ShippingOrderEntity other = (ShippingOrderEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityManager.ShippingOrderEntity[ id=" + id + " ]";
    }
    
}
