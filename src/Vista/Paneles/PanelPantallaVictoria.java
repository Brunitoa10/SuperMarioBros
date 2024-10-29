package Vista.Paneles;

import Constantes.ConstantesVista;
import Generador.GestorSonido.Sonido;
import Generador.GestorSonido.SonidoFactory;
import Vista.Controladores.ControladorVista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelPantallaVictoria extends JPanel {
    protected static final long serialVersionUID = 1L;
    protected ControladorVista controladorVistas;
    protected JLabel imagenFondo;
    protected JButton btnReiniciar;
    protected JButton btnVerPuntaje;
    protected String modoJuego;
    protected Sonido sonido;
    protected int puntaje;
    protected ImageIcon iconoImagen;
    protected Image imagenEscalada;
    protected Icon iconoImagenEscalado;

    public PanelPantallaVictoria(ControladorVista controladorVistas, String modoJuego) {
        this.controladorVistas = controladorVistas;
        this.modoJuego = modoJuego;
        sonido = SonidoFactory.crearSonido(modoJuego, "boton");
        setSize(ConstantesVista.PANEL_ANCHO, ConstantesVista.PANEL_ALTO);
        agregarImagenFondo();

        agregarBotonVerRanking();
    }

    public String obtenerModoJuego() {
        return modoJuego;
    }

    private void agregarImagenFondo() {
        imagenFondo = new JLabel();
        iconoImagen = new ImageIcon(this.getClass().getResource("/Recursos/imagenes/" + modoJuego + "/FinDeJuego.png"));
        imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVista.PANEL_ANCHO, ConstantesVista.PANEL_ALTO, Image.SCALE_SMOOTH);
        iconoImagenEscalado = new ImageIcon(imagenEscalada);
        setLayout(null);
        imagenFondo.setIcon(new ImageIcon(PanelPantallaVictoria.class.getResource("/Recursos/Imagenes/original/Victoria.png")));
        imagenFondo.setBounds(0, 5, 800, 600);
        add(imagenFondo);
    }

    private void agregarBotonVerRanking() {
        btnVerPuntaje = new JButton("");
        btnVerPuntaje.setBounds(181, 492, 474, 60);
        decorarBotonVerPuntaje();
        add(btnVerPuntaje);
        registrarOyenteVerPuntaje();
    }

    private void decorarBotonVerPuntaje() {
        btnVerPuntaje.setBackground(new Color(255, 255, 255));
        transparentarBoton(btnVerPuntaje);
    }

    private void registrarOyenteVerPuntaje() {
        btnVerPuntaje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controladorVistas.agregarJugadorAlRanking(controladorVistas.obtenerNombreUsuario(), controladorVistas.obtenerPuntajeJugador());
                controladorVistas.mostrarPantallaRanking();
            }
        });
        btnVerPuntaje.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sonido.reproducir();
            }
        });
    }

    private void transparentarBoton(JButton boton) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
    }
}
