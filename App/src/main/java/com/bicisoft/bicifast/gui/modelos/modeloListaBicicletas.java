package com.bicisoft.bicifast.gui.modelos;

import com.bicisoft.bicifast.aplicacion.Bicicleta;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Modelo de lista para mostrar las bicicletas presentes en una estación.
 */
public final class modeloListaBicicletas extends AbstractListModel<String> {
    private List<Bicicleta> bicicletas;

    /**
     * Modelo de lista para mostrar las bicicletas presentes en una estación.
     */
    public modeloListaBicicletas() {
        super();
        this.bicicletas = new ArrayList<>();
    }

    /**
     * Modelo de lista para mostrar las bicicletas presentes en una estación.
     *
     * @param bicicletas Lista de bicicletas a mostrar.
     */
    public modeloListaBicicletas(List<Bicicleta> bicicletas) {
        super();
        this.bicicletas = new ArrayList<>(bicicletas);
    }

    @Override
    public int getSize() {
        return this.bicicletas.size();
    }

    @Override
    public String getElementAt(int index) {
        Bicicleta estacionEspecifica = this.bicicletas.get(index);
        return "" + estacionEspecifica.Id();
    }

    /**
     * Devuelve una bicicleta en la posicion index.
     *
     * @param index Indice de la bicicleta a obtener.
     * @return Bicicleta en la posicion index.
     */
    public Bicicleta getEstacionAt(int index) {
        return this.bicicletas.get(index);
    }

    /**
     * Cambia la lista de bicicletas a mostrar.
     * @param bicicletas Lista de bicicletas que popularán el modelo.
     */
    public void setFilas(List<Bicicleta> bicicletas) {
        this.bicicletas = bicicletas;
        int cantidad = bicicletas.size();
        this.fireContentsChanged(this, 0, cantidad - 1);
    }
}
