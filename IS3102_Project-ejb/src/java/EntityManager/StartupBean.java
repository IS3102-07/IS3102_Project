package EntityManager;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import MRP.SalesAndOperationPlanning.SalesAndOperationPlanningBeanLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Singleton
@Startup
public class StartupBean {

    @EJB
    private AccountManagementBeanLocal accountManagementBean;

    @EJB
    private SalesAndOperationPlanningBeanLocal sopBean;

    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;

    @PostConstruct
    private void startup() {
        try {
            Query q = em.createQuery("SELECT t FROM RoleEntity t");
            List<RoleEntity> roleEntities = q.getResultList();
            // Don't insert anything if database appears to be initiated.
            if (roleEntities != null && roleEntities.size() > 0) {
                System.out.println("Skipping init of database, already initated.");
                return;
            }
            System.out.println("Starting to init database.");
            try {
                //Create roles
                RoleEntity roleEntity = new RoleEntity();
                roleEntity.create("Administrator", "System");
                em.persist(roleEntity);
                roleEntity = new RoleEntity();
                roleEntity.create("Regional Manager", "Region");
                em.persist(roleEntity);
                roleEntity = new RoleEntity();
                roleEntity.create("Warehouse Manager", "Facility");
                em.persist(roleEntity);
                roleEntity = new RoleEntity();
                roleEntity.create("Store Manager", "Facility");
                em.persist(roleEntity);
                roleEntity = new RoleEntity();
                roleEntity.create("Marketing Director", "Organization");
                em.persist(roleEntity);
                roleEntity = new RoleEntity();
                roleEntity.create("Product Development Engineer", "Organization");
                em.persist(roleEntity);
                roleEntity = new RoleEntity();
                roleEntity.create("Purchasing Manager", "Region");
                em.persist(roleEntity);
                roleEntity = new RoleEntity();
                roleEntity.create("Manufacturing Facility Manager", "Facility");
                em.persist(roleEntity);
                roleEntity = new RoleEntity();
                roleEntity.create("Cashier", "Facility");
                em.persist(roleEntity);
                roleEntity = new RoleEntity();
                roleEntity.create("Receptionist", "Facility");
                em.persist(roleEntity);
                roleEntity = new RoleEntity();
                roleEntity.create("Global Manager", "Organization");
                em.persist(roleEntity);
                roleEntity = new RoleEntity();
                roleEntity.create("Picker", "Facility");
                em.persist(roleEntity);
                System.out.println("RolesEntity init success.");
            } catch (Exception ex) {
                System.out.println("Skipping creating of roles:\n" + ex);
                ex.printStackTrace();
            }
            Long goldLoyaltyTierID = 1L;
            try {
                LoyaltyTierEntity loyaltyTierEntity;
                loyaltyTierEntity = new LoyaltyTierEntity("Gold", 5000.0);
                em.persist(loyaltyTierEntity);
                goldLoyaltyTierID = loyaltyTierEntity.getId();
                loyaltyTierEntity = new LoyaltyTierEntity("Silver", 3000.0);
                em.persist(loyaltyTierEntity);
                loyaltyTierEntity = new LoyaltyTierEntity("Bronze", 1000.0);
                em.persist(loyaltyTierEntity);
                loyaltyTierEntity = new LoyaltyTierEntity("Classic", 0.0);
                em.persist(loyaltyTierEntity);

            } catch (Exception ex) {
                System.out.println("Skipping creating of loyalty tiers\n" + ex);
                ex.printStackTrace();
            }
            try {
                //Create administrator account
                StaffEntity staffEntity = new StaffEntity();
                String passwordSalt = accountManagementBean.generatePasswordSalt();
                String passwordHash = accountManagementBean.generatePasswordHash(passwordSalt, "admin");
                staffEntity.create("S0000000A", "Administrator", "+65 65162727", "admin@if.com", "Island Furniture", passwordSalt, passwordHash);
                staffEntity.setAccountActivationStatus(true);
                List<RoleEntity> roles = new ArrayList();
                Query e = em.createQuery("SELECT t FROM RoleEntity t where t.name='Administrator'");
                RoleEntity roleEntity = (RoleEntity) e.getSingleResult();
                roles.add(roleEntity);
                staffEntity.setRoles(roles);
                em.persist(staffEntity);
                System.out.println("Created administrator with ID:admin@if.com and PW:admin.");
            } catch (Exception ex) {
                System.out.println("Skipping creating of administrator account:\n" + ex);
                ex.printStackTrace();
            }
            try {
                //Create cashier account
                StaffEntity staffEntity = new StaffEntity();
                String passwordSalt = accountManagementBean.generatePasswordSalt();
                String passwordHash = accountManagementBean.generatePasswordHash(passwordSalt, "cashier");
                staffEntity.create("S0000000A", "Cashier 1", "+65 65162727", "cashier1@if.com", "Island Furniture", passwordSalt, passwordHash);
                staffEntity.setAccountActivationStatus(true);
                List<RoleEntity> roles = new ArrayList();
                Query e = em.createQuery("SELECT t FROM RoleEntity t where t.name='Cashier'");
                RoleEntity roleEntity = (RoleEntity) e.getSingleResult();
                roles.add(roleEntity);
                staffEntity.setRoles(roles);
                em.persist(staffEntity);
                staffEntity = new StaffEntity();
                passwordSalt = accountManagementBean.generatePasswordSalt();
                passwordHash = accountManagementBean.generatePasswordHash(passwordSalt, "cashier");
                staffEntity.create("S0000000A", "Cashier 2", "+65 65162727", "cashier2@if.com", "Island Furniture", passwordSalt, passwordHash);
                staffEntity.setAccountActivationStatus(true);
                staffEntity.setRoles(roles);
                em.persist(staffEntity);

                //Create member account
                MemberEntity memberEntity = new MemberEntity();
                passwordSalt = accountManagementBean.generatePasswordSalt();
                passwordHash = accountManagementBean.generatePasswordHash(passwordSalt, "member");
                memberEntity.create("John", "Block 900 Newman Drive B1-09", new Date(), "john@hotmail.com", "8765434", null, "Weird City", "000012", passwordHash, passwordSalt);
                memberEntity.setAccountActivationStatus(true);
                memberEntity.setLoyaltyPoints(150);
                memberEntity.setLoyaltyCardId("F2E5A75D9000");
                LoyaltyTierEntity loyaltyTierEntity = em.getReference(LoyaltyTierEntity.class, goldLoyaltyTierID);
                memberEntity.setLoyaltyTier(loyaltyTierEntity);
                em.persist(memberEntity);
                memberEntity = new MemberEntity();
                passwordSalt = accountManagementBean.generatePasswordSalt();
                passwordHash = accountManagementBean.generatePasswordHash(passwordSalt, "member");
                memberEntity.create("Superman", "Block 984 Batman Drive B2-95", new Date(), "superman@hotmail.com", "999", null, "Unknown City", "006120", passwordHash, passwordSalt);
                memberEntity.setAccountActivationStatus(true);
                memberEntity.setLoyaltyPoints(500);
                memberEntity.setLoyaltyCardId("32D3A75D9000");
                memberEntity.setLoyaltyTier(loyaltyTierEntity);
                em.persist(memberEntity);
                System.out.println("Created member with ID:member@if.com and PW:member.");
            } catch (Exception ex) {
                System.out.println("Skipping creating of administrator account:\n" + ex);
                ex.printStackTrace();
            }
            try {
                //Create countries
                CountryEntity country = new CountryEntity("Singapore", "SGD", 1.25, 65);
                em.persist(country);
                country = new CountryEntity("Malaysia", "MYR", 3.0, 60);
                em.persist(country);
                country = new CountryEntity("Indonesia", "IDR", 120.0, 62);
                em.persist(country);
                country = new CountryEntity("United States", "USD", 1.0, 1);
                em.persist(country);
                country = new CountryEntity("China", "RMB", 6.13, 86);
                em.persist(country);
                country = new CountryEntity("France", "EUR", 0.798, 86);
                em.persist(country);
                //Create schedule                
                sopBean.createSchedule(2013, 1, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2013, 2, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2013, 3, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2013, 4, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2013, 5, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2013, 6, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2013, 7, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2013, 8, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2013, 9, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2013, 10, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2013, 11, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2013, 12, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2014, 1, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2014, 2, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2014, 3, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2014, 4, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2014, 5, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2014, 6, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2014, 7, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2014, 8, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2014, 9, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2014, 10, 5, 5, 5, 5, 0);
                sopBean.createSchedule(2014, 11, 0, 5, 5, 5, 5);

                System.out.println("Created country & schedule entities.");
            } catch (Exception ex) {
                System.out.println("Skipping creating of country & schedule entities:\n" + ex);
                ex.printStackTrace();
            }
            try {
                //Create regional office
                RegionalOfficeEntity regionalOfficeEntity;
                regionalOfficeEntity = new RegionalOfficeEntity();
                regionalOfficeEntity.create("Middle East Regional Office", "33 Dubai Lane", "686351234563", "MERO@if.com");
                em.persist(regionalOfficeEntity);
                regionalOfficeEntity = new RegionalOfficeEntity();
                regionalOfficeEntity.create("Asia Pacific Regional Office", "33 Jurong Town Hall Road #05-34", "61234563", "APACRO@if.com");
                em.persist(regionalOfficeEntity);
                //Create manufacturing facility & its warehouse
                ManufacturingFacilityEntity manufacturingFacilityEntity;
                WarehouseEntity warehouseEntity;
                q = em.createQuery("SELECT t FROM CountryEntity t where t.name='Singapore'");
                CountryEntity countryEntity = (CountryEntity) q.getSingleResult();

                manufacturingFacilityEntity = new ManufacturingFacilityEntity();
                manufacturingFacilityEntity.create("Manufacturing Facility SG1", "3 Jurong Industrial Park", "67183645", "MFSG1@islandfurniture.com", 10000);
                manufacturingFacilityEntity.setRegionalOffice(regionalOfficeEntity);
                em.persist(manufacturingFacilityEntity);
                warehouseEntity = new WarehouseEntity("Manufacturing Facility SG1 Warehouse", "3 Jurong Industrial Park", "67183645", "MFSG1@islandfurniture.com");
                warehouseEntity.setRegionalOffice(regionalOfficeEntity);
                warehouseEntity.setManufaturingFacility(manufacturingFacilityEntity);
                warehouseEntity.setCountry(countryEntity);
                em.persist(warehouseEntity);
                manufacturingFacilityEntity.setWarehouse(warehouseEntity);
                regionalOfficeEntity.getManufacturingFacilityList().add(manufacturingFacilityEntity);
                em.merge(manufacturingFacilityEntity);
                em.merge(regionalOfficeEntity);

                manufacturingFacilityEntity = new ManufacturingFacilityEntity();
                manufacturingFacilityEntity.create("Manufacturing Facility SG2", "26 Toh Guan Road", "67183664", "MFSG2@islandfurniture.com", 10000);
                manufacturingFacilityEntity.setRegionalOffice(regionalOfficeEntity);
                em.persist(manufacturingFacilityEntity);
                warehouseEntity = new WarehouseEntity("Manufacturing Facility SG2 Warehouse", "26 Toh Guan Road", "67183664", "MFSG2@islandfurniture.com");
                warehouseEntity.setRegionalOffice(regionalOfficeEntity);
                warehouseEntity.setManufaturingFacility(manufacturingFacilityEntity);
                warehouseEntity.setCountry(countryEntity);
                em.persist(warehouseEntity);
                manufacturingFacilityEntity.setWarehouse(warehouseEntity);
                regionalOfficeEntity.getManufacturingFacilityList().add(manufacturingFacilityEntity);
                em.merge(manufacturingFacilityEntity);
                em.merge(regionalOfficeEntity);
                System.out.println("Created manufacturing facilities & warehouse entities.");

                StoreEntity storeEntity;
                storeEntity = new StoreEntity("Queenstown Store", "317 Alexandra Rd", "67866868", "queens@if.com", countryEntity, "623633");
                storeEntity.setRegionalOffice(regionalOfficeEntity);
                warehouseEntity = new WarehouseEntity("Queenstown Store Warehouse", "317 Alexandra Rd, Singapore 159965", "67866868", "queens@if.com");
                warehouseEntity.setRegionalOffice(regionalOfficeEntity);
                warehouseEntity.setManufaturingFacility(manufacturingFacilityEntity);
                warehouseEntity.setCountry(countryEntity);
                em.persist(warehouseEntity);
                storeEntity.setWarehouse(warehouseEntity);
                em.persist(storeEntity);
                countryEntity.getStores().add(storeEntity);
                em.merge(countryEntity);
                storeEntity = new StoreEntity("Tampines Store", "60 Tampines North Drive", "67866868", "tampi@if.com", countryEntity, "640258");
                storeEntity.setRegionalOffice(regionalOfficeEntity);
                warehouseEntity = new WarehouseEntity("Tampines Store Warehouse", "60 Tampines North Drive", "67866868", "tampi@if.com");
                warehouseEntity.setRegionalOffice(regionalOfficeEntity);
                warehouseEntity.setManufaturingFacility(manufacturingFacilityEntity);
                warehouseEntity.setCountry(countryEntity);
                em.persist(warehouseEntity);
                storeEntity.setWarehouse(warehouseEntity);
                em.persist(storeEntity);
                countryEntity.getStores().add(storeEntity);
                em.merge(countryEntity);
                System.out.println("Created store facilities & warehouse entities.");
            } catch (Exception ex) {
                System.out.println("Skipping creating of facilities entities:\n" + ex);
                ex.printStackTrace();
            }

            try {
                ProductGroupEntity productGroup = new ProductGroupEntity("F001", 10, 500);
                em.persist(productGroup);
                productGroup = new ProductGroupEntity("F002", 15, 1000);
                em.persist(productGroup);
                productGroup = new ProductGroupEntity("F003", 20, 1000);
                em.persist(productGroup);
                System.out.println("Created item entities.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                //Create supplier
                q = em.createQuery("SELECT t FROM RegionalOfficeEntity t where t.name='Asia Pacific Regional Office'");
                RegionalOfficeEntity regionalOfficeEntity = (RegionalOfficeEntity) q.getSingleResult();
                SupplierEntity supplierEntity = new SupplierEntity("Supplier 1", "67911580", "supplier1@email.com", "231 Bukit Panjang Road", regionalOfficeEntity);
                q = em.createQuery("SELECT c from CountryEntity c where c.name='Singapore");
                CountryEntity country = (CountryEntity) q.getSingleResult();
                em.persist(country);
                supplierEntity.setCountry(country);
                em.persist(supplierEntity);
                regionalOfficeEntity.getSuppliers().add(supplierEntity);
                em.merge(regionalOfficeEntity);

                supplierEntity = new SupplierEntity("Supplier 2", "67911432", "supplier2@email.com", "3 Dover Road", regionalOfficeEntity);
                supplierEntity.setCountry(country);
                em.persist(supplierEntity);
                regionalOfficeEntity.getSuppliers().add(supplierEntity);
                em.merge(regionalOfficeEntity);

                supplierEntity = new SupplierEntity("Supplier 3", "67911433", "supplier3@email.com", "3 Bukit Timah Road", regionalOfficeEntity);
                supplierEntity.setCountry(country);
                em.persist(supplierEntity);
                regionalOfficeEntity.getSuppliers().add(supplierEntity);
                em.merge(regionalOfficeEntity);
                System.out.println("Created supplierEntity.");
            } catch (Exception ex) {
                System.out.println("Skipping creating of supplierEntity:\n" + ex);
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            System.out.println("Skipped init of database:\n" + ex);
            ex.printStackTrace();
        }

        try {
            //Create item
            FurnitureEntity furnitureEntity = new FurnitureEntity("F1", "Table 1", "Tables & Desks", "Pre-drilled holes for legs, for easy assembly. Adjustable feet make the table stand steady also on uneven floors", "imageURL", 100, 74, 60);
            em.persist(furnitureEntity);
            furnitureEntity = new FurnitureEntity("F2", "Bed 1", "Beds & Mattresses", "Get all-over This mattress is approved for children.Get all-over suppoall-over support and comfort with a resilient foam mattress.", "imageURL", 200, 10, 90);
            em.persist(furnitureEntity);
            furnitureEntity = new FurnitureEntity("F3", "Table 2", "Tables & Desks", "The table top in tempered glass is stain resistant and easy to clean. Adjustable feet make the table stand steady also on uneven floors.", "imageURL", 99, 71, 52);
            em.persist(furnitureEntity);
            RawMaterialEntity rawMaterialEntity1 = new RawMaterialEntity("RM1", "Steel", "Metal", "A piece of steel", 1, 1, 1);
            em.persist(rawMaterialEntity1);
            RawMaterialEntity rawMaterialEntity2 = new RawMaterialEntity("RM2", "Steel", "Metal", "A piece of steel", 1, 1, 1);
            em.persist(rawMaterialEntity2);
            MenuItemEntity menuItem1 = new MenuItemEntity("MI1", "Chicken Rice", "Main", "hao wei dao, bu jie shi", "url", 1, 1, 1);
            em.persist(menuItem1);
            MenuItemEntity menuItem2 = new MenuItemEntity("MI2", "Salad", "Sides", "hao wei dao, bu jie shi", "url", 1, 1, 1);
            em.persist(menuItem2);
            //Set lead time, lot size, price
//            Query q = em.createQuery("select t from SupplierEntity t where t.supplierName='Supplier 1'");
//            SupplierEntity supplierEntity = (SupplierEntity) q.getSingleResult();
//            Supplier_ItemEntity supplier_ItemEntity = new Supplier_ItemEntity(rawMaterialEntity, supplierEntity, 100.0, 1, 1);
//            em.persist(supplier_ItemEntity);
            //Tie it to a suppliier
//            supplierEntity.getSupplyingItems().add(supplier_ItemEntity);
            System.out.println("Created item entities.");
        } catch (Exception ex) {
            System.out.println("Skipping creating of item entities:\n" + ex);
            ex.printStackTrace();
        }

        try {
            //Item_Country pricing
            Query q = em.createQuery("Select c from CountryEntity c where c.name='Singapore'");
            CountryEntity c = (CountryEntity) q.getSingleResult();
            q = em.createQuery("Select i from ItemEntity i where i.SKU='F1'");
            ItemEntity i = (ItemEntity) q.getSingleResult();
            Item_CountryEntity item_country;
            item_country = new Item_CountryEntity();
            item_country.setCountry(c);
            item_country.setIsDeleted(false);
            item_country.setItem(i);
            item_country.setRetailPrice(100.0);
            em.persist(item_country);
            em.flush();

            q = em.createQuery("Select i from ItemEntity i where i.SKU='F2'");
            ItemEntity ii = (ItemEntity) q.getSingleResult();
            item_country = new Item_CountryEntity();
            item_country.setCountry(c);
            item_country.setIsDeleted(false);
            item_country.setItem(ii);
            item_country.setRetailPrice(200.0);
            em.persist(item_country);
            em.flush();

        } catch (Exception ex) {
            System.out.println("Skipping creating of Item_CountryEntities\n" + ex);
            ex.printStackTrace();
        }
        try {
            Query q1 = em.createQuery("select s from StoreEntity s");
            List<StoreEntity> storeList = (List<StoreEntity>) q1.getResultList();
            Query q2 = em.createQuery("select pg from ProductGroupEntity pg");
            List<ProductGroupEntity> productGroupList = (List<ProductGroupEntity>) q2.getResultList();
            Query q3 = em.createQuery("select s from MonthScheduleEntity s");
            List<MonthScheduleEntity> scheduleList = (List<MonthScheduleEntity>) q3.getResultList();
            Query q4 = em.createQuery("select m from MenuItemEntity m");
            List<MenuItemEntity> menuItemList = (List<MenuItemEntity>) q4.getResultList();

            int index = 1;
            int ind = 1;
            for (StoreEntity store : storeList) {
                for (MonthScheduleEntity schedule : scheduleList) {
                    for (int i = 0; i < productGroupList.size(); i++) {
                        try {
                            SalesFigureEntity saleFigure = new SalesFigureEntity();
                            saleFigure.setStore(store);
                            saleFigure.setProductGroup(productGroupList.get(i));
                            saleFigure.setSchedule(schedule);

                            if ((index % 4) == 0) {
                                saleFigure.setQuantity(30 + index * 3);
                            } else if ((index % 4) == 1) {
                                saleFigure.setQuantity(25 + index * 3);
                            } else if ((index % 4) == 2) {
                                saleFigure.setQuantity(30 + index * 3);
                            } else {
                                saleFigure.setQuantity(35 + index * 3);
                            }

                            if (schedule.getMonth() == 9) {
                                saleFigure.setQuantity(saleFigure.getQuantity() + 50);
                            }
                            index++;
                            em.persist(saleFigure);
                                                        
                            if(i==0){
                                SalesFigureLineItemEntity l = new SalesFigureLineItemEntity();
                                l.setSKU("F1");
                                l.setQuantity(saleFigure.getQuantity() / 2);
                                l.setSaleFigure(saleFigure);  
                                em.persist(l);
                                
                                SalesFigureLineItemEntity l1 = new SalesFigureLineItemEntity();
                                l1.setSKU("F3");
                                l1.setQuantity(saleFigure.getQuantity() - (saleFigure.getQuantity() / 2));
                                l1.setSaleFigure(saleFigure);
                                em.persist(l1);
                            }
                            if(i==1){
                                SalesFigureLineItemEntity l = new SalesFigureLineItemEntity();
                                l.setSKU("F2");
                                l.setQuantity(saleFigure.getQuantity());
                                l.setSaleFigure(saleFigure);
                                em.persist(l);
                            }
                            if(i==2){}

                        } catch (Exception ex) {
                        }
                    }

                    for (MenuItemEntity menuitem : menuItemList) {
                        try {
                            SalesFigureEntity saleFigure = new SalesFigureEntity();
                            saleFigure.setStore(store);
                            saleFigure.setMenuItem(menuitem);
                            saleFigure.setSchedule(schedule);

                            if ((ind % 5) == 0) {
                                saleFigure.setQuantity(20);
                            } else if ((ind % 5) == 1) {
                                saleFigure.setQuantity(25);
                            } else if ((ind % 5) == 2) {
                                saleFigure.setQuantity(35);
                            } else if ((ind % 5) == 3) {
                                saleFigure.setQuantity(40);
                            } else {
                                saleFigure.setQuantity(30);
                            }
                            ind++;
                            em.persist(saleFigure);
                        } catch (Exception ex) {
                        }
                    }

                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @PreDestroy
    private void shutdown() {
        System.out.println("Application is shutting down.");
    }
}
