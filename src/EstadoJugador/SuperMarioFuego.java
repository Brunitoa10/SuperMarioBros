package EstadoJugador;

import Entidades.Jugador;
import EstadoMovimiento.MarioEnAire;
import Logica.Nivel;
import Entidades.Proyectiles.BolaDeFuego;

public class SuperMarioFuego implements EstadoJugador {

    protected Jugador mario;

    public SuperMarioFuego(Jugador mario) {
        this.mario = mario;
        mario.setPosicionEnY(mario.getPosicionEnY() - 16);
        mario.getHitbox().setBounds(mario.getPosicionEnX(), mario.getPosicionEnY(), 32, 48);
    }

    public boolean recibeDanio() {
        mario.setEstadoJugador(new Mario(mario));
        return true;
    }

    @Override
    public boolean puedeLanzarBolaFuego() {
        return true;
    }

    @Override
    public void actualizarSprite() {
        mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioFlorDeFuego");
    }
  
    @Override
    public boolean puedeRomperBloques() {
        return true;
    }

    @Override
    public boolean elHongoLoHaceSuperMario() {
        return false;
    }
    public boolean puedeSerMarioFuego(){
        return false;
    }


}

