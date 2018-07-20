package entity.domain;

import entity.domain.Question;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-19T22:53:12")
@StaticMetamodel(Stage.class)
public class Stage_ { 

    public static volatile CollectionAttribute<Stage, Question> questionCollection;
    public static volatile SingularAttribute<Stage, String> name;
    public static volatile SingularAttribute<Stage, Long> id;

}