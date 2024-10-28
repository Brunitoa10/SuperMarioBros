package Logica;

import Entidades.Jugador;

public class ControladorBolasDeFuego {

    protected Jugador mario;
    protected OyenteTeclado oyenteTeclado;
    protected Temporizador temporizador;

    public ControladorBolasDeFuego(Jugador mario, OyenteTeclado oyenteTeclado) {
        this.mario = mario;
        this.oyenteTeclado = oyenteTeclado;
        temporizador = new Temporizador();
        temporizador.iniciar();
    }

    public boolean puedeLanzarBolaDeFuego() {
        boolean toRet = false;
        if(oyenteTeclado.teclaEspacio && mario.puedeLanzarBolaDeFuego() && temporizador.hanPasadoNSegundos(2000)) {
            temporizador.resetear();
            temporizador.iniciar();
            toRet = true;
        }
        return toRet;
    }

}
