package Logica;

import Entidades.Jugador;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class LoopMario implements Runnable {

    private final long updateInterval = 16_000_000; // Aproximadamente 60 FPS
    protected ControladorColisiones controladorColisiones;
    protected int timerAnimacionMorir = 0;
    protected Juego juego;
    protected Temporizador temporizadorBolasDeFuego;
    protected boolean debeSaltar;
    protected boolean FrenoElTick;
    protected boolean ejecutando;
    protected Jugador mario;
    protected boolean puedoEliminar = false;
    protected ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    protected long lastUpdateTime = System.nanoTime();

    public LoopMario(Juego juego) {
        this.mario = juego.getNivelActual().getJugador();
        this.controladorColisiones = new ControladorColisiones(juego.getNivelActual(), juego);
        this.juego = juego;
        inicializarTemporizadores();
        inicializarBooleanos();
    }

    private void inicializarBooleanos() {
        debeSaltar = false;
        ejecutando = false;
        FrenoElTick = false;
    }

    private void inicializarTemporizadores() {
        temporizadorBolasDeFuego = new Temporizador();
    }

    public synchronized void comenzar() {
        ejecutando = true;
        Thread hilo = new Thread(this);
        hilo.start();
        temporizadorBolasDeFuego.iniciar();
    }

    public synchronized void detener() {
        ejecutando = false;
        scheduler.shutdown();
        temporizadorBolasDeFuego.pausar();
    }

    @Override
    public void run() {
        while (ejecutando) {
            long now = System.nanoTime();
            if (now - lastUpdateTime >= updateInterval) {
                lastUpdateTime = now;
                tick();
                renderizar();
            }
        }
    }

    private void tick() {
        juego.sumarTiempo();
        if (!juego.frenoElTick()) {
            puedoEliminar = false;
            if (!mario.getMorir() && !juego.sinTiempo() || juego.animacionGanando()) {

                juego.checkearGanarNivel(mario);

                if (!juego.animacionGanando())
                    juego.moverMario(temporizadorBolasDeFuego);
                else
                    juego.hacerAnimacionGanar(mario);

                juego.lanzarBolasDeFuego(mario);
                if (controladorColisiones.colisionesMario())
                    juego.setFrenoElTick(true);
                if (juego.marioCaeAlVacio(mario)) {
                    mario.setMorir(true);
                }

                juego.checkearSumaVida();

                mario.getEstadoJugador().actualizarEstadoJugador();
                mario.actualizarEntidad();
            } else {
                juego.mostrarMarioMuerte(mario);
                if (timerAnimacionMorir == 0) {
                    mario.sonidoMorir();
                    juego.pausarSonidoNivel();
                }
                empezarCooldownMorir();
                if (timerAnimacionMorir == 155) {
                    juego.manejarMuerte();
                }
            }
        }else{
            controladorColisiones.colisionMarioPlataformaTickFrenado(juego.getNivelActual().getPlataformas(),mario);
            mario.actualizarEntidad();
        }
        puedoEliminar = true;
    }

    public void renderizar(){
        juego.getControladorVistaJuego().actualizarObserver();
        juego.getControladorVistaJuego().refrescar();
    }

    private void empezarCooldownMorir() {
        timerAnimacionMorir++;
    }


}