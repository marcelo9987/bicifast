package aplicacion;

public class Bicicleta
{
    private int id;
    private EstadoBicicleta estado;
    Estacion estacion;

    public Bicicleta(int id, Estacion estacion, EstadoBicicleta estado) {
        this.id = id;
        this.estado = estado;
    }

    public int Id() {
        return id;
    }
    public EstadoBicicleta Estado() {
        return estado;
    }
}
