package entity.domain;

import entity.domain.CompetitionMatch;
import entity.domain.Publicfigure;
import entity.domain.Question;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-19T22:53:12")
@StaticMetamodel(Episode.class)
public class Episode_ { 

    public static volatile CollectionAttribute<Episode, Question> questionCollection;
    public static volatile CollectionAttribute<Episode, CompetitionMatch> competitionMatchCollection;
    public static volatile CollectionAttribute<Episode, Publicfigure> publicfigureCollection;
    public static volatile SingularAttribute<Episode, String> name;
    public static volatile SingularAttribute<Episode, Long> id;

}