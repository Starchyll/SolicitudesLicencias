package Gui;
/**
 *
 * @author icoro
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;

public class FIReportes extends JInternalFrame {

    public FIReportes() {
        setTitle("Reportes de Trámites Realizados");
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(850, 500);
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
        JLabel lblInicio = new JLabel("Fecha Inicio:");
DatePicker dpInicio = new DatePicker();
JLabel lblFin = new JLabel("Fecha Fin:");
DatePicker dpFin = new DatePicker();

        JLabel lblTipo = new JLabel("Tipo de Trámite:");
        JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Todos", "Licencias", "Placas"});
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(15);
        JButton btnBuscar = new JButton("Buscar");

        String[] columnas = {"Fecha", "Tipo de Trámite", "Nombre", "Costo"};
        JTable tabla = new JTable(new Object[0][columnas.length], columnas);
        JScrollPane scroll = new JScrollPane(tabla);

        JButton btnExportar = new JButton("Exportar a PDF");
        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnCerrar = new JButton("Cerrar");

        JButton[] botones = {btnBuscar, btnExportar, btnLimpiar, btnCerrar};
        for (JButton b : botones) Estilos.aplicarEstiloBoton(b);

        JPanel filtros = new JPanel(new GridLayout(2, 5, 8, 8));
        filtros.add(lblInicio); filtros.add(dpInicio);
        filtros.add(lblFin); filtros.add(dpFin);
        filtros.add(lblTipo); filtros.add(cbTipo);
        filtros.add(lblNombre); filtros.add(txtNombre);
        filtros.add(btnBuscar);
        Estilos.aplicarPanelFondo(filtros);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.add(btnExportar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCerrar);
        Estilos.aplicarPanelFondo(panelBotones);

        Estilos.aplicarTituloVentana(this);
        setLayout(new BorderLayout());
        add(filtros, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
}
