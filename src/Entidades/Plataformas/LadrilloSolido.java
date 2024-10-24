package Entidades.Plataformas;

import Entidades.Entidad;
import Entidades.Jugador;
import Fabricas.Sprite;
import Logica.Temporizador;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.List;


public class LadrilloSolido extends Plataforma {
    public LadrilloSolido(int x, int y, Sprite sprite, List<Plataforma> listaPlataformaNivel) {
        super(x, y, sprite, listaPlataformaNivel);
    }

    public boolean detectarColision(Entidad c) {
        return false;

    }


    public void interactuar(Jugador jugador) {
        if (jugador.puedeRomperBloques()) {
            this.setAEliminar();
            this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Bloques/BloqueNada.png");
        }
    }
    public String[] getFrame() {
        return frames;
    }

}
