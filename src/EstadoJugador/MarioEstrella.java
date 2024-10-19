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
    public void lanzarBolaFuego() {

    }

    public void actualizarSprite() {

    }
}
