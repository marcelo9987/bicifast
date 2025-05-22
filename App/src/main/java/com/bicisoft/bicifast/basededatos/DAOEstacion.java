package com.bicisoft.bicifast.basededatos;

import com.bicisoft.bicifast.aplicacion.Estacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

/**
 * Clase DAOEstacion
 * Esta clase se encarga de gestionar la base de datos de las estaciones.
 */
final class DAOEstacion extends AbstractDAO {
    private final Logger logger = LoggerFactory.getLogger(DAOEstacion.class);

    /**
     * @param conexion conexion a la base de datos
     */
    DAOEstacion(Connection conexion) {
        super();
        setConexion(conexion);
    }


    /**
     * Devuelve una lista con todas las estaciones de la base de datos
     *
     * @return lista de estaciones
     */
    List<Estacion> preguntaLasEstaciones() {
        java.sql.PreparedStatement stmEstaciones = null;
        java.sql.ResultSet         rsEstaciones;
        Connection                 con;
        List<Estacion>             estaciones    = new java.util.ArrayList<>();
        con = this.getConexion();
        // Una consulta SQL para obtener todas las estaciones
        // SELECCIONA id, ubicacion y aforo DE TUPLAS DE estacion ORDENADO POR ubicacion
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
                int    idEstacion = rsEstaciones.getInt("id");
                String ubicacion  = rsEstaciones.getString("ubicacion");
                int    aforo      = rsEstaciones.getInt("aforo");
                estaciones.add(new Estacion(idEstacion, ubicacion, aforo));
            }
        } catch (Exception e) {
            e.printStackTrace();
//            System.err.println("EXCEPCION_CONSULTA_ESTACIONES");
            this.logger.error("Error al consultar las estaciones: {}", e.getMessage());
        } finally {
            try {
                if (stmEstaciones != null) {
                    stmEstaciones.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.logger.error("No se ha podido cerrar la conexión para consultar las estaciones: {}", e.getMessage());
            }
        }
        return estaciones;
    }

    /**
     * Devuelve la ocupacion de una estacion
     *
     * @param id estacion a consultar
     * @return ocupacion de la estacion
     */
    int obtenerOcupacionEstacion(int id) {
        java.sql.PreparedStatement stmEstaciones = null;
        java.sql.ResultSet         rsEstaciones;
        Connection                 con;
        int                        ocupacion     = 0;
        con = this.getConexion();
        // Una consulta SQL para obtener la ocupacion de una estacion
        // SELECCIONA la ocupacion DE TUPLAS DE estacion
        // SEA LA ocupacion EL CONTADOR DE BICICLETAS QUE NO TIENEN VIAJE
        // ORDENADO POR ubicacion Y AGRUPADO POR ESTACION
        // el select de la subconsulta devuelve 1 si no existe un viaje y 0 si existe
        String consulta =
                "SELECT " +
                        "count(IF(NOT EXISTS (SELECT 1" +
                        "                       FROM viaje v" +
                        "                       WHERE v.bicicleta = b.id" +
                        "                       AND v.hora_fin IS NULL), 1, 0)) as ocupacion " +
                        "FROM " +
                        "   estacion e LEFT JOIN " +
                        " bicicleta b ON e.id = b.estacion " +
                        " WHERE " +
                        "   e.id = ? " +
                        " GROUP BY " +
                        "   e.id";
        try {
            stmEstaciones = con.prepareStatement(consulta);
            stmEstaciones.setInt(1, id);
            rsEstaciones = stmEstaciones.executeQuery();
            if (rsEstaciones.next()) {
                ocupacion = rsEstaciones.getInt("ocupacion");
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.error("Error al consultar la ocupación de la estación: {}", e.getMessage());
        } finally {
            try {
                if (stmEstaciones != null) {
                    stmEstaciones.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.logger.error("No se ha podido cerrar la conexión para consultar la ocupación de la estación: {}", e.getMessage());
            }
        }
        return ocupacion;
    }
}
