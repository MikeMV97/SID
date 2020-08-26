package model;

import java.io.Serializable;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author miamo
 */

public class Pregunta extends DynamicField implements Serializable {

    private Integer idPregunta;

    private String textoPreg;

    private Integer idEncuesta;

    private Integer idTipopreg;

    public Pregunta() {
    }

    public Pregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Pregunta(Integer idPregunta, String textoPreg) {
        this.idPregunta = idPregunta;
        this.textoPreg = textoPreg;
    }

    public Pregunta(Integer idPregunta, Integer idEncuesta, String label, Object value, String type, boolean required, List<SelectItem> selectItems) {
        super(label, value, type, required, selectItems);
        this.idPregunta = idPregunta;
        this.idEncuesta = idEncuesta;
        this.textoPreg = label;
    }

    public Integer getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getTextoPreg() {
        return textoPreg;
    }

    public void setTextoPreg(String textoPreg) {
        this.textoPreg = textoPreg;
    }

    public Integer getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Integer idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public Integer getIdTipopreg() {
        return idTipopreg;
    }

    public void setIdTipopreg(Integer idTipopreg) {
        this.idTipopreg = idTipopreg;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        
//        final Pregunta other = (Pregunta) obj;
//        if (textoPreg!=null? !this.textoPreg.equals(other.textoPreg): other.textoPreg!=null ) return false;
//        if ( idTipopreg!=null? this.idTipopreg.equals(other.idTipopreg) : other.idTipopreg!=null) return false;
//        
//        return true;
//    }
//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 11 * hash + Objects.hashCode(this.textoPreg);
//        hash = 11 * hash + Objects.hashCode(this.idTipopreg);
//        return hash;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 3;
//        hash = 79 * hash + Objects.hashCode(this.idTipopreg);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Pregunta other = (Pregunta) obj;
//        if (!Objects.equals(this.idTipopreg, other.idTipopreg)) {
//            return false;
//        }
//        return true;
//    }

}
