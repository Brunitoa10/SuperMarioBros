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
    protected int posicionx;
    protected int posiciony;
    protected ComportamientoIA comportamientoIA;
    protected Temporizador temporizador;
    protected List<Enemigo> listaEnemigosNivel;

    public Enemigo(int x, int y, Sprite sprite, ComportamientoIA comportamientoIA, List<Enemigo> listaEnemigoNivel) {
        super(x, y, sprite);
        posicionx = x;
        posiciony = y;
        temporizador = new Temporizador();
        setDireccion(1);
        visitorEnemigo = new VisitorEnemigo(this);
        this.comportamientoIA = comportamientoIA;
        this.listaEnemigosNivel = listaEnemigoNivel;
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

    // Método para cambiar dinámicamente la estrategia de IA
    public void setComportamientoIA(ComportamientoIA nuevaIA) {
        this.comportamientoIA = nuevaIA;
    }

    // Método para cambiar la dirección del enemigo
    public void cambiarDireccion() {
        direccion = -direccion; // Invierte la dirección
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

}