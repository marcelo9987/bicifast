package gui;

import aplicacion.FachadaAplicacion;
import aplicacion.TipoUsuario;
import gui.fomularios.VLogin;
import gui.fomularios.VPrincipalAdmin;
import gui.fomularios.VPrincipalUsuario;

import javax.swing.*;

public class FachadaGUI {
    FachadaAplicacion fa;

    public FachadaGUI(FachadaAplicacion fa)
    {
        this.fa = fa;
    }

    public void iniciarVista()
    {
        JFrame frameTemporal = new JFrame();
        frameTemporal.setUndecorated(true); // sen bordes nin barra
        frameTemporal.setSize(0, 0);
        frameTemporal.setLocationRelativeTo(null);
        frameTemporal.setVisible(true); // necesario para que funcione como parent real

        VLogin dialogo_login = new VLogin(frameTemporal, fa);

        // Configurar el tamaño y visibilidad
        dialogo_login.pack();
        dialogo_login.setLocationRelativeTo(null);
        dialogo_login.setVisible(true);

        // Cerrar el JFrame temporal después de cerrar el diálogo
        frameTemporal.dispose();

        // En base al nivel de acceso, entro en una ventana u otra
        escogerVentana(fa.nivelAcceso());
    }

    private void escogerVentana(TipoUsuario tipoUsuario) {
        if(tipoUsuario == TipoUsuario.Admin) {
            // Lógica para el administrador
            System.out.println("Acceso de Administrador");
            VPrincipalAdmin vPrincipalAdmin = new VPrincipalAdmin();
            vPrincipalAdmin.setVisible(true);
            vPrincipalAdmin.pack();
            vPrincipalAdmin.setLocationRelativeTo(null);


        } else if(tipoUsuario == TipoUsuario.Mant) {
            // Lógica para el mantenimiento
            System.out.println("Acceso de Mantenimiento");
        } else if(tipoUsuario == TipoUsuario.NORMAL) {
            // Lógica para el usuario normal
            System.out.println("Acceso Normal");
            VPrincipalUsuario vPrincipalUsuario = new VPrincipalUsuario();
            vPrincipalUsuario.setVisible(true);
            vPrincipalUsuario.pack();
            vPrincipalUsuario.setLocationRelativeTo(null);
        } else {
            // Lógica para el acceso no definido
            System.out.println("Acceso No Definido");
        }
    }

    public void escalaTipoAccesoMenuPrincipal(TipoUsuario nivelAcceso)
    {
        fa.setNivelAcceso(nivelAcceso);
    }
}
