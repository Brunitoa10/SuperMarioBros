package Logica;

public class PartidaActual {
    private int vidasMario;
    private int puntaje;

    public PartidaActual() {
        this.vidasMario = 3;
        this.puntaje = 0;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setVidas(int vidasMario) {
        this.vidasMario = vidasMario;
    }

    public int getVidasMario() {
        return vidasMario;
    }

}
