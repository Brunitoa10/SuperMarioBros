package Entidades.Power_Ups;

import Constantes.ConstantesPuntaje;
import Entidades.Jugador;
import Fabricas.Sprite;

import java.util.List;

public class ChampinionVerde extends PowerUp {
protected static final int COLUMNA=3;
    public ChampinionVerde(int x, int y, Sprite sprite, List<PowerUp> listaPowerUpsNivel) {
        super(x, y, sprite, listaPowerUpsNivel);
    }

    @Override
    public int setEstadoMario(Jugador j) {
        j.sumarUnaVida(true);
        return j.getEstadoJugador().getPuntaje(COLUMNA);
    }
}
