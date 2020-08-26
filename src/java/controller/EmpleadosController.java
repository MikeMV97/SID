package controller;

import Operaciones.UsuarioFac;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Empleado;
import org.primefaces.context.RequestContext;

/**
 *
 * @author miamo
 */
@Named
@ViewScoped
public class EmpleadosController implements Serializable{
    private List<Empleado> lsEmpleados;
    private List<Empleado> lsEmpleadosBloqueados;
    
    @PostConstruct
    public void init(){
        lsEmpleados = traerEmpls('A');
        lsEmpleadosBloqueados = traerEmpls('B');
    }
    public void borarEmpleado(int idEmpleado){
        UsuarioFac usrFac = new UsuarioFac();
        boolean msg = usrFac.borrarUsr(idEmpleado);
        RequestContext.getCurrentInstance().update("formEmpleados:pgEmpleados");
        System.out.println("*******\nEmpleado eliminado: "+idEmpleado+"**********\n");
    }
    
    public void desactivarEmpleado(Empleado e){
        UsuarioFac usrFac = new UsuarioFac();
        e.setEstadoEmp('B');
        boolean msg = usrFac.modificarUsr(e, e.getPersona(), null);
        
    }
    
    public void activarEmpleado(Empleado e){
        UsuarioFac usrFac = new UsuarioFac();
        e.setEstadoEmp('A');
        boolean msg = usrFac.modificarUsr(e, e.getPersona(), null);
    }
    
    public List<Empleado> traerEmpls(char estado){
        UsuarioFac usrFac = new UsuarioFac();
        return usrFac.obtnrEmpleados(estado);
    }

    public List<Empleado> getLsEmpleados() {
        return lsEmpleados;
    }

    public List<Empleado> getLsEmpleadosBloqueados() {
        return lsEmpleadosBloqueados;
    }
    
}
