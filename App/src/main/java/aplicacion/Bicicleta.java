package aplicacion;

public final class Bicicleta
{
    private final int             id;
    private final EstadoBicicleta estado;
    Estacion estacion;

    public Bicicleta(int id, Estacion estacion, EstadoBicicleta estado) {
        this.id = id;
        this.estacion = estacion;
        this.estado = estado;
    }

    public int Id() {
        return id;
    }

    public Estacion estacion() {
        return estacion;
    }
}
