package basededatos;

import aplicacion.*;
import misc.Criptografia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class DAOUsuario extends AbstractDAO {
    public DAOUsuario(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }


    public Usuario validarUsuario(String email, String contrasenha) {
        System.out.println("email a validar: " + email);
        Usuario usuarioResultante = null;

        String hashContrasenha = Criptografia.encriptar(contrasenha);
        System.out.println("[DEBUG] Hash de la contrasenha: " + hashContrasenha);

        ResultSet  rsUsuario;
        Connection con = this.getConexion();
        try (PreparedStatement stmUsuario = con.prepareStatement
                (
                        "SELECT " +
                                "id" +
                                "   nombre" +
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
                                "   email = ? "
//                                    +"AND "
//                                    +"   contrasenha = ?"
                )) {
            try {
                //                                    +"AND "
                //                                    +"   contrasenha = ?"
                stmUsuario.setString(1, email);
//            stmUsuario.setString(2, hashContrasenha);
                rsUsuario = stmUsuario.executeQuery();

                if (rsUsuario.next()) {
                    String contrasinal = rsUsuario.getString("contrasenha");
                    if (Criptografia.verificar(contrasenha, contrasinal)) {
                        System.out.println("[DEBUG] La contraseña coincide con la almacenada en la base de datos.");
//                    TipoUsuario tipo = TipoUsuario.valueOf(rsUsuario.getString("tipo_usuario"));
                        TipoUsuario tipo = switch (rsUsuario.getString("tipo_usuario")) {
                            case "NORMAL" -> TipoUsuario.NORMAL;
                            case "ADMIN" -> TipoUsuario.Admin;
                            case "MANT" -> TipoUsuario.Mant;
                            default -> TipoUsuario.NO_DEFINIDO;
                        };

                        if (tipo == TipoUsuario.NO_DEFINIDO) {
                            System.err.println("[ERROR] El usuario no tiene un tipo definido.");
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
                        System.err.println("[ERROR] La contraseña no coincide con la almacenada en la base de datos.");
                        return null;
                    }
                }
                else {
                    System.err.println("[ERROR] No se ha encontrado el usuario con el email: " + email);
                    return null;
                }
            } catch (Exception e) {
                System.out.println("Algo ha fallado en la consulta para validar el usuario.  Más detalles: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Imposible cerrar cursores");
        }

        return usuarioResultante;
    }

    public boolean usuarioTieneBiciEnUso(Usuario usuarioLogado)
    {

        //SELECT count(1)
        //    FROM usuario u
        //             LEFT JOIN viaje v
        //                       ON u.id = v.usuario
        //    WHERE v.hora_fin IS NULL
        //      AND u.id = ?
        ResultSet  rsUsuario;
        Connection con = this.getConexion();
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
                        System.out.println("[DEBUG] El usuario tiene una bici en uso.");
                    }
                    else {
                        System.out.println("[DEBUG] El usuario no tiene una bici en uso.");
                    }
                    return tieneBici;
                }
                System.err.println("[ERROR] No se ha encontrado el usuario con el id: " + usuarioLogado.idUsuario());
                System.exit(-1);

            } catch (Exception e) {
                System.out.println("Algo ha fallado en la consulta para validar el usuario.  Más detalles: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Imposible cerrar cursores");
        }
        return false;
    }

    public Bicicleta obtenerBicicletaPorUsuario(Usuario usuarioLogado) {

        //SELECT
        //    b.id
        //    , b.estado
        //    , b.estacion
        //    , e.id
        //    , e.aforo
        //    , e.ubicacion
        //FROM estacion e LEFT JOIN bicicleta b ON e.id = b.estacion
        //WHERE
        //    b.id in (
        //        SELECT v.bicicleta
        //        FROM viaje v
        //        WHERE
        //            v.hora_fin IS NULL
        //            AND
        //            v.usuario = ?
        //        )

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
                    System.out.println("[DEBUG] El usuario tiene una bici en uso.");
                    Bicicleta bici = new Bicicleta(
                            rsUsuario.getInt("id"),
                            new Estacion(
                                    rsUsuario.getInt("id_e"),
                                    rsUsuario.getString("ubicacion"),
                                    rsUsuario.getInt("aforo")
                            ),
                            EstadoBicicleta.valueOf(rsUsuario.getString("estado"))

                    );
                    System.out.println("[DEBUG] La bicicleta es: " + bici);
                    return bici;

                }
                else {
                    System.err.println("[ERROR] No se ha encontrado el usuario con el id: " + usuarioLogado.idUsuario());
                    System.exit(-1);
                }
            } catch (Exception e) {
                System.out.println("Algo ha fallado en la consulta para validar el usuario.  Más detalles: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Imposible cerrar cursores");
        }
        return null;
    }
}
