package Entidades.Enemigos.EstadoSpiny;

import Entidades.Enemigos.Spiny;

public class SpinyEnSuelo implements EstadoSpiny{
    
    protected Spiny spiny;
    
    public SpinyEnSuelo(Spiny spiny) {
        this.spiny = spiny;
        spiny.setDireccion(-1);
        spiny.setEnPlataforma(true);
        spiny.getHitbox().setBounds(spiny.getPosicionEnX(),spiny.getPosicionEnY(),32,32);
    }
    
    public void actualizarEstadoSpiny() {
        if (spiny.getDireccion() == 1) {
            spiny.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/Spiny/SpinnyCaminandoDerechaLoop.gif");
        }
        if (spiny.getDireccion() == -1) {
            spiny.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/Spiny/SpinnyCaminandoLeftLoop.gif");
        }
    }
    
}
