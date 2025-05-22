package com.bicisoft.bicifast.misc;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CriptografiaTest {
    private final Logger logger = LoggerFactory.getLogger(CriptografiaTest.class);

    @Test
    void encriptar() {
        this.logger.debug("INICIANDO TEST: encriptar");
        String texto = "123456";
        String hash  = Criptografia.encriptar(texto);
        assert ((!texto.equals(hash)) && Criptografia.verificar(texto, hash));
        this.logger.debug("TEST PASADO: encriptar");
    }

    @Test
    void verificar() {
        this.logger.debug("INICIANDO TEST: verificar");
        String cadena1 = "123456";
        String cadena2 = "secreto";
        String cadena3 = "secreto123456";
        String hash1   = Criptografia.encriptar(cadena1);
        String hash2   = Criptografia.encriptar(cadena2);
        String hash3   = Criptografia.encriptar(cadena3);
        assertTrue(Criptografia.verificar(cadena1, hash1) &&
                Criptografia.verificar(cadena2, hash2) &&
                Criptografia.verificar(cadena3, hash3));
        this.logger.debug("TEST PASADO: verificar");
    }

    @Test
    void verificarFallo() {
        this.logger.debug("INICIANDO TEST: verificarFallo");
        String cadena1     = "123456";
        String cadena2     = "EstoNoEsUnaContraseña";
        String cadena3     = "G0s0D3P0b4r3C0ntr4S1NA1S1NC0RR3CT4S";
        String contrasenha = Criptografia.encriptar("secreto");

        boolean resultado1     = Criptografia.verificar(cadena1, contrasenha);
        boolean resultado2     = Criptografia.verificar(cadena2, contrasenha);
        boolean resultado3     = Criptografia.verificar(cadena3, contrasenha);
        boolean resultadoFinal = resultado1 || resultado2 || resultado3;
        assertFalse(resultadoFinal);
        this.logger.debug("TEST PASADO: verificarFallo");
    }
}