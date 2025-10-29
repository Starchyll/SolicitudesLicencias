package Gui;

/**
 *
 * @author icoro
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Conexion.Conexion;
import DAO.ITramiteDAO;
import DAO.TramiteDAO;
import Exception.PersistenciaException;
import Persistencia.Tramite;

public class FINacimiento extends JInternalFrame {

    private ITramiteDAO tramiteDAO;
    private SimpleDateFormat formatter;

    // --- Componentes de la GUI ---
    private JTextField txtAnio;
    private DefaultTableModel modeloTabla;

    public FINacimiento() throws PersistenciaException {
        EntityManagerFactory emf = Conexion.crearConexion();
        this.tramiteDAO = (ITramiteDAO) new TramiteDAO(emf);

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
        txtAnio = new JTextField(6);
        this.txtAnio = new JTextField(6);
        JButton btnBuscar = new JButton("Buscar");

        String[] columnas = {"RFC", "Nombre", "Año Nacimiento", "Tipo Trámite", "Costo"};
        JTable tabla = new JTable(new Object[0][columnas.length], columnas);
        // Configura el modelo de la tabla para que podamos limpiarlo y llenarlo
        modeloTabla = new DefaultTableModel(new Object[0][columnas.length], columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hace que la tabla no sea editable
            }
        };
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);

        JButton btnLimpiar = new JButton("Limpiar");
        JButton btnCerrar = new JButton("Cerrar");

        JButton[] botones = {btnBuscar, btnLimpiar, btnCerrar};
        for (JButton b : botones) {
            Estilos.aplicarEstiloBoton(b);
        }

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

        // --- CONECTAR LÓGICA A BOTONES ---
        btnBuscar.addActionListener(e -> buscarHistorial());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnCerrar.addActionListener(e -> this.dispose());
    }

    private void buscarHistorial() {
        String anio = txtAnio.getText().trim();

        if (anio.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese el año de nacimiento (YYYY).", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!anio.matches("\\d{4}")) {
            JOptionPane.showMessageDialog(this, "El año debe ser un número de 4 dígitos.", "Error de Formato", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {

            int anioNum = Integer.parseInt(anio);

            List<Tramite> tramites = tramiteDAO.consultarPorFechaLike(anioNum);

            if (tramites == null || tramites.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron trámites para personas nacidas en el año: " + anio, "Sin Resultados", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            llenarTabla(tramites);

        } catch (NumberFormatException nfe) {
            // Este catch es por si Integer.parseInt falla (aunque el regex ya valida)
            JOptionPane.showMessageDialog(this, "El año debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al consultar la base de datos: " + ex.getMessage(), "Error de Consulta", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarFormulario() {
        txtAnio.setText("");
        modeloTabla.setRowCount(0);
    }

    private void llenarTabla(List<Tramite> tramites) {
        
        modeloTabla.setRowCount(0);

        
        SimpleDateFormat anioFormatter = new SimpleDateFormat("yyyy");

        for (Tramite t : tramites) {
            String costo = (t.getCosto() != null) ? String.format("$%.2f", t.getCosto().doubleValue()) : "$0.00";
            String tipo = t.getTipo_tramite() != null ? t.getTipo_tramite().toString() : "Desconocido";

            // --- Datos de la Persona ---
            String rfc = "N/A";
            String nombre = "N/A";
            String anioNacimiento = "N/A"; 

            if (t.getPersona() != null) {
                rfc = t.getPersona().getRFC();
                nombre = t.getPersona().getNombre();

                // Obtener y formatear el Año de Nacimiento
                if (t.getPersona().getFecha_nacimiento() != null) {
                    anioNacimiento = anioFormatter.format(t.getPersona().getFecha_nacimiento());
                }
            }

            modeloTabla.addRow(new Object[]{rfc, nombre, anioNacimiento, tipo, costo});
        }
    }
}
