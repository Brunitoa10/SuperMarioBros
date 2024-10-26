package Entidades.Enemigos.EstadoLakitu;

import Entidades.Enemigos.Lakitu;
import Entidades.Jugador;
import IA.ComportamientoIA;
import IA.IAatacar;
import Logica.Temporizador;

public class LakituAtacando implements EstadoLakitu{
    protected Jugador mario;
    protected Lakitu lakitu;
    protected ComportamientoIA comportamiento;
    protected Temporizador temporizador;
    public LakituAtacando(Jugador mario, Lakitu lakitu, ComportamientoIA ia) {
        this.mario = mario;
        this.lakitu = lakitu;
        this.comportamiento = ia;
        temporizador=new Temporizador();
        temporizador.iniciar();
    }

    @Override
    public void actualizarLakitu() {
        comportamiento.actualizar(lakitu);
        lakitu.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/Lakitu/LakituAtaca.gif");
        if(temporizador.hanPasadoNSegundos(5000)){
            lakitu.lanzar();
            lakitu.setEstadoLakitu(new LakituMoviendose(mario,lakitu,comportamiento));
        }
    }
}
