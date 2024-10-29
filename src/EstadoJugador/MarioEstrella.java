package EstadoJugador;

import Constantes.ConstantesPuntaje;
import Entidades.Entidad;
import Entidades.Jugador;
import Logica.Temporizador;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MarioEstrella implements EstadoJugador {


    protected Jugador mario;
    protected EstadoJugador estadoAnterior;
    protected int alto;
    protected static final int FILA=3;
    protected Temporizador temporizador;

    public MarioEstrella(Jugador mario) {
        estadoAnterior = mario.getEstadoJugador();
        this.mario = mario;
        alto = (int) mario.getHitbox().getHeight();
        mario.setPosicionEnY(mario.getPosicionEnY() - 32 + alto);
        mario.getHitbox().setBounds(mario.getPosicionEnX(), mario.getPosicionEnY(), 32, 32);
        mario.setVelocidad(6);
        temporizador = new Temporizador();
        temporizador.iniciar();
    }

    public void recibeDanio(Entidad e) {
        e.setAEliminar();
    }

    @Override
    public boolean puedeLanzarBolaFuego() {
        return false;
    }


    public void actualizarEstadoJugador() {
        if (temporizador.hanPasadoNSegundos(7000)) {
            temporizador.resetear();
            mario.setVelocidad(4);
            mario.setPosicionEnY(mario.getPosicionEnY() + 32 - alto);
            mario.getHitbox().setBounds(mario.getPosicionEnX(), mario.getPosicionEnY(), 32, alto);
            mario.setEstadoJugador(estadoAnterior);
        }
    }

    public String inicioAnimacion(){
        return "src/Recursos/Sprites/original/Jugador/PNGMario/MarioEstrella";
    }

    @Override
    public boolean puedeRomperBloques() {
        return false;
    }

    public boolean elHongoLoHaceSuperMario() {
        return false;
    }

    public boolean puedeSerMarioFuego() {
        return true;
    }

    public String finalAnimacion() {
        return ".gif";
    }

    public int getPuntaje(int columna){
        return mario.getPuntajes().getPuntaje(FILA,columna);
    }
}
