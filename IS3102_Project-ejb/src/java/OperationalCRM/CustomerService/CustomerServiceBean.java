package OperationalCRM.CustomerService;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import Config.Config;
import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import EntityManager.FeedbackEntity;
import EntityManager.ItemEntity;
import EntityManager.LineItemEntity;
import EntityManager.PickRequestEntity;
import EntityManager.PickerEntity;
import EntityManager.RoleEntity;
import EntityManager.SalesRecordEntity;
import EntityManager.StaffEntity;
import EntityManager.StoreEntity;
import InventoryManagement.StoreAndKitchenInventoryManagement.StoreAndKitchenInventoryManagementBeanLocal;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CustomerServiceBean implements CustomerServiceBeanLocal {

    @EJB
    AccountManagementBeanLocal ambl;
    @EJB
    StoreAndKitchenInventoryManagementBeanLocal simbl;
    @EJB
    ItemManagementBeanLocal imbl;

    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;

    public List<SalesRecordEntity> viewSalesRecord(Long storeId) {
        System.out.println("View sales record is called()" + storeId);
        try {
            Query q = em.createQuery("select sr from SalesRecordEntity sr where sr.store.id = ?1").setParameter(1, storeId);
            List<SalesRecordEntity> salesRecords = q.getResultList();
            return salesRecords;
        } catch (Exception ex) {
            System.out.println("\nServer failed to retrieve sales record:\n" + ex);
            return null;
        }
    }

    public List<FeedbackEntity> viewFeedback() {
        System.out.println("View feedback is called()");
        try {
            Query q = em.createQuery("select f from FeedbackEntity f");
            List<FeedbackEntity> feedbacks = q.getResultList();
            return feedbacks;
        } catch (Exception ex) {
            System.out.println("\nServer failed to retrieve feedback:\n" + ex);
            return null;
        }
    }

    @Override
    public PickerEntity pickerLoginStaff(String email, String password) {
        Long staffID = null;
        try {
            StaffEntity staffEntity = ambl.loginStaff(email, password);
            if (staffEntity == null) {
                return null;
            }
            // Check roles, only admin, receptionist or store manager can login into picker
            List<RoleEntity> roles = staffEntity.getRoles();
            for (RoleEntity role : roles) {
                if (role.getId().equals(1L) || role.getId().equals(4L) || role.getId().equals(12L)) {
                    staffID = staffEntity.getId();
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(Config.logFilePath, true)));
                    out.println(new Date().toString() + ";" + staffID + ";pickerLoginStaff();" + staffID + ";");
                    out.close();
                    PickerEntity pickerEntity = new PickerEntity();
                    pickerEntity.setPicker(staffEntity);
                    pickerEntity.setListOfJob(new LinkedList<>());
                    em.persist(pickerEntity);
                    return pickerEntity;
                }
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public PickRequestEntity getPickRequest(Long pickerID) {
        System.out.println("getPickRequest() called");
        try {
            PickerEntity pickerEntity = em.getReference(PickerEntity.class, pickerID);
            LinkedList<PickRequestEntity> pickRequestEntities = pickerEntity.getListOfJob();
            if (pickRequestEntities.size() == 0) {
                return null;
            } else {
                return pickRequestEntities.get(0);
            }
        } catch (Exception ex) {
            System.out.println("getPickRequest(): error");
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer getPickRequestQueueSize(Long pickerID) {
        System.out.println("getPickRequestQueueSize() called");
        try {
            PickerEntity pickerEntity = em.getReference(PickerEntity.class, pickerID);
            LinkedList<PickRequestEntity> pickRequestEntities = pickerEntity.getListOfJob();
            return pickRequestEntities.size();
        } catch (Exception ex) {
            System.out.println("getPickRequestQueueSize(): error");
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public Boolean acceptPickRequest(Long pickerID, Long pickRequestID) {
        System.out.println("acceptPickRequest() called");
        try {
            PickRequestEntity pickRequestEntity = em.getReference(PickRequestEntity.class, pickRequestID);
            pickRequestEntity.setDateCompleted(new Date());
            pickRequestEntity.setPickStatus(2);//2.In-progress
            em.merge(pickRequestEntity);
            return true;
        } catch (Exception ex) {
            System.out.println("acceptPickRequest(): error");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean completePickRequest(Long pickRequestID) {
        System.out.println("completePickRequest() called");
        try {
            PickRequestEntity pickRequestEntity = em.getReference(PickRequestEntity.class, pickRequestID);
            //Update status
            pickRequestEntity.setDateCompleted(new Date());
            pickRequestEntity.setPickStatus(3);//3.Completed
            em.merge(pickRequestEntity);
            //Remove from the picker queue
            PickerEntity pickerEntity = pickRequestEntity.getPicker();
            LinkedList<PickRequestEntity> pickRequestEntities = pickerEntity.getListOfJob();
            for (int i = 0; i < pickRequestEntities.size(); i++) {
                if (pickRequestEntities.get(i).getId().equals(pickRequestID)) {
                    pickRequestEntities.remove(i);
                    em.merge(pickerEntity);
                    System.out.println("completePickRequest() success");
                    return true;
                }
            }
            //Update store inventory
//            List<ItemEntity> itemsInPickRequest = 
//            for (int itemsToRemove = itemsPurchasedSKU.size(); itemsToRemove > 0; itemsToRemove--) {
//                String currentItemSKU = itemsPurchasedSKU.get(itemsToRemove - 1);
//                String currentItemType = imbl.getItemBySKU(currentItemSKU).getType();
//                switch (currentItemType) { //only remove if is one of the following items type
//                    case "Furniture":
//                    case "Retail Product":
//                    case "Raw Material":
//                        simbl.removeItemFromInventory(storeID, itemsPurchasedSKU.get(itemsToRemove - 1), itemsPurchasedQty.get(itemsToRemove - 1), true);
//                }
//            }
            System.out.println("completePickRequest(): failed");
            return false;
        } catch (Exception ex) {
            System.out.println("completePickRequest(): error");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean pickerLogoff(Long pickerID) {
        System.out.println("pickerLogoff() called");
        try {
            PickerEntity pickerEntity = em.getReference(PickerEntity.class, pickerID);
            LinkedList<PickRequestEntity> pickerTaskQueue = pickerEntity.getListOfJob();
            //Remove him from the list of picker in the store
            em.remove(pickerEntity);
            //Reassign all the pick request in his queue to someone else
            for (PickRequestEntity curr : pickerTaskQueue) {
                curr.setPicker(null);
                em.merge(curr);
                addPickRequest(curr.getSalesRecord().getId());
            }
            return true;
        } catch (Exception ex) {
            System.out.println("pickerLogoff(): error");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean addPickRequest(Long salesRecordID) {
        System.out.println("addPickRequest() called");
        try {
            SalesRecordEntity salesRecordEntity = em.getReference(SalesRecordEntity.class, salesRecordID);

            //Find the store
            StoreEntity storeEntity = em.getReference(StoreEntity.class, salesRecordEntity.getStore().getId());

            //Find the list of pickers in the store
            Query q = em.createQuery("SELECT p FROM PickerEntity p WHERE p.store.id=:storeID");
            q.setParameter("storeID", storeEntity.getId());
            List<PickerEntity> pickers = q.getResultList();

            // If no pickers available in this store, return false
            //!!!! SHOULD NOT OCCUR DURING DEMO, PICKER HAVE TO LOGIN FIRST!!
            if (pickers.size() == 0) {
                return false;
            }

            //Find picker with least amount of job in queue
            PickerEntity pickerWithLeastJobInQueue = pickers.get(0);
            for (PickerEntity curr : pickers) {
                if (curr.getListOfJob().size() < pickerWithLeastJobInQueue.getListOfJob().size()) {
                    pickerWithLeastJobInQueue = curr;
                }
            }

            //Create the items to be picked
            List<LineItemEntity> itemsToBePicked = new ArrayList<LineItemEntity>();
            for (LineItemEntity curr : salesRecordEntity.getItemsPurchased()) {
                if (curr.getItem().getVolume() > Config.minVolumeForCollectionAreaItems) {
                    itemsToBePicked.add(curr);
                }
            }
            //Create the PickRequest 
            String receiptNo = salesRecordEntity.getReceiptNo();
            String queueNo = receiptNo.substring(receiptNo.length() - 4);
            PickRequestEntity pickRequestEntity = new PickRequestEntity(pickerWithLeastJobInQueue, salesRecordEntity, itemsToBePicked, queueNo);
            em.persist(pickRequestEntity);

            //Check the picker queue to slot the pick request in (by date)
            LinkedList<PickRequestEntity> pickerTaskQueue = pickerWithLeastJobInQueue.getListOfJob();
            Long currentPickRequestTime = salesRecordEntity.getCreatedDate().getTime();
            int pickerQueueIndex = 0;
            //If picker does not have anything, just add
            if (pickerTaskQueue.size() == 0) {
                pickerTaskQueue.add(pickRequestEntity);
                em.merge(pickerWithLeastJobInQueue);
            } else { // otherwise loop thru his queue and slot it in (based on sale order date)   
                for (int i = 0; i < pickerTaskQueue.size(); i++) {
                    // If the current one in queue is newer, slow the pick request before it
                    if (pickerTaskQueue.get(i).getSalesRecord().getCreatedDate().getTime() > currentPickRequestTime) {
                        //Add to the picker queue
                        pickerTaskQueue.add(i, pickRequestEntity);
                        em.merge(pickerWithLeastJobInQueue);
                    }
                }
            }
            return true;
        } catch (Exception ex) {
            System.out.println("addPickRequest(): error");
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<PickRequestEntity> getAllPickRequestInStore(Long storeID) {
        System.out.println("getAllPickRequestInStore() called");
        try {
            Query q = em.createQuery("SELECT p from PickRequestEntity p WHERE p.store=:storeID ORDER BY p.pickStatus ASC,p.dateSubmitted ASC");
            q.setParameter("storeID", storeID);
            return q.getResultList();
        } catch (Exception ex) {
            System.out.println("getAllPickRequestInStore(): error");
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}
