package Vista;

import java.awt.Toolkit;

import javax.swing.JFrame;

import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
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
    protected PanelPantallaNivel panel_pantalla_nivel;
    protected PanelPantallaPrincipal panel_pantalla_principal;
    protected PanelPantallaFinJuego panel_pantalla_fin_juego;
    protected PanelPantallaRanking panel_pantalla_ranking;
    protected PanelPantallaModoJuego panel_pantalla_modo_juego;
    protected Ranking ranking;
    protected OyenteTeclado oyente;

    protected Juego mi_juego;
    protected int nivel;

    public GUI(Juego juego) {
        ranking = new Ranking();
        this.mi_juego = juego;
        this.nivel = 1;
        registrar_oyente_ventana();
        panel_pantalla_nivel = new PanelPantallaNivel();
        panel_pantalla_principal = new PanelPantallaPrincipal(this);
        panel_pantalla_ranking = new PanelPantallaRanking(this, ranking);
        panel_pantalla_modo_juego = new PanelPantallaModoJuego(this);
        configurar_ventana();

    }

    protected void configurar_ventana() {
        ventana = new JFrame("Super Mario Bros - Equipo Basados");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setIconImage(
                Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/Recursos/imagenes/Mario.png")));
        ventana.setSize(ConstantesVista.VENTANA_ANCHO, ConstantesVista.VENTANA_ALTO);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    protected void registrar_oyente_ventana() {
        // To DO
    }

    // De interfaz para launcher

    public void mostrar_pantalla_inicial() {
        ventana.setContentPane(panel_pantalla_principal);

        refrescar();
    }

    // De interfaz ControladorDeVistas
    public void accionar_inicio_juego() {
        mi_juego.iniciar();
    }

    public void accionar_pantalla_ranking() {
        mi_juego.mostrar_pantalla_ranking();
    }

    public void accionar_pantalla_modo_juego() {
        System.out.println("Seleccione un modo de juego");
        mostrar_pantalla_modo_juego();
    }

    public void cambiar_modo_juego(String modo) {
        // Lógica para cambiar el modo de juego
        System.out.println("Modo seleccionado: " + modo);
        // Dependiendo del modo, podrías inicializar configuraciones o cambiar la lógica
        // del juego.
        mostrar_pantalla_inicial(); // Vuelve a la pantalla principal o inicia el juego
    }

    // De interfaz ComandosJuegoVista

    public Observer registrar_entidad(EntidadLogica entidad_logica) {
        Observer observer_entidad = panel_pantalla_nivel.incorporar_entidad(entidad_logica);
        refrescar();
        return observer_entidad;
    }

    public Observer registrar_entidad(EntidadJugador entidad_jugador) {
        Observer observer_jugador = panel_pantalla_nivel.incorporar_entidad_jugador(entidad_jugador);
        refrescar();
        return observer_jugador;
    }

    public void mostrar_pantalla_nivel() {
        ventana.setContentPane(panel_pantalla_nivel);

        oyente = new OyenteTeclado();
        panel_pantalla_nivel.addKeyListener(oyente);
        panel_pantalla_nivel.setFocusable(true);
        panel_pantalla_nivel.requestFocusInWindow();

        refrescar();
    }

    public void mostrar_pantalla_fin_juego() {
        ventana.setContentPane(panel_pantalla_fin_juego);
        refrescar();
    }

    @Override
    public void mostrar_pantalla_ranking() {
        ventana.setContentPane(panel_pantalla_ranking);
        refrescar();
    }

    public void mostrar_pantalla_modo_juego() {
        ventana.setContentPane(panel_pantalla_modo_juego);
        refrescar();
    }

    public void refrescar() {
        ventana.revalidate();
        ventana.repaint();
    }

    public void actualizarObserver() {
        panel_pantalla_nivel.actualizarObserver();
    }

    @Override
    public OyenteTeclado oyenteTeclado() {
        return oyente;
    }

}