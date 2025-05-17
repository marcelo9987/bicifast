package com.bicisoft.bicifast.control;

import com.bicisoft.bicifast.aplicacion.Estacion;
import com.bicisoft.bicifast.basededatos.FachadaBaseDatos;

import java.util.List;

/**
 * Gestiona las estaciones.
 */
public final class GestorEstacion
{
    private final FachadaBaseDatos fbd;

    /**
     * Constructor de la clase GestorEstacion.
     * @param fbd Fachada de la base de datos
     */
    public GestorEstacion(FachadaBaseDatos fbd) {
        super();
        this.fbd = fbd;
    }

    /**
     * Pregunta las estaciones disponibles en la base de datos.
     * @return Lista de estaciones disponibles
     */
    public List<Estacion> preguntaLasEstaciones()
    {
        return this.fbd.preguntaLasEstaciones();
    }
}
