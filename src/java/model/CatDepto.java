package model;

import java.io.Serializable;

/**
 *
 * @author miamo
 */
public class CatDepto implements Serializable {

    private Integer idDepto;

    private String nombDepto;

    public CatDepto() {
    }

    public CatDepto(Integer idDepto) {
        this.idDepto = idDepto;
    }

    public CatDepto(Integer idDepto, String nombDepto) {
        this.idDepto = idDepto;
        this.nombDepto = nombDepto;
    }

    public Integer getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(Integer idDepto) {
        this.idDepto = idDepto;
    }

    public String getNombDepto() {
        return nombDepto;
    }

    public void setNombDepto(String nombDepto) {
        this.nombDepto = nombDepto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDepto != null ? idDepto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatDepto)) {
            return false;
        }
        CatDepto other = (CatDepto) object;
        if ((this.idDepto == null && other.idDepto != null) || (this.idDepto != null && !this.idDepto.equals(other.idDepto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CatDepto[ idDepto=" + idDepto + " ]";
    }
    
}
