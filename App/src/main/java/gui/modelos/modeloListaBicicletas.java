package gui.modelos;

public class modeloListaBicicletas extends javax.swing.AbstractListModel<String> {
    private java.util.List<aplicacion.Bicicleta> bicicletas;

    public modeloListaBicicletas() {
        this.bicicletas = new java.util.ArrayList<>();
    }

    public modeloListaBicicletas(java.util.List<aplicacion.Bicicleta> bicicletas) {
        this.bicicletas = new java.util.ArrayList<>(bicicletas);
    }

    @Override
    public int getSize() {
        return bicicletas.size();
    }

    @Override
    public String getElementAt(int index) {
        aplicacion.Bicicleta estacionEspecifica = bicicletas.get(index);
        return ""+estacionEspecifica.Id();
    }

    public aplicacion.Bicicleta getEstacionAt(int index) {
        return bicicletas.get(index);
    }

    public void setFilas(java.util.List<aplicacion.Bicicleta> bicicletas) {
        this.bicicletas = bicicletas;
        fireContentsChanged(this, 0, bicicletas.size() - 1);
    }
}
