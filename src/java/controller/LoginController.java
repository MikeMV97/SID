package controller;

import Operaciones.UsuarioFac;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Empleado;

/**
 *
 * @author miamo
 */
@Named
@ViewScoped
public class LoginController implements Serializable {
    
    UsuarioFac usrFac;
    private String mail;
    private String psw;
    
    public LoginController() {
    }
    
    @PostConstruct
    public void init(){
        mail = new String();
        psw = new String();
        usrFac = new UsuarioFac();
    }

    public String iniciarSesion(){
        String path = null; 
        try {
            Empleado emp = usrFac.inicioSesion(mail, psw);//lgnEJB.iniciarSesion(mail, psw);
            if (emp != null){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usr", emp);
                path = "Pages/Principal?faces-redirect=true";
            } else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Firma no válida"));
                //throw new Exception("Firma de usuario denegada...");
            }
        } catch (Exception e) {
            System.out.println(""+e.getMessage());
        }
        
        return path;
    }
    
    public void cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/SID/");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
    
    
}
