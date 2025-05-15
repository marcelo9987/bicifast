package gui;

import aplicacion.FachadaAplicacion;
import gui.fomularios.VLogin;

public class FachadaGUI {
    FachadaAplicacion fa;

    public FachadaGUI(FachadaAplicacion fa)
    {
        this.fa = fa;
    }

    public void iniciarVista()
    {
        VLogin loginFrame = new VLogin(fa);

        // Configurar el tamaño y visibilidad
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }
}
