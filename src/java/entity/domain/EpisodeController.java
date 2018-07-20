/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.domain;

import entity.domain.util.JsfUtil;
import facade.EpisodeFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

@Named(value = "episodeController")
@SessionScoped
public class EpisodeController implements Serializable {

    @Inject
    private Episode episode;
    @EJB
    private EpisodeFacade episodeFacade;

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(episodeFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(episodeFacade.findAll(), true);
    }

    public Episode findEpisode(java.lang.Long id) {
        return episodeFacade.find(id);
    }

    @FacesConverter(forClass = Episode.class)
    public static class EpisodeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EpisodeController controller = (EpisodeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "episodeController");
            return controller.findEpisode(getKey(value));
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
            if (object instanceof Episode) {
                Episode o = (Episode) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Episode.class.getName());
            }
        }

    }

}
