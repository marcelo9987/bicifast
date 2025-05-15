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

    public TipoUsuario comprobarLogin(String email, String contrasenha)
    {
        Usuario usuario_obtenido =  fbd.validarUsuario(email, contrasenha);
        System.out.println("[DEBUG] Usuario obtenido: " + usuario_obtenido);
        return usuario_obtenido.tipoUsuario();
    }
}
