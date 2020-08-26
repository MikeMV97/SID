package controller;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Empleado;

/**
 *
 * @author miamo
 */
@Named(value = "plantillaController")
@ViewScoped
public class PlantillaController implements Serializable{
    
    public PlantillaController() {
    }
    
    public void verificarSesion(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Empleado usr = (Empleado) context.getExternalContext().getSessionMap().get("usr");
            
            if (usr == null)
                context.getExternalContext().redirect("/SID/");
            
        } catch (Exception e) {
        }
    }
    
    public String traerChart(){
        
        return "<script>alert(\"Hello! I am an alert box!\");</script>";
    }
}
