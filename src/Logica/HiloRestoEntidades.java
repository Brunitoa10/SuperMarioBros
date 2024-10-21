package Logica;

import Entidades.Enemigos.Enemigo;
import Entidades.Proyectiles.Proyectil;
import Vista.Controladores.ControladorVistaJuego;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class HiloRestoEntidades implements Runnable {

    private boolean ejecutando;
    private ControladorVistaJuego controlador;
    private static final int SUELO_Y = 420;

    // Intervalos para actualización y renderizado
    private long lastUpdateTime = System.nanoTime();
    private final long updateInterval = 16_000_000; // Aproximadamente 60 FPS
    private long lastRenderTime = System.nanoTime();
    private final long renderInterval = 16_000_000; // Render cada 16ms (60 FPS)
    private Nivel nivelActual;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public HiloRestoEntidades(Juego juego) {
        nivelActual = juego.getNivelActual();
        controlador = juego.getControladorVistaJuego();
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


    // Método tick para actualizar el estado de las entidades
    private void tick() {
        for (Enemigo enemigo : nivelActual.getEnemigos()) {
            enemigo.actualizar();
            for(Proyectil proyectil : nivelActual.getProyectiles()) {
                proyectil.actualizarEntidad();
                int tolerancia = 5;
                if(proyectil.getPosicionEnX() >= enemigo.getPosicionEnX() - tolerancia && proyectil.getPosicionEnX() <= enemigo.getPosicionEnX() + tolerancia &&
                proyectil.getPosicionEnY() >= enemigo.getPosicionEnY() - tolerancia && proyectil.getPosicionEnY() <= enemigo.getPosicionEnY() + tolerancia) {
                    enemigo.getVisitorEnemigo().visit(proyectil);
                    enemigo.actualizarEntidad();
                }
            }
        }
    }

    // Método renderizar para actualizar la vista de las entidades
    private void renderizar() {
        controlador.actualizarObserver(); // Actualiza la vista de cada entidad
        controlador.refrescar(); // Refresca la pantalla
    }
}
