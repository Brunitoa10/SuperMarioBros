package EstadoJugador;

import Entidades.Jugador;
import Constantes.ConstantesPuntaje;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MarioEstrella implements EstadoJugador{


    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    protected Jugador mario;
    protected EstadoJugador estadoAnterior;

    public MarioEstrella(Jugador mario) {
        estadoAnterior = mario.getEstadoJugador();
        this.mario = mario;
        iniciarTemporizador();
    }

    public void recibeDanio() {
    }

    @Override
    public boolean esInmortal() {
        return true;
    }

    @Override
    public boolean puedeLanzarBolaFuego() {
        return false;
    }

    public void actualizarSprite() {

    }
    public boolean estadoEstrella() {
        return true;
    }
    private void iniciarTemporizador() {
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                mario.setEstadoJugador(estadoAnterior);

            }
        }, 8, TimeUnit.SECONDS);
    }

    public int getPuntajeEstrella(){
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
    public boolean elHongoLoHaceSuperMario(){
        return false;
    }
    public boolean puedeSerMarioFuego(){
        return true;
    }
    public String finalAnimacion(){
        return "";
    }
}
