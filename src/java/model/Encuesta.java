package model;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author miamo
 */

public class Encuesta implements Serializable {

    private Integer idEncuesta;

    private String titulo;

    private Date fechaCreacion;

    private String idTipoencuesta;
    
    private CatDepto depto;

    public Encuesta() {
    }

    public Encuesta(Integer idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public Encuesta(Integer idEncuesta, Date fechaCreacion) {
        this.idEncuesta = idEncuesta;
        this.fechaCreacion = fechaCreacion;
    }

    public Encuesta(Integer idEncuesta, String titulo, Date fechaCreacion, String idTipoencuesta) {
        this.idEncuesta = idEncuesta;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
        this.idTipoencuesta = idTipoencuesta;
    }
    

    public Integer getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Integer idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getIdTipoencuesta() {
        return idTipoencuesta;
    }

    public void setIdTipoencuesta(String idTipoencuesta) {
        this.idTipoencuesta = idTipoencuesta;
    }

    public CatDepto getCatDepto() {
        return depto;
    }

    public void setCatDepto(CatDepto catDepto) {
        this.depto = catDepto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEncuesta != null ? idEncuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Encuesta)) {
            return false;
        }
        Encuesta other = (Encuesta) object;
        if ((this.idEncuesta == null && other.idEncuesta != null) || (this.idEncuesta != null && !this.idEncuesta.equals(other.idEncuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Encuesta[ idEncuesta=" + idEncuesta + " ]";
    }
    
}
