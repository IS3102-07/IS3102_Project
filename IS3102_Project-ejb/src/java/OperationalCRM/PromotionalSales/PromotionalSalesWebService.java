package OperationalCRM.PromotionalSales;

import java.util.Date;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

@WebService(serviceName = "PromotionalSalesWebService")
@Stateless()
public class PromotionalSalesWebService {

    @EJB
    PromotionalSalesBeanLocal psbl;
    
    @WebMethod
    public Double getPromotionRate(@WebParam(name = "SKU")String sku, @WebParam(name = "countryID")Long countryId, @WebParam(name = "date")Date date){
        return psbl.getPromotionRate(sku, countryId, date);
    }
}
