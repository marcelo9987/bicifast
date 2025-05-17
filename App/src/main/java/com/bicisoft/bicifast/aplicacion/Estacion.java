package com.bicisoft.bicifast.aplicacion;

import java.util.Objects;

/**
 * Clase que representa una estación.
 * Representa la tupla estación en la base de datos.
 * Ver documentación de la base de datos para más información.
 */
public final class Estacion {
    private final int    idEstacion;
    private final String ubicacion;
    private final int    aforo;

    /**
     * Constructor de la clase Estacion.
     * @param idEstacion id de la estación
     * @param ubicacion ubicación de la estación
     * @param aforo aforo de la estación
     */
    public Estacion(int idEstacion, String ubicacion, int aforo) {
        super();
        this.idEstacion = idEstacion;
        this.ubicacion = ubicacion;
        this.aforo = aforo;
    }

    /**
     * @return id de la estación
     */
    public int idEstacion() {
        return this.idEstacion;
    }

    /**
     * @return ubicación de la estación
     */
    public String ubicacion() {
        return this.ubicacion;
    }

    /**
     * @return cantidad de bicicletas que puede albergar la estación
     */
    public int aforo() {
        return this.aforo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var other = (Estacion) obj;
        return this.idEstacion == other.idEstacion &&
                Objects.equals(this.ubicacion, other.ubicacion) &&
                this.aforo == other.aforo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEstacion, ubicacion, aforo);
    }

    @Override
    public String toString() {
        return "Estacion[" +
                "idEstacion=" + idEstacion + ", " +
                "ubicacion=" + ubicacion + ", " +
                "aforo=" + aforo + ']';
    }

}
