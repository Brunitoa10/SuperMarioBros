package Entidades.Proyectiles;

import Entidades.Entidad;
import Fabricas.Sprite;
import Visitor.Visitor;
import Visitor.VisitorProyectil;

import java.util.List;

public class ProyectilSpiny extends Proyectil {
protected VisitorProyectil visitor;
    public ProyectilSpiny(int x, int y, Sprite sprite, List<Proyectil> listaProyectilNivel) {
        super(x, y, sprite, listaProyectilNivel);
        visitor = new VisitorProyectil(this);
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan =c.detectarColision(this);
        return false;
    }

    public VisitorProyectil getVisitor() {
        return visitor;
    }
}
