package com.bicisoft.bicifast.basededatos;

import com.bicisoft.bicifast.aplicacion.*;
import com.bicisoft.bicifast.misc.Criptografia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class DAOBicicletaTest {
    private static final String GESTOR    = "mysql";
    private static final String SERVIDOR  = "localhost";
    private static final String PUERTO    = "3306";
    private static final String BASEDATOS = "bicifast";
    Connection conexion;
    private DAOBicicleta daoBicicleta;
    private int          idEstacionTest = 999;
    private int          idBiciTest     = 999;
    private int          idUsuarioTest  = 999;

    @BeforeEach
    void setUp() throws SQLException {
        Properties usuario = new Properties();
        usuario.setProperty("user", "test");
        usuario.setProperty("password", "root");
        conexion = java.sql.DriverManager.getConnection("jdbc:" + GESTOR + "://" + SERVIDOR + ":" + PUERTO + "/" + BASEDATOS, usuario);

        // Eliminar si ya existen (respectando dependencias)
        Statement st = conexion.createStatement();
        st.executeUpdate("DELETE FROM viaje WHERE usuario = " + idUsuarioTest);
        st.executeUpdate("DELETE FROM bicicleta WHERE id = " + idBiciTest);
        st.executeUpdate("DELETE FROM estacion WHERE id = " + idEstacionTest);
        st.executeUpdate("DELETE FROM usuario WHERE id = " + idUsuarioTest);

        // Insertar estación
        PreparedStatement ps = conexion.prepareStatement("INSERT INTO estacion (id, ubicacion, aforo) VALUES (?, ?, ?)");
        ps.setInt(1, idEstacionTest);
        ps.setString(2, "Estación Test");
        ps.setInt(3, 20);
        ps.executeUpdate();

        // Insertar bicicleta
        ps = conexion.prepareStatement("INSERT INTO bicicleta (id, estado, estacion) VALUES (?, ?, ?)");
        ps.setInt(1, idBiciTest);
        ps.setString(2, "CORRECTO");
        ps.setInt(3, idEstacionTest);
        ps.executeUpdate();

        // Insertar usuario
        ps = conexion.prepareStatement("INSERT INTO usuario (id, nombre, primer_apellido, segundo_apellido, dni, email, direccion, fecha_nacimiento, telefono, contrasenha, metodo_pago, tipo_usuario) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
        ps.executeUpdate();

        ps.close();
        st.close();

        FachadaAplicacion faDummie = new FachadaAplicacion() {
        };
        daoBicicleta = new DAOBicicleta(conexion, faDummie);
    }

    @Test
    void reservarBicicleta() throws SQLException {
        Estacion  estacion  = new Estacion(idEstacionTest, "Estación Test", 10);
        Bicicleta bicicleta = new Bicicleta(idBiciTest, estacion, EstadoBicicleta.CORRECTO);
        //         Usuario prueba = new Usuario(1, "Juan", "Alfonso", "Ramirez", "41111111Y", "a@a.a", "a", new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse("15-05-2025").getTime()), "666666660", "$2b$12$YVPWtgnr2qrV0Epb.owGue58eGWapAFc8Mi3edI9xMVZmCMx/uuoG", MetodoPago.TARJETA, new Date(new SimpleDateFormat("dd-MM-yyyy").parse("15-05-2025").getTime()), new Date(new SimpleDateFormat("dd-MM-yyyy").parse("14-06-2025").getTime()), TipoUsuario.NORMAL);
        Usuario usuario = new Usuario(idUsuarioTest, "Proba", "Test", "Usuario", "98765432A", "proba@bici.fast", "Calle Falsa 123", java.sql.Date.valueOf("2000-01-01"), "123456789", Criptografia.encriptar("clave123"), MetodoPago.TARJETA, java.sql.Date.valueOf("2025-05-15"), java.sql.Date.valueOf("2025-06-14"), TipoUsuario.NORMAL);
        boolean reservada = daoBicicleta.reservarBicicleta(usuario, bicicleta);

        assertTrue(reservada, "LA reserva debería completarse correctamente");

        // Verificar que se inserto el registro
        PreparedStatement ps = conexion.prepareStatement("SELECT * FROM viaje WHERE usuario = ? AND bicicleta = ?");
        ps.setInt(1, idUsuarioTest);
        ps.setInt(2, idBiciTest);
        ResultSet rs = ps.executeQuery();
        assertTrue(rs.next(), "Debería existir un registro en la tabla 'viaje'");
        assertNotNull(rs.getTimestamp("hora_inicio"));
        assertNull(rs.getTimestamp("hora_fin"));
        rs.close();
        ps.close();
    }




    @AfterEach
    void tearDown() throws SQLException {
        Statement st = conexion.createStatement();
        st.executeUpdate("DELETE FROM viaje WHERE usuario = " + idUsuarioTest);
        st.executeUpdate("DELETE FROM bicicleta WHERE id = " + idBiciTest);
        st.executeUpdate("DELETE FROM estacion WHERE id = " + idEstacionTest);
        st.executeUpdate("DELETE FROM usuario WHERE id = " + idUsuarioTest);
        st.close();
        conexion.close();
    }


}