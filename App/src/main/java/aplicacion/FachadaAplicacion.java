package aplicacion;

import basededatos.FachadaBaseDatos;
import gui.FachadaGUI;

public class FachadaAplicacion {

    FachadaGUI fgui;

    public FachadaAplicacion() {
        fgui = new FachadaGUI(this);
    }

    public static void main(String[] args)
    {
        FachadaBaseDatos fbd = new FachadaBaseDatos();
        fbd._funciona();

        FachadaAplicacion fa = new FachadaAplicacion();
        fa.lanzarAplicacion();

    }

    public void lanzarAplicacion() {
        System.out.println("Iniciando la aplicación...");
        fgui.iniciarVista();
    }
}
