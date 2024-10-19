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
    private boolean enIdle = false;
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

        // LÃ³gica de salto
        if (oyente.teclaArriba && (mario.getEstadoMovimiento().estaEnElSuelo() || mario.estaEnPlataforma())) {
            if (mario.estaEnPlataforma())
                mario.setEnPlataforma(false);
            enIdle = false;
            saltar(); //TODO: CAMBIAR SPRITE CON ANIMADORMARIO
            actualizacionRequerida = true;
        }

        // Gravedad
        if (!mario.getEstadoMovimiento().estaEnElSuelo()) {

                gravedad();
                actualizacionRequerida = true;

        }

        for (Plataforma p : plataformas) {
            if (mario.detectarColision((p))) {
                mario.getVisitorJugador().visit(p);
            }
        }

        for(PowerUp p : powerUps) {
            if (mario.detectarColision((p))) {
                p.getVisitor().visit(mario);
                AplicarEfecto(p);
                EstadoActivado=true;
                powerUps.remove(p);
                actualizacionRequerida=true;
            }
        }

        for (Vacio vacio : vacios) {
            if(VacioColisionoAbajo(vacio)){
                System.out.println("Entreaca");
                if (mario.estaEnPlataforma()) {
                    mario.setPiso(420);
                    mario.setEstadoMovimiento(new MarioEnAire(mario));
                    mario.setEnPlataforma(false);
                }

                if (mario.getPosicionEnY() == SUELO_Y){
                    caerAlInfinito=true;
                    System.out.println(mario.getEstadoMovimiento().estaEnElSuelo());
                    mario.setPiso(459);
                    mario.setEstadoMovimiento(new MarioEnAire(mario));

                }
            }
        }



        if (actualizacionRequerida) {
            mario.actualizarEntidad();
            mario.desplazarEnX(0);

            if (mario.getPosicionEnY() == 420) {
                mario.setPiso(420);
            }

        }
    }

    private void AplicarEfecto(PowerUp p) {
        String spritePath = p.getPuntaje() == 100
                ? (direccionLocal == -1
                ? "src/Recursos/Sprites/original/Jugador/PNGMario/MarioPowerUp/LeftSuperMario.png"
                : "src/Recursos/Sprites/original/Jugador/PNGMario/MarioPowerUp/RightSuperMario.png")
                : "src/Recursos/Sprites/original/Jugador/PNGMario/MarioPowerUp/RightSuperMario.png"; // Ruta por defecto si el puntaje no es 100

        // Asignar la ruta al sprite de Mario (o alguna otra acción)
        mario.getSprite().setRutaImagen(spritePath);
    }


    private void renderizar() {
        controlador.actualizarObserver();
        controlador.refrescar();
    }


    private void actualizarSprite() {
            String spritePath = mario.getEstadoMovimiento().estaEnElSuelo()
                    ? (direccionLocal == -1 ? "src/Recursos/Sprites/original/Jugador/PNGMario/RunningLoop/MarioCaminandoLeft.gif"
                    : "src/Recursos/Sprites/original/Jugador/PNGMario/RunningLoop/MarioCaminandoRight.gif")
                    : (direccionLocal == -1 ? "src/Recursos/Sprites/original/Jugador/PNGMario/JumpingMarioLeft.png"
                    : "src/Recursos/Sprites/original/Jugador/PNGMario/JumpingMarioRigth.png");
        if(!EstadoActivado)
            mario.getSprite().setRutaImagen(spritePath);

    }

    private void estadoIdle() {
        if(!EstadoActivado){
            if (!enIdle && mario.getEstadoMovimiento().estaEnElSuelo()) {
                enIdle = true;
                mario.getSprite().setRutaImagen(direccionLocal == -1
                        ? "src/Recursos/Sprites/original/Jugador/PNGMario/StandingMarioLeft.png"
                        : "src/Recursos/Sprites/original/Jugador/PNGMario/StandingMarioRigth.png");
                iniciarTemporizadorIdle();
            }
        }
    }

    private void saltar() {
        mario.saltar();

        String jumpSprite = direccionLocal == 1
                ? "src/Recursos/Sprites/original/Jugador/PNGMario/JumpingMarioRigth.png"
                : "src/Recursos/Sprites/original/Jugador/PNGMario/JumpingMarioLeft.png";
        if(!EstadoActivado)
            mario.getSprite().setRutaImagen(jumpSprite);

    }

    private void gravedad() {
        mario.setPosicionEnY(mario.getPosicionEnY() + GRAVEDAD);
        int posicionFutura=(int)(SUELO_Y+32-mario.getHitbox().getHeight());
        if (mario.getPosicionEnY() >= SUELO_Y && !caerAlInfinito) {

            mario.setPosicionEnY(posicionFutura);
            mario.setEstadoMovimiento(new MarioParado(mario));
        }
    }

    private boolean VacioColisionoAbajo(Vacio vacio) {
        boolean Colisiono = false;
        int tolerancia=5;
        if ((mario.getPosicionEnX() >= vacio.getPosicionEnX()-tolerancia) && mario.getPosicionEnX()+mario.getHitbox().getWidth() <= vacio.getPosicionEnX()+vacio.getHitbox().getWidth()+tolerancia ) {
            if (mario.getPosicionEnY() + mario.getHitbox().getHeight() <= vacio.getPosicionEnY()) {
                System.out.println("EstoyarribaDeVacio");
                Colisiono = true;
            }
        }
        return Colisiono;
    }
}