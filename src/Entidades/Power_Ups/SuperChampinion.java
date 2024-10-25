package Entidades.Power_Ups;

import Entidades.Jugador;
import EstadoJugador.SuperMario;
import Fabricas.Sprite;

import java.util.List;

public class SuperChampinion extends PowerUp {
    public SuperChampinion(int x, int y, Sprite sprite, List<PowerUp> listaPowerUpsNivel) {
        super(x, y, sprite, listaPowerUpsNivel);
        puntaje = 100;
    }

    public int setEstadoMario(Jugador j) {
        int toReturn = j.getEstadoJugador().getPuntajeSuperChampinion();
        if (j.getEstadoJugador().elHongoLoHaceSuperMario()) {
            j.setEstadoJugador(new SuperMario(j));
        }
        this.setFrenarJuego(true);
        return toReturn;
    }

}
