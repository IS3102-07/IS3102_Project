package StoreTransaction.RetailInventoryControl;

import javax.ejb.Stateless;
import EntityManager.ItemEntity;
import EntityManager.RawMaterialEntity;
import EntityManager.RetailProductEntity;
import EntityManager.FurnitureEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class RetailInventoryControlBean implements RetailInventoryControlBeanLocal {

    @PersistenceContext
    private EntityManager em;

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
}
