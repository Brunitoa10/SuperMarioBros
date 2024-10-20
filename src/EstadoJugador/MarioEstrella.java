package EstadoJugador;

import Entidades.Jugador;
import Logica.Nivel;

public class MarioEstrella implements EstadoJugador{

    protected Jugador mario;

    public MarioEstrella(Jugador mario) {
        this.mario = mario; 
    }

    public void recibeDanio(Nivel nivel) {
        
    }

    @Override
    public boolean puedeLanzarBolaFuego() {
        return false;
    }

    public void actualizarSprite() {

    }
}
