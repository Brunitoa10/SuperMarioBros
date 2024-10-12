package Entidades;

import Visitor.Visitor;

public interface Colisionable {
    public boolean detectColision(Entidad c);
    public void accept(Visitor v);
}
