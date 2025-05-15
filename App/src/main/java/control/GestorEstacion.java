package control;

import aplicacion.Estacion;
import basededatos.FachadaBaseDatos;

import java.util.List;

public class GestorEstacion
{
    FachadaBaseDatos fbd;

    public GestorEstacion(FachadaBaseDatos fbd) {
        this.fbd = fbd;
    }

    public List<Estacion> preguntaLasEstaciones()
    {
        return fbd.preguntaLasEstaciones();
    }
}
