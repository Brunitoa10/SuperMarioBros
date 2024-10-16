package Logica;

import Entidades.Entidad;
import Entidades.Jugador;
import Fabricas.CreadorEntidad;
import Fabricas.FabricaEntidad;
import Fabricas.FabricaSprites;
import Fabricas.FabricaSpritesOriginal;
import Generador.GeneradorNivel;
import Vista.Controladores.ControladorVistaJuego;
import Vista.ObserverGrafica.Observer;
import java.util.List;

public class Juego {

    protected ControladorVistaJuego controladorVistas;
    protected GeneradorNivel generadorNivel;
    protected FabricaSprites fabricaSprites;
    protected FabricaEntidad fabricaEntidades;
    protected Nivel nivelActual;
    protected LoopMario loopMario;
    protected OyenteTeclado oyenteTeclado;

    public Juego() {
        fabricaSprites = new FabricaSpritesOriginal("src/Recursos/Sprites/Originales");
        fabricaEntidades = new CreadorEntidad(fabricaSprites);
        generadorNivel = new GeneradorNivel(fabricaEntidades);

    }

    // Comunicacion con parte grafica
    public void setControladorVistas(ControladorVistaJuego controladorVistas) {
        this.controladorVistas = controladorVistas;
    }

    public void iniciar() {
        nivelActual = generadorNivel.generarNivel(1);
        registrarObservers();
        System.out.println("Logica mostrar modo de juego");
        controladorVistas.mostrarPantallaNivel();
        loopMario = new LoopMario(this);
        loopMario.comenzar();
    }

    public void reiniciar(Nivel nivel) {
        // Implemetar
    }

    public void mostrarPantallaRanking() {
        controladorVistas.mostrarPantallaRanking();
    }

    protected void registrarObservers() {
        registrarObserverJugador(nivelActual.getJugador());
        registrarObserversParaEntidades(nivelActual.getEnemigos());
        registrarObserversParaEntidades(nivelActual.getPlataformas());
        registrarObserversParaEntidades(nivelActual.getPowerUps());
        registrarObserversParaEntidades(nivelActual.getProyectiles());
    }

    protected void registrarObserverJugador(Jugador jugador) {
        Observer observer_jugador = controladorVistas.registrarEntidad(jugador);
        jugador.registrar_observer(observer_jugador);
    }

    protected void registrarObserversParaEntidades(List<? extends Entidad> entidades) {
        for (Entidad entidad : entidades) {
            Observer observer = controladorVistas.registrarEntidad(entidad);
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
        return controladorVistas;
    }

    public Jugador getJugador() {
        return nivelActual.getJugador();
    }
}
