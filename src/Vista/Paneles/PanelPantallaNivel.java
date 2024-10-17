package Vista.Paneles;

import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import Vista.Controladores.ConstantesVista;
import Vista.Controladores.ControladorVista;
import Vista.ObserverGrafica.Observer;
import Vista.ObserverGrafica.ObserverEntidad;
import Vista.ObserverGrafica.ObserverJugador;
import javax.swing.*;
import java.awt.*;

public class PanelPantallaNivel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JLabel imagenFondoPanelNivel;
    private JLabel labelPuntaje;
    private JLabel labelVida;
    private JLabel labelTiempo;

    private ObserverEntidad observerEntidad;
    private ObserverJugador observerJugador;
    
    protected JScrollPane panelScrollNivel;
    protected JPanel panelSuperior;
    protected JPanel panelImagen;
    protected ControladorVista controladorVista;

    public PanelPantallaNivel(ControladorVista controladorVista) {
    	this.controladorVista = controladorVista;
        setPreferredSize(new Dimension(ConstantesVista.PANEL_ANCHO, ConstantesVista.PANEL_ALTO));
        setLayout(new BorderLayout());
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        agregarPanelSuperior();
        agregarPanelNivelConFondoYScroll();
    }

    private void agregarPanelSuperior() {
        panelSuperior = crearPanelSuperior();
        add(panelSuperior, BorderLayout.NORTH);
    }

    private JPanel crearPanelSuperior() {
        panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.X_AXIS));
        panelSuperior.add(Box.createHorizontalGlue());

        labelVida = crearLabel("Vidas: 0");
        labelPuntaje = crearLabel("Puntaje: 0");
        labelTiempo = crearLabel("Tiempo: 0");

        panelSuperior.add(labelVida);
        panelSuperior.add(Box.createHorizontalGlue());
        panelSuperior.add(labelPuntaje);
        panelSuperior.add(Box.createHorizontalGlue());
        panelSuperior.add(labelTiempo);
        panelSuperior.add(Box.createHorizontalGlue());

        return panelSuperior;
    }

    private JLabel crearLabel(String texto) {
        return new JLabel(texto);
    }

    private void agregarPanelNivelConFondoYScroll() {
        imagenFondoPanelNivel = new JLabel(new ImageIcon(getClass().getResource("/Recursos/Fondos/"+controladorVista.obtenerModoJuego()+"/FondoLevel1.png")));
        imagenFondoPanelNivel.setLayout(null);

        panelImagen = new JPanel(new BorderLayout());
        panelImagen.add(imagenFondoPanelNivel, BorderLayout.CENTER);

        panelScrollNivel = new JScrollPane(panelImagen);
        configurarScroll(panelScrollNivel);

        this.add(panelScrollNivel);
    }

    private void configurarScroll(JScrollPane panelScrollNivel) {
        panelScrollNivel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelScrollNivel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
    }

    public Observer incorporarEntidad(EntidadLogica entidad_logica) {
        observerEntidad = new ObserverEntidad(entidad_logica, this);
        imagenFondoPanelNivel.add(observerEntidad);
        System.out.println("Entidad añadida al panel: " + entidad_logica.getClass().getName());
        actualizarObserver();
        return observerEntidad;
    }

    public Observer incorporarEntidadJugador(EntidadJugador entidad_jugador) {
        observerJugador = new ObserverJugador(this, entidad_jugador);
        imagenFondoPanelNivel.add(observerJugador);
        actualizarInfoJugador(entidad_jugador);
        return observerJugador;
    }

    protected void actualizarInfoJugador(EntidadJugador jugador) {
        actualizarLabelsInformacion(jugador);
        actualizarScrollHaciaJugador(jugador);
    }

    protected void actualizarLabelsInformacion(EntidadJugador jugador) {
        labelPuntaje.setText(formatTexto(jugador.getPuntaje()));
        labelVida.setText(formatTexto(jugador.getVida()));
        labelTiempo.setText(formatTexto(jugador.getTiempo()));
    }

    protected String formatTexto(int numero) {
        return String.format("%05d", numero);
    }

    public void actualizarScrollHaciaJugador(EntidadJugador jugador) {
        int posicionJugadorX = jugador.getPosicionEnX();
        int anchoDeVentana = ConstantesVista.PANEL_ANCHO;
        panelScrollNivel.getHorizontalScrollBar().setValue(posicionJugadorX + anchoDeVentana / 2 + 30);
    }

    public void actualizarObserver() {
        observerJugador.actualizarObserver();
        observerEntidad.actualizarObserver();
    }
}
