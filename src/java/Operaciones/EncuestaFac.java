package Operaciones;

import DB.ManejadorJDBC;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.SelectItem;
import model.CatDepto;
import model.DynamicField;
import model.Encuesta;
import model.Pregunta;
import model.Respuestacontestada;
import model.TipoEncuesta;
import model.TipoPregunta;

/**
 *
 * @author miamo
 */
public class EncuestaFac {
    private ManejadorJDBC jdbc;

    public EncuestaFac() {
        jdbc = new ManejadorJDBC();
    }
    
    public boolean insertarEncuesta(Encuesta encuesta, List<Pregunta> preguntas) throws SQLException{
        boolean msg =false;
        List<String> lsIn = new ArrayList<>(); lsIn.clear();
        List<Integer> lsOut = new LinkedList<>(); lsOut.clear();
        
        lsIn.add("0");
        lsIn.add(encuesta.getTitulo());
        lsIn.add(encuesta.getIdTipoencuesta());
        lsIn.add(encuesta.getCatDepto().getIdDepto().toString());
        lsOut.add(Types.INTEGER);
        CallableStatement cs = jdbc.ejecutarINOUT("{call sp_Encuesta (?, ?, ?, ?)}", lsIn, lsOut);
        
        if(cs!=null){
            String idEncuesta = Integer.toString(cs.getInt(1));
            if (Integer.parseInt(idEncuesta)>0){
                for(Pregunta p : preguntas){
                    lsIn.clear();
                    lsIn.add(idEncuesta);
                    lsIn.add(p.getTextoPreg());
                    lsIn.add(p.getIdTipopreg().toString());
                    cs = jdbc.ejecutarIN("{call sp_agrPregunta (?, ?, ?)}", lsIn);
                }
                msg = true;
            }
        }
        
        return msg;
    }
    
    public boolean contestarEncuesta(int idEmp, int idEncuestaSelected, List<Respuestacontestada> lsRespConte){
        
        return false;
    }
    
    public List<Encuesta> obtnrEncuestas(){
        List<Encuesta> ls = new ArrayList<>();
            ls.clear();
        try {
            
            ResultSet rs = jdbc.consultar("select * from vw_encuesta");
            while(rs.next()){
                Encuesta e = new Encuesta(rs.getInt("idEncuesta"), rs.getString("titulo"), rs.getDate("fecha_creacion"), rs.getString("idTipo_encuesta"));
                e.setCatDepto(new CatDepto(rs.getInt("idDepto"), rs.getString("nomb_depto")));
                ls.add(e);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(EncuestaFac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }
    
    public Encuesta obtnrEncuesta(List<Encuesta> ls, int idEnc){
        Encuesta enc = null;
        if(ls==null)
            ls = obtnrEncuestas();
        for (Encuesta e : ls){
            if( e.getIdEncuesta().compareTo(idEnc)==0 )
                enc = e;
        }
        return enc;
    }

    public List<TipoEncuesta> obtnrTpsEncuesta(){
        List<TipoEncuesta> lsTpEnc = new ArrayList<>();
        try {
            ResultSet rs = jdbc.consultar("select * from vw_tpEncuesta");
            lsTpEnc.clear();
            TipoEncuesta m;
            while (rs.next()) {
                m = new TipoEncuesta(rs.getString(1).charAt(0), rs.getString(2));
                lsTpEnc.add(m);
                m = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EncuestaFac.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return lsTpEnc;
    }
    
    public List<TipoPregunta> obtnrTpsPregunta(){
        List<TipoPregunta> lsTp = new ArrayList<>();
        try {
            ResultSet rs = jdbc.consultar("select * from vw_tpPregunta");
            lsTp.clear();
            TipoPregunta m;
            while (rs.next()) {
                m = new TipoPregunta(rs.getInt("idTipo_preg"), rs.getString("tipo_preg"), rs.getString("categoria"));
                lsTp.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EncuestaFac.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return lsTp;
    }
    
    public TipoPregunta obtnrTpPregunta(List<TipoPregunta> ls, Integer id){
        TipoPregunta tp=null;
        if(ls==null)
            ls=obtnrTpsPregunta();
        for (TipoPregunta t : ls)
            if ( t!=null ? t.getIdTipopreg()!=null? t.getIdTipopreg()==id : false : false){
                tp=t;
                break;
            }
        
        return tp;
    }
            
    public List<DynamicField> obtnrCamposForm (int idEnc){
        try {
            //  En este método se obtendrán los campos a llenar en el formulario que
            //  ya debió de haber sido especificado por el usr que los consultó.
            List<DynamicField> lsDF = new ArrayList<>(); 
            lsDF.clear();
            ResultSet rsPreg = jdbc.consultar("select * from vw_Pregunta "
                                                + "where idEncuesta = "+idEnc+"");
            while(rsPreg.next()){
                ResultSet rsResp = jdbc.consultar("select r.* from vw_respuesta r " +
                                                    "where r.idPregunta = "+rsPreg.getInt(1));
                Integer idPreg = rsPreg.getInt("idPregunta");
                List<SelectItem> lsSItem = new ArrayList<>();
                lsSItem.clear();    boolean hasItems=false;
                while(rsResp.next()){
                    hasItems=true;
                    lsSItem.add(new SelectItem(Integer.getInteger(rsResp.getString("idRespuesta")), rsResp.getString("texto_resp")));
                }
//                String tpPreg = ;
//                tpPreg = (tpPreg.charAt(tpPreg.length()-1)=='2')? "select" : tpPreg; // Para la opción: tipo_preg = 'select2'
                lsDF.add(new Pregunta(idPreg, idEnc, rsPreg.getString("texto_preg"), idPreg, rsPreg.getString("tipo_preg"), false, (hasItems)?lsSItem:null) );
            }
            return lsDF;
        } catch (SQLException ex) {
            System.out.println("    ******  Catch EncuestaFac: obtnrCamposForm(). ******\n"+ex.getMessage());
            //Logger.getLogger(EncuestaFac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
