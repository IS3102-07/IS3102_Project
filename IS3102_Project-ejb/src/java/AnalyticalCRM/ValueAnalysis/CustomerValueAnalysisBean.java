package AnalyticalCRM.ValueAnalysis;

import EntityManager.FurnitureEntity;
import EntityManager.ItemEntity;
import EntityManager.RetailProductEntity;
import EntityManager.SalesRecordEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import EntityManager.MemberEntity;
import EntityManager.RoleEntity;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.Query;

@Stateful
public class CustomerValueAnalysisBean implements CustomerValueAnalysisBeanLocal {

    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;

    @EJB
    AccountManagementBeanLocal accountManagementBean;

    public CustomerValueAnalysisBean() {
        System.out.println("\nCustomer Value Analysis Server (EJB) created.");
    }

    @Override
    public Integer customerLifetimeValueOfMember(Long memberId) {
        return 5;
    }
    
    @Override
    public Date getCustomerRecency(Long memberId) {
        Date date = new Date();
        return date;
    }
    
    @Override
    public Integer getCustomerFrequency(Long memberId) {
       Integer test = 10;
       return test;
    }
    
    @Override
    public Integer getCustomerMonetaryValue(Long memberId) {
       Integer test = 10;
       return test;
    }

    @Override
    public Integer totalCummulativeSpending(Integer startAge, Integer endAge) {
        System.out.println("totalCummulativeSpending()");
        List<MemberEntity> members = accountManagementBean.listAllMember();

        int totalCummulativeSpending = 0;
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getAge() > startAge && members.get(i).getAge() < endAge) {
                System.out.println(members.get(i).getName());
                totalCummulativeSpending += members.get(i).getCummulativeSpending();
            }
        }
        return totalCummulativeSpending;
    }

    @Override
    public Integer averageCummulativeSpending() {
        System.out.println("averageCummulativeSpending()");
        List<MemberEntity> members = accountManagementBean.listAllMember();

        int totalCummulativeSpending = 0;
        for (int i = 0; i < members.size(); i++) {
            totalCummulativeSpending += members.get(i).getCummulativeSpending();
        }
        return (totalCummulativeSpending / members.size());
    }

    @Override
    public Integer numOfMembersInAgeGroup(Integer startAge, Integer endAge) {
        System.out.println("numOfMembersInAgeGroup()");
        List<MemberEntity> members = accountManagementBean.listAllMember();

        int numOfmembersInGroup = 0;
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getAge() > startAge && members.get(i).getAge() < endAge) {
                numOfmembersInGroup++;
            }
        }
        return numOfmembersInGroup;
    }

    @Override
    public Double totalMemberRevenue() {
        System.out.println("totalMemberRevenue()");
        List<MemberEntity> members = accountManagementBean.listAllMember();

        Double profit = new Double("0");

        for (int i = 0; i < members.size(); i++) {
            
            profit += members.get(i).getCummulativeSpending();
        }

        return profit;
    }
    
    @Override
    public Double totalNonMemberRevenue() {
        System.out.println("totalNonMemberRevenue()");
        
        Double profit = new Double("0");
        try {
            Query q = em.createQuery("SELECT t FROM SalesRecordEntity t where t.member_id=null");
            List<SalesRecordEntity> salesRecords = q.getResultList();
            
            for (SalesRecordEntity salesRecord : salesRecords) {
                profit += salesRecord.getAmountDue() + salesRecord.getAmountPaid();
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to list all member:\n" + ex);
        }
        return profit;
    }

    @Override
    public List<ItemEntity> viewSimilarProducts(Long itemId) {

        List<ItemEntity> similarProducts = new ArrayList();
        return similarProducts;
    }

    @Override
    public List<ItemEntity> viewUpsellProducts(Long itemId) {

        List<ItemEntity> similarProducts = new ArrayList();
        return similarProducts;
    }

    @Override
    public Integer viewMonthlyReport() {

        return 5;
    }

    @Override
    public Integer viewSalesSummary() {

        return 5;
    }

    @Override
    public List<FurnitureEntity> viewBestSellingFurniture() {
        List<FurnitureEntity> bestFurniture = new ArrayList();
        return bestFurniture;
    }

    @Override
    public List<RetailProductEntity> viewBestSellingRetailProducts() {
        List<RetailProductEntity> bestRetailProducts = new ArrayList();
        return bestRetailProducts;
    }

    @Override
    public List<SalesRecordEntity> viewMemberSalesRecord(Long memberId) {
        List<SalesRecordEntity> salesRecords = new ArrayList();
        return salesRecords;
    }

}
