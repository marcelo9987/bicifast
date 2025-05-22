package com.bicisoft.bicifast.basededatos;

import com.bicisoft.bicifast.aplicacion.*;
import com.bicisoft.bicifast.misc.Criptografia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase DAOUsuario
 * Esta clase se encarga de gestionar la base de datos de los usuarios.
 */
final class DAOUsuario extends AbstractDAO {
    //---- CONSTANTES ----
    private final static Boolean DEBUG = false;

    //---- LOGGER ----
    private final Logger logger = LoggerFactory.getLogger(DAOUsuario.class);

    DAOUsuario(Connection conexion) {
        super();
        this.setConexion(conexion);
    }


    /**
     * Método que valida un usuario en la base de datos.
     *
     * @param email       email del usuario a validar
     * @param contrasenha contraseña del usuario a validar
     * @return Usuario objeto Usuario con los datos del usuario validado
     */
    Usuario validarUsuario(String email, String contrasenha) {
        logger.info("email a validar: {}", email);
        Usuario usuarioResultante = null;

        String hashContrasenha = Criptografia.encriptar(contrasenha);
        if (DAOUsuario.DEBUG) {
            logger.debug("[DEBUG] Hash de la contrasenha: {}", hashContrasenha);
        }

        ResultSet  rsUsuario;
        Connection con = this.getConexion();
        // Consulta SQL para validar el usuario
        // SELECCIONA todo el usuario DESDE LAS TUPLAS DE usuario CUANDO SU email SEA IGUAL A ? (parámetro)
        try (PreparedStatement stmUsuario = con.prepareStatement
                (
                        "SELECT " +
                                "id" +
                                ",   nombre" +
                                "   , primer_apellido" +
                                "   , segundo_apellido" +
                                "   , dni" +
                                "   , email" +
                                "   , direccion" +
                                "   , fecha_nacimiento" +
                                "   , telefono" +
                                "   , contrasenha" +
                                "   , metodo_pago" +
                                "   , fecha_inicio_suscripcion" +
                                "   , fecha_fin_suscripcion" +
                                "   , tipo_usuario " +
                                "FROM usuario " +
                                "WHERE " +
                                "   email = ? ")) {
            try {
                //                                    +"AND "
                //                                    +"   contrasenha = ?"
                stmUsuario.setString(1, email);
//            stmUsuario.setString(2, hashContrasenha);
                rsUsuario = stmUsuario.executeQuery();

                if (rsUsuario.next()) {
                    String contrasinal = rsUsuario.getString("contrasenha");
                    if (Criptografia.verificar(contrasenha, contrasinal)) {
                        logger.debug("La contraseña coincide con la almacenada en la base de datos.");
//                    TipoUsuario tipo = TipoUsuario.valueOf(rsUsuario.getString("tipo_usuario"));
                        TipoUsuario tipo = switch (rsUsuario.getString("tipo_usuario")) {
                            case "NORMAL" -> TipoUsuario.NORMAL;
                            case "ADMIN" -> TipoUsuario.Admin;
                            case "MANT" -> TipoUsuario.Mant;
                            default -> TipoUsuario.NO_DEFINIDO;
                        };

                        if (TipoUsuario.NO_DEFINIDO == tipo) {
                            logger.error("El usuario no tiene un tipo definido.");
                            return null;
                        }


                        usuarioResultante = new Usuario(
                                rsUsuario.getInt(1),
                                rsUsuario.getString("nombre"),
                                rsUsuario.getString("primer_apellido"),
                                rsUsuario.getString("segundo_apellido"),
                                rsUsuario.getString("dni"),
                                rsUsuario.getString("email"),
                                rsUsuario.getString("direccion"),
                                rsUsuario.getDate("fecha_nacimiento"),
                                rsUsuario.getString("telefono"),
                                contrasinal,
                                MetodoPago.valueOf(rsUsuario.getString("metodo_pago")),
                                rsUsuario.getDate("fecha_inicio_suscripcion"),
                                rsUsuario.getDate("fecha_fin_suscripcion"),
                                tipo
                        );

//                        System.out.println("[DEBUG] Usuario: " + usuarioResultante);
                        return usuarioResultante;

                    }
                    else {
                        logger.error("La contraseña no coincide con la almacenada en la base de datos.");
                        return null;
                    }
                }
                else {
                    logger.error("No se ha encontrado el usuario con el email: {}", email);
                    return null;
                }
            } catch (Exception e) {
                _logarFalloValidacionConsulta(e);
            }
        } catch (SQLException e) {
            _logarNoHePodidoCerrarCursores();
        }

        return usuarioResultante;
    }

