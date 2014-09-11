
package SCM.SupplierManagement;

import EntityManager.SupplierEntity;
import java.util.List;
import javax.ejb.Local;


@Local
public interface SupplierManagementBeanLocal {
    public Long addSupplier(String supplierName, Integer contactNo, String email, String address);
    public boolean deleteSupplier(Long id);//if supplier exists call em.remove(supplier) else returns false
    public boolean editSupplier(SupplierEntity supplier);//merge the SupplierEntity if exists else returns false
    public SupplierEntity getSupplier(Long id);//returns a SupplierEntity else returns null
    public List<SupplierEntity> viewAllSupplierList();
    public List<SupplierEntity> viewActiveSupplierList();
    public List<SupplierEntity> viewInactiveSupplierList();
    public boolean markSupplierAsActive(Long id);//returns false if supplier not found
    public boolean markSupplierAsInActive(Long id);//returns false if supplier not found
}
