package Operaciones;

import DB.ManejadorJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Agencia;

/**
 *
 * @author miamo
 */
public class AgenciaFac {
    
    
    public boolean insertarAgen(Agencia agencia){
        boolean msg=false;
        try {
            ManejadorJDBC jdbc = new ManejadorJDBC();
            jdbc.ejecutar("exec sp_agrAgencia '"+agencia.getNombAgencia()+"';");
            
            msg = true;
        } catch (SQLException ex) {
            msg = false;
            Logger.getLogger(AgenciaFac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
    
    public boolean modificarAgen(Agencia agencia){
        boolean msg=false;
        try {
            ManejadorJDBC jdbc = new ManejadorJDBC();
            jdbc.ejecutar("UPDATE Agencia SET nomb_agencia = '"+agencia.getNombAgencia()+"' WHERE idAgencia = "+agencia.getIdAgencia()+";");
            msg = true;
        } catch (SQLException ex) {
            msg = false;
            Logger.getLogger(AgenciaFac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
    
    public boolean eliminarAgen(Agencia agencia){
        boolean msg=false;
        try {
            ManejadorJDBC jdbc = new ManejadorJDBC();
            jdbc.ejecutar("UPDATE Empleado SET idAgencia = (select MIN(a.idAgencia) from Agencia a where a.idAgencia != "+agencia.getIdAgencia()+") "
                    + "WHERE idAgencia = "+agencia.getIdAgencia()+";"
                        + " DELETE FROM Agencia WHERE idAgencia = "+agencia.getIdAgencia()+";");
            msg = true;
        } catch (SQLException ex) {
            msg = false;
            Logger.getLogger(AgenciaFac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
    
    public List<Agencia> obtnrAgencias() throws SQLException{
        ManejadorJDBC jdbc = new ManejadorJDBC();
        List<Agencia> lsAg = new ArrayList<>();
        ResultSet rs = jdbc.consultar("select m.* from Agencia m");
        lsAg.clear();
        Agencia m;
        while (rs.next()) {
            m = new Agencia(rs.getInt(1), rs.getString(2));
            lsAg.add(m);
            m = null;
        }

        return lsAg;
    }
}
