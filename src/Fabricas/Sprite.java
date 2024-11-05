package Fabricas;

public class Sprite {
    protected String rutaImagen;
    protected int ancho;
    protected int alto;
    protected String rutaModo;

    public Sprite(String ruta_a_imagen, int ancho, int alto, String rutaModo) {
        this.ancho = ancho;
        this.alto = alto;
        this.rutaImagen = ruta_a_imagen;
        this.rutaModo = rutaModo;
        System.out.println("Ruta de imagen clase Sprite: " + ruta_a_imagen);
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

    public String getRutaModo() {
        return rutaModo;
    }


}
