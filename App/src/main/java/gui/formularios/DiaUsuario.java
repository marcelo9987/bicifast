package gui.formularios;
import aplicacion.FachadaAplicacion;

import aplicacion.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public final class DiaUsuario extends JDialog {

    private final Usuario            usuario;
    private       JPanel             panel;
    private       GridBagConstraints gbc;
    private       int                fila;
    private final ResourceBundle     textos;

    DiaUsuario(JFrame parent, FachadaAplicacion fa) {
        super(parent, fa.pedirBundle().getString("datos.do.usuario"), true);
        this.usuario = fa.usuario();
        this.textos = fa.pedirBundle();
        inicializarFormulario();
        this.setResizable(false);
        this.pack();
    }


    private void inicializarFormulario() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        fila = 0;

        addRow(textos.getString("nombre"), usuario.nombre());
        addRow(textos.getString("primer.apellido"), usuario.apellido1());
        addRow(textos.getString("segundo.apellido"), usuario.apellido2());
        addRow("DNI", usuario.dni());
        addRow(textos.getString("email"), usuario.email());
        addRow(textos.getString("direccion"), usuario.direccion());
        addRow(textos.getString("data.de.nacimiento"), usuario.fecha_nacimiento().toString());
        addRow(textos.getString("telefono"), usuario.telefono());
        addRow(textos.getString("metodo.de.pago"), usuario.metodoPago().toString());
        addRow(textos.getString("inicio.subscricion"), usuario.inicio_suscripcion().toString());
        addRow(textos.getString("fin.subscricion"), usuario.fin_suscripcion().toString());
        addRow(textos.getString("tipo.usuario"), usuario.tipoUsuario().toString());


        gbc.gridy = fila;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton btnCerrar = new JButton(textos.getString("cerrar"));
        btnCerrar.addActionListener(e -> dispose());
        panel.add(btnCerrar, gbc);

        this.add(panel);
    }

    // Pruebo a hacer a mano el formulario
    private void addRow(String label, String valor) {
        gbc.gridy = fila++;

        gbc.gridx = 0;
        JLabel lbl = new JLabel(label + ":");
        panel.add(lbl, gbc);

        gbc.gridx = 1;
        JTextField txt = new JTextField(valor);
        txt.setEditable(false);
        txt.setColumns(20);
        panel.add(txt, gbc);
    }
}


