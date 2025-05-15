package aplicacion;

import basededatos.FachadaBaseDatos;
import control.GestorUsuario;
import gui.FachadaGUI;

public class FachadaAplicacion {

    private final FachadaGUI    fgui;
    private final FachadaBaseDatos fbd;
    private final GestorUsuario controladorUsuario;
    private TipoUsuario nivelAcceso;

    public TipoUsuario nivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(TipoUsuario nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }



    public FachadaAplicacion()
    {
        // -- FAÇADAS --
        fgui = new FachadaGUI(this);
        fbd = new FachadaBaseDatos(this);

        // -- CONTROLADORES --
        controladorUsuario = new GestorUsuario(this.fbd);
    }

    public static void main(String[] args)
    {
        FachadaAplicacion fa = new FachadaAplicacion();
//        fbd._funciona();

        fa.lanzarAplicacion();

    }

    public void lanzarAplicacion() {
        System.out.println("Iniciando la aplicación...");
        fgui.iniciarVista();
    }

    public TipoUsuario comprobarLogin(String email, String contrasenha)
    {
        return controladorUsuario.comprobarLogin(email, contrasenha);
    }
}
