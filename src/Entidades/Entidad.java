package Entidades;

import Fabricas.Sprite;
import Vista.ObserverGrafica.Observer;

public abstract class Entidad implements EntidadLogica,Colisionable {
    protected int posicionX;
    protected int posicionY;
    protected int ancho;
    protected int alto;
    protected Sprite sprite;
    protected Observer observer;

    public Entidad(int x, int y, Sprite sprite) {
        this.posicionX = x;
        this.posicionY = y;
        this.sprite = sprite;
    }

    @Override
    public Sprite get_sprite() {
        return sprite;
    }

    @Override
    public int get_posicion_x() {
        return posicionX;
    }

    public void set_posicion_x(int x) {
        posicionX = x;
    }

    @Override
    public int get_posicion_y() {
        return posicionY;
    }

    public void set_posicion_y(int y) {
        posicionY = y;
    }

    public void registrar_observer(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void actualizar_entidad() {

    }
}
