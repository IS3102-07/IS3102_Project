package CommonInfrastructure.AccountManagement;

import Config.Config;
import EntityManager.CountryEntity;
import EntityManager.ItemEntity;
import EntityManager.LineItemEntity;
import EntityManager.MemberEntity;
import EntityManager.RoleEntity;
import EntityManager.ShoppingListEntity;
import EntityManager.StaffEntity;
import EntityManager.StoreEntity;
import HelperClasses.ItemHelper;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@WebService(serviceName = "AccountManagementWebService")
@Stateless
public class AccountManagementWebService {

    @EJB
    AccountManagementBeanLocal AccountManagementBeanLocal;
    
    @PersistenceContext
    private EntityManager em;

    @WebMethod
    public String posLoginStaff(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        Long staffID = null;
        try {
            StaffEntity staffEntity = AccountManagementBeanLocal.loginStaff(email, password);
            if (staffEntity == null) {
                return null;
            }
            // Check roles, only admin, cashier or store manager can login into POS
            List<RoleEntity> roles = staffEntity.getRoles();
            for (RoleEntity role : roles) {
                if (role.getId().equals(1L) || role.getId().equals(4L) || role.getId().equals(9L)) {
                    staffID = staffEntity.getId();
                    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(Config.logFilePath, true)));
                    out.println(new Date().toString() + ";" + staffID + ";posLoginStaff();" + staffID + ";");
                    out.close();
                    return staffEntity.getName();
                }
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public Boolean kioskRegisterMember(@WebParam(name = "name") String name, @WebParam(name = "address") String address, @WebParam(name = "DOB") Date DOB, @WebParam(name = "email")String email, @WebParam(name = "phone") String phone,@WebParam(name = "city")String city,@WebParam(name = "zipCode") String zipCode, @WebParam(name = "password")String password, @WebParam(name = "storeID")Long storeID) {
        System.out.println("kioskRegisterMember() called");
        try{
            StoreEntity storeEntity = em.getReference(StoreEntity.class, storeID);
            CountryEntity country = storeEntity.getCountry();
            return AccountManagementBeanLocal.registerMember(name, address, DOB, email, phone, country, city, zipCode, password);
        } catch (Exception ex) {
            System.out.println("kioskRegisterMember(): Error");
            ex.printStackTrace();
            return null;
        }
    }
    
    @WebMethod
    public List<ItemHelper> getMemberShoppingList(@WebParam(name="email") String email) {
        try {
//            MemberEntity memberEntity = AccountManagementBeanLocal.getMemberByEmail(email);
//            if (memberEntity == null)
//                return null;
//            else
//                return memberEntity.getShoppingList();
            System.out.println("in");
            ShoppingListEntity shoppingListEntity = new ShoppingListEntity();
            LineItemEntity lineItemEntity = new LineItemEntity();
            ItemEntity item;
            Query q = em.createQuery("Select i from ItemEntity i where i.SKU=:SKU and i.isDeleted=false");
            q.setParameter("SKU", "F1");
             item = (ItemEntity) q.getSingleResult();
             lineItemEntity.setItem(item);
             lineItemEntity.setQuantity(20);
             em.persist(lineItemEntity);
             List<LineItemEntity> lineItemEntitys = new ArrayList<LineItemEntity>();
             lineItemEntitys.add(lineItemEntity);
             shoppingListEntity.setItems(lineItemEntitys);
             em.persist(shoppingListEntity);
             MemberEntity memberEntity = AccountManagementBeanLocal.getMemberByEmail(email);
             memberEntity.setShoppingList(shoppingListEntity);
             em.merge(memberEntity);
             
             
             ShoppingListEntity shoppingListEntity1 = memberEntity.getShoppingList();
             List<ItemHelper> itemHelpers = new ArrayList<>();
             for(LineItemEntity curr: shoppingListEntity1.getItems()){
                 itemHelpers.add(new ItemHelper(curr.getItem().getId(), curr.getItem().getSKU(), curr.getItem().getName(), curr.getQuantity()));
             }
             return itemHelpers;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}