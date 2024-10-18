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
        Piso=420;
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

    public int getPiso() {
        return Piso;
    }

    @Override
    public boolean detectarColision(Entidad entidad) {
        return this.getHitbox().intersects(entidad.getHitbox());
    }

    public boolean colisionArriba(Entidad entidad) {
        int rangoColision = (int)(entidad.getHitbox().getMaxY() - entidad.getHitbox().getHeight()/2);
        return (this.getHitbox().getMinY() <= entidad.getHitbox().getMaxY()) && (this.getHitbox().getMinY() >= rangoColision);
    }

    public boolean colisionAbajo(Entidad entidad) {
        int rangoColision = (int)(entidad.getHitbox().getMinY() + entidad.getHitbox().getHeight()/2);
        return (this.getHitbox().getMaxY() >= entidad.getHitbox().getMinY()) && (this.getHitbox().getMaxY() <= rangoColision);
    }

    public boolean colisionIzquierda(Entidad entidad) {
        int rangoColision = (int)(entidad.getHitbox().getMaxX() - entidad.getHitbox().getWidth()/2);
        return (this.getHitbox().getMinX() <= entidad.getHitbox().getMaxX()) && (this.getHitbox().getMinX() >= rangoColision);
    }

    public boolean colisionDerecha(Entidad entidad) {
        int rangoColision = (int) (entidad.getHitbox().getMinX() + entidad.getHitbox().getWidth()/2);
        return (this.getHitbox().getMaxX() >= entidad.getHitbox().getMinX()) && (this.getHitbox().getMaxX() <= rangoColision);
    }
}
