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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;
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
    public Integer getAverageCustomerRecency() {
        System.out.println("getAverageCustomerRecency()");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        Integer numOfDaysWithRecord = 0;
        Integer totalDays = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();

            for (MemberEntity member : members) {
                System.out.println("Inside members list");
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {
                    System.out.println("This member has purchases records of " + member.getPurchases().size());
                    List<Date> dates = new ArrayList<Date>();
                    Date latest;

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        System.out.println("Looping through purchases");
                        dates.add(member.getPurchases().get(i).getCreatedDate());
                    }
                    latest = Collections.max(dates);
                    System.out.println("Latest date for member :" + member.getName() + " is " + latest);

                    Long days = date.getTime() - latest.getTime();
                    System.out.println("Number of dates against today's date : " + TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS));
                    days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                    numOfDaysWithRecord++;
                    totalDays += (int) (long) days;
                } else {
                    System.out.println("This member has NO purchases records");
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to list recency:\n" + ex);
        }
        System.out.println("Average recency days is : " + totalDays / numOfDaysWithRecord);

        return totalDays / numOfDaysWithRecord;
    }

    @Override
    public Integer getAverageCustomerFrequency() {
        System.out.println("getAverageCustomerFrequency()");

        Integer numOfPurchases = 0;
        Integer frequency = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();

            for (MemberEntity member : members) {
                System.out.println("Inside members list");
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {
                    numOfPurchases++;
                    frequency += member.getPurchases().size();
                } else {
                    System.out.println("This member has NO purchases records");
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to list recency:\n" + ex);
        }
        System.out.println("Average frequency is : " + frequency / numOfPurchases);

        return frequency / numOfPurchases;
    }

    @Override
    public Integer getAverageCustomerMonetaryValue() {
        System.out.println("getAverageCustomerMonetaryValue()");

        Integer amountOfPurchase = 0;
        Integer numOfPurchases = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();

            for (MemberEntity member : members) {
                System.out.println("Inside members list");
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {
                    System.out.println("This member has purchases records of " + member.getPurchases().size());

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        System.out.println("Looping through purchases");
                        numOfPurchases++;
                        amountOfPurchase += member.getPurchases().get(i).getAmountDue().intValue();
                    }
                } else {
                    System.out.println("This member has NO purchases records");
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to list monetary value:\n" + ex);
        }
        System.out.println("Average monetary value : " + amountOfPurchase / numOfPurchases);

        return amountOfPurchase / numOfPurchases;
    }

    @Override
    public Integer customerLifetimeValueOfMember(Long memberId) {
        return 5;
    }

    @Override
    public Integer getCustomerRecency(Long memberId) {
        System.out.println("getAverageCustomerRecency()");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Long days = (long) 0;
        
        MemberEntity member = em.find(MemberEntity.class, memberId);
        if (member.getPurchases() != null && member.getPurchases().size() != 0) {
            System.out.println("This member has purchases records of " + member.getPurchases().size());

            List<Date> dates = new ArrayList<Date>();
            Date latest;
            for (int i = 0; i < member.getPurchases().size(); i++) {
                System.out.println("Looping through purchases");
                dates.add(member.getPurchases().get(i).getCreatedDate());
            }
            latest = Collections.max(dates);
            System.out.println("Latest date for member :" + member.getName() + " is " + latest);

            days = date.getTime() - latest.getTime();
            System.out.println("Number of dates against today's date : " + TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS));
            days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);

        } else {
            System.out.println("This member has NO purchases records");
        }
        return (int) (long) days;
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
        for (MemberEntity member : members) {
            if (member.getAge() > startAge && member.getAge() < endAge) {
                totalCummulativeSpending += member.getCummulativeSpending();
            }
        }
        System.out.println("totalCummulativeSpending is : " + totalCummulativeSpending);
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
            Query q = em.createQuery("SELECT t FROM SalesRecordEntity t");
            List<SalesRecordEntity> salesRecords = q.getResultList();

            for (SalesRecordEntity salesRecord : salesRecords) {
                if (salesRecord.getMember() == null) {
                    profit += salesRecord.getAmountDue() + salesRecord.getAmountPaid();
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to list all non member sales records:\n" + ex);
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
