package entity.domain;

import entity.domain.Episode;
import entity.domain.School;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-19T22:53:12")
@StaticMetamodel(CompetitionMatch.class)
public class CompetitionMatch_ { 

    public static volatile SingularAttribute<CompetitionMatch, Integer> currentStage;
    public static volatile SingularAttribute<CompetitionMatch, School> school1Name;
    public static volatile SingularAttribute<CompetitionMatch, Integer> score2;
    public static volatile SingularAttribute<CompetitionMatch, Long> id;
    public static volatile SingularAttribute<CompetitionMatch, Integer> score1;
    public static volatile SingularAttribute<CompetitionMatch, Episode> episodeId;
    public static volatile SingularAttribute<CompetitionMatch, School> school2Name;
    public static volatile SingularAttribute<CompetitionMatch, Integer> status;

}