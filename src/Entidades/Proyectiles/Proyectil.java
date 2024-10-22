package Entidades.Proyectiles;

import Entidades.EntidadMovil;
import Entidades.Jugador;
import Fabricas.Sprite;
import Visitor.VisitorProyectil;

public abstract class Proyectil extends EntidadMovil {

    public Proyectil(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public VisitorProyectil getVisitor() {
        return new VisitorProyectil(this);
    }

    public void hagoDaño(Jugador j){

    }

    public void Interactuar(Jugador j){

    }

}
