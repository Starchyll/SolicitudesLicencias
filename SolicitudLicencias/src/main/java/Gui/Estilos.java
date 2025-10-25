package Gui;
/**
 *
 * @author icoro
 */
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class Estilos {

    public static void aplicarEstiloBoton(JButton boton) {
        boton.setBackground(new Color(40, 100, 180));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void aplicarPanelFondo(JPanel panel) {
        panel.setBackground(new Color(245, 247, 250));
    }

    public static void aplicarTituloVentana(JInternalFrame frame) {
        frame.getContentPane().setBackground(new Color(245, 247, 250));
        frame.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }
}
