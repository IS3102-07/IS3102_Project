package OperationalCRM.LoyaltyAndRewards;

import EntityManager.LoyaltyTierEntity;
import HelperClasses.ReturnHelper;
import javax.ejb.Local;

@Local
public interface LoyaltyAndRewardsBeanLocal {
    public Integer getMemberLoyaltyPointsAmount(String email);
    public ReturnHelper createLoyaltyTier(String tier, Double amtRequiredPerAnnum);
    public ReturnHelper updateLoyaltyTier(Long tierID, Double amtRequiredPerAnnum);
    public ReturnHelper deleteLoyaltyTier(Long tierID);
    public LoyaltyTierEntity getLoyaltyTierByName(String name);
}
