package com.bicisoft.bicifast.control;

import com.bicisoft.bicifast.aplicacion.Usuario;
import com.bicisoft.bicifast.basededatos.FachadaBaseDatos;

/**
 *  Gestiona los usuarios.
 */
public final class GestorUsuario {

    private final FachadaBaseDatos fbd;


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
        System.out.println("[DEBUG] Usuario obtenido: " + usuario_obtenido);
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
            System.out.println("[DEBUG] Usuario no logado");
            return false;
        }
    }
}
