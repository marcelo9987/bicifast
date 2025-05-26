package com.bicisoft.bicifast.aplicacion;

/**
 * Enumerado que representa los idiomas en los que se puede mostrar la aplicación
 */
public enum EnumIdioma {
    /**
     * Idioma español (ES)
     */
    ESPANHOL("es"),
    /**
     * Idioma galego (GL)
     */
    GALEGO("gl"),
    /**
     * Idioma inglés (EUA)
     */
    INGLES("en");

    private final String idioma;

    EnumIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     * Buscar el idioma por su etiqueta
     * Apoyado en el método creado por Baeldung: <a href="https://www.baeldung.com/java-enum-values">fuente</a>
     *
     * @param label Etiqueta del idioma
     * @return El idioma correspondiente a la etiqueta
     */
    public static EnumIdioma valueOfLabel(String label) {
        for (EnumIdioma e : EnumIdioma.values()) {
            if (e.idioma.equals(label)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Método que devuelve el idioma en forma de cadena
     *
     * @return Cadena con el idioma
     */
    public String nombreIdioma() {
        if (this.idioma.equals(EnumIdioma.ESPANHOL.idioma)) {
            return "español";
        }

        if (this.idioma.equals(EnumIdioma.GALEGO.idioma)) {
            return "galego";
        }
        return this.idioma.equals(EnumIdioma.INGLES.idioma) ? "inglés" : "";
    }
}


