package aplicacion;

public final class Estacion
{
    private int idEstacion;
    private String ubicacion;
    private int aforo;

    public Estacion(int idEstacion, String ubicacion, int aforo)
    {
        this.idEstacion = idEstacion;
        this.ubicacion = ubicacion;
        this.aforo = aforo;
    }

    public int aforo() {
        return aforo;
    }

    public String ubicacion() {
        return ubicacion;
    }

    public int idEstacion() {
        return idEstacion;
    }
}
