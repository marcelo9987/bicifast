package aplicacion;

import basededatos.FachadaBaseDatos;
import gui.FachadaGUI;

public class FachadaAplicacion {

    FachadaGUI fgui;

    public FachadaAplicacion() {
        fgui = new FachadaGUI();
    }

    public static void main(String[] args)
    {
        FachadaBaseDatos fachada = new FachadaBaseDatos();
        fachada._funciona();

    }

    public void lanzarAplicacion() {
        System.out.println("Iniciando la aplicación...");
        fgui.iniciarVista();
    }
}
