package entity.domain;

import entity.domain.Question;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-19T22:53:12")
@StaticMetamodel(Questionpoint.class)
public class Questionpoint_ { 

    public static volatile CollectionAttribute<Questionpoint, Question> questionCollection;
    public static volatile SingularAttribute<Questionpoint, Long> id;
    public static volatile SingularAttribute<Questionpoint, Integer> points;

}