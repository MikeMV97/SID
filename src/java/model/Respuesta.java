package model;

import java.io.Serializable;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author miamo
 */

public class Respuesta extends DynamicField implements Serializable {

    private Integer idRespuesta;

    private String textoResp;
    
    private Integer idPregunta;

    private Short valorResp;

    public Respuesta() {
    }

    public Respuesta(Integer idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public Respuesta(Integer idRespuesta, Integer idPregunta, String label, Object value, String type, boolean required, List<SelectItem> selectItems) {
        super(label, value, type, required, selectItems);
        this.idRespuesta = idRespuesta;
        this.textoResp = label;
        this.idPregunta = idPregunta;
        this.valorResp = (Short) value;
    }
    

    public Integer getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(Integer idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public String getTextoResp() {
        return textoResp;
    }

    public void setTextoResp(String textoResp) {
        this.textoResp = textoResp;
    }

    public Integer getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Short getValorResp() {
        return valorResp;
    }

    public void setValorResp(Short valorResp) {
        this.valorResp = valorResp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespuesta != null ? idRespuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuesta)) {
            return false;
        }
        Respuesta other = (Respuesta) object;
        if ((this.idRespuesta == null && other.idRespuesta != null) || (this.idRespuesta != null && !this.idRespuesta.equals(other.idRespuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Respuesta[ idRespuesta=" + idRespuesta + " ]";
    }
    
}
