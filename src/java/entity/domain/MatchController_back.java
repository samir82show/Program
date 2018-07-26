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

@Named(value = "matchController_back")
@SessionScoped
public class MatchController_back implements Serializable {

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
    private List<Object> questions;

    @PostConstruct
    public void init() {
        flushMatch();
        List<Object> competitionList = competitionMatchFacade.findRunning();
        if (!competitionList.isEmpty()) {
            this.competitionMatch = (CompetitionMatch) competitionList.get(0);
            //get all questions in the episode, stage and with status 0
            //if questions are zero then if (the competition stage) is less 
            //than 6 increase by 1 and then increase the stage by 1 and get questions
        }
    }

    public List<CompetitionMatch> refreshMatches() {
        List<CompetitionMatch> comptitions = getCompetitions();
        return comptitions;
    }

    public String navToList() {
        return "list";
    }

    public Question getQuestion() {
        return question;
    }

    public String continueToQuestions() {
//        questions = questionFacade
//                .findQuestionByEpisodeStageStatus(episodeFacade.find((competitionMatch.getEpisodeId().getId()).longValue()),
//                        stageFacade.find(competitionMatch.getCurrentStage().longValue()));
        if (!questions.isEmpty()) {
            question = (Question) questions.get(0);
        } else {
            if (competitionMatch.getCurrentStage() <= 6) {
                competitionMatch.setCurrentStage(new Long(competitionMatch.getCurrentStage().intValue() + 1));
                competitionMatchFacade.edit(competitionMatch);
            }
        }
        System.out.println("questions in continueToQuestions.............. " + questions);
        return "question_page";
    }

    public void correctAnswer1() {
        System.out.println("competition inside correctAnswer1......... " + competitionMatch);
        if (question != null) {
            question.setHide("1");
            questionFacade.edit(question);
        }
        competitionMatch.setScore1(new Long(competitionMatch.getScore1() + question.getQuestionpointId().getPoints().intValue()));
        competitionMatchFacade.edit(competitionMatch);
        if (competitionMatch.getCurrentStage() <= 6) {
//            questions = questionFacade
//                    .findQuestionByEpisodeStageStatus(episodeFacade.find(new Long(competitionMatch.getEpisodeId().getId())),
//                            stageFacade.find(new Long(competitionMatch.getCurrentStage())));
            if (!questions.isEmpty()) {
                question = (Question) questions.get(0);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Moving to stage " + new Long(competitionMatch.getCurrentStage() + 1)));
                competitionMatch.setCurrentStage(new Long(competitionMatch.getCurrentStage() + 1));
                competitionMatchFacade.edit(competitionMatch);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("End of the Episode."));
        }
    }

    public void correctAnswer2() {
        question.setHide("1");
        questionFacade.edit(question);
        System.out.println("question in correctAnswer2................." + question);
        System.out.println("question points in correctAnswer2................." + question.getQuestionpointId().getPoints());
        competitionMatch.setScore2(new Long(competitionMatch.getScore2() + question.getQuestionpointId().getPoints()));
        competitionMatchFacade.edit(competitionMatch);
        System.out.println("competitionMatch.getCurrentStage inside correctAnswer1............" + competitionMatch.getCurrentStage());
        System.out.println("competitionMatch.score2 inside correctAnswer2............" + new Long(competitionMatch.getScore1() + question.getQuestionpointId().getPoints()));
        if (competitionMatch.getCurrentStage() <= 6) {
//            questions = questionFacade
//                    .findQuestionByEpisodeStageStatus(episodeFacade.find(new Long(competitionMatch.getEpisodeId().getId())),
//                            stageFacade.find(new Long(competitionMatch.getCurrentStage())));
            System.out.println("questions inside correctAnswer2..........................." + questions);
            if (!questions.isEmpty()) {
                question = (Question) questions.get(0);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Moving to stage " + new Long(competitionMatch.getCurrentStage() + 1)));
                competitionMatch.setCurrentStage(new Long(competitionMatch.getCurrentStage().intValue() + 1));
                competitionMatchFacade.edit(competitionMatch);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("End of the Episode."));
        }
    }

    public void wrongAnswer1() {
        question.setHide("1");
        questionFacade.edit(question);
        if (competitionMatch.getCurrentStage() <= 6) {
//            questions = questionFacade
//                    .findQuestionByEpisodeStageStatus(episodeFacade.find(new Long(competitionMatch.getEpisodeId().getId())),
//                            stageFacade.find(new Long(competitionMatch.getCurrentStage())));
            System.out.println("questions inside wrongAnswer2..........................." + questions);
            if (!questions.isEmpty()) {
                question = (Question) questions.get(0);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Moving to stage " + new Long(competitionMatch.getCurrentStage() + 1)));
                competitionMatch.setCurrentStage(new Long(competitionMatch.getCurrentStage() + 1));
                competitionMatchFacade.edit(competitionMatch);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("End of the Episode."));
        }
    }

