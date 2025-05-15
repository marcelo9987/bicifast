package basededatos;

import aplicacion.FachadaAplicacion;
import aplicacion.MetodoPago;
import aplicacion.TipoUsuario;
import aplicacion.Usuario;
import misc.Criptografia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUsuario extends AbstractDAO {
    public DAOUsuario(Connection conexion, FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }


    public Usuario validarUsuario(String email, String contrasenha) {
        System.out.println("email a validar: " + email);
        Usuario usuarioResultante = null;

        String hashContrasenha = Criptografia.encriptar(contrasenha);
        System.out.println("[DEBUG] Hash de la contrasenha: " + hashContrasenha);

        PreparedStatement stmUsuario = null;
        ResultSet         rsUsuario;

        Connection con = this.getConexion();
        try {
            stmUsuario= con.prepareStatement
                    (
                            "SELECT " +
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
                    );
            stmUsuario.setString(1, email);
//            stmUsuario.setString(2, hashContrasenha);
            rsUsuario = stmUsuario.executeQuery();

            if(rsUsuario.next())
            {
                String contrasinal = rsUsuario.getString("contrasenha");
                if(Criptografia.verificar(contrasenha, contrasinal))
                {
                    System.out.println("[DEBUG] La contraseña coincide con la almacenada en la base de datos.");
//                    TipoUsuario tipo = TipoUsuario.valueOf(rsUsuario.getString("tipo_usuario"));
                    TipoUsuario tipo ;
                    switch (rsUsuario.getString("tipo_usuario"))
                    {
                        case "NORMAL":
                            tipo = TipoUsuario.NORMAL;
                            break;
                        case "ADMIN":
                            tipo = TipoUsuario.Admin;
                            break;
                        case "MANT":
                            tipo = TipoUsuario.Mant;
                            break;
                        default:
                            tipo = TipoUsuario.NO_DEFINIDO;
                    }

                    if(tipo == TipoUsuario.NO_DEFINIDO)
                    {
                        System.err.println("[ERROR] El usuario no tiene un tipo definido.");
                        return null;
                    }


                        usuarioResultante = new Usuario(
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
                else
                {
                    System.err.println("[ERROR] La contraseña no coincide con la almacenada en la base de datos.");
                   return null;
                }
            }
            else
            {
                System.err.println("[ERROR] No se ha encontrado el usuario con el email: " + email);
                return null;
            }
        }
        catch (Exception e)
        {
            System.out.println("Algo ha fallado en la consulta para validar el usuario.  Más detalles: " + e.getMessage());
        } finally {
            try {
                stmUsuario.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return usuarioResultante;
    }
}
