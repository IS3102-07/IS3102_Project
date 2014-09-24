/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class StorageBin_ItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany
    private List<ItemEntity> item;
    @ManyToMany
    private List<StorageBinEntity> storageBinWithThisItem;
    private Integer quantity;

    public StorageBin_ItemEntity() {
    }

    public StorageBin_ItemEntity(ItemEntity itemEntity, StorageBinEntity storageBinEntity, Integer quantity) {
        this.item = new ArrayList();
        item.add(itemEntity);
        this.storageBinWithThisItem = new ArrayList();
        storageBinWithThisItem.add(storageBinEntity);
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemEntity> getItem() {
        return item;
    }

    public void setItem(List<ItemEntity> item) {
        this.item = item;
    }

    public List<StorageBinEntity> getStorageBinWithThisItem() {
        return storageBinWithThisItem;
    }

    public void setStorageBinWithThisItem(List<StorageBinEntity> storageBinWithThisItem) {
        this.storageBinWithThisItem = storageBinWithThisItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
        if (!(object instanceof StorageBin_ItemEntity)) {
            return false;
        }
        StorageBin_ItemEntity other = (StorageBin_ItemEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
