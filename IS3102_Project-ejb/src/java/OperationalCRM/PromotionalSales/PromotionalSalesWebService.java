package OperationalCRM.PromotionalSales;

import EntityManager.StoreEntity;
import java.util.Date;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@WebService(serviceName = "PromotionalSalesWebService")
@Stateless()
public class PromotionalSalesWebService {

    @EJB
    PromotionalSalesBeanLocal psbl;
    
    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;
    
    @WebMethod
    public Double getPromotionRate(@WebParam(name = "SKU")String sku, @WebParam(name = "storeID")Long storeID, @WebParam(name = "date")Date date){
        try {
            StoreEntity storeEntity = em.getReference(StoreEntity.class, storeID);
            Long countryId = storeEntity.getCountry().getId();
            return psbl.getPromotionRate(sku, countryId, date);
        } catch (Exception ex) {
            System.out.println("getPromotionRate(): Error");
            ex.printStackTrace();
            return 0.0;
        }
    }
}
