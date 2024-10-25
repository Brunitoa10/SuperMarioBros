package Logica;

import java.util.List;

import Animador.AnimadorMario;
import Entidades.Entidad;
import Entidades.EntidadLogica;
import Entidades.Jugador;
import Entidades.Proyectiles.BolaDeFuego;
import Entidades.Proyectiles.Proyectil;
import Fabricas.CreadorEntidad;
import Fabricas.FabricaEntidad;
import Fabricas.FabricaSpriteRegistro;
import Fabricas.FabricaSprites;
import Generador.GeneradorNivel;
import Vista.Controladores.ControladorVista;
import Vista.Controladores.ControladorVistaJuego;
import Vista.GUI;
import Vista.ObserverGrafica.Observer;

public class Juego {

    protected GUI controladorVistas;
    protected GeneradorNivel generadorNivel;
    protected FabricaSprites fabricaSprites;
    protected FabricaEntidad fabricaEntidades;
    protected Nivel nivelActual;
    protected LoopMario loopMario;
    protected HiloRestoEntidades hiloRestoEntidades;
    protected OyenteTeclado oyenteTeclado;
    protected String modoJuego;
    protected FabricaSpriteRegistro fabricaSpritesRegistry;
    protected int vidas;
    protected int puntaje;
    protected ControladorMovimientoMario controladorMovimientoMario;
    protected ControladorBolasDeFuego controladorBolasDeFuego;


    public Juego(GUI controladorVistas) {
        this.controladorVistas = controladorVistas;
        this.fabricaSpritesRegistry = new FabricaSpriteRegistro();
        vidas = 3;
        puntaje = 5;
    }

    // Comunicacion con parte grafica
    public void setControladorVistas(GUI  controladorVistas) {
        this.controladorVistas = controladorVistas;
    }

    public int getVidas(){
        return vidas;
    }

    public void perderVida(){
        vidas--;
    }

    public void sumarVida(){
        vidas++;
        controladorVistas.actualizarLabels();
    }



    public void sumarPuntaje(int puntajeParaSumar){
        puntaje += puntajeParaSumar;
        if (puntaje < 0)
            puntaje = 0;

        controladorVistas.actualizarLabels();
    }

    public void restarPuntaje(int puntajeParaRestar){
        puntaje -= puntajeParaRestar;
        controladorVistas.actualizarLabels();
    }

    public int getPuntaje(){
        return puntaje;
    }

    public void mostrarPantallaFinJuego(){
        controladorVistas.crearPantallaFinJuego(modoJuego);
        controladorVistas.mostrarPantallaFinJuego();
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
        oyenteTeclado = controladorVistas.obtenerOyente();

        controladorMovimientoMario = new ControladorMovimientoMario(nivelActual.getJugador(), oyenteTeclado);
        controladorBolasDeFuego = new ControladorBolasDeFuego(nivelActual.getJugador(), oyenteTeclado);

        iniciarLoops();
    }

    private void iniciarLoops() {
        loopMario = new LoopMario(this);
        loopMario.comenzar();
        hiloRestoEntidades = new HiloRestoEntidades(this);
        hiloRestoEntidades.comenzar();
    }

    public void reiniciar(String nivelactual) {
        loopMario.detener();
        hiloRestoEntidades.detener();
        controladorVistas.reiniciarPanelPantallaNivel();
        iniciar(nivelactual);

    }
    public void detenerLoops(){
        loopMario.detener();
        hiloRestoEntidades.detener();
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
        registrarObserversParaEntidades(nivelActual.getMonedas());
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


    public void eliminarObserversParaEntidades(List<? extends Entidad> entidades){
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

    public void moverMario(Temporizador temporizador) {
        controladorMovimientoMario.moverMario(temporizador);
    }

    public void lanzarBolasDeFuego(Jugador mario) {
        if (controladorBolasDeFuego.puedeLanzarBolaDeFuego()) {
            Proyectil bolaDeFuego = dispararBolaFuego(mario);
            mario.getEstadoMovimiento().LanzarBola();
        }
    }

    public void eliminarEntidades() {
        while (!nivelActual.getEntidadesAEliminar().isEmpty()) {
            EntidadLogica entidadAEliminar = nivelActual.getEntidadesAEliminar().getFirst();
            entidadAEliminar.getObserver().eliminarDePanel();
            entidadAEliminar.eliminarEntidad();
            nivelActual.getEntidadesAEliminar().removeFirst();
        }
    }

    public ControladorVistaJuego getControladorVistaJuego() {
        return controladorVistas;
    }

    public Jugador getJugador() {
        return nivelActual.getJugador();
    }

    public Proyectil dispararBolaFuego(Jugador mario) {
        Proyectil bolaDeFuego = fabricaEntidades.crearBolaDeFuego(mario, nivelActual.getProyectiles());
        getNivelActual().agregarProyectil(bolaDeFuego);
        Observer observer = controladorVistas.registrarEntidad(bolaDeFuego);
        bolaDeFuego.registrarObserver(observer);
        return bolaDeFuego;
    }

    public void manejarMuerte() {
        perderVida();
        if (getVidas()!=0) {
            reiniciar(modoJuego);
        }
        else{
            detenerLoops();
            mostrarPantallaFinJuego();
            vidas = 3;
        }
    }

    public void mostrarMarioMuerte(Jugador mario) {
        mario.getSprite().setRutaImagen(AnimadorMario.MUERTE_MARIO);
    }

    public int getTiempo() {
        return 0; //hacer
    }

    public void checkearSumaVida() {
        if(getJugador().debeSumarUnaVida()) {
            sumarVida();
            getJugador().sumarUnaVida(false);
        }
    }
}