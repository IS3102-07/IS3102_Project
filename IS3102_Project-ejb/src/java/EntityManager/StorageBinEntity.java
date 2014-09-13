package EntityManager;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class StorageBinEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private Integer _length;
    private Integer width;
    private Integer height;
    private Integer volume;
    private Integer freeVolume;
    @OneToMany
    private List<ItemEntity> items;

    public StorageBinEntity() {
        
    }

    public StorageBinEntity(String type, Integer _length, Integer width, Integer height) {
        this.type = type;
        this._length = _length;
        this.width = width;
        this.height = height;
        this.volume = _length * width * height;
        this.freeVolume = volume;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLength() {
        return _length;
    }

    public void setLength(Integer length) {
        this._length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getVolume() {
        return volume;
    }

    public Integer getFreeVolume() {
        return freeVolume;
    }

    public void setFreeVolume(Integer freeVolume) {
        this.freeVolume = freeVolume;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
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
        if (!(object instanceof StorageBinEntity)) {
            return false;
        }
        StorageBinEntity other = (StorageBinEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityManager.StorageBinEntity[ id=" + id + " ]";
    }

}
