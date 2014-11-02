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
import CorporateManagement.ItemManagement.ItemManagementBeanLocal;
import EntityManager.LineItemEntity;
import EntityManager.MemberEntity;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.Query;
import org.apache.commons.math3.stat.regression.SimpleRegression;

@Stateful
public class CustomerValueAnalysisBean implements CustomerValueAnalysisBeanLocal {

    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;

    @EJB
    AccountManagementBeanLocal accountManagementBean;
    
    @EJB
    ItemManagementBeanLocal itemManagementBean;

    public CustomerValueAnalysisBean() {
        System.out.println("\nCustomer Value Analysis Server (EJB) created.");
    }

    @Override
    public Boolean sendMemberLoyaltyPoints(List<MemberEntity> members, Integer loyaltyPoints) {

        return true;
    }
    
    @Override
    public Integer getTotalNumberOfSalesRecord() {
        Query q = em.createQuery("SELECT t FROM SalesRecordEntity t");
            List<SalesRecordEntity> salesRecords = q.getResultList();
            return salesRecords.size();
    }
    
    @Override
    public Double getRetainedCustomerRetentionRate(List<MemberEntity> retainedMembers) {
        System.out.println("getRetainedCustomerRetentionRate()");

        Integer numOfMembers = 0;
        Integer numOfMembersNotChurn = 0;
        try {
            List<MemberEntity> members = retainedMembers;
            numOfMembers = members.size();
            for (MemberEntity member : members) {
                Calendar c = Calendar.getInstance();

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 730);
                Date churnDate = c.getTime();
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        if (days > 0 && days < 365) {
                            numOfMembersNotChurn++;
                            break;
                        }
                    }
                } else {
                    System.out.println("This member has NO purchases records");
                }
            }
            DecimalFormat df = new DecimalFormat("#.00");
            System.out.println("Num of numbers not churn is :  " + numOfMembersNotChurn + "num of members " + numOfMembers + " retention rate is " + (numOfMembersNotChurn / numOfMembers));
            return ((double) numOfMembersNotChurn / (double) numOfMembers);
        } catch (Exception ex) {
            System.out.println("\nServer failed to list retention rate:\n" + ex);
            ex.printStackTrace();
        }
        return ((double) numOfMembersNotChurn / (double) numOfMembers);
    }
    @Override
    public List<LineItemEntity> sortBestSellingFurniture() {
        System.out.println("sortBestSellingFurniture()");
        List<LineItemEntity> sortedFurnitures = new ArrayList();

        try {
            Query q = em.createQuery("SELECT t FROM FurnitureEntity t");
            List<FurnitureEntity> furnitures = q.getResultList();

            for (FurnitureEntity furniture : furnitures) {
                LineItemEntity lineItem = new LineItemEntity();
                lineItem.setItem(furniture);
                lineItem.setQuantity(0);
                sortedFurnitures.add(lineItem);
            }
            System.out.println("Size of sortedFurnitures is : " + sortedFurnitures.size());
            Query x = em.createQuery("SELECT t FROM SalesRecordEntity t");
            List<SalesRecordEntity> salesRecords = x.getResultList();

            for (SalesRecordEntity salesRecord : salesRecords) {
                System.out.println("Looping inside salesRecord of : " + salesRecord.getId());
                if (salesRecord.getItemsPurchased().size() != 0) {
                    for (LineItemEntity lineItem : salesRecord.getItemsPurchased()) {
                        System.out.println("Looping inside purchaseRecord of : " + lineItem.getId());
                        for (int i = 0; i < sortedFurnitures.size(); i++) {
                            if (lineItem.getItem().getId() == sortedFurnitures.get(i).getItem().getId()) {
                                sortedFurnitures.get(i).setQuantity(sortedFurnitures.get(i).getQuantity() + lineItem.getQuantity());
                                System.out.println(sortedFurnitures.get(i).getItem().getName() + " quantity is updated to : " + sortedFurnitures.get(i).getQuantity());
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < sortedFurnitures.size(); i++) {
                System.out.println("Furniture name : " + sortedFurnitures.get(i).getItem().getName() + " quantity : " + sortedFurnitures.get(i).getQuantity());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sortedFurnitures;
    }
    
    @Override
    public List<LineItemEntity> sortBestSellingRetailProducts() {
        System.out.println("sortBestSellingRetailProducts()");
        List<LineItemEntity> sortedRetailProducts = new ArrayList();

        try {
            Query q = em.createQuery("SELECT t FROM RetailProductEntity t");
            List<RetailProductEntity> retailProducts = q.getResultList();

            for (RetailProductEntity furniture : retailProducts) {
                LineItemEntity lineItem = new LineItemEntity();
                lineItem.setItem(furniture);
                lineItem.setQuantity(0);
                sortedRetailProducts.add(lineItem);
            }
            System.out.println("Size of sortedRetailProducts is : " + sortedRetailProducts.size());
            Query x = em.createQuery("SELECT t FROM SalesRecordEntity t");
            List<SalesRecordEntity> salesRecords = x.getResultList();

            for (SalesRecordEntity salesRecord : salesRecords) {
                System.out.println("Looping inside salesRecord of : " + salesRecord.getId());
                if (salesRecord.getItemsPurchased().size() != 0) {
                    for (LineItemEntity lineItem : salesRecord.getItemsPurchased()) {
                        System.out.println("Looping inside purchaseRecord of : " + lineItem.getId());
                        for (int i = 0; i < sortedRetailProducts.size(); i++) {
                            if (lineItem.getItem().getId() == sortedRetailProducts.get(i).getItem().getId()) {
                                sortedRetailProducts.get(i).setQuantity(sortedRetailProducts.get(i).getQuantity() + lineItem.getQuantity());
                                System.out.println(sortedRetailProducts.get(i).getItem().getName() + " quantity is updated to : " + sortedRetailProducts.get(i).getQuantity());
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < sortedRetailProducts.size(); i++) {
                System.out.println("Retailproduct name : " + sortedRetailProducts.get(i).getItem().getName() + " quantity : " + sortedRetailProducts.get(i).getQuantity());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sortedRetailProducts;
    }
    
    @Override
    public Double getFurnitureTotalRevenue(Long furnitureId) {
        return (double) 10;
    }
    
    @Override
    public List<MemberEntity> getRetainedMembers() {
        System.out.println("getRetainedMembers()");

        List<MemberEntity> retainedMembers = new ArrayList();

        Integer numOfMembers = 0;
        Integer numOfMembersNotChurn = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();
            numOfMembers = members.size();
            for (MemberEntity member : members) {
                Calendar c = Calendar.getInstance();
                System.out.println("Member " + member.getName() + " join date is : " + member.getJoinDate());

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 365);
                Date churnDate = c.getTime();
                System.out.println("Inside members list");
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {
                    System.out.println("This member has purchases records of " + member.getPurchases().size());

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        System.out.println("Looping through purchases");
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        System.out.println("Number of days from churn date is " + days);
                        if (days > 0) {
                            retainedMembers.add(member);
                            numOfMembersNotChurn++;
                            break;
                        }
                    }
                } else {
                    System.out.println("This member has NO purchases records");
                }
            }
            DecimalFormat df = new DecimalFormat("#.00");
            System.out.println("Num of numbers not churn is :  " + numOfMembersNotChurn + "num of members " + numOfMembers + " retention rate is " + (numOfMembersNotChurn / numOfMembers));
            return retainedMembers;
        } catch (Exception ex) {

            System.out.println("\nServer failed to list retention rate:\n" + ex);
            ex.printStackTrace();
        }
        return retainedMembers;
    }

    @Override
    public Double averageOrdersPerAcquiredYear() {
        System.out.println("averageOrdersPerAcquiredYear()");

        Integer numOfOrders = 0;
        Integer numOfMembers = 0;
        Integer numOfMembersNotChurn = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();
            numOfMembers = members.size();
            for (MemberEntity member : members) {
                Calendar c = Calendar.getInstance();
                System.out.println("Member " + member.getName() + " join date is : " + member.getJoinDate());

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 365);
                Date churnDate = c.getTime();
                System.out.println("Inside members list");
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {
                    System.out.println("This member has purchases records of " + member.getPurchases().size());

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        System.out.println("Looping through purchases");
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        System.out.println("Number of days from churn date is " + days);
                        if (days > 0) {
                            numOfMembersNotChurn++;
                            numOfOrders++;
                            break;
                        }
                    }
                } else {
                    System.out.println("This member has NO purchases records");
                }
            }
            DecimalFormat df = new DecimalFormat("#.00");
            System.out.println("average order per year is " + numOfOrders);
            return ((double) numOfOrders / (double) numOfMembers);
        } catch (Exception ex) {

            System.out.println("\nServer failed to list orders per year:\n" + ex);
            ex.printStackTrace();
        }
        return ((double) numOfOrders / (double) numOfMembers);
    }

    @Override
    public Double averageOrdersPerRetainedMember() {
        System.out.println("averageOrdersPerRetainedMember()");

        Integer numOfOrders = 0;
        Integer numOfMembers = 0;
        Integer numOfMembersNotChurn = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();
            numOfMembers = members.size();
            for (MemberEntity member : members) {
                Calendar c = Calendar.getInstance();
                System.out.println("Member " + member.getName() + " join date is : " + member.getJoinDate());

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 730);
                Date churnDate = c.getTime();
                System.out.println("Inside members list");
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {
                    System.out.println("This member has purchases records of " + member.getPurchases().size());

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        System.out.println("Looping through purchases");
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        System.out.println("Number of days from churn date is " + days);
                        if (days > 0 && days < 365) {
                            numOfMembersNotChurn++;
                            numOfOrders++;
                            break;
                        }
                    }
                } else {
                    System.out.println("This member has NO purchases records");
                }
            }
            DecimalFormat df = new DecimalFormat("#.00");
            System.out.println("average order per year is " + numOfOrders);
            return ((double) numOfOrders / (double) numOfMembers);
        } catch (Exception ex) {

            System.out.println("\nServer failed to list averageOrdersPerRetainedMember:\n" + ex);
            ex.printStackTrace();
            return ((double) numOfOrders / (double) numOfMembers);
        }
        
    }
    
    @Override
    public Double averageOrderPriceInAcquiredYear() {
        System.out.println("averageOrderPriceInAcquiredYear()");

        Double totalPriceOfOrders = (double) 0;
        Integer numOfOrders = 0;
        Integer numOfMembers = 0;
        Integer numOfMembersNotChurn = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();
            numOfMembers = members.size();
            for (MemberEntity member : members) {
                Calendar c = Calendar.getInstance();
                System.out.println("Member " + member.getName() + " join date is : " + member.getJoinDate());

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 365);
                Date churnDate = c.getTime();
                System.out.println("Inside members list");
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {
                    System.out.println("This member has purchases records of " + member.getPurchases().size());

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        System.out.println("Looping through purchases");
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        System.out.println("Number of days from churn date is " + days);
                        if (days > 0) {
                            totalPriceOfOrders += member.getPurchases().get(i).getAmountDue();
                            numOfOrders++;
                            break;
                        }
                    }
                } else {
                    System.out.println("This member has NO purchases records");
                }
            }
            DecimalFormat df = new DecimalFormat("#.00");
            System.out.println("average order per year is " + numOfOrders);
            return ((double) totalPriceOfOrders / (double) numOfOrders);
        } catch (Exception ex) {

            System.out.println("\nServer failed to list avg order price per year:\n" + ex);
            ex.printStackTrace();
        }
        return ((double) totalPriceOfOrders / (double) numOfOrders);
    }
    
    @Override
    public Double averageOrderPriceForRetainedMembers() {
        System.out.println("averageOrderPriceForRetainedMembers()");

        Double totalPriceOfOrders = (double) 0;
        Integer numOfOrders = 0;
        Integer numOfMembers = 0;
        Integer numOfMembersNotChurn = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();
            numOfMembers = members.size();
            for (MemberEntity member : members) {
                Calendar c = Calendar.getInstance();
                System.out.println("Member " + member.getName() + " join date is : " + member.getJoinDate());

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 730);
                Date churnDate = c.getTime();
                System.out.println("Inside members list");
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {
                    System.out.println("This member has purchases records of " + member.getPurchases().size());

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        System.out.println("Looping through purchases");
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        System.out.println("Number of days from churn date is " + days);
                        if (days > 0 && days < 365) {
                            totalPriceOfOrders += member.getPurchases().get(i).getAmountDue();
                            numOfOrders++;
                            break;
                        }
                    }
                } else {
                    System.out.println("This member has NO purchases records");
                }
            }
            DecimalFormat df = new DecimalFormat("#.00");
            System.out.println("average order per year is " + numOfOrders);
            return ((double) totalPriceOfOrders / (double) numOfOrders);
        } catch (Exception ex) {

            System.out.println("\nServer failed to list avg order price per year:\n" + ex);
            ex.printStackTrace();
        }
        return ((double) totalPriceOfOrders / (double) numOfOrders);
    }
    @Override
    public Double averageOrderPrice() {
        System.out.println("averageOrderPrice()");

        Integer numOfOrders = 0;
        Integer numOfMembers = 0;
        Integer numOfMembersNotChurn = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();
            numOfMembers = members.size();
            for (MemberEntity member : members) {
                Calendar c = Calendar.getInstance();
                System.out.println("Member " + member.getName() + " join date is : " + member.getJoinDate());

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 365);
                Date churnDate = c.getTime();
                System.out.println("Inside members list");
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {
                    System.out.println("This member has purchases records of " + member.getPurchases().size());

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        System.out.println("Looping through purchases");
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        System.out.println("Number of days from churn date is " + days);
                        if (days > 0) {
                            numOfMembersNotChurn++;
                            numOfOrders++;
                            break;
                        }
                    }
                } else {
                    System.out.println("This member has NO purchases records");
                }
            }
            DecimalFormat df = new DecimalFormat("#.00");
            System.out.println("average order per year is " + numOfOrders);
            return ((double) numOfOrders / (double) numOfMembers);
        } catch (Exception ex) {

            System.out.println("\nServer failed to list orders per year:\n" + ex);
            ex.printStackTrace();
        }
        return ((double) numOfOrders / (double) numOfMembers);
    }

    @Override
    public Double getCustomerRetentionRate() {
        System.out.println("getCustomerRetentionRate()");

        Integer numOfMembers = 0;
        Integer numOfMembersNotChurn = 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();
            numOfMembers = members.size();
            for (MemberEntity member : members) {
                Calendar c = Calendar.getInstance();
                System.out.println("Member " + member.getName() + " join date is : " + member.getJoinDate());

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 365);
                Date churnDate = c.getTime();
                System.out.println("Inside members list");
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {
                    System.out.println("This member has purchases records of " + member.getPurchases().size());

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        System.out.println("Looping through purchases");
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        System.out.println("Number of days from churn date is " + days);
                        if (days > 0) {
                            numOfMembersNotChurn++;
                            break;
                        }
                    }
                } else {
                    System.out.println("This member has NO purchases records");
                }
            }
            DecimalFormat df = new DecimalFormat("#.00");
            System.out.println("Num of numbers not churn is :  " + numOfMembersNotChurn + "num of members " + numOfMembers + " retention rate is " + (numOfMembersNotChurn / numOfMembers));
            return ((double) numOfMembersNotChurn / (double) numOfMembers);
        } catch (Exception ex) {
            
            System.out.println("\nServer failed to list retention rate:\n" + ex);
            ex.printStackTrace();
            return ((double) numOfMembersNotChurn / (double) numOfMembers);
        }
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
        System.out.println("getCustomerRecency()");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Integer days = 0;

        MemberEntity member = em.find(MemberEntity.class, memberId);
        if (member.getPurchases() != null && member.getPurchases().size() != 0) {
            System.out.println("This member has purchases records of " + member.getPurchases().size());

            List<Date> dates = new ArrayList<Date>();

            for (int i = 0; i < member.getPurchases().size(); i++) {
                System.out.println("Looping through purchases");
                dates.add(member.getPurchases().get(i).getCreatedDate());
            }
            Date latest = Collections.max(dates);
            System.out.println("Latest date for member :" + member.getName() + " is " + latest);

            Long numOfDaysBetween = date.getTime() - latest.getTime();
            System.out.println("Number of dates against today's date : " + TimeUnit.DAYS.convert(numOfDaysBetween, TimeUnit.MILLISECONDS));
            days = (int) (long) TimeUnit.DAYS.convert(numOfDaysBetween, TimeUnit.MILLISECONDS);

        } else {
            System.out.println("This member has NO purchases records");
        }
        return days;
    }

    @Override
    public Integer getCustomerFrequency(Long memberId) {
        System.out.println("getCustomerFrequency()");

        Integer numOfPurchases;
        MemberEntity member = em.find(MemberEntity.class, memberId);
        if (member.getPurchases() != null && member.getPurchases().size() != 0) {
            numOfPurchases = member.getPurchases().size();
        } else {
            System.out.println("This member has NO purchases records");
            return 0;
        }
        return numOfPurchases;
    }

    @Override
    public Integer getCustomerMonetaryValue(Long memberId) {
        System.out.println("getCustomerMonetaryValue()");

        Integer totalPriceOfPurchases = 0;
        MemberEntity member = em.find(MemberEntity.class, memberId);
        if (member.getPurchases() != null && member.getPurchases().size() != 0) {
            for (int i = 0; i < member.getPurchases().size(); i++) {
                totalPriceOfPurchases += member.getPurchases().get(i).getAmountDue().intValue();
            }
        } else {
            System.out.println("This member has NO purchases records");
            return 0;
        }
        return totalPriceOfPurchases;
    }

    @Override
    public Integer totalCummulativeSpendingOfAge(Integer startAge, Integer endAge) {
        System.out.println("totalCummulativeSpendingOfAge()");
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
    public Integer totalCummulativeSpendingOfJoinDate(Integer startDate, Integer endDate) {
        System.out.println("totalCummulativeSpendingOfJoinDate()");
        List<MemberEntity> members = accountManagementBean.listAllMember();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Integer days = 0;
        int totalCummulativeSpending = 0;
        for (MemberEntity member : members) {
            Long numOfDaysBetween = date.getTime() - member.getJoinDate().getTime();
            days = (int) (long) TimeUnit.DAYS.convert(numOfDaysBetween, TimeUnit.MILLISECONDS);
            if (days > startDate && days < endDate) {
                totalCummulativeSpending += member.getCummulativeSpending();
            }
        }
        System.out.println("totalCummulativeSpending is : " + totalCummulativeSpending);
        return totalCummulativeSpending;
    }

    @Override
    public Double getROfAge() {
        System.out.println("getROfAge()");
        SimpleRegression regression = new SimpleRegression();

        regression.addData(10, 10);
        List<MemberEntity> members = accountManagementBean.listAllMember();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Integer days = 0;
        int totalCummulativeSpending = 0;
        for (MemberEntity member : members) {
            regression.addData(member.getAge(), member.getCummulativeSpending());
        }
        System.out.println("Get R is : " + regression.getR());
        return regression.getR();
    }

    @Override
    public Double getStdErrorOfAge() {
        System.out.println("getStdErrorOfAge()");
        SimpleRegression regression = new SimpleRegression();

        regression.addData(10, 10);
        List<MemberEntity> members = accountManagementBean.listAllMember();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Integer days = 0;
        int totalCummulativeSpending = 0;
        for (MemberEntity member : members) {
            regression.addData(member.getAge(), member.getCummulativeSpending());
        }
        System.out.println("Get R is : " + regression.getSlopeStdErr());
        return regression.getR();
    }

    @Override
    public Double getRSquaredOfAge() {
        System.out.println("getRSquaredOfAge()");
        SimpleRegression regression = new SimpleRegression();

        regression.addData(10, 10);
        List<MemberEntity> members = accountManagementBean.listAllMember();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Integer days = 0;
        int totalCummulativeSpending = 0;
        for (MemberEntity member : members) {
            regression.addData(member.getAge(), member.getCummulativeSpending());
        }
        System.out.println("Get R squared is : " + regression.getRSquare());
        return regression.getRSquare();
    }

    @Override
    public Double getROfIncome() {
        System.out.println("getROfIncome()");
        SimpleRegression regression = new SimpleRegression();

        regression.addData(10, 10);
        List<MemberEntity> members = accountManagementBean.listAllMember();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Integer days = 0;
        int totalCummulativeSpending = 0;
        for (MemberEntity member : members) {
            regression.addData(member.getIncome(), member.getCummulativeSpending());
        }
        System.out.println("Get R is : " + regression.getR());
        return regression.getR();
    }

    @Override
    public Double getStdErrorOfIncome() {
        System.out.println("getStdErrorOfIncome()");
        SimpleRegression regression = new SimpleRegression();

        regression.addData(10, 10);
        List<MemberEntity> members = accountManagementBean.listAllMember();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Integer days = 0;
        int totalCummulativeSpending = 0;
        for (MemberEntity member : members) {
            regression.addData(member.getIncome(), member.getCummulativeSpending());
        }
        System.out.println("Get R is : " + regression.getSlopeStdErr());
        return regression.getR();
    }

    @Override
    public Double getRSquaredOfIncome() {
        System.out.println("getRSquaredOfIncome()");
        SimpleRegression regression = new SimpleRegression();

        regression.addData(10, 10);
        List<MemberEntity> members = accountManagementBean.listAllMember();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Integer days = 0;
        int totalCummulativeSpending = 0;
        for (MemberEntity member : members) {
            regression.addData(member.getIncome(), member.getCummulativeSpending());
        }
        System.out.println("Get R squared is : " + regression.getRSquare());
        return regression.getRSquare();
    }

    @Override
    public Integer totalCummulativeSpendingOfIncome(Integer startIncome, Integer endIncome) {
        System.out.println("totalCummulativeSpendingOfIncome()");
        List<MemberEntity> members = accountManagementBean.listAllMember();

        int totalCummulativeSpending = 0;
        for (MemberEntity member : members) {
            if (member.getIncome() > startIncome && member.getIncome() < endIncome) {
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
    public Integer numOfMembersInIncomeGroup(Integer startIncome, Integer endIncome) {
        System.out.println("numOfMembersInIncomeGroup()");
        List<MemberEntity> members = accountManagementBean.listAllMember();

        int numOfmembersInGroup = 0;
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getIncome() > startIncome && members.get(i).getIncome() < endIncome) {
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
                } else {
                    
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to list all non member sales records:\n" + ex);
            return profit;
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
