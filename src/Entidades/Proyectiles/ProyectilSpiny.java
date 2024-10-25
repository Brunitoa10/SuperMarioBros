package Entidades.Proyectiles;

import Entidades.Entidad;
import Entidades.Jugador;
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

    public int accept(Visitor v) {
        v.visit(this);
        return 0;
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
