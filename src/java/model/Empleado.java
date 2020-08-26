package model;

import java.io.Serializable;

/**
 *
 * @author miamo
 */
public class Empleado implements Serializable {

    private Integer idEmpleado;

    private char estadoEmp;

    private Integer idAgencia;

    private Integer idDepto;

    private Persona persona;

    private Integer idTipoemp;
    
    private Firma firma;

    public Empleado() {
    }

    public Empleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Empleado(Integer idEmpleado, char estadoEmp, Integer idAgencia, Integer idDepto, Persona persona, Integer idTipoemp, Firma firma) {
        this.idEmpleado = idEmpleado;
        this.estadoEmp = estadoEmp;
        this.idAgencia = idAgencia;
        this.idDepto = idDepto;
        this.persona = persona;
        this.idTipoemp = idTipoemp;
        this.firma = firma;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public char getEstadoEmp() {
        return estadoEmp;
    }

    public void setEstadoEmp(char estadoEmp) {
        this.estadoEmp = estadoEmp;
    }

    public Integer getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(Integer idAgencia) {
        this.idAgencia = idAgencia;
    }

    public Integer getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(Integer idDepto) {
        this.idDepto = idDepto;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Integer getIdTipoemp() {
        return idTipoemp;
    }

    public void setIdTipoemp(Integer idTipoemp) {
        this.idTipoemp = idTipoemp;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Empleado[ idEmpleado=" + idEmpleado + " ]";
    }
    
}
