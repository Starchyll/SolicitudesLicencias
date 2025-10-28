package Gui;
/**
 *
 * @author icoro
 */
import DAO.PersonaDAO;
import DAO.PlacaDAO;
import DAO.TramiteDAO;
import DAO.VehiculoDAO;
import Enum.TipoTramite;
import Exception.PersistenciaException;
import Persistencia.Persona;
import Persistencia.Placa;
import Persistencia.Tramite;
import Persistencia.TramitePlaca;
import Persistencia.Vehiculo;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.persistence.EntityManagerFactory;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FIPlacas extends JInternalFrame {

    private JComboBox<String> cbTipoTramite;
    private JTextField txtSerie;
    private JTextField txtMarca;
    private JTextField txtLinea;
    private JTextField txtColor;
    private JTextField txtModelo;
    private JTextField txtRFC;
    private JButton btnBuscar;
    private JTextField txtNombre;
    private JTextField txtPlacas;
    private JTextField txtFecha;
    private JTextField txtCosto;
    private JButton btnGenerar;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JButton btnCerrar;

    private PersonaDAO personaDAO;
    private VehiculoDAO vehiculoDAO;
    private PlacaDAO placaDAO;
    private TramiteDAO tramiteDAO;
    
    private Persona personaEncontrada;
    private Vehiculo vehiculoEncontrado;
    private long costoCalculado = 0L;
    private boolean esNuevo = true;
    private String placasGeneradas = null;
    private SimpleDateFormat sdf;

    public FIPlacas() throws PersistenciaException {
        initComponents();
        
        EntityManagerFactory emf = Conexion.Conexion.crearConexion();
        this.personaDAO = new PersonaDAO(emf);
        this.vehiculoDAO = new VehiculoDAO(emf);
        this.placaDAO = new PlacaDAO(emf);
        this.tramiteDAO = new TramiteDAO(emf);
        this.sdf = new SimpleDateFormat("dd/MM/yyyy");

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
        
        toggleTipoTramite();
        setVisible(true);
    }
    
    private void conectarBotones() {
        btnBuscar.addActionListener(e -> buscarPersona());
        btnGenerar.addActionListener(e -> generarPlacas());
        btnGuardar.addActionListener(e -> guardarPlacas());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnCerrar.addActionListener(e -> this.dispose());
        
        cbTipoTramite.addActionListener(e -> toggleTipoTramite());
    }

    private void toggleTipoTramite() {
        if (cbTipoTramite.getSelectedItem().toString().equals("Auto usado")) {
            esNuevo = false;
            costoCalculado = 1000L;
            txtCosto.setText(String.valueOf(costoCalculado));
        } else {
            esNuevo = true;
            costoCalculado = 1500L;
            txtCosto.setText(String.valueOf(costoCalculado));
        }
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
            JOptionPane.showMessageDialog(this, "Persona encontrada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            personaEncontrada = null;
            txtNombre.setText("");
            JOptionPane.showMessageDialog(this, "Persona no encontrada. No se puede registrar placas sin un propietario existente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generarPlacas() {
        if (costoCalculado == 0L) {
            toggleTipoTramite();
        }

        Random rand = new Random();
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeros = "0123456789";
        
        placasGeneradas = "" + 
                letras.charAt(rand.nextInt(letras.length())) +
                letras.charAt(rand.nextInt(letras.length())) +
                letras.charAt(rand.nextInt(letras.length())) +
                numeros.charAt(rand.nextInt(numeros.length())) +
                numeros.charAt(rand.nextInt(numeros.length())) +
                numeros.charAt(rand.nextInt(numeros.length()));
        
        txtPlacas.setText(placasGeneradas);
        txtFecha.setText(sdf.format(new Date()));
    }

    private void guardarPlacas() {
        try {
            if (personaEncontrada == null) {
                JOptionPane.showMessageDialog(this, "Debe buscar y encontrar un propietario válido primero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (placasGeneradas == null) {
                JOptionPane.showMessageDialog(this, "Debe generar las placas primero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Long numSerie;
            try {
                numSerie = Long.parseLong(txtSerie.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El número de serie debe ser numérico.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String marca = txtMarca.getText();
            String linea = txtLinea.getText();
            String color = txtColor.getText();
            String modelo = txtModelo.getText();
            
            if (marca.isEmpty() || linea.isEmpty() || color.isEmpty() || modelo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos del vehículo son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            vehiculoEncontrado = vehiculoDAO.consultar(String.valueOf(numSerie));
            
            if (vehiculoEncontrado == null) {
                vehiculoEncontrado = new Vehiculo(numSerie, marca, "Auto", modelo, color, linea, personaEncontrada);
                vehiculoDAO.agregar(vehiculoEncontrado);
            } else {
                if (!vehiculoEncontrado.getDueno().getRFC().equals(personaEncontrada.getRFC())) {
                    JOptionPane.showMessageDialog(this, "Este vehículo ya está registrado a nombre de otra persona.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            TipoTramite tipo = esNuevo ? TipoTramite.Placa_Auto_Nuevo : TipoTramite.Placa_Auto_Usado;
            Date hoy = new Date();
            Long numPlacaLong;
            try {
                numPlacaLong = Long.parseLong(placasGeneradas);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error al generar el número de placa.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Tramite tramite = new Tramite(hoy, costoCalculado, personaEncontrada);
            Placa placa = new Placa(numPlacaLong, hoy, costoCalculado, esNuevo, true, hoy, vehiculoEncontrado, tramite);
            
            tramite.setPlaca(placa);
            
            tramiteDAO.agregar(tramite);

            JOptionPane.showMessageDialog(this, "Placas registradas con éxito: " + placasGeneradas, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void limpiarFormulario() {
        txtSerie.setText("");
        txtMarca.setText("");
        txtLinea.setText("");
        txtColor.setText("");
        txtModelo.setText("");
        txtRFC.setText("");
        txtNombre.setText("");
        txtPlacas.setText("");
        txtFecha.setText("");
        txtCosto.setText("");
        cbTipoTramite.setSelectedIndex(0);
        
        personaEncontrada = null;
        vehiculoEncontrado = null;
        costoCalculado = 0L;
        placasGeneradas = null;
        esNuevo = true;
    }
    
    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTipoTramite = new JLabel("Tipo de Trámite:");
        cbTipoTramite = new JComboBox<>(new String[]{"Auto nuevo", "Auto usado"});

        JLabel lblSerie = new JLabel("Número de Serie:");
        txtSerie = new JTextField();

        JLabel lblMarca = new JLabel("Marca:");
        txtMarca = new JTextField();

        JLabel lblLinea = new JLabel("Línea:");
        txtLinea = new JTextField();

        JLabel lblColor = new JLabel("Color:");
        txtColor = new JTextField();

        JLabel lblModelo = new JLabel("Modelo (Año):");
        txtModelo = new JTextField();

        JLabel lblRFC = new JLabel("RFC Propietario:");
        txtRFC = new JTextField();
        btnBuscar = new JButton("Buscar");

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        txtNombre.setEditable(false);

        JLabel lblPlacas = new JLabel("Número de Placas:");
        txtPlacas = new JTextField();
        txtPlacas.setEditable(false);

        JLabel lblFecha = new JLabel("Fecha Emisión:");
        txtFecha = new JTextField();
        txtFecha.setEditable(false);

        JLabel lblCosto = new JLabel("Costo:");
        txtCosto = new JTextField();
        txtCosto.setEditable(false);
        txtSerie.setPreferredSize(new Dimension(200, 30));
        txtMarca.setPreferredSize(new Dimension(200, 30));
        txtLinea.setPreferredSize(new Dimension(200, 30));
        txtColor.setPreferredSize(new Dimension(200, 30));
        txtModelo.setPreferredSize(new Dimension(200, 30));

        btnGenerar = new JButton("Generar Placas");
        btnGuardar = new JButton("Guardar");
        btnLimpiar = new JButton("Limpiar");
        btnCerrar = new JButton("Cerrar");
        
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
