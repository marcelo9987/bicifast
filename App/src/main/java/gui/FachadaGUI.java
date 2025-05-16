package gui;

import aplicacion.FachadaAplicacion;
import aplicacion.TipoUsuario;
import aplicacion.Usuario;
import gui.formularios.DiaLogin;
import gui.formularios.VPrincipalUsuario;
import java.util.Locale;

import javax.swing.*;

public class FachadaGUI {

    // -- CONSTANTES --
    private static final String LENGUA_POR_DEFECTO = "gl"; // Galego

    // -- ATRIBUTOS --
    FachadaAplicacion fa;
    Locale localizacionActual;
    VPrincipalUsuario principal;

    public FachadaGUI(FachadaAplicacion fa)
    {
        this.localizacionActual =  Locale.of(LENGUA_POR_DEFECTO);
        this.fa = fa;
    }

    public void iniciarVista()
    {
        JFrame frameTemporal = new JFrame();
        frameTemporal.setUndecorated(true); // sen bordes nin barra
        frameTemporal.setSize(0, 0);
        frameTemporal.setLocationRelativeTo(null);
        frameTemporal.setVisible(true); // necesario para que funcione como parent real

        DiaLogin dialogo_login = new DiaLogin(this.localizacionActual,frameTemporal, fa);

        // Configurar el tamaño y visibilidad
        dialogo_login.pack();
        dialogo_login.setLocationRelativeTo(null);
        dialogo_login.setVisible(true);

        // Cerrar el JFrame temporal después de cerrar el diálogo
        frameTemporal.dispose();

        // En base al nivel de acceso, entro en una ventana u otra
        escogerVentana(fa.nivelAcceso(), false);
    }

    public void lanzarInterfazSinAutenticacion()
    {
        // En base al nivel de acceso, entro en una ventana u otra
        this.principal.dispose();
        escogerVentana(fa.nivelAcceso(), true);
    }

    private void escogerVentana(TipoUsuario tipoUsuario, boolean byPass)
    {
        if(byPass)
        {
            System.out.println("[INFO] Procedo a permitir un acceso sin autenticar. Razón: I8Nized");
            System.out.println("[INFO] Si no se está cambiando el idioma, algo muy grave ha pasado.");
        }

        if(tipoUsuario == TipoUsuario.Admin) {
            // Lógica para el administrador
            System.out.println("Acceso de Administrador");
//            VPrincipalAdmin vPrincipalAdmin = new VPrincipalAdmin();
//            vPrincipalAdmin.setVisible(true);
//            vPrincipalAdmin.pack();
//            vPrincipalAdmin.setLocationRelativeTo(null);
            JOptionPane.showMessageDialog(null, "Acceso de Administrador no implementado", "Error", JOptionPane.ERROR_MESSAGE);


        } else if(tipoUsuario == TipoUsuario.Mant) {
            // Lógica para el mantenimiento
            System.out.println("Acceso de Mantenimiento");
            JOptionPane.showMessageDialog(null, "Acceso de Mantenimiento no implementado", "Error", JOptionPane.ERROR_MESSAGE);
        } else if(tipoUsuario == TipoUsuario.NORMAL) {
            // Lógica para el usuario normal
            System.out.println("Acceso Normal");
            principal = new VPrincipalUsuario(this.fa);
            principal.setVisible(true);
            principal.pack();
            principal.setLocationRelativeTo(null);
        } else {
            // Lógica para el acceso no definido
            System.out.println("Acceso No Definido");
        }
    }


    public void setLocalizacionActual(Locale localizacion)
    {
        this.localizacionActual = localizacion;
        // Actualizar la localización de la interfaz gráfica
        if (principal != null) {
            // Actualizar la localización de la ventana principal
            principal.setLocale(localizacion);
            principal.revalidate();
            principal.repaint();
        }

    }
}
