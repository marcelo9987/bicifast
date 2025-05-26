package com.bicisoft.bicifast.basededatos;

import com.bicisoft.bicifast.aplicacion.MetodoPago;
import com.bicisoft.bicifast.aplicacion.TipoUsuario;
import com.bicisoft.bicifast.aplicacion.Usuario;
import com.bicisoft.bicifast.misc.Criptografia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.sql.*;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DAOUsuarioTest {

    private static final String     GESTOR    = "mysql";
    private static final String     SERVIDOR  = "46.101.113.177";
    private static final String     PUERTO    = "3306";
    private static final String     BASEDATOS = "BiciTest";

    private final int        idUsuarioTest = 999;
    private       DAOUsuario daoUsuario;
    private       Connection conexion;
    private final static Logger     logger = org.slf4j.LoggerFactory.getLogger(DAOUsuarioTest.class);

    @BeforeEach
    void setUp() throws SQLException {
        Properties usuario = new Properties();
        usuario.setProperty("user", "test");
        usuario.setProperty("password", "test");
        conexion = java.sql.DriverManager.getConnection("jdbc:" + GESTOR + "://" + SERVIDOR + ":" + PUERTO + "/" + BASEDATOS, usuario);
        if (conexion == null) {
            logger.error("No se ha podido conectar a la base de datos");
            throw new SQLException("No se ha podido conectar a la base de datos");
        } else {
            logger.debug("Conexión a: {} exitosa", conexion.getMetaData().getURL());
        }

        // Eliminar si ya existen (respetando dependencias)
        PreparedStatement ps = conexion.prepareStatement("INSERT INTO usuario (id, nombre, primer_apellido, segundo_apellido, dni, email, direccion, fecha_nacimiento, telefono, contrasenha, metodo_pago, tipo_usuario, fecha_inicio_suscripcion) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");
        ps.setInt(1, idUsuarioTest);
        ps.setString(2, "Proba");
        ps.setString(3, "Test");
        ps.setString(4, "Usuario");
        ps.setString(5, "98765432A");
        ps.setString(6, "proba@bici.fast");
        ps.setString(7, "Calle Falsa 123");
        ps.setDate(8, java.sql.Date.valueOf("2000-01-01"));
        ps.setString(9, "123456789");
        ps.setString(10, Criptografia.encriptar("clave123"));
        ps.setString(11, "TARJETA");
        ps.setString(12, "NORMAL");
        ps.setDate(13, java.sql.Date.valueOf("2025-05-15"));
        ps.executeUpdate();

        ps.close();

        daoUsuario = new DAOUsuario(conexion);
    }

    @AfterEach
    void tearDown() throws SQLException {
        Statement st = conexion.createStatement();
//        st.executeUpdate("DELETE FROM usuario WHERE id = " + idUsuarioTest);
        // Fulminamos todas las tablas modificadas (deletes sin where)
        st.executeUpdate("DELETE FROM usuario");

        st.close();
        conexion.close();
        logger.debug("Conexión cerrada");
    }

    @Test
    void validarUsuario() {

        this.logger.info("TEST INICIADO: validarUsuario");

//        Usuario prueba            = new Usuario(1, "Juan", "Alfonso", "Ramirez", "41111111Y", "a@a.a", "a", new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse("15-05-2025").getTime()), "666666660", "$2b$12$YVPWtgnr2qrV0Epb.owGue58eGWapAFc8Mi3edI9xMVZmCMx/uuoG", MetodoPago.TARJETA, new Date(new SimpleDateFormat("dd-MM-yyyy").parse("15-05-2025").getTime()), new Date(new SimpleDateFormat("dd-MM-yyyy").parse("14-06-2025").getTime()), TipoUsuario.NORMAL);
        Usuario prueba = new Usuario(idUsuarioTest, "Proba", "Test", "Usuario", "98765432A", "proba@bici.fast", "Calle Falsa 123", java.sql.Date.valueOf("2000-01-01"), "123456789", Criptografia.encriptar("clave123"), MetodoPago.TARJETA, java.sql.Date.valueOf("2025-05-15"), java.sql.Date.valueOf("2025-06-14"), TipoUsuario.NORMAL);
        String  email             = "proba@bici.fast";
        String  contrasenha       = "clave123";
        Usuario usuarioResultante = daoUsuario.validarUsuario(email, contrasenha);

        assert usuarioResultante != null;
        assert usuarioResultante.idUsuario() == prueba.idUsuario();
        assert usuarioResultante.nombre().equals(prueba.nombre());
        assert usuarioResultante.apellido1().equals(prueba.apellido1());
        assert usuarioResultante.apellido2().equals(prueba.apellido2());
        assert usuarioResultante.dni().equals(prueba.dni());
        assert usuarioResultante.email().equals(prueba.email());
        assert usuarioResultante.direccion().equals(prueba.direccion());
        assert usuarioResultante.fecha_nacimiento().equals(prueba.fecha_nacimiento());
        assert usuarioResultante.telefono().equals(prueba.telefono());
        assert usuarioResultante.metodoPago().equals(prueba.metodoPago());
        assertEquals(usuarioResultante.inicio_suscripcion(), prueba.inicio_suscripcion(), "Inicio de suscripción no coincide: " + usuarioResultante.inicio_suscripcion() + " != " + prueba.inicio_suscripcion());
        assert usuarioResultante.fin_suscripcion().equals(prueba.fin_suscripcion());
        assert usuarioResultante.tipoUsuario().equals(prueba.tipoUsuario());

        this.logger.info("TEST PASADO: validarUsuario");
    }

    @Nested
    class ValidarUsuarioInvalido {
        @Test
        void validarUsuarioInvalidoEmail() {
            logger.info("INICIADA SUITE DE TESTS: validarUsuarioInvalido");
            logger.info("TEST INICIADO: validarUsuarioInvalidoEmail");
            String email       = "noexisto@null.error";
            String contrasenha = "clave123";

            Usuario usuarioResultante = daoUsuario.validarUsuario(email, contrasenha);
            assert usuarioResultante == null;
            logger.info("TEST PASADO: validarUsuarioInvalidoEmail");
        }

        @Test
        void validarUsuarioInvalidoContrasenha() {
            logger.info("TEST INICIADO: validarUsuarioInvalidoContrasenha");
            String email       = "proba@bici.fast";
            String contrasenha = "noexisto";

            Usuario usuarioResultante = daoUsuario.validarUsuario(email, contrasenha);
            assert usuarioResultante == null;
            logger.info("TEST PASADO: validarUsuarioInvalidoContrasenha");
        }

        @Test
        void validarUsuarioInvalidoEmailYContrasenha() {
            logger.info("TEST INICIADO: validarUsuarioInvalidoEmailYContrasenha");
            String  email             = "w";
            String  contrasenha       = "noexisto";
            Usuario usuarioResultante = daoUsuario.validarUsuario(email, contrasenha);
            assert usuarioResultante == null;
            logger.info("TEST PASADO: validarUsuarioInvalidoEmailYContrasenha");
            logger.info("FINALIZADA CON ÉXITO SUITE DE TESTS: validarUsuarioInvalido");
        }
    }
}