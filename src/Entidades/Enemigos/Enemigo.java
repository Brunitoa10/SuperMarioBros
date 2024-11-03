package Entidades.Enemigos;

import Entidades.Entidad;
import Entidades.EntidadMovil;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Entidades.Vacio;
import Fabricas.Sprite;
import IA.ComportamientoIA;
import Logica.Temporizador;
import Visitor.Visitor;
import Visitor.VisitorEnemigo;

import java.util.List;

public abstract class Enemigo extends EntidadMovil {

    protected VisitorEnemigo visitorEnemigo;
    protected ComportamientoIA comportamientoIA;
    protected Temporizador temporizador;
    protected List<Enemigo> listaEnemigosNivel;
    boolean estoyEnPlataforma;

    public Enemigo(int x, int y, Sprite sprite, ComportamientoIA comportamientoIA, List<Enemigo> listaEnemigoNivel) {
        super(x, y, sprite);
        temporizador = new Temporizador();
        setDireccion(1);
        visitorEnemigo = new VisitorEnemigo(this);
        this.comportamientoIA = comportamientoIA;
        this.listaEnemigosNivel = listaEnemigoNivel;
        estoyEnPlataforma = false;
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

    public abstract int interactuar(Jugador jugador);

    public void logicaInteraccionConProyectil(Proyectil proyectil) {
        this.setAEliminar();
        proyectil.setDireccion(0);
    }

    public int interactuarConProyectil(Proyectil proyectil) {
        logicaInteraccionConProyectil(proyectil);
        return 0;
    }

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

    public boolean estoyEnPlataforma() {
        return estoyEnPlataforma;
    }

    public void setEnPlataforma(boolean enPlataforma) {
        estoyEnPlataforma = enPlataforma;
    }

    public boolean colisionIzquierda(Entidad entidad) {
        int solapamientoY = calcularSolapamientoY(this.getHitbox(), entidad.getHitbox());
        int rangoColision = (int) (entidad.getHitbox().getMaxX() - entidad.getHitbox().getWidth() / 2);
        boolean colisiona = (this.getHitbox().getMinX() <= entidad.getHitbox().getMaxX()) && (this.getHitbox().getMinX() >= rangoColision);
        return colisiona && ((solapamientoY > 2) || (solapamientoY == entidad.getHitbox().getHeight()));
    }

    public boolean colisionDerecha(Entidad entidad) {
        int solapamientoY = calcularSolapamientoY(this.getHitbox(), entidad.getHitbox());
        int rangoColision = (int) (entidad.getHitbox().getMinX() + entidad.getHitbox().getWidth() / 2);
        boolean colisiona = (this.getHitbox().getMaxX() >= entidad.getHitbox().getMinX()) && (this.getHitbox().getMaxX() <= rangoColision);
        return colisiona && (((solapamientoY > 2) || (solapamientoY == entidad.getHitbox().getHeight())));
    }

    public boolean colisionVacio(Vacio vacio) {
        int toleranciaX = 1;
        int toleranciaY = 3;
        boolean colisionEnX = ((this.getPosicionEnX() + toleranciaX ) >= vacio.getPosicionEnX()) && ((this.getPosicionEnX() - toleranciaX ) <= vacio.getPosicionEnX());
        boolean puedeCaerse = (vacio.getHitbox().getMinY() + toleranciaY) > this.getHitbox().getMaxY() && (vacio.getHitbox().getMinY() - toleranciaY <= this.getHitbox().getMaxY());
        return colisionEnX && puedeCaerse;
    }

}