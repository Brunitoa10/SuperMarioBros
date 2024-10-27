package Entidades.Enemigos.EstadoLakitu;

import Entidades.Enemigos.Lakitu;

public class LakituRetirada implements EstadoLakitu {
    protected static final int VELOCIDAD_X=-5;
    protected Lakitu lakitu;
    public LakituRetirada(Lakitu lakitu) {
        this.lakitu = lakitu;
        lakitu.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/Lakitu/LakituMoviendose.png");
    }
    @Override
    public void actualizarLakitu() {
        lakitu.setPosicionEnX(lakitu.getPosicionEnX()+VELOCIDAD_X);
    }
}
