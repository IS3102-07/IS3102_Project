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
import javax.persistence.OneToMany;
import javax.persistence.Entity;

@Entity
public class ShoppingListEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToMany(cascade={CascadeType.ALL})
    private List<ItemEntity> items;

    
    public void create() {

    }
    
    public void addItems(ItemEntity item) {
        this.items.add(item);
    }
    
    public Long getId() {
        return id;
    }
    public List<ItemEntity> getItems() {
        return this.items;
    }
    
   
}
