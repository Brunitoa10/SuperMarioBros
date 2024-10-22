package Entidades.Proyectiles;

import Entidades.Entidad;
import Entidades.Jugador;
import Fabricas.Sprite;
import Visitor.Visitor;
import Visitor.VisitorProyectil;

public class ProyectilSpiny extends Proyectil {
protected VisitorProyectil visitor;
    public ProyectilSpiny(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        visitor = new VisitorProyectil(this);
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan =c.detectarColision(this);
        return false;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public VisitorProyectil getVisitor() {
        return visitor;
    }

    public void hagoDaño(Jugador j){
        j.getEstadoJugador().recibeDanio();
    }

    public void Interactuar(Jugador j){

    }
}
