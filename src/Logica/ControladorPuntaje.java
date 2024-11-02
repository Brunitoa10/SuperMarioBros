package Logica;

public class ControladorPuntaje {

    private int puntaje;

    public ControladorPuntaje() {
        this.puntaje = 0;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void sumarPuntaje(int puntajeASumar) {
        this.puntaje += puntajeASumar;
    }


}
