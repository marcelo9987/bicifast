package com.bicisoft.bicifast.control;

import com.bicisoft.bicifast.aplicacion.Usuario;
import com.bicisoft.bicifast.basededatos.FachadaBaseDatos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Gestiona los usuarios.
 */
public final class GestorUsuario {

    //--- FAÇADAS ---
    private final FachadaBaseDatos fbd;

    //---- LOGGER ----
    private static final Logger logger = LoggerFactory.getLogger(GestorUsuario.class);


    /**
     * Constructor de la clase GestorUsuario.
     * @param fbd  Fachada de la base de datos
     */
    public GestorUsuario(FachadaBaseDatos fbd) {
        super();
        this.fbd = fbd;
    }

    /**
     * Comprueba si el usuario existe en la base de datos y si la contraseña es correcta.
     * @param email email del usuario
     * @param contrasenha contraseña del usuario
     * @return Usuario si el usuario existe y la contraseña es correcta, null en caso contrario
     */
    public Usuario comprobarLogin(String email, String contrasenha)
    {
        Usuario usuario_obtenido =  fbd.validarUsuario(email, contrasenha);
        logger.debug("Usuario obtenido: {}", usuario_obtenido);
        return usuario_obtenido;
    }

    /**
     * Comprueba si el usuario logado tiene una bicicleta en uso
     * @param usuarioLogado Usuario logado
     * @return true si el usuario tiene una bicicleta en uso, false en caso contrario
     */
    public boolean usuarioTieneBiciEnUso(Usuario usuarioLogado)
    {
        if (usuarioLogado != null) {
            return fbd.usuarioTieneBiciEnUso(usuarioLogado);
        } else {
            logger.debug("Usuario no logado");
            return false;
        }
    }
}
