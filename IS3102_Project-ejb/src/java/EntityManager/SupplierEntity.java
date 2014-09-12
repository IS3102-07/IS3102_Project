package EntityManager;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class SupplierEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String supplierName;
    private Integer contactNo;
    private String email;
    private String address;
    private boolean isActive;
    @ManyToOne
    private CountryEntity country;
    @OneToMany(mappedBy="supplier", cascade={CascadeType.ALL})
    private List<Supplier_RawMaterialEntity> listOfSupplier_RawMaterialInfo;
    @OneToMany(mappedBy="supplier", cascade={CascadeType.ALL})
    private List<Supplier_RetailProductEntity> listOfSupplier_RetailProductInfo;

    public SupplierEntity() {

    }

    public SupplierEntity(String supplierName, Integer contactNo, String email, String address) {
        this.supplierName = supplierName;
        this.contactNo = contactNo;
        this.email = email;
        this.address = address;
        this.isActive = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getContactNo() {
        return contactNo;
    }

    public void setContactNo(Integer contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public List<Supplier_RawMaterialEntity> getListOfSupplier_RawMaterialInfo() {
        return listOfSupplier_RawMaterialInfo;
    }

    public void setListOfSupplier_RawMaterialInfo(List<Supplier_RawMaterialEntity> listOfSupplier_RawMaterialInfo) {
        this.listOfSupplier_RawMaterialInfo = listOfSupplier_RawMaterialInfo;
    }

    public List<Supplier_RetailProductEntity> getListOfSupplier_RetailProductInfo() {
        return listOfSupplier_RetailProductInfo;
    }

    public void setListOfSupplier_RetailProductInfo(List<Supplier_RetailProductEntity> listOfSupplier_RetailProductInfo) {
        this.listOfSupplier_RetailProductInfo = listOfSupplier_RetailProductInfo;
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
        if (!(object instanceof SupplierEntity)) {
            return false;
        }
        SupplierEntity other = (SupplierEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityManager.SupplierEntity[ id=" + id + " ]";
    }

}
