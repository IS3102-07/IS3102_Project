/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Administrator
 */
@Entity
public class MonthScheduleEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar date;
    private Integer year;
    private Integer month;
    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "schedule")
    private List<SaleAndOperationPlanEntity> sopList;

    public MonthScheduleEntity() {
        this.sopList = new ArrayList<>();
    }

    ;

    public MonthScheduleEntity(Calendar date) {
        this.date = date;
        this.year = date.get(Calendar.YEAR);
        this.month = date.get(Calendar.MONTH);
        this.sopList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public List<SaleAndOperationPlanEntity> getSopList() {
        return sopList;
    }

    public void setSopList(List<SaleAndOperationPlanEntity> sopList) {
        this.sopList = sopList;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
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
        if (!(object instanceof MonthScheduleEntity)) {
            return false;
        }
        MonthScheduleEntity other = (MonthScheduleEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityManager.MonthScheduleEntity[ id=" + id + " ]";
    }

}
