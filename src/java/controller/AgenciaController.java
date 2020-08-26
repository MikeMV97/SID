package controller;

import Operaciones.AgenciaFac;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Agencia;

@Named
@ViewScoped
public class AgenciaController implements Serializable{
    private AgenciaFac agF;
    private Agencia agencia;
    private String numAgen;
    private List<SelectItem> selectItemsAgencia;
    private List<Agencia> lsAgencias;

    @PostConstruct
    public void init(){
        try {
            agF = new AgenciaFac();
            agencia = new Agencia();
            numAgen = new String();
            lsAgencias = agF.obtnrAgencias();
            setSelectItemsAgencia(lsAgencias);
        } catch (SQLException ex) {
            Logger.getLogger(AgenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertar(){
        AgenciaFac agF = new AgenciaFac();
        boolean msg = agF.insertarAgen(agencia);
        if (msg)
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Agregado..."));
        else
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Agencia no modificada :'( "));
    }
    
    public void modificar(){
        AgenciaFac agF = new AgenciaFac();
        agencia.setIdAgencia(Integer.parseInt(numAgen));
        boolean msg = agF.modificarAgen(agencia);
        if (msg)
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Modificación exitosa..."));
        else
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Agencia no modificada :'( "));
    }
    
    public void eliminar(){
        AgenciaFac agF = new AgenciaFac();
        agencia.setIdAgencia(Integer.parseInt(numAgen));
        boolean msg = agF.eliminarAgen(agencia);
        if (msg)
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Eliminado..."));
        else
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Agencia no modificada :'( "));
    }
    
    public List<SelectItem> getSelectItemsAgencia(){
        return selectItemsAgencia;
    }
    public void setSelectItemsAgencia(List<Agencia> list_agen){
        this.selectItemsAgencia = new ArrayList<>();
        this.selectItemsAgencia.clear();
            for (Agencia ag : list_agen){
                SelectItem sltItem = new SelectItem(ag.getIdAgencia(), ag.getNombAgencia());
                this.selectItemsAgencia.add(sltItem);
            }
    }

    public List<Agencia> getLsAgencias() {
        return lsAgencias;
    }

    public void setLsAgencias(List<Agencia> lsAgencias) {
        this.lsAgencias = lsAgencias;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public String getNumAgen() {
        return numAgen;
    }

    public void setNumAgen(String numAgen) {
        this.numAgen = numAgen;
    }
    
}

