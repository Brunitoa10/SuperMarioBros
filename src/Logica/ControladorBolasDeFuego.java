package Logica;

import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.FabricaEntidad;
import Vista.GUI;
import Vista.ObserverGrafica.Observer;

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

    public void dispararBolaFuego(GUI controladorVistas, FabricaEntidad fabricaEntidades) {
        Proyectil bolaDeFuego = fabricaEntidades.crearBolaDeFuego(mario, controladorVistas.getJuego().getNivelActual().getProyectiles());
        controladorVistas.getJuego().getNivelActual().agregarProyectil(bolaDeFuego);
        Observer observer = controladorVistas.registrarEntidad(bolaDeFuego);
        bolaDeFuego.registrarObserver(observer);
    }

}
