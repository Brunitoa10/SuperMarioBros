package Vista.Paneles;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Generador.GestorSonido.Sonido;
import Generador.GestorSonido.SonidoFactory;
import Vista.Controladores.ControladorVista;

import Constantes.ConstantesVista;

public class PanelPantallaFinJuego extends JPanel {

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
    
    public PanelPantallaFinJuego(ControladorVista controladorVistas, String modoJuego) {
        this.controladorVistas = controladorVistas;
        this.modoJuego = modoJuego;
        sonido = SonidoFactory.crearSonido(modoJuego, "boton");
        setSize(803, 601);
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
        imagenFondo.setIcon(new ImageIcon(PanelPantallaFinJuego.class.getResource("/Recursos/Imagenes/original/FinDeJuego.png")));
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
        decorarBotonVolverMenu();
        add(btnVolverMenu);
        registrarOyenteVerPuntaje();
    }

    private void decorarBotonReiniciar() {
        btnReiniciar.setBackground(new Color(255, 255, 255));
        transparentarBoton(btnReiniciar);
    }

    private void decorarBotonVolverMenu() {
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
            	controladorVistas.agregarJugadorAlRanking(controladorVistas.obtenerNombreUsuario(), controladorVistas.obtenerPuntajeJugador());
                controladorVistas.mostrarPantallaRanking();
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