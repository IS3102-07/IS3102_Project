package OperationalCRM.LoyaltyAndRewards;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import EntityManager.LoyaltyTierEntity;
import EntityManager.MemberEntity;
import HelperClasses.ReturnHelper;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class LoyaltyAndRewardsBean implements LoyaltyAndRewardsBeanLocal {

    @EJB
    AccountManagementBeanLocal AccountManagementBeanLocal;
    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;

    @Override
    public Integer getMemberLoyaltyPointsAmount(String email) {
        MemberEntity memberEntity = AccountManagementBeanLocal.getMemberByEmail(email);
        if (memberEntity == null) {
            return 0;
        } else {
            return memberEntity.getLoyaltyPoints();
        }
    }

    @Override
    public ReturnHelper createLoyaltyTier(String tier, Double amtRequiredPerAnnum) {
        try {
            System.out.println("createLoyaltyTier() called");
            if(getLoyaltyTierByName(tier)!=null)
                return new ReturnHelper(false, "Failed to create tier, the chosen name has been used before.");
            LoyaltyTierEntity loyaltyTierEntity = new LoyaltyTierEntity(tier, amtRequiredPerAnnum);
            em.persist(loyaltyTierEntity);
            return new ReturnHelper(true, "Tier created successfully.");
        } catch (Exception ex) {
            System.out.println("createLoyaltyTier(): failed. " + ex);
            ex.printStackTrace();
            return new ReturnHelper(false, "Failed to create tier.");
        }
    }

    @Override
    public ReturnHelper updateLoyaltyTier(Long tierID, Double amtRequiredPerAnnum) {
        System.out.println("updateLoyaltyTier() called");
        try {
        LoyaltyTierEntity loyaltyTierEntity = em.getReference(LoyaltyTierEntity.class, tierID);
        loyaltyTierEntity.setAmtRequiredPerAnnum(amtRequiredPerAnnum);
        em.merge(loyaltyTierEntity);
        return new ReturnHelper(true, "Tier updated successfully.");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ReturnHelper(false, "Failed to update tier. TierID not found.");
        }
    }

    @Override
    public ReturnHelper deleteLoyaltyTier(Long tierID) {
        System.out.println("deleteLoyaltyTier() called");
        try {
        LoyaltyTierEntity loyaltyTierEntity = em.getReference(LoyaltyTierEntity.class, tierID);
        loyaltyTierEntity.setIsDeleted(true);
        em.merge(loyaltyTierEntity);
        return new ReturnHelper(true, "Tier deleted successfully.");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ReturnHelper(false, "Failed to delete tier. TierID not found.");
        }
    }

    @Override
    public LoyaltyTierEntity getLoyaltyTierByName(String name) {
        System.out.println("getLoyaltyTierByName() called");
        try {
            Query q = em.createQuery("SELECT t FROM LoyaltyTierEntity t where t.tier=:name");
            q.setParameter("name", name);
            LoyaltyTierEntity loyaltyTierEntity = (LoyaltyTierEntity) q.getSingleResult();
            return loyaltyTierEntity;
        } catch (Exception ex) {
            return null;   
        }
    }

}
