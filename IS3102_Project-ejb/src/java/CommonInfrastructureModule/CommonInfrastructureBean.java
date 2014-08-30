package CommonInfrastructureModule;
import em.CountryEntity;
import em.ItemCountryEntity;
import em.ItemEntity;
import em.MemberEntity;
import em.LoyaltyTierEntity;
import java.util.Collection;
import java.util.Date;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
public class CommonInfrastructureBean implements CommonInfrastructureBeanLocal {

    @PersistenceContext
    private EntityManager em;

    public CommonInfrastructureBean() {
        System.out.println("\nCommonInfrastructure Server (EJB) created.");
        
        /*
        //Test
        String msg;
        //Create country
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.create("Singapore", "Singapore Dollars");
        em.persist(countryEntity);
        countryEntity = new CountryEntity();
        countryEntity.create("Malaysia", "Malaysian Ringget");
        em.persist(countryEntity);
        //Create item
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.create("Chair", "materialID thingy", "This is a simple and cheap chair designed for sitting", "http://");
        em.persist(itemEntity);
        
        //Set the item price
        Query q;
        String country = "Singapore";
        Integer retailPrice = 5;
        
        q = em.createQuery("SELECT t FROM ItemEntity t WHERE t.id=:id");
        q.setParameter("id", 1);
        itemEntity = (ItemEntity) q.getSingleResult();
        q = em.createQuery("SELECT t FROM CountryEntity t where t.name=:countryName");
        q.setParameter("countryName", country);
        countryEntity = (CountryEntity) q.getSingleResult();
        
        country = "Malaysia";
        retailPrice = 10;
        
        q = em.createQuery("SELECT t FROM ItemEntity t WHERE t.id=:id");
        q.setParameter("id", 1);
        itemEntity = (ItemEntity) q.getSingleResult();
        q = em.createQuery("SELECT t FROM CountryEntity t where t.name=:countryName");
        q.setParameter("countryName", country);
        countryEntity = (CountryEntity) q.getSingleResult();
        try {
            // Create the country specific pricing entry
            ItemCountryEntity itemCountryEntity = new ItemCountryEntity();
            itemCountryEntity.create(countryEntity, retailPrice);
            em.persist(itemCountryEntity);
            
            // Add it into the item list & country list
            Collection<ItemCountryEntity> itemCountryList;
            itemCountryList = itemEntity.getItemCountryList();
            itemCountryList.add(itemCountryEntity);
            itemEntity.setItemCountryList(itemCountryList);
            em.persist(itemEntity);
           
        } catch (Exception ex) {
            msg = "\nServer error while adding a book.\n";
            System.out.println(msg);
        }*/
    }
    
    public void registerMember(String name, String address, Date DOB, String email, Integer phone, String country, String city, Integer zipCode, String username, String password){
        System.out.println("registerMember() called with:" + name);
        long memberID;
        String msg;
        try {
            MemberEntity memberEntity = new MemberEntity();
            memberEntity.create(name, address, DOB, email, phone, country, city, zipCode, username, password);
            LoyaltyTierEntity loyaltyTierEntity = new LoyaltyTierEntity();
            loyaltyTierEntity.setLoyalty("Tier 1");//TODO: Types of tier string to pass in? or int?
            memberEntity.setLoyalty(loyaltyTierEntity);
            em.persist(memberEntity); // also cascade and sets loyaltyTier
            memberID = memberEntity.getMemberID();
            msg = "Member \"" + name + "\" registered successfully as memberID:" + memberID;
            System.out.println(msg);
        } catch (Exception ex) {
            msg = "\nServer failed to register member:\n" + ex;
            System.out.println(msg);
        }
    }
}
