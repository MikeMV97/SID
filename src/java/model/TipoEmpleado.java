package model;

import java.io.Serializable;

/**
 *
 * @author miamo
 */

public class TipoEmpleado implements Serializable {


    private Integer idTipoemp;

    private String tipoEmp;

    public TipoEmpleado() {
    }

    public TipoEmpleado(Integer idTipoemp) {
        this.idTipoemp = idTipoemp;
    }

    public TipoEmpleado(Integer idTipoemp, String tipoEmp) {
        this.idTipoemp = idTipoemp;
        this.tipoEmp = tipoEmp;
    }

    public Integer getIdTipoemp() {
        return idTipoemp;
    }

    public void setIdTipoemp(Integer idTipoemp) {
        this.idTipoemp = idTipoemp;
    }

    public String getTipoEmp() {
        return tipoEmp;
    }

    public void setTipoEmp(String tipoEmp) {
        this.tipoEmp = tipoEmp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoemp != null ? idTipoemp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEmpleado)) {
            return false;
        }
        TipoEmpleado other = (TipoEmpleado) object;
        if ((this.idTipoemp == null && other.idTipoemp != null) || (this.idTipoemp != null && !this.idTipoemp.equals(other.idTipoemp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TipoEmpleado[ idTipoemp=" + idTipoemp + " ]";
    }
    
}
