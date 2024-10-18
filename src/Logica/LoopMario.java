package Logica;

import Entidades.Jugador;
import Entidades.Plataformas.Plataforma;
import Entidades.Vacio;
import EstadoMovimiento.MarioEnAire;
import EstadoMovimiento.MarioParado;
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
    protected List<Vacio> vacios;


    public LoopMario(Juego juego) {
        this.mario = juego.getNivelActual().getJugador();
        this.plataformas = juego.getNivelActual().getPlataformas();
        this.vacios = juego.getNivelActual().getVacios();
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

            actualizarSprite();

        } else {
                estadoIdle();

        }

        // Lógica de salto
        if (oyente.teclaArriba && (mario.getEstadoMovimiento().estaEnElSuelo() || mario.estaEnPlataforma())) {
            if(mario.estaEnPlataforma())
                mario.setEnPlataforma(false);
            enIdle = false;
            saltar();
            actualizacionRequerida = true;
        }

        // Gravedad
        if (!mario.getEstadoMovimiento().estaEnElSuelo()) {
            gravedad();
            actualizacionRequerida = true;
        }

        for(Plataforma p : plataformas) {
            if(mario.detectarColision((p))){
                mario.getVisitorJugador().visit(p);
            }
        }

        for(Vacio vacio : vacios) {
            System.out.println("Hitbox superior vacio"+(vacio.getPosicionEnY()));
            System.out.println(mario.getPosicionEnY()+mario.getHitbox().getHeight());
            if(mario.getPosicionEnX()-mario.getHitbox().getWidth()<=vacio.getPosicionEnX()-vacio.getHitbox().getWidth()) {
                if (mario.getPosicionEnY() + mario.getHitbox().getHeight() <= vacio.getPosicionEnY()) {
                    System.out.println("EstoyarribaDeVacio");
                    mario.setEstadoMovimiento(new MarioEnAire(mario, 0));
                    mario.setPiso(420);
                }
            }
            if (mario.detectarColision(vacio)) {
                System.out.println("detecto vacio");
                mario.getVisitorJugador().visit(vacio);

                }
        }




        if (actualizacionRequerida) {
            mario.actualizarEntidad();
            mario.desplazarEnX(0);

            if (mario.getPosicionEnY() == 420){
                mario.setPiso(420);
            }

        }
    }

    private void renderizar() {
        controlador.actualizarObserver();
        controlador.refrescar();
    }




    private void actualizarSprite(){
        String spritePath = mario.getEstadoMovimiento().estaEnElSuelo()
                ? (direccionLocal == -1 ? "src/Recursos/Sprites/Originales/Jugador/PNGMario/RunningLoop/MarioCaminandoLeft.gif"
                : "src/Recursos/Sprites/Originales/Jugador/PNGMario/RunningLoop/MarioCaminandoRight.gif")
                : (direccionLocal == -1 ? "src/Recursos/Sprites/Originales/Jugador/PNGMario/JumpingMarioLeft.png"
                : "src/Recursos/Sprites/Originales/Jugador/PNGMario/JumpingMarioRigth.png");
        mario.getSprite().setRutaImagen(spritePath);
    }

    private void estadoIdle(){
        if (!enIdle && mario.getEstadoMovimiento().estaEnElSuelo()) {
            enIdle = true;
            mario.getSprite().setRutaImagen(direccionLocal == -1
                    ? "src/Recursos/Sprites/Originales/Jugador/PNGMario/StandingMarioLeft.png"
                    : "src/Recursos/Sprites/Originales/Jugador/PNGMario/StandingMarioRigth.png");
            iniciarTemporizadorIdle();
        }
    }
    private void saltar(){
        mario.saltar();
        String jumpSprite = direccionLocal == 1
                ? "src/Recursos/Sprites/Originales/Jugador/PNGMario/JumpingMarioRigth.png"
                : "src/Recursos/Sprites/Originales/Jugador/PNGMario/JumpingMarioLeft.png";
        mario.getSprite().setRutaImagen(jumpSprite);
    }
    private void gravedad(){
        mario.setPosicionEnY(mario.getPosicionEnY() + GRAVEDAD);
        if (mario.getPosicionEnY() >= SUELO_Y) {
            mario.setPosicionEnY(SUELO_Y);
            mario.setEstadoMovimiento(new MarioParado(mario));
        }
    }
}