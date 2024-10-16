package Logica;

import Entidades.Enemigos.Enemigo;
import Vista.Controladores.ControladorVistaJuego;

public class HiloRestoEntidades implements Runnable {

    private boolean ejecutando;
    private ControladorVistaJuego controlador;
    private static final int SUELO_Y = 420;
    private long lastUpdateTime = System.nanoTime();
    private final long updateInterval = 16_000_000; // Aproximadamente 60 FPS
    private CargarEntidades cargarEntidades;

    public HiloRestoEntidades(Juego juego) {
        cargarEntidades = new CargarEntidades(juego);
        controlador = juego.getControladorVistaJuego();

    }

    public synchronized void comenzar() {
        ejecutando = true;
        Thread hilo = new Thread(this);
        hilo.start();
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



    private void tick() {
        for (Enemigo enemigo : cargarEntidades.getEnemigos()){
            enemigo.actualizar();
        }
        for (Enemigo enemigo : cargarEntidades.getEnemigos()){


        }


    }

    private void renderizar() {
        controlador.actualizarObserver();
        controlador.refrescar();
    }
}