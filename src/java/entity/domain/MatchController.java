/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.domain;

import entity.domain.util.JsfUtil;
import facade.CompetitionMatchFacade;
import facade.EpisodeFacade;
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

    @Inject
    private CompetitionMatch competitionMatch;
    @Inject
    private Question question;

    @PostConstruct
    public void init() {
        flushMatch();
        List<Object> competitionList = competitionMatchFacade.findRunning();
        if (!competitionList.isEmpty()) {
            this.competitionMatch = (CompetitionMatch) competitionList.get(0);
        }
    }

    public Question getQuestion() {
        return question;
    }

    public Episode findEpisode() {
        return (Episode) episodeFacade.find(1L);
    }

//    public String findQuestion() {
//        return ((Question) questionFacade.findQuestion()).getContent();
//    }
    public String create() {
        System.out.println("Current Stage: " + competitionMatch.getCurrentStage());
        if (competitionMatch.getCurrentStage() == null) {
            competitionMatch.setCurrentStage(1);
        } else {
            
        }
        competitionMatch.setStatus(1);
        competitionMatchFacade.create(competitionMatch);
        return "question_page";
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
