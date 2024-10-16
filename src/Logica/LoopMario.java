package Logica;

import Entidades.Jugador;
import Entidades.Plataformas.Plataforma;
import Vista.Controladores.ControladorVistaJuego;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoopMario implements Runnable {

    private boolean ejecutando;
    private Jugador mario;
    private int VelocidadMario;
    private ControladorVistaJuego controlador;
    private static final int GRAVEDAD = 1;
    private static final int SUELO_Y = 420;
    private int direccionLocal = 0;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private boolean enIdle = false;
    private static final String MARIO_AFK = "src/Recursos/Sprites/Originales/AnimacionMarioIdle.gif";
    private long lastUpdateTime = System.nanoTime();
    private final long updateInterval = 16_000_000; // Aproximadamente 60 FPS
    protected List<Plataforma> plataformas;


    public LoopMario(Juego juego) {
        this.mario = juego.getNivelActual().getJugador();
        this.plataformas = juego.getNivelActual().getPlataformas();
        this.controlador = juego.getControladorVistaJuego();
        ejecutando = false;
    }

    public synchronized void comenzar() {
        ejecutando = true;
        VelocidadMario=mario.get_velocidad();
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
                    mario.getSprite().setRutaImagen(MARIO_AFK);
                }
            }, 3, 3, TimeUnit.SECONDS);
        }
    }

    private void tick() {
        OyenteTeclado oyente = controlador.oyenteTeclado();
        boolean actualizacionRequerida = false;

        // Movimiento lateral
        if (oyente.teclaIzquierda || oyente.teclaDerecha) {
            enIdle = false;
            if (oyente.teclaIzquierda) {
                mario.desplazarEnX(-1);
                direccionLocal = -1;
                actualizacionRequerida = true;
            } else {
                mario.desplazarEnX(1);
                direccionLocal = 1;
                actualizacionRequerida = true;
            }

            // Actualiza el sprite basado en el estado
            String spritePath = mario.getEstadoMovimiento().estaEnElSuelo()
                    ? (direccionLocal == -1 ? "src/Recursos/Sprites/Originales/Jugador/PNGMario/RunningLoop/MarioCaminandoLeft.gif"
                    : "src/Recursos/Sprites/Originales/Jugador/PNGMario/RunningLoop/MarioCaminandoRight.gif")
                    : (direccionLocal == -1 ? "src/Recursos/Sprites/Originales/Jugador/PNGMario/JumpingMarioLeft.png"
                    : "src/Recursos/Sprites/Originales/Jugador/PNGMario/JumpingMarioRigth.png");
            mario.getSprite().setRutaImagen(spritePath);
        } else {
            // Lógica para idle
            if (!enIdle && mario.getEstadoMovimiento().estaEnElSuelo()) {
                enIdle = true;
                mario.getSprite().setRutaImagen(direccionLocal == -1
                        ? "src/Recursos/Sprites/Originales/Jugador/PNGMario/StandingMarioLeft.png"
                        : "src/Recursos/Sprites/Originales/Jugador/PNGMario/StandingMarioRigth.png");
                iniciarTemporizadorIdle();
            }
        }

        // Lógica de salto
        if (oyente.teclaArriba && mario.getEstadoMovimiento().estaEnElSuelo()) {
            enIdle = false;
            mario.saltar();
            String jumpSprite = direccionLocal == 1
                    ? "src/Recursos/Sprites/Originales/Jugador/PNGMario/JumpingMarioRigth.png"
                    : "src/Recursos/Sprites/Originales/Jugador/PNGMario/JumpingMarioLeft.png";
            mario.getSprite().setRutaImagen(jumpSprite);
            actualizacionRequerida = true;
        }

        // Gravedad
        if (!mario.getEstadoMovimiento().estaEnElSuelo()) {
            mario.setPosicionEnY(mario.getPosicionEnY() + GRAVEDAD);
            if (mario.getPosicionEnY() >= SUELO_Y) {
                mario.setPosicionEnY(SUELO_Y);
            }
            actualizacionRequerida = true;
        }

        for(Plataforma p : plataformas) {
            System.out.println(mario.detectarColision(p));
            if(mario.detectarColision((p))){
                mario.getVJ().visit(p);
                if(p.getPosicionEnX()>mario.getPosicionEnX()){
                    if(p.getPosicionEnY()+32<mario.getPosicionEnY()){
                        mario.set_velocidad(VelocidadMario);
                    }
                    if(direccionLocal==-1){
                        mario.set_velocidad(VelocidadMario);
                    }
                }else{
                    if(direccionLocal==1){
                        mario.set_velocidad(VelocidadMario);
                    }
                }

            }
        }

        if (actualizacionRequerida) {
            mario.actualizarEntidad();
            mario.desplazarEnX(0);
        }
    }

    private void renderizar() {
        controlador.actualizarObserver();
        controlador.refrescar();
    }
}