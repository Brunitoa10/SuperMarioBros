package Entidades.Plataformas;

import Fabricas.Sprite;

import java.util.List;

public class BloqueSolido extends Plataforma {

    public BloqueSolido(int x, int y, Sprite sprite, List<Plataforma> listaPlataformaNivel) {
        super(x, y, sprite, listaPlataformaNivel);
    }

}
