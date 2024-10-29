package Logica;

import Constantes.AnimadorMario;
import Constantes.CadenasValidacion;
import Constantes.ConstantesPuntaje;
import Entidades.Entidad;
import Entidades.EntidadLogica;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.CreadorEntidad;
import Fabricas.FabricaEntidad;
import Fabricas.FabricaSpriteRegistro;
import Fabricas.FabricaSprites;
import Generador.GeneradorNivel;
import Generador.GestorSonido.Sonido;
import Generador.GestorSonido.SonidoFactory;
import Vista.Controladores.ControladorVistaJuego;
import Vista.GUI;
import Vista.ObserverGrafica.Observer;

import java.util.List;

public class Juego {

	protected FabricaEntidad fabricaEntidades;
    protected Sonido sonido;
    protected GUI controladorVistas;
    protected GeneradorNivel generadorNivel;
    protected Nivel nivelActual;
    protected LoopMario loopMario;
    protected HiloRestoEntidades hiloRestoEntidades;
    protected OyenteTeclado oyenteTeclado;
    protected String modoJuego;
    protected FabricaSpriteRegistro fabricaSpritesRegistry;
    protected ControladorMovimientoMario controladorMovimientoMario;
    protected ControladorBolasDeFuego controladorBolasDeFuego;
    protected FabricaSprites fabricaSprites;
    protected Temporizador temporizador;
    protected boolean frenarTick;
    protected int nivel;
    protected int vidas;
    protected int puntaje;
    protected int tiempoJuego;
    protected boolean animacionGanando = false;
    
	public Juego(GUI controladorVistas) {
		this.controladorVistas = controladorVistas;
		this.fabricaSpritesRegistry = new FabricaSpriteRegistro();
		inicializarAtributos();
	}

    private void inicializarAtributos() {
    	vidas = 3;
		puntaje = 0;
		nivel = 1;
		tiempoJuego = 0;
		frenarTick = false;
		temporizador = new Temporizador();
	}

	public int getVidas() {
        return vidas;
    }

    public void perderVida() {
        vidas--;
    }

    public void sumarVida() {
        vidas++;
        controladorVistas.actualizarLabels();
    }

