/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CorporateManagement.FacilityManagement;

import EntityManager.ManufacturingFacilityEntity;
import EntityManager.RegionalOfficeEntity;
import EntityManager.StoreEntity;
import EntityManager.WarehouseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Loi Liang Yang
 */
@Stateless
public class FacilityManagementBean implements FacilityManagementBeanLocal {

    @PersistenceContext
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean addRegionalOffice(String regionalOfficeName, String address, String telephone, String email) {
        System.out.println("addRegionalOffice() called with name:" + regionalOfficeName);
        String name;
        Long id;
        try {
            RegionalOfficeEntity regionalOfficeEntity = new RegionalOfficeEntity();
            regionalOfficeEntity.create(regionalOfficeName, address, telephone, email);
            em.persist(regionalOfficeEntity);
            name = regionalOfficeEntity.getName();
            id = regionalOfficeEntity.getId();
            System.out.println("Regional Office Name \"" + name + "\" registered successfully as id:" + id);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to register regional office:\n" + ex);
            return false;
        }
    }

    public Boolean editRegionalOffice(Long id, String regionalOfficeName, String address, String telephone, String email) {
        System.out.println("editRegionalOffice() called with ID:" + id + regionalOfficeName + address + telephone + email);
        try {
            RegionalOfficeEntity regionalOffice = em.find(RegionalOfficeEntity.class, id);
            regionalOffice.setName(regionalOfficeName);
            regionalOffice.setAddress(address);
            regionalOffice.setTelephone(telephone);
            regionalOffice.setEmail(email);
            em.merge(regionalOffice);
            System.out.println("\nServer edited regional office: " + id);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove regional office:\n" + ex);
            return false;
        }
    }

