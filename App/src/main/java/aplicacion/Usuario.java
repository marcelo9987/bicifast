package aplicacion;

import java.sql.Date;
import java.util.StringJoiner;

public final class Usuario {
    private String      nombre;
    private String      apellido1;
    private String      apellido2;
    private String      dni;
    private String      email;
    private String      direccion;
    private Date        fecha_nacimiento;
    private String      telefono;
    private String      contrasenha;
    private MetodoPago  metodoPago;
    private Date        inicio_suscripcion;
    private Date        fin_suscripcion;
    private TipoUsuario tipoUsuario;

    public Usuario(String nombre, String apellido1, String apellido2, String dni, String email, String direccion, Date fecha_nacimiento, String telefono, String contrasenha, MetodoPago metodoPago, Date inicio_suscripcion, Date fin_suscripcion,  TipoUsuario tipoUsuario) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.contrasenha = contrasenha;
        this.direccion = direccion;
        this.dni = dni;
        this.email = email;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fin_suscripcion = fin_suscripcion;
        this.inicio_suscripcion = inicio_suscripcion;
        this.metodoPago = metodoPago;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Usuario.class.getSimpleName() + "[", "]")
                .add("apellido1='" + apellido1 + "'")
                .add("nombre='" + nombre + "'")
                .add("apellido2='" + apellido2 + "'")
                .add("dni='" + dni + "'")
                .add("email='" + email + "'")
                .add("direccion='" + direccion + "'")
                .add("fecha_nacimiento=" + fecha_nacimiento)
                .add("telefono='" + telefono + "'")
                .add("contrasenha='" + contrasenha + "'")
                .add("metodoPago=" + metodoPago)
                .add("inicio_suscripcion=" + inicio_suscripcion)
                .add("fin_suscripcion=" + fin_suscripcion)
                .add("tipoUsuario=" + tipoUsuario)
                .toString();
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
