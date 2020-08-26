package model;

import java.io.Serializable;

/**
 *
 * @author miamo
 */
public class Agencia implements Serializable {
    
    private Integer idAgencia;

    private String nombAgencia;


    public Agencia() {
    }

    public Agencia(Integer idAgencia) {
        this.idAgencia = idAgencia;
    }

    public Agencia(Integer idAgencia, String nombAgencia) {
        this.idAgencia = idAgencia;
        this.nombAgencia = nombAgencia;
    }

    public Integer getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(Integer idAgencia) {
        this.idAgencia = idAgencia;
    }

    public String getNombAgencia() {
        return nombAgencia;
    }

    public void setNombAgencia(String nombAgencia) {
        this.nombAgencia = nombAgencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgencia != null ? idAgencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agencia)) {
            return false;
        }
        Agencia other = (Agencia) object;
        if ((this.idAgencia == null && other.idAgencia != null) || (this.idAgencia != null && !this.idAgencia.equals(other.idAgencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Agencia[ idAgencia=" + idAgencia + " ]";
    }
    
}
