/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StoreTransaction.SalesReporting;

import EntityManager.LineItemEntity;
import HelperClasses.ReturnHelper;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author -VeRyLuNaTiC
 */
@Local
public interface SalesReportingBeanLocal {
    public ReturnHelper submitSalesRecord(String email, String password, Long storeID, String posName, List<LineItemEntity> itemsPurchased);
}
