package Entidades.EntidadInmovil;

import Entidades.Entidad;
import Fabricas.Sprite;
import Visitor.Visitor;

import java.util.List;

public class Moneda extends EntidadInmovil{
    protected List<Moneda> listaMonedasNivel;

    public Moneda(int x, int y, Sprite sprite, List<Moneda> listaMonedasNivel) {
        super(x, y, sprite);
        this.listaMonedasNivel = listaMonedasNivel;
    }


    @Override
    public boolean detectarColision(Entidad c) {

        return false;
    }

    @Override
    public int accept(Visitor v) {
        v.visit(this);
        return 0;
    }

    @Override
    public void eliminarEntidad() {
        listaMonedasNivel.remove(this);
    }
}
