package Logica;

import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Vista.ObserverGrafica.Observer;

public class ControladorBolasDeFuego {

    protected Jugador mario;
    protected OyenteTeclado oyenteTeclado;
    protected Temporizador temporizador;
    protected Juego juego;

    public ControladorBolasDeFuego(Jugador mario, OyenteTeclado oyenteTeclado, Juego juego) {
        this.mario = mario;
        this.juego = juego;
        this.oyenteTeclado = oyenteTeclado;
        temporizador = new Temporizador();
        temporizador.iniciar();
    }

    public boolean puedeLanzarBolaDeFuego() {
        boolean toRet = false;
        if(oyenteTeclado.teclaEspacio && temporizador.hanPasadoNSegundos(2000)) {
            temporizador.resetear();
            temporizador.iniciar();
            toRet = true;
        }
        return toRet;
    }

    public void dispararBolaFuego() {
        Proyectil bolaDeFuego = juego.fabricaEntidades.crearBolaDeFuego(mario, juego.controladorVistas.getJuego().getNivelActual().getProyectiles());
        juego.controladorVistas.getJuego().getNivelActual().agregarProyectil(bolaDeFuego);
        Observer observer = juego.controladorVistas.registrarEntidad(bolaDeFuego);
        bolaDeFuego.registrarObserver(observer);
    }

}
