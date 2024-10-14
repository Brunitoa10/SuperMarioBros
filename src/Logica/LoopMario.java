package Logica;

import Entidades.Jugador;
import Vista.Controladores.ControladorVista;
import Vista.Controladores.ControladorVistaJuego;


public class LoopMario implements Runnable {

    private static final int MILISENGUDOS_POR_SEGUNDO = 1000;
    private static final int NANOSEGUNDOS_POR_SEGUNDO = 1000000000;
    private static final double NUMERO_TICKS_POR_SEGUNDO = 60.0;
    private boolean ejecutando;
    private Thread hilo;
    private OyenteTeclado controlTeclado;
    private Jugador mario;
    private ControladorVistaJuego controlador;
    
    public LoopMario(Juego juego) {
        this.controlTeclado = juego.getOyenteTeclado();
        this.mario = juego.getNivelActual().getJugador();
        this.controlador = juego.getControladorVistaJuego();
        inicializar();
    }


    private void inicializar() {
        comenzar();
    }

    private synchronized void comenzar() {
        hilo = new Thread(this);
        hilo.start();
        ejecutando = true;
    }

    private synchronized void terminar() {
        try {
            hilo.join();
            ejecutando = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long tiempoAnterior = System.nanoTime(); //Cambiar nombre variable
        double cantidadDeTicks = NUMERO_TICKS_POR_SEGUNDO;
        double nanoSegundos = NANOSEGUNDOS_POR_SEGUNDO / cantidadDeTicks;
        double delta = 0;
        long tmporizador = System.currentTimeMillis();
        int cuadrosPorSegundo = 0;
        int actualizaciones = 0;

        while(ejecutando) {
            long tiempoActual = System.nanoTime();//Cambiar nombre variable
            delta += (tiempoActual - tiempoAnterior) / nanoSegundos;
            tiempoAnterior = tiempoActual;
            while(delta >= 1) {
                tick();
                actualizaciones++;
                delta--;
            }
            if(ejecutando) {
                renderizar();
                cuadrosPorSegundo++; 
            }
            if(System.currentTimeMillis() - tmporizador > MILISENGUDOS_POR_SEGUNDO) {
                tmporizador += MILISENGUDOS_POR_SEGUNDO;
                System.out.println("FPS: " + cuadrosPorSegundo + " " + "TPS: " + actualizaciones);
                actualizaciones = 0;
                cuadrosPorSegundo = 0;
            }
        }
        terminar();
    }

    private void tick() {
        if (controlTeclado.teclaIzquierda) {
            mario.desplazarEnX(-1);
        }
        if (controlTeclado.teclaDerecha) {

            mario.desplazarEnX(1);

        }
        if (controlTeclado.teclaArriba) {
            mario.saltar();
            controlTeclado.teclaArriba = false;
        }
        mario.desplazarEnX(1);
        mario.get_posicion_x();
        System.out.println(mario.get_posicion_x());
        mario.actualizar_entidad();

    }

    private void renderizar() {

        controlador.refrescar();
    }

}