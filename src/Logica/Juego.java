package Logica;

import java.util.List;

import Entidades.Entidad;
import Entidades.Jugador;
import Fabricas.CreadorEntidad;
import Fabricas.FabricaEntidad;
import Fabricas.FabricaSpriteRegistro;
import Fabricas.FabricaSprites;
import Generador.GeneradorNivel;
import Vista.Controladores.ControladorVistaJuego;
import Vista.ObserverGrafica.Observer;

public class Juego {

    protected ControladorVistaJuego controladorVistas;
    protected GeneradorNivel generadorNivel;
    protected FabricaSprites fabricaSprites;
    protected FabricaEntidad fabricaEntidades;
    protected Nivel nivelActual;
    protected LoopMario loopMario;
    protected HiloRestoEntidades hiloRestoEntidades;
    protected OyenteTeclado oyenteTeclado;
    protected String modoJuego;
    protected FabricaSpriteRegistro fabricaSpritesRegistry;

    public Juego(ControladorVistaJuego controladorVistas) {
        this.controladorVistas = controladorVistas;
        this.fabricaSpritesRegistry = new FabricaSpriteRegistro();
    }

    // Comunicacion con parte grafica
    public void setControladorVistas(ControladorVistaJuego controladorVistas) {
        this.controladorVistas = controladorVistas;
    }

    public void iniciar(String modoJuego) {

        this.modoJuego = modoJuego;

        System.out.println("Modojuego juego " + modoJuego);
        fabricaSprites = fabricaSpritesRegistry.obtenerFabrica(modoJuego);

        fabricaEntidades = new CreadorEntidad(fabricaSprites);
        generadorNivel = new GeneradorNivel(fabricaEntidades);

        nivelActual = generadorNivel.generarNivel(1);

        registrarObservers();

        System.out.println("Logica mostrar modo de juego: " + modoJuego);

        controladorVistas.mostrarPantallaNivel();
        iniciarLoops();
    }

    private void iniciarLoops() {
        loopMario = new LoopMario(this);
        loopMario.comenzar();
        hiloRestoEntidades = new HiloRestoEntidades(this);
        hiloRestoEntidades.comenzar();
    }

    public void reiniciar(Nivel nivel) {

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
        Observer observerJugador = controladorVistas.registrarEntidad(jugador);
        jugador.registrarObserver(observerJugador);
    }

    protected void registrarObserversParaEntidades(List<? extends Entidad> entidades) {
        for (Entidad entidad : entidades) {
            Observer observer = controladorVistas.registrarEntidad(entidad);
            entidad.registrarObserver(observer);
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
