/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.domain;

import entity.domain.util.JsfUtil;
import facade.CompetitionMatchFacade;
import facade.EpisodeFacade;
import facade.QuestionFacade;
import facade.StageFacade;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

@Named(value = "matchController")
@SessionScoped
public class MatchController implements Serializable {

    @EJB
    private CompetitionMatchFacade competitionMatchFacade;
    @EJB
    private EpisodeFacade episodeFacade;
    @EJB
    private QuestionFacade questionFacade;
    @EJB
    private StageFacade stageFacade;
    @Inject
    private CompetitionMatch competitionMatch;
    @Inject
    private Question question;
    @Inject
    private Episode episode;
    private List<Question> questions;
    private List<CompetitionMatch> competitionsList;
    private String result;

    @PostConstruct
    public void init() {
        competitionMatch.setScore1(0L);
        competitionMatch.setScore2(0L);
    }

    public String startMatch() {
//        flushMatch();
        competitionMatch.setCurrentStage(1L);
        questions = questionFacade
                .findQuestionByEpisodeStageStatus(
                        episodeFacade.find(competitionMatch.getEpisodeId().getId()), stageFacade.find(competitionMatch.getCurrentStage())
                );
        question = questions.get(0);
        competitionMatch.setStatus(1);
        competitionMatchFacade.create(competitionMatch);
        createXml();
        return "stage1?faces-redirect=true";
    }

    public String correctAnswer1() {
        long stage = competitionMatch.getCurrentStage();
        question.setHide("1");
        questionFacade.edit(question);
        questions = questionFacade
                .findQuestionByEpisodeStageStatus(
                        episodeFacade.find(competitionMatch.getEpisodeId().getId()), stageFacade.find(competitionMatch.getCurrentStage())
                );
        if (!questions.isEmpty() && competitionMatch.getCurrentStage() < 6) {
            competitionMatch.setScore1(new Long(competitionMatch.getScore1() + question.getQuestionpointId().getPoints()));
            question = questions.get(0);
        } else if (!questions.isEmpty() && competitionMatch.getCurrentStage() == 6) {
            competitionMatch.setScore1(new Long(competitionMatch.getScore1() + question.getQuestionpointId().getPoints()));

            questions = questionFacade
                    .findQuestionByEpisodeStageStatus(
                            episodeFacade.find(competitionMatch.getEpisodeId().getId()), stageFacade.find(competitionMatch.getCurrentStage())
                    );
            question = questions.get(0);
        } else if (questions.isEmpty() && competitionMatch.getCurrentStage() < 6) {
            competitionMatch.setScore1(new Long(competitionMatch.getScore1() + question.getQuestionpointId().getPoints()));

            competitionMatch.setCurrentStage(new Long(competitionMatch.getCurrentStage() + 1));
            questions = questionFacade
                    .findQuestionByEpisodeStageStatus(
                            episodeFacade.find(competitionMatch.getEpisodeId().getId()), stageFacade.find(competitionMatch.getCurrentStage())
                    );
            question = questions.get(0);
        } else if (questions.isEmpty() && competitionMatch.getCurrentStage() == 6) {
            result = "End of Episode.";
        }
        createXml();
        if (stage == competitionMatch.getCurrentStage()) {
            return null;
        }
        return "stage" + competitionMatch.getCurrentStage() + "?faces-redirect=true";
    }

    public void flushMatch() {
        competitionMatch.setEpisodeId(null);
        competitionMatch.setSchool1Name(null);
        competitionMatch.setSchool2Name(null);
        competitionMatch.setScore1(0L);
        competitionMatch.setScore2(0L);
        competitionMatch.setStatus(null);
    }

    public void createXml() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("c:\\data\\rayantv.xml"), "UTF-8"));
            bufferedWriter.write("<?xml version=\"1.0\"?>\n");
            bufferedWriter.write("<root>\n");
            bufferedWriter.write("<question>" + question.getContent() + "</question>\n");
            bufferedWriter.write("<points>" + question.getQuestionpointId().getPoints() + "</points>\n");
            bufferedWriter.write("<option1>" + question.getOption1() + "</option1>\n");
            bufferedWriter.write("<option2>" + question.getOption1() + "</option2>\n");
            bufferedWriter.write("<option3>" + question.getOption1() + "</option3>\n");
            bufferedWriter.write("<directAnswer>" + question.getDirectanswer() + "</directAnswer>\n");
            bufferedWriter.write("<school1>" + competitionMatch.getSchool1Name() + "</school1\n");
            bufferedWriter.write("<school2>" + competitionMatch.getSchool2Name() + "</school2\n");
            bufferedWriter.write("<school1_current_score>" + competitionMatch.getScore1() + "</school1_current_score>\n");
            bufferedWriter.write("<school2_current_score>" + competitionMatch.getScore2() + "</school2_current_score>\n");
            bufferedWriter.write("</root>");
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }

    public String getResult() {
        return result;
    }

    public void delete(CompetitionMatch competitionMatch) {
        this.competitionMatch = competitionMatch;
        competitionMatchFacade.remove(competitionMatch);
    }

    public String edit(CompetitionMatch competitionMatch) {
        this.competitionMatch = competitionMatch;
        return "edit?faces-redirect=true";
    }

    public void edit() {
        competitionMatchFacade.edit(competitionMatch);
    }

    public List<CompetitionMatch> getCompetitionMatches() {
        return competitionMatchFacade.findCompetitionMatchSorted();
    }

    public CompetitionMatch findCompetitionMatch(Long id) {
        return competitionMatchFacade.find(id);
    }

    public CompetitionMatch getCompetitionMatch() {
        return competitionMatch;
    }

    public Question getQuestion() {
        return question;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(competitionMatchFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(competitionMatchFacade.findAll(), true);

    }

    @FacesConverter(forClass = CompetitionMatch.class)
    public static class CompetitionmatchControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MatchController controller = (MatchController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "competitionmatchController");
            return controller.findCompetitionMatch(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CompetitionMatch) {
                CompetitionMatch o = (CompetitionMatch) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + CompetitionMatch.class.getName());
            }
        }

    }

}
