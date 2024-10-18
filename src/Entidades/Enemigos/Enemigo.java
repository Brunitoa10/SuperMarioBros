package Entidades.Enemigos;

import Entidades.EntidadMovil;
import Fabricas.Sprite;

public abstract class Enemigo extends EntidadMovil {

    private int puntajeDestruido;

    protected int puntajeKill;


    public Enemigo(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    public void caminar() {
        posicionX += this.get_velocidad();
    }

    public void actualizar() {
        caminar();
        super.actualizarEntidad();
    }


}