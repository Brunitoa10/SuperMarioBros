package Vista.Paneles;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Vista.Controladores.ConstantesVista;
import Vista.Controladores.ControladorVista;

public class PanelPantallaPrincipal extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private ControladorVista controladorVistas;
    private JLabel imagenFondo;
    private JButton btnIniciarJuego;
    private JButton botonPuntajes;

    public PanelPantallaPrincipal(ControladorVista controladorVistas) {
        this.controladorVistas = controladorVistas;
        setSize(ConstantesVista.PANEL_ANCHO, ConstantesVista.PANEL_ALTO);
        agregarImagenFondo();
        agregarBotonIniciar();
        agregarBotonPuntaje();
    }

    protected void agregarImagenFondo() {
        imagenFondo = new JLabel();
        ImageIcon icono_imagen = new ImageIcon(this.getClass().getResource("/Recursos/imagenes/original/Inicio.png"));
        Image imagen_escalada = icono_imagen.getImage().getScaledInstance(ConstantesVista.PANEL_ANCHO,
                ConstantesVista.PANEL_ALTO, Image.SCALE_SMOOTH);
        Icon icono_imagen_escalado = new ImageIcon(imagen_escalada);
        setLayout(null);
        imagenFondo.setIcon(icono_imagen_escalado);
        imagenFondo.setBounds(0, 5, 800, 600);
        add(imagenFondo);
    }

    protected void agregarBotonIniciar() {

        System.out.println("Agregue boton inicio");

        btnIniciarJuego = new JButton("");
        btnIniciarJuego.setBounds(300, 391, 230, 57);

        decorarBotonIniciar();
        add(btnIniciarJuego);

        registrarOyenteBotonIniciar();
    }

    protected void agregarBotonPuntaje() {
        botonPuntajes = new JButton();
        // boton_puntajes.setBounds(270, 484, 380, 76);
        decorarBotonPuntajes();
        registrarOyenteBotonPuntajes();
        add(botonPuntajes);
    }

    protected void decorarBotonIniciar() {
        System.out.println("Decorando boton inicio");
        btnIniciarJuego.setBackground(new Color(255, 255, 255));
        transparentar_boton(btnIniciarJuego);
    }

    protected void decorarBotonPuntajes() {
        transparentar_boton(botonPuntajes);
        botonPuntajes.setBounds(176, 487, 471, 65);
    }

    protected void registrarOyenteBotonIniciar() {
        System.out.println("Registrando boton inicio");
        btnIniciarJuego.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Boton inicio apretado");
                controladorVistas.accionarInicioJuego();
            }
        });
    }

    protected void registrarOyenteBotonPuntajes() {
        botonPuntajes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controladorVistas.accionarPantallaRanking();
            }
        });
    }

    protected void transparentar_boton(JButton boton) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
    }
}
