package Fabricas;

public class Sprite {
    protected String ruta_a_imagen;
    protected int ancho;
    protected int alto;
    protected int posicionX;
    protected int posicionY;

    public Sprite(String ruta_a_imagen, int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.ruta_a_imagen = ruta_a_imagen;
        System.out.println("Ruta de imagen: " + ruta_a_imagen);
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
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

    public void set_ruta_imagen(String ruta_a_imagen) {
        this.ruta_a_imagen = ruta_a_imagen;
    }


}
