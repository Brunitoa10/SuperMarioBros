package Logica;

import Entidades.Jugador;
import Entidades.Proyectiles.BolaDeFuego;
import Entidades.Proyectiles.Proyectil;
import Vista.GUI;

public class ControladorBolasDeFuego {

    protected Jugador mario;
    protected OyenteTeclado oyenteTeclado;
    protected int cooldownBola = 60;
    protected boolean empezarCooldown;
    protected Proyectil bolaDeFuego;

    public ControladorBolasDeFuego(Jugador mario, OyenteTeclado oyenteTeclado) {
        this.mario = mario;
        this.oyenteTeclado = oyenteTeclado;
    }

    public void lanzarBolaDeFuego(GUI controladorVistas) {
        if(oyenteTeclado.teclaEspacio && mario.puedeLanzarBolaDeFuego() && cooldownBola >= 30){
            System.out.println("Lanzo una bola de fuego");
            cooldownBola=0;
            bolaDeFuego = new BolaDeFuego(mario);

            controladorVistas.registrarEntidad(bolaDeFuego);
            empezarCooldown = true;
        }

        if (cooldownBola==20){
            bolaDeFuego.setPosicionEnY(-100);
        }

        if (empezarCooldown){
            cooldownBola++;
        }
    }

}
