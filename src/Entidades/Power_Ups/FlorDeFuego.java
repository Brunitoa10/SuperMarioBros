package Entidades.Power_Ups;

import Entidades.Entidad;
import Entidades.Jugador;
import EstadoJugador.SuperMarioFuego;
import Fabricas.Sprite;

import java.util.List;

public class FlorDeFuego extends PowerUp {

    protected int puntaje;

    public FlorDeFuego(int x, int y, Sprite sprite, List<PowerUp> listaPowerUpsNivel) {
        super(x, y, sprite, listaPowerUpsNivel);
        puntaje = 5;
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

    public void setEstadoMario(Jugador j){
        j.setEstadoJugador(new SuperMarioFuego(j));
    }
}
