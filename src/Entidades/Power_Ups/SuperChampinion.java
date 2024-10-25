package Entidades.Power_Ups;
import Entidades.Entidad;
import Entidades.Jugador;
import EstadoJugador.SuperMario;
import Fabricas.Sprite;

import java.util.List;

public class SuperChampinion extends PowerUp{

    public SuperChampinion(int x, int y, Sprite sprite, List<PowerUp> listaPowerUpsNivel) {
        super(x, y, sprite, listaPowerUpsNivel);
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan =c.detectarColision(this);
        return colisionan;
    }



    public int setEstadoMario(Jugador j) {
        int toReturn = j.getEstadoJugador().getPuntajeSuperChampinion();
        if (j.getEstadoJugador().elHongoLoHaceSuperMario()) {
            j.setEstadoJugador(new SuperMario(j));
        }
        return toReturn;
    }
}
