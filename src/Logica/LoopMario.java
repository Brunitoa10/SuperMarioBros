package Logica;

import Entidades.Jugador;
import Entidades.Plataformas.Plataforma;
import Entidades.Power_Ups.PowerUp;
import Entidades.Vacio;
import EstadoMovimiento.MarioCaminando;
import EstadoMovimiento.MarioEnAire;
import EstadoMovimiento.MarioParado;
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
    private int VelocidadMario;
    private ControladorVistaJuego controlador;
    private static final int GRAVEDAD = 1;
    private static final int SUELO_Y = 420;
    private int direccionLocal = 0;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private boolean enIdle;
    private long lastUpdateTime = System.nanoTime();
    private final long updateInterval = 16_000_000; // Aproximadamente 60 FPS
    protected List<Plataforma> plataformas;
    protected List<Vacio> vacios;
    protected List<PowerUp> powerUps;
    protected boolean caerAlInfinito = false;
    protected boolean EstadoActivado = false;

    public LoopMario(Juego juego) {
        this.mario = juego.getNivelActual().getJugador();
        this.plataformas = juego.getNivelActual().getPlataformas();
        this.powerUps=juego.getNivelActual().getPowerUps();
        this.vacios = juego.getNivelActual().getVacios();
        this.controlador = juego.getControladorVistaJuego();
        ejecutando = false;
    }

    public synchronized void comenzar() {
        ejecutando = true;
        VelocidadMario = mario.getVelocidad();
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
        OyenteTeclado oyente = controlador.oyenteTeclado();
        boolean actualizacionRequerida = false;

        // Movimiento lateral
        if (oyente.teclaIzquierda || oyente.teclaDerecha ) {
            enIdle = false;
            if (oyente.teclaIzquierda) {
                mario.setVelocidad(VelocidadMario);
                mario.desplazarEnX(-1);
                direccionLocal = -1;

            } else {
                mario.setVelocidad(VelocidadMario);
                mario.desplazarEnX(1);
                direccionLocal = 1;
            }

        }
            if(!oyente.teclaIzquierda && !oyente.teclaDerecha && !oyente.teclaArriba && (mario.estaEnPlataforma()))
                mario.setEstadoMovimiento(new MarioParado(mario));




        // LÃ³gica de salto
        if (oyente.teclaArriba && (mario.estaEnPlataforma())) {
            if (mario.estaEnPlataforma())
                mario.setEnPlataforma(false);
            enIdle = false;
            mario.saltar();
            actualizacionRequerida = true;
        }
        mario.setDireccion(direccionLocal);


        for (Plataforma p : plataformas) {
            if (mario.detectarColision((p))) {
                mario.getVisitorJugador().visit(p);
                p.actualizarEntidad();
            }
        }

        for(PowerUp p : powerUps) {
            if (mario.detectarColision((p))) {
                p.getVisitor().visit(mario);
                p.actualizarEntidad();
                powerUps.remove(p);
                actualizacionRequerida=true;
                mario.getEstadoJugador().actualizarSprite();
            }
        }

        for (Vacio vacio : vacios) {
            if(VacioColisionoAbajo(vacio)){

                if (mario.estaEnPlataforma()) {
                    mario.setEstadoMovimiento(new MarioEnAire(mario));
                    mario.setEnPlataforma(false);
                }
                else{

                }

                //hacer
                if (mario.getPosicionEnY() == SUELO_Y){
                    caerAlInfinito=true;
                    mario.setEstadoMovimiento(new MarioEnAire(mario));

                }

            }
        }

        if (actualizacionRequerida) {

             // Evitar movimiento no deseado
        }
        mario.getEstadoJugador().actualizarSprite();
        mario.actualizarEntidad();
        mario.desplazarEnX(0);
    }


    private void renderizar() {
        controlador.actualizarObserver();
        controlador.refrescar();
    }

    private boolean VacioColisionoAbajo(Vacio vacio) {
        boolean Colisiono = false;
        int tolerancia=5;
        if ((mario.getPosicionEnX() >= vacio.getPosicionEnX()-tolerancia) && mario.getPosicionEnX()+mario.getHitbox().getWidth() <= vacio.getPosicionEnX()+vacio.getHitbox().getWidth()+tolerancia ) {
            if (mario.getPosicionEnY() + mario.getHitbox().getHeight() <= vacio.getPosicionEnY()) {
                Colisiono = true;
            }
        }
        return Colisiono;
    }
}