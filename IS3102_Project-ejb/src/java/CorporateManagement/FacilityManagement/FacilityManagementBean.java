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
import HelperClasses.ManufacturingFacilityHelper;
import HelperClasses.StoreHelper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
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

    @Override
    public boolean checkNameExistsOfRegionalOffice(String name) {
        System.out.println("checkNameExistsOfRegionalOffice() called with name:" + name);
        try {
            Query q = em.createQuery("SELECT t FROM RegionalOfficeEntity t where t.isDeleted=false");
            for (Object o : q.getResultList()) {
                RegionalOfficeEntity i = (RegionalOfficeEntity) o;
                System.out.println(" i name is : " + i.getName());
                if (i.getName().equalsIgnoreCase(name)) {
                    System.out.println("Found existing name");
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to return regional office:\n" + ex);
            return false;
        }
    }

    @Override
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
            RegionalOfficeEntity regionalOfficeEntity = em.getReference(RegionalOfficeEntity.class, Long.valueOf(regionalOfficeName));
            regionalOfficeEntity.setIsDeleted(true);
            em.merge(regionalOfficeEntity);
            em.flush();
            System.out.println("Regional office removed succesfully");
            return true;
        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Failed to remove regional office, regional office not found.");
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove regional office:\n" + ex);
            return false;
        }

    }

    @Override
    public RegionalOfficeEntity viewRegionalOffice(String regionalOfficeId) {
        System.out.println("viewRegionalOffice() called with regionalOfficeName:" + regionalOfficeId);
        try {
            RegionalOfficeEntity regionalOffice = em.find(RegionalOfficeEntity.class, Long.valueOf(regionalOfficeId));

            System.out.println(" i id is :" + regionalOffice.getId());

            return regionalOffice;

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
            Query q = em.createQuery("SELECT t FROM RegionalOfficeEntity t where t.isDeleted=false");
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

    @Override
    public boolean checkNameExistsOfManufacturingFacility(String name) {
        System.out.println("checkNameExistsOfManufacturingFacility() called with name:" + name);
        try {
            Query q = em.createQuery("SELECT t FROM ManufacturingFacilityEntity t where t.isDeleted=false");
            for (Object o : q.getResultList()) {
                ManufacturingFacilityEntity i = (ManufacturingFacilityEntity) o;
                System.out.println(" i name is : " + i.getName());
                if (i.getName().equalsIgnoreCase(name)) {
                    System.out.println("Found existing name");
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to find manufacturing facility:\n" + ex);
            return false;
        }
    }

    @Override
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
    public boolean removeManufacturingFacility(String manufacturingFacilityID) {
        System.out.println("removeManufacturingFacility() called with ID:" + manufacturingFacilityID);
        try {
            Query q = em.createQuery("SELECT t FROM ManufacturingFacilityEntity t");

            for (Object o : q.getResultList()) {
                ManufacturingFacilityEntity i = (ManufacturingFacilityEntity) o;
                if (i.getId() == Long.valueOf(manufacturingFacilityID)) {
                    RegionalOfficeEntity regionalOffice = i.getRegionalOffice();
                    regionalOffice.getManufacturingFacilityEntityList().remove(i);
                    em.merge(regionalOffice);
                    i.setIsDeleted(true);
                    em.merge(i);
                    em.flush();
                    System.out.println("\nServer removed manufacturing facility:\n" + manufacturingFacilityID);
                    return true;
                }
            }
            return false;
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
            Query q = em.createQuery("SELECT t FROM ManufacturingFacilityEntity t where t.isDeleted=false");
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

    @Override
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

    @Override
    public boolean checkNameExistsOfStore(String name) {
        System.out.println("checkNameExistsOfStore() called with name:" + name);
        try {
            Query q = em.createQuery("SELECT t FROM StoreEntity t where t.isDeleted=false");
            for (Object o : q.getResultList()) {
                StoreEntity i = (StoreEntity) o;
                System.out.println(" i name is : " + i.getName());
                if (i.getName().equalsIgnoreCase(name)) {
                    System.out.println("Found existing name");
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to return store:\n" + ex);
            return false;
        }
    }

    @Override
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

    @Override
    public Boolean removeStore(Long storeId) {
        System.out.println("removeStore() called.");
        try {
            Query q = em.createQuery("select s from StoreEntity s where s.id = ?1").setParameter(1, storeId);
            StoreEntity store = (StoreEntity) q.getSingleResult();
            store.getRegionalOffice().getStoreList().remove(store);
            store.setIsDeleted(true);
            em.merge(store);
            System.out.println("removeStore(): store removed");
            return true;
        } catch (Exception ex) {
            System.out.println("removeStore(): Failed to remove store:");
            ex.printStackTrace();
        }
        return false;
    }

    @Override
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

    @Override
    public List<StoreEntity> viewListOfStore() {
        System.out.println("viewListOfStorey() called.");
        List<StoreEntity> listOfStore = new ArrayList<StoreEntity>();
        try {
            Query q = em.createQuery("SELECT t FROM StoreEntity t where t.isDeleted=false");
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
    public WarehouseEntity createWarehouse(String warehouseName, String address, String telephone, String email, Long storeId, Long mfId) {
        try {
            Query q = em.createQuery("select w from WarehouseEntity w where w.warehouseName = ?1").setParameter(1, warehouseName);
            if (q.getResultList().isEmpty()) {
                WarehouseEntity warehouse = new WarehouseEntity(warehouseName, address, telephone, email);
                if (storeId != -1) {
                    StoreEntity store = em.find(StoreEntity.class, storeId);
                    warehouse.setStore(store);
                    store.setWarehouse(warehouse);
                    em.merge(store);
                } else if (mfId != -1) {
                    ManufacturingFacilityEntity mf = em.find(ManufacturingFacilityEntity.class, mfId);
                    warehouse.setManufaturingFacility(mf);
                    mf.setWarehouse(warehouse);
                    em.merge(mf);
                }
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
    public boolean checkNameExistsOfWarehouse(String name) {
        System.out.println("checkNameExistsOfWarehouse() called.");
        try {
            Query q = em.createQuery("Select i from WarehouseEntity i where i.warehouseName=:name and i.isDeleted=false");
            q.setParameter("name", name);
            q.getSingleResult();
            return true;
        } catch (NoResultException n) {
            System.out.println("\ncheckNameExistsOfWarehouse(): No warehouse of that name exist.");
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to perform name check of warehouse:\n" + ex);
            return false;
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
            ManufacturingFacilityEntity mf = warehouse.getManufaturingFacility();
            if (mf != null) {
                mf.setWarehouse(null);
                em.merge(mf);
            }
            StoreEntity store = warehouse.getStore();
            if (store != null) {
                store.setWarehouse(null);
                em.merge(store);
            }
            warehouse.setIsDeleted(true);
            em.merge(warehouse);
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
            Query q = em.createQuery("select w from WarehouseEntity w where w.isDeleted=false");
            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<WarehouseEntity>();
        }
    }

    @Override
    public WarehouseEntity getWarehouseByName(String warehouseName) {
        try {
            Query q = em.createQuery("select w from WarehouseEntity w where w.isDeleted=false and w.warehouseName = ?1").setParameter(1, warehouseName);
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

    @Override
    public StoreEntity getStoreByName(String storeName) {
        try {
            Query q = em.createQuery("select s from StoreEntity s where s.isDeleted=false and s.name = ?1").setParameter(1, storeName);
            return (StoreEntity) q.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public StoreEntity getStoreByID(Long storeID) {
        System.out.println("getStoreByID() called.");
        try {
            Query q = em.createQuery("select s from StoreEntity s where s.isDeleted=false and s.id = ?1").setParameter(1, storeID);
            System.out.println("getStoreByID(): Store returned.");
            return (StoreEntity) q.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("getStoreByID(): No such store found.");
            return null;
        } catch (Exception ex) {
            System.out.println("getStoreByID(): Failed to getStoreByID()");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public StoreHelper getStoreHelperClass(Long Id) {
        try {
            StoreEntity store = this.viewStoreEntity(Id);
            StoreHelper helper = new StoreHelper();
            helper.store = store;
            helper.regionalOffice = store.getRegionalOffice();
            System.out.println("return helper class");
            return helper;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StoreHelper> getStoreHelperList() {
        try {
            List<StoreEntity> storeList = this.viewListOfStore();
            List<StoreHelper> helperList = new ArrayList<StoreHelper>();
            for (StoreEntity s : storeList) {
                StoreHelper helper = new StoreHelper();
                helper.store = s;
                helper.regionalOffice = s.getRegionalOffice();
                helperList.add(helper);
            }
            return helperList;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Boolean updateStoreToRegionalOffice(Long regionalOfficeId, Long storeId) {
        try {
            StoreEntity store = em.find(StoreEntity.class, storeId);
            RegionalOfficeEntity newRegionalOffice = em.find(RegionalOfficeEntity.class, regionalOfficeId);
            RegionalOfficeEntity oldRegionalOffice = store.getRegionalOffice();

            oldRegionalOffice.getStoreList().remove(store);
            store.setRegionalOffice(newRegionalOffice);
            newRegionalOffice.getStoreList().add(store);

            em.merge(oldRegionalOffice);
            em.merge(store);
            em.merge(newRegionalOffice);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public ManufacturingFacilityHelper getManufacturingFacilityHelper(Long manufacturingFacilityId) {
        try {
            ManufacturingFacilityHelper helper = new ManufacturingFacilityHelper();
            ManufacturingFacilityEntity mf = em.find(ManufacturingFacilityEntity.class, manufacturingFacilityId);
            helper.manufacturingFacilityEntity = mf;
            helper.regionalOffice = mf.getRegionalOffice();
            return helper;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ManufacturingFacilityHelper> getManufacturingFacilityHelperList() {
        try {
            List<ManufacturingFacilityEntity> mfList = this.viewListOfManufacturingFacility();
            List<ManufacturingFacilityHelper> helperList = new ArrayList<ManufacturingFacilityHelper>();
            for (ManufacturingFacilityEntity mf : mfList) {
                ManufacturingFacilityHelper helper = new ManufacturingFacilityHelper();
                helper.manufacturingFacilityEntity = mf;
                helper.regionalOffice = mf.getRegionalOffice();
                helperList.add(helper);
            }
            return helperList;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Boolean addManufacturingFacilityToRegionalOffice(Long regionalOfficeId, Long MFid) {
        try {
            ManufacturingFacilityEntity MF = em.find(ManufacturingFacilityEntity.class, MFid);
            RegionalOfficeEntity ro = em.find(RegionalOfficeEntity.class, regionalOfficeId);
            MF.setRegionalOffice(ro);
            ro.getManufacturingFacilityEntityList().add(MF);
            em.merge(MF);
            em.merge(ro);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean updateManufacturingFacilityToRegionalOffice(Long regionalOfficeId, Long MFid) {
        try {
            ManufacturingFacilityEntity MF = em.find(ManufacturingFacilityEntity.class, MFid);
            RegionalOfficeEntity newRO = em.find(RegionalOfficeEntity.class, regionalOfficeId);
            RegionalOfficeEntity oldRO = MF.getRegionalOffice();

            oldRO.getManufacturingFacilityEntityList().remove(MF);
            MF.setRegionalOffice(newRO);
            newRO.getManufacturingFacilityEntityList().add(MF);

            em.merge(newRO);
            em.merge(MF);
            em.merge(oldRO);

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean removeManufacturingFacility(Long Id) {
        try {
            ManufacturingFacilityEntity mf = em.find(ManufacturingFacilityEntity.class, Id);
            RegionalOfficeEntity ro = mf.getRegionalOffice();
            ro.getManufacturingFacilityEntityList().remove(mf);
            em.merge(ro);
            mf.setIsDeleted(true);
            em.merge(mf);
            em.flush();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkIfWarehouseContainsItem(Long id) {
        try {
            WarehouseEntity we = em.find(WarehouseEntity.class, id);
            if (we.getStorageBins().isEmpty()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
