package Entidades;

import Fabricas.Sprite;
import Generador.GestorSonido.Sonido;

public abstract class EntidadMovil extends Entidad {

    protected int direccion;
    protected int velocidad;
    protected boolean estaVivo;
    protected Sonido sonido;
    protected int Piso;

    public EntidadMovil(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        direccion = 0;
        estaVivo = true;
    }

    public int get_direccion() {
        return direccion;
    }

    public void set_direccion(int direccion) {
        this.direccion = direccion;
    }

    public int get_velocidad() {
        return velocidad;
    }

    public void set_velocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public boolean estaVivo() {
        return estaVivo;
    }

    public void setPiso(int piso) {
        Piso=piso;
    }

    public int get_piso() {
        return Piso;
    }
}
