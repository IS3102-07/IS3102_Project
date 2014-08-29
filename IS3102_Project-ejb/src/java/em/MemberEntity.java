package em;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MemberEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;   
    private String name;
    private String type;
    private String hashedPassword;
    private LoyaltyTierEntity loyaltyTier;
    
    public void create(String name, String type, String password){
        this.name=name;
        this.type=type;
        this.hashedPassword=password; //Todo HASHING password functtionality
    }
    
    public void setLoyalty(LoyaltyTierEntity loyaltyTier) {
        this.loyaltyTier = loyaltyTier;
    }
    public Long getMemberID() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MemberEntity)) {
            return false;
        }
        MemberEntity other = (MemberEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityManagerBean.Customer[ id=" + id + " ]";
    }
    
}
