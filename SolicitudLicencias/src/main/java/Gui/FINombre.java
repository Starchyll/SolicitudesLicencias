package Gui;
/**
 *
 * @author icoro
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class FINombre extends JInternalFrame {

    public FINombre() {
        setTitle("Buscar Trámites por Nombre");
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(800, 500);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(15);
        JButton btnBuscar = new JButton("Buscar");

        String[] columnas = {"RFC", "Nombre", "Tipo Trámite", "Fecha", "Costo"};
        JTable tabla = new JTable(new Object[0][columnas.length], columnas);
        JScrollPane scroll = new JScrollPane(tabla);

        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnCerrar = new JButton("Cerrar");

        JButton[] botones = {btnBuscar, btnLimpiar, btnCerrar};
        for (JButton b : botones) Estilos.aplicarEstiloBoton(b);

        JPanel top = new JPanel();
        top.add(lblNombre);
        top.add(txtNombre);
        top.add(btnBuscar);
        Estilos.aplicarPanelFondo(top);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottom.add(btnLimpiar);
        bottom.add(btnCerrar);
        Estilos.aplicarPanelFondo(bottom);

        Estilos.aplicarTituloVentana(this);
        setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }
}
