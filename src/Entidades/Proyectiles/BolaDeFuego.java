package Entidades.Proyectiles;

import Entidades.Entidad;
import Entidades.Jugador;
import Fabricas.Sprite;
import Visitor.Visitor;

import java.util.List;

public class BolaDeFuego extends Proyectil {

    public BolaDeFuego(Jugador mario, List<Proyectil> listaProyectilNivel) {
        super((int) mario.getHitbox().getMaxX(), calcularMitadDeMario(mario) , crearSprite(), listaProyectilNivel);
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


}
