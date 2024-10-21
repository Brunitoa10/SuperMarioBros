package Entidades.Power_Ups;

import Entidades.Entidad;
import Fabricas.Sprite;
import Logica.Nivel;

public class ChampinionVerde extends PowerUp {

    public ChampinionVerde(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        puntaje = 0;
    }

    public void aplicarEfecto(Nivel nivel){
        // vidas que el champinion suma al agarrarlo
        int vidasChampinion = 1;
        nivel.sumarVida(vidasChampinion);
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan =c.detectarColision(this);
        return false;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void Consumir(){
        this.setPosicionEnY(1000);
    }
}
