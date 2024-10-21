package Entidades.Power_Ups;
import Entidades.Entidad;
import Fabricas.Sprite;

public class Estrella extends PowerUp {

    public Estrella(int x, int y, Sprite sprite) {
        super(x, y, sprite);
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
}
