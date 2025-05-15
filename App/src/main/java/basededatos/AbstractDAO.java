
package basededatos;

import aplicacion.FachadaAplicacion;

import java.sql.*;



public abstract class AbstractDAO
{
    public static final String IMPOSIBLE_CERRAR_CONEXION = "Imposible cerrar conexión";
    public static final String IMPOSIBLE_CERRAR_CURSORES = "Imposible cerrar cursores";

    private FachadaAplicacion fa;
    private Connection        conexion;


    protected Connection getConexion() {
        return this.conexion;
    }

    protected void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    protected void setFachadaAplicacion(FachadaAplicacion fa) {
        this.fa = fa;
    }

    protected FachadaAplicacion getFachadaAplicacion(){
        return fa;
    }


}
