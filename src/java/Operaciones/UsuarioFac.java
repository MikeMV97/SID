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
import model.Empleado;
import model.Firma;
import model.Persona;
import model.TipoEmpleado;

/**
 *
 * @author miamo
 */
public class UsuarioFac {

    private ManejadorJDBC jdbc;
    public UsuarioFac() {
        jdbc = new ManejadorJDBC();
    }
    
    public Empleado inicioSesion(String mail, String psw) {
        Empleado empleado=null;
        Persona persona;
        try {
            List<String> listParam = new ArrayList<>(); listParam.clear();
            listParam.add(mail);
            listParam.add(psw);
            CallableStatement cs = jdbc.ejecutarIN("{call sp_firmar (?, ?)}", listParam);
            if (cs!=null){
                ResultSet rs = cs.getResultSet();
                if(rs.next()){
                    //CorreoPer correo = new CorreoPer(rs.getInt(1), mail);
                    persona = new Persona(rs.getInt(1));
                    persona.setCorreoPer(mail);
                    persona.setNombPer(rs.getString(2));
                    persona.setApellidosPer(rs.getString(3));
                    empleado = new Empleado(rs.getInt(4));
                    empleado.setPersona(persona);
                    empleado.setIdDepto(rs.getInt(5));
                    empleado.setIdAgencia(rs.getInt(6));
                    empleado.setIdTipoemp(rs.getInt(7));
                    empleado.setEstadoEmp(rs.getString(8).charAt(0));
                    empleado.setFirma(new Firma(persona.getIdPersona()));
                    empleado.getFirma().setPswd(psw);
                } else {
                    persona = null;
                    empleado = null;
                }
            } else {
                    persona = null;
                    empleado = null;
                }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioFac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleado;
    }
    
    public boolean insertarUsr(Empleado e, Persona p, Firma f){
        boolean rsp = false;
        try {
            List<String> lsp = new LinkedList<String>();
            lsp.clear();
            lsp.add(Integer.toString(0));
            lsp.add(p.getCorreoPer());
            lsp.add(p.getNombPer());
            lsp.add(p.getApellidosPer());
            List<Integer> lsop = new ArrayList<>();
            lsop.clear();
            lsop.add(Types.INTEGER);
            CallableStatement cs = jdbc.ejecutarINOUT("{call sp_Persona(?, ?, ?, ?)}", lsp, lsop);
            //ResultSet rs = cs.getResultSet();
            if (cs != null){ 
                p.setIdPersona(cs.getInt(1));
                if (p.getIdPersona()>0){
                    lsp.clear();
                    lsp.add(p.getIdPersona().toString());
                    lsp.add(e.getIdDepto().toString());
                    lsp.add(e.getIdAgencia().toString());
                    lsp.add(e.getIdTipoemp().toString());
                    lsp.add(f.getPswd());
                    cs = jdbc.ejecutarIN("{call sp_agrEmpleado(?, ?, ?, ?, 'A', ?)}", lsp);
                    rsp = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioFac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rsp;
    }
    
    public boolean modificarUsr(Empleado e, Persona p, Firma f){
        boolean msg = false;
        try {
            List<String> lsp = new LinkedList<>();
            lsp.clear();
            if(p!=null){
                lsp.add(Integer.toString(p.getIdPersona()));
                lsp.add(p.getCorreoPer());
                lsp.add(p.getNombPer());
                lsp.add(p.getApellidosPer());
                List<Integer> lsop = new ArrayList<>();
                lsop.clear();
                lsop.add(Types.INTEGER);
                CallableStatement cs = jdbc.ejecutarINOUT("{call sp_Persona(?, ?, ?, ?)}", lsp, lsop);
                //ResultSet rs = cs.getResultSet();
                if (cs != null){ 
                    p.setIdPersona(cs.getInt(1));
                    if (p.getIdPersona()>0){
                        lsp.clear();
                        lsp.add(p.getIdPersona().toString());
                        lsp.add(e.getIdDepto().toString());
                        lsp.add(e.getIdAgencia().toString());
                        lsp.add(e.getIdTipoemp().toString());
                        lsp.add(Character.toString(e.getEstadoEmp()));
                        if(f!=null)    lsp.add(f.getPswd());
                        else lsp.add("mazda");
                        cs = jdbc.ejecutarIN("{call sp_modEmpleado(?, ?, ?, ?, ?, ?)}", lsp);
                        msg = true;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioFac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
    
    public boolean borrarUsr(int idEmpleado){
        boolean msg = false;
        List<String> ls =new ArrayList<>();     ls.clear();
        ls.add(Integer.toString(idEmpleado));
        CallableStatement cs = jdbc.ejecutarIN("{call sp_elimEmpleado(?)}", ls);
        if (cs!= null){
            msg=true;
        }
        return msg;
    }
    
    public List<Empleado> obtnrEmpleados(char estado){
        List<Empleado> ls =null;
        try {
            ResultSet rs = jdbc.consultar("select * from vw_empleados where estado_emp = '"+estado+"'");
            ls=new LinkedList<>(); ls.clear();
            while(rs.next()){
                Integer i = rs.getInt("idEmpleado");
                Persona p = new Persona(i, rs.getString("nomb_per"), rs.getString("apellidos_per"), rs.getString("correo_elec"));
                ls.add(new Empleado(i, rs.getString("estado_emp").charAt(0) , rs.getInt("idAgencia"), rs.getInt("idDepto"), p, rs.getInt("idTipo_emp"), null));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioFac.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }
    
    public List<TipoEmpleado> obtnrTpEmpleados() throws SQLException{
        List<TipoEmpleado> lsTpEmp = new ArrayList<>();
        ResultSet rs = jdbc.consultar("select m.* from Tipo_empleado m");
        lsTpEmp.clear();
        TipoEmpleado m;
        while (rs.next()) {
            m = new TipoEmpleado(rs.getInt(1), rs.getString(2));
            lsTpEmp.add(m);
            m = null;
        }

        return lsTpEmp;
    }

}
