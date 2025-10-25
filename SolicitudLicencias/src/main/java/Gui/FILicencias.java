package Gui;
/**
 *
 * @author icoro
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FILicencias extends JInternalFrame {

    public FILicencias() {
        setTitle("Trámite de Licencias");
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
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblRFC = new JLabel("RFC:");
        JTextField txtRFC = new JTextField();
        JButton btnBuscar = new JButton("Buscar");

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();

       JLabel lblNacimiento = new JLabel("Fecha de Nacimiento:");
        DatePicker dpNacimiento = new DatePicker();


        JLabel lblTelefono = new JLabel("Teléfono:");
        JTextField txtTelefono = new JTextField();

        JLabel lblVigencia = new JLabel("Vigencia:");
        JComboBox<String> cbVigencia = new JComboBox<>(new String[]{"1 año", "2 años", "3 años"});

        JLabel lblTipo = new JLabel("Tipo de Persona:");
        JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Normal", "Discapacitado"});

        JLabel lblCosto = new JLabel("Costo:");
        JTextField txtCosto = new JTextField();
        txtCosto.setEditable(false);

       JLabel lblFecha = new JLabel("Fecha Expedición:");
    DatePicker dpExpedicion = new DatePicker();
    dpExpedicion.setDateToToday();
        

        JButton btnCalcular = new JButton("Calcular costo");
        JButton btnGuardar = new JButton("Guardar");
        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnCerrar = new JButton("Cerrar");

        // Estilo
        JButton[] botones = {btnBuscar, btnCalcular, btnGuardar, btnLimpiar, btnCerrar};
        for (JButton b : botones) Estilos.aplicarEstiloBoton(b);
        Estilos.aplicarPanelFondo(panel);
        Estilos.aplicarTituloVentana(this);

        // Layout
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(lblRFC, gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        panel.add(txtRFC, gbc);
        gbc.gridx = 2; gbc.weightx = 0;
        panel.add(btnBuscar, gbc);

        gbc.gridy++;
        gbc.gridx = 0; panel.add(lblNombre, gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; panel.add(txtNombre, gbc);
        gbc.gridwidth = 1;

        gbc.gridy++;
        gbc.gridx = 0; panel.add(lblNacimiento, gbc);
        gbc.gridx = 1; panel.add(dpNacimiento, gbc);
        gbc.gridx = 2; panel.add(lblTelefono, gbc);
        gbc.gridx = 3; panel.add(txtTelefono, gbc);

        gbc.gridy++;
        gbc.gridx = 0; panel.add(lblVigencia, gbc);
        gbc.gridx = 1; panel.add(cbVigencia, gbc);
        gbc.gridx = 2; panel.add(lblTipo, gbc);
        gbc.gridx = 3; panel.add(cbTipo, gbc);

        gbc.gridy++;
        gbc.gridx = 0; panel.add(lblCosto, gbc);
        gbc.gridx = 1; panel.add(txtCosto, gbc);
        gbc.gridx = 2; panel.add(lblFecha, gbc);
        gbc.gridx = 3; panel.add(dpExpedicion, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        Estilos.aplicarPanelFondo(panelBotones);
        panelBotones.add(btnCalcular);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCerrar);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
}
