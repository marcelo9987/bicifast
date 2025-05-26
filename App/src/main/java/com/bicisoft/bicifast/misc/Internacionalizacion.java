package com.bicisoft.bicifast.misc;

import com.bicisoft.bicifast.aplicacion.EnumIdioma;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Clase que se encarga de la internacionalización de la aplicación.
 *
 * @version 2.0
 */
public final class Internacionalizacion {
    private static Internacionalizacion instance;
    private        ResourceBundle       bundle;

    private Internacionalizacion() {
        super();

        Locale.setDefault(Locale.of("es", "ES"));

        this.bundle = ResourceBundle.getBundle("gui.formularios.internacionalizacion", Locale.getDefault());

    }

    /**
     * Devuelve la instancia de la clase Internacionalizacion.
     *
     * @return Devuelve la instancia de la clase Internacionalizacion.
     */
    public static Internacionalizacion getInstance() {
        if (Internacionalizacion.instance != null) {
            return Internacionalizacion.instance;
        }
        synchronized (Internacionalizacion.class) {
            if (Internacionalizacion.instance == null) {
                Internacionalizacion.instance = new Internacionalizacion();
            }
        }
        return Internacionalizacion.instance;
    }

    /**
     * Cambia el idioma de la aplicación.
     *
     * @param idioma Idioma al que se quiere cambiar.
     *               Opciones:
     *               ESPAÑOL,
     *               GALEGO,
     *               INGLES.
     */
    public void cambiarIdioma(EnumIdioma idioma) {
        switch (idioma) {
            case ESPANHOL:
                this.bundle = ResourceBundle.getBundle("gui.formularios.internacionalizacion", Locale.of("es", "ES"));
                break;

            case GALEGO:
                this.bundle = ResourceBundle.getBundle("gui.formularios.internacionalizacion", Locale.of("gl", "GL"));
                break;

            case INGLES:
                this.bundle = ResourceBundle.getBundle("gui.formularios.internacionalizacion", Locale.of("en", "US"));
                break;
        }
        ResourceBundle.clearCache();
    }

    /**
     * Devuelve el ResourceBundle actual.
     *
     * @return Devuelve el ResourceBundle actual.
     */
    public ResourceBundle getBundle() {
        if (this.bundle == null) {
            this.bundle = ResourceBundle.getBundle("gui.formularios", Locale.getDefault());
        }
        return this.bundle;
    }


}
