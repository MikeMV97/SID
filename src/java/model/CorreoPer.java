package model;

import java.io.Serializable;

/**
 *
 * @author miamo
 */
public class CorreoPer implements Serializable {

    private Integer idPersona;
    private String correoElec;

    public CorreoPer() {
    }

    public CorreoPer(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public CorreoPer(Integer idPersona, String correoElec) {
        this.idPersona = idPersona;
        this.correoElec = correoElec;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getCorreoElec() {
        return correoElec;
    }

    public void setCorreoElec(String correoElec) {
        this.correoElec = correoElec;
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
        if (!(object instanceof CorreoPer)) {
            return false;
        }
        CorreoPer other = (CorreoPer) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CorreoPer[ idPersona=" + idPersona + " ]";
    }
    
}
