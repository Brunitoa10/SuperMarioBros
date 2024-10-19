package Entidades.Power_Ups;
import Entidades.Entidad;
import Fabricas.Sprite;
import Visitor.VisitorPowerUp;
import Visitor.Visitor;

public class SuperChampinion extends PowerUp{
    protected VisitorPowerUp Visitor;
    public SuperChampinion(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        visitor = new VisitorPowerUp(this);
        puntaje = 100;
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan =c.detectarColision(this);
        return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void Consumir(){
        this.setPosicionEnY(1000);
    }

}
