package CommonInfrastructureModule;
import em.CountryEntity;
import em.ItemEntity;
import em.MemberEntity;
import em.LoyaltyTierEntity;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class CommonInfrastructureBean implements CommonInfrastructureBeanLocal {

    @PersistenceContext
    private EntityManager em;

    public CommonInfrastructureBean() {
        System.out.println("\nCommonInfrastructure Server (EJB) created.");
        //CountryEntity country = new CountryEntity();
        //country.create("Singapore", "Singapore Dollars");
        //ItemEntity item = new ItemEntity();
        //item.create("Item Name 1", "materialID thingy", "Item desscription", "http://", 5);
        
    }
    
    public String registerMember(String name, String type, String password, String phoneNumber, String email) {
        System.out.println("registerMember() called with:" + name + "," + type + ",password(not shown)," + email);
        long memberID;
        String msg;
        String tier;
        try {
            MemberEntity memberEntity = new MemberEntity();
            memberEntity.create(name, type, password);
            LoyaltyTierEntity loyaltyTierEntity = new LoyaltyTierEntity();
            loyaltyTierEntity.setLoyalty("Tier 1");//TODO: Types of tier string to pass in? or int?
            memberEntity.setLoyalty(loyaltyTierEntity);
            em.persist(memberEntity); // also cascade and sets loyaltyTier
            memberID = memberEntity.getMemberID();
            msg = "Member \"" + name + "\" registered successfully as memberID:" + memberID;
            System.out.println(msg);
            return msg;
        } catch (Exception ex) {
            msg = "\nServer failed to register member:\n" + ex;
            System.out.println(msg);
            return msg;
        }
    }
}
