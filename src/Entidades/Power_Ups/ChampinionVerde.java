package Entidades.Power_Ups;

import Entidades.Entidad;
import Entidades.Jugador;
import Fabricas.Sprite;

import java.util.List;

public class ChampinionVerde extends PowerUp {

    public ChampinionVerde(int x, int y, Sprite sprite, List<PowerUp> listaPowerUpsNivel) {
        super(x, y, sprite, listaPowerUpsNivel);
        puntaje = 0;
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan =c.detectarColision(this);
        return colisionan;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void Consumir(){
        this.setPosicionEnY(1000);
    }

    @Override
    public void setEstadoMario(Jugador j) {
        j.sumarUnaVida(true);
    }
}
