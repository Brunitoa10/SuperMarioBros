package Entidades.Enemigos;

import Entidades.EntidadMovil;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;
import IA.ComportamientoIA;
import Logica.Temporizador;
import Visitor.VisitorEnemigo;
import Visitor.Visitor;

import java.util.List;

public abstract class Enemigo extends EntidadMovil {

    protected VisitorEnemigo visitorEnemigo;
    protected ComportamientoIA comportamientoIA;
    protected Temporizador temporizador;
    protected List<Enemigo> listaEnemigosNivel;

    public Enemigo(int x, int y, Sprite sprite, ComportamientoIA comportamientoIA, List<Enemigo> listaEnemigoNivel) {
        super(x, y, sprite);
        temporizador = new Temporizador();
        setDireccion(1);
        visitorEnemigo = new VisitorEnemigo(this);
        this.comportamientoIA = comportamientoIA;
        this.listaEnemigosNivel = listaEnemigoNivel;
    }

    public VisitorEnemigo getVisitorEnemigo() {
        return visitorEnemigo;
    }

    public int accept(Visitor v) {
        return v.visit(this);
    }

    public void actualizar() {
        comportamientoIA.actualizar(this);
        super.actualizarEntidad();
    }


    public void setComportamientoIA(ComportamientoIA nuevaIA) {
        this.comportamientoIA = nuevaIA;
    }


    public void cambiarDireccion() {
        direccion = -direccion;
    }

    public abstract int interactuar(Jugador jugador);

    public abstract int interactuarConProyectil(Proyectil proyectil);

    public void setDireccion(int direccion) {
        this.direccion = direccion;
        temporizador.iniciar();
    }

    public Temporizador getTemporizador() {
        return temporizador;
    }

    public void eliminarEntidad() {
        this.listaEnemigosNivel.remove(this);
    }

    public ComportamientoIA getComportamientoIA() {
        return comportamientoIA;
    }

    public void setEnPlataforma(boolean enPlataforma) {

    }

}