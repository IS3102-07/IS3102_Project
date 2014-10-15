package OperationalCRM.LoyaltyAndRewards;

import EntityManager.MemberEntity;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "LoyaltyAndRewardsWebService")
@Stateless
public class LoyaltyAndRewardsWebService {

    @EJB
    LoyaltyAndRewardsBeanLocal LoyaltyAndRewardsBeanLocal;

    
    @WebMethod
    public Integer getMemberLoyaltyPoints(@WebParam(name = "memberEmail") String memberEmail) {
        return LoyaltyAndRewardsBeanLocal.getMemberLoyaltyPointsAmount(memberEmail);
    }
    
    @WebMethod
    public MemberEntity getMember(@WebParam(name = "memberEmail") String memberEmail) {
        return LoyaltyAndRewardsBeanLocal.getMember(memberEmail);
    }

}
