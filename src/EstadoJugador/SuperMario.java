package EstadoJugador;

import Constantes.ConstantesPuntaje;
import Entidades.Entidad;
import Entidades.Jugador;
import Entidades.Power_Ups.Estrella;
import Entidades.Power_Ups.FlorDeFuego;
import Entidades.Power_Ups.SuperChampinion;

public class SuperMario implements EstadoJugador {

    protected Jugador mario;
    protected static final int FILA=1;
    public SuperMario(Jugador mario) {
        this.mario = mario;
        mario.setPosicionEnY(mario.getPosicionEnY() - 16);
        mario.getHitbox().setBounds(mario.getPosicionEnX(), mario.getPosicionEnY(), 32, 48);
    }

    public void recibeDanio(Entidad e) {
        mario.setEstadoJugador(new MarioInvencible(mario));
    }

    @Override
    public boolean puedeLanzarBolaFuego() {
        return false;
    }

    public void actualizar() {

    }
    @Override
    public String inicioAnimacion(){
        return mario.getSprite().getRutaModo() + "/Jugador/PNGMario/MarioPowerUp";
    }

    @Override
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
    public void interactuar(SuperChampinion powerUp) {

    }

    @Override
    public void interactuar(FlorDeFuego powerUp) {
        mario.setEstadoJugador(new SuperMarioFuego(mario));
    }

    @Override
    public void interactuar(Estrella powerUp) {
        mario.setEstadoJugador(new MarioEstrella(mario));
    }
}
