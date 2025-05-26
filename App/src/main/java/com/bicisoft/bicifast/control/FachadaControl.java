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
    private final GestorUsuario     gestorUsuario;
    private final GestorBicicleta   gestorBicicleta;
    private final GestorEstacion    gestorEstacion;
    private final FachadaBaseDatos  fbd;

    /**
     * Constructor de la clase FachadaControl.
     */
    public FachadaControl() {
        super();
        this.fbd = new FachadaBaseDatos();
        this.gestorUsuario = new GestorUsuario(this.fbd);
        this.gestorBicicleta = new GestorBicicleta(this.fbd);
        this.gestorEstacion = new GestorEstacion(this.fbd);
    }


    /**
     *
     * @param email
     * @param contrasenha
     * @return
     */
    public Usuario comprobarLogin(String email, String contrasenha) {
        return this.gestorUsuario.comprobarLogin(email, contrasenha);
    }

    public List<Estacion> preguntaLasEstaciones() {
        return this.gestorEstacion.preguntaLasEstaciones();
    }

    public List<Bicicleta> obtenerBicisLibresPorEstacion(Estacion estacionUsada) {
        return this.gestorBicicleta.obtenerBicisLibresPorEstacion(estacionUsada);
    }

    public boolean usuarioTieneBiciEnUso(Usuario usuarioLogado) {
        return this.gestorUsuario.usuarioTieneBiciEnUso(usuarioLogado);
    }

    public boolean devolverBicicleta(Usuario usuarioLogado, Estacion estacionSeleccionada) {
        return this.gestorBicicleta.devolverBicicleta(usuarioLogado, estacionSeleccionada);
    }

    public List<Integer> preguntaLasBicicletasPorEstacion() {
        return this.gestorBicicleta.preguntaLasBicicletasPorEstacion();
    }

    public int reservarBicicleta(Usuario usuarioLogado, Estacion estacionSeleccionada) {
        return this.gestorBicicleta.reservarBicicleta(usuarioLogado, estacionSeleccionada);
    }
}
