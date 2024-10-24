package Logica;

import Entidades.Entidad;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import EstadoMovimiento.MarioParado;
import Fabricas.Sprite;
import Vista.Controladores.ControladorVistaJuego;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import EstadoMovimiento.MarioCaminando;
import EstadoMovimiento.MarioParado;

import Animador.AnimadorMario;


public class LoopMario implements Runnable {

    private boolean ejecutando;
    private Jugador mario;
    private int direccionLocal = 0;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private boolean enIdle;
    private long lastUpdateTime = System.nanoTime();
    private final long updateInterval = 16_000_000; // Aproximadamente 60 FPS
    protected int cooldownBola = 60;
    protected boolean empezarCooldown;
    protected Proyectil bolaDeFuego;
    protected ControladorColisiones controladorColisiones;
    protected int timerAnimacionMorir =0;
    protected Juego juego;
    protected boolean debeSaltar;
    protected ControladorBolasDeFuego controladorBolasDeFuego;

    public LoopMario(Juego juego) {
        this.mario = juego.getNivelActual().getJugador();
        this.controladorColisiones = new ControladorColisiones(juego.getNivelActual());
        ejecutando = false;
        this.juego = juego;
        debeSaltar = false;
    }

    public synchronized void comenzar() {
        ejecutando = true;
        Thread hilo = new Thread(this);
        hilo.start();
    }

    public synchronized void detener() {
        ejecutando = false;
        scheduler.shutdown();
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

    private void iniciarTemporizadorIdle() {
        if (scheduler.isShutdown()) {
            scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(() -> {
                if (enIdle) {
                    mario.getSprite().setRutaImagen(AnimadorMario.MARIO_AFK);
                }
            }, 3, 3, TimeUnit.SECONDS);
        }
    }

    private void tick() {
        OyenteTeclado oyente = juego.getControladorVistaJuego().oyenteTeclado();
        if(!mario.getMorir()) {
        	debeSaltar = oyente.teclaArriba && mario.estaEnPlataforma();
            juego.moverMario(debeSaltar);

            juego.lanzarBolasDeFuego(mario);


            controladorColisiones.colisionMarioConPlataforma(juego.getNivelActual().getPlataformas(), mario);

            while(!juego.getNivelActual().getEntidadesAEliminar().isEmpty()) {
                juego.getNivelActual().getPlataformas().remove(juego.getNivelActual().getEntidadesAEliminar().getFirst());
                juego.getNivelActual().getEntidadesAEliminar().remove(juego.getNivelActual().getEntidadesAEliminar().getFirst());
            }

            controladorColisiones.colisionMarioConEnemigos(juego.getNivelActual().getEnemigos(), mario);

            while(!juego.getNivelActual().getEntidadesAEliminar().isEmpty()) {
                juego.getNivelActual().getEnemigos().remove(juego.getNivelActual().getEntidadesAEliminar().getFirst());
                juego.getNivelActual().getEntidadesAEliminar().remove(juego.getNivelActual().getEntidadesAEliminar().getFirst());
            }

            controladorColisiones.colisionMarioConMonedas(juego.getNivelActual().getMonedas(), mario);

            while(!juego.getNivelActual().getEntidadesAEliminar().isEmpty()) {
                juego.getNivelActual().getMonedas().remove(juego.getNivelActual().getEntidadesAEliminar().getFirst());
                juego.getNivelActual().getEntidadesAEliminar().remove(juego.getNivelActual().getEntidadesAEliminar().getFirst());
            }

            controladorColisiones.colisionMarioConPowerUps(juego.getNivelActual().getPowerUps(), mario);

            while(!juego.getNivelActual().getEntidadesAEliminar().isEmpty()) {
                juego.getNivelActual().getPowerUps().remove(juego.getNivelActual().getEntidadesAEliminar().getFirst());
                juego.getNivelActual().getEntidadesAEliminar().remove(juego.getNivelActual().getEntidadesAEliminar().getFirst());
            }

            controladorColisiones.colisionMarioConVacio(juego.getNivelActual().getVacios(), mario);

            while(!juego.getNivelActual().getEntidadesAEliminar().isEmpty()) {
                juego.getNivelActual().getVacios().remove(juego.getNivelActual().getEntidadesAEliminar().getFirst());
                juego.getNivelActual().getEntidadesAEliminar().remove(juego.getNivelActual().getEntidadesAEliminar().getFirst());
            }
            controladorColisiones.ColisionConProyectiles(juego.getNivelActual().getProyectiles(), mario);
            if(mario.getPosicionEnY()>460) {
                mario.setMorir(true);
            }

            mario.getEstadoJugador().actualizarSprite();
            mario.actualizarEntidad();

        } else {
            juego.mostrarMarioMuerte(mario);
            empezarCooldownMorir();
            if (timerAnimacionMorir == 100) {
                juego.manejarMuerte();
            }
        }
    }

    private void empezarCooldownMorir() {
        timerAnimacionMorir++;
    }

    private void renderizar() {
        juego.getControladorVistaJuego().actualizarObserver();
    }
}