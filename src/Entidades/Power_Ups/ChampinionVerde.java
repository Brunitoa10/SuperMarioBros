package Entidades.Power_Ups;

import Entidades.EntidadMovil;
import Fabricas.Sprite;
import Logica.Nivel;

public class ChampinionVerde extends PowerUp {

    public ChampinionVerde(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        puntaje = 0;
    }

    public void aplicarEfecto(Nivel nivel){
        nivel.sumarVida(1);
    }

}
