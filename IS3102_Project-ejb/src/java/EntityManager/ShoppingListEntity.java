/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EntityManager;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.ManyToMany;
/**
 *
 * @author yang
 */
public class ShoppingListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private MemberEntity member;
    
    @ManyToMany(cascade={CascadeType.ALL})
    private List<FurnitureEntity> furnitures;
    
    @ManyToMany(cascade={CascadeType.ALL})
    private List<RetailProductEntity> retailProducts;
    
    public void create() {
        
    }
    
    public void setMember(MemberEntity member) {
        this.member = member;
    }
    
    public MemberEntity getMember() {
        return member;
    }
    
    public void setFurnitures(List<FurnitureEntity> furnitures) {
        this.furnitures = furnitures;        
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
