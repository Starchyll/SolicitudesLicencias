package Gui;
/**
 *
 * @author icoro
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManagerFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Conexion.Conexion;
import DAO.ITramiteDAO;
import DAO.TramiteDAO;
import Enum.TipoTramite;
import Persistencia.Licencia;
import Persistencia.Placa;
import Persistencia.Tramite;

public class FINombre extends JInternalFrame {
    private ITramiteDAO tramiteDAO;
    private SimpleDateFormat formatter;

    public FINombre() {
        EntityManagerFactory emf =  Conexion.crearConexion(); 
        this.tramiteDAO = (ITramiteDAO) new TramiteDAO(emf); 

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

        // --- CONECTAR LÓGICA A BOTONES ---
        btnBuscar.addActionListener(e -> buscarHistorial());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnCerrar.addActionListener(e -> this.dispose()); 
    }

    

    private void buscarHistorial() {
    String nombre = txtNombre.getText().trim(); 

    if (nombre.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese el nombre o parte del nombre.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        // Llama al DAO. Se envía el patrón con wildcards (%).
        List<Tramite> tramites = tramiteDAO.consultarPorNombrePersonaLike("%" + nombre + "%");

        if (tramites == null || tramites.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron trámites para la persona con nombre similar a: " + nombre, "Sin Resultados", JOptionPane.INFORMATION_MESSAGE);

            return;
        }

        llenarTabla(tramites); 
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al consultar la base de datos: " + ex.getMessage(), "Error de Consulta", JOptionPane.ERROR_MESSAGE);
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        modeloTabla.setRowCount(0);
    }

    /**
     * Rellena la JTable con la lista de entidades Tramite.
     * @param tramites La lista de trámites obtenida del DAO.
     */
    private void llenarTabla(List<Tramite> tramites) {

        for (Tramite t : tramites) {
            String tipo = t.getTipo_tramite() != null ? t.getTipo_tramite().toString() : "Desconocido";
            String fecha = (t.getFecha_realizacion() != null) ? formatter.format(t.getFecha_realizacion()) : "N/A";
            String costo = (t.getCosto() != null) ? String.format("$%.2f", t.getCosto()) : "$0.00";
            String detalle = "N/A"; 

            if (t.getTipo_tramite() == TipoTramite.Licencia) {
                 Licencia lic = t.getLicencia(); 
                 if (lic != null) {
                     detalle = lic.getVigencia() + " años";
                 }
            } else if (t.getTipo_tramite() == TipoTramite.Placa_Auto_Nuevo || t.getTipo_tramite() == TipoTramite.Placa_Auto_Usado) {
                 Placa p = t.getPlaca(); 
                 if (p != null) {
                     detalle = "Placa: " + p.getNum_placa();
                 }
            }

            modeloTabla.addRow(new Object[]{tipo, fecha, detalle, costo});
        }
    }
}

