package EstadoJugador;

import Entidades.Jugador;
import Logica.Nivel;
import Entidades.Proyectiles.BolaDeFuego;

public class SuperMarioFuego implements EstadoJugador{

    protected Jugador mario;

    public SuperMarioFuego(Jugador mario) {
        this.mario = mario;
        mario.setPosicionEnY(mario.getPosicionEnY()-16);
        mario.getHitbox().setBounds(mario.getPosicionEnX(),mario.getPosicionEnY(),33,48);
    }
    public void recibeDanio(Nivel nivel) {
        mario.setEstadoJugador(new Mario(mario));
    }

    @Override
    public void lanzarBolaFuego() {
    }

    @Override
    public void actualizarSprite() {
        mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioFlorDeFuego");
    }

    @Override
    public boolean puedeRomperBloques() {
        return true;
    }

}
