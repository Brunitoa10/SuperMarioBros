package EstadoJugador;

import Entidades.Entidad;
import Entidades.Jugador;
import Entidades.Power_Ups.Estrella;
import Entidades.Power_Ups.FlorDeFuego;
import Entidades.Power_Ups.SuperChampinion;

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
        return mario.getSprite().getRutaModo() + "/Jugador/PNGMario";
    }

    @Override
    public void actualizarEstadoJugador() {

    }

    @Override
    public int interactuar(SuperChampinion powerUp, int COLUMNA) {
        mario.setEstadoJugador(new SuperMario(mario));
        return getPuntaje(COLUMNA);
    }

    @Override
    public int interactuar(FlorDeFuego powerUp, int COLUMNA) {
        mario.setEstadoJugador(new SuperMarioFuego(mario));
        return getPuntaje(COLUMNA);
    }

    @Override
    public int interactuar(Estrella powerUp, int COLUMNA) {
        mario.setEstadoJugador(new MarioEstrella(mario));
        return getPuntaje(COLUMNA);
    }


    @Override
    public boolean puedeRomperBloques() {
        return false;
    }

    public String finalAnimacion() {
        return "";
    }

    public int getPuntaje(int columna){
        return mario.getPuntajes().getPuntaje(FILA,columna);
    }
}
