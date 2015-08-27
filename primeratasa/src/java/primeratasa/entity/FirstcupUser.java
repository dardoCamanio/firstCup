/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primeratasa.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dardo
 */
@Entity
@NamedQuery(name="findAverangeAgeDifferenceOfAllPrimeraTasaUsers",
        query="SELECT AVG(u.ageDifference) FROM FirstcupUser u")
public class FirstcupUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    protected Calendar birthday;
    protected int ageDifference;

    public FirstcupUser() {
    }

    public FirstcupUser(Date date, int ageDifference) {
        Calendar cal=new GregorianCalendar();
        cal.setTime(date);  
        this.setBirthday(cal);
        this.setAgeDifference(ageDifference);
    }
    
    
    
    
    //-----------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public int getAgeDifference() {
        return ageDifference;
    }
    
    public void setAgeDifference(int ageDifference) {
        this.ageDifference = ageDifference;
    }
    //-------------------------------------------------------
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FirstcupUser)) {
            return false;
        }
        FirstcupUser other = (FirstcupUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "primeratasa.entity.FirstcupUser[ id=" + id + " ]";
    }

    
}
