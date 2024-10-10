package Vista;

import javax.swing.JFrame;

import Entidades.EntidadJugador;
import Entidades.EntidadLogica;
import Logica.Juego;

public class GUI implements ControladorVista, ControladorVistaJuego {

    protected JFrame ventana;
    protected PanelPantallaNivel panel_pantalla_nivel;
    protected PanelPantallaPrincipal panel_pantalla_principal;
    protected Juego mi_juego;

    public GUI(Juego juego) {
        this.mi_juego = juego;
        panel_pantalla_nivel = new PanelPantallaNivel();
        panel_pantalla_principal = new PanelPantallaPrincipal()
        configurar_ventana();
        registrar_oyente_ventana();
    }

    protected void configurar_ventana() {
        ventana = new JFrame("Super Mario Bros - Equipo Basados");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
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

    public void accionar_pantalla_puntajes() {
        // To Do
    }

    public void accionar_pantalla_modo_juego() {
        // To Do
    }

    public void cambiar_modo_juego(int modo) {
        // To Do
        mostrar_pantalla_inicial();
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

    /*
     * public Observer registrar_silueta(EntidadLogica silueta) {
     * Observer observer_silueta = panel_pantalla_nivel.incorporar_silueta(silueta);
     * refrescar();
     * return observer_silueta;
     * }
     */

    public void mostrar_pantalla_carrera() {
        ventana.setContentPane(panel_pantalla_nivel);
        refrescar();
    }

    public void mostrar_pantalla_fin_juego() {

    }

    protected void refrescar() {
        ventana.revalidate();
        ventana.repaint();
    }

}