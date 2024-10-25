package EstadoJugador;

import Entidades.Jugador;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MarioInvencible implements EstadoJugador {
    protected Jugador mario;
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);


    public MarioInvencible(Jugador mario) {
        this.mario = mario;

        mario.setPosicionEnY(mario.getPosicionEnY() + 16);
        mario.getHitbox().setBounds(mario.getPosicionEnX(), mario.getPosicionEnY(), 32, 32);
        mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioGolpeado");
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
        mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioGolpeado");
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

    private void iniciarTemporizador() {
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                if (mario.getEstadoJugador().esInmortal())
                mario.setEstadoJugador(new Mario(mario));
            }
        }, 3, TimeUnit.SECONDS);
    }

    public String finalAnimacion(){
        return ".gif";
    }
}