    @Override
    public boolean removeRegionalOffice(String regionalOfficeName) {
        System.out.println("removeRegionalOffice() called with ID:" + regionalOfficeName);

        try {
            Query q = em.createQuery("SELECT t FROM RegionalOfficeEntity t");

            for (Object o : q.getResultList()) {
                RegionalOfficeEntity i = (RegionalOfficeEntity) o;
                if (i.getId() == (Long.valueOf(regionalOfficeName))) {
                    em.remove(i);
                    em.flush();
                    System.out.println("\nServer removed regional office:\n" + regionalOfficeName);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove regional office:\n" + ex);
            return false;
        }
    }

    @Override
    public RegionalOfficeEntity viewRegionalOffice(String regionalOfficeId) {
        System.out.println("viewRegionalOffice() called with regionalOfficeName:" + regionalOfficeId);
        try {
            Query q = em.createQuery("SELECT t FROM RegionalOfficeEntity t");
            for (Object o : q.getResultList()) {
                RegionalOfficeEntity i = (RegionalOfficeEntity) o;
                System.out.println(" i id is : " + i + " =" + regionalOfficeId);
                if (i.getId() == Long.valueOf(regionalOfficeId)) {
                    System.out.println("\nServer returns regional office:\n" + regionalOfficeId);
                    return i;
                }
            }
            return null; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to return regional office:\n" + ex);
            return null;
        }
    }

    @Override
    public List<RegionalOfficeEntity> viewListOfRegionalOffice() {
        System.out.println("viewListOfRegionalOffice() called.");
        List<RegionalOfficeEntity> listOfRegionalOffice = new ArrayList<RegionalOfficeEntity>();
        try {
            Query q = em.createQuery("SELECT t FROM RegionalOfficeEntity t");
            for (Object o : q.getResultList()) {
                RegionalOfficeEntity i = (RegionalOfficeEntity) o;
                listOfRegionalOffice.add(i);
            }
            return listOfRegionalOffice; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to run:\n" + ex);
            return null;
        }
    }

    @Override
    public ManufacturingFacilityEntity createManufacturingFacility(String manufacturingFacility, String address, String telephone, String email, Integer capacity) {
        System.out.println("createManufacturingFacility() called with name:" + manufacturingFacility);
        String name;
        Long id;
        try {
            ManufacturingFacilityEntity manufacturingFacilityEntity = new ManufacturingFacilityEntity();
            manufacturingFacilityEntity.create(manufacturingFacility, address, telephone, email, capacity);
            em.persist(manufacturingFacilityEntity);
            name = manufacturingFacilityEntity.getName();
            id = manufacturingFacilityEntity.getId();
            System.out.println("Manufacturing Facility Name \"" + name + "\" registered successfully as id:" + id);
            return manufacturingFacilityEntity;
        } catch (Exception ex) {
            System.out.println("\nServer failed to register manufacturing facility:\n" + ex);
            return null;
        }
    }

    public Boolean editManufacturingFacility(Long id, String manufacturingFacilityName, String address, String telephone, String email, Integer capacity) {
        System.out.println("editManufacturingFacility() called with ID:" + id);
        try {
             ManufacturingFacilityEntity manufacturingFacility = em.find(ManufacturingFacilityEntity.class, id);
            manufacturingFacility.setName(manufacturingFacilityName);
            manufacturingFacility.setAddress(address);
            manufacturingFacility.setTelephone(telephone);
            manufacturingFacility.setEmail(email);
            manufacturingFacility.setCapacity(capacity);
            em.merge(manufacturingFacility);
            System.out.println("\nServer edited manufacturing facility: " + id);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to edit manufacturing facility:\n" + ex);
            return false;
        }
    }

    @Override
    public boolean removeManufacturingFacility(String manufacturingFacility) {
        System.out.println("removeManufacturingFacility() called with ID:" + manufacturingFacility);
        try {
            Query q = em.createQuery("SELECT t FROM ManufacturingFacilityEntity t");

            for (Object o : q.getResultList()) {
                ManufacturingFacilityEntity i = (ManufacturingFacilityEntity) o;
                if (i.getId() == Long.valueOf(manufacturingFacility)) {
                    em.remove(i);
                    em.flush();
                    System.out.println("\nServer removed manufacturing facility:\n" + manufacturingFacility);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove manufacturing facility:\n" + ex);
            return false;
        }
    }

    @Override
    public ManufacturingFacilityEntity viewManufacturingFacility(Long manufacturingFacilityId) {
        System.out.println("viewManufacturingFacility() called with manufacturingFacility:" + manufacturingFacilityId);
        try {
            ManufacturingFacilityEntity manufacturingFacilityEntity = em.find(ManufacturingFacilityEntity.class, manufacturingFacilityId);
            return manufacturingFacilityEntity;
        } catch (Exception ex) {
            System.out.println("\nServer failed to return manufacturing facility:\n" + ex);
            return null;
        }
    }

    @Override
    public List<ManufacturingFacilityEntity> viewListOfManufacturingFacility() {
        System.out.println("viewListOfRegionalOffice() called.");
        List<ManufacturingFacilityEntity> listOfManufacturingFacility = new ArrayList<ManufacturingFacilityEntity>();
        try {
            Query q = em.createQuery("SELECT t FROM ManufacturingFacilityEntity t");
            for (Object o : q.getResultList()) {
                ManufacturingFacilityEntity i = (ManufacturingFacilityEntity) o;
                listOfManufacturingFacility.add(i);
            }
            return listOfManufacturingFacility; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to run:\n" + ex);
            return null;
        }
    }

    @Override
    public Boolean addStoreToRegionalOffice(Long id, Long storeId) {
        try {
            StoreEntity store = em.find(StoreEntity.class, storeId);
            RegionalOfficeEntity regionalOffice = em.find(RegionalOfficeEntity.class, id);
            store.setRegionalOffice(regionalOffice);
            regionalOffice.getStoreList().add(store);                    
            em.merge(regionalOffice);
            em.merge(store);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public StoreEntity createStore(String storeName, String address, String telephone, String email) {
        System.out.println("createStore() called with name:" + storeName);
        String name;
        Long id;
        try {
            StoreEntity storeEntity = new StoreEntity();
            storeEntity.create(storeName, address, telephone, email);
            em.persist(storeEntity);
            name = storeEntity.getName();
            id = storeEntity.getId();
            System.out.println("Store Name \"" + name + "\" registered successfully as id:" + id);
            return storeEntity;
        } catch (Exception ex) {
            System.out.println("\nServer failed to register manufacturing facility:\n" + ex);
            return null;
        }
    }

    public Boolean editStore(Long id, String storeName, String address, String telephone, String email) {
        System.out.println("editStore() called with ID:" + id);
        try {
            StoreEntity storeEntity = em.find(StoreEntity.class, id);

            storeEntity.setName(storeName);
            storeEntity.setAddress(address);
            storeEntity.setTelephone(telephone);
            storeEntity.setEmail(email);
            em.merge(storeEntity);
            System.out.println("\nServer edited store:\n" + id);
            return true;

        } catch (Exception ex) {
            System.out.println("\nServer failed to edit store:\n" + ex);
            return false;
        }
    }

    public boolean removeStore(String id) {
        System.out.println("removeStore() called with storeName:" + id);
        try {
            Query q = em.createQuery("SELECT t FROM StoreEntity t");

            for (Object o : q.getResultList()) {
                StoreEntity i = (StoreEntity) o;
                if (i.getId() == Long.valueOf(id)) {
                    em.remove(i);
                    em.flush();
                    System.out.println("\nServer removed store:\n" + id);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove store:\n" + ex);
            return false;
        }
    }

    public StoreEntity viewStoreEntity(Long storeId) {
        System.out.println("viewStoreEntity() called with storeEntity Id:" + storeId);
        try {
            StoreEntity storeEntity = em.find(StoreEntity.class, storeId);
            System.out.println("\nServer viewed store:\n" + storeId);
            return storeEntity;
        } catch (Exception ex) {
            System.out.println("\nServer failed to view store:\n" + ex);
            return null;
        }
    }

    public List<StoreEntity> viewListOfStore() {
        System.out.println("viewListOfStorey() called.");
        List<StoreEntity> listOfStore = new ArrayList<StoreEntity>();
        try {
            Query q = em.createQuery("SELECT t FROM StoreEntity t");
            for (Object o : q.getResultList()) {
                StoreEntity i = (StoreEntity) o;
                listOfStore.add(i);
            }
            return listOfStore; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to run:\n" + ex);
            return null;
        }
    }

    @Override
    public WarehouseEntity createWarehouse(String warehouseName, String address, String telephone, String email) {
        try {
            Query q = em.createQuery("select w from WarehouseEntity w where w.warehouseName = ?1").setParameter(1, warehouseName);
            if (q.getResultList().isEmpty()) {
                WarehouseEntity warehouse = new WarehouseEntity(warehouseName, address, telephone, email);
                em.persist(warehouse);
                return warehouse;
            } else {
                System.out.println("warehouse name dupicated");
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean
            editWarehouse(Long Id, String warehouseName, String address, String telephone, String email) {
        try {
            WarehouseEntity warehouse = em.find(WarehouseEntity.class, Id);
            warehouse.setWarehouseName(warehouseName);

            warehouse.setAddress(address);

            warehouse.setTelephone(telephone);

            warehouse.setEmail(email);

            em.merge(warehouse);

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean
            deleteWarehouse(Long id) {
        try {
            WarehouseEntity warehouse = em.find(WarehouseEntity.class, id);
            em.remove(warehouse);

            em.flush();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<WarehouseEntity> getWarehouseList() {
        try {
            Query q = em.createQuery("select w from WarehouseEntity w");
            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<WarehouseEntity>();
        }
    }

    @Override
    public WarehouseEntity getWarehouseByName(String warehouseName) {
        try {
            Query q = em.createQuery("select w from WarehouseEntity w where w.warehouseName = ?1").setParameter(1, warehouseName);
            return (WarehouseEntity) q.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public WarehouseEntity
            getWarehouseById(Long Id) {
        try {
            return em.find(WarehouseEntity.class, Id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
            
    @Override
    public List<StoreEntity> getStoreListByRegionalOffice(Long regionalOfficeId) {
        try {
            Query q = em.createQuery("select s from StoreEntity s where s.regionalOffice.id = ?1").setParameter(1, regionalOfficeId);
            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    @Remove
    public void remove() {
        System.out.println("Facility Management is removed");
    }

}
