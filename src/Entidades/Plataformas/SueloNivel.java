package Entidades.Plataformas;

import Fabricas.Sprite;

import java.util.List;

public class SueloNivel extends Plataforma {
    public SueloNivel(int x, int y, Sprite sprite, List<Plataforma> listaPlataformaNivel) {
        super(x, y, sprite, listaPlataformaNivel);
    }
}
