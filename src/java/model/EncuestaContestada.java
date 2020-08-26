package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author miamo
 */
public class EncuestaContestada implements Serializable {

    private Integer idEncuestaconte;

    private Date fechaConte;

    private Date horaConte;

    private Integer idEmpleado;

    private Integer idEncuesta;

    public EncuestaContestada() {
    }

    public EncuestaContestada(Integer idEncuestaconte) {
        this.idEncuestaconte = idEncuestaconte;
    }

    public EncuestaContestada(Integer idEncuestaconte, Date fechaConte, Date horaConte) {
        this.idEncuestaconte = idEncuestaconte;
        this.fechaConte = fechaConte;
        this.horaConte = horaConte;
    }

    public Integer getIdEncuestaconte() {
        return idEncuestaconte;
    }

    public void setIdEncuestaconte(Integer idEncuestaconte) {
        this.idEncuestaconte = idEncuestaconte;
    }

    public Date getFechaConte() {
        return fechaConte;
    }

    public void setFechaConte(Date fechaConte) {
        this.fechaConte = fechaConte;
    }

    public Date getHoraConte() {
        return horaConte;
    }

    public void setHoraConte(Date horaConte) {
        this.horaConte = horaConte;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Integer idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEncuestaconte != null ? idEncuestaconte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EncuestaContestada)) {
            return false;
        }
        EncuestaContestada other = (EncuestaContestada) object;
        if ((this.idEncuestaconte == null && other.idEncuestaconte != null) || (this.idEncuestaconte != null && !this.idEncuestaconte.equals(other.idEncuestaconte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.EncuestaContestada[ idEncuestaconte=" + idEncuestaconte + " ]";
    }
    
}
