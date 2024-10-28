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

import Constantes.ConstantesVista;
import Generador.GestorSonido.Sonido;
import Generador.GestorSonido.SonidoFactory;
import Vista.Controladores.ControladorVista;


public class PanelPantallaPrincipal extends JPanel {

    protected static final long serialVersionUID = 1L;
    protected ControladorVista controladorVistas;
    protected JLabel imagenFondo;
    protected JButton btnIniciarJuego;
    protected JButton botonPuntajes;
    protected String modoJuego;
    protected Sonido sonidoBoton;
    protected ImageIcon icono_imagen;
    protected Image imagen_escalada;
    
    public PanelPantallaPrincipal(ControladorVista controladorVistas, String modoJuego) {
        this.controladorVistas = controladorVistas;
        this.modoJuego = modoJuego;
        sonidoBoton = SonidoFactory.crearSonido(modoJuego, "boton");
        setSize(ConstantesVista.PANEL_ANCHO, ConstantesVista.PANEL_ALTO);
        agregarImagenFondo();
        agregarBotonIniciar();
        agregarBotonPuntaje();
    }

    private void agregarImagenFondo() {
        imagenFondo = new JLabel();
        icono_imagen = new ImageIcon( this.getClass().getResource("/Recursos/imagenes/" + modoJuego + "/Inicio.png"));
        imagen_escalada = icono_imagen.getImage().getScaledInstance(ConstantesVista.PANEL_ANCHO, ConstantesVista.PANEL_ALTO, Image.SCALE_SMOOTH);
        Icon icono_imagen_escalado = new ImageIcon(imagen_escalada);
        setLayout(null);
        imagenFondo.setIcon(icono_imagen_escalado);
        imagenFondo.setBounds(0, 0, 800, 566);
        add(imagenFondo);
    }

    private void agregarBotonIniciar() {
        btnIniciarJuego = new JButton("");
        btnIniciarJuego.setBounds(300, 391, 230, 57);
        decorarBotonIniciar();
        add(btnIniciarJuego);
        registrarOyenteBotonIniciar();

    }

    private void agregarBotonPuntaje() {
        botonPuntajes = new JButton();
        decorarBotonPuntajes();
        registrarOyenteBotonPuntajes();
        add(botonPuntajes);

    }

    protected void decorarBotonIniciar() {
        btnIniciarJuego.setBackground(new Color(255, 255, 255));
        transparentar_boton(btnIniciarJuego);
    }

    protected void decorarBotonPuntajes() {
        transparentar_boton(botonPuntajes);
        botonPuntajes.setBounds(176, 487, 471, 65);
    }

    private void registrarOyenteBotonIniciar() {
        btnIniciarJuego.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controladorVistas.accionarInicioJuego(modoJuego);
            }
        });
        btnIniciarJuego.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sonidoBoton.reproducir();
            }
        });
    }

    private void registrarOyenteBotonPuntajes() {
        botonPuntajes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controladorVistas.accionarPantallaRanking();
            }
        });
        botonPuntajes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sonidoBoton.reproducir();
            }
        });
    }
    
    private void transparentar_boton(JButton boton) {
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
    }

    public String obtenerModoJuego() {
        return modoJuego;
    }
}
