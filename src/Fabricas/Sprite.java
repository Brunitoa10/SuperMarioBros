package Fabricas;

public class Sprite {
    protected String ruta_a_imagen;
    protected int ancho;
    protected int alto;

    public Sprite(String ruta_a_imagen, int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.ruta_a_imagen = ruta_a_imagen;
    }

    public int get_ancho() {
        return ancho;
    }

    public int get_alto() {
        return alto;
    }

    public String get_ruta_imagen() {
        return ruta_a_imagen;
    }
}
