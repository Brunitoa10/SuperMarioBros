package Entidades.Enemigos.EstadoLakitu;

import Entidades.Enemigos.Lakitu;
import Entidades.Jugador;
import IA.ComportamientoIA;
import Logica.Temporizador;

public class LakituMoviendose implements EstadoLakitu {
    protected Jugador mario;
    protected Lakitu lakitu;
    protected ComportamientoIA comportamiento;
    protected Temporizador temporizador;

    public LakituMoviendose(Jugador mario, Lakitu lakitu, ComportamientoIA ia) {
        this.mario = mario;
        this.lakitu = lakitu;
        this.comportamiento = ia;
        temporizador=new Temporizador();
        temporizador.iniciar();
        lakitu.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/Lakitu/LakituMoviendose.png");
    }

    public void actualizarLakitu() {
        comportamiento.actualizar(lakitu);
        lakitu.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/Lakitu/LakituMoviendose.png");
        if(comportamiento.comienzoAtaque() && temporizador.hanPasadoNSegundos(3000)) {
            lakitu.setEstadoLakitu(new LakituAtacando(mario, lakitu, comportamiento));
            temporizador.resetear();
        }
    }
}
