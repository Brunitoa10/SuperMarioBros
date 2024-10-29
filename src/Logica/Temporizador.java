package Logica;

public class Temporizador {
	
	protected long tiempoInicio;
    protected boolean iniciado;
    protected long tiempoAcumulado;

    public Temporizador() {
        iniciado = false;
        tiempoAcumulado = 0;
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
    
    public void pausar() {
        if (iniciado) {
            tiempoAcumulado += System.currentTimeMillis() - tiempoInicio;
            iniciado = false;
        }
    }

    // Devuelve el tiempo total transcurrido en segundos sin reiniciar
    public int obtenerTiempoTranscurrido() {
        if (iniciado) {
            return (int) ((tiempoAcumulado + (System.currentTimeMillis() - tiempoInicio)) / 1000);
        } else {
            return (int) (tiempoAcumulado / 1000);
        }
    }
}