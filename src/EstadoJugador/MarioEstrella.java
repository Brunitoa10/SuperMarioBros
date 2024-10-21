package EstadoJugador;

import Entidades.Jugador;
import Logica.Nivel;

public class MarioEstrella implements EstadoJugador{

    protected Jugador mario;

    public MarioEstrella(Jugador mario) {
        this.mario = mario;
    }

    public boolean recibeDanio() {
        return false;
    }

    @Override
    public boolean puedeLanzarBolaFuego() {
        return false;
    }

    public void actualizarSprite() {

    }


    @Override
    public boolean puedeRomperBloques() {
        return false;
    }
}
