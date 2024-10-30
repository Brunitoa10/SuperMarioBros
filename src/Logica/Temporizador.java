package Logica;

public class Temporizador {
	
	protected long tiempoInicio;
    protected boolean iniciado;
    protected long tiempoAcumulado;

    public Temporizador() {
        iniciado = false;
        tiempoAcumulado = 0;
    }

    public void iniciar() {
        if (!iniciado) {
            tiempoInicio = System.currentTimeMillis();
            iniciado = true;
        }
    }

    public void resetear() {
        iniciado = false;
    }

    // Devuelve true si han pasado n segundos desde que se inició
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
}