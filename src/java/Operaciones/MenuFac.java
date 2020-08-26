package Operaciones;

import DB.ManejadorJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Menu;

/**
 *
 * @author miamo
 */
public class MenuFac {
    private ManejadorJDBC jdbc = null;

    public MenuFac() {
        jdbc = new ManejadorJDBC();
    }

    public List<Menu> obtnrMenus() throws SQLException{
        List<Menu> lsMenu = new ArrayList<>();
        ResultSet rs = jdbc.consultar("select m.* from Menu m");
        lsMenu.clear();
        while (rs.next()) {
            Menu m = new Menu(rs.getShort(1), rs.getString(2));
            m.setIdTipoemp(rs.getInt(3));
            m.setIdMenupadre(rs.getShort(4));
            m.setTipoItem(rs.getString(5).charAt(0));
            m.setDireccion(rs.getString(6));
            lsMenu.add(m);
            m = null;
        }

        return lsMenu;
    }
    
    public Menu getMenu(List<Menu> lsMenu, short idMn) throws SQLException{
        Menu mn=null;
        lsMenu = (lsMenu!=null) ? lsMenu : obtnrMenus();
        for (Menu menu : lsMenu){
            if (menu.getIdMenu() == idMn) {
                mn = menu;
                break;
            }
        }
        return mn;
    }
    
    public void setJdbc(ManejadorJDBC sql) {
        this.jdbc = sql;
    }

    public ManejadorJDBC getJdbc() {
        return jdbc;
    }
    
    
}
