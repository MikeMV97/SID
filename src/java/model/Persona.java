package model;

import java.io.Serializable;

/**
 *
 * @author miamo
 */

public class Persona implements Serializable {

    private Integer idPersona;

    private String nombPer;

    private String apellidosPer;

    private String correoPer;

    public Persona() {
    }

    public Persona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Persona(Integer idPersona, String nombPer) {
        this.idPersona = idPersona;
        this.nombPer = nombPer;
    }

    public Persona(Integer idPersona, String nombPer, String apellidosPer, String correoPer) {
        this.idPersona = idPersona;
        this.nombPer = nombPer;
        this.apellidosPer = apellidosPer;
        this.correoPer = correoPer;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombPer() {
        return nombPer;
    }

    public void setNombPer(String nombPer) {
        this.nombPer = nombPer;
    }

    public String getApellidosPer() {
        return apellidosPer;
    }

    public void setApellidosPer(String apellidosPer) {
        this.apellidosPer = apellidosPer;
    }

    public String getCorreoPer() {
        return correoPer;
    }

    public void setCorreoPer(String correoPer) {
        this.correoPer = correoPer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Persona[ idPersona=" + idPersona + " ]";
    }
    
}
