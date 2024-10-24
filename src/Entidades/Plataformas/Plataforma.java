package Entidades.Plataformas;

import Entidades.Entidad;
import Entidades.EntidadInmovil.EntidadInmovil;
import Entidades.Jugador;
import Fabricas.Sprite;
import Visitor.Visitor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Plataforma extends EntidadInmovil {

    protected boolean solido;
    protected boolean rompible;
    protected boolean meRompi=false;

    public Plataforma(int x, int y, Sprite sprite) {

        super(x, y, sprite);
    }

    public boolean detectarColision(Entidad c) {
        return this.getHitbox().intersects(c.getHitbox());
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public void interactuar(Jugador j){

    }

    public String[] getFrames() {
        String[] frames = new String[1];
        frames[0]= "src/Recursos/Sprites/original/Bloques/BloqueNada.png";
        return frames;
    }
}
