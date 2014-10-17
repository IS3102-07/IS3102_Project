package OperationalCRM.LoyaltyAndRewards;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import EntityManager.LoyaltyTierEntity;
import EntityManager.MemberEntity;
import EntityManager.StoreEntity;
import HelperClasses.ReturnHelper;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class LoyaltyAndRewardsBean implements LoyaltyAndRewardsBeanLocal {

    @EJB
    AccountManagementBeanLocal AccountManagementBeanLocal;
    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;

    @Override
    public MemberEntity getMemberViaEmail(String email) {
        MemberEntity memberEntity = AccountManagementBeanLocal.getMemberByEmail(email);
        if (memberEntity == null) {
            return null;
        } else {
            return memberEntity;
        }
    }

    @Override
    public MemberEntity getMemberViaCard(String memberCard) {
        try {
            Query q = em.createQuery("SELECT t FROM MemberEntity t where t.loyaltyCardId=:memberCard");
            q.setParameter("memberCard", memberCard);
            MemberEntity memberEntity = (MemberEntity) q.getSingleResult();
            return memberEntity;
        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

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
            if (getLoyaltyTierByName(tier) != null) {
                return new ReturnHelper(false, "Failed to create tier, the chosen name has been used before.");
            }
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
    public LoyaltyTierEntity getLowestLevelTier() {
        try {
            LoyaltyTierEntity lowestTier = new LoyaltyTierEntity();
            Query q = em.createQuery("select t from LoyaltyTierEntity t where t.isDeleted=false ORDER BY t.amtOfSpendingRequired ASC");
            return (LoyaltyTierEntity) q.getResultList().get(0);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public LoyaltyTierEntity getMemberLoyaltyTier(String email) {
        System.out.println("getMemberLoyaltyTier() called");
        try {
            Query q = em.createQuery("select m from MemberEntity m where m.email=:email and m.isDeleted=false");
            MemberEntity memberEntity = (MemberEntity) q.getSingleResult();
            return memberEntity.getLoyaltyTier();
        } catch (NoResultException ex) {
            System.out.println("getMemberLoyaltyTier(): Staff with specified email not found");
            return getLowestLevelTier();
        } catch (Exception ex) {
            System.out.println("getMemberLoyaltyTier() failed.");
            ex.printStackTrace();
            return getLowestLevelTier();
        }
    }

    @Override
    public ReturnHelper updateMemberLoyaltyPointsAndTier(String email, Double amountPaid, Long storeID) {
        System.out.println("updateMemberLoyaltyPointsAndTier() called");
        ReturnHelper rh = new ReturnHelper(false, "Loyalty tier not updated");
        try {
            Query q = em.createQuery("select m from MemberEntity m where m.email=:email and m.isDeleted=false");
            q.setParameter("email", email);
            MemberEntity memberEntity = (MemberEntity) q.getSingleResult();
            memberEntity.setCummulativeSpending(memberEntity.getCummulativeSpending() + amountPaid);
            //Retrieve country for currency & exchange rate
            StoreEntity storeEntity;
            //Calculate points earned
            try {
                storeEntity = em.getReference(StoreEntity.class, storeID);
                int loyaltyPointsEarned = (int) Math.round(amountPaid / storeEntity.getCountry().getExchangeRate());
                memberEntity.setLoyaltyPoints(memberEntity.getLoyaltyPoints() + loyaltyPointsEarned);
            } catch (Exception ex) {
                System.out.println("createSalesRecord(): Error in retriving country");
                return new ReturnHelper(false, "System error in retriving country information.");
            }
            //Update to new tier (if neccessary)
            List<LoyaltyTierEntity> tiers = getAllLoyaltyTiers();
            for (LoyaltyTierEntity curr : tiers) {
                if (memberEntity.getCummulativeSpending() > curr.getAmtOfSpendingRequired()) {
                    memberEntity.setLoyaltyTier(curr);
                    rh = new ReturnHelper(true, "Congratulations! Your account have been upgraded to a new tier:" + curr.getTier());
                }
            }
            em.merge(memberEntity);
            return rh;
        } catch (NoResultException ex) {
            System.out.println("updateMemberLoyaltyPointsAndTier(): Staff with specified email not found");
            return new ReturnHelper(false, "Tier not updated, member account not found.");
        } catch (Exception ex) {
            System.out.println("updateMemberLoyaltyPointsAndTier(): failed.");
            ex.printStackTrace();
            return new ReturnHelper(false, "System error");
        }
    }

    @Override
    public ReturnHelper updateLoyaltyTier(Long tierID, Double amtRequiredPerAnnum) {
        System.out.println("updateLoyaltyTier() called");
        try {
            LoyaltyTierEntity loyaltyTierEntity = em.getReference(LoyaltyTierEntity.class, tierID);
            loyaltyTierEntity.setAmtOfSpendingRequired(amtRequiredPerAnnum);
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
            Query q = em.createQuery("SELECT t FROM LoyaltyTierEntity t where t.tier=:name ORDER BY t.amtOfSpendingRequired ASC");
            q.setParameter("name", name);
            LoyaltyTierEntity loyaltyTierEntity = (LoyaltyTierEntity) q.getSingleResult();
            return loyaltyTierEntity;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<LoyaltyTierEntity> getAllLoyaltyTiers() {
        System.out.println("getAllLoyaltyTiers() called");
        try {
            Query q = em.createQuery("SELECT t FROM LoyaltyTierEntity t where t.isDeleted=false");
            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList();
        }
    }

}
