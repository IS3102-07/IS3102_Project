package EntityManager;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class SupplierEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    private String supplierName;
    @Lob
    private String contactNo;
    @Lob
    private String email;
    @Lob
    private String address;
    private Boolean isDeleted;
    @ManyToOne
    private CountryEntity country;
    @OneToMany (mappedBy="supplier")
    private List<RawMaterialEntity> rawMaterials;
    //@OneToMany (mappedBy="supplier")
    //private List<RetailProductEntity> retailProducts;
    @OneToMany(mappedBy="supplier")
    private List<PurchaseOrderEntity> purchaseOrders;
            
    public SupplierEntity() {

    }

    public SupplierEntity(String supplierName, String contactNo, String email, String address) {
        this.supplierName = supplierName;
        this.contactNo = contactNo;
        this.email = email;
        this.address = address;
        this.isDeleted = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PurchaseOrderEntity> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(List<PurchaseOrderEntity> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }


    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }


    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
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

 
    public List<RawMaterialEntity> getRawMaterials() {
        return rawMaterials;
    }
  
    
    public void setRawMaterials(List<RawMaterialEntity> rawMaterials) {
        this.rawMaterials = rawMaterials;
    }

   
    //public List<RetailProductEntity> getRetailProducts() {
      //  return retailProducts;
   // }

  
    //public void setRetailProducts(List<RetailProductEntity> retailProducts) {
      //  this.retailProducts = retailProducts;
    //}

}
