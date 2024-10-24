package Entidades.Enemigos;

import Entidades.EntidadMovil;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;
import IA.ComportamientoIA;
import Visitor.VisitorEnemigo;
import Visitor.Visitor;

import java.util.List;

public abstract class Enemigo extends EntidadMovil {

    protected VisitorEnemigo visitorEnemigo;
    protected int posicionx;
    protected int posiciony;
    protected ComportamientoIA comportamientoIA;
    protected List<Enemigo> listaEnemigoNivel;

    public Enemigo(int x, int y, Sprite sprite,ComportamientoIA comportamientoIA, List<Enemigo> listaEnemigoNivel) {
        super(x, y, sprite);
        posicionx = x;
        posiciony = y;
        visitorEnemigo = new VisitorEnemigo(this);
        this.comportamientoIA = comportamientoIA;
        this.listaEnemigoNivel = listaEnemigoNivel;
    }

    public VisitorEnemigo getVisitorEnemigo() {
        return visitorEnemigo;
    }

    // Método de actualización que puede extenderse en subclases
    public void actualizar() {
    	comportamientoIA.actualizar(this);
        super.actualizarEntidad();
        // Otros comportamientos que puedan ser agregados como colisiones o IA
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void eliminarEntidad() {
        this.listaEnemigoNivel.remove(this);
    }

    // Método para cambiar dinámicamente la estrategia de IA
    public void setComportamientoIA(ComportamientoIA nuevaIA) {
        this.comportamientoIA = nuevaIA;
    }
    
    // Método para cambiar la dirección del enemigo
    public void cambiarDireccion() {
        direccion = -direccion; // Invierte la dirección
    }

    public void interactuar(Jugador jugador) {

    }

    public void interactuarConProyectil(Proyectil proyectil) {

    }

}
