package aplicacion;

public final class Estacion
{
    private String ubicacion;
    private int aforo;

    public Estacion(String ubicacion, int aforo) {
        this.ubicacion = ubicacion;
        this.aforo = aforo;
    }

    public int aforo() {
        return aforo;
    }

    public String ubicacion() {
        return ubicacion;
    }
}
