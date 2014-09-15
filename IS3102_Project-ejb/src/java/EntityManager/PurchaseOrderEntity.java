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
import javax.persistence.ManyToOne;
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
    private SupplierEntity supplier;    
    @OneToMany(mappedBy="purchaseOrder",cascade={CascadeType.ALL})
    private List<LineItemEntity> lineItems;                
    @ManyToOne
    private WarehouseEntity receivedWarehouse;  
    @Temporal(value = TemporalType.DATE)
    private Date expectedReceivedDate;
    
    private String status;
    
    public PurchaseOrderEntity() {}

    public PurchaseOrderEntity(SupplierEntity supplier, WarehouseEntity destination, Date expectedReceivedDate) {
        this.supplier = supplier;
        this.receivedWarehouse = destination;
        this.expectedReceivedDate = expectedReceivedDate;
        this.setStatus("Pending");
    }        
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }   

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public SupplierEntity getSupplier(){
        return supplier;
    }
    public void setSupplier(SupplierEntity origin){
        this.supplier = origin;
    }
    public List<LineItemEntity> getLineItems(){
        return lineItems;
    }
    public void setLineItems(List<LineItemEntity> lineItems){
        this.lineItems = lineItems;
    }    

    public Date getExpectedReceivedDate() {
        return expectedReceivedDate;
    }

    public void setExpectedReceivedDate(Date expectedReceivedDate) {
        this.expectedReceivedDate = expectedReceivedDate;
    }

    public WarehouseEntity getReceivedWarehouse() {
        return receivedWarehouse;
    }

    public void setReceivedWarehouse(WarehouseEntity destination) {
        this.receivedWarehouse = destination;
    }
    
}

    
