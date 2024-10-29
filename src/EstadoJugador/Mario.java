package EstadoJugador;

import Constantes.ConstantesPuntaje;
import Entidades.Entidad;
import Entidades.Jugador;

public class Mario implements EstadoJugador {

    protected Jugador mario;
    protected static final int FILA=0;

    public Mario(Jugador mario) {
        this.mario = mario;
        mario.setPosicionEnY(mario.getPosicionEnY() + 16);
        mario.getHitbox().setBounds(mario.getPosicionEnX(), mario.getPosicionEnY(), 32, 32);
    }

    public void recibeDanio(Entidad e) {
        mario.setMorir(true);
    }

    @Override
    public boolean puedeLanzarBolaFuego() {
        return false;
    }

    public String inicioAnimacion(){
        return "src/Recursos/Sprites/original/Jugador/PNGMario";
    }

    @Override
    public void actualizarEstadoJugador() {

    }


    @Override
    public boolean puedeRomperBloques() {
        return false;
    }

    public boolean elHongoLoHaceSuperMario() {
        return true;
    }

    public boolean puedeSerMarioFuego() {
        return true;
    }

    public String finalAnimacion() {
        return "";
    }

    public int getPuntaje(int columna){
        return mario.getPuntajes().getPuntaje(FILA,columna);
    }
}