    public void sumarPuntaje(int puntajeParaSumar) {
        puntaje += puntajeParaSumar;
        if (puntaje < 0)
            puntaje = 0;

        controladorVistas.actualizarLabels();
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void mostrarPantallaFinJuego() {
        controladorVistas.crearPantallaPerdiste(modoJuego);
        controladorVistas.mostrarPantallaFinJuego();
    }
    
    public void mostrarPantallaVictoria() {
        controladorVistas.crearPantallaVictoria(modoJuego);
        controladorVistas.mostrarPantallaVictoria();
    }

    public void iniciar(String modoJuego) {
        this.modoJuego = modoJuego;

        fabricaSprites = fabricaSpritesRegistry.obtenerFabrica(modoJuego);
        fabricaEntidades = new CreadorEntidad(fabricaSprites);
        generadorNivel = new GeneradorNivel(fabricaEntidades);
        sonido = SonidoFactory.crearSonido(modoJuego, "nivel");

        nivelActual = generadorNivel.generarNivel(nivel);

        registrarObservers();

        controladorVistas.mostrarPantallaNivel();
        oyenteTeclado = controladorVistas.obtenerOyente();

        controladorMovimientoMario = new ControladorMovimientoMario(nivelActual.getJugador(), oyenteTeclado);
        controladorBolasDeFuego = new ControladorBolasDeFuego(nivelActual.getJugador(), oyenteTeclado);

        iniciarLoops();
    }
    
    public void agregarTiempoAlTotal(int tiempoNivelActual) {
        tiempoJuego += tiempoNivelActual;
    }
    

    public int getTiempo() {
        return tiempoJuego; //hacer
    }

    private void iniciarLoops() {
        loopMario = new LoopMario(this);
        loopMario.comenzar();
        hiloRestoEntidades = new HiloRestoEntidades(this);
        hiloRestoEntidades.comenzar();
        sonido.reproducir();
    }

    public void reiniciar(String nivelActual) {
        detenerLoops();
        controladorVistas.reiniciarPanelPantallaNivel();
        iniciar(nivelActual);
    }

    public void nivelSiguiente() {
        int tiempoAux = tiempoJuego;
    	sonido.detener();
        detenerLoops();
        if (nivel < CadenasValidacion.MAXIMO_NIVELES) {
            nivel++;
            nivelActual = generadorNivel.generarNivel(nivel);
            controladorVistas.actualizarImagenFondoNivel(nivel);
            registrarObservers();
            controladorVistas.mostrarPantallaNivel();
            controladorVistas.actualizarLabels();
            sonido = SonidoFactory.crearSonido(modoJuego, "nivel");
            controladorMovimientoMario = new ControladorMovimientoMario(nivelActual.getJugador(), oyenteTeclado);
            controladorBolasDeFuego = new ControladorBolasDeFuego(nivelActual.getJugador(), oyenteTeclado);
            iniciarLoops();
        } else {
           mostrarPantallaVictoria();
        }
    }

    public void detenerLoops() {
        loopMario.detener();
        hiloRestoEntidades.detener();
        sonido.detener();
    }

    public void mostrarPantallaRanking() {
        controladorVistas.mostrarPantallaRanking();
    }

    protected void registrarObservers() {
        registrarObserverJugador(nivelActual.getJugador());
        registrarObserversParaEntidades(nivelActual.getPlataformas());
        registrarObserversParaEntidades(nivelActual.getEnemigos());
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

    public Nivel getNivelActual() {
        return nivelActual;
    }

    public void moverMario(Temporizador temporizador) {
        controladorMovimientoMario.moverMario(temporizador);
    }

    public boolean marioCaeAlVacio(Jugador mario) {
        return mario.getPosicionEnY() > 460;
    }

    public void lanzarBolasDeFuego(Jugador mario) {
        if (controladorBolasDeFuego.puedeLanzarBolaDeFuego()) {
            dispararBolaFuego(mario);
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

    public void manejarMuerte() {
        perderVida();
        if (getVidas() != 0) {
            reiniciar(modoJuego);
        } else {
            detenerLoops();
            mostrarPantallaFinJuego();
            vidas = 3;
        }
    }

    public void mostrarMarioMuerte(Jugador mario) {
        mario.getSprite().setRutaImagen(AnimadorMario.MUERTE_MARIO);
    }

    public void checkearSumaVida() {
        if (getJugador().debeSumarUnaVida()) {
            sumarVida();
            sumarPuntaje(ConstantesPuntaje.PUNTAJE_CHAMPINON_VERDE);
            getJugador().sumarUnaVida(false);
        }
    }

    public void frenarHilos() {
        hiloRestoEntidades.pause();
    }

    public void reanudarHilo() {
        hiloRestoEntidades.resume();
    }

    public int nivel() {
        return nivel;
    }

    public void consumirHongo(Jugador mario) {
        temporizador.iniciar();
        frenarHilos();
        aplicarSprite(mario);
        if (temporizador.hanPasadoNSegundos(2000)) {
            frenarTick = false;
            reanudarHilo();
        }
    }

    private void aplicarSprite(Jugador mario) {
        if (mario.getDireccion() == -1)
            mario.getSprite().setRutaImagen("src/Recursos6/Sprites/original/Jugador/PNGMario/MarioPowerUp/ConsumePowerUp/ConsumoHongoLeft.gif");
        if (mario.getDireccion() == +1)
            mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioPowerUp/ConsumePowerUp/ConsumoHongoRigth.gif");
    }

    public boolean frenoElTick() {
        return frenarTick;
    }


    public void setFrenoElTick(boolean frenar) {
        frenarTick = frenar;
    }

    private void dispararBolaFuego(Jugador mario) {
        Proyectil bolaDeFuego = fabricaEntidades.crearBolaDeFuego(mario, nivelActual.getProyectiles());
        getNivelActual().agregarProyectil(bolaDeFuego);
        Observer observer = controladorVistas.registrarEntidad(bolaDeFuego);
        bolaDeFuego.registrarObserver(observer);
    }

    public void setAnimacionGanando(boolean tocoBandera){
        animacionGanando = tocoBandera;
    }

    public boolean animacionGanando() {
        return animacionGanando;
    }

    public void checkearGanarNivel(Jugador mario) {
        if (mario.getPosicionEnX() >= 6150) {
            mario.setVelocidad(1);
            animacionGanando = true;
        }
    }

    public void hacerAnimacionGanar(Jugador mario) {
        mario.desplazarEnX(1);

        if (mario.getPosicionEnX() > 6350) {
            mario.setVelocidad(CadenasValidacion.MARIO_VELOCIDAD);
            nivelSiguiente();
            animacionGanando = false;
        }
    }
}