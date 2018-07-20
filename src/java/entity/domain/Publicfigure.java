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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "publicfigure")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publicfigure.findAll", query = "SELECT p FROM Publicfigure p")
    , @NamedQuery(name = "Publicfigure.findById", query = "SELECT p FROM Publicfigure p WHERE p.id = :id")
    , @NamedQuery(name = "Publicfigure.findByBrief", query = "SELECT p FROM Publicfigure p WHERE p.brief = :brief")
    , @NamedQuery(name = "Publicfigure.findByName", query = "SELECT p FROM Publicfigure p WHERE p.name = :name")})
public class Publicfigure implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "brief")
    private String brief;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "publicfigureId")
    private Collection<Question> questionCollection;
    @JoinColumn(name = "episode_id", referencedColumnName = "id")
    @ManyToOne
    private Episode episodeId;

    public Publicfigure() {
    }

    public Publicfigure(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
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

    public Episode getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Episode episodeId) {
        this.episodeId = episodeId;
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
        if (!(object instanceof Publicfigure)) {
            return false;
        }
        Publicfigure other = (Publicfigure) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.domain.Publicfigure[ id=" + id + " ]";
    }
    
}
