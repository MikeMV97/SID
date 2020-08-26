package Operaciones;

import DB.ManejadorJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CatDepto;

/**
 *
 * @author miamo
 */
public class DeptoFac {
    ManejadorJDBC jdbc;

    public DeptoFac() {
        jdbc = new ManejadorJDBC();
    }
    
    public boolean insertarDepto(CatDepto depto){
        boolean msg=false;
        try {
            ManejadorJDBC jdbc = new ManejadorJDBC();
            jdbc.ejecutar("exec sp_agrDepto '"+depto.getNombDepto()+"';");
            
            msg = true;
        } catch (SQLException ex) {
            msg = false;
            Logger.getLogger(AgenciaFac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
    
    public boolean modificarDepto(CatDepto depto){
        boolean msg=false;
        try {
            ManejadorJDBC jdbc = new ManejadorJDBC();
            jdbc.ejecutar("UPDATE Cat_depto SET nomb_depto = '"+depto.getNombDepto()+"' WHERE idDepto = "+depto.getIdDepto()+";");
            msg = true;
        } catch (SQLException ex) {
            msg = false;
            Logger.getLogger(AgenciaFac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
    
    public boolean eliminarDepto(CatDepto depto){
        boolean msg=false;
        try {
            ManejadorJDBC jdbc = new ManejadorJDBC();
            jdbc.ejecutar("UPDATE Empleado SET idDepto = (select MIN(a.idDepto) from Cat_depto a where a.idDepto !="+depto.getIdDepto()+") " 
                    +"WHERE idDepto = "+depto.getIdDepto()+"; "
                    +"DELETE FROM Cat_depto WHERE idDepto = "+depto.getIdDepto()+";");
            msg = true;
        } catch (SQLException ex) {
            msg = false;
            Logger.getLogger(AgenciaFac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
    
    public List<CatDepto> obtnrDepartamentos() throws SQLException{
        List<CatDepto> lsDp = new ArrayList<>();
        ResultSet rs = jdbc.consultar("select m.* from Cat_depto m");
        lsDp.clear();
        CatDepto m;
        while (rs.next()) {
            m = new CatDepto(rs.getInt(1), rs.getString(2));
            lsDp.add(m);
            m = null;
        }

        return lsDp;
    }
}
