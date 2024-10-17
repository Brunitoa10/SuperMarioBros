package Logica;

import Entidades.Enemigos.Enemigo;
import Vista.Controladores.ControladorVistaJuego;
import java.util.List;

public class HiloRestoEntidades implements Runnable {

    private boolean ejecutando;
    private ControladorVistaJuego controlador;
    private static final int SUELO_Y = 420;

    // Intervalos para actualización y renderizado
    private long lastUpdateTime = System.nanoTime();
    private final long updateInterval = 16_000_000; // Aproximadamente 60 FPS
    private long lastRenderTime = System.nanoTime();
    private final long renderInterval = 16_000_000; // Render cada 16ms (60 FPS)

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

            // Actualización de entidades
            if (now - lastUpdateTime >= updateInterval) {
                lastUpdateTime = now;
                tick();
            }

            // Renderizado de entidades
            if (now - lastRenderTime >= renderInterval) {
                lastRenderTime = now;
                renderizar();
            }

            // Pausa para aliviar la carga del CPU
            try {
                Thread.sleep(1); // Una pequeña pausa para evitar la sobrecarga
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Método tick para actualizar el estado de las entidades
    private void tick() {
        List<Enemigo> enemigos = cargarEntidades.getEnemigos();
        for (Enemigo enemigo : enemigos) {
            enemigo.actualizar(); // Actualiza cada enemigo
        }
    }

    // Método renderizar para actualizar la vista de las entidades
    private void renderizar() {
        controlador.actualizarObserver(); // Actualiza la vista de cada entidad
        controlador.refrescar(); // Refresca la pantalla
    }
}
