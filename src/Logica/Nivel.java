package Logica;

import Entidades.Enemigos.*;
import Entidades.*;
import Entidades.Plataformas.*;
import Entidades.Power_Ups.*;
import Entidades.Proyectiles.*;
import Fabricas.FabricaEntidad;

import java.util.LinkedList;
import java.util.List;

public class Nivel {

    protected String ruta_nivel_txt;
    protected List<Enemigo> enemigos;
    protected List<Plataforma> plataformas;
    protected List<PowerUp> powerUps;
    protected List<Proyectil> proyectiles;
    protected Jugador jugador;
    protected int vida;
    protected int puntajeTotal;
    protected FabricaEntidad fabricaEntidades;

    public Nivel(String ruta_nivel_txt) {
        this.puntajeTotal = 0;
        this.vida = 3;
        this.ruta_nivel_txt = ruta_nivel_txt;
        this.enemigos = new LinkedList<Enemigo>();
        this.plataformas = new LinkedList<Plataforma>();
        this.powerUps = new LinkedList<PowerUp>();
        this.proyectiles = new LinkedList<Proyectil>();
    }

    public Jugador getJugador() {
        return jugador;
    }

    public List<Enemigo> getEnemigos() {
        return enemigos;
    }

    public List<Plataforma> getPlataformas() {
        return plataformas;
    }

    public List<PowerUp> getPowerUps() {
        return powerUps;
    }

    public List<Proyectil> getProyectiles() {
        return proyectiles;
    }

    public void agregarJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void agregarEnemigo(Enemigo enemigo) {
        this.enemigos.addLast(enemigo);
    }

    public void agregarPlataforma(Plataforma plataforma) {
        this.plataformas.addLast(plataforma);
    }

    public void agregarPowerUp(PowerUp powerUp) {
        this.powerUps.addLast(powerUp);
    }

    public void agregarProyectiles(Proyectil proyectiles) {
        this.proyectiles.addLast(proyectiles);
    }
}
