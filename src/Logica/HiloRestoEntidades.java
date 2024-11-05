package Logica;


import Entidades.Entidad;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class HiloRestoEntidades implements Runnable {

    private final long updateInterval = 16_000_000; // Aproximadamente 60 FPS
    protected boolean FrenarHilo = false;
    protected List<Entidad> EntidadesAEliminar;
    protected boolean ejecutando;
    protected ControladorColisiones controladorColisiones;
    protected long lastUpdateTime = System.nanoTime();
    protected boolean puedoEliminar=false;
    protected ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    protected Juego juego;

    public HiloRestoEntidades(Juego juego) {
        this.juego = juego;
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
                if(juego.loopMario.puedoEliminar) { //metodo para borrar entidades
                    juego.setFrenoElTick(true);
                    juego.eliminarEntidades();
                }
                juego.setFrenoElTick(false);
                tick();
            }
        }
    }

    public void renderizar(){
        juego.getControladorVistaJuego().actualizarObserver();
        juego.getControladorVistaJuego().refrescar();
    }

    private void tick() {
        puedoEliminar=false;
        if (!FrenarHilo && ejecutando) {
            controladorColisiones.colisionesRestoEntidades();
        }
        puedoEliminar = true;

        juego.checkear100Monedas();

    }

    public void pause() {
        FrenarHilo = true;
    }

    public void resume() {
        FrenarHilo = false;
    }
}
