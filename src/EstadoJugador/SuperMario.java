package EstadoJugador;

import Entidades.Jugador;
import Logica.Nivel;

public class SuperMario implements EstadoJugador{

    protected Jugador mario;

    public SuperMario(Jugador mario) {
        this.mario = mario;
        mario.setPosicionEnY(mario.getPosicionEnY()-16);
        mario.getHitbox().setBounds(mario.getPosicionEnX(),mario.getPosicionEnY(),32,48);
    }

    public boolean recibeDanio() {
        mario.setEstadoJugador(new Mario(mario));
        return true;
    }

    @Override
    public boolean puedeLanzarBolaFuego() {
        return false;
    }

    public void actualizar(){

    }

    public void actualizarSprite() {
        mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioPowerUp");
    }

    @Override
    public boolean puedeRomperBloques() {
        return true;
    }
}
