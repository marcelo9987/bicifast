package control;

import aplicacion.Bicicleta;
import aplicacion.Estacion;
import aplicacion.Usuario;
import basededatos.FachadaBaseDatos;

import java.util.List;

public class GestorBicicleta
{
    FachadaBaseDatos fbd;
    public GestorBicicleta(FachadaBaseDatos fbd)
    {
        this.fbd = fbd;
    }

    public List<Bicicleta> obtenerBicisLibresPorEstacion(Estacion estacionUsada)
    {
        List<Bicicleta> bicicletas = fbd.obtenerBicisPorEstacion(estacionUsada);
        List<Bicicleta> bicicletasEnUso = fbd.obtenerBicisEnUsoPorEstacion(estacionUsada);

        bicicletas.removeAll(bicicletasEnUso);
        return bicicletas;
    }

    public void devolverBicicleta(Usuario usuarioLogado, Estacion estacionSeleccionada)
    {
        Bicicleta bicicleta = fbd.obtenerBicicletaPorUsuario(usuarioLogado);
        if (bicicleta != null) {
            fbd.estacionarBicicleta(bicicleta, estacionSeleccionada);
            fbd.devolverBicicleta(usuarioLogado, bicicleta, estacionSeleccionada);
        }
    }

    public List<Integer> preguntaLasBicicletasPorEstacion()
    {
        List<Integer> bicicletas = fbd.preguntaLasBicicletasPorEstacion();
        return bicicletas;
    }
}
