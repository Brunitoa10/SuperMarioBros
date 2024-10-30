package Entidades.Power_Ups;

import Entidades.Jugador;
import EstadoJugador.SuperMarioFuego;
import Fabricas.Sprite;

import java.util.List;

public class FlorDeFuego extends PowerUp {
protected static final int COLUMNA=1;

    public FlorDeFuego(int x, int y, Sprite sprite, List<PowerUp> listaPowerUpsNivel) {
        super(x, y, sprite, listaPowerUpsNivel);
    }

    public int setEstadoMario(Jugador j) {
        int toReturn = j.getEstadoJugador().getPuntaje(COLUMNA);
        j.getEstadoJugador().interactuar(this);
        return toReturn;
    }
}
