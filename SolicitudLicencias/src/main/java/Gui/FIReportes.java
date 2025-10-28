package Gui;
/**
 *
 * @author icoro
 */

import DAO.IPersonaDAO;
import DAO.ITramiteDAO;
import DAO.PersonaDAO;
import DAO.TramiteDAO;
import Exception.PersistenciaException;
import Persistencia.Tramite;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FIReportes extends JInternalFrame {

    private DatePicker dpInicio;
    private DatePicker dpFin;
    private JComboBox<String> cbTipo;
    private JTextField txtNombre;
    private JButton btnBuscar;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnExportar;
    private JButton btnLimpiar;
    private JButton btnCerrar;
    // Mantenido tu definición original de columnas
    private String[] columnas = {"Fecha", "Tipo de Trámite", "Nombre", "Costo"};

    private IPersonaDAO personaDAO;
    private ITramiteDAO tramiteDAO;
    private SimpleDateFormat formatter;

    public FIReportes() throws PersistenciaException {
        setTitle("Reportes de Trámites Realizados");
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(850, 500);

        initComponents(); // Inicializa los componentes visuales

        // Inicializa DAOs usando la conexión JPA y tus clases DAO
        EntityManagerFactory emf = Conexion.Conexion.crearConexion();
        this.personaDAO = new PersonaDAO(emf);
        this.tramiteDAO = new TramiteDAO(emf);
        this.formatter = new SimpleDateFormat("dd/MM/yyyy"); // Para formatear fechas en la tabla

        conectarBotones(); // Añade listeners a los botones
        
        // Listener para ajustar tamaño al mostrar
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                Container parent = getParent();
                if (parent != null) {
                    setSize(parent.getWidth(), parent.getHeight());
                }
            }
        });
        setVisible(true);
    }

    // Método para asignar acciones a los botones
    private void conectarBotones() {
        btnBuscar.addActionListener(e -> buscarTramites());
        btnExportar.addActionListener(e -> exportarPDF());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnCerrar.addActionListener(e -> this.dispose()); // Cierra esta ventana interna
    }

    // Lógica principal: se ejecuta al presionar "Buscar"
    private void buscarTramites() {
        // 1. Obtener valores de los filtros
        LocalDate fechaInicioLocal = dpInicio.getDate();
        LocalDate fechaFinLocal = dpFin.getDate();
        String tipoSeleccionado = cbTipo.getSelectedItem().toString();
        String nombre = txtNombre.getText().trim();

        // 2. Convertir LocalDate a java.util.Date (necesario para tu DAO)
        Date fechaInicio = null;
        Date fechaFin = null;
        if (fechaInicioLocal != null) {
            fechaInicio = Date.from(fechaInicioLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        if (fechaFinLocal != null) {
            // Ajuste para incluir el día final completo (hasta las 23:59:59) si es necesario
             fechaFin = Date.from(fechaFinLocal.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
             // Si tu consulta BETWEEN es inclusiva, podrías no necesitar plusDays(1)
             // fechaFin = Date.from(fechaFinLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }


        // 3. Validar fechas
        if (fechaInicio != null && fechaFin != null && fechaInicio.after(fechaFin)) {
            JOptionPane.showMessageDialog(this, "La fecha de inicio no puede ser posterior a la fecha de fin.", "Error de Fechas", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // 4. Decidir qué método DAO llamar según los filtros ingresados
        List<Tramite> tramites = new ArrayList<>();
        try {
            boolean algunFiltro = fechaInicio != null || fechaFin != null || !tipoSeleccionado.equals("Todos") || !nombre.isEmpty();
            boolean soloNombre = nombre.isEmpty() && fechaInicio == null && fechaFin == null && tipoSeleccionado.equals("Todos");

             if (algunFiltro) {
                 // --- Llamada al método con filtros ---
                 String tipoTramiteFiltro = tipoSeleccionado.equals("Todos") ? null : tipoSeleccionado;
                 
                 // *** ¡¡ADVERTENCIA!! ***
                 // La siguiente línea ASUME que has corregido tu método
                 // ITramiteDAO.consultarPorCriterio para que acepte java.util.Date
                 // en lugar del incorrecto DateTime. Si no lo has hecho, fallará.
                 tramites = tramiteDAO.consultarPorCriterio(fechaInicio, fechaFin, tipoTramiteFiltro, nombre);

             } else if (!nombre.isEmpty() && fechaInicio == null && fechaFin == null && tipoSeleccionado.equals("Todos")) {
                 // --- Llamada si SOLO se filtra por nombre ---
                 // Usa el método que tienes en IPersonaDAO
                 tramites = personaDAO.consultarPorNombreLike(nombre);

             } else {
                 // --- Llamada si NO hay filtros ---
                 // Usa el método consultarTodos de ITramiteDAO
                 tramites = tramiteDAO.consultarTodos();
             }

            // 5. Mostrar resultados o mensaje si no hay resultados
            if (tramites.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron trámites con los criterios especificados.", "Sin Resultados", JOptionPane.INFORMATION_MESSAGE);
            }
            llenarTabla(tramites); // Actualiza la JTable

        } catch (Exception ex) {
            // Manejo de errores (importante mostrar el error al usuario)
            ex.printStackTrace(); // Imprime el error completo en consola para depuración
            JOptionPane.showMessageDialog(this, "Error al consultar los trámites: " + ex.getMessage() +
                    "\n\n(Asegúrese de haber corregido el tipo de fecha en TramiteDAO.consultarPorCriterio a java.util.Date)", 
                    "Error de Consulta", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para poblar la JTable con los resultados
    private void llenarTabla(List<Tramite> tramites) {
        modeloTabla.setRowCount(0); // Limpia la tabla antes de llenarla

        for (Tramite t : tramites) {
            String fecha = (t.getFecha_realizacion() != null) ? formatter.format(t.getFecha_realizacion()) : "N/A";
            // Asume que tu Tramite tiene getTipo_tramite() que devuelve el Enum
            String tipo = (t.getTipo_tramite() != null) ? t.getTipo_tramite().toString().replace("_", " ") : "Desconocido";
            String nombrePersona = (t.getPersona() != null) ? t.getPersona().getNombre() : "N/A";
            String costo = (t.getCosto() != null) ? "$" + t.getCosto() : "$0.00";

            // Añade una fila por cada trámite
            modeloTabla.addRow(new Object[]{fecha, tipo, nombrePersona, costo});
        }
    }

    // Acción para el botón "Exportar a PDF" (placeholder)
    private void exportarPDF() {
        JOptionPane.showMessageDialog(this, "Funcionalidad de exportar a PDF aún no implementada.", "Pendiente", JOptionPane.INFORMATION_MESSAGE);
        // Aquí iría la lógica para generar el PDF usando una librería como iText o Apache PDFBox
        // Necesitarías obtener los datos de 'modeloTabla'
    }

    // Acción para el botón "Limpiar"
    private void limpiarFormulario() {
        dpInicio.clear(); // Limpia los DatePicker
        dpFin.clear();
        cbTipo.setSelectedIndex(0); // Restablece el ComboBox
        txtNombre.setText(""); // Limpia el campo de texto
        modeloTabla.setRowCount(0); // Limpia la tabla
    }

    // Inicializa y configura los componentes visuales de la ventana
    private void initComponents() {
        JLabel lblInicio = new JLabel("Fecha Inicio:");
        dpInicio = new DatePicker();
        JLabel lblFin = new JLabel("Fecha Fin:");
        dpFin = new DatePicker();

        JLabel lblTipo = new JLabel("Tipo de Trámite:");
        // Asegúrate que los valores coincidan con tu Enum TipoTramite o lo que esperas en el DAO
        cbTipo = new JComboBox<>(new String[]{"Todos", "Licencia", "Placa_Auto_Nuevo", "Placa_Auto_Usado"}); 
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(15);
        btnBuscar = new JButton("Buscar");

        // Configura el modelo de la tabla para que no sea editable
        modeloTabla = new DefaultTableModel(new Object[0][columnas.length], columnas) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; } // Celdas no editables
        };
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla); // Agrega la tabla a un panel con scroll

        btnExportar = new JButton("Exportar a PDF");
        btnLimpiar = new JButton("Limpiar");
        btnCerrar = new JButton("Cerrar");

        // Aplica estilos (si tienes la clase Estilos)
        JButton[] botones = {btnBuscar, btnExportar, btnLimpiar, btnCerrar};
        // for (JButton b : botones) Estilos.aplicarEstiloBoton(b); // Comentado si no existe Estilos

        // Panel superior para los filtros
        JPanel filtros = new JPanel(new GridLayout(2, 5, 8, 8)); // 2 filas, 5 columnas
        filtros.add(lblInicio); filtros.add(dpInicio);
        filtros.add(lblFin); filtros.add(dpFin);
        filtros.add(new JLabel()); // Celda vacía para espaciado
        filtros.add(lblTipo); filtros.add(cbTipo);
        filtros.add(lblNombre); filtros.add(txtNombre);
        filtros.add(btnBuscar);
        // Estilos.aplicarPanelFondo(filtros); // Comentado si no existe Estilos

        // Panel inferior para los botones de acción
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.add(btnExportar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCerrar);
        // Estilos.aplicarPanelFondo(panelBotones); // Comentado si no existe Estilos

        // Estilos.aplicarTituloVentana(this); // Comentado si no existe Estilos

        // Añade los paneles a la ventana
        setLayout(new BorderLayout());
        add(filtros, BorderLayout.NORTH); // Filtros arriba
        add(scroll, BorderLayout.CENTER); // Tabla en el centro
        add(panelBotones, BorderLayout.SOUTH); // Botones abajo
    }
}
