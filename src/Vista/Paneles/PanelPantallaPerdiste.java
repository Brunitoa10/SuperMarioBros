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

public class PanelPantallaPerdiste extends JPanel {

    protected static final long serialVersionUID = 1L;
    protected ControladorVista controladorVistas;
    protected JLabel imagenFondo;
    protected JButton btnReiniciar;
    protected JButton btnVolverMenu;
    protected String modoJuego;
    protected Sonido sonido;
    protected int puntaje;
    protected ImageIcon iconoImagen;
    protected Image imagenEscalada;
    protected Icon iconoImagenEscalado;

    public PanelPantallaPerdiste(ControladorVista controladorVistas, String modoJuego) {
        this.controladorVistas = controladorVistas;
        this.modoJuego = modoJuego;
        sonido = SonidoFactory.crearSonido(modoJuego, "boton");
        setSize(ConstantesVista.PANEL_ANCHO, ConstantesVista.PANEL_ALTO);
        agregarImagenFondo();
        agregarBotonReiniciar();
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
        imagenFondo.setIcon(new ImageIcon(PanelPantallaPerdiste.class.getResource("/Recursos/Imagenes/original/FinDeJuego.png")));
        imagenFondo.setBounds(0, 5, 800, 600);
        add(imagenFondo);
    }


    private void agregarBotonReiniciar() {
        btnReiniciar = new JButton("");
        btnReiniciar.setBounds(292, 388, 243, 68);
        decorarBotonReiniciar();
        add(btnReiniciar);
        registrarOyenteBotonReiniciar();
    }

    private void agregarBotonVerRanking() {
        btnVolverMenu = new JButton("");
        btnVolverMenu.setBounds(113, 481, 575, 82);
        decorarBotonVerPuntaje();
        add(btnVolverMenu);
        registrarOyenteVerPuntaje();
    }

    private void decorarBotonReiniciar() {
        btnReiniciar.setBackground(new Color(255, 255, 255));
        transparentarBoton(btnReiniciar);
    }

    private void decorarBotonVerPuntaje() {
        btnVolverMenu.setBackground(new Color(255, 255, 255));
        transparentarBoton(btnVolverMenu);
    }


    private void registrarOyenteBotonReiniciar() {
        btnReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controladorVistas.accionarPantallaModoJuego();
            }
        });
        btnReiniciar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sonido.reproducir();
            }
        });
    }

    private void registrarOyenteVerPuntaje() {
        btnVolverMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controladorVistas.accionarPantallaRanking();
            }
        });
        btnVolverMenu.addMouseListener(new MouseAdapter() {
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