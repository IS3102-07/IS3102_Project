package OperationalCRM.CustomerInformationManagement;

import javax.ejb.Stateless;
import EntityManager.ShoppingListEntity;
import EntityManager.FurnitureEntity;
import EntityManager.MemberEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerInformationManagementBean implements CustomerInformationManagementBeanLocal {

    @PersistenceContext
    private EntityManager em;

    public Boolean addFurnitureToList(Integer furnitureId, Integer memberId) {
        FurnitureEntity furniture = em.find(FurnitureEntity.class, furnitureId);
        MemberEntity member = em.find(MemberEntity.class, memberId);
        
        
        
        return true;
    }
}
