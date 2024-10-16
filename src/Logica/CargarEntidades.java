package Logica;

import Entidades.Enemigos.Enemigo;
import Entidades.EntidadInmovil.Bandera;
import Entidades.EntidadInmovil.Moneda;
import Entidades.EntidadInmovil.Princesa;
import Entidades.Plataformas.Plataforma;
import Entidades.Power_Ups.PowerUp;
import Entidades.Proyectiles.Proyectil;

import java.util.List;

public class CargarEntidades {

    protected List<Enemigo> enemigos;
    protected List<Plataforma> plataformas;
    protected List<PowerUp> powerUps;
    protected List<Proyectil> proyectiles;
    protected List<Moneda> monedas;
    protected Bandera bandera;
    protected Princesa princesa;


    public CargarEntidades(Juego juego){
        this.enemigos = juego.getNivelActual().getEnemigos();
        this.plataformas = juego.getNivelActual().getPlataformas();
        this.powerUps = juego.getNivelActual().getPowerUps();
        this.proyectiles = juego.getNivelActual().getProyectiles();
        this.monedas = juego.getNivelActual().monedas;
        this.bandera = juego.getNivelActual().bandera;
        this.princesa = juego.getNivelActual().princesa;
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
    public List<Moneda> getMonedas() {
        return monedas;
    }
    public Bandera getBandera() {
        return bandera;
    }
    public Princesa getPrincesa() {
        return princesa;
    }

}

