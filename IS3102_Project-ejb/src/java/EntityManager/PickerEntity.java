package EntityManager;

import java.io.Serializable;
import java.util.LinkedList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class PickerEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private StoreEntity store;

    @OneToOne
    private StaffEntity picker;

    @OneToMany(mappedBy = "picker")
    private LinkedList<PickRequestEntity> listOfJob;

    public PickerEntity() {
    }

    public PickerEntity(StaffEntity staff, StoreEntity store) {
        this.picker = staff;
        this.store = store;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public LinkedList<PickRequestEntity> getListOfJob() {
        return listOfJob;
    }

    public void setListOfJob(LinkedList<PickRequestEntity> listOfJob) {
        this.listOfJob = listOfJob;
    }

    public StaffEntity getPicker() {
        return picker;
    }

    public void setPicker(StaffEntity picker) {
        this.picker = picker;
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
        if (!(object instanceof PickerEntity)) {
            return false;
        }
        PickerEntity other = (PickerEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
