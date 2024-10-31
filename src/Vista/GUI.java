package Vista;

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
import Vista.Paneles.*;

import javax.swing.*;
import java.awt.*;

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
    protected HistorialPaneles historialPaneles;
    protected String nombreUsuario;
    protected PanelPantallaNombreUsuario panelPantallaNombreUsuario;
    protected PanelPantallaCarga panelPantallaCarga;
    protected PanelPantallaVictoria panelPantallaVictoria;

    public GUI() {
        configuracion = ConfiguracionJuego.obtenerInstancia();
        ranking = new Ranking();
        this.miJuego = new Juego(this);
        historialPaneles = new HistorialPaneles();

        configurarVentana();
        configurarPaneles();
        mostrarPantallaCarga();
    }

    public void reiniciarPanelPantallaNivel() {
        panelPantallaNivel = new PanelPantallaNivel(this);
    }

    protected void configurarVentana() {
    	ventana = new VentanaManager();
    }
  

    // De interfaz para launcher
    @Override
    public void mostrarPantallaInicial(String modoJuego) {
        configuracion.setModoJuego(modoJuego);
        panelPantallaPrincipal = new PanelPantallaPrincipal(this, modoJuego);
        historialPaneles.push(panelPantallaPrincipal);
        panelPantallaNivel = new PanelPantallaNivel(this);
        panelPantallaRanking = new PanelPantallaRanking(this, ranking);
        ventana.setContenido(panelPantallaPrincipal);
        refrescar();
    }

    // De interfaz ControladorDeVistas
    @Override
    public void accionarInicioJuego(String modoJuego) {
        configuracion.setModoJuego(modoJuego);
        miJuego.iniciar(modoJuego);
    }

    public void crearPantallaPerdiste(String modoJuego) {
        panelPantallaFinJuego = new PanelPantallaPerdiste(this, modoJuego);
    }
    
    public void crearPantallaVictoria(String modoJuego) {
        panelPantallaVictoria = new PanelPantallaVictoria(this, modoJuego);
    }

    public void agregarJugadorAlRanking(String nombreAgregar, int puntajeAgregar) {
        ranking.agregarAlRanking(nombreAgregar, puntajeAgregar);
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
        historialPaneles.push(panelPantallaNivel);
        ventana.setContenido(panelPantallaNivel);
        oyente = new OyenteTeclado();
        panelPantallaNivel.addKeyListener(oyente);
        panelPantallaNivel.setFocusable(true);
        panelPantallaNivel.requestFocusInWindow();
        refrescar();
    }

    @Override
    public void mostrarPantallaFinJuego() {
        historialPaneles.push(panelPantallaFinJuego);
        ventana.setContenido(panelPantallaFinJuego);
        refrescar();
    }
    
    public void mostrarPantallaVictoria() {
    	 historialPaneles.push(panelPantallaVictoria);
         ventana.setContenido(panelPantallaVictoria);
         refrescar();
		
	}

    @Override
    public void mostrarPantallaRanking() {
        historialPaneles.push(panelPantallaRanking);
        ventana.setContenido(panelPantallaRanking);
        refrescar();
    }

    public void mostrarPantallaModoJuego() {
        historialPaneles.push(panelPantallaModoJuego);
        ventana.setContenido(panelPantallaModoJuego);
        refrescar();
    }

    public void volverAlPanelAnterior() {
        JPanel panelAnterior = historialPaneles.pop();
        if (panelAnterior != null) {
            ventana.setContenido(panelAnterior);
            refrescar();
        }
    }

    public void agregarOyenteBotonVolver(JButton botonVolver) {
        botonVolver.addActionListener(e -> volverAlPanelAnterior());
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
        ventana.setContenido(panelPantallaNombreUsuario);
        refrescar();
    }

    @Override
    public int obtenerPuntajeJugador() {
        return miJuego.getPuntaje();
    }

    public void actualizarImagenFondoNivel(int nivel) {
        panelPantallaNivel.actualizarImagenFondoNivel(nivel);
    }

    private void configurarPaneles() {
        panelPantallaModoJuego = new PanelPantallaModoJuego(this);
    }

    private void mostrarPantallaCarga() {
        panelPantallaCarga = new PanelPantallaCarga();
        ventana.setContenido(panelPantallaCarga);
        refrescar();


        Timer timer = new Timer(4000, e -> mostrarPantallaNombreUsuario());
        timer.setRepeats(false);
        timer.start();
    }

	@Override
	public void actualizarTiempoJuego(int tiempo) {
		panelPantallaNivel.actualizarLabelTiempo(tiempo);
	}

	@Override
	public void refrescar() {
		ventana.refrescar();
	}

	
}
