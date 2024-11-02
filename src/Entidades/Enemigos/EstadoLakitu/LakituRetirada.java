package Entidades.Enemigos.EstadoLakitu;

import Entidades.Enemigos.Lakitu;

public class LakituRetirada implements EstadoLakitu {
    protected static final int VELOCIDAD_X=-5;
    protected Lakitu lakitu;
    public LakituRetirada(Lakitu lakitu) {
        this.lakitu = lakitu;
    }
    @Override
    public void actualizarLakitu() {
        lakitu.getSprite().setRutaImagen(lakitu.getSprite().getRutaModo()+"/Enemigos/Lakitu/LakituMoviendose.png");
        lakitu.setPosicionEnX(lakitu.getPosicionEnX()+VELOCIDAD_X);
        if(lakitu.getPosicionEnX()<-600)
            lakitu.setAEliminar();
    }
}
