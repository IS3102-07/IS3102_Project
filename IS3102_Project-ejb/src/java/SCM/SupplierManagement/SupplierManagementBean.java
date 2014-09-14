package SCM.SupplierManagement;

import EntityManager.CountryEntity;
import EntityManager.SupplierEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    public void addSupplier(String supplierName, String contactNo, String email, String address, Long countryId) {
        System.out.println("addSupplier() called.");
        try {
            supplier = new SupplierEntity(supplierName, contactNo, email, address);
            country = em.find(CountryEntity.class, countryId);
            supplier.setCountry(country);
            em.persist(supplier);
        } catch (Exception ex) {
            System.out.println("Failed to add supplier: " + ex);
        }
    }

    @Override
    public boolean deleteSupplier(Long id) {
        System.out.println("deleteSupplier() called.");
        try {
            if (checkSupplierExists(id)) {
                supplier = em.merge(em.getReference(SupplierEntity.class, id));
                em.remove(supplier);
                return true;
            }
            return false;
        } catch (Exception ex) {
            System.out.println("Failed to delete supplier: " + ex);
            return false;
        }
    }

    @Override
    public List<SupplierEntity> viewAllSupplierList() {
        System.out.println("viewAllSupplierList() called.");
        try {
            Query q = em.createQuery("Select s from SupplierEntity s");
            List<SupplierEntity> list = q.getResultList();
            return list;
        } catch (Exception ex) {
            System.out.println("Failed to viewAllSupplierList: " + ex);
            return null;
        }
    }

    @Override
    public boolean markSupplierAsActive(Long id) {
        System.out.println("markSupplierAsActive() called.");
        try {
            if (checkSupplierExists(id)) {
                supplier = em.getReference(SupplierEntity.class, id);
                supplier.setIsActive(true);
                return true;
            }
            return false;
        } catch (Exception ex) {
            System.out.println("Failed to markSupplierAsActive: " + ex);
            return false;
        }
    }

    @Override
    public boolean markSupplierAsInactive(Long id) {
        System.out.println("markSupplierAsInactive() called.");
        try {
            if (checkSupplierExists(id)) {
                supplier = em.getReference(SupplierEntity.class, id);
                supplier.setIsActive(false);
                return true;
            }
            return false;
        } catch (Exception ex) {
            System.out.println("Failed to markSupplierAsInActive: " + ex);
            return false;
        }
    }

    @Override
    public List<SupplierEntity> viewActiveSupplierList() {
        System.out.println("viewActiveSupplierList() called.");
        try {
            Query q = em.createQuery("Select s from SupplierEntity s where s.isActive='true'");
            List<SupplierEntity> list = q.getResultList();
            return list;
        } catch (Exception ex) {
            System.out.println("Failed to viewActiveSupplierList: " + ex);
            return null;
        }
    }

    @Override
    public List<SupplierEntity> viewInactiveSupplierList() {
        System.out.println("viewInactiveSupplierList() called.");
        try {
            Query q = em.createQuery("Select s from SupplierEntity s where s.isActive='false'");
            List<SupplierEntity> list = q.getResultList();
            return list;
        } catch (Exception ex) {
            System.out.println("Failed to viewInactiveSupplierList: " + ex);
            return null;
        }
    }

    @Override
    public SupplierEntity getSupplier(Long id) {
        System.out.println("getSupplier() called.");
        try {
            if (checkSupplierExists(id)) {
                return findASupplier(id);
            }
            return null;
        } catch (Exception ex) {
            System.out.println("Failed to viewInactiveSupplierList: " + ex);
            return null;
        }
    }

    @Override
    public boolean checkSupplierExists(String supplierName) {
        System.out.println("checkSupplierExists() called.");
        try {
            Query q = em.createQuery("Select s from SupplierEntity s where s.supplierName=:supplierName");
            q.setParameter("supplierName", supplierName);
            q.getSingleResult();
            return true;
        } catch (NoResultException n) {
            System.out.println("\nServer return no result:\n" + n);
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to perform checkSupplierExists:\n" + ex);
            return false;
        }
    }

    @Override
    public List<CountryEntity> getListOfCountries() {
        System.out.println("getListOfCountries() called.");
        try {
            Query q = em.createQuery("Select c from CountryEntity c");
            return q.getResultList();
        } catch (Exception ex) {
            System.out.println("\nServer failed to getListOfCountries:\n" + ex);
            return null;
        }
    }

    @Override
    public boolean editSupplier(Long supplierId, String name, String phone, String email, String address, Long countryId) {
        System.out.println("editSupplier() called.");
        try {
            if (checkSupplierExists(supplierId)) {
                supplier = em.getReference(SupplierEntity.class, supplierId);
                supplier.setSupplierName(name);
                supplier.setContactNo(phone);
                supplier.setEmail(email);
                supplier.setAddress(address);
                supplier.setCountry(em.getReference(CountryEntity.class, countryId));
                em.merge(supplier);
                return true;
            }
            return false;
        } catch (Exception ex) {
            System.out.println("Failed to edit supplier: " + ex);
            return false;
        }
    }
}
