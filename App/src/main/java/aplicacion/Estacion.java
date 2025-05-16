package aplicacion;

import java.util.Objects;

public final class Estacion {
    private final int    idEstacion;
    private final String ubicacion;
    private final int    aforo;

    public Estacion(int idEstacion, String ubicacion, int aforo) {
        this.idEstacion = idEstacion;
        this.ubicacion = ubicacion;
        this.aforo = aforo;
    }

    public int idEstacion() {
        return idEstacion;
    }

    public String ubicacion() {
        return ubicacion;
    }

    public int aforo() {
        return aforo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (Estacion) obj;
        return this.idEstacion == that.idEstacion &&
                Objects.equals(this.ubicacion, that.ubicacion) &&
                this.aforo == that.aforo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEstacion, ubicacion, aforo);
    }

    @Override
    public String toString() {
        return "Estacion[" +
                "idEstacion=" + idEstacion + ", " +
                "ubicacion=" + ubicacion + ", " +
                "aforo=" + aforo + ']';
    }

}
