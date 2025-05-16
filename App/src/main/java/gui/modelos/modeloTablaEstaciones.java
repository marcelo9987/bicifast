package gui.modelos;

import aplicacion.Estacion;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class modeloTablaEstaciones extends AbstractTableModel {

    private List<Estacion> estaciones;
    private List<Integer>            capacidades;

    public modeloTablaEstaciones() {
        this.estaciones = new java.util.ArrayList<>();
    }

    @Override
    public int getColumnCount() {
        return 2; // Dos columnas: Nombre y cantidad
    }

    @Override
    public int getRowCount() {
        return estaciones.size();
    }

    @Override
    public String getColumnName(int col) {
        ResourceBundle bundle = ResourceBundle.getBundle("gui.formularios.internacionalizacion", Locale.getDefault());
        return switch (col) {
            case 0 -> bundle.getString("estacion");
            case 1 -> bundle.getString("capacidad");
            default -> "";
        };
    }

    @Override
    public Class getColumnClass(int col) {

        return switch (col) {
            case 0, 1 -> String.class;
            default -> null;
        };
    }

    @Override
    public Object getValueAt(int row, int col) {

        return switch (col) {
            case 0 -> estaciones.get(row).ubicacion();
            case 1 -> capacidades.get(row) + "/" + estaciones.get(row).aforo();
            default -> null;
        };
    }

    public void setFilas(java.util.List<Estacion> estaciones, List<Integer> capacidades) {
        this.estaciones = estaciones;
        this.capacidades = capacidades;
        fireTableDataChanged();
    }

    public Estacion obtenerEstacion(int i) {
        return this.estaciones.get(i);
    }

}


