package entity.domain;

import entity.domain.Episode;
import entity.domain.Question;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-26T15:19:56")
@StaticMetamodel(Publicfigure.class)
public class Publicfigure_ { 

    public static volatile SingularAttribute<Publicfigure, String> brief;
    public static volatile CollectionAttribute<Publicfigure, Question> questionCollection;
    public static volatile SingularAttribute<Publicfigure, String> name;
    public static volatile SingularAttribute<Publicfigure, Integer> id;
    public static volatile SingularAttribute<Publicfigure, Episode> episodeId;

}