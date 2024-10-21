package Entidades.Plataformas;

import Entidades.Entidad;
import Entidades.Jugador;
import Fabricas.Sprite;


public class LadrilloSolido extends Plataforma {
    public LadrilloSolido(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public boolean detectarColision(Entidad c) {
        System.out.println("detectarColision");
        return false;

    }

    public void interactuar(Jugador jugador) {
        if (jugador.puedeRomperBloques()) {
            setAEliminar();
            this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Bloques/AnimacionLadrillo/AnimacionBloqueRompiendose.gif");
        }
    }


}
