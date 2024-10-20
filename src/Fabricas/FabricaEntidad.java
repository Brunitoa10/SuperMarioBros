package Fabricas;

import Entidades.Enemigos.*;
import Entidades.EntidadInmovil.*;
import Entidades.Jugador;
import Entidades.Plataformas.*;
import Entidades.Power_Ups.*;
import Entidades.Proyectiles.BolaDeFuego;
import Entidades.Proyectiles.ProyectilSpiny;
import Entidades.Vacio;

public interface FabricaEntidad {
    public Goomba crearGoomba(int x, int y);
    public BuzzyBeetle crearBuzzyBeetle(int x, int y);
    public KoopaTroopa crearKoopaTroopa(int x, int y);
    public Lakitu crearLakitu(int x, int y);
    public Spiny crearSpiny(int x, int y);
    public PiranhaPlant crearPiranhaPlant(int x, int y);
    public Moneda crearMoneda(int x, int y);
    public ChampinionVerde crearChampinionVerde(int x, int y);
    public Estrella crearEstrella(int x, int y);
    public FlorDeFuego crearFlorDeFuego(int x, int y);
    public SuperChampinion crearSuperChampinion(int x, int y);
    public BolaDeFuego crearBolaDeFuego(int x, int y);
    public ProyectilSpiny crearProyectilSpiny(int x, int y);
    public LadrilloSolido crearLadrilloSolido(int x, int y);
    public BloqueSolido crearBloqueSolido(int x, int y);
    public Tuberia crearTuberia(int x, int y);
    public BloquePregunta crearBloquePregunta(int x, int y, int recompensa);
    public Jugador crearJugador(int x, int y);
    public Bandera crearBandera(int x, int y);
    public Princesa crearPrincesa(int x, int y);
    public Vacio crearVacio(int x, int y);
}
