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
    private Integer pickStatus;//1.Queued,2.In-progress,3.Completed
    private Integer collectionStatus;//1.Picking,2.Ready for Collection,3.Uncollected,4.Collected
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSubmitted;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCompleted;
    @OneToOne
    private StoreEntity store;

    public PickRequestEntity() {
    }

    public PickRequestEntity(PickerEntity picker, SalesRecordEntity salesRecord, String queueNo) {
        this.picker = picker;
        this.salesRecord = salesRecord;
        this.queueNo = queueNo;
        this.pickStatus = 1;
        this.collectionStatus = 1;
        this.dateSubmitted = new Date();
    }

    public String getPickStatus() {
        switch (pickStatus) {
            case 1:
                return "Queued";
            case 2:
                return "In-progress";
            case 3:
                return "Completed";
            default:
                return "Unavailable";
        }
    }

    public void setPickStatus(Integer pickStatus) {
        this.pickStatus = pickStatus;
    }

    public String getCollectionStatus() {
        switch (collectionStatus) {
            case 1:
                return "Picking";
            case 2:
                return "Ready for Collection";
            case 3:
                return "Uncollected";
            case 4:
                return "Collected";
            default:
                return "Unavailable";
        }
    }

    public void setCollectionStatus(Integer collectionStatus) {
        this.collectionStatus = collectionStatus;
    }

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
