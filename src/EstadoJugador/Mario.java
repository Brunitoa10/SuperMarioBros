package EstadoJugador;

import Constantes.ConstantesPuntaje;
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
        return "src/Recursos/Sprites/original/Jugador/PNGMario";
    }

    @Override
    public void actualizarEstadoJugador() {

    }

    @Override
    public void interactuar(SuperChampinion powerUp) {
        mario.setEstadoJugador(new SuperMario(mario));
    }

    @Override
    public void interactuar(FlorDeFuego powerUp) {
        mario.setEstadoJugador(new SuperMarioFuego(mario));
    }

    @Override
    public void interactuar(Estrella powerUp) {
        mario.setEstadoJugador(new MarioEstrella(mario));
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
