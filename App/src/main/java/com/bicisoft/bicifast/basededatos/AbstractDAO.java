package com.bicisoft.bicifast.basededatos;

import com.bicisoft.bicifast.aplicacion.FachadaAplicacion;

import java.sql.Connection;


/**
 * Esqueleto de la clase DAO
 * Sirve para definir los métodos y atributos comunes a todas las clases DAO
 */
abstract class AbstractDAO {

    private FachadaAplicacion fa;
    private Connection        conexion;


    Connection getConexion() {
        return this.conexion;
    }

    void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    void setFachadaAplicacion(FachadaAplicacion fa) {
        this.fa = fa;
    }


}
