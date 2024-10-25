package Entidades.Power_Ups;
import Entidades.Entidad;
import Entidades.Jugador;
import EstadoJugador.MarioEstrella;
import Fabricas.Sprite;

import java.util.List;

public class Estrella extends PowerUp {

    public Estrella(int x, int y, Sprite sprite, List<PowerUp> listaPowerUpsNivel) {
        super(x, y, sprite, listaPowerUpsNivel);
        puntaje = 0;
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan =c.detectarColision(this);
        return false;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void Consumir(){
        sprite=null;
    }

    @Override
    public void setEstadoMario(Jugador j) {
        j.setEstadoJugador(new MarioEstrella(j));
    }
}
