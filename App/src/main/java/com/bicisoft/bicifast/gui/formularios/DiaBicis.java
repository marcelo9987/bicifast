package com.bicisoft.bicifast.gui.formularios;

import com.bicisoft.bicifast.aplicacion.Bicicleta;
import com.bicisoft.bicifast.aplicacion.Estacion;
import com.bicisoft.bicifast.aplicacion.FachadaAplicacion;
import com.bicisoft.bicifast.gui.modelos.modeloListaBicicletas;
import com.bicisoft.bicifast.misc.Internacionalizacion;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Diálogo que muestra las bicicletas disponibles en una estación
 */

public final class DiaBicis extends JDialog {

    private final Estacion          estacionUsada;
    private final FachadaAplicacion fa;
    private final ResourceBundle    rb;
    private       JList<String>     jlistBicisDisponibles;
    private       JLabel            lblNombreEstacion;


    public DiaBicis(JFrame parent, boolean modal, FachadaAplicacion fa, Estacion estacion) {
        super(parent, modal);
        this.fa = fa;
        this.rb = fa.pedirBundle();
        this.estacionUsada = estacion;
        this.setLocationRelativeTo(null);
        this._lanzarInterfaz();

        String ubicacion = estacion.ubicacion();
        this.lblNombreEstacion.setText(ubicacion);

        this._listarBicicletas();
        this.pack();
    }

    private void _lanzarInterfaz() {

        JPanel      jpanPanelPrincipal = new JPanel();
        JScrollPane jScrollPane1       = new JScrollPane();
        this.jlistBicisDisponibles = new javax.swing.JList<>();
        JLabel lblEstacion = new JLabel();
        this.lblNombreEstacion = new javax.swing.JLabel();

        JButton btnActualizar = new JButton();
        JButton btnCancelar   = new JButton();

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.jlistBicisDisponibles.setModel(new modeloListaBicicletas());
        jScrollPane1.setViewportView(this.jlistBicisDisponibles);

        String estacion = this.rb.getString("estacion");
        lblEstacion.setText(estacion);

        this.lblNombreEstacion.setText("jLabel2");

        String actualizar = this.rb.getString("actualizar");
        btnActualizar.setText(actualizar);
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        String cancelar = this.rb.getString("cancelar");
        btnCancelar.setText(cancelar);
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        javax.swing.GroupLayout panelPrincipalLayout = new javax.swing.GroupLayout(jpanPanelPrincipal);
        jpanPanelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
                panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnCancelar))
                                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(lblEstacion)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(this.lblNombreEstacion)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                                .addComponent(btnActualizar)))
                                .addContainerGap())
        );
        panelPrincipalLayout.setVerticalGroup(
                panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPrincipalLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblEstacion)
                                        .addComponent(this.lblNombreEstacion))
                                .addGap(18, 18, 18)
                                .addGroup(panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 55, Short.MAX_VALUE))
                                        .addGroup(panelPrincipalLayout.createSequentialGroup()
                                                .addComponent(btnActualizar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnCancelar)))
                                .addContainerGap())
        );

        Container               contentPane = this.getContentPane();
        javax.swing.GroupLayout layout      = new javax.swing.GroupLayout(contentPane);
        contentPane.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jpanPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jpanPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        this.pack();
    }

    private void _listarBicicletas() {
        modeloListaBicicletas modelo                = (modeloListaBicicletas) this.jlistBicisDisponibles.getModel();
        List<Bicicleta>       bicicletasSinReservar = this.fa.obtenerBicisLibresPorEstacion(this.estacionUsada);
        modelo.setFilas(bicicletasSinReservar);

    }

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        this._listarBicicletas();
    }


    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }
}
