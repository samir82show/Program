package entity.domain;

import entity.domain.Episode;
import entity.domain.Publicfigure;
import entity.domain.Questionpoint;
import entity.domain.Questionsubject;
import entity.domain.Stage;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-26T15:19:56")
@StaticMetamodel(Question.class)
public class Question_ { 

    public static volatile SingularAttribute<Question, Publicfigure> publicfigureId;
    public static volatile SingularAttribute<Question, Questionpoint> questionpointId;
    public static volatile SingularAttribute<Question, String> multiplecorrectanswer;
    public static volatile SingularAttribute<Question, String> imagevideopath;
    public static volatile SingularAttribute<Question, Episode> episodeId;
    public static volatile SingularAttribute<Question, String> content;
    public static volatile SingularAttribute<Question, String> multipletruefalsedirectanswer;
    public static volatile SingularAttribute<Question, String> directanswer;
    public static volatile SingularAttribute<Question, Questionsubject> questionsubjectQuestionSubject;
    public static volatile SingularAttribute<Question, String> hide;
    public static volatile SingularAttribute<Question, String> option3;
    public static volatile SingularAttribute<Question, String> option1;
    public static volatile SingularAttribute<Question, Long> id;
    public static volatile SingularAttribute<Question, String> option2;
    public static volatile SingularAttribute<Question, String> truefalsecorrectanswer;
    public static volatile SingularAttribute<Question, String> videoImageNone;
    public static volatile SingularAttribute<Question, Stage> stageId;

}