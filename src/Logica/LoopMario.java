package Logica;

import Entidades.Entidad;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import EstadoMovimiento.MarioEnAire;
import EstadoMovimiento.MarioParado;
import Fabricas.Sprite;
import Vista.Controladores.ControladorVistaJuego;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Constantes.AnimadorMario;
import EstadoMovimiento.MarioCaminando;
import EstadoMovimiento.MarioParado;


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
    protected Temporizador temporizador;
    protected boolean debeSaltar;
    protected ControladorBolasDeFuego controladorBolasDeFuego;
    protected boolean FrenoElTick=false;
    protected Temporizador temporizador2;

    public LoopMario(Juego juego) {
        this.mario = juego.getNivelActual().getJugador();

        this.controladorColisiones = new ControladorColisiones(juego.getNivelActual(),juego);

        ejecutando = false;
        temporizador = new Temporizador();
        temporizador2= new Temporizador();
        this.juego = juego;
        debeSaltar = false;
    }

    public synchronized void comenzar() {
        ejecutando = true;
        Thread hilo = new Thread(this);
        hilo.start();
        temporizador.iniciar();
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
        if(!FrenoElTick) {
            if (!mario.getMorir()) {
                juego.moverMario(temporizador);
                juego.lanzarBolasDeFuego(mario);
                FrenoElTick=controladorColisiones.colisionesMario();
                juego.eliminarEntidades();
                if (mario.getPosicionEnY() > 460) {
                    mario.setMorir(true);
                }

                juego.checkearSumaVida();

                mario.getEstadoJugador().actualizarSprite();
                mario.actualizarEntidad();
            } else {
                juego.mostrarMarioMuerte(mario);
                empezarCooldownMorir();
                if (timerAnimacionMorir == 100) {
                    juego.manejarMuerte();
                }
            }
        }else{
            temporizador2.iniciar();
            juego.frenarHilos();
            if(mario.getDireccion()==-1)
                mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioPowerUp/ConsumePowerUp/ConsumoHongoLeft.gif");
            if(mario.getDireccion()==+1)
                mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioPowerUp/ConsumePowerUp/ConsumoHongoRigth.gif");
            if(temporizador2.hanPasadoNSegundos(2000)){
                FrenoElTick=false;
                juego.reanudarHilo();
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