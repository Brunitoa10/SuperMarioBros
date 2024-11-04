package Logica;

import Constantes.AnimadorMario;
import Constantes.CadenasValidacion;
import Constantes.ConstantesPuntaje;
import Entidades.Enemigos.Enemigo;
import Entidades.Entidad;
import Entidades.EntidadLogica;
import Entidades.Jugador;
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
import GestorArchivos.Ranking;

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
    protected int tiempoJuego;
    protected boolean animacionGanando;
    protected ControladorVidasMario controladorVidasMario;
    protected ControladorPuntaje controladorPuntaje;
    protected Temporizador timerGanarJuego;

    public Juego(GUI controladorVistas) {
        animacionGanando = false;
        this.controladorVistas = controladorVistas;
        this.fabricaSpritesRegistry = new FabricaSpriteRegistro();
        inicializarAtributos();
    }

    private void inicializarAtributos() {
        nivel = 3;
        tiempoJuego = 0;
        frenarTick = false;
        temporizador = new Temporizador();
        controladorPuntaje = new ControladorPuntaje();
        controladorVidasMario = new ControladorVidasMario();
        timerGanarJuego = new Temporizador();
    }

    public int getVidas() {
        return controladorVidasMario.getVidas();
    }

    public void perderVida() {
        controladorVidasMario.perderVida();
        controladorVistas.actualizarLabels();
    }

    public void sumarVida() {
        controladorVidasMario.sumarVida();
        controladorVistas.actualizarLabels();
    }

    public void sumarTiempo() {
        tiempoJuego++;

        if (tiempoJuego % 60 == 0) {
            controladorVistas.actualizarTiempoJuego(this);
        }
    }

    public void sumarPuntaje(int puntajeParaSumar) {
        controladorPuntaje.sumarPuntaje(puntajeParaSumar);
        if (controladorPuntaje.getPuntaje() < 0)
            controladorPuntaje.setPuntaje(0);

        controladorVistas.actualizarLabels();
    }

    public int getPuntaje() {
        return controladorPuntaje.getPuntaje();
    }

    public void iniciar(String modoJuego) {
        this.modoJuego = modoJuego;

        fabricaSprites = fabricaSpritesRegistry.obtenerFabrica(modoJuego);
        fabricaEntidades = new CreadorEntidad(fabricaSprites);
        generadorNivel = new GeneradorNivel(fabricaEntidades);

        sonido = SonidoFactory.crearSonido(modoJuego, "nivel");

        nivelActual = generadorNivel.generarNivel(nivel);
        controladorVistas.mostrarPantallaNivel();
        oyenteTeclado = controladorVistas.obtenerOyente();
        controladorMovimientoMario = new ControladorMovimientoMario(nivelActual.getJugador(), oyenteTeclado);
        controladorBolasDeFuego = new ControladorBolasDeFuego(nivelActual.getJugador(), oyenteTeclado, this);
        registrarObservers();
        iniciarLoops();
        nivelActual.getJugador().setRutaOrigen(fabricaSprites.getRuta_carpeta());
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
        tiempoJuego = 0;
        sonido.detener();
        detenerLoops();
        if (nivel < CadenasValidacion.MAXIMO_NIVELES) {
            nivel++;
            nivelActual = generadorNivel.generarNivel(nivel);
            controladorVistas.actualizarImagenFondoNivel(nivel);
            controladorVistas.actualizarLabels();
            iniciar(modoJuego);
        }
    }

    public void detenerLoops() {
        loopMario.detener();
        hiloRestoEntidades.detener();
        sonido.detener();
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
        boolean caeAlVacio = false;
        if (mario.getPosicionEnY() > 460) {
            controladorPuntaje.sumarPuntaje(ConstantesPuntaje.PUNTAJE_VACIO_MUERTE_MARIO);
            caeAlVacio = true;
        }
        return caeAlVacio;
    }

    public void lanzarBolasDeFuego(Jugador mario) {
        if (controladorBolasDeFuego.puedeLanzarBolaDeFuego()) {
            nivelActual.getJugador().getEstadoJugador().lanzarBolaDeFuego(controladorBolasDeFuego);
        }
    }

    public void eliminarEntidades() {
        while (!nivelActual.getEntidadesAEliminar().isEmpty()) {
            EntidadLogica entidadAEliminar = nivelActual.getEntidadesAEliminar().getFirst();
            entidadAEliminar.getObserver().eliminarDePanel();
            entidadAEliminar.eliminarEntidad();
            nivelActual.getEntidadesAEliminar().removeFirst();
        }
        hiloRestoEntidades.renderizar();
    }

    public ControladorVistaJuego getControladorVistaJuego() {
        return controladorVistas;
    }

    public Jugador getJugador() {
        return nivelActual.getJugador();
    }


    public void manejarMuerte() {
        perderVida();
        tiempoJuego = 0;
        if (getVidas() != 0) {
            reiniciar(modoJuego);
        } else {
            controladorPuntaje.setPuntaje(0);
            detenerLoops();
            controladorVistas.mostrarPantallaFinJuego(modoJuego);
            controladorVidasMario.setVidas(3);
            nivel = 1;
        }
    }

    public void mostrarMarioMuerte(Jugador mario) {
        System.out.println(getJugador().getSprite().getRutaModo()+AnimadorMario.MUERTE_MARIO);
        mario.getSprite().setRutaImagen(getJugador().getSprite().getRutaModo()+AnimadorMario.MUERTE_MARIO);
    }

    public void checkearSumaVida() {
        if (getJugador().debeSumarUnaVida()) {
            sumarVida();
            sumarPuntaje(ConstantesPuntaje.PUNTAJE_CHAMPINON_VERDE);
            getJugador().sumarUnaVida(false);
        }
    }

    public int nivel() {
        return nivel;
    }

    public boolean frenoElTick() {
        return frenarTick;
    }


    public void setFrenoElTick(boolean frenar) {
        frenarTick = frenar;
    }

    public boolean animacionGanando() {
        return animacionGanando;
    }

    public void checkearGanarNivel(Jugador mario) {
        if ((mario.getPosicionEnX() >= 6150) && ((nivelActual.nivel == 1) || (nivelActual.nivel == 2))) {
            mario.setVelocidad(1);
            animacionGanando = true;

        } else if ((mario.getPosicionEnX() >= 6775) && (nivelActual.nivel == 3)) {
            mario.setVelocidad(0);
            animacionGanando = true;
            timerGanarJuego.iniciar();
        }
    }

    public void hacerAnimacionGanar(Jugador mario) {
        mario.desplazarEnX(1);

        if ((mario.getPosicionEnX() > 6350) && ((nivelActual.nivel == 1) || (nivelActual.nivel == 2))) {
            mario.setVelocidad(CadenasValidacion.MARIO_VELOCIDAD);
            nivelSiguiente();
            if (animacionGanando) {
                controladorPuntaje.sumarPuntaje(200);
                controladorVistas.actualizarLabels();
            }
            animacionGanando = false;
        }
        if (timerGanarJuego.hanPasadoNSegundos(2000) && mario.estaEnPlataforma()) {
            mario.saltar();
        }
        if (timerGanarJuego.hanPasadoNSegundos(5000)) {
            detenerLoops();
            controladorVistas.mostrarPantallaVictoria(modoJuego);
            if (animacionGanando) {
                controladorPuntaje.sumarPuntaje(300);
                controladorVistas.actualizarLabels();
            }
            animacionGanando = false;
            controladorVistas.agregarJugadorAlRanking(controladorPuntaje.getPuntaje());
        }
    }

    public void pausarSonidoNivel() {
        sonido.detener();
    }

    public int getTiempo() {
        return Math.max(nivelActual.getTiempoMaximoNivel() - ticksASegundos(), 0);
    }

    private int ticksASegundos() {
        return tiempoJuego / 60;
    }

    public boolean sinTiempo() {
        return ticksASegundos() >= nivelActual.getTiempoMaximoNivel();
    }

    public boolean marioEstaCerca(Jugador mario, Enemigo enemigo) {
        return Math.abs(mario.getPosicionEnX() - enemigo.getPosicionEnX()) <= 600;
    }

}