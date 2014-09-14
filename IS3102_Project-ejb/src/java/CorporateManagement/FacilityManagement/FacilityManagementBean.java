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
    public boolean addRegionalOffice(String regionalOfficeName) {
        System.out.println("addRegionalOffice() called with name:" + regionalOfficeName);
        String name;
        Long id;
        try {
            RegionalOfficeEntity regionalOfficeEntity = new RegionalOfficeEntity();
            regionalOfficeEntity.create(regionalOfficeName);
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

    public boolean removeRegionalOffice(String regionalOfficeName) {
        System.out.println("removeRegionalOffice() called with staffID:" + regionalOfficeName);
        try {
            Query q = em.createQuery("SELECT t FROM regionalOfficeEntity t");

            for (Object o : q.getResultList()) {
                RegionalOfficeEntity i = (RegionalOfficeEntity) o;
                if (i.getName().equalsIgnoreCase(regionalOfficeName)) {
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

    public RegionalOfficeEntity viewRegionalOffice(String regionalOfficeName) {
        System.out.println("viewRegionalOffice() called with regionalOfficeName:" + regionalOfficeName);
        try {
            Query q = em.createQuery("SELECT t FROM regionalOfficeEntity t");

            for (Object o : q.getResultList()) {
                RegionalOfficeEntity i = (RegionalOfficeEntity) o;
                if (i.getName().equalsIgnoreCase(regionalOfficeName)) {
                    System.out.println("\nServer returns regional office:\n" + regionalOfficeName);
                    return i;
                }
            }
            return null; //Could not find the role to remove
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove regional office:\n" + ex);
            return null;
        }
    }

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

    public ManufacturingFacilityEntity createManufacturingFacility(String manufacturingFacility) {
        System.out.println("createManufacturingFacility() called with name:" + manufacturingFacility);
        String name;
        Long id;
        try {
            ManufacturingFacilityEntity manufacturingFacilityEntity = new ManufacturingFacilityEntity();
            manufacturingFacilityEntity.create(manufacturingFacility);
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

    public boolean removeManufacturingFacility(String manufacturingFacility) {
        System.out.println("removeManufacturingFacility() called with staffID:" + manufacturingFacility);
        try {
            Query q = em.createQuery("SELECT t FROM ManufacturingFacilityEntity t");

            for (Object o : q.getResultList()) {
                ManufacturingFacilityEntity i = (ManufacturingFacilityEntity) o;
                if (i.getName().equalsIgnoreCase(manufacturingFacility)) {
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

    public StoreEntity createStore(String storeName) {
        System.out.println("createStore() called with name:" + storeName);
        String name;
        Long id;
        try {
            StoreEntity storeEntity = new StoreEntity();
            storeEntity.create(storeName);
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

    public boolean removeStore(String storeName) {
        System.out.println("removeStore() called with storeName:" + storeName);
        try {
            Query q = em.createQuery("SELECT t FROM StoreEntity t");

            for (Object o : q.getResultList()) {
                StoreEntity i = (StoreEntity) o;
                if (i.getName().equalsIgnoreCase(storeName)) {
                    em.remove(i);
                    em.flush();
                    System.out.println("\nServer removed store:\n" + storeName);
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

    public List<StoreEntity> viewListOfStorey() {
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
    public WarehouseEntity createWarehouse(String warehouseName) {
        try{
            WarehouseEntity warehouse = new WarehouseEntity(warehouseName);
            em.persist(warehouse);
            return warehouse;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }    

    @Override
    public Boolean deleteWarehouse(Long id) {
        try{
            WarehouseEntity warehouse = em.find(WarehouseEntity.class, id);
            em.remove(warehouse);
            em.flush();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<WarehouseEntity> getWarehouseList() {
        try{
            Query q = em.createQuery("select w from WarehouseEntity w");
            return q.getResultList();
        }catch(Exception ex){
            ex.printStackTrace();
            return new ArrayList<WarehouseEntity>();
        }
    }        

    @Override
    public WarehouseEntity getWarehouseByName(String warehouseName) {
        try{
            Query q = em.createQuery("select w from WarehouseEntity w where w.warehouseName = ?1").setParameter(1, warehouseName);
            return (WarehouseEntity)q.getSingleResult();
        }catch(Exception ex){
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
