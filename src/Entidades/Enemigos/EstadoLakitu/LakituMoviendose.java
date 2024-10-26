package Entidades.Enemigos.EstadoLakitu;

import Entidades.Enemigos.Lakitu;
import Entidades.Jugador;
import IA.ComportamientoIA;
import IA.IAatacar;

public class LakituMoviendose implements EstadoLakitu {
    protected Jugador mario;
    protected Lakitu lakitu;
    protected ComportamientoIA comportamiento;

    public LakituMoviendose(Jugador mario, Lakitu lakitu, ComportamientoIA ia) {
        this.mario = mario;
        this.lakitu = lakitu;
        this.comportamiento = ia;
        lakitu.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/Lakitu/LakituMoviendose.png");
    }

    public void actualizarLakitu() {
        comportamiento.actualizar(lakitu);
        lakitu.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/Lakitu/LakituMoviendose.png");
        if(comportamiento.comienzoAtaque())
            lakitu.setEstadoLakitu(new LakituAtacando(mario,lakitu,comportamiento));
    }
}
