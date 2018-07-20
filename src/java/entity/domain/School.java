/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sawad
 */
@Entity
@Table(name = "school")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "School.findAll", query = "SELECT s FROM School s")
    , @NamedQuery(name = "School.findByName", query = "SELECT s FROM School s WHERE s.name = :name")
    , @NamedQuery(name = "School.findByContactperson", query = "SELECT s FROM School s WHERE s.contactperson = :contactperson")
    , @NamedQuery(name = "School.findByPhones", query = "SELECT s FROM School s WHERE s.phones = :phones")})
public class School implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @Size(max = 255)
    @Column(name = "contactperson")
    private String contactperson;
    @Size(max = 255)
    @Column(name = "phones")
    private String phones;
    @OneToMany(mappedBy = "school1Name")
    private Collection<CompetitionMatch> competitionMatchCollection;
    @OneToMany(mappedBy = "school2Name")
    private Collection<CompetitionMatch> competitionMatchCollection1;

    public School() {
    }

    public School(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    @XmlTransient
    public Collection<CompetitionMatch> getCompetitionMatchCollection() {
        return competitionMatchCollection;
    }

    public void setCompetitionMatchCollection(Collection<CompetitionMatch> competitionMatchCollection) {
        this.competitionMatchCollection = competitionMatchCollection;
    }

    @XmlTransient
    public Collection<CompetitionMatch> getCompetitionMatchCollection1() {
        return competitionMatchCollection1;
    }

    public void setCompetitionMatchCollection1(Collection<CompetitionMatch> competitionMatchCollection1) {
        this.competitionMatchCollection1 = competitionMatchCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof School)) {
            return false;
        }
        School other = (School) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
