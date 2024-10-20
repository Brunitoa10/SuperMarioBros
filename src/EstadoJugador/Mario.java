package EstadoJugador;

import Entidades.Jugador;
import Logica.Nivel;

public class Mario implements EstadoJugador {

    protected Jugador mario; 

    public Mario(Jugador mario) {
        this.mario = mario;
    }

    public void recibeDanio(Nivel nivel) {
        nivel.perdioVida();
    }

    @Override
    public boolean puedeLanzarBolaFuego() {
        return false;
    }

    public void actualizarSprite(){

            mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario");

    }

}
