package Vista;

import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import GestorArchivos.Ranking;
import Logica.ConfiguracionJuego;
import Logica.Juego;
import Logica.OyenteTeclado;
import Vista.Controladores.ControladorVista;
import Vista.Controladores.ControladorVistaJuego;
import Vista.ObserverGrafica.Observer;
import Vista.Paneles.*;

import javax.swing.*;

public class GUI implements ControladorVista, ControladorVistaJuego {

	protected VentanaManager ventana;
	protected Ranking ranking;
	protected OyenteTeclado oyente;
	protected ConfiguracionJuego configuracion;
	protected Juego miJuego;
	protected String nombreUsuario;
	protected GestorPaneles gestorPaneles;
	protected PanelFactory panelFactory;
	protected PanelPantallaNivel panelPantallaNivel;
	protected PanelPantallaRanking panelPantallaRanking;

	public GUI() {
		panelFactory = new PanelFactory(this,this);
		inicializarComponentes();
		mostrarPantallaNombreUsuario();
	}
	
	public void reiniciarPanelPantallaNivel() {
		panelPantallaNivel = panelFactory.crearPanelPantallaNivel();
	}

	// De interfaz para launcher
	@Override
	public void mostrarPantallaInicial(String modoJuego) {
		configuracion.setModoJuego(modoJuego);
		gestorPaneles.mostrarPantalla(panelFactory.crearPanelPantallaPrincipal(modoJuego));
		refrescar();
	}

	// De interfaz ControladorDeVistas
	@Override
	public void accionarInicioJuego(String modoJuego) {
		configuracion.setModoJuego(modoJuego);
		miJuego.iniciar(modoJuego);

	}

	public void agregarJugadorAlRanking(String nombreAgregar, int puntajeAgregar) {
		ranking.agregarAlRanking(nombreAgregar, puntajeAgregar);
		panelPantallaRanking.llenarTabla();
	}

	@Override
	public void accionarPantallaRanking() {
		panelPantallaRanking = panelFactory.crearPanelPantallaRanking(ranking);
		mostrarPantallaRanking();
	}

	@Override
	public void accionarPantallaModoJuego() {
		mostrarPantallaModoJuego();
	}

	public void actualizarLabels() {
		panelPantallaNivel.actualizarLabelsInformacion(miJuego);
	}

	@Override
	public void cambiarModoJuego(String modo) {
		configuracion.setModoJuego(modo);
		mostrarPantallaInicial(modo);
	}

	// De interfaz ComandosJuegoVista
	@Override
	public Observer registrarEntidad(EntidadLogica entidadLogica) {
		return panelPantallaNivel.incorporarEntidad(entidadLogica);
	}

	@Override
	public Observer registrarEntidad(EntidadJugador entidadJugador) {
		return panelPantallaNivel.incorporarEntidadJugador(entidadJugador, miJuego);
	}

	public OyenteTeclado obtenerOyente() {
		return oyente;
	}

	@Override
	public void mostrarPantallaNivel() {
		panelPantallaNivel = panelFactory.crearPanelPantallaNivel();
		oyente = new OyenteTeclado();
		panelPantallaNivel.addKeyListener(oyente);
		panelPantallaNivel.setFocusable(true);
		gestorPaneles.mostrarPantalla(panelPantallaNivel);
		panelPantallaNivel.requestFocusInWindow();
	}

	@Override
	public void mostrarPantallaFinJuego(String modoJuego) {
		gestorPaneles.mostrarPantalla(panelFactory.crearPanelPantallaPerdiste(modoJuego));
	}

	public void mostrarPantallaVictoria(String modoJuego) {
		gestorPaneles.mostrarPantalla(panelFactory.crearPanelPantallaVictoria(modoJuego));	
	}

	@Override
	public void mostrarPantallaRanking() {
		gestorPaneles.mostrarPantalla(panelPantallaRanking);
	}

	public void mostrarPantallaModoJuego() {
		gestorPaneles.mostrarPantalla(panelFactory.crearPanelPantallaModoJuego());
	}

	@Override
	public void actualizarObserver() {
		panelPantallaNivel.actualizarObservadores();
	}

	public Juego getJuego() {
		return miJuego;
	}

	@Override
	public String obtenerModoJuego() {
		return configuracion.getModoJuego();
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public void mostrarPantallaNombreUsuario() {
		gestorPaneles.mostrarPantalla(panelFactory.crearPanelPantallaNombreUsuario());
	}

	public void actualizarImagenFondoNivel(int nivel) {
		panelPantallaNivel.actualizarImagenFondoNivel(nivel);
	}

	@Override
	public void actualizarTiempoJuego(Juego miJuego) {
		panelPantallaNivel.actualizarLabelTiempo(miJuego);
	}

	@Override
	public void refrescar() {
		gestorPaneles.refrescar();
	}

	@Override
	public void volverAlPanelAnterior() {
		gestorPaneles.volverAlPanelAnterior();
	}
	
	private void inicializarComponentes() {
		ventana = new VentanaManager();
		configuracion = ConfiguracionJuego.obtenerInstancia();
		ranking = new Ranking();
		miJuego = new Juego(this);
		gestorPaneles = new GestorPaneles(ventana, new HistorialPaneles());
	}

}
