package Gui;
/**
 *
 * @author icoro
 */

import Conexion.Conexion;
import Conexion.IConexion;
import DAO.IPersonaDAO;
import DAO.PersonaDAO;
import Enum.TipoTramite;
import Exception.PersistenciaException;
import Persistencia.Licencia;
import Persistencia.Placa;
import Persistencia.Tramite;
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

public class FIHistorial extends JInternalFrame {

    // --- Componentes de la GUI ---
    private JTextField txtRFC;
    private JButton btnBuscar;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnLimpiar;
    private JButton btnCerrar;
    private String[] columnas = {"Tipo de Trámite", "Fecha", "Vigencia / Placas", "Costo"};

    // --- Capa de DAO ---
    private IPersonaDAO personaDAO;
    private SimpleDateFormat formatter; // Para formatear fechas

    public FIHistorial() throws PersistenciaException {
        // 1. Inicializar la conexión y los DAOs
        // [cite: Conexion/ConexionJPA.java]
        EntityManagerFactory emf =  Conexion.crearConexion(); 
        this.personaDAO = new PersonaDAO(emf); // [cite: PersonaDAO.java]
        this.formatter = new SimpleDateFormat("dd/MM/yyyy");

        // 2. Configuración de la ventana
        setTitle("Historial de Trámites por Persona");
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

        // 3. Construir los componentes
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JLabel lblRFC = new JLabel("RFC:");
        txtRFC = new JTextField(15);
        btnBuscar = new JButton("Buscar");

        // Configura el modelo de la tabla para que podamos limpiarlo y llenarlo
        modeloTabla = new DefaultTableModel(new Object[0][columnas.length], columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hace que la tabla no sea editable
            }
        };
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);

        btnLimpiar = new JButton("Limpiar");
        btnCerrar = new JButton("Cerrar");

        // Asumo que tienes una clase Estilos, la he comentado por si acaso
        JButton[] botones = {btnBuscar, btnLimpiar, btnCerrar};
        // for (JButton b : botones) Estilos.aplicarEstiloBoton(b);

        JPanel top = new JPanel();
        top.add(lblRFC);
        top.add(txtRFC);
        top.add(btnBuscar);
        // Estilos.aplicarPanelFondo(top);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottom.add(btnLimpiar);
        bottom.add(btnCerrar);
        // Estilos.aplicarPanelFondo(bottom);

        // Estilos.aplicarTituloVentana(this);
        setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        // --- CONECTAR LÓGICA A BOTONES ---
        btnBuscar.addActionListener(e -> buscarHistorial());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnCerrar.addActionListener(e -> this.dispose()); // Cierra esta ventana interna
    }

    /**
     * Método llamado por el botón "Buscar".
     */
    private void buscarHistorial() {
        String rfc = txtRFC.getText().trim().toUpperCase();

        if (rfc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un RFC.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // 1. Llama directamente al DAO
            // [cite: IPersonaDAO.java]
            List<Tramite> tramites = personaDAO.consultarHistorialTramites(rfc);

            if (tramites == null || tramites.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron trámites para el RFC: " + rfc, "Sin Resultados", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario(); // Limpia por si había una búsqueda anterior
                return;
            }

            // 2. Llena la tabla con los resultados
            llenarTabla(tramites);

        } catch (Exception ex) {
            // 3. Muestra cualquier error de la base de datos
            ex.printStackTrace(); // Bueno para depurar
            JOptionPane.showMessageDialog(this, "Error al consultar la base de datos: " + ex.getMessage(), "Error de Consulta", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Limpia la tabla y el campo de RFC.
     */
    private void limpiarFormulario() {
        txtRFC.setText("");
        // Limpia la tabla
        modeloTabla.setRowCount(0);
    }

    /**
     * Rellena la JTable con la lista de entidades Tramite.
     * @param tramites La lista de trámites obtenida del DAO.
     */
    private void llenarTabla(List<Tramite> tramites) {
        // Limpia la tabla antes de llenar
        modeloTabla.setRowCount(0);

        for (Tramite t : tramites) {
            // Asumo que corregiste DateTime a java.util.Date
            String fecha = (t.getFecha_realizacion() != null) ? formatter.format(t.getFecha_realizacion()) : "N/A";
            String costo = (t.getCosto() != null) ? "$" + t.getCosto() : "$0.00";
            String tipo = "Desconocido";
            String detalle = "N/A";

            // Revisa el Enum del trámite para saber qué datos mostrar
            // [cite: TipoTramite.java]
            if (t.getTipo_tramite() == TipoTramite.Licencia) {
                tipo = "Licencia";
                // Usamos la relación en Tramite [cite: Tramite.java] para obtener la Licencia
                Licencia lic = t.getLicencia(); 
                if (lic != null) {
                    detalle = "Vigencia: " + lic.getVigencia() + " años";
                }
            } else if (t.getTipo_tramite() == TipoTramite.Placa_Auto_Nuevo || t.getTipo_tramite() == TipoTramite.Placa_Auto_Usado) {
                tipo = "Placa";
                // Usamos la relación en Tramite [cite: Tramite.java] para obtener la Placa
                Placa p = t.getPlaca(); 
                if (p != null) {
                    detalle = "Placa: " + p.getNum_placa();
                }
            }

            // Añade la fila a la tabla
            modeloTabla.addRow(new Object[]{tipo, fecha, detalle, costo});
        }
    }
    
}
