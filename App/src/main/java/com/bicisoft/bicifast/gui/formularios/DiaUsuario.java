package com.bicisoft.bicifast.gui.formularios;

import com.bicisoft.bicifast.aplicacion.FachadaAplicacion;
import com.bicisoft.bicifast.aplicacion.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public final class DiaUsuario extends JDialog {

    private final Usuario            usuario;
    private final ResourceBundle     textos;
    private       JPanel             panel;
    private       GridBagConstraints gbc;
    private       int                fila;

    public DiaUsuario(JFrame parent, FachadaAplicacion fa) {
        super(parent, fa.pedirBundle().getString("datos.do.usuario"), true);
        this.usuario = fa.usuario();
        this.textos = fa.pedirBundle();
        this.inicializarFormulario();
        this.setResizable(false);
        this.pack();
    }


    private void inicializarFormulario() {
        this.panel = new JPanel();
        this.panel.setLayout(new GridBagLayout());
        this.gbc = new GridBagConstraints();
        this.gbc.insets = new Insets(5, 10, 5, 10);
        this.gbc.anchor = GridBagConstraints.WEST;
        this.fila = 0;

        this.addRow(this.textos.getString("nombre"), this.usuario.nombre());
        this.addRow(this.textos.getString("primer.apellido"), this.usuario.apellido1());
        this.addRow(this.textos.getString("segundo.apellido"), this.usuario.apellido2());
        this.addRow("DNI", this.usuario.dni());
        this.addRow(this.textos.getString("email"), this.usuario.email());
        this.addRow(this.textos.getString("direccion"), this.usuario.direccion());
        this.addRow(this.textos.getString("data.de.nacimiento"), this.usuario.fecha_nacimiento().toString());
        this.addRow(this.textos.getString("telefono"), this.usuario.telefono());
        this.addRow(this.textos.getString("metodo.de.pago"), this.usuario.metodoPago().toString());
        this.addRow(this.textos.getString("inicio.subscricion"), this.usuario.inicio_suscripcion().toString());
        this.addRow(this.textos.getString("fin.subscricion"), this.usuario.fin_suscripcion().toString());
        this.addRow(this.textos.getString("tipo.usuario"), this.usuario.tipoUsuario().toString());


        this.gbc.gridy = this.fila;
        this.gbc.gridx = 0;
        this.gbc.gridwidth = 2;
        this.gbc.anchor = GridBagConstraints.CENTER;
        JButton btnCerrar = new JButton(this.textos.getString("cerrar"));
        btnCerrar.addActionListener(this::actionPerformed);
        this.panel.add(btnCerrar, this.gbc);

        this.add(this.panel);
    }

    // Pruebo a hacer a mano el formulario
    private void addRow(String label, String valor) {
        this.gbc.gridy = this.fila++;

        this.gbc.gridx = 0;
        JLabel lbl = new JLabel(label + ":");
        this.panel.add(lbl, this.gbc);

        this.gbc.gridx = 1;
        JTextField txt = new JTextField(valor);
        txt.setEditable(false);
        txt.setColumns(20);
        this.panel.add(txt, this.gbc);
    }

    private void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}

