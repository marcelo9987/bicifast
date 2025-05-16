package aplicacion;

import java.sql.Date;


public final class Usuario {
    private final int         idUsuario;
    private final String      nombre;
    private final String      apellido1;
    private final String      apellido2;
    private final String      dni;
    private final String      email;
    private final String      direccion;
    private final Date        fecha_nacimiento;
    private final String      telefono;
    private final String      contrasenha;
    private final MetodoPago  metodoPago;
    private final Date        inicio_suscripcion;
    private final Date        fin_suscripcion;
    private final TipoUsuario tipoUsuario;

    public Usuario(int id, String nombre, String apellido1, String apellido2, String dni, String email, String direccion, Date fecha_nacimiento, String telefono, String contrasenha, MetodoPago metodoPago, Date inicio_suscripcion, Date fin_suscripcion, TipoUsuario tipoUsuario) {
        this.idUsuario = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dni = dni;
        this.email = email;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono = telefono;
        this.contrasenha = contrasenha;
        this.metodoPago = metodoPago;
        this.inicio_suscripcion = inicio_suscripcion;
        this.fin_suscripcion = fin_suscripcion;
        this.tipoUsuario = tipoUsuario;
    }

    public String apellido1() {
        return apellido1;
    }

    public String apellido2() {
        return apellido2;
    }

    public String contrasenha() {
        return contrasenha;
    }

    public String direccion() {
        return direccion;
    }

    public String dni() {
        return dni;
    }

    public String email() {
        return email;
    }

    public Date fecha_nacimiento() {
        return fecha_nacimiento;
    }

    public Date fin_suscripcion() {
        return fin_suscripcion;
    }

    public int idUsuario() {
        return idUsuario;
    }

    public Date inicio_suscripcion() {
        return inicio_suscripcion;
    }

    public MetodoPago metodoPago() {
        return metodoPago;
    }

    public String nombre() {
        return nombre;
    }

    public String telefono() {
        return telefono;
    }

    public TipoUsuario tipoUsuario() {
        return tipoUsuario;
    }
}
