package Gui;
/**
 *
 * @author icoro
 */
import DAO.ILicenciaDAO;
import DAO.IPersonaDAO;
import DAO.ITramiteDAO;
import DAO.LicenciaDAO;
import DAO.PersonaDAO;
import DAO.TramiteDAO;
import Exception.PersistenciaException;
import Persistencia.Licencia;
import Persistencia.Persona;
import Persistencia.Tramite;
import Persistencia.TramiteLicencia;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import com.github.lgooddatepicker.components.DatePicker;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManagerFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FILicencias extends JInternalFrame {

    private JTextField txtRFC;
    private JTextField txtNombre;
    private DatePicker dpNacimiento;
    private JTextField txtTelefono;
    private JComboBox<String> cbVigencia;
    private JComboBox<String> cbTipo;
    private JTextField txtCosto;
    private DatePicker dpExpedicion;
    private JButton btnBuscar;
    private JButton btnCalcular;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JButton btnCerrar;

    private IPersonaDAO personaDAO;
    private ILicenciaDAO licenciaDAO;
    private ITramiteDAO tramiteDAO;
    
    private Persona personaEncontrada;
    private long costoCalculado = 0L;

    public FILicencias() throws PersistenciaException {
        setTitle("Trámite de Licencias");
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(800, 500);
        
        initComponents();
        
        EntityManagerFactory emf = Conexion.Conexion.crearConexion();
        this.personaDAO = new PersonaDAO(emf);
        this.licenciaDAO = new LicenciaDAO(emf);
        this.tramiteDAO = new TramiteDAO(emf);
        
        conectarBotones();
        
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

    private void conectarBotones() {
        btnBuscar.addActionListener(e -> buscarPersona());
        btnCalcular.addActionListener(e -> calcularCosto());
        btnGuardar.addActionListener(e -> guardarLicencia());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnCerrar.addActionListener(e -> this.dispose());
    }

    private void buscarPersona() {
        String rfc = txtRFC.getText().trim().toUpperCase();
        if (rfc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un RFC.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Persona> todasLasPersonas = personaDAO.consultarTodos();
        Optional<Persona> personaOpt = todasLasPersonas.stream()
                .filter(p -> p.getRFC().equalsIgnoreCase(rfc))
                .findFirst();

        if (personaOpt.isPresent()) {
            personaEncontrada = personaOpt.get();
            txtNombre.setText(personaEncontrada.getNombre());
            txtTelefono.setText(personaEncontrada.getTelefono());
            
            Date fechaNac = personaEncontrada.getFecha_nacimiento();
            if (fechaNac != null) {
                dpNacimiento.setDate(fechaNac.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            }
            
            txtNombre.setEditable(false);
            txtTelefono.setEditable(false);
            dpNacimiento.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Persona encontrada. Puede proceder.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            personaEncontrada = null;
            txtNombre.setText("");
            txtTelefono.setText("");
            dpNacimiento.clear();
            txtNombre.setEditable(true);
            txtTelefono.setEditable(true);
            dpNacimiento.setEnabled(true);
            JOptionPane.showMessageDialog(this, "Persona no encontrada. Puede registrarla.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void calcularCosto() {
        int vigencia = Integer.parseInt(cbVigencia.getSelectedItem().toString().split(" ")[0]);
        boolean esDiscapacitado = cbTipo.getSelectedItem().toString().equals("Discapacitado");

        if (esDiscapacitado) {
            costoCalculado = 200L * vigencia;
        } else {
            costoCalculado = 600L * vigencia;
        }
        
        txtCosto.setText(String.valueOf(costoCalculado));
    }

    private void guardarLicencia() {
        try {
            if (costoCalculado == 0L) {
                calcularCosto();
            }

            String rfc = txtRFC.getText().trim().toUpperCase();
            String nombre = txtNombre.getText().trim();
            String telefono = txtTelefono.getText().trim();
            LocalDate fechaNacLocal = dpNacimiento.getDate();

            if (rfc.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || fechaNacLocal == null) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Date fechaNac = Date.from(fechaNacLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());

            if (personaEncontrada == null) {
                personaEncontrada = new Persona(rfc, telefono, fechaNac, nombre);
                personaDAO.agregar(personaEncontrada);
            }

            Date fechaExp = Date.from(dpExpedicion.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            int vigencia = Integer.parseInt(cbVigencia.getSelectedItem().toString().split(" ")[0]);
            boolean esDiscapacitado = cbTipo.getSelectedItem().toString().equals("Discapacitado");

            Calendar c = Calendar.getInstance();
            c.setTime(fechaExp);
            c.add(Calendar.YEAR, vigencia);
            Date fechaVenc = c.getTime();

            Tramite tramite = new Tramite(fechaVenc, costoCalculado, personaEncontrada);
            Licencia licencia = new Licencia(vigencia, costoCalculado, fechaVenc, fechaExp, esDiscapacitado, personaEncontrada, tramite);
            
            tramite.setLicencia(licencia);

            tramiteDAO.agregar(tramite);

            JOptionPane.showMessageDialog(this, "Licencia registrada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void limpiarFormulario() {
        txtRFC.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCosto.setText("");
        dpNacimiento.clear();
        dpExpedicion.setDateToToday();
        cbVigencia.setSelectedIndex(0);
        cbTipo.setSelectedIndex(0);
        
        txtNombre.setEditable(true);
        txtTelefono.setEditable(true);
        dpNacimiento.setEnabled(true);
        
        personaEncontrada = null;
        costoCalculado = 0L;
    }
    
    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblRFC = new JLabel("RFC:");
        txtRFC = new JTextField();
        btnBuscar = new JButton("Buscar");

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();

        JLabel lblNacimiento = new JLabel("Fecha de Nacimiento:");
        dpNacimiento = new DatePicker();

        JLabel lblTelefono = new JLabel("Teléfono:");
        txtTelefono = new JTextField();

        JLabel lblVigencia = new JLabel("Vigencia:");
        cbVigencia = new JComboBox<>(new String[]{"1 año", "2 años", "3 años"});

        JLabel lblTipo = new JLabel("Tipo de Persona:");
        cbTipo = new JComboBox<>(new String[]{"Normal", "Discapacitado"});

        JLabel lblCosto = new JLabel("Costo:");
        txtCosto = new JTextField();
        txtCosto.setEditable(false);

        JLabel lblFecha = new JLabel("Fecha Expedición:");
        dpExpedicion = new DatePicker();
        dpExpedicion.setDateToToday();

        btnCalcular = new JButton("Calcular costo");
        btnGuardar = new JButton("Guardar");
        btnLimpiar = new JButton("Limpiar");
        btnCerrar = new JButton("Cerrar");

        JButton[] botones = {btnBuscar, btnCalcular, btnGuardar, btnLimpiar, btnCerrar};
        for (JButton b : botones) Estilos.aplicarEstiloBoton(b);
        Estilos.aplicarPanelFondo(panel);
        Estilos.aplicarTituloVentana(this);

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
