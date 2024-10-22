package Entidades.Proyectiles;

import Entidades.Entidad;
import Entidades.Jugador;
import Fabricas.Sprite;
import Visitor.Visitor;

public class BolaDeFuego extends Proyectil {

    public BolaDeFuego(Jugador mario) {
        super((int) mario.getHitbox().getMaxX(), calcularMitadDeMario(mario) , crearSprite());
        this.velocidad = 7;
        this.setDireccion(mario.getDireccion());
    }

    private static Sprite crearSprite() {
        return new Sprite("src/Recursos/Sprites/original/fireball.png", 16, 16);
    }

    private static int calcularMitadDeMario(Jugador mario) {
        return (int)(mario.getHitbox().getMaxY() - (mario.getHitbox().getHeight() / 2));
    }

    @Override
    public void actualizarEntidad() {
        posicionX += velocidad * direccion;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }


}
