package EntityManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class WarehouseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String warehouseName;
    private String address;
    private String email;
    private String telephone;
    @OneToMany(mappedBy = "warehouse")
    private List<StorageBinEntity> storageBins; 
    @OneToMany(mappedBy = "warehouses")
    private List<ItemEntity> items;
    @OneToOne(mappedBy="warehouse")
    private StoreEntity store;
    @OneToOne(mappedBy="warehouse")
    private ManufacturingFacilityEntity manufaturingFacility;
    @OneToMany(mappedBy="receivedWarehouse")
    List<PurchaseOrderEntity> purchaseOrderEntityList;    
    
    public WarehouseEntity(){
        this.storageBins = new ArrayList<>();
        this.items = new ArrayList<>();
        this.purchaseOrderEntityList = new ArrayList<>();
    }    

    public WarehouseEntity(String warehouseName, String address, String telephone, String email) {
        this.warehouseName = warehouseName;
        this.address = address;
        this.email = email;
        this.telephone = telephone;
        this.storageBins = new ArrayList<>();
        this.items = new ArrayList<>();
        this.purchaseOrderEntityList = new ArrayList<>();
    }                
    
    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }        

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<StorageBinEntity> getStorageBins() {
        return storageBins;
    }

    public void setStorageBins(List<StorageBinEntity> storageBins) {
        this.storageBins = storageBins;
    }            
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<StorageBinEntity> getStorageBin() {
        return storageBins;
    }

    public void setStorageBin(List<StorageBinEntity> storageBin) {
        this.storageBins = storageBin;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public ManufacturingFacilityEntity getManufaturingFacility() {
        return manufaturingFacility;
    }

    public void setManufaturingFacility(ManufacturingFacilityEntity manufaturingFacility) {
        this.manufaturingFacility = manufaturingFacility;
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
        if (!(object instanceof WarehouseEntity)) {
            return false;
        }
        WarehouseEntity other = (WarehouseEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityManager.WarehouseEntity[ id=" + id + " ]";
    }

    

    /**
     * @return the items
     */
    public List<ItemEntity> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

}
