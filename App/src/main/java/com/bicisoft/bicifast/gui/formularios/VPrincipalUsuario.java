/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.bicisoft.bicifast.gui.formularios;

import com.bicisoft.bicifast.aplicacion.Estacion;
import com.bicisoft.bicifast.aplicacion.FachadaAplicacion;
import com.bicisoft.bicifast.gui.FachadaGUI;
import com.bicisoft.bicifast.gui.modelos.modeloTablaEstaciones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Vista principal del usuario.
 */
public final class VPrincipalUsuario extends JFrame {

    private final ResourceBundle    idioma;
    private final FachadaAplicacion fa;
    private final FachadaGUI       fgui;
    private       JButton           btnDevolverBici;
    private       JCheckBox         chkBiciEnUso;
    private       JTable            tablaEstaciones;

    /**
     * Crea una nueva instancia de la clase VPrincipalUsuario.
     *
     * @param fa Fachada de la aplicación.
     */
    public VPrincipalUsuario(FachadaAplicacion fa, FachadaGUI fgui) {
        super();
        this.fa = fa;
        this.fgui = fgui;
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

        JPanel  jPanel1              = new JPanel();
        JButton btnConfig            = new JButton();
        JButton btnPerfil            = new JButton();
        JLabel  lblListaDeEstaciones = new JLabel();
        JButton btnSalir             = new JButton();
        this.btnDevolverBici = new JButton();
        this.chkBiciEnUso = new JCheckBox();
        JButton     btnBicisEstacion = new JButton();
        JScrollPane jScrollPane2     = new JScrollPane();
        this.tablaEstaciones = new JTable();

        JButton btnActualizar        = new JButton();
        JButton btnReservarBicicleta = new JButton();

        this.setTitle("BiciFast");

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        btnConfig.setText(this.idioma.getString("configuracion"));
        btnConfig.setMaximumSize(new Dimension(175, 40));
        btnConfig.setMinimumSize(new Dimension(100, 28));
        btnConfig.setPreferredSize(new Dimension(150, 40));
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

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);

        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnConfig, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnPerfil)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblListaDeEstaciones, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(this.chkBiciEnUso))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnActualizar)
                                                        .addComponent(btnReservarBicicleta)
                                                        .addComponent(btnBicisEstacion)
                                                        .addComponent(this.btnDevolverBici))
                                                .addGap(30, 30, 30))))
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSalir)
                                .addContainerGap())
        );

        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnConfig, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnPerfil))
                                .addGap(18, 18, 18)
                                .addComponent(lblListaDeEstaciones)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnActualizar)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnReservarBicicleta)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnBicisEstacion)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(this.btnDevolverBici)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(this.chkBiciEnUso)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSalir)
                                .addContainerGap())
        );

        Container               contentPane = this.getContentPane();
        GroupLayout layout      = new GroupLayout(contentPane);
        contentPane.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        this.pack();
    }

    private void _gestionarBicicletaEnUso() {
        boolean usuarioTieneBici = this.fa.usuarioTieneBici();
        if (usuarioTieneBici) {
            this.btnDevolverBici.setEnabled(true);
            this.chkBiciEnUso.setSelected(true);



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

    private void btnConfigActionPerformed(ActionEvent evt) {
        this.fgui.lanzarSeleccionIdioma(this);
    }

    private void btnPerfilActionPerformed(ActionEvent evt) {
        this.fgui.lanzarPerfilUsuario(this);
    }

    private void btnSalirActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnDevolverBiciActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnDevolverBiciActionPerformed
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
    }

    private void btnBicisEstacionActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnBicisEstacionActionPerformed
        // TODO add your handling code here:
        int index = this.tablaEstaciones.getSelectedRow();
        if (-1 == index) {
            JOptionPane.showMessageDialog(this, this.idioma.getString("debes.seleccionar.una.estacion"));
            return;
        }

        Estacion estacionSeleccionada = ((modeloTablaEstaciones) this.tablaEstaciones.getModel()).obtenerEstacion(index);

        this.fgui.lanzarMenuBicis(this, estacionSeleccionada);

    }

    private void btnActualizarActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        this._listarEstaciones();
        this._gestionarBicicletaEnUso();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnReservarBicicletaActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnReservarBicicletaActionPerformed
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
