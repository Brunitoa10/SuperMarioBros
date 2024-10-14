package Logica;


import Entidades.Jugador;
import Vista.Controladores.ControladorVistaJuego;

public class LoopMario implements Runnable {

    private boolean ejecutando;
    private Jugador mario;
    private OyenteTeclado oyenteTeclado;
    private ControladorVistaJuego controlador;

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
                // Pausa de 16ms para lograr unos 60 FPS aproximados
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void tick() {

        OyenteTeclado oyente = controlador.oyenteTeclado();

        if (oyente.teclaIzquierda) {
            mario.desplazarEnX(-1);
            mario.actualizar_entidad();
        }

        if (oyente.teclaDerecha) {
            mario.desplazarEnX(1);
            mario.actualizar_entidad();
        }
        if (oyente.teclaArriba) {
            mario.saltar();
            mario.actualizar_entidad();
        }

    }

    private void renderizar() {
        controlador.actualizarObserver();
        controlador.refrescar();
    }
}
