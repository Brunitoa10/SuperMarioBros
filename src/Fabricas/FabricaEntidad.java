package Fabricas;

import Entidades.Enemigos.*;
import Entidades.EntidadInmovil.*;
import Entidades.Jugador;
import Entidades.Plataformas.*;
import Entidades.Power_Ups.*;
import Entidades.Proyectiles.BolaDeFuego;
import Entidades.Proyectiles.Proyectil;
import Entidades.Proyectiles.ProyectilKoopa;
import Entidades.Proyectiles.ProyectilSpiny;
import Entidades.Vacio;

import java.util.List;

public interface FabricaEntidad {
    public Goomba crearGoomba(int x, int y, List<Enemigo> listaEnemigoNivel);
    public BuzzyBeetle crearBuzzyBeetle(int x, int y, List<Enemigo> listaEnemigoNivel);
    public KoopaTroopa crearKoopaTroopa(int x, int y,ProyectilKoopa proyectilKoopa, List<Enemigo> listaEnemigoNivel);
    public Lakitu crearLakitu(int x, int y, List<Enemigo> listaEnemigoNivel);
    public Spiny crearSpiny(int x, int y, List<Enemigo> listaEnemigoNivel);
    public PiranhaPlant crearPiranhaPlant(int x, int y, List<Enemigo> listaEnemigoNivel);
    public Moneda crearMoneda(int x, int y, List<Moneda> listaMonedasNivel);
    public ChampinionVerde crearChampinionVerde(int x, int y, List<PowerUp> listaPowerUpsNivel);
    public Estrella crearEstrella(int x, int y, List<PowerUp> listaPowerUpsNivel);
    public FlorDeFuego crearFlorDeFuego(int x, int y, List<PowerUp> listaPowerUpsNivel);
    public SuperChampinion crearSuperChampinion(int x, int y, List<PowerUp> listaPowerUpsNivel);
    public BolaDeFuego crearBolaDeFuego(Jugador jugador, List<Proyectil> listaProyectilNivel);
    public ProyectilSpiny crearProyectilSpiny(int x, int y, List<Proyectil> listaProyectilNivel);
    public ProyectilKoopa crearProyectilKoopa(int x, int y, List<Proyectil> listaProyectilNivel);
    public LadrilloSolido crearLadrilloSolido(int x, int y, List<Plataforma> listaPlataformaNivel);
    public BloqueSolido crearBloqueSolido(int x, int y, List<Plataforma> listaPlataformaNivel);
    public Tuberia crearTuberia(int x, int y, List<Plataforma> listaPlataformaNivel);
    public BloquePregunta crearBloquePregunta(int x, int y, PowerUp p, List<Plataforma> listaPlataformaNivel);
    public Jugador crearJugador(int x, int y);
    public Bandera crearBandera(int x, int y);
    public Princesa crearPrincesa(int x, int y);
    public Vacio crearVacio(int x, int y, List<Vacio> listaVaciosNivel);
}
