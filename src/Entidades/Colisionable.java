package Entidades;

import Visitor.Visitor;

public interface Colisionable {
    public boolean detectColision(Colisionable c);
    public void accept(Visitor v);
}
