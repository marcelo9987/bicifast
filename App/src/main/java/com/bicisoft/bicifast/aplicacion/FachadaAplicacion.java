package com.bicisoft.bicifast.aplicacion;

import com.bicisoft.bicifast.control.FachadaControl;
import com.bicisoft.bicifast.gui.FachadaGUI;
import com.bicisoft.bicifast.misc.Internacionalizacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ResourceBundle;

/**
 * Fachada de la aplicación. Esta clase actúa como intermediaria entre la GUI y
 * los controladores de la aplicación. Proporciona métodos para interactuar con
 * los controladores y gestionar el estado de la aplicación.
 */
public class FachadaAplicacion {
    // -- LOGGER --
    private final static Logger logger = LoggerFactory.getLogger(FachadaAplicacion.class);

    // -- FACHADAS --
    private final FachadaGUI     fgui;
    private final FachadaControl fc;

    // -- ATRIBUTOS --
    private Usuario              usuarioLogado;
    private Internacionalizacion itz;

    /**
     * Constructor de la clase FachadaAplicacion.
     */
    public FachadaAplicacion() {
        super();

        // -- FACHADAS --
        this.fgui = new FachadaGUI(this);
        this.fc = new FachadaControl();

        // -- Clases misceláneas y utilidades --
        this.itz = Internacionalizacion.getInstance();

    }

    /**
     * Devuelve el nivel de acceso del usuario autenticado.
     *
     * @return TipoUsuario del usuario autenticado.
     */
    public TipoUsuario nivelAcceso() {
        return this.usuarioLogado.tipoUsuario();
    }

    /**
     * Establece el usuario autenticado en la aplicación.
     *
     * @param usuarioNuevo Usuario que se va a establecer como autenticado.
     */
    public void estableceUsuarioLogado(Usuario usuarioNuevo) {
        this.usuarioLogado = usuarioNuevo;
    }

    /**
     * Inicia la interfaz gráfica de la aplicación.
     */
    public void lanzarAplicacion() {
        logger.info("Iniciando la aplicación...");
        this.fgui.iniciarVista();
    }

    /**
     * Cambia el idioma de la aplicación.
     *
     * @param idioma Idioma al que se desea cambiar.
     */
    public void cambiarIdioma(EnumIdioma idioma) {
        this.itz.cambiarIdioma(idioma);
        this._reLanzarInterfaz();

    }

    /**
     * Relanza la interfaz gráfica de la aplicación.
     */
    private void _reLanzarInterfaz() {
        this.fgui.lanzarInterfazSinAutenticacion();
    }

    /**
     * Solicita a la clase de internacionalización el bundle de recursos
     *
     * @return Un ResourceBundle nuevo si no existe, o el existente.
     */
    public ResourceBundle pedirBundle() {
        if (this.itz == null) {
            logger.warn("Acabas de solicitar un bundle que no existe, lo hago por ti, por favor, revisa el código");
            this.itz = Internacionalizacion.getInstance();
        }
        return this.itz.getBundle();
    }

    /**
     * Comprueba si el usuario está autenticado.
     *
     * @param email       Email del usuario.
     * @param contrasenha Contraseña del usuario.
     * @return Usuario si el usuario está autenticado, null en caso contrario.
     */
    public Usuario comprobarLogin(String email, String contrasenha) {
        return this.fc.comprobarLogin(email, contrasenha);
    }

    /**
     * Obtiene la lista de estaciones disponibles.
     *
     * @return Lista de estaciones.
     */
    public List<Estacion> preguntaLasEstaciones() {
        return this.fc.preguntaLasEstaciones();
    }

    /**
     * Obtiene la lista de bicicletas disponibles en una estación.
     *
     * @param estacionUsada Estación de la que se quieren obtener las bicicletas.
     * @return Lista de bicicletas disponibles en la estación.
     */
    public List<Bicicleta> obtenerBicisLibresPorEstacion(Estacion estacionUsada) {
        return this.fc.obtenerBicisLibresPorEstacion(estacionUsada);
    }

    /**
     * Comprueba si el usuario tiene una bicicleta en uso.
     *
     * @return true si el usuario tiene una bicicleta en uso, false en caso contrario.
     */
    public boolean usuarioTieneBici() {
        return this.fc.usuarioTieneBiciEnUso(this.usuarioLogado);
    }

    /**
     * Devuelve una bicicleta a la estación seleccionada.
     *
     * @param estacionSeleccionada Estación a la que se quiere devolver la bicicleta.
     * @return true si la devolución se ha realizado correctamente, false en caso contrario.
     */
    public boolean devolverBicicleta(Estacion estacionSeleccionada) {
        return this.fc.devolverBicicleta(this.usuarioLogado, estacionSeleccionada);
    }

    /**
     * Cuenta el número de bicicletas (disponibles) por estación.
     *
     * @return Lista de enteros que representan el número de bicicletas por estación.
     */
    public List<Integer> preguntaLasBicicletasPorEstacion() {
        return this.fc.preguntaLasBicicletasPorEstacion();
    }

    /**
     * Reserva una bicicleta en la estación seleccionada.
     *
     * @param estacionSeleccionada Estación en la que se quiere reservar la bicicleta.
     * @return ID de la bicicleta reservada, o -1 si no se pudo reservar.
     */
    public int reservarBicicleta(Estacion estacionSeleccionada) {
        return this.fc.reservarBicicleta(this.usuarioLogado, estacionSeleccionada);
    }

    /**
     * Obtiene el usuario asociado a la sesión actual.
     *
     * @return Usuario autenticado.
     */
    public Usuario usuario() {
        return this.usuarioLogado;
    }

}
