
package SCM.SupplierManagement;

import EntityManager.SupplierEntity;
import java.util.List;
import javax.ejb.Local;


@Local
public interface SupplierManagementBeanLocal {
    public Long addSupplier(String supplierName, Integer contactNo, String email, String address);
    public boolean deleteSupplier(Long id);
    public boolean editSupplier(SupplierEntity supplier);
    public List<SupplierEntity> viewAllSupplierList();
    public List<SupplierEntity> viewActiveSupplierList();
    public List<SupplierEntity> viewInactiveSupplierList();
    public boolean markSupplierAsActive(Long id);
    public boolean markSupplierAsInActive(Long id);
}
