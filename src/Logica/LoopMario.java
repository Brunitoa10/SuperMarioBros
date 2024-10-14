package Logica;

import Entidades.Jugador;
import Vista.Controladores.ControladorVista;
import Vista.Controladores.ControladorVistaJuego;
import Vista.ObserverGrafica.ObserverGrafica;
import Vista.Paneles.PanelPantallaNivel;
import java.awt.*;
import java.security.Key;


public class LoopMario implements Runnable {

    private static final int MILISENGUDOS_POR_SEGUNDO = 1000;
    private static final int NANOSEGUNDOS_POR_SEGUNDO = 1000000000;
    private static final double NUMERO_TICKS_POR_SEGUNDO = 60.0;
    private boolean ejecutando;
    private Thread hilo;
    private OyenteTeclado oyenteTeclado;
    private Jugador mario;
    private ControladorVistaJuego controlador;
    private PanelPantallaNivel panel;
    private OyenteTeclado oyente;

    public LoopMario(Juego juego) {

        this.mario = juego.getNivelActual().getJugador();
        this.controlador = juego.getControladorVistaJuego();
        oyente = controlador.oyenteTeclado();
        inicializar();

    }

    public void setKeyH(OyenteTeclado oyente){
        oyenteTeclado = oyente;
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
       if (oyente.teclaIzquierda) {
           System.out.println("Izquierda");
            mario.desplazarEnX(-1);
        }
        if (oyente.teclaDerecha) {
            mario.desplazarEnX(1);
        }
        if (oyente.teclaArriba) {
            mario.saltar();
            oyente.teclaArriba = false;
        }
        mario.get_posicion_x();

        mario.actualizar_entidad();

    }

    private void renderizar() {
        controlador.actualizarObserver();
        controlador.refrescar();
        mario.actualizar_entidad();
    }

}