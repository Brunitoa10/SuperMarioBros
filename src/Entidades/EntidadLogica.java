package Entidades;

import Fabricas.Sprite;

public interface EntidadLogica{
    public Sprite getSprite();

    public int getPosicionEnX();

    public int getPosicionEnY();

    public void actualizarEntidad();
}
