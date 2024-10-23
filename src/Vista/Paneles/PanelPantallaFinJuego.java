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
import javax.swing.JTextField;

import Generador.GestorSonido.Sonido;
import Generador.GestorSonido.SonidoFactory;
import Vista.Controladores.ConstantesVista;
import Vista.Controladores.ControladorVista;

public class PanelPantallaFinJuego extends JPanel {

    private static final long serialVersionUID = 1L;
    private ControladorVista controladorVistas;
    private JLabel imagenFondo;
    private JButton btnReiniciar;
    private JButton btnVolverMenu;
    protected String modoJuego;
    protected Sonido sonido;
    private int puntaje;

    public PanelPantallaFinJuego(ControladorVista controladorVistas, String modoJuego, int puntaje) {
        this.controladorVistas = controladorVistas;
        this.modoJuego = modoJuego;
        this.puntaje = puntaje;
        sonido = SonidoFactory.crearSonido(modoJuego, "boton");
        setSize(803, 601);
        agregarImagenFondo();
        agregarBotonReiniciar();
        agregarBotonVolverMenu();
    }

    protected void agregarImagenFondo() {
        imagenFondo = new JLabel();
        ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource("/Recursos/imagenes/" + modoJuego + "/FinDeJuego.png"));
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVista.PANEL_ANCHO, ConstantesVista.PANEL_ALTO, Image.SCALE_SMOOTH);
        Icon iconoImagenEscalado = new ImageIcon(imagenEscalada);
        setLayout(null);
        imagenFondo.setIcon(iconoImagenEscalado);
        imagenFondo.setBounds(0, 5, 800, 600);
        add(imagenFondo);
    }

    protected void agregarBotonReiniciar() {
        btnReiniciar = new JButton("");
        btnReiniciar.setBounds(366, 401, 286, 72);
        decorarBotonReiniciar();
        add(btnReiniciar);
        registrarOyenteBotonReiniciar();
    }

    protected void agregarBotonVolverMenu() {
        btnVolverMenu = new JButton("");
        btnVolverMenu.setBounds(225, 518, 575, 82);
        decorarBotonVolverMenu();
        add(btnVolverMenu);
        registrarOyenteVerPuntaje();
    }

    protected void decorarBotonReiniciar() {
        btnReiniciar.setBackground(new Color(255, 255, 255));
        transparentarBoton(btnReiniciar);
    }

    protected void decorarBotonVolverMenu() {
        btnVolverMenu.setBackground(new Color(255, 255, 255));
        transparentarBoton(btnVolverMenu);
    }

    protected void registrarOyenteBotonReiniciar() {
        btnReiniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //controladorVistas.accionarReiniciarJuego(modoJuego);  // Método para reiniciar el juego
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

    protected void registrarOyenteVerPuntaje() {
        btnVolverMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	controladorVistas.mostrarPantallaRanking();;  // Método para volver al menú principal
            }
        });
        btnVolverMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sonido.reproducir();
            }
        });
    }

    protected void transparentarBoton(JButton boton) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
    }

    public String obtenerModoJuego() {
        return modoJuego;
    }
}