package EstadoJugador;

import Entidades.Jugador;
import Logica.Nivel;

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
    public String finalAnimacion(){
        return "";
    }
}
