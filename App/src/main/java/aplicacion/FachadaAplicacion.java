package aplicacion;

import basededatos.FachadaBaseDatos;
import control.GestorBicicleta;
import control.GestorEstacion;
import control.GestorUsuario;
import gui.FachadaGUI;

import java.util.List;

public class FachadaAplicacion {

    private final FachadaGUI    fgui;
    private final FachadaBaseDatos fbd;
    private final GestorUsuario  controladorUsuario;
    private final GestorEstacion  controladorEstacion;
    private final GestorBicicleta controladorBicicleta;
    private       Usuario     usuarioLogado;

    public TipoUsuario nivelAcceso() {
        return usuarioLogado.tipoUsuario();
    }

    public void estableceUsuarioLogado(Usuario nivelAcceso) {
        this.usuarioLogado = nivelAcceso;
    }



    public FachadaAplicacion()
    {
        // -- FAÇADAS --
        fgui = new FachadaGUI(this);
        fbd = new FachadaBaseDatos(this);

        // -- CONTROLADORES --
        controladorUsuario = new GestorUsuario(this.fbd);
        controladorEstacion = new GestorEstacion(this.fbd);
        controladorBicicleta = new GestorBicicleta(this.fbd);
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

    public Usuario comprobarLogin(String email, String contrasenha)
    {
        return controladorUsuario.comprobarLogin(email, contrasenha);
    }

    public List<Estacion> preguntaLasEstaciones()
    {
        return controladorEstacion.preguntaLasEstaciones();
    }

    public List<Bicicleta> obtenerBicisLibresPorEstacion(Estacion estacionUsada)
    {
        return controladorBicicleta.obtenerBicisLibresPorEstacion(estacionUsada);
    }

    public boolean usuarioTieneBici()
    {
        return controladorUsuario.usuarioTieneBiciEnUso(usuarioLogado);
    }

    public void devolverBicicleta(Estacion estacionSeleccionada)
    {
        controladorBicicleta.devolverBicicleta(usuarioLogado, estacionSeleccionada);
    }

    public List<Integer> preguntaLasBicicletasPorEstacion()
    {
        return controladorBicicleta.preguntaLasBicicletasPorEstacion();
    }
}
