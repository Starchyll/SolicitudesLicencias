package Gui;
/**
 *
 * @author icoro
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FIPlacas extends JInternalFrame {

    public FIPlacas() {
        setTitle("Trámite de Placas");
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(800, 500);
addComponentListener(new java.awt.event.ComponentAdapter() {
    @Override
    public void componentShown(java.awt.event.ComponentEvent e) {
        Container parent = getParent();
        if (parent != null) {
            setSize(parent.getWidth(), parent.getHeight());
        }
    }
});

        initComponents();
        setVisible(true);
    }
    

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTipoTramite = new JLabel("Tipo de Trámite:");
        JComboBox<String> cbTipoTramite = new JComboBox<>(new String[]{"Auto nuevo", "Auto usado"});

        JLabel lblSerie = new JLabel("Número de Serie:");
        JTextField txtSerie = new JTextField();

        JLabel lblMarca = new JLabel("Marca:");
        JTextField txtMarca = new JTextField();

        JLabel lblLinea = new JLabel("Línea:");
        JTextField txtLinea = new JTextField();

        JLabel lblColor = new JLabel("Color:");
        JTextField txtColor = new JTextField();

        JLabel lblModelo = new JLabel("Modelo (Año):");
        JTextField txtModelo = new JTextField();

        JLabel lblRFC = new JLabel("RFC Propietario:");
        JTextField txtRFC = new JTextField();
        JButton btnBuscar = new JButton("Buscar");

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

        JLabel lblPlacas = new JLabel("Número de Placas:");
        JTextField txtPlacas = new JTextField();
        txtPlacas.setEditable(false);

        JLabel lblFecha = new JLabel("Fecha Emisión:");
        JTextField txtFecha = new JTextField();
        txtFecha.setEditable(false);

        JLabel lblCosto = new JLabel("Costo:");
        JTextField txtCosto = new JTextField();
        txtCosto.setEditable(false);
        txtSerie.setPreferredSize(new Dimension(200, 30));
        txtMarca.setPreferredSize(new Dimension(200, 30));
        txtLinea.setPreferredSize(new Dimension(200, 30));
        txtColor.setPreferredSize(new Dimension(200, 30));
        txtModelo.setPreferredSize(new Dimension(200, 30));

        JButton btnGenerar = new JButton("Generar Placas");
        JButton btnGuardar = new JButton("Guardar");
        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnCerrar = new JButton("Cerrar");

        JButton[] botones = {btnBuscar, btnGenerar, btnGuardar, btnLimpiar, btnCerrar};
        for (JButton b : botones) Estilos.aplicarEstiloBoton(b);
        Estilos.aplicarPanelFondo(panel);
        Estilos.aplicarTituloVentana(this);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(lblTipoTramite, gbc);
        gbc.gridx = 1; panel.add(cbTipoTramite, gbc);

        gbc.gridy++;
        gbc.gridx = 0; panel.add(lblSerie, gbc);
        gbc.gridx = 1; panel.add(txtSerie, gbc);
        gbc.gridx = 2; panel.add(lblMarca, gbc);
        gbc.gridx = 3; panel.add(txtMarca, gbc);

        gbc.gridy++;
        gbc.gridx = 0; panel.add(lblLinea, gbc);
        gbc.gridx = 1; panel.add(txtLinea, gbc);
        gbc.gridx = 2; panel.add(lblColor, gbc);
        gbc.gridx = 3; panel.add(txtColor, gbc);

        gbc.gridy++;
        gbc.gridx = 0; panel.add(lblModelo, gbc);
        gbc.gridx = 1; panel.add(txtModelo, gbc);

        gbc.gridy++;
        gbc.gridx = 0; panel.add(lblRFC, gbc);
        gbc.gridx = 1; panel.add(txtRFC, gbc);
        gbc.gridx = 2; panel.add(btnBuscar, gbc);

        gbc.gridy++;
        gbc.gridx = 0; panel.add(lblNombre, gbc);
        gbc.gridx = 1; gbc.gridwidth = 3; panel.add(txtNombre, gbc);
        gbc.gridwidth = 1;

        gbc.gridy++;
        gbc.gridx = 0; panel.add(lblPlacas, gbc);
        gbc.gridx = 1; panel.add(txtPlacas, gbc);
        gbc.gridx = 2; panel.add(lblFecha, gbc);
        gbc.gridx = 3; panel.add(txtFecha, gbc);

        gbc.gridy++;
        gbc.gridx = 0; panel.add(lblCosto, gbc);
        gbc.gridx = 1; panel.add(txtCosto, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        Estilos.aplicarPanelFondo(panelBotones);
        panelBotones.add(btnGenerar);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCerrar);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
}
