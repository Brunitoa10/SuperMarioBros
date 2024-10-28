package Logica;

import Entidades.Enemigos.*;
import Entidades.*;
import Entidades.EntidadInmovil.*;
import Entidades.Plataformas.*;
import Entidades.Power_Ups.*;
import Entidades.Proyectiles.*;

import java.util.LinkedList;
import java.util.List;

public class Nivel {

    protected List<Enemigo> enemigos;
    protected List<Plataforma> plataformas;
    protected List<PowerUp> powerUps;
    protected List<Proyectil> proyectiles;
    protected List<Moneda> monedas;
    protected List<Vacio> vacios;
    protected List<Entidad> entidadesAEliminar;
    protected Jugador jugador;
    protected Bandera bandera;
    protected Princesa princesa;
    protected int nivel;

        public Nivel(int numero) {
            this.enemigos = new LinkedList<Enemigo>();
            this.plataformas = new LinkedList<Plataforma>();
            this.powerUps = new LinkedList<PowerUp>();
            this.proyectiles = new LinkedList<Proyectil>();
            this.monedas = new LinkedList<Moneda>();
            this.vacios = new LinkedList<Vacio>();
            this.entidadesAEliminar = new LinkedList<Entidad>();
            nivel = numero;
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

        public List<Vacio> getVacios() {
            return vacios;
        }

        public List<Moneda> getMonedas() {
            return monedas;
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

        public void agregarProyectil(Proyectil proyectil) {
            this.proyectiles.addLast(proyectil);
        }

        public void agregarMoneda(Moneda moneda) {
            this.monedas.addLast(moneda);
        }

        public void agregarBandera(Bandera bandera) {
            this.bandera = bandera;
        }

        public void agregarPrincesa(Princesa princesa) {
            this.princesa = princesa;
        }

        public void agregarVacio(Vacio vacio) {
            this.vacios.addLast(vacio);
        }

        public List<Entidad> getEntidadesAEliminar() {
            return entidadesAEliminar;
        }
        
        public int nivel() {
        	return nivel;
        }

    }
