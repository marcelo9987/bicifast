package com.bicisoft.bicifast.gui;

import com.bicisoft.bicifast.aplicacion.Estacion;
import com.bicisoft.bicifast.aplicacion.FachadaAplicacion;
import com.bicisoft.bicifast.aplicacion.TipoUsuario;
import com.bicisoft.bicifast.gui.formularios.*;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialLiteTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Clase fachada de la GUI.
 */
public final class FachadaGUI {

    // -- LOGGER --
    private final static Logger logger = LoggerFactory.getLogger(FachadaGUI.class);

    // -- FAÇADA --
    private final FachadaAplicacion fa;

    // -- ATRIBUTOS --
    private VPrincipalUsuario principal;

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
    public void iniciarVista() {

        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialLiteTheme()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frameTemporal = new JFrame();
        frameTemporal.setUndecorated(true); // sen bordes nin barra
        frameTemporal.setSize(0, 0);
        frameTemporal.setLocationRelativeTo(null);
        frameTemporal.setVisible(true); // necesario para que funcione como parent real

        DiaLogin dialogoLogin = new DiaLogin(frameTemporal, this.fa, this);

        // Configurar el tamaño y visibilidad
        dialogoLogin.pack();
        dialogoLogin.setLocationRelativeTo(null);
        dialogoLogin.setVisible(true);
        dialogoLogin.pack();

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
            logger.warn("Procedo a permitir un acceso sin autenticar. Razón: I18nized");
            logger.warn("Si no se está cambiando el idioma, algo muy grave ha pasado.");
        }

        switch (tipoUsuario) {
            case Admin -> {
                // Lógica para el administrador
                logger.info("Acceso de Administrador");
                JOptionPane.showMessageDialog(null, "Acceso de Administrador no implementado", "Error", JOptionPane.ERROR_MESSAGE);
            }
            case Mant -> {
                // Lógica para el mantenimiento
                logger.info("Acceso de Mantenimiento");
                JOptionPane.showMessageDialog(null, "Acceso de Mantenimiento no implementado", "Error", JOptionPane.ERROR_MESSAGE);
            }
            case NORMAL -> {
                // Lógica para el usuario normal
                logger.info("Acceso Normal");
                this.principal = new VPrincipalUsuario(this.fa, this);
                this.principal.setVisible(true);
                this.principal.pack();
                this.principal.setLocationRelativeTo(null);
            }
            case null, default ->
                // Lógica para el acceso no definido
                    logger.warn("Acceso No Definido");
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

    /**
     * Método que lanza el menú de bicicletas
     *
     * @param vPrincipalUsuario    Ventana principal del usuario
     * @param estacionSeleccionada Estación seleccionada
     */
    public void lanzarMenuBicis(JFrame vPrincipalUsuario, Estacion estacionSeleccionada) {
        DiaBicis menuBicis = new DiaBicis(vPrincipalUsuario, true, this.fa, estacionSeleccionada);
        menuBicis.setVisible(true);
        menuBicis.pack();
        menuBicis.setLocationRelativeTo(null);
    }

    /**
     * Método que lanza el diálogo de selección de idioma.
     *
     * @param parent Ventana principal del usuario
     */
    public void lanzarSeleccionIdioma(JFrame parent) {
        VIdioma idioma = new VIdioma(parent, true, this.fa);
        idioma.setVisible(true);
    }

    /**
     * Método que lanza el diálogo de perfil de usuario.
     *
     * @param parent Ventana principal del usuario
     */
    public void lanzarPerfilUsuario(JFrame parent) {
        DiaUsuario menuUsuario = new DiaUsuario(parent, this.fa);

        menuUsuario.setVisible(true);
    }

    /**
     * Método que lanza un mensaje de aviso.
     *
     * @param mensaje Mensaje que se mostrará en el aviso
     */
    public void lanzarMensajeAviso(String mensaje) {
        JLabel label = new JLabel(mensaje);
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));

        final int AJUSTAR_LINEA = 7; // Ajuste de línea para el ancho del mensaje

        // Tamaño mínimo de línea
        int ancho = Math.max(300, mensaje.length() * AJUSTAR_LINEA);
        int alto  = 50;

        label.setPreferredSize(new Dimension(ancho, alto));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setVerticalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(null, panel, "AVISO", JOptionPane.INFORMATION_MESSAGE);
    }
}
