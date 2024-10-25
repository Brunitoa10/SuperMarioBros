package Entidades.Power_Ups;

import Entidades.Entidad;
import Entidades.Jugador;
import EstadoJugador.SuperMarioFuego;
import Fabricas.Sprite;

import java.util.List;

public class FlorDeFuego extends PowerUp {


    public FlorDeFuego(int x, int y, Sprite sprite, List<PowerUp> listaPowerUpsNivel) {
        super(x, y, sprite, listaPowerUpsNivel);
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan =c.detectarColision(this);
        return colisionan;
    }


    public void Consumir(){
        this.setPosicionEnY(1000);
    }

    public int setEstadoMario(Jugador j) {
        int toReturn = j.getEstadoJugador().getPuntajeFlorDeFuego();
        if (j.getEstadoJugador().puedeSerMarioFuego()) {
            j.setEstadoJugador(new SuperMarioFuego(j));
        }
        return toReturn;
    }
}
