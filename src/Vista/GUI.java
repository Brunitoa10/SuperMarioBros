package Vista;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import Constantes.ConstantesVista;
import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import GestorArchivos.Ranking;
import Logica.ConfiguracionJuego;
import Logica.Juego;
import Logica.OyenteTeclado;
import Vista.Controladores.ControladorVista;
import Vista.Controladores.ControladorVistaJuego;
import Vista.ObserverGrafica.Observer;
import Vista.Paneles.PanelPantallaCarga;
import Vista.Paneles.PanelPantallaFinJuego;
import Vista.Paneles.PanelPantallaModoJuego;
import Vista.Paneles.PanelPantallaNivel;
import Vista.Paneles.PanelPantallaNombreUsuario;
import Vista.Paneles.PanelPantallaPrincipal;
import Vista.Paneles.PanelPantallaRanking;

public class GUI implements ControladorVista, ControladorVistaJuego {

	protected JFrame ventana;
	protected PanelPantallaNivel panelPantallaNivel;
	protected PanelPantallaPrincipal panelPantallaPrincipal;
	protected PanelPantallaFinJuego panelPantallaFinJuego;
	protected PanelPantallaRanking panelPantallaRanking;
	protected PanelPantallaModoJuego panelPantallaModoJuego;
	protected Ranking ranking;
	protected OyenteTeclado oyente;
	protected ConfiguracionJuego configuracion;
	protected Juego miJuego;
	protected HistorialPaneles historialPaneles;
	protected String nombreUsuario;
	protected PanelPantallaNombreUsuario panelPantallaNombreUsuario;
	protected PanelPantallaCarga panelPantallaCarga;

	public GUI() {
		configuracion = ConfiguracionJuego.obtenerInstancia();
		ranking = new Ranking();
		this.miJuego = new Juego(this);
		historialPaneles = new HistorialPaneles();

		configurarVentana();
		configurarPaneles();
		mostrarPantallaCarga();
	}
	
	public void reiniciarPanelPantallaNivel(){
		panelPantallaNivel = new PanelPantallaNivel(this);
	}

	protected void configurarVentana() {
		ventana = new JFrame("Super Mario Bros - Equipo Basados");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setIconImage(cargarIcono());
		ventana.setSize(ConstantesVista.VENTANA_ANCHO, ConstantesVista.VENTANA_ALTO);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	// De interfaz para launcher
	@Override
	public void mostrarPantallaInicial(String modoJuego) {
		configuracion.setModoJuego(modoJuego);
		panelPantallaPrincipal = new PanelPantallaPrincipal(this, modoJuego);
		historialPaneles.push(panelPantallaPrincipal);
		panelPantallaNivel = new PanelPantallaNivel(this);
		panelPantallaRanking = new PanelPantallaRanking(this, ranking);
		ventana.setContentPane(panelPantallaPrincipal);
		refrescar();
	}

	// De interfaz ControladorDeVistas
	@Override
	public void accionarInicioJuego(String modoJuego) {
		configuracion.setModoJuego(modoJuego);
		miJuego.iniciar(modoJuego);
	}

	public void crearPantallaFinJuego(String modoJuego){
		panelPantallaFinJuego = new PanelPantallaFinJuego(this, modoJuego);
	}

	public void agregarJugadorAlRanking(String nombreAgregar, int puntajeAgregar){
		System.out.println(nombreAgregar);
		System.out.println(puntajeAgregar);
		ranking.agregarAlRanking(nombreAgregar,puntajeAgregar);
		panelPantallaRanking.llenarTabla();
	}

	@Override
	public void accionarPantallaRanking() {
		miJuego.mostrarPantallaRanking();
	}

	@Override
	public void accionarPantallaModoJuego() {
		mostrarPantallaModoJuego();
	}

	public void actualizarLabels(){
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
		Observer observerEntidad = panelPantallaNivel.incorporarEntidad(entidadLogica);
		refrescar();
		return observerEntidad;
	}

	@Override
	public Observer registrarEntidad(EntidadJugador entidadJugador) {
		Observer observerJugador = panelPantallaNivel.incorporarEntidadJugador(entidadJugador, miJuego);
		refrescar();
		return observerJugador;
	}

	public OyenteTeclado obtenerOyente(){
		return oyente;
	}

	@Override
	public void mostrarPantallaNivel() {
		historialPaneles.push(panelPantallaNivel);
		ventana.setContentPane(panelPantallaNivel);
		oyente = new OyenteTeclado();
		panelPantallaNivel.addKeyListener(oyente);
		panelPantallaNivel.setFocusable(true);
		panelPantallaNivel.requestFocusInWindow();
		refrescar();
	}

	@Override
	public void mostrarPantallaFinJuego() {
		historialPaneles.push(panelPantallaFinJuego);
		ventana.setContentPane(panelPantallaFinJuego);
		refrescar();
	}

	@Override
	public void mostrarPantallaRanking() {
		historialPaneles.push(panelPantallaRanking);
		ventana.setContentPane(panelPantallaRanking);
		refrescar();
	}

	public void mostrarPantallaModoJuego() {
		historialPaneles.push(panelPantallaModoJuego);
		ventana.setContentPane(panelPantallaModoJuego);
		refrescar();
	}

	public void volverAlPanelAnterior() {
		JPanel panelAnterior = historialPaneles.pop();
		if (panelAnterior != null) {
			ventana.setContentPane(panelAnterior);
			refrescar();
		}
	}

	public void agregarOyenteBotonVolver(JButton botonVolver) {
		botonVolver.addActionListener(e -> volverAlPanelAnterior());
	}

	@Override
	public void refrescar() {
		ventana.revalidate();
		ventana.repaint();
	}

	@Override
	public void actualizarObserver() {
		panelPantallaNivel.actualizarObservadores();
	}

	@Override
	public OyenteTeclado oyenteTeclado() {
		return oyente;
	}

	public Juego getJuego() {
		return miJuego;
	}

	@Override
	public String obtenerModoJuego() {
		return configuracion.getModoJuego();
	}

	@Override
	public Ranking obtenerRanking() {
		return ranking;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String obtenerNombreUsuario() {
		return nombreUsuario;
	}

	public void mostrarPantallaNombreUsuario() {
		panelPantallaNombreUsuario = new PanelPantallaNombreUsuario(this);
		historialPaneles.push(panelPantallaNombreUsuario);
		ventana.setContentPane(panelPantallaNombreUsuario);
		refrescar();
	}

	@Override
	public int obtenerPuntajeJugador() {
		return miJuego.getPuntaje();
	}

	public void actualizarImagenFondoNivel(int nivel) {
		panelPantallaNivel.actualizarImagenFondoNivel(nivel);
	}


	private Image cargarIcono() {
		return Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/Recursos/imagenes/original/Mario.png"));
	}

	private void configurarPaneles() {
		panelPantallaModoJuego = new PanelPantallaModoJuego(this);
	}
	
	private void mostrarPantallaCarga() {
		panelPantallaCarga = new PanelPantallaCarga();
		ventana.setContentPane(panelPantallaCarga);
		refrescar();

	
		Timer timer = new Timer(4000, e -> mostrarPantallaNombreUsuario());
		timer.setRepeats(false);
		timer.start();
	}
}
