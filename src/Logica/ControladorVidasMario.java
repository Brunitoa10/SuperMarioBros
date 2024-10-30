package Logica;


public class ControladorVidasMario {

    private int vidas;

    public ControladorVidasMario() {
        vidas = 3;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public void perderVida() {
        vidas--;
    }

    public void sumarVida() {
        vidas++;
    }


}
