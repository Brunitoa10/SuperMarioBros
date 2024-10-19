package EstadoJugador;

import Entidades.Jugador;
import Logica.Nivel;

public class SuperMario implements EstadoJugador{

    protected Jugador mario;

    public SuperMario(Jugador mario) {
        this.mario = mario;
    }

    public void recibeDanio(Nivel nivel) {
        mario.setEstadoJugador(new Mario(mario));
    }

    @Override
    public void lanzarBolaFuego() {

    }

    public void actualizar(){

    }
}
