package EntityManager;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Xiaodong
 */
@Entity
public class PurchaseOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "purchaseOrder")
    private SupplierEntity origin;
    @OneToMany(mappedBy="purchaseOrder",cascade={CascadeType.ALL})
    private List<LineItemEntity> lineItems;
    
    @Temporal(value = TemporalType.DATE)
    private Date shippedDate;
    @Temporal(value = TemporalType.DATE)
    private Date expectedReceivedDate;
    private Time deliveryTime;
    @OneToOne
    private WarehouseEntity destination;
    private String contactName;
    private Integer contactNumber;
    private String status;
    
    public PurchaseOrderEntity() {}
    
    public PurchaseOrderEntity(Date shippedDate, Date expectedReceivedDate, Time deliveryTime, SupplierEntity origin, WarehouseEntity destination, String contactName, Integer contactNumber, String status){
        this.shippedDate = shippedDate;
        this.expectedReceivedDate = expectedReceivedDate;
        this.deliveryTime = deliveryTime;
        this.origin = origin;
        this.destination = destination;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.status = status;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public Integer getContactNumber() {
        return contactNumber;
    }
    
    public void setContactNumber(Integer contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Time getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Time deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public SupplierEntity getSupplier(){
        return origin;
    }
    public void setSupplier(SupplierEntity origin){
        this.origin = origin;
    }
    public List<LineItemEntity> getLineItems(){
        return lineItems;
    }
    public void setLineItems(List<LineItemEntity> lineItems){
        this.lineItems = lineItems;
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

    public WarehouseEntity getDestination() {
        return destination;
    }

    public void setDestination(WarehouseEntity destination) {
        this.destination = destination;
    }

    public SupplierEntity getOrigin() {
        return origin;
    }

    public void setOrigin(SupplierEntity origin) {
        this.origin = origin;
    }
}

    
