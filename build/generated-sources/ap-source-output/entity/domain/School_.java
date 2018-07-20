package entity.domain;

import entity.domain.CompetitionMatch;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-19T22:53:12")
@StaticMetamodel(School.class)
public class School_ { 

    public static volatile CollectionAttribute<School, CompetitionMatch> competitionMatchCollection;
    public static volatile SingularAttribute<School, String> contactperson;
    public static volatile SingularAttribute<School, String> name;
    public static volatile SingularAttribute<School, String> phones;
    public static volatile CollectionAttribute<School, CompetitionMatch> competitionMatchCollection1;

}