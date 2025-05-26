package com.bicisoft.bicifast.basededatos;


import com.bicisoft.bicifast.aplicacion.Bicicleta;
import com.bicisoft.bicifast.aplicacion.Estacion;
import com.bicisoft.bicifast.aplicacion.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Clase que gestiona la conexión a la base de datos
 */
public final class FachadaBaseDatos {
    //---- LOGGER ----
    private final static Logger log = LoggerFactory.getLogger(FachadaBaseDatos.class);

    // ---- D.A.O.(s) ----
    private final DAOUsuario   daoUsuario;
    private final DAOEstacion  daoEstacion;
    private final DAOBicicleta daoBicicleta;
    private final DAOViaje     daoViaje;

    // ---- CONEXIÓN A BBDD ----
    private Connection conexion;


    /**
     * Constructor de la clase FachadaBaseDatos
     */
    public FachadaBaseDatos() {
        super();
        // ---- INICIALIZACIÓN DE VARIABLES ----
        // ---- CONEXIÓN A BB.DD. ----
        Properties  configuracion = new Properties();
        InputStream arqConfiguracion;

        try {
            arqConfiguracion = this.getClass().getResourceAsStream("/baseDatos.properties");
            if (arqConfiguracion == null) {
                throw new FileNotFoundException("No se pudo encontrar baseDatos.properties");
            }

            configuracion.load(arqConfiguracion);
            arqConfiguracion.close();

            Properties usuario = new Properties();


            String gestor = configuracion.getProperty("gestor");

            usuario.setProperty("user", configuracion.getProperty("usuario"));
            usuario.setProperty("password", configuracion.getProperty("clave"));
            this.conexion = java.sql.DriverManager.getConnection("jdbc:" + gestor + "://" +
                            configuracion.getProperty("servidor") + ":" +
                            configuracion.getProperty("puerto") + "/" +
                            configuracion.getProperty("baseDatos"),
                    usuario);


        } catch (SQLException | IOException exc) {
            String directorio = System.getProperty("user.dir");
            log.debug("Estoy en el dir:{}", directorio);
            String mensaje = exc.getMessage();
            log.debug(mensaje);
        }

        // ------ INICIALIZACIÓN DE CONECTORES A BBDD (D.A.O.) ------
        this.daoUsuario = new DAOUsuario(this.conexion);
        this.daoEstacion = new DAOEstacion(this.conexion);
        this.daoBicicleta = new DAOBicicleta(this.conexion);
        this.daoViaje = new DAOViaje(this.conexion);
    }


    /**
     * Prueba de conexión a la base de datos
     */
    @Deprecated(since = "1.0", forRemoval = true)
    public void _funciona() {
        // Prueba de conexión
        try {
            if (this.conexion != null && !this.conexion.isClosed()) {
                log.debug("Conexión exitosa a la base de datos.");
            }
            else {
                log.warn("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (SQLException e) {
            log.error("Error al verificar la conexión: {}", e.getMessage());
        }
    }

    /**
     * Validar usuario en la base de datos
     *
     * @param email       email que se usará como identificador
     * @param contrasenha contraseña del usuario
     * @return Usuario objeto que representa al usuario validado
     */
    public Usuario validarUsuario(String email, String contrasenha) {
        return this.daoUsuario.validarUsuario(email, contrasenha);
    }

    /**
     * Obtiene una lista de estaciones
     *
     * @return Lista de estaciones
     */
    public List<Estacion> preguntaLasEstaciones() {
        return this.daoEstacion.preguntaLasEstaciones();
    }

    /**
     * Obtiene una lista de bicicletas para una estación
     *
     * @param estacionUsada Estación de la que se quieren obtener las bicicletas
     * @return Lista de bicicletas
     */
    public List<Bicicleta> obtenerBicisPorEstacion(Estacion estacionUsada) {
        return this.daoBicicleta.obtenerBicisPorEstacion(estacionUsada);

    }

    /**
     * Método que obtiene una lista de bicicletas en uso en una estación específica.
     *
     * @param estacionUsada La estación de la que se quieren obtener las bicicletas en uso.
     * @return Lista de bicicletas en uso en la estación especificada.
     */
    public List<Bicicleta> obtenerBicisEnUsoPorEstacion(Estacion estacionUsada) {
        return this.daoBicicleta.obtenerBicisEnUsoPorEstacion(estacionUsada);
    }

    /**
     * Método que comprueba si un usuario tiene una bicicleta en uso.
     *
     * @param usuarioLogado usuario a comprobar
     * @return true si el usuario tiene una bicicleta en uso, false en caso contrario
     */
    public boolean usuarioTieneBiciEnUso(Usuario usuarioLogado) {
        return this.daoUsuario.usuarioTieneBiciEnUso(usuarioLogado);
    }

    /**
     * Método que obtiene la bicicleta en reserva de un usuario.
     *
     * @param usuarioLogado usuario a comprobar
     * @return bicicleta del usuario
     */
    public Bicicleta obtenerBicicletaPorUsuario(Usuario usuarioLogado) {
        return this.daoUsuario.obtenerBicicletaPorUsuario(usuarioLogado);
    }

    /**
     * Método que deja una bicicleta en una estación específica.
     *
     * @param bicicleta            La bicicleta que se va a dejar en la estación.
     * @param estacionSeleccionada La estación en la que se va a dejar la bicicleta.
     */
    public void estacionarBicicleta(Bicicleta bicicleta, Estacion estacionSeleccionada) {
        this.daoBicicleta.estacionarBicicleta(bicicleta, estacionSeleccionada);
    }


    /**
     * Finaliza un viaje, actualizando la tabla viaje
     *
     * @param usuarioLogado        Usuario que ha iniciado sesión
     * @param bicicleta            Bicicleta que se va a devolver
     * @param estacionSeleccionada Estación a la que se va a devolver la bicicleta
     * @return true si la devolución se ha realizado correctamente, false en caso contrario
     */
    public boolean devolverBicicleta(Usuario usuarioLogado, Bicicleta bicicleta, Estacion estacionSeleccionada) {
        return this.daoViaje.devolverBicicleta(usuarioLogado, bicicleta, estacionSeleccionada);
    }

    /**
     * Este método cuenta el número de bicicletas libres (es decir, sin uso) por estación.
     *
     * @return Una lista de enteros que representa el número de bicicletas libres en cada estación.
     */
    public List<Integer> preguntaLasBicicletasPorEstacion() {
        return this.daoBicicleta.preguntaLasBicicletasPorEstacion();
    }

    /**
     * Método que reserva una bicicleta para un usuario específico.
     *
     * @param usuarioLogado      El usuario que está reservando la bicicleta.
     * @param bicicletaReservada La bicicleta que se va a reservar.
     * @return true si la reserva se realizó correctamente, false en caso contrario.
     */
    public boolean reservarBicicleta(Usuario usuarioLogado, Bicicleta bicicletaReservada) {
        return this.daoBicicleta.reservarBicicleta(usuarioLogado, bicicletaReservada);
    }

    /**
     * Devuelve la ocupacion de una estacion
     *
     * @param estacionSeleccionada estacion a consultar
     * @return ocupacion de la estacion
     */
    public int obtenerOcupacionEstacion(Estacion estacionSeleccionada) {
        return this.daoEstacion.obtenerOcupacionEstacion(estacionSeleccionada.idEstacion());
    }
}
