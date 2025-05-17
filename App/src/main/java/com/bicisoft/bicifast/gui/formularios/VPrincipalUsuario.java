/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.bicisoft.bicifast.gui.formularios;

import com.bicisoft.bicifast.aplicacion.Estacion;
import com.bicisoft.bicifast.aplicacion.FachadaAplicacion;
import com.bicisoft.bicifast.gui.modelos.modeloTablaEstaciones;

import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Vista principal del usuario.
 */
public final class VPrincipalUsuario extends javax.swing.JFrame {

    private final ResourceBundle    idioma;
    private final FachadaAplicacion fa;
    private       JButton           btnDevolverBici;
    private       JCheckBox         chkBiciEnUso;
    private       JTable            tablaEstaciones;

    /**
     * Crea una nueva instancia de la clase VPrincipalUsuario.
     *
     * @param fa Fachada de la aplicación.
     */
    public VPrincipalUsuario(FachadaAplicacion fa) {
        super();
        this.fa = fa;
        this.idioma = fa.pedirBundle();
        this.initComponents();
        this._gestionarBicicletaEnUso();
        this._listarEstaciones();
        this.pack();
    }


    /**
     * Inicializa los componentes de la interfaz gráfica.
     */
    
    private void initComponents() {

        javax.swing.JPanel  jPanel1              = new javax.swing.JPanel();
        javax.swing.JButton btnConfig            = new javax.swing.JButton();
        javax.swing.JButton btnPerfil            = new javax.swing.JButton();
        javax.swing.JLabel  lblListaDeEstaciones = new javax.swing.JLabel();
        javax.swing.JButton btnSalir             = new javax.swing.JButton();
        this.btnDevolverBici = new javax.swing.JButton();
        this.chkBiciEnUso = new javax.swing.JCheckBox();
        javax.swing.JButton     btnBicisEstacion = new javax.swing.JButton();
        javax.swing.JScrollPane jScrollPane2     = new javax.swing.JScrollPane();
        this.tablaEstaciones = new javax.swing.JTable();
        // Variables declaration - do not modify//GEN-BEGIN:variables
        javax.swing.JButton btnActualizar        = new javax.swing.JButton();
        javax.swing.JButton btnReservarBicicleta = new javax.swing.JButton();

        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnConfig.setText(this.idioma.getString("configuracion"));
        btnConfig.setMaximumSize(new java.awt.Dimension(130, 28));
        btnConfig.setMinimumSize(new java.awt.Dimension(130, 28));
        btnConfig.setPreferredSize(new java.awt.Dimension(130, 28));
        btnConfig.addActionListener(this::btnConfigActionPerformed);

        btnPerfil.setText(this.idioma.getString("perfil"));
        btnPerfil.addActionListener(this::btnPerfilActionPerformed);

        lblListaDeEstaciones.setText(this.idioma.getString("lista.de.estaciones"));

        btnSalir.setText(this.idioma.getString("Salir"));
        btnSalir.addActionListener(this::btnSalirActionPerformed);

        this.btnDevolverBici.setText(this.idioma.getString("devolver.bicicleta"));
        this.btnDevolverBici.addActionListener(this::btnDevolverBiciActionPerformed);

        this.chkBiciEnUso.setText(this.idioma.getString("bicicleta.en.uso"));
        this.chkBiciEnUso.setEnabled(false);

        btnBicisEstacion.setText(this.idioma.getString("bicicletas"));
        btnBicisEstacion.addActionListener(this::btnBicisEstacionActionPerformed);

        this.tablaEstaciones.setModel(new modeloTablaEstaciones());
        jScrollPane2.setViewportView(this.tablaEstaciones);

        btnActualizar.setText(this.idioma.getString("actualizar"));
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        btnReservarBicicleta.setText(this.idioma.getString("reservar"));
        btnReservarBicicleta.addActionListener(this::btnReservarBicicletaActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(btnConfig, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(btnPerfil).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addGroup(jPanel1Layout.createSequentialGroup().addGap(77, 107, Short.MAX_VALUE).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.btnDevolverBici).addGap(113, 113, 113).addComponent(this.chkBiciEnUso).addGap(45, 45, 45)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))))).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(btnBicisEstacion).addComponent(btnActualizar).addComponent(btnReservarBicicleta)).addGap(54, 54, 54)).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(btnSalir).addContainerGap()).addGroup(jPanel1Layout.createSequentialGroup().addGap(253, 253, 253).addComponent(lblListaDeEstaciones).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(btnConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(btnPerfil)).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(jPanel1Layout.createSequentialGroup().addGap(55, 55, 55).addComponent(btnActualizar).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(btnReservarBicicleta).addGap(15, 15, 15).addComponent(btnBicisEstacion).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(btnSalir).addContainerGap()).addGroup(jPanel1Layout.createSequentialGroup().addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE).addComponent(lblListaDeEstaciones).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(this.btnDevolverBici).addComponent(this.chkBiciEnUso)).addGap(77, 77, 77)))));

        Container               contentPane = this.getContentPane();
        javax.swing.GroupLayout layout      = new javax.swing.GroupLayout(contentPane);
        contentPane.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        this.pack();
    }// </editor-fold>//GEN-END:initComponents

    private void _gestionarBicicletaEnUso() {
        boolean usuarioTieneBici = this.fa.usuarioTieneBici();
        if (usuarioTieneBici) {
            this.btnDevolverBici.setEnabled(true);
            this.chkBiciEnUso.setSelected(true);
//            chkBiciEnUso.set


        }
        else {
            this.chkBiciEnUso.setSelected(false);
            this.btnDevolverBici.setEnabled(false);
        }
    }

    private void _listarEstaciones() {
        modeloTablaEstaciones modelo = (modeloTablaEstaciones) this.tablaEstaciones.getModel();
        modelo.setFilas(this.fa.preguntaLasEstaciones(), this.fa.preguntaLasBicicletasPorEstacion());
    }

    private void btnConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigActionPerformed
        // lanzo la interfaz de sel. de idioma
        VIdioma idioma = new VIdioma(this, true, this.fa);
        idioma.setLocationRelativeTo(null);
        idioma.pack();
        idioma.setVisible(true);

    }//GEN-LAST:event_btnConfigActionPerformed

    private void btnPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilActionPerformed
        DiaUsuario menuUsuario = new DiaUsuario(this, this.fa);
        menuUsuario.setLocationRelativeTo(null);
        menuUsuario.setVisible(true);

    }//GEN-LAST:event_btnPerfilActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnDevolverBiciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolverBiciActionPerformed
        int filaSeleccionada = this.tablaEstaciones.getSelectedRow();
        if (-1 == filaSeleccionada) {
            JOptionPane.showMessageDialog(this, this.idioma.getString("debes.seleccionar.una.estacion"));
            return;
        }

        Estacion estacionSeleccionada = ((modeloTablaEstaciones) this.tablaEstaciones.getModel()).obtenerEstacion(filaSeleccionada);
        if (!this.fa.devolverBicicleta(estacionSeleccionada)) {
            JOptionPane.showMessageDialog(this, this.idioma.getString("el.aforo.de.la.estacion.no.es.suficiente.para.devolver.la.bicicleta"));
        }

        this._listarEstaciones();
        this._gestionarBicicletaEnUso();
    }//GEN-LAST:event_btnDevolverBiciActionPerformed

    private void btnBicisEstacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBicisEstacionActionPerformed
        // TODO add your handling code here:
        int index = this.tablaEstaciones.getSelectedRow();
        if (-1 == index) {
            JOptionPane.showMessageDialog(this, this.idioma.getString("debes.seleccionar.una.estacion"));
            return;
        }

        Estacion estacionSeleccionada = ((modeloTablaEstaciones) this.tablaEstaciones.getModel()).obtenerEstacion(index);
        DiaBicis menuBicis            = new DiaBicis(this, true, this.fa, estacionSeleccionada);
        menuBicis.setVisible(true);
        menuBicis.pack();
        menuBicis.setLocationRelativeTo(null);

    }//GEN-LAST:event_btnBicisEstacionActionPerformed
    // End of variables declaration//GEN-END:variables

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        this._listarEstaciones();
        this._gestionarBicicletaEnUso();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnReservarBicicletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservarBicicletaActionPerformed
        int filaSeleccionada = this.tablaEstaciones.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, this.idioma.getString("debes.seleccionar.una.estacion"));
            return;
        }
        boolean usuarioTieneBici = this.fa.usuarioTieneBici();

        if (usuarioTieneBici) {
            JOptionPane.showMessageDialog(this, this.idioma.getString("ya.tienes.una.bicicleta.reservada") + this.idioma.getString("por.favor.devuelvela.antes.de.reservar.otra"));
            return;
        }

        // En este punto, ya sabemos que el usuario no tiene una bicicleta en uso

        Estacion estacionSeleccionada = ((modeloTablaEstaciones) this.tablaEstaciones.getModel()).obtenerEstacion(filaSeleccionada);
        int      bicicletaAsignada    = this.fa.reservarBicicleta(estacionSeleccionada);
        if (bicicletaAsignada == -1) {
            JOptionPane.showMessageDialog(this, this.idioma.getString("no.hay.bicicletas.disponibles.en.esta.estacion"));
        }
        else {
            JOptionPane.showMessageDialog(this, MessageFormat.format(this.idioma.getString("bicicleta.numero.0.reservada"), bicicletaAsignada));
        }
        this._listarEstaciones();
        this._gestionarBicicletaEnUso();
    }//GEN-LAST:event_btnReservarBicicletaActionPerformed

}
