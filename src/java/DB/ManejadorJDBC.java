package DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author miamo
 */
public class ManejadorJDBC {
    
    private PreparedStatement ps = null;
    private CallableStatement cs = null;
    private ConexionBD datosCon = null;
    private Connection conexion = null;

    public ManejadorJDBC() {
        datosCon = new ConexionBD();
        conectar();
    }
    
    public ResultSet consultar(String query) throws SQLException {
        this.ps = conexion.prepareStatement(query);
        return this.ps.executeQuery();
    } 

    public void ejecutar(String query) throws SQLException {
        this.ps = conexion.prepareStatement(query);
        ps.executeUpdate();
    }

    public CallableStatement ejecutarIN(String query, List<String> listParam) {
        try {
            cs = conexion.prepareCall(query);
            
            int i = 1; //   Indice del parametro a colocar
            for (String param: listParam){
                if (param==null){
                    cs.setString(i, "");
                } else {
                    cs.setString(i, param);
                }
                i++;
            }
            
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("** Excepción al ejecutar SQL...\n" 
                    + ex.getMessage());
        }

        return cs;
    }

    public CallableStatement ejecutarINOUT(String query, List<String> listParam, List<Integer> listOut) {
        try {
            //  Preparamos el CallableStatement
            cs = conexion.prepareCall(query);
            //  Asignamos Valores de los parametros 
            int i = 1; //   Indice del parametro a colocar
            for (String param: listParam){
                if (param==null){
                    cs.setString(i, "");
                } else {
                    cs.setString(i, param);
                }
                i++;
            }
            //  Registramos los parametros que serán devueltos por el procedimiento
            i = 1;
            for(Integer tipoDato: listOut){
                cs.registerOutParameter(i, tipoDato);
                i++;
            }
            
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("** Excepción al ejecutar SQL...\n" 
                    + ex.getMessage());
        }
        return cs;
    }
    
    public void conectar() {
        try {

            Class.forName(datosCon.getDriverClassName()).newInstance();
            this.conexion = DriverManager.getConnection(datosCon.getUrlBD(), datosCon.getUsrBD(), datosCon.getPassBD());
            //System.out.println("Conexion: " + conexion.getCatalog());
            conexion.setCatalog("BD_SID");
            //System.out.println("Conexion: " + conexion.getCatalog());
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            System.out.println("** Excepción para establecer conexión... **\n" 
                    + ex.getMessage());
        }
    }
    
    public void cerrarConexion() throws SQLException {
        this.conexion.close();
    }
}
