package Vista.Paneles;

import Entidades.Entidad;
import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import Logica.Juego;
import Vista.Controladores.ControladorVista;
import Vista.ObserverGrafica.Observer;
import Vista.ObserverGrafica.ObserverEntidad;
import Vista.ObserverGrafica.ObserverJugador;
import javax.swing.*;

import Constantes.ConstantesVista;

import java.awt.*;
import java.util.Map;

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
	private Map<Entidad, Observer> observers;

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
		imagenFondoPanelNivel = new JLabel(new ImageIcon(getClass().getResource("/Recursos/Fondos/"+controladorVista.obtenerModoJuego()+"/FondoLevel"+controladorVista.getJuego().nivel()+".png")));

		imagenFondoPanelNivel.setPreferredSize(new Dimension(imagenFondoPanelNivel.getIcon().getIconWidth(), imagenFondoPanelNivel.getIcon().getIconHeight()));

		panelImagen = new JPanel();
		panelImagen.setLayout(new GridBagLayout());
		panelImagen.add(imagenFondoPanelNivel);

		panelScrollNivel = new JScrollPane(panelImagen);
		configurarScroll(panelScrollNivel);

		this.add(panelScrollNivel);
	}

	private void configurarScroll(JScrollPane panelScrollNivel) {
		panelScrollNivel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelScrollNivel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
	}


	public Observer incorporarEntidad(EntidadLogica entidadLogica) {
		observerEntidad = new ObserverEntidad(entidadLogica, this);
		imagenFondoPanelNivel.add(observerEntidad);
		actualizarObserver();
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

	protected void actualizarInfoJugador(EntidadJugador jugador, Juego miJuego) {
		actualizarLabelsInformacion(miJuego);
		actualizarScrollHaciaJugador(jugador);
	}

	public void actualizarLabelsInformacion(Juego miJuego) {
		labelPuntaje.setText(formatTexto(miJuego.getPuntaje()));
		labelVida.setText(formatTexto(miJuego.getVidas()));
		labelTiempo.setText(formatTexto(miJuego.getTiempo()));
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
		if(observerJugador != null && observerEntidad != null) {
			observerJugador.actualizarObserver();
			observerEntidad.actualizarObserver();
		}
	}
	
	public void actualizarImagenFondoNivel(int nivel) {
	    // Eliminar la imagen anterior
	    panelImagen.removeAll();

	    // Crear el nuevo fondo de nivel
	    String rutaImagen = "/Recursos/Fondos/" + controladorVista.obtenerModoJuego() + "/FondoLevel" + nivel + ".png";
	    ImageIcon fondoIcon = new ImageIcon(getClass().getResource(rutaImagen));
	    imagenFondoPanelNivel = new JLabel(fondoIcon);

	    // Actualizar tamaño preferido de la nueva imagen
	    imagenFondoPanelNivel.setPreferredSize(new Dimension(fondoIcon.getIconWidth(), fondoIcon.getIconHeight()));

	    // Agregar la nueva imagen al panel contenedor
	    panelImagen.add(imagenFondoPanelNivel);

	    // Agregar nuevamente el jugador y otras entidades si es necesario
	    if (observerJugador != null) {
	        imagenFondoPanelNivel.add(observerJugador);
	    }
	    if (observerEntidad != null) {
	        imagenFondoPanelNivel.add(observerEntidad);
	    }
	    refrescar();
	}
	
	private void refrescar() {
		// Refrescar el panel de imagen
	    panelImagen.revalidate();
	    panelImagen.repaint();

	    // Refrescar el JScrollPane para que muestre los cambios
	    panelScrollNivel.revalidate();
	    panelScrollNivel.repaint();

	    // Finalmente, refrescar el PanelPantallaNivel completo
	    this.revalidate();
	    this.repaint();
	}


}
