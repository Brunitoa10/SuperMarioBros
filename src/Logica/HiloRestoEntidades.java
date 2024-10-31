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
    private ControladorColisiones controladorColisiones;
    private long lastUpdateTime = System.nanoTime();
    protected boolean puedoEliminar=false;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
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
                tick();
                if(juego.loopMario.puedoEliminar) { //metodo para borrar entidades
                    juego.frenarTick=true;
                    juego.frenarHilos();
                    juego.eliminarEntidades();
                    juego.frenarTick=false;
                    juego.reanudarHilo();
                }
            }
        }
    }

    public void renderizar(){
        juego.getControladorVistaJuego().actualizarObserver();
        juego.getControladorVistaJuego().refrescar();
    }

    private void tick() {
        puedoEliminar=false;
        if (!FrenarHilo) {
            controladorColisiones.colisionesRestoEntidades();
        }
        puedoEliminar = true;

    }



    public void pause() {
        FrenarHilo = true;
    }

    public void resume() {
        FrenarHilo = false;
    }
}
