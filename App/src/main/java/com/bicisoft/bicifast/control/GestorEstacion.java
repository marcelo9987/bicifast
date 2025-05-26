package com.bicisoft.bicifast.control;

import com.bicisoft.bicifast.aplicacion.Estacion;
import com.bicisoft.bicifast.basededatos.FachadaBaseDatos;

import java.util.List;

/**
 * Gestiona las estaciones.
 */
final class GestorEstacion {
    //---- FACHADAS -----
    private final FachadaBaseDatos fbd;

    /**
     * Constructor de la clase GestorEstacion.
     *
     * @param fbd Fachada de la base de datos
     */
    GestorEstacion(FachadaBaseDatos fbd) {
        super();
        this.fbd = fbd;
    }

    /**
     * Pregunta las estaciones disponibles en la base de datos.
     *
     * @return Lista de estaciones disponibles
     */
    List<Estacion> preguntaLasEstaciones() {
        return this.fbd.preguntaLasEstaciones();
    }
}
