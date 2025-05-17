/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.bicisoft.bicifast.gui.formularios;

import com.bicisoft.bicifast.aplicacion.EnumIdioma;
import com.bicisoft.bicifast.aplicacion.FachadaAplicacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Diálogo que permite seleccionar el idioma de la aplicación.
 */

final class VIdioma extends javax.swing.JDialog {
    private final FachadaAplicacion             fa;
    private final ResourceBundle                rb;
    private       javax.swing.JComboBox<String> jComboBox1;

    /**
     * Crea un nuevo diálogo de selección de idioma.
     */
    VIdioma(JFrame parent, boolean modal, FachadaAplicacion fa) {
        super(parent, modal);
        this.fa = fa;
        this.rb = fa.pedirBundle();
        this.initComponents();

    }


    private void initComponents() {

        this.jComboBox1 = new javax.swing.JComboBox<>();

        JButton btnAceptar = new JButton();

        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        this.jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{this.rb.getString("espanol"), this.rb.getString("galego"), this.rb.getString("ingles")}));

        btnAceptar.setText(this.rb.getString("aceptar"));
        btnAceptar.addActionListener(this::actionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(layout.createSequentialGroup().addGap(31, 31, 31).addComponent(btnAceptar))).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE).addComponent(btnAceptar).addContainerGap()));

        this.pack();
    }

    private void btnAceptarActionPerformed() {
        String idiomaSeleccionado;
        switch (this.jComboBox1.getSelectedIndex()) {
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
            JOptionPane.showMessageDialog(this, bundleActual.getString("el.idioma.ya.esta.seleccionado"));
        }
        else {
            this.fa.cambiarIdioma(idioma);
            JOptionPane.showMessageDialog(this, MessageFormat.format(bundleActual.getString("idioma.cambiado.a.0"), Objects.requireNonNull(idioma).nombreIdioma()));
            this.dispose();
        }

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void actionPerformed(ActionEvent e) {
        this.btnAceptarActionPerformed();
    }

}
