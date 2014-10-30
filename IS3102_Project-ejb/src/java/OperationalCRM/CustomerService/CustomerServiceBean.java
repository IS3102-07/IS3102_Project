package OperationalCRM.CustomerService;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import Config.Config;
import EntityManager.FeedbackEntity;
import EntityManager.PickRequestEntity;
import EntityManager.PickerEntity;
import EntityManager.RoleEntity;
import EntityManager.SalesRecordEntity;
import EntityManager.StaffEntity;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
    public Long pickerLoginStaff(String email, String password) {
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
                    pickerEntity.setListOfJob(new ArrayList<>());
                    em.persist(pickerEntity);
                    return pickerEntity.getId();
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

        } catch (Exception ex) {
            System.out.println("getPickRequest(): error");
            ex.printStackTrace();
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getPickRequestQueueSize(Long pickerID) {
        System.out.println("getPickRequestQueueSize() called");
        try {

        } catch (Exception ex) {
            System.out.println("getPickRequestQueueSize(): error");
            ex.printStackTrace();
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean acceptPickRequest(Long pickerID, Long pickRequestID) {
        System.out.println("acceptPickRequest() called");
        try {

        } catch (Exception ex) {
            System.out.println("acceptPickRequest(): error");
            ex.printStackTrace();
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean completePickRequest(Long pickRequestID) {
        System.out.println("completePickRequest() called");
        try {

        } catch (Exception ex) {
            System.out.println("completePickRequest(): error");
            ex.printStackTrace();
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean pickerLogoff(Long pickerID) {
        System.out.println("pickerLogoff() called");
        try {

        } catch (Exception ex) {
            System.out.println("pickerLogoff(): error");
            ex.printStackTrace();
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean addPickRequest(Long salesRecordID) {
        System.out.println("addPickRequest() called");
        try {
            SalesRecordEntity salesRecordEntity = em.getReference(SalesRecordEntity.class, salesRecordID);
            //PickRequestEntity pickRequestEntity = new PickRequestEntity(picker, salesRecordEntity, queueNo);
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

        } catch (Exception ex) {
            System.out.println("getAllPickRequestInStore(): error");
            ex.printStackTrace();
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
