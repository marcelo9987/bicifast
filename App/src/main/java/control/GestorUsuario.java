package control;

import aplicacion.TipoUsuario;
import aplicacion.Usuario;
import basededatos.FachadaBaseDatos;

public class GestorUsuario {

    FachadaBaseDatos fbd;

    public GestorUsuario(FachadaBaseDatos fbd)
    {
        this.fbd = fbd;
    }

    public Usuario comprobarLogin(String email, String contrasenha)
    {
        Usuario usuario_obtenido =  fbd.validarUsuario(email, contrasenha);
        System.out.println("[DEBUG] Usuario obtenido: " + usuario_obtenido);
        return usuario_obtenido;
    }

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