    private void _logarFalloValidacionConsulta(Exception e) {
        logger.warn("Algo ha fallado en la consulta para validar el usuario.  Más detalles: {}", e.getMessage());
    }

    private void _logarNoHePodidoCerrarCursores() {
        logger.error("Imposible cerrar cursores.");
    }

    /**
     * Método que comprueba si un usuario tiene una bicicleta en uso.
     *
     * @param usuarioLogado usuario a comprobar
     * @return true si el usuario tiene una bicicleta en uso, false en caso contrario
     */
    boolean usuarioTieneBiciEnUso(Usuario usuarioLogado) {
        ResultSet  rsUsuario;
        Connection con = this.getConexion();
        // Consulta SQL para validar el usuario
        String consulta = "SELECT count(1) " +
                "FROM usuario u " +
                "LEFT JOIN viaje v ON u.id = v.usuario " +
                "WHERE v.hora_fin IS NULL " +
                "AND u.id = ?";
        try (PreparedStatement stmUsuario = con.prepareStatement(consulta)) {
            try {
                stmUsuario.setInt(1, usuarioLogado.idUsuario());
                rsUsuario = stmUsuario.executeQuery();

                if (rsUsuario.next()) {
                    boolean tieneBici = rsUsuario.getInt(1) > 0;
                    if (tieneBici) {
                        _logarUsuarioBiciEnUso();
                    }
                    else {
                        logger.debug("El usuario no tiene una bici en uso.");
                    }
                    return tieneBici;
                }
                _quejarseNoExisteUsuario(usuarioLogado);
                System.exit(-1);

            } catch (Exception e) {
                _logarFalloValidacionConsulta(e);
            }
        } catch (SQLException e) {
            _logarNoHePodidoCerrarCursores();
        }
        return false;
    }

    private void _logarUsuarioBiciEnUso() {
        logger.debug("El usuario tiene una bici en uso.");
    }

    private void _quejarseNoExisteUsuario(Usuario usuarioLogado) {
        logger.error("No se ha encontrado el usuario con el id: {}", usuarioLogado.idUsuario());
    }

    /**
     * Método que obtiene la bicicleta en reserva de un usuario.
     *
     * @param usuarioLogado usuario a comprobar
     * @return bicicleta del usuario
     */
    Bicicleta obtenerBicicletaPorUsuario(Usuario usuarioLogado) {
        ResultSet  rsUsuario;
        Connection con = this.getConexion();
        String consulta = "SELECT " +
                "   b.id" +
                "   , b.estado" +
                "   , b.estacion" +
                "   , e.id as id_e" +
                "   , e.aforo" +
                "   , e.ubicacion " +
                "FROM estacion e LEFT JOIN bicicleta b ON e.id = b.estacion " +
                "WHERE" +
                "   b.id in (" +
                "        SELECT v.bicicleta" +
                "        FROM viaje v" +
                "        WHERE" +
                "            v.hora_fin IS NULL" +
                "            AND" +
                "            v.usuario = ? " +
                ")";
        try (PreparedStatement stmUsuario = con.prepareStatement(consulta)) {
            try {
                stmUsuario.setInt(1, usuarioLogado.idUsuario());
                rsUsuario = stmUsuario.executeQuery();

                if (rsUsuario.next()) {
                    _logarUsuarioBiciEnUso();
                    Bicicleta bici = new Bicicleta(
                            rsUsuario.getInt("id"),
                            new Estacion(
                                    rsUsuario.getInt("id_e"),
                                    rsUsuario.getString("ubicacion"),
                                    rsUsuario.getInt("aforo")
                            ),
                            EstadoBicicleta.valueOf(rsUsuario.getString("estado"))

                    );
                    logger.debug("La bicicleta es: {}", bici);
                    return bici;

                }
                else {
                    _quejarseNoExisteUsuario(usuarioLogado);
                    System.exit(-1);
                }
            } catch (Exception e) {
                _logarFalloValidacionConsulta(e);
            }
        } catch (SQLException e) {
            _logarNoHePodidoCerrarCursores();
        }
        return null;
    }
}
