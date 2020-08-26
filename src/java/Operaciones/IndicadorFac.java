package Operaciones;

import DB.ManejadorJDBC;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author miamo
 */
public class IndicadorFac {
    private ManejadorJDBC jdbc;

    public IndicadorFac() {
        jdbc = new ManejadorJDBC();
    }
    
    public float obtnrIndicador(int idEnc) throws SQLException{
        float porcentaje=0f;
        List<String> listParam = new ArrayList<>(); listParam.clear();
        List<Integer> listOut = new LinkedList<>(); listOut.clear();
        listParam.add("0");
        listParam.add(Integer.toString(idEnc));
        listOut.add(Types.FLOAT);
        CallableStatement cs = jdbc.ejecutarINOUT("{call sp_obtnrIndicador (?, ?)}", listParam, listOut);
        if (cs!=null) {
            porcentaje = cs.getFloat(1);
        }
        return porcentaje;
    }
    
}
