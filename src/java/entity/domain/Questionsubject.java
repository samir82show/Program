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
@Table(name = "questionsubject")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Questionsubject.findAll", query = "SELECT q FROM Questionsubject q")
    , @NamedQuery(name = "Questionsubject.findByQuestionSubject", query = "SELECT q FROM Questionsubject q WHERE q.questionSubject = :questionSubject")})
public class Questionsubject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "question_subject")
    private String questionSubject;
    @OneToMany(mappedBy = "questionsubjectQuestionSubject")
    private Collection<Question> questionCollection;

    public Questionsubject() {
    }

    public Questionsubject(String questionSubject) {
        this.questionSubject = questionSubject;
    }

    public String getQuestionSubject() {
        return questionSubject;
    }

    public void setQuestionSubject(String questionSubject) {
        this.questionSubject = questionSubject;
    }

    @XmlTransient
    public Collection<Question> getQuestionCollection() {
        return questionCollection;
    }

    public void setQuestionCollection(Collection<Question> questionCollection) {
        this.questionCollection = questionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionSubject != null ? questionSubject.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Questionsubject)) {
            return false;
        }
        Questionsubject other = (Questionsubject) object;
        if ((this.questionSubject == null && other.questionSubject != null) || (this.questionSubject != null && !this.questionSubject.equals(other.questionSubject))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.domain.Questionsubject[ questionSubject=" + questionSubject + " ]";
    }
    
}
