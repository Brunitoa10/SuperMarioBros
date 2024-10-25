package EstadoJugador;

import Entidades.Jugador;
import EstadoMovimiento.MarioEnAire;
import Logica.ConstantesPuntaje;
import Logica.Nivel;
import Entidades.Proyectiles.BolaDeFuego;

public class SuperMarioFuego implements EstadoJugador {

    protected Jugador mario;

    public SuperMarioFuego(Jugador mario) {
        this.mario = mario;
        mario.setPosicionEnY(mario.getPosicionEnY() - 16);
        mario.getHitbox().setBounds(mario.getPosicionEnX(), mario.getPosicionEnY(), 32, 48);
    }

    public void recibeDanio() {
        mario.setEstadoJugador(new MarioInvencible(mario));
    }

    public boolean estadoEstrella() {
        return false;
    }

    @Override
    public int getPuntajeEstrella() {
        return ConstantesPuntaje.PUNTAJE_ESTRELLA_NORMAL;
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
        return ConstantesPuntaje.PUNTAJE_FLOR_DE_FUEGO_CON_FUEGO;
    }

    @Override
    public boolean esInmortal() {
        return false;
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

    public String finalAnimacion(){
        return "";
    }
}

