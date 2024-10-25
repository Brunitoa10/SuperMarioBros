package EstadoJugador;

import Entidades.Jugador;
import Logica.Nivel;

public class Mario implements EstadoJugador {

    protected Jugador mario; 

    public Mario(Jugador mario) {
        this.mario = mario;
        mario.setPosicionEnY(mario.getPosicionEnY() + 16);
        mario.getHitbox().setBounds(mario.getPosicionEnX(), mario.getPosicionEnY(), 32, 32);
    }

    public void recibeDanio() {
        mario.setMorir(true);
    }

    public boolean esInmortal(){
        return false;
    }

    @Override
    public boolean puedeLanzarBolaFuego() {
        return false;
    }

    public void actualizarSprite(){
        mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario");

    }

    @Override
    public boolean puedeRomperBloques() {
        return false;
    }

    public boolean elHongoLoHaceSuperMario(){
        return true;
    }
    public boolean puedeSerMarioFuego(){
        return true;
    }
    public String finalAnimacion(){
        return "";
    }

    @Override
    public boolean estadoEstrella() {
        return false;
    }
}
