package Entidades.Enemigos.EstadoSpiny;

import Entidades.Enemigos.Spiny;

public class SpinyCayendo implements EstadoSpiny{
    protected Spiny spiny;

    public SpinyCayendo(Spiny spiny){
        this.spiny = spiny;
        spiny.setDireccion(0);
        spiny.setEnPlataforma(false);
        spiny.getHitbox().setBounds(spiny.getPosicionEnX(),spiny.getPosicionEnY(),32,32);
        spiny.getSprite().setRutaImagen(spiny.getSprite().getRutaModo()+"/Enemigos/Spiny/FallingSpinny/SpinnyCayendo.gif");
    }

    public void actualizarEstadoSpiny(){
        if(spiny.estoyEnPlataforma())
            spiny.setEstadoSpiny(new SpinyEnSuelo(spiny));
    }

}
