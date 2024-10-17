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
    protected HiloRestoEntidades hiloRestoEntidades;
    protected OyenteTeclado oyenteTeclado;
    protected String modoJuego;

    public Juego(ControladorVistaJuego controladorVistas) {
    	 this.controladorVistas = controladorVistas;
    }

    // Comunicacion con parte grafica
    public void setControladorVistas(ControladorVistaJuego controladorVistas) {
        this.controladorVistas = controladorVistas;
    }

    public void iniciar(String modoJuego) {
    	
    	this.modoJuego = modoJuego;
    	System.out.println("Modojuego juego "+modoJuego);
        // Inicializar fábricas antes de generar el nivel
        fabricaSprites = new FabricaSpritesOriginal("src/Recursos/Sprites/" + modoJuego);
        fabricaEntidades = new CreadorEntidad(fabricaSprites);
        generadorNivel = new GeneradorNivel(fabricaEntidades);
        
        System.out.println("Antes generar nivel juego ");
        // Generar el nivel actual
        nivelActual = generadorNivel.generarNivel(1);
        System.out.println("Despues generar nivel juego ");
        // Registrar los observers
        System.out.println("Antes registrar observers");
        registrarObservers();
        System.out.println("Despues registrarObservers");
        System.out.println("Logica mostrar modo de juego: " + modoJuego);
        
        // Mostrar la pantalla del nivel
        controladorVistas.mostrarPantallaNivel();
        
        // Iniciar el bucle del juego y otros hilos
        loopMario = new LoopMario(this);
        loopMario.comenzar();
        hiloRestoEntidades = new HiloRestoEntidades(this);
        hiloRestoEntidades.comenzar();

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
