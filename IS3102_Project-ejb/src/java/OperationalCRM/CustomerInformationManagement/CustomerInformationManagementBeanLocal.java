/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OperationalCRM.CustomerInformationManagement;

import EntityManager.ItemEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Jason
 */
@Local
public interface CustomerInformationManagementBeanLocal {
    public Boolean addFurnitureToList(String sku, Long memberId);
    public Boolean addEmailToSubscription(String email);
    public List <ItemEntity> shoppingList(String email);
}
