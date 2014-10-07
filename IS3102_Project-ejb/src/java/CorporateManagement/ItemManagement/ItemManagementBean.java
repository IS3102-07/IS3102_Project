package CorporateManagement.ItemManagement;

import javax.ejb.Stateless;
import EntityManager.ItemEntity;
import EntityManager.RawMaterialEntity;
import EntityManager.ProductGroupEntity;
import EntityManager.RetailProductEntity;
import EntityManager.FurnitureEntity;
import EntityManager.BillOfMaterialEntity;
import EntityManager.CountryEntity;
import EntityManager.Item_CountryEntity;
import EntityManager.LineItemEntity;
import EntityManager.SupplierEntity;
import EntityManager.ProductGroupLineItemEntity;
import HelperClasses.ReturnHelper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remove;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ItemManagementBean implements ItemManagementBeanLocal {

    @PersistenceContext
    private EntityManager em;
    private SupplierEntity supplier;
    private RawMaterialEntity rawMaterial;

    public boolean addRawMaterial(String SKU, String name, String category, String description, Integer _length, Integer width, Integer height, Integer lotSize, Integer leadTime, Double price, Long supplierId) {
        System.out.println("addRawMaterial() called with SKU:" + SKU);
        try {
            rawMaterial = new RawMaterialEntity(SKU, name, category, description, _length, width, height, lotSize, leadTime, price);
            supplier = em.find(SupplierEntity.class, supplierId);
            rawMaterial.setSupplier(supplier);
            em.persist(rawMaterial);
            em.flush();
            em.merge(rawMaterial);
            supplier.getItems().add(rawMaterial);
            System.out.println("Raw Material name \"" + name + "\" added successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to add new raw material:\n" + ex);
            return false;
        }
    }

    public boolean editRawMaterial(String id, String SKU, String name, String category, String description, Integer lotSize, Integer leadTime, Double price, Long supplierId) {
        System.out.println("editRawMaterial() called with SKU:" + SKU);
        try {
            RawMaterialEntity i = em.find(RawMaterialEntity.class, Long.valueOf(id));
            i.setName(name);
            i.setCategory(category);
            i.setDescription(description);
            i.setLotSize(lotSize);
            i.setLeadTime(leadTime);
            i.setPrice(price);
            i.getSupplier().getItems().remove(i);
            SupplierEntity supplier1 = em.getReference(SupplierEntity.class, supplierId);
            i.setSupplier(supplier1);
            supplier1.getItems().add(i);
            em.flush();
            System.out.println("\nServer updated raw material:\n" + name);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to update raw material:\n" + ex);
            return false;
        }
    }

    @Override
    public boolean removeRawMaterial(String SKU) {
        System.out.println("removeRawMaterial() called with SKU:" + SKU);
        try {
            RawMaterialEntity rawMaterialEntity = em.getReference(RawMaterialEntity.class, Long.valueOf(SKU));
            rawMaterialEntity.setIsDeleted(true);
            em.merge(rawMaterialEntity);
            em.flush();
            System.out.println("removeRawMaterial(): Furniture removed succesfully");
            return true;
        } catch (EntityNotFoundException ex) {
            System.out.println("removeRawMaterial(): Failed to find SKU");
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove raw material:\n" + ex);
            return false;
        }
    }

    @Override
    public RawMaterialEntity viewRawMaterial(String SKU) {
        System.out.println("viewRawMaterial() called with SKU:" + SKU);
        try {
            Query q = em.createQuery("SELECT t FROM RawMaterialEntity t");

            for (Object o : q.getResultList()) {
                RawMaterialEntity i = (RawMaterialEntity) o;
                if (i.getSKU().equalsIgnoreCase(SKU) && i.getIsDeleted() == false) {
                    System.out.println("\nServer returns raw material:\n" + SKU);
                    return i;
                }
            }
            return null; //Could not find
        } catch (Exception ex) {
            System.out.println("\nServer failed to view raw material:\n" + ex);
            return null;
        }
    }

    @Override
    public boolean addFurniture(String SKU, String name, String category, String description, String imageURL, Integer _length, Integer width, Integer height) {
        System.out.println("addFurniture() called with SKU:" + SKU);
        try {
            FurnitureEntity furnitureEntity = new FurnitureEntity(SKU, name, category, description, imageURL, _length, width, height);
            em.persist(furnitureEntity);
            System.out.println("Furniture name \"" + name + "\" added successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to add new furniture:\n" + ex);
            return false;
        }
    }

    @Override
    public boolean editFurniture(String id, String SKU, String name, String category, String description, String imageURL) {
        System.out.println("editFurniture() called with SKU:" + SKU + " id : " + id);
        try {
            FurnitureEntity i = em.find(FurnitureEntity.class, Long.valueOf(id));
            System.out.println("i name is " + i.getName() + " image url is : " + imageURL);
            i.setName(name);
            i.setCategory(category);
            i.setDescription(description);
            i.setImageURL(imageURL);
            em.merge(i);
            em.flush();
            System.out.println("\nServer updated furniture:\n" + name);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to update furniture:\n" + ex);
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeFurniture(String SKU) {
        System.out.println("removeFurniture() called with SKU:" + SKU);
        try {
            FurnitureEntity furnitureEntity = em.getReference(FurnitureEntity.class, Long.valueOf(SKU));
            furnitureEntity.setIsDeleted(true);
            BillOfMaterialEntity billOfMaterialEntity = furnitureEntity.getBOM();
            billOfMaterialEntity.setFurniture(null);
            em.merge(billOfMaterialEntity);
            em.merge(furnitureEntity);
            em.flush();
            System.out.println("Furniture removed succesfully");
            return true;
        } catch (EntityNotFoundException ex) {
            System.out.println("removeFurniture(): Could not find furniture");
            return false;
        } catch (Exception ex) {
            System.out.println("removeFurniture(): Failed to remove furniture:\n" + ex);
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public FurnitureEntity viewFurniture(String SKU) {
        System.out.println("viewRawMaterial() called with SKU:" + SKU);
        try {
            Query q = em.createQuery("SELECT t FROM FurnitureEntity t where t.isDeleted=false");

            for (Object o : q.getResultList()) {
                FurnitureEntity i = (FurnitureEntity) o;
                if (i.getSKU().equalsIgnoreCase(SKU) && i.getIsDeleted() == false) {
                    System.out.println("\nServer returns furniture:\n" + SKU);
                    return i;
                }
            }
            return null; //Could not find
        } catch (Exception ex) {
            System.out.println("\nServer failed to view furniture:\n" + ex);
            return null;
        }
    }

    @Override
    public boolean addRetailProduct(String SKU, String name, String category, String description, String imageURL, Integer _length, Integer width, Integer height, Integer lotSize, Integer leadTime, Double price, Long supplierId) {
        System.out.println("addRetailProduct() called with SKU:" + SKU);
        try {
            RetailProductEntity retailProductEntity = new RetailProductEntity(SKU, name, category, description, imageURL, _length, width, height, lotSize, leadTime, price);
            supplier = em.find(SupplierEntity.class, supplierId);
            retailProductEntity.setSupplier(supplier);
            em.persist(retailProductEntity);
            em.flush();
            em.merge(retailProductEntity);
            supplier.getItems().add(retailProductEntity);
            System.out.println("Retail product name \"" + name + "\" added successfully.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to add new furniture:\n" + ex);
            return false;
        }
    }

    @Override
    public boolean editRetailProduct(String id, String SKU, String name, String category, String description, String imageURL, Integer lotSize, Integer leadTime, Double price, Long supplierId) {
        System.out.println("editRetailProduct() called with SKU:" + SKU);
        try {
            RetailProductEntity i = em.find(RetailProductEntity.class, Long.valueOf(id));
            i.setName(name);
            i.setCategory(category);
            i.setDescription(description);
            i.setImageURL(imageURL);
            i.setLotSize(lotSize);
            i.setLeadTime(leadTime);
            i.setPrice(price);
            i.getSupplier().getItems().remove(i);
            SupplierEntity supplier1 = em.getReference(SupplierEntity.class, supplierId);
            i.setSupplier(supplier1);
            supplier1.getItems().add(i);
            em.flush();
            System.out.println("\nServer updated retail product:\n" + name);
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to update retail product:\n" + ex);
            return false;
        }
    }

    @Override
    public boolean removeRetailProduct(String SKU) {
        System.out.println("removeRetailProduct() called with SKU:" + SKU);
        try {
            RetailProductEntity retailProductEntity = em.getReference(RetailProductEntity.class, Long.valueOf(SKU));
            retailProductEntity.setIsDeleted(true);
            em.merge(retailProductEntity);
            em.flush();
            System.out.println("Retail product removed succesfully");
            return true;
        } catch (EntityNotFoundException ex) {
            System.out.println("Failed to find SKU");
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove retail product:\n" + ex);
            return false;
        }
    }

    @Override
    public RetailProductEntity viewRetailProduct(String SKU) {
        System.out.println("viewRetailProduct() called with SKU:" + SKU);
        try {
            Query q = em.createQuery("SELECT t FROM RetailProductEntity t where t.isDeleted=false");

            for (Object o : q.getResultList()) {
                RetailProductEntity i = (RetailProductEntity) o;
                if (i.getSKU().equalsIgnoreCase(SKU)) {
                    System.out.println("\nServer returns retail product:\n" + SKU);
                    return i;
                }
            }
            return null; //Could not find
        } catch (Exception ex) {
            System.out.println("\nServer failed to view retail product:\n" + ex);
            return null;
        }
    }

    @Override
    public boolean createBOM(String name, String description) {//
        System.out.println("createBillOfMaterial() called with name:" + name);
        try {
            BillOfMaterialEntity BOM = new BillOfMaterialEntity();
            BOM.setDescription(description);
            BOM.setName(name);
            em.persist(BOM);

            System.out.println("Bill Of Material Name \"" + name + "\" registered successfully as id:" + BOM.getId());
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to createBOM():\n" + ex);
            return false;
        }
    }

    @Override
    public boolean editBOM(Long BOMId, String name, String description) {//
        System.out.println("editBOM() called with bill of material name:" + name + "and description: " + description);
        try {
            BillOfMaterialEntity BOM = em.find(BillOfMaterialEntity.class, BOMId);
            BOM.setName(name);
            BOM.setDescription(description);
            em.persist(BOM);
            System.out.println("editBOM() is successful.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to editBOM():\n" + ex);
            return false;
        }
    }

    @Override
    public boolean deleteBOM(Long BOMId) {
        System.out.println("deleteBillOfMaterial() called with bomName:" + BOMId);
        try {
            BillOfMaterialEntity BOM = em.find(BillOfMaterialEntity.class, BOMId);
            em.remove(BOM);
            System.out.println("deleteBillOfMaterial() is successful.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove bill of material:\n" + ex);
            return false;
        }
    }

    @Override
    public BillOfMaterialEntity viewSingleBOM(Long BOMId) {
        System.out.println("viewBillOfMaterial() called with id:" + BOMId);
        try {
            BillOfMaterialEntity BOM = em.find(BillOfMaterialEntity.class, BOMId);
            System.out.println("viewSingleBOM() is successful.");
            return BOM;
        } catch (Exception ex) {
            System.out.println("\nServer failed to view bill of material:\n" + ex);
            return null;
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean addLineItemToBOM(String SKU, Integer qty, Long BOMId) {
        System.out.println("addLineItemToBOM() called with id:" + BOMId);
        try {
            //if sku is not a raw material throw exception
            ItemEntity item = getItemBySKU(SKU);
            if (item.getType().equals("Raw Material")) {
                LineItemEntity lineItem = new LineItemEntity(item, qty, "");
                BillOfMaterialEntity BOM = em.find(BillOfMaterialEntity.class, BOMId);
                BOM.getListOfLineItems().add(lineItem);
                System.out.println("addLineItemToBOM() is successful.");
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception ex) {
            System.out.println("\nServer failed to addLineItemToBOM():\n" + ex);
            return false;
        }
    }

    @Override
    public boolean deleteLineItemFromBOM(Long lineItemId, Long BOMId) {
        System.out.println("deleteLineItemFromBOM() called with id:" + BOMId);
        try {
            LineItemEntity lineItem = em.find(LineItemEntity.class, lineItemId);
            BillOfMaterialEntity BOM = em.find(BillOfMaterialEntity.class, BOMId);
            BOM.getListOfLineItems().remove(lineItem);
            System.out.println("Line item id:" + lineItemId + " is removed from BOM");
            em.remove(lineItem);
            System.out.println("Line item id:" + lineItemId + " is removed from Line Item");
            System.out.println("deleteLineItemFromBOM() is successful.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to deleteLineItemFromBOM():\n" + ex);
            return false;
        }
    }

    @Override
    public boolean linkBOMAndFurniture(Long BOMId, Long FurnitureId) {
        System.out.println("linkBOMAndFurniture() called with id:" + BOMId);
        try {
            BillOfMaterialEntity BOM = em.find(BillOfMaterialEntity.class, BOMId);
            FurnitureEntity furniture = em.find(FurnitureEntity.class, FurnitureId);
            BOM.setFurniture(furniture);
            furniture.setBOM(BOM);
            System.out.println("linkBOMAndFurniture() is successful.");
            return true;
        } catch (Exception ex) {
            System.out.println("\nServer failed to linkBOMAndFurniture():\n" + ex);
            return false;
        }
    }

    @Override
    public List<RawMaterialEntity> listAllRawMaterials() {
        System.out.println("listAllRawMaterials() called.");
        try {
            Query q = em.createQuery("SELECT t FROM RawMaterialEntity t where t.isDeleted=false");
            List<RawMaterialEntity> rawMaterialEntity = q.getResultList();
            return rawMaterialEntity;
        } catch (Exception ex) {
            System.out.println("\nServer failed to list all raw materials:\n" + ex);
            return null;
        }
    }

    @Override
    public List<FurnitureEntity> listAllFurniture() {
        System.out.println("listAllFurniture() called.");
        try {
            Query q = em.createQuery("SELECT t FROM FurnitureEntity t where t.isDeleted=false");
            List<FurnitureEntity> furnitureEntity = q.getResultList();
            return furnitureEntity;
        } catch (Exception ex) {
            System.out.println("\nServer failed to list all furniture:\n" + ex);
            return null;
        }
    }

    @Override
    public List<FurnitureEntity> listAllFurnitureWithoutBOM() {
        System.out.println("listAllFurniture() called.");
        try {
            Query q = em.createQuery("Select fu from FurnitureEntity fu where fu.isDeleted=false and fu.id not in (Select f.id from FurnitureEntity f, BillOfMaterialEntity b where f.id=b.furniture.id)");
            List<FurnitureEntity> furnitureEntity = q.getResultList();
            return furnitureEntity;
        } catch (Exception ex) {
            System.out.println("\nServer failed to list all furniture:\n" + ex);
            return null;
        }
    }

    @Override
    public List<RetailProductEntity> listAllRetailProduct() {
        System.out.println("listAllRetailProduct() called.");
        try {
            Query q = em.createQuery("SELECT t FROM RetailProductEntity t where t.isDeleted=false");
            List<RetailProductEntity> retailProductEntity = q.getResultList();
            return retailProductEntity;
        } catch (Exception ex) {
            System.out.println("\nServer failed to list all retail product:\n" + ex);
            return null;
        }
    }

    @Override
    public List<BillOfMaterialEntity> listAllBOM() {
        System.out.println("listAllBOM() called.");
        try {
            Query q = em.createQuery("SELECT b FROM BillOfMaterialEntity b");
            List<BillOfMaterialEntity> billOfMaterialEntity = q.getResultList();
            System.out.println("listAllBOM() is successful.");
            return billOfMaterialEntity;
        } catch (Exception ex) {
            System.out.println("\nServer failed to list all BOM:\n" + ex);
            return null;
        }
    }

    @Override
    public ItemEntity getItemBySKU(String SKU) {
        System.out.println("getItemBySKU() called with SKU: " + SKU);
        try {
            Query q = em.createQuery("Select i from ItemEntity i where i.SKU=:SKU and i.isDeleted=false");
            q.setParameter("SKU", SKU);
            ItemEntity item = (ItemEntity) q.getSingleResult();
            System.out.println("getItemBySKU() is successful.");
            return item;
        } catch (Exception ex) {
            System.out.println("\nServer failed to getItemBySKU():\n" + ex);
            return null;
        }
    }

    @Override
    public boolean checkSKUExists(String SKU) {
        try {
            Query q = em.createQuery("Select i from ItemEntity i where i.SKU=:SKU and i.isDeleted=false");
            q.setParameter("SKU", SKU);
            q.getSingleResult();
            return true;
        } catch (NoResultException n) {
            System.out.println("\nServer return no result:\n" + n);
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to perform checkSKUExists:\n" + ex);
            return false;
        }
    }

    @Override
    @Remove
    public void remove() {
        System.out.println("Item Management Bean is removed.");
    }

    @Override
    public ProductGroupEntity createProductGroup(String name, Integer workhours, Integer lotSize) {
        try {
            Query q = em.createQuery("select pg from ProductGroupEntity pg where pg.productGroupName = ?1").setParameter(1, name);
            if (q.getResultList().isEmpty()) {
                ProductGroupEntity prouductGroup = new ProductGroupEntity(name, workhours, lotSize);
                em.persist(prouductGroup);
                return prouductGroup;
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean editProductGroup(Long productGroupID, String name, Integer workhours, Integer lotSize) {
        System.out.println("editProductGroup() called");
        try {
            Query q = em.createQuery("select pg from ProductGroupEntity pg where pg.productGroupName = ?1").setParameter(1, name);
            List<ProductGroupEntity> listOfProductGroupEntity = q.getResultList();
            ProductGroupEntity productGroupEntity = em.getReference(ProductGroupEntity.class, productGroupID);
            if (listOfProductGroupEntity == null || listOfProductGroupEntity.isEmpty() || productGroupEntity.getId().equals(productGroupID)) {
                productGroupEntity.setName(name);
                productGroupEntity.setWorkHours(workhours);
                productGroupEntity.setLotSize(lotSize);
                em.merge(productGroupEntity);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public ProductGroupEntity getProductGroup(Long id) {
        try {
            return em.find(ProductGroupEntity.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductGroupEntity> getAllProductGroup() {
        try {
            Query q = em.createQuery("select pg from ProductGroupEntity pg where pg.isDeleted=false");
            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public ProductGroupLineItemEntity createProductGroupLineItem(String SKU, double percent) {
        System.out.println("createProductGroupLineItem() called");
        try {
            Query q = em.createQuery("Select f from FurnitureEntity f where f.SKU=:SKU");
            q.setParameter("SKU", SKU);
            FurnitureEntity furniture = (FurnitureEntity) q.getSingleResult();
            ProductGroupLineItemEntity lineItem = new ProductGroupLineItemEntity();
            lineItem.setFurniture(furniture);
            lineItem.setPercent(percent);
            em.persist(lineItem);
            return lineItem;
        } catch (NoResultException ex) {
            System.out.println("Cuold not find furniture with SKU.");
        } catch (Exception ex) {
            System.out.println("Failed to createProductGroupLineItem()");
            ex.printStackTrace();
        }
        return null;
    }

    public Boolean deleteProductGroupLineItem(Long id) {
        try {
            em.remove(em.find(ProductGroupLineItemEntity.class, id));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean checkIfSKUIsFurniture(String SKU) {
        try {
            Query q = em.createQuery("Select i from ItemEntity i where i.SKU=:SKU");
            q.setParameter("SKU", SKU);
            ItemEntity itemEntity = (ItemEntity) q.getSingleResult();
            if (itemEntity.getType().equals("Furniture")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean editProductGroupLineItem(Long productGroupLineItemID, String SKU, double percent) {
        try {
            ProductGroupLineItemEntity lineItem = em.find(ProductGroupLineItemEntity.class, productGroupLineItemID);
            lineItem.setPercent(percent);
            Query q = em.createQuery("Select i from FurnitureEntity i where i.SKU=:SKU");
            q.setParameter("SKU", SKU);
            FurnitureEntity furnitureEntity = (FurnitureEntity) q.getSingleResult();
            lineItem.setFurniture(furnitureEntity);
            em.merge(lineItem);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean addLineItemToProductGroup(Long productGroupId, Long lineItemId) {
        try {
            ProductGroupEntity productGroup = em.find(ProductGroupEntity.class, productGroupId);
            ProductGroupLineItemEntity lineItem = em.find(ProductGroupLineItemEntity.class, lineItemId);
            lineItem.setProductGroup(productGroup);
            productGroup.getLineItemList().add(lineItem);
            em.merge(productGroup);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean removeLineItemFromProductGroup(Long productGroupId, Long lineItemId) {
        System.out.println("removeLineItemFromProductGroup() called");
        try {
            ProductGroupEntity productGroup = em.find(ProductGroupEntity.class, productGroupId);
            ProductGroupLineItemEntity lineItem = em.find(ProductGroupLineItemEntity.class, lineItemId);
            productGroup.getLineItemList().remove(lineItem);
            em.merge(productGroup);
            this.deleteProductGroupLineItem(lineItemId);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeProductGroup(Long productGroupID) {
        System.out.println("removeProductGroup() called with SKU:" + productGroupID);
        try {
            ProductGroupEntity productGroupEntity = em.getReference(ProductGroupEntity.class, productGroupID);
            productGroupEntity.setIsDeleted(true);
            em.merge(productGroupEntity);
            em.flush();
            System.out.println("Product Group removed succesfully");
            return true;
        } catch (EntityNotFoundException ex) {
            System.out.println("Product group not found");
            return false;
        } catch (Exception ex) {
            System.out.println("\nServer failed to remove product group:\n" + ex);
            return false;
        }
    }

    @Override
    public ReturnHelper addCountryItemPricing(Long countryId, String SKU, double price) {
        //can only have one item price per country
        System.out.println("addCountryItemPricing() called.");
        ReturnHelper helper = new ReturnHelper();
        try {
            Item_CountryEntity itemCountry = new Item_CountryEntity();
            itemCountry.setCountry(em.find(CountryEntity.class, countryId));
            itemCountry.setItem(getItemBySKU(SKU));
            itemCountry.setRetailPrice(price);
            em.persist(itemCountry);
            em.flush();

            System.out.println("addCountryItemPricing(): Successfully added.");

            helper.setMessage("Record Added Successfully");
            helper.setIsSuccess(true);
            return helper;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("addCountryItemPricing(): Failed to add.");
            helper.setMessage("Failed to add.");
            helper.setIsSuccess(false);
            return helper;
        }
    }

    @Override
    public ReturnHelper removeCountryItemPricing(Long countryItemId) {
        System.out.println("removeCountryItemPricing() called.");
        ReturnHelper helper = new ReturnHelper();
        try {
            Item_CountryEntity itemCountry = em.find(Item_CountryEntity.class, countryItemId);
            em.remove(itemCountry);
            System.out.println("removeCountryItemPricing(): Successful.");

            helper.setMessage("Record removed successfully.");
            helper.setIsSuccess(true);
            return helper;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("removeCountryItemPricing(): Failed to remove record.");
            helper.setMessage("Failed to remove record.");
            helper.setIsSuccess(false);
            return helper;
        }
    }

    @Override
    public ReturnHelper editCountryItemPricing(Long countryItemId, Long countryId, String SKU, double price) {
        System.out.println("editCountryItemPricing() called.");
        ReturnHelper helper = new ReturnHelper();
        try {
            Item_CountryEntity itemCountry = em.find(Item_CountryEntity.class, countryItemId);
            CountryEntity country = em.find(CountryEntity.class, countryId);
            itemCountry.setCountry(country);
            ItemEntity item = getItemBySKU(SKU);
            itemCountry.setItem(item);
            itemCountry.setRetailPrice(price);
            em.merge(itemCountry);
            em.flush();

            System.out.println("editCountryItemPricing(): Successful.");

            helper.setMessage("Record updated successfully.");
            helper.setIsSuccess(true);
            return helper;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("editCountryItemPricing(): Failed to update record.");
            helper.setMessage("Failed to update record.");
            helper.setIsSuccess(false);
            return helper;
        }
    }

    @Override
    public Item_CountryEntity getCountryItemPricing(Long countryItemId) {
        System.out.println("getCountryItemPricing() called.");
        try {
            Item_CountryEntity itemCountry = em.find(Item_CountryEntity.class, countryItemId);
            System.out.println("getCountryItemPricing(): Successful");
            return itemCountry;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("getCountryItemPricing(): Failed to retrieve record.");
            return null;
        }
    }
}
