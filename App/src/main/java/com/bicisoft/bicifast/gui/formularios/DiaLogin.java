package com.bicisoft.bicifast.gui.formularios;

import com.bicisoft.bicifast.aplicacion.FachadaAplicacion;
import com.bicisoft.bicifast.aplicacion.TipoUsuario;
import com.bicisoft.bicifast.aplicacion.Usuario;
import com.bicisoft.bicifast.gui.FachadaGUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Diálogo que muestra el formulario de login.
 */

public final class DiaLogin extends JDialog {
    //---- LOGGER ----
    private final static Logger logger = LoggerFactory.getLogger(DiaLogin.class);

    //----RECURSOS ----
    private final ResourceBundle textos;

    //---- FACHADAS ----
    private final FachadaAplicacion fa;
    private final FachadaGUI        fgui;

    //---- COMPONENTES ----
    private JLabel         lblLoginIncorrecto;
    private JPasswordField pwdLogin;
    private JTextPane      txtEmail;
    private JButton        btnAceptar;
    private JButton        btnSalir;

    /**
     * Crea un nuevo dialogo de login.
     *
     * @param padre Ventana padre.
     * @param fa    Fachada de la aplicacion.
     * @param fgui  Fachada de la GUI.
     */
    public DiaLogin(JFrame padre, FachadaAplicacion fa, FachadaGUI fgui) {
        super(padre, true);
        this.fa = fa;
        this.fgui = fgui;
        this.textos = fa.pedirBundle();
        this.initComponents();
        this.lblLoginIncorrecto.setVisible(false);
        this.setTitle(this.textos.getString("login.titulo"));


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // código generado por netbeans
    private void initComponents() {

        JPanel panelPrincipalLogin = new JPanel();
        this.txtEmail = new javax.swing.JTextPane();
        JLabel jLabel1 = new JLabel();

        this.btnAceptar = new JButton();
        this.btnSalir = new JButton();
        this.pwdLogin = new javax.swing.JPasswordField();
        JLabel lblUsuario     = new JLabel();
        JLabel lblContrasenha = new JLabel();
        this.lblLoginIncorrecto = new javax.swing.JLabel();

        jLabel1.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(this.getClass().getResource("/imagenes/bicifast_logo.jpeg")))); // NOI18

        this.btnAceptar.setText(this.textos.getString("aceptar"));
        this.btnAceptar.addActionListener(this::btnAceptarActionPerformed);

        this.btnSalir.setText("Salir");
        this.btnSalir.addActionListener(this::btnSalirActionPerformed);

        lblUsuario.setText("Usuario");
        lblUsuario.setMinimumSize(new java.awt.Dimension(60, 20));
        lblUsuario.setPreferredSize(new java.awt.Dimension(80, 25));

        lblContrasenha.setText("Contraseña");
        lblContrasenha.setMinimumSize(new java.awt.Dimension(80, 20));
        lblContrasenha.setPreferredSize(new java.awt.Dimension(100, 25));

        this.lblLoginIncorrecto.setForeground(new java.awt.Color(153, 0, 0));
        this.lblLoginIncorrecto.setText("jLabel2");

        GroupLayout panelPrincipalLoginLayout = new GroupLayout(panelPrincipalLogin);
        panelPrincipalLogin.setLayout(panelPrincipalLoginLayout);

        this.txtEmail.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER -> _comprobarLoginCorrecto();
                    case KeyEvent.VK_TAB -> {
                        String textoActual = txtEmail.getText();
                        if (textoActual.endsWith("\t")) {
                            txtEmail.setText(textoActual.substring(0, textoActual.length() - 1));
                        }
                        // Si se pulsa TAB:
                        pwdLogin.requestFocusInWindow();
                    }
                }
            }
        });

        this.txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (txtEmail.getText().endsWith("\t")) {
                    txtEmail.setText(txtEmail.getText().substring(0, txtEmail.getText().length() - 1));

                }

            }
        });

        this.pwdLogin.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    _comprobarLoginCorrecto();
                }
            }
        });

        // Desactivar o comportamento por defecto do TAB (traversal key) no campo pwdLogin
        Set<KeyStroke> empty = Collections.emptySet();
        this.pwdLogin.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, empty);

        // Sobrescribir o comportamento do TAB no campo de contrasinal
        InputMap  im = this.pwdLogin.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap am = this.pwdLogin.getActionMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "moveToAceptar");
        am.put("moveToAceptar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DiaLogin.logger.debug("Se ha pulsado TAB en el campo de contraseña (InputMap)");
                btnAceptar.requestFocusInWindow();
            }
        });


        this.btnAceptar.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, empty);
        this.btnSalir.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, empty);

        // TAB en btnAceptar -> btnSalir
        InputMap  imAceptar = this.btnAceptar.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap amAceptar = this.btnAceptar.getActionMap();
        imAceptar.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "moveToSalir");
        amAceptar.put("moveToSalir", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSalir.requestFocusInWindow();
            }
        });

        // TAB en btnSalir -> txtEmail
        InputMap  imSalir = this.btnSalir.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap amSalir = this.btnSalir.getActionMap();
        imSalir.put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "moveToEmail");
        amSalir.put("moveToEmail", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtEmail.requestFocusInWindow();
            }
        });

