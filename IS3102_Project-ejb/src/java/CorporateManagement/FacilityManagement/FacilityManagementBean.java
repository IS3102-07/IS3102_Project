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
    
    public Boolean editRegionalOffice(String regionalOfficeName) {
        System.out.println("editRegionalOffice() called with ID:" + regionalOfficeName);
        try {
            Query q = em.createQuery("SELECT t FROM RegionalOfficeEntity t");

            for (Object o : q.getResultList()) {
                RegionalOfficeEntity i = (RegionalOfficeEntity) o;
                if (i.getName().equalsIgnoreCase(regionalOfficeName)) {
                    i.setName(regionalOfficeName);
                    em.merge(i);
                    System.out.println("\nServer edited regional office:\n" + regionalOfficeName);
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

    public Boolean editManufacturingFacility(Long id, String manufacturingFacilityName) {
        System.out.println("editManufacturingFacility() called with ID:" + id);
        try {
            Query q = em.createQuery("SELECT t FROM ManufacturingFacilityEntity t");

            for (Object o : q.getResultList()) {
                ManufacturingFacilityEntity i = (ManufacturingFacilityEntity) o;
                if (i.getId() == id) {
                    i.setName(manufacturingFacilityName);
                    em.merge(i);
                    System.out.println("\nServer edited manufacturing facility:\n" + manufacturingFacilityName);
                    return true;
                }
            }
            return false; //Could not find the role to remove
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
    public ManufacturingFacilityEntity viewManufacturingFacility(String manufacturingFacility) {
        System.out.println("viewManufacturingFacility() called with manufacturingFacility:" + manufacturingFacility);
        try {
            Query q = em.createQuery("SELECT t FROM ManufacturingFacilityEntity t");

            for (Object o : q.getResultList()) {
                ManufacturingFacilityEntity i = (ManufacturingFacilityEntity) o;
                if (i.getName().equalsIgnoreCase(manufacturingFacility)) {
                    System.out.println("\nServer returns regional office:\n" + manufacturingFacility);
                    return i;
                }
            }
            return null; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove regional office:\n" + ex);
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
    public Boolean addStoreConnectionToManufacturingFacility(Long id, StoreEntity store) {
        try {
            ManufacturingFacilityEntity manufacturingFacility = em.find(ManufacturingFacilityEntity.class, id);
            manufacturingFacility.getStoreList().add(store);
            em.merge(manufacturingFacility);
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
    
    public Boolean editStore(Long id, String storeName) {
        System.out.println("editStore() called with ID:" + id);
        try {
            Query q = em.createQuery("SELECT t FROM StoreEntity t");

            for (Object o : q.getResultList()) {
                StoreEntity i = (StoreEntity) o;
                if (i.getId() == id) {
                    i.setName(storeName);
                    em.merge(i);
                    System.out.println("\nServer edited regional office:\n" + id);
                    return true;
                }
            }
            return false; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove regional office:\n" + ex);
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

    public StoreEntity viewStoreEntity(String storeEntity) {
        System.out.println("viewStoreEntity() called with storeEntity:" + storeEntity);
        try {
            Query q = em.createQuery("SELECT t FROM ManufacturingFacilityEntity t");

            for (Object o : q.getResultList()) {
                StoreEntity i = (StoreEntity) o;
                if (i.getName().equalsIgnoreCase(storeEntity)) {
                    System.out.println("\nServer returns store entity:\n" + storeEntity);
                    return i;
                }
            }
            return null; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove regional office:\n" + ex);
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
    public Boolean editWarehouse(Long Id, String warehouseName, String address, String telephone, String email) {
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
    public Boolean deleteWarehouse(Long id) {
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
    public WarehouseEntity getWarehouseById(Long Id) {
        try {
            return em.find(WarehouseEntity.class, Id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    @Remove
    public void remove() {
        System.out.println("Facility Management is removed");
    }

}
