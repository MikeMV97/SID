package controller;

import Operaciones.EncuestaFac;
import Operaciones.IndicadorFac;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import model.Encuesta;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author miamo
 */
@Named
@ViewScoped
public class IndicadorController implements Serializable{

    private int selectedOptn;
    private List<Encuesta> lsEncuestas;//Se inicializa en el init()->generarMenu()
    private MeterGaugeChartModel meterGaugeModel;
    private MenuModel modelEncuestas;
    
    @PostConstruct
    public void init(){
        selectedOptn=-1;
        generarMenu();
    }
    
    private void createMeterGaugeModels(String idEnc) {
        try {
            IndicadorFac fac = new IndicadorFac();
            Encuesta enc = new EncuestaFac().obtnrEncuesta(lsEncuestas, Integer.parseInt(idEnc));
            float porcent = fac.obtnrIndicador(Integer.parseInt(idEnc));
            meterGaugeModel = initMeterGaugeModel(porcent);
            meterGaugeModel.setTitle("KPI "+ enc.getTitulo());
            meterGaugeModel.setGaugeLabel("% efectividad");
            meterGaugeModel.setGaugeLabelPosition("bottom");
            //meterGaugeModel.setSeriesColors("cc6666,E7E658,93b75f,66cc66");
            meterGaugeModel.setSeriesColors("cc6666,ffd966,E7E658,66cc66");
        } catch (SQLException ex) {
            Logger.getLogger(IndicadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private MeterGaugeChartModel initMeterGaugeModel(float porcentaje) {
        List<Number> intervals = new ArrayList<Number>(){{
            add(10);
            add(30);
            add(65);
            add(100);
        }};
        return new MeterGaugeChartModel(porcentaje, intervals);
    }
    
    public void generarMenu(){
        modelEncuestas = new DefaultMenuModel();
        EncuestaFac eFac = new EncuestaFac();
        lsEncuestas = eFac.obtnrEncuestas();
        Integer idDeptoAnterior = lsEncuestas.get(0).getCatDepto().getIdDepto();
        boolean p1raIteracion = true;
        for (Encuesta enc : lsEncuestas){ //Este for para crear los submenus principales del Menu
            if ( p1raIteracion || !enc.getCatDepto().getIdDepto().equals(idDeptoAnterior) ){
                DefaultSubMenu submenu = new DefaultSubMenu(enc.getCatDepto().getNombDepto());
                for(Encuesta i : lsEncuestas){  // Este for para añadir los items a cada submenu
                    if ( enc.getCatDepto().getIdDepto().equals(i.getCatDepto().getIdDepto()) ){
                        DefaultMenuItem item = new DefaultMenuItem();
                        StringBuilder sb = new StringBuilder();
                        sb.append((i.getIdTipoencuesta().charAt(0)=='E')? "Emp-":"Client-");
                        sb.append(i.getTitulo());
                        item.setValue(sb.toString());
                        item.setCommand("#{indicadorController.setSelectedOptn("+i.getIdEncuesta()+")}");
                        item.setUpdate(":formIndicador:pnlCentIndicador");
                        submenu.addElement(item);
                    }
                }
                modelEncuestas.addElement(submenu);
                p1raIteracion=false;
            }
            idDeptoAnterior= enc.getCatDepto().getIdDepto();
        }
    }

    public MeterGaugeChartModel getMeterGaugeModel() {
        return meterGaugeModel;
    }
    public int getSelectedOptn() {
        return selectedOptn;
    }

    public void setSelectedOptn(String selectedOptn) {
        createMeterGaugeModels(selectedOptn);
        this.selectedOptn = Integer.parseInt(selectedOptn);
    }
    
    public MenuModel getModelEncuestas() {
        return modelEncuestas;
    }

    public void setModelEncuestas(MenuModel modelEncuestas) {
        this.modelEncuestas = modelEncuestas;
    }
    
}
