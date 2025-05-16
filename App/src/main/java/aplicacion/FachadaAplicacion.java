package aplicacion;

import basededatos.FachadaBaseDatos;
import control.GestorBicicleta;
import control.GestorEstacion;
import control.GestorUsuario;
import gui.FachadaGUI;
import misc.Internacionalizacion;

import java.util.List;
import java.util.ResourceBundle;

public class FachadaAplicacion {

    private final FachadaGUI    fgui;
    private final FachadaBaseDatos fbd;
    private final GestorUsuario  controladorUsuario;
    private final GestorEstacion  controladorEstacion;
    private final GestorBicicleta controladorBicicleta;
    private       Usuario        usuarioLogado;
    private Internacionalizacion itz;

    public TipoUsuario nivelAcceso() {
        return usuarioLogado.tipoUsuario();
    }

    public void estableceUsuarioLogado(Usuario usuarioNuevo) {
        this.usuarioLogado = usuarioNuevo;
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

        // -- Clases misceláneas y utilidades --
        itz = Internacionalizacion.getInstance();

    }

    public static void main(String[] args)
    {
        FachadaAplicacion fa = new FachadaAplicacion();
//        fbd._funciona();

        fa.lanzarAplicacion();

    }

    private void lanzarAplicacion() {
        System.out.println("Iniciando la aplicación...");
        fgui.iniciarVista();
    }

    private void _reLanzarInterfaz() {
        fgui.lanzarInterfazSinAutenticacion();
    }

    public void cambiarIdioma(EnumIdioma idioma) {
        itz.cambiarIdioma(idioma);
        this._reLanzarInterfaz();

    }

    public ResourceBundle pedirBundle() {
        if(itz == null) {
            System.out.println("[DEBUG] Acabas de solicitar un bundlde que no existe, lo hago por ti, por favor, revisa el código");
            itz = Internacionalizacion.getInstance();
        }
        return itz.getBundle();
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

    public int reservarBicicleta(Estacion estacionSeleccionada)
    {
        return controladorBicicleta.reservarBicicleta(usuarioLogado, estacionSeleccionada);
    }
}
