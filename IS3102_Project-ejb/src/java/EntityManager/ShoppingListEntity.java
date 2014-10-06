/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EntityManager;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
import javax.persistence.Entity;

@Entity
public class ShoppingListEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToMany(cascade={CascadeType.ALL})
    private List<FurnitureEntity> furnitures;
    
    @ManyToMany(cascade={CascadeType.ALL})
    private List<RetailProductEntity> retailProducts;
    
    public void create() {

    }
    

    
    public void setFurnitures(List<FurnitureEntity> furnitures) {
        this.furnitures = furnitures;        
    }
    
    public Long getId() {
        return id;
    }
    public List<FurnitureEntity> getFurnitures() {
        return this.furnitures;
    }
    
    public void setRetailProducts(List<RetailProductEntity> retailProducts) {
        this.retailProducts = retailProducts;        
    }
    
    public List<RetailProductEntity> getRetailProducts() {
        return this.retailProducts;
    }
}
