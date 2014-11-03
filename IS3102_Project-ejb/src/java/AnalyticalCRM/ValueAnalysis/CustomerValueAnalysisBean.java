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
import EntityManager.MenuItemEntity;
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
import javax.persistence.TemporalType;
import org.apache.commons.math3.stat.regression.SimpleRegression;

@Stateful
public class CustomerValueAnalysisBean implements CustomerValueAnalysisBeanLocal {

    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;

    @EJB
    AccountManagementBeanLocal accountManagementBean;

    @EJB
    ItemManagementBeanLocal itemManagementBean;
    
        
    public Double getEstimatedCustomerLife(){    
        System.out.println("getEstimatedCustomerLife()");
        return 1/(1 - this.getAverageRetentionRate());
    }    
    
    public List<LineItemEntity> sortBestSellingMenuItem() {
        System.out.println("sortBestSellingMenuItem()");
        List<LineItemEntity> sortedMenuItem = new ArrayList();

        try {
            Query q = em.createQuery("SELECT t FROM MenuItemEntity t");
            List<MenuItemEntity> menuItems = q.getResultList();

            for (MenuItemEntity menuItem : menuItems) {
                LineItemEntity lineItem = new LineItemEntity();
                lineItem.setItem(menuItem);
                lineItem.setQuantity(0);
                sortedMenuItem.add(lineItem);
            }
            Query x = em.createQuery("SELECT t FROM SalesRecordEntity t");
            List<SalesRecordEntity> salesRecords = x.getResultList();

            for (SalesRecordEntity salesRecord : salesRecords) {
                if (salesRecord.getItemsPurchased().size() != 0) {
                    for (LineItemEntity lineItem : salesRecord.getItemsPurchased()) {
                        for (int i = 0; i < sortedMenuItem.size(); i++) {
                            if (lineItem.getItem().getId() == sortedMenuItem.get(i).getItem().getId()) {
                                sortedMenuItem.get(i).setQuantity(sortedMenuItem.get(i).getQuantity() + lineItem.getQuantity());
                                
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sortedMenuItem;
    }
    
    public Double getCustomerLifeTimeValue() {
        System.out.println("getCustomerLifeTimeValue()");
        return this.getEstimatedCustomerLife() * this.getAverageCustomerMonetaryValue();
    }
    
    public Double getAverageRetentionRate() {
        System.out.println("getAverageRetentionRate()");
        try {
            Double retentionRate_09 = this.getRetentionRateByYear(2009);
            Double retentionRate_10 = this.getRetentionRateByYear(2010);
            Double retentionRate_11 = this.getRetentionRateByYear(2011);
            Double retentionRate_12 = this.getRetentionRateByYear(2012);
            Double retentionRate_13 = this.getRetentionRateByYear(2013);
            
            return (retentionRate_09 + retentionRate_10 + retentionRate_11 + retentionRate_12 + retentionRate_13)/5 ;
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Double getRetentionRateByYear(Integer year) {
        System.out.println("getRetentionRateByYear()");
        try {
            int numberOfCustomerRetained = 0;
            Calendar cal = Calendar.getInstance();
            cal.clear();

            cal.set(Calendar.YEAR, year + 1);
            cal.set(Calendar.MONTH, 1);
            cal.set(Calendar.DAY_OF_MONTH, 1);

            Query q = em.createQuery("SELECT m FROM MemberEntity m where m.joinDate < ?1").setParameter(1, cal.getTime(), TemporalType.DATE);
            List<MemberEntity> members = q.getResultList();
            for (MemberEntity m : members) {

                Query q1 = em.createQuery("select s from SalesRecordEntity s where s.member.id = ?1 and s.createdDate > ?2 ")
                        .setParameter(1, m.getId())
                        .setParameter(2, cal.getTime(), TemporalType.DATE);
                if (!q1.getResultList().isEmpty()) {
                    numberOfCustomerRetained++;
                }
            }
            return (1.0 * numberOfCustomerRetained / q.getResultList().size());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public CustomerValueAnalysisBean() {
        System.out.println("\nCustomer Value Analysis Server (EJB) created.");
    }

    @Override
    public Boolean sendMemberLoyaltyPoints(List<MemberEntity> members, Integer loyaltyPoints) {

        return true;
    }

    @Override
    public Integer getRevenueOfJoinDate(Integer year) {
        System.out.println("getRevenueOfJoinDate()");

        Integer numOfMembers = 0;
        Integer numOfMembersNotChurn = 0;
        Double totalRevenue = (double) 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();
            numOfMembers = members.size();
            for (MemberEntity member : members) {
                Calendar c = Calendar.getInstance();

                Date date = new Date();
                c.setTime(date);
                c.add(Calendar.DATE, (-365 * year));
                Date churnDate = c.getTime();
                System.out.println("Today date minus off is " + churnDate);
                if (member.getJoinDate().getTime() > churnDate.getTime()) {
                    if (member.getPurchases() != null && member.getPurchases().size() != 0) {
                        for (int i = 0; i < member.getPurchases().size(); i++) {
                            totalRevenue += member.getPurchases().get(i).getAmountDue();
                        }
                    } else {
                        System.out.println("This member has NO purchases records");
                    }
                }
            }
            DecimalFormat df = new DecimalFormat("#.00");
            System.out.println("Total revenue is " + totalRevenue.intValue());
            return totalRevenue.intValue();
        } catch (Exception ex) {
            System.out.println("\nServer failed to list retention rate:\n" + ex);
            ex.printStackTrace();
            return totalRevenue.intValue();
        }
    }

    @Override
    public Integer numOfMembersInJoinDate(Integer year) {
        System.out.println("numOfMembersInJoinDate()");

        Integer numOfMembers = 0;
        Integer numOfMembersNotChurn = 0;
        Double totalRevenue = (double) 0;
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t");
            List<MemberEntity> members = q.getResultList();
            numOfMembers = members.size();
            for (MemberEntity member : members) {
                Calendar c = Calendar.getInstance();

                Date date = new Date();
                c.setTime(date);
                c.add(Calendar.DATE, (-365*year));
                Date churnDate = c.getTime();

                System.out.println("Churn date is " + churnDate);
                Long days = member.getJoinDate().getTime() - churnDate.getTime();
                days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                if (days > 0) {
                    numOfMembersNotChurn++;
                }
            }
            DecimalFormat df = new DecimalFormat("#.00");
            
            return numOfMembersNotChurn;
        } catch (Exception ex) {
            System.out.println("\nServer failed to list retention rate:\n" + ex);
            ex.printStackTrace();
            return numOfMembersNotChurn;
        }
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
                }
            }
           return ((double) numOfMembersNotChurn / (double) numOfMembers);
        } catch (Exception ex) {
            System.out.println("\nServer failed to list retention rate:\n" + ex);
            ex.printStackTrace();
            return ((double) numOfMembersNotChurn / (double) numOfMembers);
        }
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
            Query x = em.createQuery("SELECT t FROM SalesRecordEntity t");
            List<SalesRecordEntity> salesRecords = x.getResultList();

            for (SalesRecordEntity salesRecord : salesRecords) {
                if (salesRecord.getItemsPurchased().size() != 0) {
                    for (LineItemEntity lineItem : salesRecord.getItemsPurchased()) {
                        for (int i = 0; i < sortedFurnitures.size(); i++) {
                            if (lineItem.getItem().getId() == sortedFurnitures.get(i).getItem().getId()) {
                                sortedFurnitures.get(i).setQuantity(sortedFurnitures.get(i).getQuantity() + lineItem.getQuantity());
                                
                            }
                        }
                    }
                }
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
            Query x = em.createQuery("SELECT t FROM SalesRecordEntity t");
            List<SalesRecordEntity> salesRecords = x.getResultList();

            for (SalesRecordEntity salesRecord : salesRecords) {
                if (salesRecord.getItemsPurchased().size() != 0) {
                    for (LineItemEntity lineItem : salesRecord.getItemsPurchased()) {
                        for (int i = 0; i < sortedRetailProducts.size(); i++) {
                            if (lineItem.getItem().getId() == sortedRetailProducts.get(i).getItem().getId()) {
                                sortedRetailProducts.get(i).setQuantity(sortedRetailProducts.get(i).getQuantity() + lineItem.getQuantity());
                            }
                        }
                    }
                }
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

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 365);
                Date churnDate = c.getTime();
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        if (days > 0) {
                            retainedMembers.add(member);
                            numOfMembersNotChurn++;
                            break;
                        }
                    }
                } else {
                }
            }
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

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 365);
                Date churnDate = c.getTime();
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        if (days > 0) {
                            numOfMembersNotChurn++;
                            numOfOrders++;
                        }
                    }
                } else {
                }
            }
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

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 730);
                Date churnDate = c.getTime();
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        if (days > 0 && days < 365) {
                            numOfMembersNotChurn++;
                            numOfOrders++;
                        }
                    }
                } else {
                }
            }

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

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 365);
                Date churnDate = c.getTime();
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);;
                        if (days > 0) {
                            totalPriceOfOrders += member.getPurchases().get(i).getAmountDue();
                            numOfOrders++;
                            break;
                        }
                    }
                } else {
                }
            }
            return ((double) totalPriceOfOrders / (double) numOfOrders);
        } catch (Exception ex) {

            System.out.println("\nServer failed to list avg order price per acquired year:\n" + ex);
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

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 730);
                Date churnDate = c.getTime();
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        if (days > 0 && days < 365) {
                            totalPriceOfOrders += member.getPurchases().get(i).getAmountDue();
                            numOfOrders++;
                        }
                    }
                } else {
                }
            }
            DecimalFormat df = new DecimalFormat("#.00");
            return ((double) totalPriceOfOrders / (double) numOfOrders);
        } catch (Exception ex) {

            System.out.println("\nServer failed to list avg order price per retained member year:\n" + ex);
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

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 365);
                Date churnDate = c.getTime();
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        Long days = churnDate.getTime() - member.getPurchases().get(i).getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        if (days > 0) {
                            numOfMembersNotChurn++;
                            numOfOrders++;
                            break;
                        }
                    }
                } else {
                }
            }
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

                c.setTime(member.getJoinDate());
                c.add(Calendar.DATE, 365);
                Date churnDate = c.getTime();
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {

                    for (SalesRecordEntity record : member.getPurchases()) {
                        Long days = churnDate.getTime() - record.getCreatedDate().getTime();
                        days = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                        if (days > 0) {
                            numOfMembersNotChurn++;
                            break;
                        }
                    }
                } else {

                }
            }
            DecimalFormat df = new DecimalFormat("#.00");
            
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
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {
                    List<Date> dates = new ArrayList<Date>();
                    Date latest;

                    for (int i = 0; i < member.getPurchases().size(); i++) {
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
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to list recency:\n" + ex);
            ex.printStackTrace();
        }

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
            ex.printStackTrace();
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
                if (member.getPurchases() != null && member.getPurchases().size() != 0) {

                    for (int i = 0; i < member.getPurchases().size(); i++) {
                        numOfPurchases++;
                        amountOfPurchase += member.getPurchases().get(i).getAmountDue().intValue();
                    }
                } else {
                }
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to list monetary value:\n" + ex);
            ex.printStackTrace();
        }

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

            List<Date> dates = new ArrayList<Date>();

            for (int i = 0; i < member.getPurchases().size(); i++) {
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
    public Integer totalCummulativeSpendingOfCountry(String country) {
        System.out.println("totalCummulativeSpendingOfCountry()");
        List<MemberEntity> members = accountManagementBean.listAllMember();

        int totalCummulativeSpending = 0;
        for (MemberEntity member : members) {
            if (member.getCity().equalsIgnoreCase(country)) {
                totalCummulativeSpending += member.getCummulativeSpending();
            }
        }
        System.out.println("totalCummulativeSpendingOfCountry is : " + totalCummulativeSpending);
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
    public Integer numOfMembersInCountry(String country) {
        System.out.println("numOfMembersInCountry()");
        List<MemberEntity> members = accountManagementBean.listAllMember();

        int numOfmembersInGroup = 0;
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getCity().equalsIgnoreCase(country)) {
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
            ex.printStackTrace();
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
