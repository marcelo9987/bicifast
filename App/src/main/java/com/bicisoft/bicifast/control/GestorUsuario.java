package com.bicisoft.bicifast.control;

import com.bicisoft.bicifast.aplicacion.Usuario;
import com.bicisoft.bicifast.basededatos.FachadaBaseDatos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Gestiona los usuarios.
 */
final class GestorUsuario {
    //---- LOGGER ----
    private final static Logger logger = LoggerFactory.getLogger(GestorUsuario.class);

    //--- FACHADAS ---
    private final FachadaBaseDatos fbd;


    /**
     * Constructor de la clase GestorUsuario.
     *
     * @param fbd Fachada de la base de datos
     */
    GestorUsuario(FachadaBaseDatos fbd) {
        super();
        this.fbd = fbd;
    }

    /**
     * Comprueba si el usuario existe en la base de datos y si la contraseña es correcta.
     *
     * @param email       email del usuario a comprobar. Este email se convierte a minúsculas y se eliminan los espacios en blanco antes de la comprobación.
     * @param contrasenha contraseña del usuario
     * @return Usuario si el usuario existe y la contraseña es correcta, null en caso contrario
     */
    Usuario comprobarLogin(String email, String contrasenha) {
        logger.debug("Comprobando login para el usuario con email sin modificar: {}", email);
        String emailSinMayusculasNiEspacios = email.toLowerCase().trim();
        logger.debug("Email sin mayúsculas ni espacios: {}", emailSinMayusculasNiEspacios);
        Usuario usuarioObtenido = this.fbd.validarUsuario(emailSinMayusculasNiEspacios, contrasenha);
        logger.debug("Usuario obtenido: {}", usuarioObtenido);
        return usuarioObtenido;
    }

    /**
     * Comprueba si el usuario logado tiene una bicicleta en uso
     *
     * @param usuarioLogado Usuario logado
     * @return true si el usuario tiene una bicicleta en uso, false en caso contrario
     */
    boolean usuarioTieneBiciEnUso(Usuario usuarioLogado) {
        if (usuarioLogado != null) {
            return this.fbd.usuarioTieneBiciEnUso(usuarioLogado);
        }
        else {
            logger.debug("Usuario no logado");
            return false;
        }
    }
}
