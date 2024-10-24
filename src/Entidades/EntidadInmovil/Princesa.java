package Entidades.EntidadInmovil;

import Entidades.Entidad;
import Fabricas.Sprite;
import Visitor.Visitor;

public class Princesa extends EntidadInmovil {
    public Princesa(int x, int y, Sprite sprite) {
        super(x, y, sprite);
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

    }
}
