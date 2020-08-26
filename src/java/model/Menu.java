package model;

import java.io.Serializable;


/**
 *
 * @author miamo
 */

public class Menu implements Serializable {


    private short idMenu;

    private String nombreItem;

    private char tipoItem;

    private short idMenupadre;

    private Integer idTipoemp;
    private String direccion;

    public Menu() {
    }

    public Menu(Short idMenu) {
        this.idMenu = idMenu;
    }

    public Menu(Short idMenu, String nombreItem) {
        this.idMenu = idMenu;
        this.nombreItem = nombreItem;
    }

    public short getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(short idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;
    }

    public char getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(char tipoItem) {
        this.tipoItem = tipoItem;
    }

    public short getIdMenupadre() {
        return idMenupadre;
    }

    public void setIdMenupadre(short idMenupadre) {
        this.idMenupadre = idMenupadre;
    }

    public Integer getIdTipoemp() {
        return idTipoemp;
    }

    public void setIdTipoemp(Integer idTipoemp) {
        this.idTipoemp = idTipoemp;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
