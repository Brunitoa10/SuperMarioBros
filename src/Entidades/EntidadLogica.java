package Entidades;

import Fabricas.Sprite;

import java.awt.*;

public interface EntidadLogica{
    public Sprite getSprite();

    public int getPosicionEnX();

    public int getPosicionEnY();

    public void actualizarEntidad();

    public Rectangle getHitbox();

    public void eliminarEntidad();

}
