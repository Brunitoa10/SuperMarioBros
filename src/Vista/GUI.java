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
    protected PanelPantallaNivel panelPantallaNivel;
    protected PanelPantallaPrincipal panelPantallaPrincipal;
    protected PanelPantallaPerdiste panelPantallaFinJuego;
    protected PanelPantallaRanking panelPantallaRanking;
    protected PanelPantallaModoJuego panelPantallaModoJuego;
    protected Ranking ranking;
    protected OyenteTeclado oyente;
    protected ConfiguracionJuego configuracion;
    protected Juego miJuego;
    protected String nombreUsuario;
    protected PanelPantallaNombreUsuario panelPantallaNombreUsuario;
    protected PanelPantallaCarga panelPantallaCarga;
    protected PanelPantallaVictoria panelPantallaVictoria;
    protected GestorPaneles gestorPaneles;

    public GUI() {
    	ventana = new VentanaManager();
        configuracion = ConfiguracionJuego.obtenerInstancia();
        ranking = new Ranking();
        miJuego = new Juego(this);
        gestorPaneles = new GestorPaneles(ventana, new HistorialPaneles());

        mostrarPantallaCarga();
    }

    public void reiniciarPanelPantallaNivel() {
        panelPantallaNivel = new PanelPantallaNivel(this);
    }

    // De interfaz para launcher
    @Override
    public void mostrarPantallaInicial(String modoJuego) {
        configuracion.setModoJuego(modoJuego);
        panelPantallaPrincipal = new PanelPantallaPrincipal(this, modoJuego);
        gestorPaneles.mostrarPantalla(panelPantallaPrincipal);
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
    	panelPantallaRanking = new PanelPantallaRanking(this, ranking);
    	agregarJugadorAlRanking(nombreUsuario, miJuego.getPuntaje());
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

    public OyenteTeclado obtenerOyente() {
        return oyente;
    }

    @Override
    public void mostrarPantallaNivel() {
    	panelPantallaNivel = new PanelPantallaNivel(this);
        oyente = new OyenteTeclado();
        panelPantallaNivel.addKeyListener(oyente);
        panelPantallaNivel.setFocusable(true);
        gestorPaneles.mostrarPantalla(panelPantallaNivel);
        panelPantallaNivel.requestFocusInWindow();
    }

    @Override
    public void mostrarPantallaFinJuego(String modoJuego) {
    	panelPantallaFinJuego = new PanelPantallaPerdiste(this, modoJuego);
    	gestorPaneles.mostrarPantalla(panelPantallaFinJuego);
    }
    
    public void mostrarPantallaVictoria(String modoJuego) {
    	 panelPantallaVictoria = new PanelPantallaVictoria(this, modoJuego);
         gestorPaneles.mostrarPantalla(panelPantallaVictoria);	
	}

    @Override
    public void mostrarPantallaRanking() {
    	gestorPaneles.mostrarPantalla(panelPantallaRanking);
    }

    public void mostrarPantallaModoJuego() {
    	panelPantallaModoJuego = new PanelPantallaModoJuego(this);
    	gestorPaneles.mostrarPantalla(panelPantallaModoJuego);
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
        panelPantallaNombreUsuario = new PanelPantallaNombreUsuario(this);
        gestorPaneles.mostrarPantalla(panelPantallaNombreUsuario);
    }

    public void actualizarImagenFondoNivel(int nivel) {
        panelPantallaNivel.actualizarImagenFondoNivel(nivel);
    }

	@Override
	public void actualizarTiempoJuego(int tiempo) {
		panelPantallaNivel.actualizarLabelTiempo(tiempo);
	}

	@Override
	public void refrescar() {
		gestorPaneles.refrescar();
	}

	@Override
	public void volverAlPanelAnterior() {
		gestorPaneles.volverAlPanelAnterior();
	}
	
    private void mostrarPantallaCarga() {
        panelPantallaCarga = new PanelPantallaCarga();
        gestorPaneles.mostrarPantalla(panelPantallaCarga);

        Timer timer = new Timer(4000, e -> mostrarPantallaNombreUsuario());
        timer.setRepeats(false);
        timer.start();
    }
}
