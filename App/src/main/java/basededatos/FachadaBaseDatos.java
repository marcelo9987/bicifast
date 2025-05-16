package basededatos;


import aplicacion.Bicicleta;
import aplicacion.Estacion;
import aplicacion.FachadaAplicacion;
import aplicacion.Usuario;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;


public class FachadaBaseDatos {

    private final DAOUsuario          daoUsuario;
    private final DAOEstacion         daoEstacion;
    private       java.sql.Connection conexion;
    private       FachadaAplicacion fa;
    private final DAOBicicleta      daoBicicleta;
    private final DAOViaje          daoViaje;

    public FachadaBaseDatos(FachadaAplicacion fa) {
        // ---- INICIALIZACIÓN DE VARIABLES ----
        this.fa = fa;
        // ---- CONEXIÓN A BB.DD. ----
        Properties      configuracion = new Properties();
        FileInputStream arqConfiguracion;

        try {
            arqConfiguracion = new FileInputStream("App/baseDatos.properties");
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


        } catch (SQLException | IOException f) {
            System.out.println("Estoy en el dir:" + System.getProperty("user.dir"));
            System.out.println(f.getMessage());
        }

        // ------ INICIALIZACIÓN DE CONECTORES A BBDD (D.A.O.) ------
        this.daoUsuario = new DAOUsuario(conexion, this.fa);
        this.daoEstacion = new DAOEstacion(conexion, this.fa);
        this.daoBicicleta = new DAOBicicleta(conexion, this.fa);
        this.daoViaje = new DAOViaje(conexion, this.fa);

    }

    /**
     * @hidden FUNCIONALIDAD DE PRUEBA
     * @deprecated {@since 1.0}
     */
    public void _funciona() {
        // Prueba de conexión
        try {
            if (conexion != null && !conexion.isClosed()) {
                System.out.println("Conexión exitosa a la base de datos.");
            }
            else {
                System.out.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar la conexión: " + e.getMessage());
        }
    }

    public Usuario validarUsuario(String email, String contrasenha) {
        return daoUsuario.validarUsuario(email, contrasenha);
    }

    public List<Estacion> preguntaLasEstaciones() {
        return daoEstacion.preguntaLasEstaciones();
    }

    public List<Bicicleta> obtenerBicisPorEstacion(Estacion estacionUsada) {
        return daoBicicleta.obtenerBicisPorEstacion(estacionUsada);

    }

    public List<Bicicleta> obtenerBicisEnUsoPorEstacion(Estacion estacionUsada) {
        return daoBicicleta.obtenerBicisEnUsoPorEstacion(estacionUsada);
    }

    public boolean usuarioTieneBiciEnUso(Usuario usuarioLogado) {
        return daoUsuario.usuarioTieneBiciEnUso(usuarioLogado);
    }

    public Bicicleta obtenerBicicletaPorUsuario(Usuario usuarioLogado) {
        return daoUsuario.obtenerBicicletaPorUsuario(usuarioLogado);
    }

    public void estacionarBicicleta(Bicicleta bicicleta, Estacion estacionSeleccionada) {
        daoBicicleta.estacionarBicicleta(bicicleta, estacionSeleccionada);
    }

    public void devolverBicicleta(Usuario usuarioLogado, Bicicleta bicicleta, Estacion estacionSeleccionada) {
        daoViaje.devolverBicicleta(usuarioLogado, bicicleta, estacionSeleccionada);
    }

    public List<Integer> preguntaLasBicicletasPorEstacion()
    {
        return daoBicicleta.preguntaLasBicicletasPorEstacion();
    }

    public boolean reservarBicicleta(Usuario usuarioLogado, Bicicleta bicicletaReservada)
    {
        return daoBicicleta.reservarBicicleta(usuarioLogado, bicicletaReservada);
    }

    public int obtenerOcupacionEstacion(Estacion estacionSeleccionada)
    {
        return daoEstacion.obtenerOcupacionEstacion(estacionSeleccionada.idEstacion());
    }
}
