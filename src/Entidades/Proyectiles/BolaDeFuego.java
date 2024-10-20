package Entidades.Proyectiles;

import Entidades.Entidad;
import Fabricas.Sprite;
import Visitor.Visitor;

public class BolaDeFuego extends Proyectil {

    public BolaDeFuego(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        this.velocidad = 10;
    }

    @Override
    public void actualizarEntidad() {
        posicionX += velocidad * direccion;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }


}
