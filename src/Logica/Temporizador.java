package Logica;

public class Temporizador {
    private long tiempoInicio;
    private boolean iniciado;

    public Temporizador() {
        iniciado = false;
    }

    // Inicia el temporizador
    public void iniciar() {
        if (!iniciado) {
            tiempoInicio = System.currentTimeMillis();
            iniciado = true;
        }
    }

    // Resetea el temporizador
    public void resetear() {
        iniciado = false;
    }

    // Devuelve true si han pasado 5 segundos desde que se inició
    public boolean hanPasadoNSegundos(int tiempo) {
        if (!iniciado) {
            return false;
        }
        long tiempoActual = System.currentTimeMillis();
        long tiempoTranscurrido = (tiempoActual - tiempoInicio); // Convertir a segundos
        return tiempoTranscurrido >= tiempo;
    }
}