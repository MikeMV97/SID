package model;

import java.io.Serializable;

/**
 *
 * @author miamo
 */
public class Respuestacontestada implements Serializable {

    private Integer idRespconte;

    private Integer idPregunta;

    private Integer idRespuesta;

    public Respuestacontestada() {
    }

    public Respuestacontestada(Integer idRespconte) {
        this.idRespconte = idRespconte;
    }

    public Integer getIdRespconte() {
        return idRespconte;
    }

    public void setIdRespconte(Integer idRespconte) {
        this.idRespconte = idRespconte;
    }

    public Integer getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Integer getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(Integer idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespconte != null ? idRespconte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuestacontestada)) {
            return false;
        }
        Respuestacontestada other = (Respuestacontestada) object;
        if ((this.idRespconte == null && other.idRespconte != null) || (this.idRespconte != null && !this.idRespconte.equals(other.idRespconte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Respuestacontestada[ idRespconte=" + idRespconte + " ]";
    }
    
}
