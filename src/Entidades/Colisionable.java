package Entidades;

import Visitor.Visitor;

public interface Colisionable {
    public boolean detectarColision(Entidad c);
    public void accept(Visitor v);
}
