package Entidades.Enemigos.EstadoSpiny;

import Entidades.Enemigos.Spiny;

public class SpinyCayendo implements EstadoSpiny{
    protected Spiny spiny;

    public SpinyCayendo(Spiny spiny){
        this.spiny = spiny;
        spiny.setDireccion(0);
        spiny.setEnPlataforma(false);
        spiny.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/Spiny/FallingSpinny/SpinnyCayendo2.gif");
    }

    public void actualizarEstadoSpiny(){
        spiny.setPosicionEnY(spiny.getPosicionEnY() + 2);
        if(spiny.estoyEnPlataforma())
            spiny.setEstadoSpiny(new SpinyEnSuelo(spiny));
    }

}
