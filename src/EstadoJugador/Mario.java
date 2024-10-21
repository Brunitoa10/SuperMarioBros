package EstadoJugador;

import Entidades.Jugador;
import Logica.Nivel;

public class Mario implements EstadoJugador {

    protected Jugador mario; 

    public Mario(Jugador mario) {
        this.mario = mario;
    }

    public boolean recibeDanio() {
        mario.setVidas(mario.getVidas()-1);
        return true;
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

}
