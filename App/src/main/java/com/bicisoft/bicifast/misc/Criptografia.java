package com.bicisoft.bicifast.misc;

import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;

/**
 * Clase para la encriptación de contraseñas.
 * Utiliza la librería Password4j.
 *
 * @author Marcelo Fort Muñoz
 */
public final class Criptografia {
    private Criptografia() {
        super();
    }

    /**
     * Genera un hash a partir de una cadena de texto. Utiliza el algoritmo Bcrypt.
     *
     * @param texto Texto a encriptar.
     * @return Cadena de texto encriptada.
     * Cada vez que se encripta el mismo texto, se genera un hash diferente.
     * Los diferentes hashes son equivalentes, pero no son iguales.
     */
    public static String encriptar(String texto) {
        BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
        // El Salt se genera automáticamente, va incluido en el hash

        Hash hash = Password.hash(texto)
                .with(bcrypt);

        return hash.getResult();
    }

    /**
     * Verifica si una cadena de texto coincide con un hash.
     *
     * @param cadena Cadena de texto a verificar.
     * @param hash   Hash a comparar.
     * @return true si la cadena coincide con el hash, false en caso contrario.
     */
    public static boolean verificar(String cadena, String hash) {
        BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);
        return Password.check(cadena, hash)
                .with(bcrypt);
    }
}
