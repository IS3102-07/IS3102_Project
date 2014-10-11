package EntityManager;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class SalesRecordEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdDate;
    private Double paymentAmount;
    private Double loyaltyPaymentAmount;
    private String currency;
    private String posName;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<LineItemEntity> itemsPurchased;
    @ManyToOne
    private MemberEntity member;
    @ManyToOne
    private StoreEntity store;

    public SalesRecordEntity() {
    }

    public SalesRecordEntity(MemberEntity member, Double paymentAmount, Double loyaltyPaymentAmount, String currency, String posName, List<LineItemEntity> itemsPurchased) {
        this.createdDate = new Date();
        this.member = member;
        this.paymentAmount = paymentAmount;
        this.loyaltyPaymentAmount = loyaltyPaymentAmount;
        this.currency = currency;
        this.posName = posName;
        this.itemsPurchased = itemsPurchased;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StoreEntity getStore() {
        return store;
    }

    public void setStore(StoreEntity store) {
        this.store = store;
    }

    public Double getLoyaltyPaymentAmount() {
        return loyaltyPaymentAmount;
    }

    public void setLoyaltyPaymentAmount(Double loyaltyPaymentAmount) {
        this.loyaltyPaymentAmount = loyaltyPaymentAmount;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public List<LineItemEntity> getItemsPurchased() {
        return itemsPurchased;
    }

    public void setItemsPurchased(List<LineItemEntity> itemsPurchased) {
        this.itemsPurchased = itemsPurchased;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
