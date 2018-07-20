package entity.domain;

import entity.domain.util.JsfUtil;
import facade.SchoolFacade;
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

@Named(value = "schoolController")
@SessionScoped
public class SchoolController implements Serializable {

    @Inject
    private School school;
    @EJB
    private SchoolFacade schoolFacade;

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(schoolFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(schoolFacade.findAll(), true);
    }

    public School getSchool() {
        return school;
    }

    public School findSchool(java.lang.String id) {
        return schoolFacade.find(id);
    }

    @FacesConverter(forClass = School.class)
    public static class SchoolControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SchoolController controller = (SchoolController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "schoolController");
            return controller.findSchool(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof School) {
                School o = (School) object;
                return getStringKey(o.getName());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + School.class.getName());
            }
        }
    }

}
