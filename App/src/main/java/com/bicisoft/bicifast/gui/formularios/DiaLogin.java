package com.bicisoft.bicifast.gui.formularios;

import com.bicisoft.bicifast.aplicacion.FachadaAplicacion;
import com.bicisoft.bicifast.aplicacion.TipoUsuario;
import com.bicisoft.bicifast.aplicacion.Usuario;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


import java.awt.*;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Diálogo que muestra el formulario de login.
 */

public final class DiaLogin extends JDialog {
    private final FachadaAplicacion          fa;
    private final ResourceBundle             textos;
    private       javax.swing.JLabel         lblLoginIncorrecto;
    private       javax.swing.JPasswordField pwdLogin;
    private       javax.swing.JTextPane      txtEmail;

    /**
     * Crea un nuevo dialogo de login.
     *
     * @param padre Ventana padre.
     * @param fa    Fachada de la aplicacion.
     */
    public DiaLogin(JFrame padre, FachadaAplicacion fa) {
        super(padre, true);
        this.fa = fa;
        this.textos = fa.pedirBundle();
        this.initComponents();
        this.lblLoginIncorrecto.setVisible(false);
        this.setTitle(this.textos.getString("login.titulo"));


        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // código generado por netbeans
    private void initComponents() {

        JPanel panelPrincipalLogin = new JPanel();
        this.txtEmail = new javax.swing.JTextPane();
        JLabel jLabel1 = new JLabel();

        JButton btnAceptar = new JButton();
        JButton btnSalir   = new JButton();
        this.pwdLogin = new javax.swing.JPasswordField();
        JLabel lblUsuario     = new JLabel();
        JLabel lblContrasenha = new JLabel();
        this.lblLoginIncorrecto = new javax.swing.JLabel();

        jLabel1.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(this.getClass().getResource("/imagenes/bicifast_logo.jpeg")))); // NOI18N
        jLabel1.setText("jLabel1");

        btnAceptar.setText(this.textos.getString("aceptar"));
        btnAceptar.addActionListener(this::btnAceptarActionPerformed);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(this::btnSalirActionPerformed);

        lblUsuario.setText("Usuario");

        lblContrasenha.setText("Contraseña");

        this.lblLoginIncorrecto.setForeground(new java.awt.Color(153, 0, 0));
        this.lblLoginIncorrecto.setText("jLabel2");

        GroupLayout panelPrincipalLoginLayout = new GroupLayout(panelPrincipalLogin);
        panelPrincipalLogin.setLayout(panelPrincipalLoginLayout);

        this.pwdLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    btnAceptarActionPerformed(null);
                }
            }
        });

        this.txtEmail.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    btnAceptarActionPerformed(null);
                }
            }
        });

// Configuración horizontal
        panelPrincipalLoginLayout.setHorizontalGroup(
                panelPrincipalLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelPrincipalLoginLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelPrincipalLoginLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        // Fila de usuario
                                        .addGroup(panelPrincipalLoginLayout.createSequentialGroup()
                                                .addComponent(lblUsuario)
                                                .addGap(15, 15, 15)
                                                .addComponent(this.txtEmail, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
                                        // Fila de contraseña
                                        .addGroup(panelPrincipalLoginLayout.createSequentialGroup()
                                                .addComponent(lblContrasenha)
                                                .addGap(15, 15, 15)
                                                .addComponent(this.pwdLogin, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
                                )
                                .addGap(15, 15, 15)
                                .addComponent(btnAceptar)
                                .addContainerGap(20, Short.MAX_VALUE))
                        // Título/Logo
                        .addGroup(panelPrincipalLoginLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(50, Short.MAX_VALUE))
                        // Mensaje de error y botón salir
                        .addGroup(panelPrincipalLoginLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(this.lblLoginIncorrecto, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSalir)
                                .addContainerGap())
        );

// Configuración vertical
        panelPrincipalLoginLayout.setVerticalGroup(
                panelPrincipalLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panelPrincipalLoginLayout.createSequentialGroup()
                                .addContainerGap()
                                // Logo/Título
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                // Campos de entrada y botón aceptar
                                .addGroup(panelPrincipalLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        // Grupo de campos
                                        .addGroup(panelPrincipalLoginLayout.createSequentialGroup()
                                                .addGroup(panelPrincipalLoginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblUsuario)
                                                        .addComponent(this.txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(15, 15, 15)
                                                .addGroup(panelPrincipalLoginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblContrasenha)
                                                        .addComponent(this.pwdLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                        // Botón aceptar alineado verticalmente con los campos
                                        .addComponent(btnAceptar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                // Mensaje de error y botón salir
                                .addGroup(panelPrincipalLoginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(this.lblLoginIncorrecto)
                                        .addComponent(btnSalir))
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        Container   contentPane = this.getContentPane();
        GroupLayout layout      = new GroupLayout(contentPane);
        contentPane.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(panelPrincipalLogin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(panelPrincipalLogin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
    }



    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        this._comprobarLoginCorrecto();
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }

    private void _comprobarLoginCorrecto() {
        TipoUsuario permisosDelUsuario = TipoUsuario.NO_DEFINIDO;

        Usuario usr = this.fa.comprobarLogin(this.txtEmail.getText(), new String(this.pwdLogin.getPassword()));

        if (usr != null) {
            permisosDelUsuario = usr.tipoUsuario();
        }

        System.out.println("Permisos del usuario: " + permisosDelUsuario.toString());
        if (permisosDelUsuario == TipoUsuario.NO_DEFINIDO) {
            JOptionPane.showMessageDialog(this, "Login incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
            this.lblLoginIncorrecto.setText("Contraseña/Usuario incorrectos");
            this.lblLoginIncorrecto.setVisible(true);
        }
        else {
            this.fa.estableceUsuarioLogado(usr);
            this.dispose();
        }

    }
}
