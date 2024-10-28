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

    public void moverMario(Temporizador temporizador) {
        if(oyenteTeclado.teclaIzquierda || oyenteTeclado.teclaDerecha) {
            moverMarioHorizontalmente(oyenteTeclado);
        }
        if(oyenteTeclado.teclaArriba && mario.estaEnPlataforma()) {
            mario.saltar();
        }
        if (!oyenteTeclado.teclaIzquierda && !oyenteTeclado.teclaDerecha && !oyenteTeclado.teclaArriba
                && !oyenteTeclado.teclaEspacio && temporizador.hanPasadoNSegundos(3000)) {
            if (mario.estaEnPlataforma()) {
                mario.setEstadoMovimiento(new MarioParado(mario));
            }
            else {
                mario.getEstadoMovimiento().EnAire();
            }
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
