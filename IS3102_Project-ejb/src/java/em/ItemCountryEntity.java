/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package em;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author -VeRyLuNaTiC
 */
@Entity
public class ItemCountryEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer retailPrice;
    @ManyToOne(cascade={CascadeType.ALL})
    private ItemEntity item;
    @ManyToOne(cascade={CascadeType.ALL})
    private CountryEntity country;

    public void create(CountryEntity country, Integer retailPrice){
        this.country = country;
        this.retailPrice = retailPrice;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Integer retailPrice) {
        this.retailPrice = retailPrice;
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
        if (!(object instanceof ItemCountryEntity)) {
            return false;
        }
        ItemCountryEntity other = (ItemCountryEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "em.ItemPriceEntity[ id=" + id + " ]";
    }

    /**
     * @return the item
     */
    public ItemEntity getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(ItemEntity item) {
        this.item = item;
    }

    /**
     * @return the country
     */
    public CountryEntity getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(CountryEntity country) {
        this.country = country;
    }
    
}
