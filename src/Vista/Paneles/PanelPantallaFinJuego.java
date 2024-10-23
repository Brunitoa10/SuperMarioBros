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
import Vista.Controladores.ConstantesVista;
import Vista.Controladores.ControladorVista;
import javax.swing.JTextField;

public class PanelPantallaFinJuego extends JPanel {

    private static final long serialVersionUID = 1L;
    private ControladorVista controladorVistas;
    private JLabel imagenFondo;
    private JButton btnReiniciar;
    private JButton btnVolverMenu;
    protected String modoJuego;
    protected Sonido sonido;
    protected int puntaje;
    private JTextField textField;
    private JButton btnNewButton;

    public PanelPantallaFinJuego(ControladorVista controladorVistas, String modoJuego, int puntaje) {
        this.controladorVistas = controladorVistas;
        this.modoJuego = modoJuego;
        sonido = SonidoFactory.crearSonido(modoJuego, "boton");
        this.puntaje = puntaje;
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

        JLabel lblNewLabel = new JLabel("Su puntaje es: "+ puntaje);
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(328, 263, 149, 34);
        add(lblNewLabel);

        agregarBotonAgregarRanking();

        textField = new JTextField();
        textField.setBounds(292, 315, 217, 62);
        add(textField);
        textField.setColumns(10);
        imagenFondo.setIcon(new ImageIcon(PanelPantallaFinJuego.class.getResource("/Recursos/Imagenes/original/FinDeJuego.png")));
        imagenFondo.setBounds(0, 5, 800, 600);
        add(imagenFondo);
    }

    protected void agregarBotonAgregarRanking(){
        btnNewButton = new JButton("Enviar Puntaje");
        btnNewButton.setBounds(444, 257, 122, 46);
        add(btnNewButton);
        registrarOyenteBotonEnviarRanking();
    }

    protected void agregarBotonReiniciar() {
        btnReiniciar = new JButton("");
        btnReiniciar.setBounds(292, 388, 243, 68);
        decorarBotonReiniciar();
        add(btnReiniciar);
        registrarOyenteBotonReiniciar();
    }

    protected void agregarBotonVolverMenu() {
        btnVolverMenu = new JButton("");
        btnVolverMenu.setBounds(113, 481, 575, 82);
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

    public void registrarOyenteBotonEnviarRanking() {
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controladorVistas.agregarJugadorAlRanking(textField.getText(), puntaje);
            }
        });
    }
    public void reiniciarPuntaje(){
        puntaje = 0;
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