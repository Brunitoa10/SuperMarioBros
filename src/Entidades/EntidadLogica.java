package Entidades;

import Fabricas.Sprite;

public interface EntidadLogica {
    public Sprite get_sprite();

    public int get_posicion_x();

    public int get_posicion_y();

    public void actualizar_entidad();
}
