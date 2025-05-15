package gui.modelos;

public class ModeloListaEstaciones extends javax.swing.AbstractListModel<String> {
    private java.util.List<aplicacion.Estacion> estaciones;

    public ModeloListaEstaciones() {
        this.estaciones = new java.util.ArrayList<>();
    }

    public ModeloListaEstaciones(java.util.List<aplicacion.Estacion> estaciones) {
        this.estaciones = new java.util.ArrayList<>(estaciones);
    }

    @Override
    public int getSize() {
        return estaciones.size();
    }

    @Override
    public String getElementAt(int index) {
        aplicacion.Estacion estacionEspecifica = estaciones.get(index);
        return estacionEspecifica.ubicacion();
    }

    public aplicacion.Estacion getEstacionAt(int index) {
        return estaciones.get(index);
    }

    public void setFilas(java.util.List<aplicacion.Estacion> estaciones) {
        this.estaciones = estaciones;
        fireContentsChanged(this, 0, estaciones.size() - 1);
    }
}
