package model;

import java.io.Serializable;

/**
 *
 * @author miamo
 */

public class RelEncCliente implements Serializable {

    private Integer idEncclient;
    private String motivo;

    private Integer idEncuesta;

    private Integer idCliente;

    public RelEncCliente() {
    }

    public RelEncCliente(Integer idEncclient) {
        this.idEncclient = idEncclient;
    }

    public RelEncCliente(Integer idEncclient, String motivo) {
        this.idEncclient = idEncclient;
        this.motivo = motivo;
    }

    public Integer getIdEncclient() {
        return idEncclient;
    }

    public void setIdEncclient(Integer idEncclient) {
        this.idEncclient = idEncclient;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Integer idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEncclient != null ? idEncclient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelEncCliente)) {
            return false;
        }
        RelEncCliente other = (RelEncCliente) object;
        if ((this.idEncclient == null && other.idEncclient != null) || (this.idEncclient != null && !this.idEncclient.equals(other.idEncclient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.RelEncCliente[ idEncclient=" + idEncclient + " ]";
    }
    
}
