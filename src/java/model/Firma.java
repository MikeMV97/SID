package model;

import java.io.Serializable;

/**
 *
 * @author miamo
 */
public class Firma implements Serializable {

    private Integer idFirmaemp;
    
    private String pswd;

    public Firma() {
    }

    public Firma(Integer idFirmaemp) {
        this.idFirmaemp = idFirmaemp;
    }

    public Integer getIdFirmaemp() {
        return idFirmaemp;
    }

    public void setIdFirmaemp(Integer idFirmaemp) {
        this.idFirmaemp = idFirmaemp;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFirmaemp != null ? idFirmaemp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Firma)) {
            return false;
        }
        Firma other = (Firma) object;
        if ((this.idFirmaemp == null && other.idFirmaemp != null) || (this.idFirmaemp != null && !this.idFirmaemp.equals(other.idFirmaemp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Firma[ idFirmaemp=" + idFirmaemp + " ]";
    }
    
}
