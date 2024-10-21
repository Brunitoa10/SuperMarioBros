package Vista;

import java.awt.Toolkit;

import javax.swing.JFrame;

import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import Logica.ConfiguracionJuego;
import Logica.Juego;
import Logica.OyenteTeclado;
import Logica.Ranking;
import Vista.Controladores.ConstantesVista;
import Vista.Controladores.ControladorVista;
import Vista.Controladores.ControladorVistaJuego;
import Vista.ObserverGrafica.Observer;
import Vista.Paneles.PanelPantallaFinJuego;
import Vista.Paneles.PanelPantallaModoJuego;
import Vista.Paneles.PanelPantallaNivel;
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

    public GUI() {
        configuracion = ConfiguracionJuego.obtenerInstancia();
        ranking = new Ranking();
        this.miJuego = new Juego(this);

        registrarOyenteVentana();
        configurarVentana();
        configurarPaneles();
    }

    private void configurarPaneles() {
        panelPantallaModoJuego = new PanelPantallaModoJuego(this);
    }

    protected void configurarVentana() {
        ventana = new JFrame("Super Mario Bros - Equipo Basados");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setIconImage(Toolkit.getDefaultToolkit()
                .getImage(GUI.class.getResource("/Recursos/imagenes/" + configuracion.getModoJuego() + "/Mario.png")));
        ventana.setSize(ConstantesVista.VENTANA_ANCHO, ConstantesVista.VENTANA_ALTO);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    protected void registrarOyenteVentana() {
        // To DO
    }

    // De interfaz para launcher
    @Override
    public void mostrarPantallaInicial(String modoJuego) {
        configuracion.setModoJuego(modoJuego); // Actualizamos el modo de juego en ConfiguracionJuego
        panelPantallaPrincipal = new PanelPantallaPrincipal(this, modoJuego);
        panelPantallaNivel = new PanelPantallaNivel(this);
        panelPantallaRanking = new PanelPantallaRanking(this, ranking);
        ventana.setContentPane(panelPantallaPrincipal);
        refrescar();
    }

    // De interfaz ControladorDeVistas
    @Override
    public void accionarInicioJuego(String modoJuego) {
        configuracion.setModoJuego(modoJuego); // Actualizar el modo de juego
        miJuego.iniciar(modoJuego);
    }

    @Override
    public void accionarPantallaRanking() {
        miJuego.mostrarPantallaRanking();
    }

    @Override
    public void accionarPantallaModoJuego() {
        System.out.println("Seleccione un modo de juego");
        mostrarPantallaModoJuego();
    }

    @Override
    public void cambiarModoJuego(String modo) {
        // Actualizamos el modo de juego en ConfiguracionJuego
        System.out.println("Modo seleccionado: " + modo);
        configuracion.setModoJuego(modo);
        mostrarPantallaInicial(modo); // Vuelve a la pantalla principal o inicia el juego
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
        Observer observerJugador = panelPantallaNivel.incorporarEntidadJugador(entidadJugador);
        refrescar();
        return observerJugador;
    }

    @Override
    public void mostrarPantallaNivel() {
        ventana.setContentPane(panelPantallaNivel);
        oyente = new OyenteTeclado();
        panelPantallaNivel.addKeyListener(oyente);
        panelPantallaNivel.setFocusable(true);
        panelPantallaNivel.requestFocusInWindow();
        refrescar();
    }

    @Override
    public void mostrarPantallaFinJuego() {
        ventana.setContentPane(panelPantallaFinJuego);
        refrescar();
    }

    @Override
    public void mostrarPantallaRanking() {
        ventana.setContentPane(panelPantallaRanking);
        refrescar();
    }

    public void mostrarPantallaModoJuego() {
        ventana.setContentPane(panelPantallaModoJuego);
        refrescar();
    }

    @Override
    public void refrescar() {
        ventana.revalidate();
        ventana.repaint();
    }

    @Override
    public void actualizarObserver() {
        panelPantallaNivel.actualizarObserver();
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
        return configuracion.getModoJuego(); // Obtenemos el modo directamente de ConfiguracionJuego
    }
}
