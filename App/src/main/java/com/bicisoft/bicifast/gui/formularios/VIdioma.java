/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.bicisoft.bicifast.gui.formularios;

import com.bicisoft.bicifast.aplicacion.EnumIdioma;
import com.bicisoft.bicifast.aplicacion.FachadaAplicacion;
import com.bicisoft.bicifast.gui.FachadaGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Diálogo que permite seleccionar el idioma de la aplicación.
 */

public final class VIdioma extends javax.swing.JDialog {
    //---- RECURSOS ----
    private final ResourceBundle rb;

    //---- FACHADAS ----
    private final FachadaAplicacion fa;
    private final FachadaGUI        fgui;

    //---- COMPONENTES ----
    private JComboBox<String> comboIdiomas;

    /**
     * Crea un nuevo diálogo de selección de idioma.
     *
     * @param parent Formulario padre, que puede ser null si no hay uno.
     * @param modal  Indica si el diálogo es modal, lo que significa que bloquea la interacción con otros diálogos hasta que se cierre.
     * @param fa     Fachada de la aplicación que proporciona acceso a los datos y funcionalidades.
     */
    public VIdioma(JFrame parent, boolean modal, FachadaAplicacion fa) {
        super(parent, modal);
        this.fgui = new FachadaGUI(fa);
        this.fa = fa;
        this.rb = fa.pedirBundle();
        this.setLocationRelativeTo(null);
        this.initComponents();

    }


    private void initComponents() {

        this.comboIdiomas = new JComboBox<>();

        JButton btnAceptar = new JButton();

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.comboIdiomas.setModel(new DefaultComboBoxModel<>(new String[]{this.rb.getString("espanol"), this.rb.getString("galego"), this.rb.getString("ingles")}));

        btnAceptar.setText(this.rb.getString("aceptar"));
        btnAceptar.addActionListener(this::actionPerformed);

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.comboIdiomas, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)).addGroup(layout.createSequentialGroup().addGap(31, 31, 31).addComponent(btnAceptar))).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.comboIdiomas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE).addComponent(btnAceptar).addContainerGap()));

        this.pack();
    }

    private void actionPerformed(ActionEvent e) {
        this.btnAceptarActionPerformed();
    }

    private void btnAceptarActionPerformed() {
        String idiomaSeleccionado;
        switch (this.comboIdiomas.getSelectedIndex()) {
            case 0:
                idiomaSeleccionado = "es";
                break;
            case 1:
                idiomaSeleccionado = "gl";
                break;
            case 2:
                idiomaSeleccionado = "en";
                break;
            default:
                return;
        }
        ResourceBundle bundleActual = this.fa.pedirBundle();
        EnumIdioma     idioma       = EnumIdioma.valueOfLabel(idiomaSeleccionado);
        if (bundleActual.getLocale().getLanguage().equals(idiomaSeleccionado)) {
            fgui.lanzarMensajeAviso(bundleActual.getString("el.idioma.ya.esta.seleccionado"));
        }
        else {
            this.fa.cambiarIdioma(idioma);
            JOptionPane.showMessageDialog(this, MessageFormat.format(bundleActual.getString("idioma.cambiado.a.0"), Objects.requireNonNull(idioma).nombreIdioma()));
            this.dispose();
        }

    }

}
