package com.bicisoft.bicifast.gui;

import com.bicisoft.bicifast.aplicacion.FachadaAplicacion;
import com.bicisoft.bicifast.aplicacion.TipoUsuario;
import com.bicisoft.bicifast.gui.formularios.DiaLogin;
import com.bicisoft.bicifast.gui.formularios.VPrincipalUsuario;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialLiteTheme;

import javax.swing.*;

/**
 * Clase fachada de la GUI.
 */
public final class FachadaGUI {

    // -- ATRIBUTOS --
    private final FachadaAplicacion fa;
    private       VPrincipalUsuario principal;

    /**
     * Constructor de la fachada de la GUI.
     *
     * @param fa Fachada de la aplicación
     */
    public FachadaGUI(FachadaAplicacion fa) {
        super();
        this.fa = fa;
    }

    /**
     * Método que inicia la gui
     */
    public void iniciarVista()
    {

        try
        {
            UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialLiteTheme()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frameTemporal = new JFrame();
        frameTemporal.setUndecorated(true); // sen bordes nin barra
        frameTemporal.setSize(0, 0);
        frameTemporal.setLocationRelativeTo(null);
        frameTemporal.setVisible(true); // necesario para que funcione como parent real

        DiaLogin dialogoLogin = new DiaLogin(frameTemporal, this.fa);

        // Configurar el tamaño y visibilidad
        dialogoLogin.pack();
        dialogoLogin.setLocationRelativeTo(null);
        dialogoLogin.setVisible(true);

        // Cerrar el JFrame temporal después de cerrar el diálogo
        frameTemporal.dispose();

        // En base al nivel de acceso, entro en una ventana u otra
        TipoUsuario nivelDeAcceso = this.fa.nivelAcceso();
        this._escogerVentana(nivelDeAcceso, false);
    }

    /**
     * Método que escoge la ventana a mostrar basándonos en el tipo de usuario
     *
     * @param tipoUsuario Tipo de usuario
     * @param byPass      true si se permite el acceso sin autenticar, false en caso contrario
     */
    private void _escogerVentana(TipoUsuario tipoUsuario, boolean byPass) {
        if (byPass) {
            System.out.println("[INFO] Procedo a permitir un acceso sin autenticar. Razón: I18nized");
            System.out.println("[INFO] Si no se está cambiando el idioma, algo muy grave ha pasado.");
        }

        switch (tipoUsuario) {
            case Admin -> {
                // Lógica para el administrador
                System.out.println("Acceso de Administrador");
                JOptionPane.showMessageDialog(null, "Acceso de Administrador no implementado", "Error", JOptionPane.ERROR_MESSAGE);
            }
            case Mant -> {
                // Lógica para el mantenimiento
                System.out.println("Acceso de Mantenimiento");
                JOptionPane.showMessageDialog(null, "Acceso de Mantenimiento no implementado", "Error", JOptionPane.ERROR_MESSAGE);
            }
            case NORMAL -> {
                // Lógica para el usuario normal
                System.out.println("Acceso Normal");
                this.principal = new VPrincipalUsuario(this.fa);
                this.principal.setVisible(true);
                this.principal.pack();
                this.principal.setLocationRelativeTo(null);
            }
            case null, default ->
                // Lógica para el acceso no definido
                    System.out.println("Acceso No Definido");
        }
    }

    /**
     * Método que lanza la interfaz sin autenticar
     */
    public void lanzarInterfazSinAutenticacion() {
        // En base al nivel de acceso, entro en una ventana u otra
        this.principal.dispose();

        TipoUsuario nivelDeAcceso = this.fa.nivelAcceso();
        this._escogerVentana(nivelDeAcceso, true);
    }
}
