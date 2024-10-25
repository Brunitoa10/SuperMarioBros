package EstadoJugador;

import Entidades.Jugador;
import Constantes.ConstantesPuntaje;

public class SuperMario implements EstadoJugador{

    protected Jugador mario;

    public SuperMario(Jugador mario) {
        this.mario = mario;
        mario.setPosicionEnY(mario.getPosicionEnY()-16);
        mario.getHitbox().setBounds(mario.getPosicionEnX(),mario.getPosicionEnY(),32,48);
    }

    public void recibeDanio() {
        mario.setEstadoJugador(new MarioInvencible(mario));
    }

    @Override
    public boolean puedeLanzarBolaFuego() {
        return false;
    }

    public void actualizar(){

    }

    public void actualizarSprite() {
        mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioPowerUp");
    }

    @Override
    public boolean puedeRomperBloques() {
        return true;
    }
    public boolean elHongoLoHaceSuperMario(){
        return false;
    }
    public boolean puedeSerMarioFuego(){
        return true;
    }

    @Override
    public boolean esInmortal() {
        return false;
    }

    public boolean estadoEstrella() {
        return false;
    }

    @Override
    public int getPuntajeEstrella() {
        return ConstantesPuntaje.PUNTAJE_ESTRELLA_SUPER_MARIO;
    }

    @Override
    public int getPuntajeSuperChampinion() {
        return ConstantesPuntaje.PUNTAJE_SUPER_CHAMPINION_SUPER_MARIO;
    }

    @Override
    public int getPuntajeChampinionVerde() {
        return ConstantesPuntaje.PUNTAJE_CHAMPINON_VERDE;
    }

    @Override
    public int getPuntajeFlorDeFuego() {
        return ConstantesPuntaje.PUNTAJE_FLOR_DE_FUEGO_SUPER_MARIO;
    }

    public String finalAnimacion(){
        return "";
    }
}
