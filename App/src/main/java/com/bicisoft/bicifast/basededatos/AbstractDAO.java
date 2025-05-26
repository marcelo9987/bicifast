package com.bicisoft.bicifast.basededatos;


import java.sql.Connection;


/**
 * Esqueleto de la clase DAO
 * Sirve para definir los métodos y atributos comunes a todas las clases DAO
 */
abstract class AbstractDAO {

    /**
     * Conexión a la base de datos
     */
    private Connection conexion;


    /**
     * @return Devuelve la conexión a la base de datos
     */
    Connection getConexion() {
        return this.conexion;
    }

    void setConexion(Connection conexion) {
        this.conexion = conexion;
    }


}
