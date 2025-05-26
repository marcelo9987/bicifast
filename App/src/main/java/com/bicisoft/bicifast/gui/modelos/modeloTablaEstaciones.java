package com.bicisoft.bicifast.gui.modelos;

import com.bicisoft.bicifast.aplicacion.Estacion;
import com.bicisoft.bicifast.misc.Internacionalizacion;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Modelo de tabla para mostrar las estaciones y su capacidad.
 */
public final class modeloTablaEstaciones extends AbstractTableModel {

    /**
     * Lista de estaciones que se mostrarán en la tabla.
     */
    private List<Estacion> estaciones;
    /**
     * Lista de capacidades de las estaciones, que se mostrará junto a cada estación.
     */
    private List<Integer>  capacidades;

    /**
     * Modelo de tabla para mostrar las estaciones y su capacidad.
     */
    public modeloTablaEstaciones() {
        super();
        this.estaciones = new java.util.ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return this.estaciones.size();
    }

    @Override
    public int getColumnCount() {
        return 2; // Dos columnas: Nombre y cantidad
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Estacion estacion = this.estaciones.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> estacion.ubicacion();
            case 1 -> this.capacidades.get(rowIndex) + "/" + estacion.aforo();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        // todo: esto no debería hacerlo así, pero no tengo tiempo para hacerlo más bonito
        ResourceBundle bundle = Internacionalizacion.getInstance().getBundle();
        return switch (column) {
            case 0 -> bundle.getString("estacion");
            case 1 -> bundle.getString("capacidad");
            default -> "";
        };
    }


    @Override
    public Class getColumnClass(int columnIndex) {

        return switch (columnIndex) {
            case 0, 1 -> String.class;
            default -> null;
        };
    }

    /**
     * Método que establece las filas de la tabla.
     *
     * @param estaciones  Lista de estaciones
     * @param capacidades Lista de capacidades
     */
    public void setFilas(java.util.List<Estacion> estaciones, List<Integer> capacidades) {
        this.estaciones = estaciones;
        this.capacidades = capacidades;
        this.fireTableDataChanged();
    }

    /**
     * Método que obtiene la estación en la posición i.
     *
     * @param i Índice de la estación
     * @return Estación en la posición i
     */
    public Estacion obtenerEstacion(int i) {
        return this.estaciones.get(i);
    }

}


