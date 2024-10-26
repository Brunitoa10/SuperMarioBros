package Entidades.Plataformas;

import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;

import java.util.List;


public class LadrilloSolido extends Plataforma {
    public LadrilloSolido(int x, int y, Sprite sprite, List<Plataforma> listaPlataformaNivel) {
        super(x, y, sprite, listaPlataformaNivel);
    }


    public void interactuar(Jugador jugador) {
        if (jugador.puedeRomperBloques()) {
            this.setAEliminar();
            this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Bloques/AnimacionLadrillo/AnimacionBloqueRompiendose.gif");
        }
    }

    public void reaccionar(Proyectil proyectil) {
        if (proyectil.puedeRomperBloques()) {
            this.setAEliminar();
            this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Bloques/AnimacionLadrillo2/InteraccionFuegoLadrillo.gif");
        }
    }

}
