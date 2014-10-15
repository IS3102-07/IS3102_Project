package EntityManager;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.Entity;

@Entity
public class ShoppingListEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToMany
    private List<ItemEntity> items;
    
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
