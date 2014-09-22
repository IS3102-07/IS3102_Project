/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EntityManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author yang
 */
@Entity
public class RegionalOfficeEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String telephone;
    private String email;
    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy="regionalOffice")
    List<WarehouseEntity> warehouseList;
    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy="regionalOffice")
    List<StoreEntity> storeList;
    @OneToMany(cascade={CascadeType.REMOVE}, mappedBy="regionalOffice")
    List<ManufacturingFacilityEntity> manufacturingFacilityEntityList;
    
    public RegionalOfficeEntity(){
        this.storeList = new ArrayList<>();        
        this.manufacturingFacilityEntityList = new ArrayList<>();
        this.warehouseList = new ArrayList<>();
    }        
    
    public void create(String name, String address, String telephone, String email) {
        this.setName(name);
        this.setAddress(address);
        this.setTelephone(telephone);
        this.setEmail(email);
    }        

    public List<WarehouseEntity> getWarehouseList() {
        return warehouseList;
    }

    public void setWarehouseList(List<WarehouseEntity> warehouseList) {
        this.warehouseList = warehouseList;
    }

    public List<StoreEntity> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<StoreEntity> storeList) {
        this.storeList = storeList;
    }

    public List<ManufacturingFacilityEntity> getManufacturingFacilityEntityList() {
        return manufacturingFacilityEntityList;
    }

    public void setManufacturingFacilityEntityList(List<ManufacturingFacilityEntity> manufacturingFacilityEntityList) {
        this.manufacturingFacilityEntityList = manufacturingFacilityEntityList;
    }        
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getTelephone() {
        return address;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
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
        if (!(object instanceof RegionalOfficeEntity)) {
            return false;
        }
        RegionalOfficeEntity other = (RegionalOfficeEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityManager.RegionalOfficeEntity[ id=" + id + " ]";
    }
    
}
