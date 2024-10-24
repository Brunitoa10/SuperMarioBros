package Entidades.Plataformas;

import Entidades.Entidad;
import Entidades.Jugador;
import Fabricas.Sprite;
import Logica.Temporizador;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class LadrilloSolido extends Plataforma {
    protected String[] frames;

    public LadrilloSolido(int x, int y, Sprite sprite) {
        super(x, y, sprite);
        frames = new String[7];  // Ejemplo con 5 imágenes
        frames[0] = "src/Recursos/Sprites/original/Bloques/AnimacionLadrillo/Ladrillo1.png";
        frames[1] = "src/Recursos/Sprites/original/Bloques/AnimacionLadrillo/Ladrillo2.png";
        frames[2] = "src/Recursos/Sprites/original/Bloques/AnimacionLadrillo/Ladrillo3.png";
        frames[3] = "src/Recursos/Sprites/original/Bloques/AnimacionLadrillo/Ladrillo4.png";
        frames[4] = "src/Recursos/Sprites/original/Bloques/AnimacionLadrillo/Ladrillo5.png";
        frames[5] = "src/Recursos/Sprites/original/Bloques/AnimacionLadrillo/Ladrillo6.png";
        frames[6] = "src/Recursos/Sprites/original/Bloques/BloqueNada.png";

    }

    public boolean detectarColision(Entidad c) {
        System.out.println("detectarColision");
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
