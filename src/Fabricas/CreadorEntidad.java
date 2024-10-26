package Fabricas;

import Entidades.Enemigos.*;
import Entidades.Jugador;
import Entidades.EntidadInmovil.Bandera;
import Entidades.EntidadInmovil.Moneda;
import Entidades.EntidadInmovil.Princesa;
import Entidades.Plataformas.*;
import Entidades.Power_Ups.*;
import Entidades.Proyectiles.BolaDeFuego;
import Entidades.Proyectiles.Proyectil;
import Entidades.Proyectiles.ProyectilKoopa;
import Entidades.Proyectiles.ProyectilSpiny;
import Entidades.Vacio;

import java.util.List;

public class CreadorEntidad implements FabricaEntidad {

    protected FabricaSprites fabricaSprites;
    private Jugador mario;
    public CreadorEntidad(FabricaSprites fabricaSprites) {
        this.fabricaSprites = fabricaSprites;
    }

    @Override
    public Goomba crearGoomba(int x, int y, List<Enemigo> listaEnemigoNivel) {
        Sprite goombaSprite = fabricaSprites.crearSpriteGoomba();
        return new Goomba(x, y, goombaSprite, listaEnemigoNivel);
    }

    @Override
    public BuzzyBeetle crearBuzzyBeetle(int x, int y, List<Enemigo> listaEnemigoNivel) {
        Sprite buzzyBeetleSprite = fabricaSprites.crearSpriteBuzzyBeetle();
        return new BuzzyBeetle(x, y, buzzyBeetleSprite, listaEnemigoNivel);
    }

    @Override
    public KoopaTroopa crearKoopaTroopa(int x, int y, ProyectilKoopa proyectilKoopa, List<Enemigo> listaEnemigoNivel) {
        Sprite koopaTroopaSprite = fabricaSprites.crearSpriteKoopaTroopa();
        return new KoopaTroopa(x, y, koopaTroopaSprite, proyectilKoopa, listaEnemigoNivel);
    }

    @Override
    public Lakitu crearLakitu(int x, int y, List<Enemigo> listaEnemigoNivel) {
        Sprite lakituSprite = fabricaSprites.crearSpriteLakitu();
        return new Lakitu(x, y, lakituSprite, listaEnemigoNivel,mario);
    }

    @Override
    public Spiny crearSpiny(int x, int y, List<Enemigo> listaEnemigoNivel) {
        Sprite spinySprite = fabricaSprites.crearSpriteSpiny();
        return new Spiny(x, y, spinySprite, listaEnemigoNivel);
    }

    @Override
    public PiranhaPlant crearPiranhaPlant(int x, int y, List<Enemigo> listaEnemigoNivel) {
        Sprite piranhaPlantSprite = fabricaSprites.crearSpritePiranhaPlant();
        return new PiranhaPlant(x, y, piranhaPlantSprite, listaEnemigoNivel);
    }

    @Override
    public Moneda crearMoneda(int x, int y, List<Moneda> listaMonedasNivel) {
        Sprite monedaSprite = fabricaSprites.crearSpriteMoneda();
        return new Moneda(x, y, monedaSprite, listaMonedasNivel);
    }

    @Override
    public ChampinionVerde crearChampinionVerde(int x, int y, List<PowerUp> listaPowerUpsNivel) {
        Sprite champinionVerdeSprite = fabricaSprites.crearSpriteChampinionVerde();
        return new ChampinionVerde(x, y, champinionVerdeSprite, listaPowerUpsNivel);
    }

    @Override
    public Estrella crearEstrella(int x, int y, List<PowerUp> listaPowerUpsNivel) {
        Sprite estrellaSprite = fabricaSprites.crearSpriteEstrella();
        return new Estrella(x, y, estrellaSprite, listaPowerUpsNivel);
    }

    @Override
    public FlorDeFuego crearFlorDeFuego(int x, int y, List<PowerUp> listaPowerUpsNivel) {
        Sprite florDeFuegoSprite = fabricaSprites.crearSpriteFlorDeFuego();
        return new FlorDeFuego(x, y, florDeFuegoSprite, listaPowerUpsNivel);
    }

    @Override
    public SuperChampinion crearSuperChampinion(int x, int y, List<PowerUp> listaPowerUpsNivel) {
        Sprite superChampinionSprite = fabricaSprites.crearSpriteSuperChampinion();
        return new SuperChampinion(x, y, superChampinionSprite, listaPowerUpsNivel);
    }

    @Override
    public BolaDeFuego crearBolaDeFuego(Jugador jugador, List<Proyectil> listaProyectilNivel) {
        Sprite bolaDeFuegoSprite = fabricaSprites.crearSpriteBolaDeFuego();
        return new BolaDeFuego(jugador, listaProyectilNivel);
    }

    @Override
    public ProyectilSpiny crearProyectilSpiny(int x, int y, List<Proyectil> listaProyectilNivel) {
        Sprite proyectilSpinySprite = fabricaSprites.crearSpriteProyectilSpiny();
        return new ProyectilSpiny(x, y, proyectilSpinySprite, listaProyectilNivel);
    }

    @Override
    public ProyectilKoopa crearProyectilKoopa(int x, int y, List<Proyectil> listaProyectilNivel) {
        Sprite ProyectilKoopaSprite = fabricaSprites.crearProyectilKoopa();
        return new ProyectilKoopa(x, y, ProyectilKoopaSprite, listaProyectilNivel);
    }

    @Override
    public LadrilloSolido crearLadrilloSolido(int x, int y, List<Plataforma> listaPlataformaNivel) {
        System.out.println(x+""+y);
        Sprite ladrilloSolidoSprite = fabricaSprites.crearSpriteLadrilloSolido();
        return new LadrilloSolido(x, y, ladrilloSolidoSprite, listaPlataformaNivel);
    }

    @Override
    public BloqueSolido crearBloqueSolido(int x, int y, List<Plataforma> listaPlataformaNivel) {
        Sprite bloqueSolidoSprite = fabricaSprites.crearSpriteBloqueSolido();
        return new BloqueSolido(x, y, bloqueSolidoSprite, listaPlataformaNivel);
    }

    @Override
    public Tuberia crearTuberia(int x, int y, List<Plataforma> listaPlataformaNivel) {
        Sprite tuberiaSprite = fabricaSprites.crearSpriteTuberia();
        return new Tuberia(x, y, tuberiaSprite, listaPlataformaNivel);
    }

    @Override
    public BloquePregunta crearBloquePregunta(int x, int y, PowerUp p, List<Plataforma> listaPlataformaNivel) {
        Sprite bloquePreguntaSprite = fabricaSprites.crearSpriteBloquePregunta();
        return new BloquePregunta(x, y, bloquePreguntaSprite, p, listaPlataformaNivel);
    }

    @Override
    public Jugador crearJugador(int x, int y) {
        Sprite jugadorSprite = fabricaSprites.crearSpriteJugador();
        mario=new Jugador(x, y, jugadorSprite);
        return mario;
    }

    @Override
    public Bandera crearBandera(int x, int y) {
        Sprite banderaSprite = fabricaSprites.crearSpriteBandera();
        return new Bandera(x, y, banderaSprite);
    }

    @Override
    public Princesa crearPrincesa(int x, int y) {
        Sprite princesaSprite = fabricaSprites.crearSpritePrincesa();
        return new Princesa(x, y, princesaSprite);
    }

    @Override
    public Vacio crearVacio(int x, int y, List<Vacio> listaVaciosNivel) {
        Sprite vacioSprite = fabricaSprites.crearSpriteVacio();
        return new Vacio(x, y, vacioSprite, listaVaciosNivel);
    }
}
