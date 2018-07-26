/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sawad
 */
@Entity
@Table(name = "competition_match")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompetitionMatch.findAll", query = "SELECT c FROM CompetitionMatch c")
    , @NamedQuery(name = "CompetitionMatch.findAllSorted", query = "SELECT c FROM CompetitionMatch c order by c.id ASC")
    , @NamedQuery(name = "CompetitionMatch.findById", query = "SELECT c FROM CompetitionMatch c WHERE c.id = :id")
    , @NamedQuery(name = "CompetitionMatch.findByScore1", query = "SELECT c FROM CompetitionMatch c WHERE c.score1 = :score1")
    , @NamedQuery(name = "CompetitionMatch.findByScore2", query = "SELECT c FROM CompetitionMatch c WHERE c.score2 = :score2")
    , @NamedQuery(name = "CompetitionMatch.findByStatus", query = "SELECT c FROM CompetitionMatch c WHERE c.status = :status")})
public class CompetitionMatch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "score1")
    private Long score1;
    @Column(name = "score2")
    private Long score2;
    @Column(name = "status")
    private Integer status;
    @Column(name = "current_stage")
    private Long currentStage;
    @JoinColumn(name = "episode_id", referencedColumnName = "id")
    @ManyToOne
    private Episode episodeId;
    @JoinColumn(name = "school1_name", referencedColumnName = "name")
    @ManyToOne
    private School school1Name;
    @JoinColumn(name = "school2_name", referencedColumnName = "name")
    @ManyToOne
    private School school2Name;

    public CompetitionMatch() {
    }

    public CompetitionMatch(Long id) {
        this.id = id;
    }

    public Long getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Long currentStage) {
        this.currentStage = currentStage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScore1() {
        return score1;
    }

    public void setScore1(Long score1) {
        this.score1 = score1;
    }

    public Long getScore2() {
        return score2;
    }

    public void setScore2(Long score2) {
        this.score2 = score2;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Episode getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Episode episodeId) {
        this.episodeId = episodeId;
    }

    public School getSchool1Name() {
        return school1Name;
    }

    public void setSchool1Name(School school1Name) {
        this.school1Name = school1Name;
    }

    public School getSchool2Name() {
        return school2Name;
    }

    public void setSchool2Name(School school2Name) {
        this.school2Name = school2Name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CompetitionMatch other = (CompetitionMatch) obj;
        if (!Objects.equals(this.currentStage, other.currentStage)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.score1, other.score1)) {
            return false;
        }
        if (!Objects.equals(this.score2, other.score2)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.episodeId, other.episodeId)) {
            return false;
        }
        if (!Objects.equals(this.school1Name, other.school1Name)) {
            return false;
        }
        if (!Objects.equals(this.school2Name, other.school2Name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CompetitionMatch{" + "id=" + id + ", score1=" + score1 + ", score2=" + score2 + ", status=" + status + ", currentStage=" + currentStage + ", episodeId=" + episodeId + ", school1Name=" + school1Name + ", school2Name=" + school2Name + '}';
    }

}
