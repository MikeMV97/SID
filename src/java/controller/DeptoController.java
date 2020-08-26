package controller;

import Operaciones.DeptoFac;
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
import model.CatDepto;

/**
 *
 * @author miamo
 */
@Named
@ViewScoped
public class DeptoController implements Serializable{
    
    private DeptoFac dpF;
    private CatDepto depto;
    private String numDepto;
    private List<SelectItem> selectItemsDepto;
    //private List<Agencia> lsAgencias;
    
    @PostConstruct
    public void init(){
        dpF = new DeptoFac();
        depto = new CatDepto();
        numDepto = new String();
        selectItemsDepto = getSelectItemsDepto();
    }
    
//    @Override
    public void insertar() {
        boolean msg = dpF.insertarDepto(depto);
        if (msg)
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Agregado..."));
        else
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Agencia no agregada :'( "));
    }

  //  @Override
    public void modificar() {
        depto.setIdDepto(Integer.parseInt(numDepto));
        boolean msg = dpF.modificarDepto(depto);
        if (msg)
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Modificación exitosa..."));
        else
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Agencia no modificada :'( "));
    }

    //@Override
    public void consultar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public void eliminar() {
        depto.setIdDepto(Integer.parseInt(numDepto));
        boolean msg = dpF.eliminarDepto(depto);
        if (msg)
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Eliminado..."));
        else
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Agencia no modificada :'( "));
    }

    public DeptoFac getDpF() {
        return dpF;
    }

    public void setDpF(DeptoFac dpF) {
        this.dpF = dpF;
    }

    public CatDepto getDepto() {
        return depto;
    }

    public void setDepto(CatDepto depto) {
        this.depto = depto;
    }

    public String getNumDepto() {
        return numDepto;
    }

    public void setNumDepto(String numDepto) {
        this.numDepto = numDepto;
    }
    public List<SelectItem> getSelectItemsDepto(){
        try {
            this.selectItemsDepto = new ArrayList<>();
            List<CatDepto> list_agen = dpF.obtnrDepartamentos();
            
            this.selectItemsDepto.clear();
            for (CatDepto ag : list_agen){
                SelectItem sltItem = new SelectItem(ag.getIdDepto(), ag.getNombDepto());
                this.selectItemsDepto.add(sltItem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return selectItemsDepto;
    }
    
}
