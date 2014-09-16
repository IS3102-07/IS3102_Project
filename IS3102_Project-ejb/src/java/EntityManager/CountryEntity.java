
package EntityManager;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CountryEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String currency;
    private Double exchangeRate;
    private Integer countryCode;
    @OneToMany(mappedBy="country")
    private List<SupplierEntity> suppliers;
    @OneToMany(mappedBy="country")
    private List<WarehouseEntity> warehouses;

    public List<SupplierEntity> getSupplier() {
        return suppliers;
    }

    public void setSupplier(List<SupplierEntity> supplier) {
        this.suppliers = supplier;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void create(Long id, String name, String currency, Double exchangeRate){
        this.setId(id);
        this.setName(name);
        this.setCurrency(currency);
        this.setExchangeRate(exchangeRate);
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
        if (!(object instanceof CountryEntity)) {
            return false;
        }
        CountryEntity other = (CountryEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "em.CountryEntity[ id=" + id + " ]";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return the exchangeRate
     */
    public Double getExchangeRate() {
        return exchangeRate;
    }

    /**
     * @param exchangeRate the exchangeRate to set
     */
    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public List<SupplierEntity> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierEntity> suppliers) {
        this.suppliers = suppliers;
    }

    public List<WarehouseEntity> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseEntity> warehouses) {
        this.warehouses = warehouses;
    }
    
}
