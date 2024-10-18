package Entidades.Enemigos;

import Entidades.EntidadMovil;
import Fabricas.Sprite;
import IA.ComportamientoIA;

public abstract class Enemigo extends EntidadMovil {

    private int puntajeDestruido;
    protected int puntajeKill;
    protected int posicionx;
    protected int posiciony;
    protected ComportamientoIA comportamientoIA;

    public Enemigo(int x, int y, Sprite sprite,ComportamientoIA comportamientoIA) {
        super(x, y, sprite);
        posicionx = x;
        posiciony = y;
        this.comportamientoIA = comportamientoIA;
    }


    // Método de actualización que puede extenderse en subclases
    public void actualizar() {
    	comportamientoIA.actualizar(this);
        super.actualizarEntidad();
        // Otros comportamientos que puedan ser agregados como colisiones o IA
    }
    
    // Método para cambiar dinámicamente la estrategia de IA
    public void setComportamientoIA(ComportamientoIA nuevaIA) {
        this.comportamientoIA = nuevaIA;
    }
    
    // Método para cambiar la dirección del enemigo
    public void cambiarDireccion() {
        direccion = -direccion; // Invierte la dirección
    }
}