    public void wrongAnswer2() {
        question.setHide("1");
        questionFacade.edit(question);
        if (competitionMatch.getCurrentStage() <= 6) {
//            questions = questionFacade
//                    .findQuestionByEpisodeStageStatus(episodeFacade.find(new Long(competitionMatch.getEpisodeId().getId())),
//                            stageFacade.find(new Long(competitionMatch.getCurrentStage())));
            System.out.println("questions inside wrongAnswer2..........................." + questions);
            if (!questions.isEmpty()) {
                question = (Question) questions.get(0);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Moving to stage " + new Long(competitionMatch.getCurrentStage() + 1)));
                competitionMatch.setCurrentStage(new Long(competitionMatch.getCurrentStage() + 1));
                competitionMatchFacade.edit(competitionMatch);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("End of the Episode."));
        }
    }

    public String create() {
        if (competitionMatch != null) {
            competitionMatch.setCurrentStage(1L);
            competitionMatch.setStatus(1);
        }
        competitionMatchFacade.create(competitionMatch);
        createXml();
//        questions = questionFacade
//                .findQuestionByEpisodeStageStatus(episodeFacade.find(new Long(competitionMatch.getEpisodeId().getId())),
//                        stageFacade.find(new Long(competitionMatch.getCurrentStage())));
        question = (Question) questions.get(0);
        return "question_page";
    }

    public void createXml() {
        FileWriter fstream = null;
        BufferedWriter out = null;

        try {
//            FileWriter fileWriter = new FileWriter("c:\\data\\rayantv.xml");
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("c:\\data\\rayantv.xml"), "UTF-8"));
            bufferedWriter.write("<?xml version=\"1.0\"?>");
            bufferedWriter.write("<ResultSet>");
            bufferedWriter.write("<Result>");
            bufferedWriter.write("<Championship>Senior Stallionso</Championship>");
            bufferedWriter.write("<GoldNum>" + competitionMatch.getScore1() + "</GoldNum>");
            bufferedWriter.write("<GoldName>Shaheen Lembarko</GoldName>");
            bufferedWriter.write("<SilverNum>200</SilverNum>");
            bufferedWriter.write("<Championship>Senior Stallionso</Championship>");
            bufferedWriter.write("<SilverName>" + competitionMatch.getSchool1Name() + "</SilverName>");
            bufferedWriter.write("<BronzeNum>300</BronzeNum>");
            bufferedWriter.write("<BronzeName>Amaar Al Nassero</BronzeName>");
            bufferedWriter.write("</Result>");
            bufferedWriter.write("</ResultSet>");
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }

    public String stopMatch() {
        competitionMatch.setStatus(0);
        competitionMatchFacade.edit(competitionMatch);
        flushMatch();
        return "index";
    }

    public String edit(CompetitionMatch competitionMatch) {
        this.competitionMatch = competitionMatch;
        return "edit";
    }

    public String goIndex() {
        return "index";
    }

    public void edit() {
        competitionMatchFacade.edit(competitionMatch);
    }

    public String delete(CompetitionMatch competitionMatch) {
        this.competitionMatch = competitionMatch;
        competitionMatchFacade.remove(competitionMatch);
        flushMatch();
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Match deleted."));
        return "list";
    }

    public List<CompetitionMatch> getCompetitions() {
        return competitionMatchFacade.findAll();
    }

    public CompetitionMatch getCompetitionMatch() {
        return competitionMatch;
    }

    public CompetitionMatch findCompetitionMatch(Long id) {
        return competitionMatchFacade.find(id);
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(competitionMatchFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(competitionMatchFacade.findAll(), true);
    }

    private void flushMatch() {
        competitionMatch.setId(null);
        competitionMatch.setEpisodeId(new Episode());
        competitionMatch.setSchool1Name(new School(""));
        competitionMatch.setSchool2Name(new School(""));
        competitionMatch.setScore1(null);
        competitionMatch.setScore2(null);
        competitionMatch.setStatus(0);
        competitionMatch.setCurrentStage(null);
    }

    @FacesConverter(forClass = CompetitionMatch.class)
    public static class CompetitionmatchControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MatchController_back controller = (MatchController_back) facesContext.getApplication().getELResolver().
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
