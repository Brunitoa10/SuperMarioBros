package Logica;

import Entidades.Jugador;
import Vista.Controladores.ControladorVistaJuego;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoopMario implements Runnable {

    private boolean ejecutando;
    private Jugador mario;
    private OyenteTeclado oyenteTeclado;
    private ControladorVistaJuego controlador;
    private static final int GRAVEDAD = 1;
    private static final int SUELO_Y = 420;
    private int direccionLocal=0;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private boolean enIdle=false;
    private boolean TerminoIdle=false;
    private static final String MARIO_AFK="src/Recursos/Sprites/Originales/AnimacionMarioIdle.gif";

    public LoopMario(Juego juego) {
        this.mario = juego.getNivelActual().getJugador();
        this.controlador = juego.getControladorVistaJuego();
        ejecutando = false;
    }

    public synchronized void comenzar() {
        ejecutando = true;
        Thread hilo = new Thread(this);
        hilo.start();
    }

    public synchronized void detener() {
        ejecutando = false;
    }

    @Override
    public void run() {
        while (ejecutando) {
            tick();
            renderizar();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void iniciarTemporizadorIdle() {
        scheduler.scheduleAtFixedRate(() -> {
            mario.getSprite().setRutaImagen(MARIO_AFK);

        }, 3, 3, TimeUnit.SECONDS); // 5 segundos de espera entre comprobaciones
    }

    private void tick() {
        OyenteTeclado oyente = controlador.oyenteTeclado();
        boolean actualizacionRequerida = false;

        // Movimiento lateral
        if (oyente.teclaIzquierda) {
            enIdle = false;
            mario.desplazarEnX(-1);
            if(mario.getEstadoMovimiento().estaEnElSuelo()){
                mario.getSprite().setRutaImagen("src/Recursos/Sprites/Originales/Jugador/PNGMario/RunningLoop/MarioCaminandoLeft.gif");
            }else{
                if(direccionLocal==1) {
                    mario.getSprite().setRutaImagen("src/Recursos/Sprites/Originales/Jugador/PNGMario/JumpingMarioRigth.png");
                }else{
                    mario.getSprite().setRutaImagen("src/Recursos/Sprites/Originales/Jugador/PNGMario/JumpingMarioLeft.png");
                }
            }
            actualizacionRequerida = true;
            direccionLocal=-1;
        }

        if (oyente.teclaDerecha) {
            enIdle = false;
            mario.desplazarEnX(1);
            if(mario.getEstadoMovimiento().estaEnElSuelo()) {
                mario.getSprite().setRutaImagen("src/Recursos/Sprites/Originales/Jugador/PNGMario/RunningLoop/MarioCaminandoRight.gif");
            }else{
                if(direccionLocal==1) {
                    mario.getSprite().setRutaImagen("src/Recursos/Sprites/Originales/Jugador/PNGMario/JumpingMarioRigth.png");
                }else{
                    mario.getSprite().setRutaImagen("src/Recursos/Sprites/Originales/Jugador/PNGMario/JumpingMarioLeft.png");
                }
            }
            actualizacionRequerida = true;
            direccionLocal=1;
        }


        if (oyente.teclaArriba && mario.getEstadoMovimiento().estaEnElSuelo()) {
            enIdle = false;
            mario.saltar();
            if(direccionLocal==1) {
                mario.getSprite().setRutaImagen("src/Recursos/Sprites/Originales/Jugador/PNGMario/JumpingMarioRigth.png");
            }else {
                mario.getSprite().setRutaImagen("src/Recursos/Sprites/Originales/Jugador/PNGMario/JumpingMarioLeft.png");
            }
            actualizacionRequerida = true;
        }

        if (!mario.getEstadoMovimiento().estaEnElSuelo()) {
            mario.setPosicionEnY(mario.getPosicionEnY() + GRAVEDAD);

            if (mario.getPosicionEnY() >= SUELO_Y) {
                mario.setPosicionEnY(SUELO_Y);
            }
            actualizacionRequerida = true;
        }

        if(!enIdle){
            reiniciarTemporizadorIdle();
        }

            if (!oyente.teclaIzquierda && !oyente.teclaDerecha && !oyente.teclaArriba && mario.getEstadoMovimiento().estaEnElSuelo()) {
                if(!enIdle) {
                    System.out.println("true");
                    enIdle = true;

                    if (direccionLocal != -1) {
                        mario.getSprite().setRutaImagen("src/Recursos/Sprites/Originales/Jugador/PNGMario/StandingMarioRigth.png");
                        System.out.println("Parado");

                    } else {
                        mario.getSprite().setRutaImagen("src/Recursos/Sprites/Originales/Jugador/PNGMario/StandingMarioLeft.png");
                        System.out.println("Parado");
                    }

                }else{

                        iniciarTemporizadorIdle();
                        actualizacionRequerida = true;

                }


            }

        if (actualizacionRequerida) {
            mario.actualizarEntidad();
            mario.desplazarEnX(0);
        }
    }


    private void reiniciarTemporizadorIdle() {
        scheduler.shutdownNow(); // Detiene el scheduler actual
        scheduler = Executors.newSingleThreadScheduledExecutor(); // Crea un nuevo scheduler
        iniciarTemporizadorIdle(); // Inicia el nuevo temporizador
    }

    private void renderizar() {
        controlador.actualizarObserver();
        controlador.refrescar();
    }
}
