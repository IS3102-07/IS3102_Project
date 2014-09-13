package EntityManager;

import static com.sun.javafx.accessible.utils.ControlTypeIds.CALENDAR;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class TransferOrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany
    private List<LineItemEntity> lineItems;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateTransferred;
    private StorageBinEntity origin;
    private StorageBinEntity target;
    
    public TransferOrderEntity() {

    }

    public TransferOrderEntity(List<LineItemEntity> lineItems, StorageBinEntity origin, StorageBinEntity target) {
        this.lineItems = lineItems;
        this.origin = origin;
        this.target = target;
        this.dateTransferred = Calendar.getInstance().getTime();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Calendar cal = Calendar.getInstance();
//        dateFormat.format(cal.getTime());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LineItemEntity> getItems() {
        return lineItems;
    }

    public void setItems(List<LineItemEntity> items) {
        this.lineItems = items;
    }

    public Date getDateTransferred() {
        return dateTransferred;
    }

    public void setDateTransferred(Date dateTransferred) {
        this.dateTransferred = dateTransferred;
    }

    public StorageBinEntity getOrigin() {
        return origin;
    }

    public void setOrigin(StorageBinEntity origin) {
        this.origin = origin;
    }

    public StorageBinEntity getTarget() {
        return target;
    }

    public void setTarget(StorageBinEntity target) {
        this.target = target;
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
        if (!(object instanceof TransferOrderEntity)) {
            return false;
        }
        TransferOrderEntity other = (TransferOrderEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityManager.TransferOrderEntity[ id=" + id + " ]";
    }

}
