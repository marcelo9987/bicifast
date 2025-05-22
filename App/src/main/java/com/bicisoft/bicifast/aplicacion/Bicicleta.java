package com.bicisoft.bicifast.aplicacion;

/**
 * Clase que representa una bicicleta.
 * Representa la tupla bicicleta en la base de datos.\n
 * Ver documentación de la base de datos para más información.
 */
public final class Bicicleta {
    private final int             id;
    private final EstadoBicicleta estado;
    private final Estacion        estacion;

    /**
     * Constructor de la clase Bicicleta.
     *
     * @param id       Identificador de la bicicleta.
     * @param estacion Estación a la que pertenece la bicicleta.
     * @param estado   Estado de la bicicleta.
     */
    public Bicicleta(int id, Estacion estacion, EstadoBicicleta estado) {
        super();
        this.id = id;
        this.estacion = estacion;
        this.estado = estado;
    }

    /**
     * Getter del identificador de la bicicleta.
     *
     * @return Identificador de la bicicleta.
     */
    public int Id() {
        return this.id;
    }

    /**
     * Getter de la estación en la que se encuentra la bicicleta.
     *
     * @return Estacion en la que se encuentra la bicicleta.
     */
    public Estacion estacion() {
        return this.estacion;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Bicicleta bicicleta)) {
            return false;
        }

        return this.id == bicicleta.id;
    }
}


