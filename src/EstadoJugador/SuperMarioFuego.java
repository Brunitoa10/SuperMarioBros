package EstadoJugador;

import Constantes.ConstantesPuntaje;
import Entidades.Entidad;
import Entidades.Jugador;
import Entidades.Power_Ups.Estrella;
import Entidades.Power_Ups.FlorDeFuego;
import Entidades.Power_Ups.SuperChampinion;

public class SuperMarioFuego implements EstadoJugador {

    protected Jugador mario;
    protected static final int FILA=2;

    public SuperMarioFuego(Jugador mario) {
        this.mario = mario;
        mario.setPosicionEnY(mario.getPosicionEnY() - 16);
        mario.getHitbox().setBounds(mario.getPosicionEnX(), mario.getPosicionEnY(), 32, 48);
    }

    public void recibeDanio(Entidad e) {
        mario.setEstadoJugador(new MarioInvencible(mario));
    }

    @Override
    public boolean puedeLanzarBolaFuego() {
        return true;
    }

    public String inicioAnimacion(){
        return mario.getSprite().getRutaModo() + "/Jugador/PNGMario/MarioFlorDeFuego";
    }

    public void actualizarEstadoJugador() {

    }

    @Override
    public boolean puedeRomperBloques() {
        return true;
    }

    public String finalAnimacion() {
        return "";
    }

    public int getPuntaje(int columna){
        return mario.getPuntajes().getPuntaje(FILA,columna);
    }

    @Override
    public int interactuar(SuperChampinion powerUp, int COLUMNA) {
        return getPuntaje(COLUMNA);
    }

    @Override
    public int interactuar(FlorDeFuego powerUp, int COLUMNA) {
        return getPuntaje(COLUMNA);
    }

    @Override
    public int interactuar(Estrella powerUp, int COLUMNA) {
        mario.setEstadoJugador(new MarioEstrella(mario));
        return getPuntaje(COLUMNA);
    }
}

