package com.bicisoft.bicifast.basededatos;

import com.bicisoft.bicifast.aplicacion.Bicicleta;
import com.bicisoft.bicifast.aplicacion.Estacion;
import com.bicisoft.bicifast.aplicacion.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

/**
 * Clase que gestiona la tabla viaje de la base de datos
 */
final class DAOViaje extends AbstractDAO {
    //---- LOGGER ----
    private final static Logger logger = LoggerFactory.getLogger(DAOViaje.class);

    /**
     * @param conexion conexion a la base de datos
     */
    DAOViaje(Connection conexion) {
        super();
        this.setConexion(conexion);
    }


    /**
     * Finaliza un viaje, actualizando la tabla viaje
     *
     * @param usuarioLogado        Usuario que ha iniciado sesión
     * @param bicicleta            Bicicleta que se va a devolver
     * @param estacionSeleccionada Estación a la que se va a devolver la bicicleta
     * @return true si se ha devuelto correctamente, false en caso contrario
     */
    boolean devolverBicicleta(Usuario usuarioLogado, Bicicleta bicicleta, Estacion estacionSeleccionada) {
        Connection con = this.getConexion();
        int        numViajesAlterados;
        String consulta =
                "UPDATE viaje " +
                        "SET destino = ? " +
                        ", hora_fin = NOW() "
                        + "WHERE usuario = ?"
                        + " AND bicicleta = ? "
                        + " AND hora_fin IS NULL";
        try (java.sql.PreparedStatement stmBicisLibres = con.prepareStatement(consulta)) {
            try {
                stmBicisLibres.setInt(1, estacionSeleccionada.idEstacion());
                stmBicisLibres.setInt(2, usuarioLogado.idUsuario());
                stmBicisLibres.setInt(3, bicicleta.Id());
                numViajesAlterados = stmBicisLibres.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error al devolver la bicicleta");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Imposible cerrar el cursor");
            return false;
        }
        return numViajesAlterados > 0;
    }
}
