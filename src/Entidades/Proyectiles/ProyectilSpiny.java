package Entidades.Proyectiles;

import Entidades.Entidad;
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

    public int accept(Visitor v) {
        v.visit(this);
        return 0;
    }

    public VisitorProyectil getVisitor() {
        return visitor;
    }
}
