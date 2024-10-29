package Logica;


import Entidades.Entidad;
import Vista.Controladores.ControladorVistaJuego;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class HiloRestoEntidades implements Runnable {

    private final long updateInterval = 16_000_000; // Aproximadamente 60 FPS
    protected boolean FrenarHilo = false;
    protected List<Entidad> EntidadesAEliminar;
    private boolean ejecutando;
    private ControladorVistaJuego controlador;
    private ControladorColisiones controladorColisiones;
    private long lastUpdateTime = System.nanoTime();
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();


    public HiloRestoEntidades(Juego juego) {
        controlador = juego.getControladorVistaJuego();
        EntidadesAEliminar = juego.getNivelActual().entidadesAEliminar;
        controladorColisiones = new ControladorColisiones(juego.getNivelActual(), juego);
    }

    public synchronized void comenzar() {
        ejecutando = true;
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


    private void tick() {
        if (!FrenarHilo)
            controladorColisiones.colisionesRestoEntidades();
    }


    private void renderizar() {
        controlador.actualizarObserver(); // Actualiza la vista de cada entidad
        controlador.refrescar(); // Refresca la pantalla
    }

    public void pause() {
        FrenarHilo = true;
    }

    public void resume() {
        FrenarHilo = false;
    }
}
