package basededatos;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;


public class FachadaBaseDatos {
    private java.sql.Connection conexion;

    public FachadaBaseDatos (){

        Properties configuracion = new Properties();
        FileInputStream arqConfiguracion;

        try {
            arqConfiguracion = new FileInputStream("App/baseDatos.properties");
            configuracion.load(arqConfiguracion);
            arqConfiguracion.close();

            Properties usuario = new Properties();


            String gestor = configuracion.getProperty("gestor");

            usuario.setProperty("user", configuracion.getProperty("usuario"));
            usuario.setProperty("password", configuracion.getProperty("clave"));
            this.conexion=java.sql.DriverManager.getConnection("jdbc:"+gestor+"://"+
                            configuracion.getProperty("servidor")+":"+
                            configuracion.getProperty("puerto")+"/"+
                            configuracion.getProperty("baseDatos"),
                    usuario);


        } catch (SQLException | IOException f){
            System.out.println("Estoy en el dir:"+ System.getProperty("user.dir"));
            System.out.println(f.getMessage());
        }


    }

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

}
