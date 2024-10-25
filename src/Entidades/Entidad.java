package Entidades;

import Fabricas.Sprite;
import Generador.GestorSonido.Sonido;
import Vista.ObserverGrafica.Observer;
import Vista.ObserverGrafica.ObserverEntidad;

import java.awt.*;

public abstract class Entidad implements EntidadLogica,Colisionable {
    protected int posicionX;
    protected int posicionY;
    protected Sprite sprite;
    protected Observer observer;
    protected Rectangle hitbox;
    protected Sonido sonido;
    protected boolean aEliminar;

    public Entidad(int x, int y, Sprite sprite) {
        this.posicionX = x;
        this.posicionY = y;
        this.sprite = sprite;
        inicializarHitbox();
    }

    private void inicializarHitbox() {
        this.hitbox = new Rectangle(posicionX, posicionY, sprite.getAncho(), sprite.getAlto());
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    public boolean detectarColision(Entidad entidad) {
        return this.getHitbox().intersects(entidad.getHitbox());
    }

    @Override
    public int getPosicionEnX() {
        return posicionX;
    }

    public void setPosicionEnX(int x) {
        posicionX = x;
        hitbox.x = x;
    }

    @Override
    public int getPosicionEnY() {
        return posicionY;
    }

    public void setPosicionEnY(int y) {
        posicionY = y;
        hitbox.y = y;
    }

    public void registrarObserver(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void actualizarEntidad() {
        observer.actualizarObserver();
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setAEliminar() {
        aEliminar = true;
    }

    public boolean aEliminar() {
        return aEliminar;
    }

    @Override
    public Observer getObserver() {
        return observer;
    }
}
