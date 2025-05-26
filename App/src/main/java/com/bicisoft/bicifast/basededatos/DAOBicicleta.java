package com.bicisoft.bicifast.basededatos;

import com.bicisoft.bicifast.aplicacion.Bicicleta;
import com.bicisoft.bicifast.aplicacion.Estacion;
import com.bicisoft.bicifast.aplicacion.EstadoBicicleta;
import com.bicisoft.bicifast.aplicacion.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

/**
 * Clase que implementa la interfaz DAO para la entidad Bicicleta.
 * Esta clase se encarga de realizar las operaciones de acceso a datos relacionadas con las bicicletas.
 */
final class DAOBicicleta extends AbstractDAO {
    //---- LOGGER ----
    private final static Logger logger = LoggerFactory.getLogger(DAOBicicleta.class);

    /**
     * Constructor de la clase DAOBicicleta.
     *
     * @param conexion Conexión a la base de datos.
     */
    DAOBicicleta(Connection conexion) {
        super();
        this.setConexion(conexion);
    }


    /**
     * Método que obtiene una lista de bicicletas disponibles en una estación específica.
     *
     * @param estacionUsada La estación de la que se quieren obtener las bicicletas.
     * @return Lista de bicicletas disponibles en la estación especificada.
     */
    List<Bicicleta> obtenerBicisPorEstacion(Estacion estacionUsada) {
        java.sql.PreparedStatement stmBicisLibres = null;
        java.sql.ResultSet         datosEntrada;
        List<Bicicleta>            resultado      = new java.util.ArrayList<>();
        Connection                 con            = this.getConexion();
        // Sencilla consulta a la tabla bicicleta, más sencilla no la hay
        String consulta =
                "Select id, estado "
                        + "From bicicleta "
                        + "Where estacion = ? ";
        try {
            stmBicisLibres = con.prepareStatement(consulta);
            stmBicisLibres.setInt(1, estacionUsada.idEstacion());
            datosEntrada = stmBicisLibres.executeQuery();
            while (datosEntrada.next()) {
                resultado.add(new Bicicleta(datosEntrada.getInt("id"), estacionUsada, EstadoBicicleta.valueOf(datosEntrada.getString("estado"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Fallo en la consulta de bicicletas: {}", e.getMessage());
        } finally {
            try {
                if (stmBicisLibres != null) {
                    stmBicisLibres.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error al cerrar la conexión: {}", e.getMessage());
            }
        }

        return resultado;
    }

    /**
     * Método que obtiene una lista de bicicletas en uso en una estación específica.
     *
     * @param estacionUsada La estación de la que se quieren obtener las bicicletas en uso.
     * @return Lista de bicicletas en uso en la estación especificada.
     */
    List<Bicicleta> obtenerBicisEnUsoPorEstacion(Estacion estacionUsada) {
        java.sql.PreparedStatement stmBicisLibres = null;
        java.sql.ResultSet         datosEntrada;
        List<Bicicleta>            resultado      = new java.util.ArrayList<>();
        Connection                 con            = this.getConexion();

        // SEL(id,estado) CUANDO BICICLETA EN ESTACIÓN Y BICICLETA EN VIAJE Y HAY UN VIAJE SIN FIN
        String consulta =
                "SELECT " +
                        "    b.id " +
                        "   , b.estado " +
                        "FROM " +
                        "    bicicleta b JOIN viaje v ON b.id = v.bicicleta " +
                        "WHERE" +
                        "  v.hora_fin IS NULL " +
                        "  AND" +
                        "  b.estacion = ?";
        try {
            stmBicisLibres = con.prepareStatement(consulta);
            stmBicisLibres.setInt(1, estacionUsada.idEstacion());
            datosEntrada = stmBicisLibres.executeQuery();
            while (datosEntrada.next()) {
                resultado.add(new Bicicleta(datosEntrada.getInt("id"), estacionUsada, EstadoBicicleta.valueOf(datosEntrada.getString("estado"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error en la consulta de bicicletas en uso: {}", e.getMessage());
        } finally {
            try {
                if (stmBicisLibres != null) {
                    stmBicisLibres.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error al cerrar cursores: {}", e.getMessage());
            }
        }
        return resultado;
    }

    /**
     * Método que deja una bicicleta en una estación específica.
     *
     * @param bicicleta            La bicicleta que se va a dejar en la estación.
     * @param estacionSeleccionada La estación en la que se va a dejar la bicicleta.
     */
    void estacionarBicicleta(Bicicleta bicicleta, Estacion estacionSeleccionada) {
        Connection con = this.getConexion();
        // Simple Update: ESTACION_NUEVA(n+1) <- BICICLETA ; ESTACION_ANTIGUA(BICICLETA.id) <- (Ø)
        String consulta =
                "UPDATE bicicleta " +
                        "SET estacion = ? " +
                        "WHERE id = ?";
        try (java.sql.PreparedStatement stmBicisLibres = con.prepareStatement(consulta)) {
            try {
                stmBicisLibres.setInt(1, estacionSeleccionada.idEstacion());
                stmBicisLibres.setInt(2, bicicleta.Id());
                stmBicisLibres.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error al estacionar la bicicleta: {}", e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Imposible cerrar el cursor");
        }
    }

    /**
     * Este método cuenta el número de bicicletas libres (es decir, sin uso) por estación.
     *
     * @return Una lista de enteros que representa el número de bicicletas libres en cada estación.
     */
    List<Integer> preguntaLasBicicletasPorEstacion() {
        // cuenta por orden de ubicacion cuantas bicicletas hay en cada estacion
        java.sql.PreparedStatement stmBicisLibres = null;
        java.sql.ResultSet         datosEntrada;
        List<Integer>              resultado      = new java.util.ArrayList<>();
        Connection                 con            = this.getConexion();

        // Consulta SQL para contar el número de bicicletas libres por estación
        // AGRUPAR ESTACION(id) <<-|-> CONTAR BICICLETA(id) CUANDO BICICLETA EN ESTACION(id) Y
        // BICICLETA EN VIAJE(id) Y NO HAY UN VIAJE SIN FIN
        String consulta =
                "SELECT e.id, COUNT(CASE WHEN vb.id IS NULL THEN b.id END) AS cuenta " +
                        "FROM estacion e " +
                        "LEFT JOIN bicicleta b ON b.estacion = e.id " +
                        "LEFT JOIN ( " +
                        "    SELECT v.bicicleta AS id " +
                        "    FROM viaje v " +
                        "    WHERE v.hora_fin IS NULL " +
                        ") vb ON vb.id = b.id " +
                        "GROUP BY e.id, e.ubicacion " +
                        "ORDER BY e.ubicacion";
        try {
            stmBicisLibres = con.prepareStatement(consulta);
            datosEntrada = stmBicisLibres.executeQuery();
            while (datosEntrada.next()) {
                resultado.add(datosEntrada.getInt("cuenta"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error en la consulta de bicicletas libres por estación: {}", e.getMessage());
        } finally {
            try {
                if (stmBicisLibres != null) {
                    stmBicisLibres.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error al cerrar cursores - {}", e.getMessage());
            }
        }
        return resultado;

    }

    /**
     * Método que reserva una bicicleta para un usuario específico.
     *
     * @param usuarioLogado      El usuario que está reservando la bicicleta.
     * @param bicicletaReservada La bicicleta que se va a reservar.
     * @return true si la reserva se realizó correctamente, false en caso contrario.
     */
    boolean reservarBicicleta(Usuario usuarioLogado, Bicicleta bicicletaReservada) {
        Connection con = this.getConexion();
        // Insert en la tabla viaje: usuario(id), bicicleta(id), hora_inicio, origen
        String consulta =
                "INSERT INTO viaje " +
                        "(usuario, bicicleta, hora_inicio, origen) " +
                        "VALUES (?, ?, ?, ?)";
        try (java.sql.PreparedStatement stmBicisLibres = con.prepareStatement(consulta)) {
            try {
                stmBicisLibres.setInt(1, usuarioLogado.idUsuario());
                stmBicisLibres.setInt(2, bicicletaReservada.Id());
                stmBicisLibres.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
                stmBicisLibres.setInt(4, bicicletaReservada.estacion().idEstacion());
                stmBicisLibres.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error al reservar la bicicleta: {}", e.getMessage());
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error al cerrar el cursor: {}", e.getMessage());
            return false;
        }
        return true;
    }
}
