/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CorporateManagement.ItemManagement;

import EntityManager.FurnitureEntity;
import EntityManager.ItemEntity;
import EntityManager.BillOfMaterialEntity;
import EntityManager.RawMaterialEntity;
import EntityManager.ProductionGroupEntity;
import EntityManager.RetailProductEntity;
import javax.ejb.Local;

/**
 *
 * @author Loi Liang Yang
 */
@Local
public interface ItemManagementBeanLocal {
    
    public boolean addRawMaterial(String name);
    public boolean editRawMaterial(String name);
    public boolean removeRawMaterial(String name);
    public RawMaterialEntity viewRawMaterial(String name);
    
    public boolean addFurniture(String name);
    public boolean editFurniture(String name);
    public boolean removeFurniture(String name);
    public FurnitureEntity viewFurniture(String name);
    
    public boolean addRetailProduct(String name);
    public boolean editRetailProduct(String name);
    public boolean removeRetailProduct(String name);
    public RetailProductEntity viewRetailProduct(String name);   
    
    public boolean createBillOfMaterial(String name);
    public boolean editBillOfMaterial(String name);
    public boolean deleteBillOfMaterial(String bomName);
    public BillOfMaterialEntity viewBillOfMaterial(String name); 
    
    public boolean createProductionGroup(String name);
    public boolean editProductionGroup(String name);
    public boolean deleteProductionGroup(String bomName);
    public ProductionGroupEntity viewProductionGroup(String name); 
    /*
    public boolean addItem(String name, String materialID, String description, String imageURL);
    public boolean editItem(String name, String materialID, String description, String imageURL);
    public boolean removeItem(String itemName);
    public ItemEntity viewItem(String itemName);
    */
}
