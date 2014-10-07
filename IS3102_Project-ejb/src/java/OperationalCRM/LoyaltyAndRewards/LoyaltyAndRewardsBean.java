/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OperationalCRM.LoyaltyAndRewards;

import CommonInfrastructure.AccountManagement.AccountManagementBeanLocal;
import EntityManager.MemberEntity;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class LoyaltyAndRewardsBean implements LoyaltyAndRewardsBeanLocal {

    @EJB
    AccountManagementBeanLocal AccountManagementBeanLocal;
    @PersistenceContext(unitName = "IS3102_Project-ejbPU")
    private EntityManager em;
    
    public Integer getMemberLoyaltyPointsAmount(String email) {
        MemberEntity memberEntity = AccountManagementBeanLocal.getMemberByEmail(email);
        if (memberEntity==null)
            return 0;
        else 
            return memberEntity.getLoyaltyPoints();
    }
}
