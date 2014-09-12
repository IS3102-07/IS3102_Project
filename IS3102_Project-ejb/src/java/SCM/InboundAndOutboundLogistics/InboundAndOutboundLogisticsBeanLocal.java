/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SCM.InboundAndOutboundLogistics;

import EntityManager.ItemEntity;
import EntityManager.LineItemEntity;
import EntityManager.ShippingOrderEntity;
import EntityManager.WarehouseEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remove;

@Local
public interface InboundAndOutboundLogisticsBeanLocal {
    // shipping tye can be by ship, by truck, by train etc
    public Long createShippingOrderBasicInfo(String ShippingType, Date shippedDate, Date expectedReceivedDate, 
            WarehouseEntity origin, WarehouseEntity destination);    
    
    public Boolean addLineItemToShippingOrder(Long id, LineItemEntity lineItems);        
    
    public List<ShippingOrderEntity> getShippingOrderList(WarehouseEntity origin, Date shippedDate);
    
    public List<ShippingOrderEntity> getShippingOrderList(Date expectedReceivedDate, WarehouseEntity destination);        
    
    // return empty list when is no result or error
    public List<ShippingOrderEntity> getShippingOrderList(ItemEntity item, Date shippedDate, Date expectedReceivedDate, WarehouseEntity origin, 
            WarehouseEntity destination);
    
    public ShippingOrderEntity getShippingOrderById(Long id);
    
    public Boolean updateShippingOrder(Long id, String status);     
    
    @Remove
    public void remove();
}
