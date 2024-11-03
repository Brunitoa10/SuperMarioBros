package Entidades.Power_Ups;

import Entidades.Jugador;
import Fabricas.Sprite;

import java.util.List;

public class Estrella extends PowerUp {
protected static final int COLUMNA=2;
    public Estrella(int x, int y, Sprite sprite, List<PowerUp> listaPowerUpsNivel) {
        super(x, y, sprite, listaPowerUpsNivel);
    }

    @Override
    public int setEstadoMario(Jugador j) {
        return j.getEstadoJugador().interactuar(this,COLUMNA); //interactua y devuelve el valor
    }
}
