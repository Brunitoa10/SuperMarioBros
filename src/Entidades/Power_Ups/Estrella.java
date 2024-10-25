package Entidades.Power_Ups;
import Entidades.Entidad;
import Entidades.Jugador;
import EstadoJugador.MarioEstrella;
import Fabricas.Sprite;

import java.util.List;

public class Estrella extends PowerUp {

    public Estrella(int x, int y, Sprite sprite, List<PowerUp> listaPowerUpsNivel) {
        super(x, y, sprite, listaPowerUpsNivel);
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan =c.detectarColision(this);
        return colisionan;
    }


    @Override
    public int setEstadoMario(Jugador j) {
        int toReturn = j.getEstadoJugador().getPuntajeEstrella();
        j.setEstadoJugador(new MarioEstrella(j));
        return toReturn;
    }
}
