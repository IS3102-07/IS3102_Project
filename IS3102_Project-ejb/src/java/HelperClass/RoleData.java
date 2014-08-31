package HelperClass;

import em.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class RoleData implements Serializable {

    private Long id;
    private String name;
    private String accessLevel;
    private Collection<MemberEntity> members = new ArrayList<MemberEntity>();

    public void create(Long id, String name, String accessLevel, Collection<MemberEntity> members) {
        this.setId(id);
        this.setName(name);
        this.setAccessLevel(accessLevel);
        this.setMembers(members);
    }
    public Long getId() {
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
        if (!(object instanceof RoleData)) {
            return false;
        }
        RoleData other = (RoleData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "em.RoleEntity[ id=" + id + " ]";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the accessLevel
     */
    public String getAccessLevel() {
        return accessLevel;
    }

    /**
     * @param accessLevel the accessLevel to set
     */
    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    /**
     * @return the members
     */
    public Collection<MemberEntity> getMembers() {
        return members;
    }

    /**
     * @param members the members to set
     */
    public void setMembers(Collection<MemberEntity> members) {
        this.members = members;
    }

}
