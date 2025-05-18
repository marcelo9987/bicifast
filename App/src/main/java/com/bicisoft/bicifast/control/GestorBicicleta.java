package com.bicisoft.bicifast.control;

import com.bicisoft.bicifast.aplicacion.Bicicleta;
import com.bicisoft.bicifast.aplicacion.Estacion;
import com.bicisoft.bicifast.aplicacion.Usuario;
import com.bicisoft.bicifast.basededatos.FachadaBaseDatos;

import java.util.List;

/**
 * Clase de control para gestionar las bicicletas (Business Logic)
 */
public final class GestorBicicleta {
    private final FachadaBaseDatos fbd;

    /**
     * Constructor de la clase GestorBicicleta.
     * @param fbd Fachada de la base de datos
     */
    public GestorBicicleta(FachadaBaseDatos fbd) {
        super();
        this.fbd = fbd;
    }


    /**
     * Método para devolver una bicicleta a una estación.
     *
     * @param usuarioLogado        Usuario que está devolviendo la bicicleta
     * @param estacionSeleccionada Estación a la que se pretende devolver la bicicleta
     * @return true si se ha devuelto correctamente, false en caso contrario
     */
    public boolean devolverBicicleta(Usuario usuarioLogado, Estacion estacionSeleccionada) {
        Bicicleta bicicleta = this.fbd.obtenerBicicletaPorUsuario(usuarioLogado);
        if (bicicleta != null) {
            // Si la estación tiene el aforo completo, NO se puede devolver la bicicleta
            int aforo = this.fbd.obtenerOcupacionEstacion(estacionSeleccionada);
            if (aforo >= estacionSeleccionada.aforo()) {
                System.out.println("[DEBUG] La estación está llena. No se puede devolver la bicicleta.");
                return false;
            }
            this.fbd.estacionarBicicleta(bicicleta, estacionSeleccionada);
            return this.fbd.devolverBicicleta(usuarioLogado, bicicleta, estacionSeleccionada);

        }
        System.out.println("[DEBUG] No se encontró la bicicleta asociada al usuario.");
        return false;
    }

    /**
     * Obtiene el número de bicicletas por estación.
     *
     * @return Lista de enteros donde cada entero representa el número de bicicletas en una estación
     */
    public List<Integer> preguntaLasBicicletasPorEstacion() {
        return this.fbd.preguntaLasBicicletasPorEstacion();
    }

    /**
     * Método para reservar una bicicleta en una estación.
     *
     * @param usuarioLogado        Usuario que está reservando la bicicleta
     * @param estacionSeleccionada Estación donde se desea reservar la bicicleta
     * @return ID de la bicicleta reservada, o -1 si no se pudo reservar
     */
    public int reservarBicicleta(Usuario usuarioLogado, Estacion estacionSeleccionada) {
        List<Bicicleta> bicicletasLibres = obtenerBicisLibresPorEstacion(estacionSeleccionada);

        if (bicicletasLibres.isEmpty()) {
            System.out.println("[DEBUG] No hay bicicletas libres en la estación seleccionada.");
            return -1; // Indica que no hay bicicletas disponibles
        }
        Bicicleta bicicletaReservada = bicicletasLibres.getFirst(); // Reservar la primera bicicleta libre
        if (!this.fbd.reservarBicicleta(usuarioLogado, bicicletaReservada)) {
            System.out.println("[DEBUG] No se pudo reservar la bicicleta.");
            return -1; // Indica que no se pudo reservar la bicicleta
        }
//            System.err.println("[DEBUG] ¿Como ha llegado aquí? <.>_<.> | Fallo en la BBDD o SQL");
        return bicicletaReservada.Id(); // Retorna el ID de la bicicleta reservada

    }

    /**
     * Método para obtener una lista de bicicletas libres en una estación.
     *
     * @param estacionUsada Estación de la que se desea obtener las bicicletas libres
     * @return Lista de bicicletas libres en la estación seleccionada
     */
    public List<Bicicleta> obtenerBicisLibresPorEstacion(Estacion estacionUsada) {
        List<Bicicleta> bicicletas      = this.fbd.obtenerBicisPorEstacion(estacionUsada);
        List<Bicicleta> bicicletasEnUso = this.fbd.obtenerBicisEnUsoPorEstacion(estacionUsada);

        bicicletas.removeAll(bicicletasEnUso);
        return bicicletas;
    }
}
