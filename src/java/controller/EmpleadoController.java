package controller;


import Operaciones.AgenciaFac;
import Operaciones.DeptoFac;
import Operaciones.UsuarioFac;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Agencia;
import model.CatDepto;
import model.Empleado;
import model.Firma;
import model.Persona;
import model.TipoEmpleado;

@Named
@ViewScoped
public class EmpleadoController implements Serializable{

    private Persona persona;
    private Empleado empleado;
    private Firma login;
    private String agencia;
    private String tpEmp;
    private String depto;
    private List<SelectItem> selectItemsAgencia;
    private List<SelectItem> selectItemsEmpleado;
    private List<SelectItem> selectItemsDeptos;
    
    @PostConstruct
    public void init(){
        persona = new Persona();
        empleado = new Empleado();
        login = new Firma();
    }
    
    public void registrar(){
        UsuarioFac usrF = new UsuarioFac();
            //empleado.setIdEmpleado(persona.getIdPersona());
            empleado.setIdDepto(Integer.parseInt(depto));
            empleado.setIdAgencia(Integer.parseInt(agencia));
            empleado.setIdTipoemp(Integer.parseInt(tpEmp));
            
            boolean msg = usrF.insertarUsr(empleado, persona, login);
            if (msg)
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro exitoso..."));
            else
                FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Empleado no registrado :'( "));
    }
    
    public List<SelectItem> getSelectItemsAgencia(){
        try {
            AgenciaFac agen = new AgenciaFac();
            this.selectItemsAgencia = new ArrayList<>();
            List<Agencia> list_agen = agen.obtnrAgencias();//agenEJB.findAll();
            
            this.selectItemsAgencia.clear();
            for (Agencia ag : list_agen){
                SelectItem sltItem = new SelectItem(ag.getIdAgencia(), ag.getNombAgencia());
                this.selectItemsAgencia.add(sltItem);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return selectItemsAgencia;
    }
    
    public List<SelectItem> getSelectItemsEmpleado(){
        try {
            this.selectItemsEmpleado = new ArrayList<>();
            UsuarioFac usrF = new UsuarioFac();
            List<TipoEmpleado> list_tpEmps = usrF.obtnrTpEmpleados();//tpEmpEJB.findAll();
            
            this.selectItemsEmpleado.clear();
            for (TipoEmpleado emp : list_tpEmps){
                SelectItem siEmp = new SelectItem(emp.getIdTipoemp(), emp.getTipoEmp());
                this.selectItemsEmpleado.add(siEmp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return selectItemsEmpleado;
    }
    
    public List<SelectItem> getSelectItemsDeptos(){
        try {
            this.selectItemsDeptos = new ArrayList<>();
            DeptoFac dpF = new DeptoFac();
            List<CatDepto> list_tpEmps = dpF.obtnrDepartamentos();//tpEmpEJB.findAll();
            
            this.selectItemsDeptos.clear();
            for (CatDepto emp : list_tpEmps){
                SelectItem siEmp = new SelectItem(emp.getIdDepto(), emp.getNombDepto());
                this.selectItemsDeptos.add(siEmp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return selectItemsDeptos;
    }
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Firma getLogin() {
        return login;
    }

    public void setLogin(Firma login) {
        this.login = login;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getTpEmp() {
        return tpEmp;
    }

    public void setTpEmp(String tpEmp) {
        this.tpEmp = tpEmp;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }
    
}
