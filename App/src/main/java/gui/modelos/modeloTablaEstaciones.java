package gui.modelos;

import aplicacion.Estacion;

import javax.swing.table.AbstractTableModel;
import java.util.List;

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
        String nombre = "";

        switch (col) {
            case 0:
                nombre = "Nombre";
                break;
            case 1:
                nombre = "Capacidad máxima";
                break;
        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col) {
        Class clase = null;

        switch (col) {
            case 0:
                clase = java.lang.String.class;
                break;
            case 1:
                clase = java.lang.String.class;
                break;
        }
        return clase;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object resultado = null;

        switch (col) {
            case 0:
                resultado = estaciones.get(row).ubicacion();
                break;
            case 1:
                resultado = capacidades.get(row) + "/" + estaciones.get(row).aforo();
                break;
        }
        return resultado;
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


