package basededatos;

import aplicacion.Bicicleta;
import aplicacion.Estacion;
import aplicacion.FachadaAplicacion;
import aplicacion.Usuario;

import java.sql.Connection;

public class DAOViaje extends AbstractDAO {
    public DAOViaje(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }


    public void devolverBicicleta(Usuario usuarioLogado, Bicicleta bicicleta, Estacion estacionSeleccionada) {
        java.sql.PreparedStatement stmBicisLibres = null;
        Connection                 con            = this.getConexion();
        String consulta =
                "UPDATE viaje " +
                        "SET destino = ? " +
                        ", hora_fin = NOW() "
                        + "WHERE usuario = ?"
                        + " AND bicicleta = ? "
                        + " AND hora_fin IS NULL";
        try {
            stmBicisLibres = con.prepareStatement(consulta);
            stmBicisLibres.setInt(1, estacionSeleccionada.idEstacion());
            stmBicisLibres.setInt(2, usuarioLogado.idUsuario());
            stmBicisLibres.setInt(3, bicicleta.Id());
            stmBicisLibres.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al devolver la bicicleta");
        } finally {
            try {
                if (stmBicisLibres != null) {
                    stmBicisLibres.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Imposible cerrar el cursor");
            }
        }
    }
}
