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

@Entity
@Table(name = "episode")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Episode.findAll", query = "SELECT e FROM Episode e")
    , @NamedQuery(name = "Episode.findById", query = "SELECT e FROM Episode e WHERE e.id = :id")
    , @NamedQuery(name = "Episode.findByName", query = "SELECT e FROM Episode e WHERE e.name = :name")})
public class Episode implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "episodeId")
    private Collection<Question> questionCollection;
    @OneToMany(mappedBy = "episodeId")
    private Collection<Publicfigure> publicfigureCollection;
    @OneToMany(mappedBy = "episodeId")
    private Collection<CompetitionMatch> competitionMatchCollection;

    public Episode() {
    }

    public Episode(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Question> getQuestionCollection() {
        return questionCollection;
    }

    public void setQuestionCollection(Collection<Question> questionCollection) {
        this.questionCollection = questionCollection;
    }

    @XmlTransient
    public Collection<Publicfigure> getPublicfigureCollection() {
        return publicfigureCollection;
    }

    public void setPublicfigureCollection(Collection<Publicfigure> publicfigureCollection) {
        this.publicfigureCollection = publicfigureCollection;
    }

    @XmlTransient
    public Collection<CompetitionMatch> getCompetitionMatchCollection() {
        return competitionMatchCollection;
    }

    public void setCompetitionMatchCollection(Collection<CompetitionMatch> competitionMatchCollection) {
        this.competitionMatchCollection = competitionMatchCollection;
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
        if (!(object instanceof Episode)) {
            return false;
        }
        Episode other = (Episode) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
