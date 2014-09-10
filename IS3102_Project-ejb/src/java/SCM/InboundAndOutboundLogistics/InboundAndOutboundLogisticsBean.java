/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SCM.InboundAndOutboundLogistics;

import EntityManager.ItemEntity;
import EntityManager.LineItemEntity;
import EntityManager.ShippingOrderEntity;
import EntityManager.ManufacturingFacilityEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class InboundAndOutboundLogisticsBean implements InboundAndOutboundLogisticsBeanLocal {
    
    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;        

    @Override
    public Long createShippingOrder(List<LineItemEntity> lineItemList, Date shippedDate, Date expectedReceivedDate, ManufacturingFacilityEntity origin, ManufacturingFacilityEntity destination) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //ShippingOrderEntity shippingOrder = new ShippingOrderEntity();
        //shippingOrder.setShippedDate(shippedDate);
        //shippingOrder.setExpectedReceivedDate(expectedReceivedDate);        
    }

    @Override
    public List<ItemEntity> getShippingOrderList(ItemEntity item, Date shippedDate, Date expectedReceivedDate, ManufacturingFacilityEntity origin, ManufacturingFacilityEntity destination) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ItemEntity getShippingOrderById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean updateShippingOrder(Long id, String status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void persist(Object object) {
        em.persist(object);
    }

    
}
