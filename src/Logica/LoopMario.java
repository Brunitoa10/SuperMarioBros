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

            mario.get_sprite().set_ruta_imagen("src/Recursos/Sprites/Originales/AnimacionMarioIdle.gif");

        }, 5, 5, TimeUnit.SECONDS); // 5 segundos de espera entre comprobaciones
    }

    private void tick() {
        OyenteTeclado oyente = controlador.oyenteTeclado();
        boolean actualizacionRequerida = false;

        // Movimiento lateral
        if (oyente.teclaIzquierda) {
            enIdle = false;
            mario.desplazarEnX(-1);
            if(mario.getEstadoMovimiento().estaEnElSuelo()){
            mario.get_sprite().set_ruta_imagen("src/Recursos/Sprites/Originales/Jugador/PNGMario/RunningLoop/MarioCaminandoLeft.gif");
            }else{
                if(direccionLocal==1) {
                    mario.get_sprite().set_ruta_imagen("src/Recursos/Sprites/Originales/Jugador/PNGMario/JumpingMarioRigth.png");
                }else{
                    mario.get_sprite().set_ruta_imagen("src/Recursos/Sprites/Originales/Jugador/PNGMario/JumpingMarioLeft.png");
                }
            }
            actualizacionRequerida = true;
            direccionLocal=-1;
        }

        if (oyente.teclaDerecha) {
            enIdle = false;
            mario.desplazarEnX(1);
            if(mario.getEstadoMovimiento().estaEnElSuelo()) {
                mario.get_sprite().set_ruta_imagen("src/Recursos/Sprites/Originales/Jugador/PNGMario/RunningLoop/MarioCaminandoRight.gif");
            }else{
                if(direccionLocal==1) {
                    mario.get_sprite().set_ruta_imagen("src/Recursos/Sprites/Originales/Jugador/PNGMario/JumpingMarioRigth.png");
                }else{
                    mario.get_sprite().set_ruta_imagen("src/Recursos/Sprites/Originales/Jugador/PNGMario/JumpingMarioLeft.png");
                }
            }
            actualizacionRequerida = true;
            direccionLocal=1;
        }


        if (oyente.teclaArriba && mario.getEstadoMovimiento().estaEnElSuelo()) {
            enIdle = false;
            mario.saltar();
            if(direccionLocal==1) {
                mario.get_sprite().set_ruta_imagen("src/Recursos/Sprites/Originales/Jugador/PNGMario/JumpingMarioRigth.png");
            }else{
                mario.get_sprite().set_ruta_imagen("src/Recursos/Sprites/Originales/Jugador/PNGMario/JumpingMarioLeft.png");
            }
            actualizacionRequerida = true;
        }

        if (!mario.getEstadoMovimiento().estaEnElSuelo()) {
            mario.set_posicion_y(mario.get_posicion_y() + GRAVEDAD);

            if (mario.get_posicion_y() >= SUELO_Y) {
                mario.set_posicion_y(SUELO_Y);
            }
            actualizacionRequerida = true;
        }
        if(!enIdle) {
            if (!oyente.teclaIzquierda && !oyente.teclaDerecha && !oyente.teclaArriba && mario.getEstadoMovimiento().estaEnElSuelo()) {
                if (direccionLocal != -1) {
                    System.out.println(mario.get_direccion());
                    mario.get_sprite().set_ruta_imagen("src/Recursos/Sprites/Originales/Jugador/PNGMario/StandingMarioRigth.png");
                } else {
                    mario.get_sprite().set_ruta_imagen("src/Recursos/Sprites/Originales/Jugador/PNGMario/StandingMarioLeft.png");
                }
                enIdle = true;
                iniciarTemporizadorIdle();

            }
        }

        if (actualizacionRequerida) {
            mario.actualizar_entidad();
            mario.desplazarEnX(0);
        }
    }

    private void renderizar() {
        controlador.actualizarObserver();
        controlador.refrescar();
    }
}
