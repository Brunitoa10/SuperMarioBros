package EstadoJugador;

import Constantes.ConstantesPuntaje;
import Entidades.Entidad;
import Entidades.Jugador;

public class Mario implements EstadoJugador {

    protected Jugador mario; 

    public Mario(Jugador mario) {
        this.mario = mario;
        mario.setPosicionEnY(mario.getPosicionEnY() + 16);
        mario.getHitbox().setBounds(mario.getPosicionEnX(), mario.getPosicionEnY(), 32, 32);
    }

    public void recibeDanio(Entidad e) {
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

    public int getPuntajeEstrella(){
        return ConstantesPuntaje.PUNTAJE_ESTRELLA_NORMAL;
    }

    @Override
    public int getPuntajeSuperChampinion() {
        return ConstantesPuntaje.PUNTAJE_SUPER_CHAMPINION_NORMAL;
    }

    @Override
    public int getPuntajeChampinionVerde() {
        return ConstantesPuntaje.PUNTAJE_CHAMPINION_VERDE_NORMAL;
    }

    @Override
    public int getPuntajeFlorDeFuego() {
        return ConstantesPuntaje.PUNTAJE_FLOR_DE_FUEGO_NORMAL;
    }
}
