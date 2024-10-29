package EstadoJugador;

import Constantes.ConstantesPuntaje;
import Entidades.Entidad;
import Entidades.Jugador;
import Logica.Temporizador;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MarioInvencible implements EstadoJugador {
    protected Jugador mario;
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    protected static final int FILA=0;
    protected Temporizador temporizador;

    public MarioInvencible(Jugador mario) {
        this.mario = mario;
        temporizador = new Temporizador();
        temporizador.iniciar();
        mario.setPosicionEnY(mario.getPosicionEnY() + 16);
        mario.getHitbox().setBounds(mario.getPosicionEnX(), mario.getPosicionEnY(), 32, 32);
        mario.getSprite().setRutaImagen("src/Recursos/Sprites/original/Jugador/PNGMario/MarioGolpeado");
    }

    public void recibeDanio(Entidad e) {
    }

    @Override
    public boolean puedeLanzarBolaFuego() {
        return false;
    }

    public void actualizarEstadoJugador() {
        if(temporizador.hanPasadoNSegundos(3000)){
            temporizador.resetear();
            mario.setEstadoJugador(new Mario(mario));
        }
    }

    @Override
    public String inicioAnimacion(){
        return "src/Recursos/Sprites/original/Jugador/PNGMario/MarioGolpeado";
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
