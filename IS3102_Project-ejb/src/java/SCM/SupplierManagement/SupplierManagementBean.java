package SCM.SupplierManagement;

import EntityManager.CountryEntity;
import EntityManager.SupplierEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SupplierManagementBean implements SupplierManagementBeanLocal {

    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;
    private SupplierEntity supplier;
    private CountryEntity country;

    public SupplierEntity findASupplier(Long id) {
        return em.find(SupplierEntity.class, id);
    }

    public boolean checkSupplierExists(Long id) {
        return em.find(SupplierEntity.class, id) != null;
    }

    @Override
    public void addSupplier(String supplierName, Integer contactNo, String email, String address, Long countryId) {
        supplier = new SupplierEntity(supplierName, contactNo, email, address);
        country = em.find(CountryEntity.class, countryId);
        supplier.setCountry(country);
        em.persist(supplier);
    }

    @Override
    public boolean deleteSupplier(Long id) {
        if (checkSupplierExists(id)) {
            em.remove(supplier);
            return true;
        }
        return false;
    }

    @Override
    public boolean editSupplier(SupplierEntity supplier) {
        if (checkSupplierExists(supplier.getId())) {
            em.merge(supplier);
            return true;
        }
        return false;
    }

    @Override
    public List<SupplierEntity> viewAllSupplierList() {
        Query q = em.createQuery("Select s from SupplierEntity s");
        List<SupplierEntity> list = q.getResultList();
        return list;
    }

    @Override
    public boolean markSupplierAsActive(Long id) {
        if (checkSupplierExists(id)) {
            supplier = em.getReference(SupplierEntity.class, id);
            supplier.setIsActive(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean markSupplierAsInActive(Long id) {
        if (checkSupplierExists(id)) {
            supplier = em.getReference(SupplierEntity.class, id);
            supplier.setIsActive(false);
            return true;
        }
        return false;
    }

    @Override
    public List<SupplierEntity> viewActiveSupplierList() {
        Query q = em.createQuery("Select s from SupplierEntity s where s.isActive='true'");
        List<SupplierEntity> list = q.getResultList();
        return list;
    }

    @Override
    public List<SupplierEntity> viewInactiveSupplierList() {
        Query q = em.createQuery("Select s from SupplierEntity s where s.isActive='false'");
        List<SupplierEntity> list = q.getResultList();
        return list;
    }

    @Override
    public SupplierEntity getSupplier(Long id) {
        if (checkSupplierExists(id)) {
            return findASupplier(id);
        }
        return null;
    }

}
