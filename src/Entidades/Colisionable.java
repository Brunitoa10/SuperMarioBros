package Entidades;

import Visitor.Visitor;

public interface Colisionable {
    public boolean detectarColision(Entidad c);
    public int accept(Visitor v);
}
