package controller;

import Operaciones.EncuestaFac;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import model.DynamicField;
import model.Empleado;
import model.Encuesta;
import model.Respuestacontestada;
import org.primefaces.extensions.model.fluidgrid.FluidGridItem;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author miamo
 */
@ManagedBean
@ViewScoped
public class EncuestaController implements Serializable{
    private List<FluidGridItem> items;
    private List<Encuesta> lsEncuestas;
    private MenuModel modelEncuestas;
    private int idEncuestaSelected;
  
    @PostConstruct  
    protected void init() {
        items = new ArrayList<FluidGridItem>();
        idEncuestaSelected=0;
        //llenarFormulario(); 
        generarMenu();
    }  
    
    public void llenarFormulario(int idEnc){
        EncuestaFac ef = new EncuestaFac();
        List<DynamicField> lsDF = ef.obtnrCamposForm(idEnc);
        if (lsDF!=null)
            for (DynamicField df : lsDF) {
                items.add(new FluidGridItem(df, df.getType()));
            }
    }
    
    public void guardarContestada(){
        Empleado emp = (Empleado) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usr");
        if (idEncuestaSelected>0 && emp!=null){
            Integer idEmp = emp.getIdEmpleado();
            EncuestaFac fac = new EncuestaFac();
            for (int i =0 ; i<items.size(); i++){
                Object o = items.get(i).getData();
                if(o instanceof DynamicField){
                    System.out.println("ES de dynamic field");
                } else
                    System.out.println("No lo es... :''(");
            }
            List<Respuestacontestada> lsRespConte = new ArrayList<>(); lsRespConte.clear();
            boolean msg = fac.contestarEncuesta(idEmp, idEncuestaSelected, lsRespConte);
        } else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "La sesión ha caducado..."));
        
    }
  
    public void generarMenu(){
        modelEncuestas = new DefaultMenuModel();
        EncuestaFac eFac = new EncuestaFac();
        lsEncuestas = eFac.obtnrEncuestas();
        Integer idDeptoAnterior = lsEncuestas.get(0).getCatDepto().getIdDepto();
        boolean p1raIteracion = true;
        for (Encuesta enc : lsEncuestas){ //Este for para crear los submenus principales del Menu
            if ( p1raIteracion || !enc.getCatDepto().getIdDepto().equals(idDeptoAnterior) ){
                DefaultSubMenu submenu = new DefaultSubMenu(enc.getCatDepto().getNombDepto());
                for(Encuesta i : lsEncuestas){  // Este for para añadir los items a cada submenu
    //                        Encuesta itemSubmn = mnFac.getMenu(lsMenu, i.getIdMenupadre()); // i.getIdMenupadre();
                    if ( enc.getCatDepto().getIdDepto().equals(i.getCatDepto().getIdDepto()) ){
                        DefaultMenuItem item = new DefaultMenuItem();
                        StringBuilder sb = new StringBuilder();
                        sb.append((i.getIdTipoencuesta().charAt(0)=='E')? "Emp-":"Client-");
                        sb.append(i.getTitulo());
                        item.setValue(sb.toString());
                        item.setParam("idEncuesta", i.getIdEncuesta());
                        item.setCommand("#{encuestaController.setidEncuestaSelected("+i.getIdEncuesta()+")}");
                        item.setUpdate(":formEncContestar:pgEnc");
                        //item.setOnclick("");
    //                        item.setUrl(i.getDireccion()+".mzd");
                        submenu.addElement(item);
                    }
                }
                modelEncuestas.addElement(submenu);
                p1raIteracion=false;
            }
            idDeptoAnterior= enc.getCatDepto().getIdDepto();
        }
    }
    

    public void setidEncuestaSelected(String idEnc){
        llenarFormulario(Integer.parseInt(idEnc));
        this.idEncuestaSelected = Integer.parseInt(idEnc);
    }
            
    public int getIdEncuestaSelected() {
        return idEncuestaSelected;
    }
    
    public boolean selected(){
        if(!(idEncuestaSelected==0)){
            llenarFormulario(idEncuestaSelected);
            return true;
        }
        return false;
    }
    
    public List<FluidGridItem> getItems() {  
        return items;  
    }  

    public MenuModel getModelEncuestas() {
        return modelEncuestas;
    }
    
}
