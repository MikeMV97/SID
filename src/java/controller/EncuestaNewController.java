package controller;

import Operaciones.DeptoFac;
import Operaciones.EncuestaFac;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.CatDepto;
import model.Encuesta;
import model.Pregunta;
import model.TipoEncuesta;
import model.TipoPregunta;

/**
 *
 * @author miamo
 */
@Named("encuestaNew")
@ViewScoped
public class EncuestaNewController implements Serializable{
    
    private List<SelectItem> lsSlctItmsTipoEnc;
    private String idTipoEnc;
    private List<SelectItem> lsSlctItmsDepto;
    private String idDepto;
    private List<SelectItem> lsSlctItmsTpRespuesta;
    private String idTpRespuesta;
    private Encuesta encuesta;
    private List<Pregunta> preguntas;
    private Pregunta pregunta;
    
    @PostConstruct
    public void init(){
        idTipoEnc = new String();
        idDepto = new String();
        idTpRespuesta = new String();
        encuesta = new Encuesta();
        pregunta = new Pregunta();
        preguntas = new ArrayList<Pregunta>();
    }
    
    public String reinit(){
        //  Si ya hay algo en la lista de preguntas...
        if (preguntas!=null? preguntas.size()>0 : false )
            //  Si la última pregunta añadida es diferente de null...
            if ( !(preguntas.get(preguntas.size()-1).getTextoPreg().replace(" ", "").equals("")) ){ 
                //  Si la pregunta añadida ya tiene TipoPregunta...
                if( pregunta!=null && (pregunta.getIdTipopreg()!=null? pregunta.getIdTipopreg()>0:false)){
                    EncuestaFac fac = new EncuestaFac();
                    TipoPregunta tp = fac.obtnrTpPregunta(null, pregunta.getIdTipopreg());            
                    preguntas.get(preguntas.size()-1).setType(tp.getCategoria());
                } else {
                    preguntas.remove(preguntas.size()-1);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Falta seleccionar Tipo de pregunta..."));
                }
            } else {
                preguntas.remove(preguntas.size()-1);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Teclea la pregunta..."));
            }
        pregunta = new Pregunta();
        return null;
    }
    
    public void guardarEncuesta(){
        if (encuesta.getTitulo()!=null? (!encuesta.getTitulo().replace(" ", "").equals("")):false){
            if(preguntas.size()>=5){
                try {
                    EncuestaFac fac = new EncuestaFac();
                    encuesta.setIdTipoencuesta(idTipoEnc);
                    encuesta.setCatDepto(new CatDepto(Integer.parseInt(idDepto)));
                    boolean msg = fac.insertarEncuesta(encuesta, preguntas);
                    
                    if (msg){
                        preguntas.clear();
                        encuesta=new Encuesta();
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Encuesta registrada..."));
                    } else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Error durante registro..."));
                } catch (Exception ex) {
                    Logger.getLogger(EncuestaNewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Se necesitan por lo menos cinco preguntas..."));
        } else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso", "Falta un título a la encuesta"));
    }
    
    public List<SelectItem> getLsSlctItmsTipoEnc(){
        try {
            EncuestaFac enc = new EncuestaFac();
            this.lsSlctItmsTipoEnc = new ArrayList<>();
            List<TipoEncuesta> list_enc = enc.obtnrTpsEncuesta();
            
            if (list_enc!=null){
                lsSlctItmsTipoEnc.clear();
                for (TipoEncuesta e : list_enc){
                    SelectItem sltItem = new SelectItem(e.getIdTipoencuesta(), e.getTipoEncuesta());
                    this.lsSlctItmsTipoEnc.add(sltItem);
                }
            } else lsSlctItmsTipoEnc.add(new SelectItem("1", "Error al obtnr valores TipoEncuesta"));
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lsSlctItmsTipoEnc;
    }
    public List<SelectItem> getLsSlctItmsDepto(){
        try {
            DeptoFac enc = new DeptoFac();
            this.lsSlctItmsDepto = new ArrayList<>();
            List<CatDepto> list_dto = enc.obtnrDepartamentos();
            
            if (list_dto!=null){
                lsSlctItmsDepto.clear();
                for (CatDepto e : list_dto){
                    SelectItem sltItem = new SelectItem(e.getIdDepto(), e.getNombDepto());
                    this.lsSlctItmsDepto.add(sltItem);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lsSlctItmsDepto;
    }
    public List<SelectItem> getLsSlctItmsTpRespuesta(){
        try {
            EncuestaFac enc = new EncuestaFac();
            this.lsSlctItmsTpRespuesta = new ArrayList<>();
            List<TipoPregunta> list_rsp = enc.obtnrTpsPregunta();
            
            if (list_rsp!=null){
                lsSlctItmsTpRespuesta.clear();
                for (TipoPregunta e : list_rsp){
                    SelectItem sltItem = new SelectItem(e.getIdTipopreg(), e.getCategoria());
                    this.lsSlctItmsTpRespuesta.add(sltItem);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lsSlctItmsTpRespuesta;
    }

    public String getIdTipoEnc() {
        return idTipoEnc;
    }

    public void setIdTipoEnc(String idTipoEnc) {
        this.idTipoEnc = idTipoEnc;
    }

    public String getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(String idDepto) {
        this.idDepto = idDepto;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public String getIdTpRespuesta() {
        return idTpRespuesta;
    }

    public void setIdTpRespuesta(String idTpRespuesta) {
        this.idTpRespuesta = idTpRespuesta;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> lsPregunta) {
        this.preguntas = lsPregunta;
    }
    
}
