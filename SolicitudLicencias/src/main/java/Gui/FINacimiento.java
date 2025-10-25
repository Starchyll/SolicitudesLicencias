package Gui;

/**
 *
 * @author icoro
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class FINacimiento extends JInternalFrame {

    public FINacimiento() {
        setTitle("Buscar por Año de Nacimiento");
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
        JLabel lblAnio = new JLabel("Año de Nacimiento:");
        JTextField txtAnio = new JTextField(6);
        JButton btnBuscar = new JButton("Buscar");

        String[] columnas = {"RFC", "Nombre", "Año Nacimiento", "Tipo Trámite", "Costo"};
        JTable tabla = new JTable(new Object[0][columnas.length], columnas);
        JScrollPane scroll = new JScrollPane(tabla);

        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnCerrar = new JButton("Cerrar");

        JButton[] botones = {btnBuscar, btnLimpiar, btnCerrar};
        for (JButton b : botones) Estilos.aplicarEstiloBoton(b);

        JPanel top = new JPanel();
        top.add(lblAnio);
        top.add(txtAnio);
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
