package Entidades.Power_Ups;
import Entidades.Entidad;
import Fabricas.Sprite;
import Visitor.Visitor;

public class Estrella extends PowerUp {

    public Estrella(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        puntaje = 0;
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan =c.detectColission(this);
        return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }


}
