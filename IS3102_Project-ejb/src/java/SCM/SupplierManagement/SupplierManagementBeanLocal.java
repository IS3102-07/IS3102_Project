
package SCM.SupplierManagement;

import EntityManager.CountryEntity;
import EntityManager.SupplierEntity;
import HelperClasses.ItemSupplierHelper;
import java.util.List;
import javax.ejb.Local;


@Local
public interface SupplierManagementBeanLocal {
    public void addSupplier(String supplierName, String contactNo, String email, String address, Long countryId);
    public boolean deleteSupplier(Long id);//if supplier exists call em.remove(supplier) else returns false
    public boolean editSupplier(Long supplierId, String name, String phone, String email, String address, Long countryId);//merge the SupplierEntity if exists else returns false
    public SupplierEntity getSupplier(Long id);//returns a SupplierEntity else returns null
    public List<SupplierEntity> viewAllSupplierList();
    public boolean checkSupplierExists(String supplierName);
    public List<CountryEntity> getListOfCountries();
    public List<ItemSupplierHelper> getSupplierItemList(Long supplierID);
}
