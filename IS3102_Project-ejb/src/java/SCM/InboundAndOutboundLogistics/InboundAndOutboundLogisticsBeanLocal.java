/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SCM.InboundAndOutboundLogistics;

import EntityManager.ItemEntity;
import EntityManager.WarehouseEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface InboundAndOutboundLogisticsBeanLocal {
    public Long createShippingOrder(ItemEntity item, Integer quantity, Date shippedDate, Date expectedReceivedDate, WarehouseEntity origin, 
            WarehouseEntity destination);    
    public List<ItemEntity> getShippingOrderList(ItemEntity item, Date shippedDate, Date expectedReceivedDate, WarehouseEntity origin, 
            WarehouseEntity destination);
    public ItemEntity getShippingOrderById(Long id);
    public Boolean updateShippingOrder(Long id, String status);     
}
