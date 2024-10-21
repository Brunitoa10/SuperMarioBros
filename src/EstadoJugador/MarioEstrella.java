package EstadoJugador;

import Entidades.Jugador;
import Logica.Nivel;

public class MarioEstrella implements EstadoJugador{

    protected Jugador mario;

    public MarioEstrella(Jugador mario) {
        this.mario = mario;
    }

    public void recibeDanio() {
    }

    @Override
    public boolean esInmortal() {
        return true;
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
    public boolean elHongoLoHaceSuperMario(){
        return false;
    }
    public boolean puedeSerMarioFuego(){
        return true;
    }
    public String finalAnimacion(){
        return "";
    }
}
