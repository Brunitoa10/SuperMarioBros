package EstadoJugador;

import Entidades.Jugador;
import Logica.Nivel;
import Entidades.Proyectiles.BolaDeFuego;

public class SuperMarioFuego implements EstadoJugador{

    protected Jugador mario;

    public void recibeDanio(Nivel nivel) {
        mario.setEstadoJugador(new Mario(mario));
    }

    @Override
    public void lanzarBolaFuego() {
    }

    @Override
    public void actualizarSprite() {
        mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioPowerUp");
    }

}
