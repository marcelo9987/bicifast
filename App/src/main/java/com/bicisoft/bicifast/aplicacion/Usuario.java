package com.bicisoft.bicifast.aplicacion;

import java.sql.Date;


/**
 * Clase que representa un usuario.
 */
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

    /**
     * Constructor de la clase Usuario
     *
     * @param id                 Id del usuario
     * @param nombre             Nombre del usuario
     * @param apellido1          Primer apellido del usuario
     * @param apellido2          Segundo apellido del usuario
     * @param dni                DNI del usuario
     * @param email              Email del usuario
     * @param direccion          Dirección del usuario
     * @param fecha_nacimiento   Fecha de nacimiento del usuario
     * @param telefono           Teléfono del usuario
     * @param contrasenha        Contraseña del usuario
     * @param metodoPago         Método de pago del usuario
     * @param inicio_suscripcion Fecha de inicio de la suscripción del usuario
     * @param fin_suscripcion    Fecha de fin de la suscripción del usuario
     * @param tipoUsuario        Tipo de usuario
     */
    public Usuario(int id, String nombre, String apellido1, String apellido2, String dni, String email, String direccion, Date fecha_nacimiento, String telefono, String contrasenha, MetodoPago metodoPago, Date inicio_suscripcion, Date fin_suscripcion, TipoUsuario tipoUsuario) {
        super();
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Usuario{");
        sb.append("apellido1='").append(apellido1).append('\'');
        sb.append(", idUsuario=").append(idUsuario);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", apellido2='").append(apellido2).append('\'');
        sb.append(", dni='").append(dni).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", direccion='").append(direccion).append('\'');
        sb.append(", fecha_nacimiento=").append(fecha_nacimiento);
        sb.append(", telefono='").append(telefono).append('\'');
        sb.append(", contrasenha='").append(contrasenha).append('\'');
        sb.append(", metodoPago=").append(metodoPago);
        sb.append(", inicio_suscripcion=").append(inicio_suscripcion);
        sb.append(", fin_suscripcion=").append(fin_suscripcion);
        sb.append(", tipoUsuario=").append(tipoUsuario);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Getter del primer apellido del usuario.
     * @return Primer apellido del usuario
     */
    public String apellido1() {
        return this.apellido1;
    }

    /**
     * Getter del segundo apellido del usuario.
     * @return Segundo apellido del usuario
     */
    public String apellido2() {
        return this.apellido2;
    }

    /**
     * Getter de la contraseña del usuario.
     * @return Contraseña del usuario
     */
    public String contrasenha() {
        return this.contrasenha;
    }

    /**
     * Getter de la dirección del usuario.
     * @return Dirección del usuario
     */
    public String direccion() {
        return this.direccion;
    }

    /**
     * Getter del DNI del usuario.
     * @return DNI del usuario
     */
    public String dni() {
        return this.dni;
    }

    /**
     * Getter del email del usuario.
     * @return Email del usuario
     */
    public String email() {
        return this.email;
    }

    /**
     * Getter de la fecha de nacimiento del usuario.
     * @return Fecha de nacimiento del usuario
     */
    public Date fecha_nacimiento() {
        return this.fecha_nacimiento;
    }

    /**
     * Getter de la fecha de fin de suscripción del usuario.
     * @return Fecha de fin de suscripción del usuario
     */
    public Date fin_suscripcion() {
        return this.fin_suscripcion;
    }

    /**
     * Getter de la id del usuario.
     * @return Id del usuario
     */
    public int idUsuario() {
        return this.idUsuario;
    }

    /**
     * Getter de la fecha de inicio de suscripción del usuario.
     * @return Fecha de inicio de suscripción del usuario
     */
    public Date inicio_suscripcion() {
        return this.inicio_suscripcion;
    }

    /**
     * Getter del método de pago del usuario.
     * @return Método de pago del usuario
     */
    public MetodoPago metodoPago() {
        return this.metodoPago;
    }

    /**
     * Getter del nombre del usuario.
     * @return Nombre del usuario
     */
    public String nombre() {
        return this.nombre;
    }

    /**
     * Getter del teléfono del usuario.
     * @return Teléfono del usuario
     */
    public String telefono() {
        return this.telefono;
    }

    /**
     * Getter del tipo de usuario.
     * @return Tipo de usuario
     */
    public TipoUsuario tipoUsuario() {
        return this.tipoUsuario;
    }
}
