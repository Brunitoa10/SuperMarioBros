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

    public void moverMario() {
        if(oyenteTeclado.teclaIzquierda || oyenteTeclado.teclaDerecha) {
            moverMarioHorizontalmente(oyenteTeclado);
        }
        if (!oyenteTeclado.teclaIzquierda && !oyenteTeclado.teclaDerecha && !oyenteTeclado.teclaArriba && mario.estaEnPlataforma()) {
            mario.setEstadoMovimiento(new MarioParado(mario));
        }
        if(oyenteTeclado.teclaArriba) {
            moverMarioVerticalmente();
        }
    }

    public void moverMarioVerticalmente() {
        if (mario.estaEnPlataforma())
            mario.setEnPlataforma(false);
        mario.saltar();
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
