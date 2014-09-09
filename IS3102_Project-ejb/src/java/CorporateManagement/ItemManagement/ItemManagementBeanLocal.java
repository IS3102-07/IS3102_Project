/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CorporateManagement.ItemManagement;

import EntityManager.ItemEntity;
import javax.ejb.Local;

/**
 *
 * @author Loi Liang Yang
 */
@Local
public interface ItemManagementBeanLocal {
    public boolean addItem(String name, String materialID, String description, String imageURL);
    public boolean editItem(String name, String materialID, String description, String imageURL);
    public boolean removeItem(String itemName);
    public ItemEntity viewItem(String itemName);
}
