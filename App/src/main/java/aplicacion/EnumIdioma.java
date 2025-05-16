package aplicacion;

import java.util.Objects;

/**
 * Enumerado que representa los idiomas en los que se puede mostrar la aplicación
 */
public enum EnumIdioma
{
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

    private EnumIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     * Buscar el idioma por su etiqueta
     * Apoyado en el método creado por Baeldung
     * @see https://www.baeldung.com/java-enum-values
     */
    public static EnumIdioma valueOfLabel(String label) {
        for (EnumIdioma e : values()) {
            if (e.idioma.equals(label)) {
                return e;
            }
        }
        return null;
    }

    public String nombreIdioma() {
        return Objects.equals(this.idioma, ESPANHOL.idioma) ?"español":
                Objects.equals(this.idioma, GALEGO.idioma) ?"galego":
                        Objects.equals(this.idioma, INGLES.idioma) ?"inglés":"";
    }
}
