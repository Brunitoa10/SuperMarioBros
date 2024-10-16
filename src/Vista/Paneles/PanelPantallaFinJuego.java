package Vista.Paneles;

import Vista.Controladores.ConstantesVista;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPantallaFinJuego extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel mensajeFinJuego;
    private JLabel puntajeFinal;

    public PanelPantallaFinJuego(int puntaje) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(ConstantesVista.PANEL_ANCHO, ConstantesVista.PANEL_ALTO));

        // Panel para el mensaje de fin de juego
        JPanel panelMensaje = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panelMensaje, BoxLayout.Y_AXIS);
        panelMensaje.setLayout(boxLayout);
        panelMensaje.add(Box.createVerticalGlue()); 

        mensajeFinJuego = new JLabel("¡Fin del juego!");
        mensajeFinJuego.setFont(new Font(mensajeFinJuego.getFont().getName(), Font.BOLD, 48));
        mensajeFinJuego.setForeground(Color.WHITE);
        mensajeFinJuego.setAlignmentX(CENTER_ALIGNMENT);
        panelMensaje.add(mensajeFinJuego);

        panelMensaje.add(Box.createVerticalStrut(20)); //Espacio entre el mensaje y el puntaje

        puntajeFinal = new JLabel("Puntaje final: " + puntaje);
        puntajeFinal.setFont(new Font(puntajeFinal.getFont().getName(), Font.PLAIN, 32));
        puntajeFinal.setForeground(Color.WHITE);
        puntajeFinal.setAlignmentX(CENTER_ALIGNMENT);
        panelMensaje.add(puntajeFinal);

        panelMensaje.add(Box.createVerticalGlue());
        add(panelMensaje, BorderLayout.CENTER);
    }
}