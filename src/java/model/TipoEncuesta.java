package model;

import java.io.Serializable;

/**
 *
 * @author miamo
 */

public class TipoEncuesta implements Serializable {

    private char idTipoencuesta;

    private String tipoEncuesta;

    public TipoEncuesta() {
    }

    public TipoEncuesta(char idTipoencuesta) {
        this.idTipoencuesta = idTipoencuesta;
    }

    public TipoEncuesta(char idTipoencuesta, String tipoEncuesta) {
        this.idTipoencuesta = idTipoencuesta;
        this.tipoEncuesta = tipoEncuesta;
    }

    public char getIdTipoencuesta() {
        return idTipoencuesta;
    }

    public void setIdTipoencuesta(char idTipoencuesta) {
        this.idTipoencuesta = idTipoencuesta;
    }

    public String getTipoEncuesta() {
        return tipoEncuesta;
    }

    public void setTipoEncuesta(String tipoEncuesta) {
        this.tipoEncuesta = tipoEncuesta;
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (idTipoencuesta != null ? idTipoencuesta.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof TipoEncuesta)) {
//            return false;
//        }
//        TipoEncuesta other = (TipoEncuesta) object;
//        if ((this.idTipoencuesta == null && other.idTipoencuesta != null) || (this.idTipoencuesta != null && !this.idTipoencuesta.equals(other.idTipoencuesta))) {
//            return false;
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "model.TipoEncuesta[ idTipoencuesta=" + idTipoencuesta + " ]";
    }
    
}
