package DB;

/**
 *
 * @author miamo
 */
public class ConexionBD {
    private String usrBD;   
    private String passBD;  
    private String urlBD;
    private String driverClassName;

    public ConexionBD() {
        this.setConexion();
    }
    
    public void setConexion (){
        this.usrBD = "sa";
        this.passBD = "mimova";
        this.urlBD = "jdbc:sqlserver://localhost:1433";//jdbc:mysql://localhost:3306/bdEjemplo";
        this.driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//"com.mysql.jdbc.Driver";
        
    }

    public String getUsrBD() {
        return usrBD;
    }

    public void setUsrBD(String usrBD) {
        this.usrBD = usrBD;
    }

    public String getPassBD() {
        return passBD;
    }

    public void setPassBD(String passBD) {
        this.passBD = passBD;
    }

    public String getUrlBD() {
        return urlBD;
    }

    public void setUrlBD(String urlBD) {
        this.urlBD = urlBD;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

}
