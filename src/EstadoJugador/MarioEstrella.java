package EstadoJugador;

import Constantes.ConstantesPuntaje;
import Entidades.Entidad;
import Entidades.Jugador;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MarioEstrella implements EstadoJugador {


    protected Jugador mario;
    protected EstadoJugador estadoAnterior;
    protected int alto;
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public MarioEstrella(Jugador mario) {
        estadoAnterior = mario.getEstadoJugador();
        this.mario = mario;
        iniciarTemporizador();
        alto = (int) mario.getHitbox().getHeight();
        mario.setPosicionEnY(mario.getPosicionEnY() - 32 + alto);
        mario.getHitbox().setBounds(mario.getPosicionEnX(), mario.getPosicionEnY(), 32, 32);
        mario.setVelocidad(6);

    }

    public void recibeDanio(Entidad e) {
        e.setAEliminar();
    }

    @Override
    public boolean puedeLanzarBolaFuego() {
        return false;
    }

    public void actualizarSprite() {
        mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioEstrella");
    }

    private void iniciarTemporizador() {
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                mario.setVelocidad(4);
                mario.setPosicionEnY(mario.getPosicionEnY() + 32 - alto);
                mario.getHitbox().setBounds(mario.getPosicionEnX(), mario.getPosicionEnY(), 32, alto);
                mario.setEstadoJugador(estadoAnterior);

            }
        }, 8, TimeUnit.SECONDS);
    }

    public int getPuntajeEstrella() {
        return ConstantesPuntaje.PUNTAJE_ESTRELLA_CON_ESTRELLA;
    }

    @Override
    public int getPuntajeSuperChampinion() {
        return estadoAnterior.getPuntajeSuperChampinion();
    }

    @Override
    public int getPuntajeChampinionVerde() {
        return estadoAnterior.getPuntajeChampinionVerde();
    }

    @Override
    public int getPuntajeFlorDeFuego() {
        return estadoAnterior.getPuntajeFlorDeFuego();
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
}
