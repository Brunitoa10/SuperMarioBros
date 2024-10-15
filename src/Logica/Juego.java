package Logica;

import java.util.List;

import Entidades.Entidad;
import Entidades.Jugador;
import Fabricas.CreadorEntidad;
import Fabricas.FabricaEntidad;
import Fabricas.FabricaSprites;
import Fabricas.FabricaSpritesOriginal;
import Generador.GeneradorNivel;
import Vista.Controladores.ControladorVistaJuego;
import Vista.ObserverGrafica.Observer;

public class Juego {

    protected ControladorVistaJuego controlador_vistas;
    protected GeneradorNivel generador_nivel;
    protected FabricaSprites fabrica_sprites;
    protected FabricaEntidad fabrica_entidades;
    protected Nivel nivelActual;
    protected static Juego instanciaJuego;
    protected LoopMario loopMario;
    protected OyenteTeclado oyenteTeclado;

    public Juego() {
        fabrica_sprites = new FabricaSpritesOriginal("src/Recursos/Sprites/Originales");
        fabrica_entidades = new CreadorEntidad(fabrica_sprites);
        generador_nivel = new GeneradorNivel(fabrica_entidades);

    }

    // Comunicacion con parte grafica
    public void set_controlador_vistas(ControladorVistaJuego controlador_vistas) {
        this.controlador_vistas = controlador_vistas;
    }

    public void iniciar() {
        nivelActual = generador_nivel.generarNivel(1);
        registrar_observers();
        System.out.println("Logica mostrar modo de juego");
        controlador_vistas.mostrar_pantalla_nivel();
        loopMario = new LoopMario(this);
        loopMario.comenzar();
    }

    public void reiniciar(Nivel nivel) {
        // Implemetar
    }

    public void mostrar_pantalla_ranking() {
        controlador_vistas.mostrar_pantalla_ranking();
    }

    protected void registrar_observers() {
        registrar_observer_jugador(nivelActual.getJugador());
        registrar_observers_para_entidades(nivelActual.getEnemigos());
        registrar_observers_para_entidades(nivelActual.getPlataformas());
        registrar_observers_para_entidades(nivelActual.getPowerUps());
        registrar_observers_para_entidades(nivelActual.getProyectiles());
    }

    protected void registrar_observer_jugador(Jugador jugador) {
        Observer observer_jugador = controlador_vistas.registrar_entidad(jugador);
        jugador.registrar_observer(observer_jugador);
    }

    protected void registrar_observers_para_entidades(List<? extends Entidad> entidades) {
        for (Entidad entidad : entidades) {
            Observer observer = controlador_vistas.registrar_entidad(entidad);
            entidad.registrar_observer(observer);
        }
    }

    public Nivel getNivelActual() {
        return nivelActual;
    }

    public OyenteTeclado getOyenteTeclado() {
        return oyenteTeclado;
    }

    public ControladorVistaJuego getControladorVistaJuego() {
        return controlador_vistas;
    }

    public Jugador getJugador() {
        return nivelActual.getJugador();
    }
}
