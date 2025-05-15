package basededatos;

import aplicacion.Estacion;
import aplicacion.FachadaAplicacion;

import java.sql.Connection;
import java.util.List;

public class DAOEstacion extends AbstractDAO
{
    public DAOEstacion(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }


    public List<Estacion> preguntaLasEstaciones()
    {
        java.sql.PreparedStatement stmEstaciones = null;
        java.sql.ResultSet rsEstaciones;
        Connection con;
        List<Estacion> estaciones = new java.util.ArrayList<>();
        con = this.getConexion();
        String consulta =
                "SELECT " +
                        "id " +
                        "   ,ubicacion" +
                        "  , aforo " +
                        "FROM " +
                        "   estacion " +
                        "ORDER BY " +
                        "   ubicacion";
        try {
            stmEstaciones = con.prepareStatement(consulta);
            rsEstaciones = stmEstaciones.executeQuery();
            while (rsEstaciones.next()) {
                int idEstacion = rsEstaciones.getInt("id");
                String ubicacion = rsEstaciones.getString("ubicacion");
                int aforo = rsEstaciones.getInt("aforo");
                estaciones.add(new Estacion(idEstacion, ubicacion, aforo));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("EXCEPCION_CONSULTA_ESTACIONES");
        } finally {
            try {
                if (stmEstaciones != null) {
                    stmEstaciones.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("IMPOSIBLE_CERRAR_CONEXION");
            }
        }
        return estaciones;
    }
}
