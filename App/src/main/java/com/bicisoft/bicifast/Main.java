package com.bicisoft.bicifast;

import com.bicisoft.bicifast.aplicacion.FachadaAplicacion;


/**
 * Clase Main
 */
public final class Main {
    /**
     * Método principal de la aplicación.
     *
     * @param args Sin uso
     */
    public static void main(String[] args) {
        FachadaAplicacion fa = new FachadaAplicacion();

        fa.lanzarAplicacion();
    }
}
