package Vista.Paneles;

import Constantes.ConstantesVista;
import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import Logica.Juego;
import Vista.Controladores.ControladorVista;
import Vista.ObserverGrafica.Observer;
import Vista.ObserverGrafica.ObserverEntidad;
import Vista.ObserverGrafica.ObserverJugador;

import javax.swing.*;
import java.awt.*;

public class PanelPantallaNivel extends JPanel {

    protected static final long serialVersionUID = 1L;

    protected JLabel imagenFondoPanelNivel;
    protected JLabel labelPuntaje;
    protected JLabel labelVida;
    protected JLabel labelTiempo;

    protected ObserverEntidad observerEntidad;
    protected ObserverJugador observerJugador;
    protected JScrollPane panelScrollNivel;
    protected JPanel panelSuperior;
    protected JPanel panelImagen;
    protected ControladorVista controladorVista;
    protected String rutaImagen;

    public PanelPantallaNivel(ControladorVista controladorVista) {
        this.controladorVista = controladorVista;
        configurarPanelPrincipal();
        inicializarComponentes();
    }


    public Observer incorporarEntidad(EntidadLogica entidadLogica) {
        observerEntidad = new ObserverEntidad(entidadLogica, this);
        imagenFondoPanelNivel.add(observerEntidad);
        actualizarObservadores();
        return observerEntidad;
    }

    public Observer incorporarEntidadJugador(EntidadJugador entidadJugador, Juego miJuego) {
        observerJugador = new ObserverJugador(this, entidadJugador);
        imagenFondoPanelNivel.add(observerJugador);
        actualizarInfoJugador(entidadJugador, miJuego);
        return observerJugador;
    }

    public void eliminarEntidad(ObserverEntidad observerEntidad) {
        imagenFondoPanelNivel.remove(observerEntidad);
    }

    public void eliminarEntidadJugador(ObserverJugador observerJugador) {
        imagenFondoPanelNivel.remove(observerJugador);
    }

    private void actualizarInfoJugador(EntidadJugador jugador, Juego miJuego) {
        actualizarLabelsInformacion(miJuego);
        ajustarScrollHaciaJugador(jugador);
    }

    public void actualizarLabelsInformacion(Juego miJuego) {
        labelPuntaje.setText(String.format("Puntaje: %05d", miJuego.getPuntaje()));
        labelVida.setText(String.format("Vidas: %d", miJuego.getVidas()));
    }

    public void ajustarScrollHaciaJugador(EntidadJugador jugador) {
        int posicionJugadorX = jugador.getPosicionEnX();
        int anchoDeVentana = ConstantesVista.PANEL_ANCHO;
        panelScrollNivel.getHorizontalScrollBar().setValue(posicionJugadorX + anchoDeVentana / 2 + 30);
    }

    public void actualizarObservadores() {
        if (observerJugador != null) {
            observerJugador.actualizarObserver();
        }
        if (observerEntidad != null) {
            observerEntidad.actualizarObserver();
        }
    }

    public void actualizarImagenFondoNivel(int nivel) {
        cargarImagenFondo();
        actualizarObservadores();
        refrescarPaneles();
    }

    private void refrescarPaneles() {
        panelImagen.revalidate();
        panelImagen.repaint();
        panelScrollNivel.revalidate();
        panelScrollNivel.repaint();
        revalidate();
        repaint();
    }

    private void configurarPanelPrincipal() {
        setPreferredSize(new Dimension(ConstantesVista.PANEL_ANCHO, ConstantesVista.PANEL_ALTO));
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        agregarPanelSuperior();
        configurarPanelNivelConScroll();
    }

    private void agregarPanelSuperior() {
        panelSuperior = crearPanelSuperior();
        add(panelSuperior, BorderLayout.NORTH);
    }

    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(Box.createHorizontalGlue());

        labelVida = crearLabel("Vidas: 0");
        labelPuntaje = crearLabel("Puntaje: 0");
        labelTiempo = crearLabel("Tiempo: 0");

        panel.add(labelVida);
        panel.add(Box.createHorizontalGlue());
        panel.add(labelPuntaje);
        panel.add(Box.createHorizontalGlue());
        panel.add(labelTiempo);
        panel.add(Box.createHorizontalGlue());

        return panel;
    }

    private JLabel crearLabel(String texto) {
        return new JLabel(texto);
    }

    private void configurarPanelNivelConScroll() {
        panelImagen = new JPanel(new GridBagLayout());
        cargarImagenFondo();

        panelScrollNivel = new JScrollPane(panelImagen);
        configurarScroll(panelScrollNivel);

        add(panelScrollNivel, BorderLayout.CENTER);
    }

    private void configurarScroll(JScrollPane scrollPane) {
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
    }

    private void cargarImagenFondo() {
        String rutaImagen = obtenerRutaImagen();
        imagenFondoPanelNivel = new JLabel(new ImageIcon(getClass().getResource(rutaImagen)));
        imagenFondoPanelNivel.setPreferredSize(new Dimension(imagenFondoPanelNivel.getIcon().getIconWidth(), imagenFondoPanelNivel.getIcon().getIconHeight()));

        panelImagen.removeAll();
        panelImagen.add(imagenFondoPanelNivel);
    }

    private String obtenerRutaImagen() {
        return "/Recursos/Fondos/" + controladorVista.obtenerModoJuego() + "/FondoLevel" + controladorVista.getJuego().nivel() + ".png";
    }


	public void actualizarLabelTiempo(int tiempo) {
		labelTiempo.setText(String.format("Tiempo: %05d", tiempo));
	}

}
