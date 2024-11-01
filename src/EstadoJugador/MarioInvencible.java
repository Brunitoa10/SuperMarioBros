package EstadoJugador;

import Constantes.ConstantesPuntaje;
import Entidades.Entidad;
import Entidades.Jugador;
import Entidades.Power_Ups.Estrella;
import Entidades.Power_Ups.FlorDeFuego;
import Entidades.Power_Ups.SuperChampinion;
import Logica.Temporizador;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class MarioInvencible implements EstadoJugador {
    protected Jugador mario;
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
        return mario.getSprite().getRutaModo() + "/Jugador/PNGMario/MarioGolpeado";
    }

    @Override
    public boolean puedeRomperBloques() {
        return false;
    }

    public String finalAnimacion() {
        return ".gif";
    }

    public int getPuntaje(int columna){
        return mario.getPuntajes().getPuntaje(FILA,columna);
    }

    @Override
    public int interactuar(SuperChampinion powerUp, int COLUMNA) {
        mario.setEstadoJugador(new SuperMario(mario));
        return getPuntaje(COLUMNA);
    }

    @Override
    public int interactuar(FlorDeFuego powerUp, int COLUMNA) {
        mario.setEstadoJugador(new SuperMarioFuego(mario));
        return getPuntaje(COLUMNA);
    }

    @Override
    public int interactuar(Estrella powerUp, int COLUMNA) {
        mario.setEstadoJugador(new Mario(mario));
        mario.setEstadoJugador(new MarioEstrella(mario));
        return getPuntaje(COLUMNA);
    }
}
