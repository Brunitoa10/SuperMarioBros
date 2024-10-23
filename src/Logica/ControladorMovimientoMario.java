package Logica;

import Entidades.Jugador;
import EstadoMovimiento.MarioParado;

public class ControladorMovimientoMario {

    protected Jugador mario;
    protected OyenteTeclado oyenteTeclado;

    public ControladorMovimientoMario(Jugador mario, OyenteTeclado oyenteTeclado) {
        this.mario = mario;
        this.oyenteTeclado = oyenteTeclado;
    }

    public void moverMario(boolean debeSaltar) {
        if(oyenteTeclado.teclaIzquierda || oyenteTeclado.teclaDerecha) {
            moverMarioHorizontalmente(oyenteTeclado);
        }
        if(debeSaltar) {
            mario.saltar();
        }
        if (!oyenteTeclado.teclaIzquierda && !oyenteTeclado.teclaDerecha && !oyenteTeclado.teclaArriba && mario.estaEnPlataforma()) {
            mario.setEstadoMovimiento(new MarioParado(mario));
        }
    }

    private void moverMarioHorizontalmente(OyenteTeclado oyenteTeclado) {
        if(oyenteTeclado.teclaIzquierda) {
            moverM(-1);
        }
        else {
            moverM(1);
        }
    }

    private void moverM(int direccionMario) {
        mario.desplazarEnX(direccionMario);
        mario.setDireccion(direccionMario);
        mario.desplazarEnX(0);
    }

}