// Configuración horizontal
        panelPrincipalLoginLayout.setHorizontalGroup(panelPrincipalLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(panelPrincipalLoginLayout.createSequentialGroup().addContainerGap().addGroup(panelPrincipalLoginLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        // Fila de usuario
                        .addGroup(panelPrincipalLoginLayout.createSequentialGroup().addComponent(lblUsuario).addGap(15, 15, 15).addComponent(this.txtEmail, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                        // Fila de contraseña
                        .addGroup(panelPrincipalLoginLayout.createSequentialGroup().addComponent(lblContrasenha).addGap(15, 15, 15).addComponent(this.pwdLogin, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))).addGap(15, 15, 15).addComponent(this.btnAceptar).addContainerGap(20, Short.MAX_VALUE))
                // Título/Logo
                .addGroup(panelPrincipalLoginLayout.createSequentialGroup().addGap(50, 50, 50).addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE).addContainerGap(50, Short.MAX_VALUE))
                // Mensaje de error y botón salir
                .addGroup(panelPrincipalLoginLayout.createSequentialGroup().addContainerGap().addComponent(this.lblLoginIncorrecto, 250, 250, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(this.btnSalir).addContainerGap()));

// Configuración vertical
        panelPrincipalLoginLayout.setVerticalGroup(panelPrincipalLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(panelPrincipalLoginLayout.createSequentialGroup().addContainerGap()
                // Logo/Título
                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE).addGap(30, 30, 30)
                // Campos de entrada y botón aceptar
                .addGroup(panelPrincipalLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        // Grupo de campos
                        .addGroup(panelPrincipalLoginLayout.createSequentialGroup().addGroup(panelPrincipalLoginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblUsuario).addComponent(this.txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(15, 15, 15).addGroup(panelPrincipalLoginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblContrasenha).addComponent(this.pwdLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                        // Botón aceptar alineado verticalmente con los campos
                        .addComponent(this.btnAceptar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)).addGap(15, 15, 15)
                // Mensaje de error y botón salir
                .addGroup(panelPrincipalLoginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.lblLoginIncorrecto).addComponent(this.btnSalir)).addContainerGap(20, Short.MAX_VALUE)));
        this._configurarPanel(panelPrincipalLogin);
    }

    private void btnAceptarActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // Si es tabulador, saltamos a salir
        if ((evt.getSource() == this.pwdLogin && (evt.getModifiers() & (InputEvent.SHIFT_DOWN_MASK | InputEvent.ALT_DOWN_MASK)) != 0)) {
            this.btnSalir.requestFocusInWindow();
            return;
        }
        this._comprobarLoginCorrecto();
    }

    private void btnSalirActionPerformed(ActionEvent evt) {
        // Si es tabulador, volvemos al email
        if (evt.getSource() == this.btnSalir
                && (evt.getModifiers() & (InputEvent.SHIFT_DOWN_MASK | InputEvent.ALT_DOWN_MASK)) != 0) {
            this.txtEmail.requestFocusInWindow();
            return;
        }
        System.exit(0);
    }

    private void _comprobarLoginCorrecto() {
        TipoUsuario permisosDelUsuario = TipoUsuario.NO_DEFINIDO;

        Usuario usr = this.fa.comprobarLogin(this.txtEmail.getText(), new String(this.pwdLogin.getPassword()));

        if (usr != null) {
            permisosDelUsuario = usr.tipoUsuario();
        }

        DiaLogin.logger.info("Permisos del usuario:{}", permisosDelUsuario.toString());
        if (TipoUsuario.NO_DEFINIDO == permisosDelUsuario) {
//            JOptionPane.showMessageDialog(this, "Login incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
            this.fgui.lanzarMensajeAviso(this.textos.getString("login.incorrecto"));
            this.lblLoginIncorrecto.setText("Contraseña/Usuario incorrectos");
            this.lblLoginIncorrecto.setVisible(true);
        }
        else {
            this.fa.estableceUsuarioLogado(usr);
            this.dispose();
        }

    }

    private void _configurarPanel(JPanel panelPrincipalLogin) {
        Container   contentPane = this.getContentPane();
        GroupLayout layout      = new GroupLayout(contentPane);
        contentPane.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(panelPrincipalLogin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(panelPrincipalLogin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
    }
}
