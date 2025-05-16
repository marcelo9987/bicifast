package control;

import aplicacion.Bicicleta;
import aplicacion.Estacion;
import aplicacion.Usuario;
import basededatos.FachadaBaseDatos;

import java.util.List;

public class GestorBicicleta {
    FachadaBaseDatos fbd;

    public GestorBicicleta(FachadaBaseDatos fbd) {
        this.fbd = fbd;
    }


    public boolean devolverBicicleta(Usuario usuarioLogado, Estacion estacionSeleccionada) {
        Bicicleta bicicleta = fbd.obtenerBicicletaPorUsuario(usuarioLogado);
        if (bicicleta != null) {
            // Si la estación tiene el aforo completo, NO se puede devolver la bicicleta
            int aforo = fbd.obtenerOcupacionEstacion(estacionSeleccionada);
            if (aforo >= estacionSeleccionada.aforo()) {
                System.out.println("[DEBUG] La estación está llena. No se puede devolver la bicicleta.");
                return false;
            }
            fbd.estacionarBicicleta(bicicleta, estacionSeleccionada);
            fbd.devolverBicicleta(usuarioLogado, bicicleta, estacionSeleccionada);
            return true;
        }
        System.out.println("[DEBUG] No se encontró la bicicleta asociada al usuario.");
        return false;
    }

    public List<Integer> preguntaLasBicicletasPorEstacion() {
        return fbd.preguntaLasBicicletasPorEstacion();
    }

    public int reservarBicicleta(Usuario usuarioLogado, Estacion estacionSeleccionada) {
        List<Bicicleta> bicicletasLibres = obtenerBicisLibresPorEstacion(estacionSeleccionada);

        if (bicicletasLibres.isEmpty()) {
            System.out.println("[DEBUG] No hay bicicletas libres en la estación seleccionada.");
            return -1; // Indica que no hay bicicletas disponibles
        }
        Bicicleta bicicletaReservada = bicicletasLibres.getFirst(); // Reservar la primera bicicleta libre
        if (!fbd.reservarBicicleta(usuarioLogado, bicicletaReservada)) {
            System.out.println("[DEBUG] No se pudo reservar la bicicleta.");
            return -1; // Indica que no se pudo reservar la bicicleta
        }
//            System.err.println("[DEBUG] ¿Como ha llegado aquí? <.>_<.> | Fallo en la BBDD o SQL");
        return bicicletaReservada.Id(); // Retorna el ID de la bicicleta reservada

    }

    public List<Bicicleta> obtenerBicisLibresPorEstacion(Estacion estacionUsada) {
        List<Bicicleta> bicicletas      = fbd.obtenerBicisPorEstacion(estacionUsada);
        List<Bicicleta> bicicletasEnUso = fbd.obtenerBicisEnUsoPorEstacion(estacionUsada);

        bicicletas.removeAll(bicicletasEnUso);
        return bicicletas;
    }
}
