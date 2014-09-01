
package HelperClass;

import java.io.Serializable;

public class CountryData implements Serializable {

    private Long id;
    private String name;
    private String currency;
    private Double exchangeRate;
    
    public void create(Long id, String name, String currency, Double exchangeRate) {
        this.setId(id);
        this.setName(name);
        this.setCurrency(currency);
        this.setExchangeRate(exchangeRate);
    }    

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void create(String name, String currency) {
        this.name = name;
        this.setCurrency(currency);
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CountryData)) {
            return false;
        }
        CountryData other = (CountryData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
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
    
}
