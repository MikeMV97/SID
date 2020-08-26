package controller;

import Operaciones.MenuFac;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import model.Empleado;
import model.Menu;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author miamo
 */
@Named(value = "menuController")
@SessionScoped
public class MenuController implements Serializable{
    
    private MenuFac mnFac;
    private MenuModel model;
    private List<Menu> lsMenu;
    
    public MenuController() {
    }
    @PostConstruct
    public void init(){
        model = new DefaultMenuModel();
        mnFac = new MenuFac();
        this.listarMenu();
        establecerMenu();
    }
    public void listarMenu(){
        try {
            lsMenu = mnFac.obtnrMenus();//menuEJB.findAll();
        } catch (Exception e) {
            System.out.println(""+e.getMessage());
        }
    }
    
    public void establecerMenu(){
        Empleado emp = (Empleado) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usr");
        if (emp!= null)
          for (Menu m : lsMenu){
            //System.out.println("establecerMenu: "+m.getIdMenu()+" " + m.getTipoItem());
            if ( m.getTipoItem()=='S' ){
                DefaultSubMenu submenu = new DefaultSubMenu(m.getNombreItem());
                for(Menu i : lsMenu){
                    try {
                        Menu submn = mnFac.getMenu(lsMenu, i.getIdMenupadre()); // i.getIdMenupadre();
                        if (submn != null){
                            if (submn.getIdMenu() == m.getIdMenu() && 
                                    (emp.getIdTipoemp() == 2 || 
                                    emp.getIdTipoemp().compareTo(i.getIdTipoemp())==0 )){
                                DefaultMenuItem item = new DefaultMenuItem(i.getNombreItem());
                                item.setUrl(i.getDireccion()+".mzd");
                                submenu.addElement(item);
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                model.addElement(submenu);
            } else if (m.getTipoItem()=='I' && m.getIdMenupadre()==0 && 
                                    (emp.getIdTipoemp().intValue() == 2 || 
                                    emp.getIdTipoemp().intValue()==m.getIdTipoemp())) {
                        DefaultMenuItem item = new DefaultMenuItem(m.getNombreItem());
                        item.setUrl(m.getDireccion()+".mzd");
                        model.addElement(item);
            }
        }
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
    
}
