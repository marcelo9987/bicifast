package com.bicisoft.bicifast.control;

import com.bicisoft.bicifast.aplicacion.Bicicleta;
import com.bicisoft.bicifast.aplicacion.Estacion;
import com.bicisoft.bicifast.aplicacion.Usuario;
import com.bicisoft.bicifast.basededatos.FachadaBaseDatos;

import java.util.List;

/**
 * Fachada de control que actúa como intermediaria entre la GUI y los gestores de la aplicación.
 */
public final class FachadaControl {
    // ---- GESTORES ----
    private final GestorUsuario   gestorUsuario;
    private final GestorBicicleta gestorBicicleta;
    private final GestorEstacion  gestorEstacion;

    /**
     * Constructor de la clase FachadaControl.
     */
    public FachadaControl() {
        super();
        FachadaBaseDatos fbd = new FachadaBaseDatos();
        this.gestorUsuario = new GestorUsuario(fbd);
        this.gestorBicicleta = new GestorBicicleta(fbd);
        this.gestorEstacion = new GestorEstacion(fbd);
    }


    /**
     * @param email       Email del usuario al que se quiere comprobar el login.
     * @param contrasenha Contraseña del usuario al que se quiere comprobar el login.
     * @return Usuario si el usuario existe y la contraseña es correcta, null en caso contrario.
     */
    public Usuario comprobarLogin(String email, String contrasenha) {
        return this.gestorUsuario.comprobarLogin(email, contrasenha);
    }

    /**
     * Obtiene una lista de estaciones disponibles en el sistema.
     *
     * @return Lista de estaciones disponibles en el sistema.
     */
    public List<Estacion> preguntaLasEstaciones() {
        return this.gestorEstacion.preguntaLasEstaciones();
    }

    /**
     * Obtiene las bicicletas libres en una estación específica.
     *
     * @param estacionUsada Estación para la que se quieren obtener las bicicletas libres.
     * @return Lista de bicicletas libres en la estación especificada.
     */
    public List<Bicicleta> obtenerBicisLibresPorEstacion(Estacion estacionUsada) {
        return this.gestorBicicleta.obtenerBicisLibresPorEstacion(estacionUsada);
    }

    /**
     * @param usuarioLogado Usuario que se quiere comprobar si tiene una bicicleta en uso.
     * @return true si el usuario tiene una bicicleta en uso, false en caso contrario.
     */
    public boolean usuarioTieneBiciEnUso(Usuario usuarioLogado) {
        return this.gestorUsuario.usuarioTieneBiciEnUso(usuarioLogado);
    }

    /**
     * @param usuarioLogado        Usuario que quiere obtener su bicicleta en uso.
     * @param estacionSeleccionada Estación en la que se quiere devolver la bicicleta.
     * @return true si se ha devuelto la bicicleta correctamente, false en caso contrario.
     */
    public boolean devolverBicicleta(Usuario usuarioLogado, Estacion estacionSeleccionada) {
        return this.gestorBicicleta.devolverBicicleta(usuarioLogado, estacionSeleccionada);
    }

    /**
     * Obtiene una lista de bicicletas libres por estación.
     *
     * @return Lista de suma de bicicletas libres por estación.
     */
    public List<Integer> preguntaLasBicicletasPorEstacion() {
        return this.gestorBicicleta.preguntaLasBicicletasPorEstacion();
    }

    /**
     * Método para reservar una bicicleta en una estación específica.
     *
     * @param usuarioLogado        Usuario que quiere reservar una bicicleta.
     * @param estacionSeleccionada Estación en la que se quiere reservar la bicicleta.
     * @return ID de la bicicleta reservada, o -1 si no se pudo reservar.
     */
    public int reservarBicicleta(Usuario usuarioLogado, Estacion estacionSeleccionada) {
        return this.gestorBicicleta.reservarBicicleta(usuarioLogado, estacionSeleccionada);
    }
}
