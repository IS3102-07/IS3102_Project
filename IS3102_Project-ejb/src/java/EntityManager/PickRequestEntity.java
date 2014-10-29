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
import javax.persistence.TemporalType;

@Entity
public class PickRequestEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private PickerEntity picker;
    @OneToOne
    private SalesRecordEntity salesRecord;
    private String queueNo;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSubmitted;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCompleted;
    @OneToOne
    private StoreEntity store;

    public PickerEntity getPicker() {
        return picker;
    }

    public void setPicker(PickerEntity picker) {
        this.picker = picker;
    }

    public SalesRecordEntity getSalesRecord() {
        return salesRecord;
    }

    public void setSalesRecord(SalesRecordEntity salesRecord) {
        this.salesRecord = salesRecord;
    }

    public String getQueueNo() {
        return queueNo;
    }

    public void setQueueNo(String queueNo) {
        this.queueNo = queueNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PickRequestEntity)) {
            return false;
        }
        PickRequestEntity other = (PickRequestEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }    
}
