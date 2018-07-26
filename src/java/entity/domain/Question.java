package entity.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q")
    , @NamedQuery(name = "Question.findById", query = "SELECT q FROM Question q WHERE q.id = :id")
    , @NamedQuery(name = "Question.findByContent", query = "SELECT q FROM Question q WHERE q.content = :content")
    , @NamedQuery(name = "Question.findByDirectanswer", query = "SELECT q FROM Question q WHERE q.directanswer = :directanswer")
    , @NamedQuery(name = "Question.findByHide", query = "SELECT q FROM Question q WHERE q.hide = :hide")
    , @NamedQuery(name = "Question.findByEpisodeStageHide", query = "SELECT q FROM Question q WHERE q.episodeId = :episodeId AND q.stageId = :stageId AND q.hide = :hide ORDER BY q.id ASC")
    , @NamedQuery(name = "Question.findByImagevideopath", query = "SELECT q FROM Question q WHERE q.imagevideopath = :imagevideopath")
    , @NamedQuery(name = "Question.findByMultiplecorrectanswer", query = "SELECT q FROM Question q WHERE q.multiplecorrectanswer = :multiplecorrectanswer")
    , @NamedQuery(name = "Question.findByMultipletruefalsedirectanswer", query = "SELECT q FROM Question q WHERE q.multipletruefalsedirectanswer = :multipletruefalsedirectanswer")
    , @NamedQuery(name = "Question.findByOption1", query = "SELECT q FROM Question q WHERE q.option1 = :option1")
    , @NamedQuery(name = "Question.findByOption2", query = "SELECT q FROM Question q WHERE q.option2 = :option2")
    , @NamedQuery(name = "Question.findByOption3", query = "SELECT q FROM Question q WHERE q.option3 = :option3")
    , @NamedQuery(name = "Question.findByTruefalsecorrectanswer", query = "SELECT q FROM Question q WHERE q.truefalsecorrectanswer = :truefalsecorrectanswer")
    , @NamedQuery(name = "Question.findByVideoImageNone", query = "SELECT q FROM Question q WHERE q.videoImageNone = :videoImageNone")})
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Size(max = 255)
    @Column(name = "content")
    private String content;
    @Size(max = 255)
    @Column(name = "directanswer")
    private String directanswer;
    @Size(max = 255)
    @Column(name = "hide")
    private String hide;
    @Size(max = 255)
    @Column(name = "imagevideopath")
    private String imagevideopath;
    @Size(max = 255)
    @Column(name = "multiplecorrectanswer")
    private String multiplecorrectanswer;
    @Size(max = 255)
    @Column(name = "multipletruefalsedirectanswer")
    private String multipletruefalsedirectanswer;
    @Size(max = 255)
    @Column(name = "option1")
    private String option1;
    @Size(max = 255)
    @Column(name = "option2")
    private String option2;
    @Size(max = 255)
    @Column(name = "option3")
    private String option3;
    @Size(max = 255)
    @Column(name = "truefalsecorrectanswer")
    private String truefalsecorrectanswer;
    @Size(max = 255)
    @Column(name = "video_image_none")
    private String videoImageNone;
    @JoinColumn(name = "episode_id", referencedColumnName = "id")
    @ManyToOne
    private Episode episodeId;
    @JoinColumn(name = "publicfigure_id", referencedColumnName = "id")
    @ManyToOne
    private Publicfigure publicfigureId;
    @JoinColumn(name = "questionpoint_id", referencedColumnName = "id")
    @ManyToOne
    private Questionpoint questionpointId;
    @JoinColumn(name = "questionsubject_question_subject", referencedColumnName = "question_subject")
    @ManyToOne
    private Questionsubject questionsubjectQuestionSubject;
    @JoinColumn(name = "stage_id", referencedColumnName = "id")
    @ManyToOne
    private Stage stageId;

    public Question() {
    }

    public Question(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDirectanswer() {
        return directanswer;
    }

    public void setDirectanswer(String directanswer) {
        this.directanswer = directanswer;
    }

    public String getHide() {
        return hide;
    }

    public void setHide(String hide) {
        this.hide = hide;
    }

    public String getImagevideopath() {
        return imagevideopath;
    }

    public void setImagevideopath(String imagevideopath) {
        this.imagevideopath = imagevideopath;
    }

    public String getMultiplecorrectanswer() {
        return multiplecorrectanswer;
    }

    public void setMultiplecorrectanswer(String multiplecorrectanswer) {
        this.multiplecorrectanswer = multiplecorrectanswer;
    }

    public String getMultipletruefalsedirectanswer() {
        return multipletruefalsedirectanswer;
    }

    public void setMultipletruefalsedirectanswer(String multipletruefalsedirectanswer) {
        this.multipletruefalsedirectanswer = multipletruefalsedirectanswer;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getTruefalsecorrectanswer() {
        return truefalsecorrectanswer;
    }

    public void setTruefalsecorrectanswer(String truefalsecorrectanswer) {
        this.truefalsecorrectanswer = truefalsecorrectanswer;
    }

    public String getVideoImageNone() {
        return videoImageNone;
    }

    public void setVideoImageNone(String videoImageNone) {
        this.videoImageNone = videoImageNone;
    }

    public Episode getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Episode episodeId) {
        this.episodeId = episodeId;
    }

    public Publicfigure getPublicfigureId() {
        return publicfigureId;
    }

    public void setPublicfigureId(Publicfigure publicfigureId) {
        this.publicfigureId = publicfigureId;
    }

    public Questionpoint getQuestionpointId() {
        return questionpointId;
    }

    public void setQuestionpointId(Questionpoint questionpointId) {
        this.questionpointId = questionpointId;
    }

    public Questionsubject getQuestionsubjectQuestionSubject() {
        return questionsubjectQuestionSubject;
    }

    public void setQuestionsubjectQuestionSubject(Questionsubject questionsubjectQuestionSubject) {
        this.questionsubjectQuestionSubject = questionsubjectQuestionSubject;
    }

    public Stage getStageId() {
        return stageId;
    }

    public void setStageId(Stage stageId) {
        this.stageId = stageId;
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
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.domain.Question[ id=" + id + " ]";
    }
    
}
