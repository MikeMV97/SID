package model;

import java.io.Serializable;

/**
 *
 * @author miamo
 */

public class TipoPregunta implements Serializable {

    private Integer idTipopreg;

    private String tipoPreg;
    private String categoria;

    public TipoPregunta() {
    }

    public TipoPregunta(Integer idTipopreg) {
        this.idTipopreg = idTipopreg;
    }

    public TipoPregunta(Integer idTipopreg, String tipoPreg, String categoria) {
        this.idTipopreg = idTipopreg;
        this.tipoPreg = tipoPreg;
        this.categoria = categoria;
    }

    public Integer getIdTipopreg() {
        return idTipopreg;
    }

    public void setIdTipopreg(Integer idTipopreg) {
        this.idTipopreg = idTipopreg;
    }

    public String getTipoPreg() {
        return tipoPreg;
    }

    public void setTipoPreg(String tipoPreg) {
        this.tipoPreg = tipoPreg;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipopreg != null ? idTipopreg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPregunta)) {
            return false;
        }
        TipoPregunta other = (TipoPregunta) object;
        if ((this.idTipopreg == null && other.idTipopreg != null) || (this.idTipopreg != null && !this.idTipopreg.equals(other.idTipopreg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TipoPregunta[ idTipopreg=" + idTipopreg + " ]";
    }
    
}
