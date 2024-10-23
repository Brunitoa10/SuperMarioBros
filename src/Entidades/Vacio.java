package Entidades;

import Entidades.EntidadInmovil.EntidadInmovil;
import Fabricas.Sprite;
import Visitor.Visitor;

import java.util.List;

public class Vacio extends EntidadInmovil {

    protected List<Vacio> listaVacioNivel;

    public Vacio(int x, int y, Sprite sprite, List<Vacio> listaVacioNivel) {
        super(x, y, sprite);
        this.listaVacioNivel = listaVacioNivel;
    }

    @Override
    public boolean detectarColision(Entidad c) {
        return false;
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public void eliminarEntidad() {
        listaVacioNivel.remove(this);
    }
}
