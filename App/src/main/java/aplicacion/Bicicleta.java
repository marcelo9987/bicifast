package aplicacion;

public final class Bicicleta
{
    private int id;
    private EstadoBicicleta estado;
    Estacion estacion;

    public Bicicleta(int id, Estacion estacion, EstadoBicicleta estado) {
        this.id = id;
        this.estacion = estacion;
        this.estado = estado;
    }

    public int Id() {
        return id;
    }
    public EstadoBicicleta Estado() {
        return estado;
    }

    public Estacion estacion() {
        return estacion;
    }
}
