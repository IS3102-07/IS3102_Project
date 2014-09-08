
package SCM.SupplierManagement;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class SupplierManagementBean implements SupplierManagementBeanLocal {
    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public void addSupplier(){
        
    }
}
