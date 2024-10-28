package Entidades;

import Fabricas.Sprite;
import Vista.ObserverGrafica.Observer;

import java.awt.*;

public interface EntidadLogica {
    public Sprite getSprite();

    public int getPosicionEnX();

    public int getPosicionEnY();

    public void actualizarEntidad();

    public Rectangle getHitbox();

    public Observer getObserver();

    public void eliminarEntidad();

}
