package Fabricas;

public class Sprite {
    protected String rutaImagen;
    protected int ancho;
    protected int alto;
    protected int posicionX;
    protected int posicionY;

    public Sprite(String ruta_a_imagen, int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.rutaImagen = ruta_a_imagen;
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

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String ruta_a_imagen) {
        this.rutaImagen = ruta_a_imagen;
    }


}
