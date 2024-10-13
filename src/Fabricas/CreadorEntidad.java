package Fabricas;
import Entidades.*;
import Entidades.Enemigos.*;
import Entidades.EntidadInmovil.*;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Plataformas.*;
import Entidades.Power_Ups.*;
import Entidades.Proyectiles.BolaDeFuego;
import Entidades.Proyectiles.Proyectil;
import Entidades.Proyectiles.ProyectilSpiny;

public class CreadorEntidad implements FabricaEntidad {

    protected FabricaSprites fabricaSprites;

    public CreadorEntidad(FabricaSprites fabricaSprites) {
        this.fabricaSprites = fabricaSprites;
    }

    @Override
    public Goomba crearGoomba(int x, int y) {
        Sprite goombaSprite = fabricaSprites.crearSpriteGoomba();
        return new Goomba(x, y, goombaSprite);
    }

    @Override
    public BuzzyBeetle crearBuzzyBeetle(int x, int y) {
        Sprite buzzyBeetleSprite = fabricaSprites.crearSpriteBuzzyBeetle();
        return new BuzzyBeetle(x, y, buzzyBeetleSprite);
    }

    @Override
    public KoopaTroopa crearKoopaTroopa(int x, int y) {
        Sprite koopaTroopaSprite = fabricaSprites.crearSpriteKoopaTroopa();
        return new KoopaTroopa(x, y, koopaTroopaSprite);
    }

    @Override
    public Lakitu crearLakitu(int x, int y) {
        Sprite lakituSprite = fabricaSprites.crearSpriteLakitu();
        return new Lakitu(x, y, lakituSprite);
    }

    @Override
    public Spiny crearSpiny(int x, int y) {
        Sprite spinySprite = fabricaSprites.crearSpriteSpiny();
        return new Spiny(x, y, spinySprite);
    }

    @Override
    public Bowser crearBowser(int x, int y) {
        Sprite bowserSprite = fabricaSprites.crearSpriteBowser();
        return new Bowser(x, y, bowserSprite);
    }

    @Override
    public PiranhaPlant crearPiranhaPlant(int x, int y) {
        Sprite piranhaPlantSprite = fabricaSprites.crearSpritePiranhaPlant();
        return new PiranhaPlant(x, y, piranhaPlantSprite);
    }

    @Override
    public Moneda crearMoneda(int x, int y) {
        Sprite monedaSprite = fabricaSprites.crearSpriteMoneda();
        return new Moneda(x, y, monedaSprite);
    }

    @Override
    public ChampinionVerde crearChampinionVerde(int x, int y) {
        Sprite champinionVerdeSprite = fabricaSprites.crearSpriteChampinionVerde();
        return new ChampinionVerde(x, y, champinionVerdeSprite);
    }

    @Override
    public Estrella crearEstrella(int x, int y) {
        Sprite estrellaSprite = fabricaSprites.crearSpriteEstrella();
        return new Estrella(x, y, estrellaSprite);
    }

    @Override
    public FlorDeFuego crearFlorDeFuego(int x, int y) {
        Sprite florDeFuegoSprite = fabricaSprites.crearSpriteFlorDeFuego();
        return new FlorDeFuego(x, y, florDeFuegoSprite);
    }

    @Override
    public SuperChampinion crearSuperChampinion(int x, int y) {
        Sprite superChampinionSprite = fabricaSprites.crearSpriteSuperChampinion();
        return new SuperChampinion(x, y, superChampinionSprite);
    }

    @Override
    public BolaDeFuego crearBolaDeFuego(int x, int y) {
        Sprite bolaDeFuegoSprite = fabricaSprites.crearSpriteBolaDeFuego();
        return new BolaDeFuego(x, y, bolaDeFuegoSprite);
    }

    @Override
    public ProyectilSpiny crearProyectilSpiny(int x, int y) {
        Sprite proyectilSpinySprite = fabricaSprites.crearSpriteProyectilSpiny();
        return new ProyectilSpiny(x,y,proyectilSpinySprite);
    }

    @Override
    public LadrilloSolido crearLadrilloSolido(int x, int y) {
        Sprite ladrilloSolidoSprite = fabricaSprites.crearSpriteLadrilloSolido();
        return new LadrilloSolido(x, y, ladrilloSolidoSprite);
    }

    @Override
    public BloqueSolido crearBloqueSolido(int x, int y) {
        Sprite bloqueSolidoSprite = fabricaSprites.crearSpriteBloqueSolido();
        return new BloqueSolido(x, y, bloqueSolidoSprite);
    }

    @Override
    public Tuberia crearTuberia(int x, int y) {
        Sprite tuberiaSprite = fabricaSprites.crearSpriteTuberia();
        return new Tuberia(x, y, tuberiaSprite);
    }

    @Override
    public BloquePregunta crearBloquePregunta(int x, int y, int recompensa) {
        Sprite bloquePreguntaSprite = fabricaSprites.crearSpriteBloquePregunta();
        return new BloquePregunta(x, y, bloquePreguntaSprite, recompensa);
    }

    @Override
    public Jugador crearJugador(int x, int y) {
        Sprite jugadorSprite = fabricaSprites.crearSpriteJugador();
        return new Jugador(x,y,jugadorSprite);
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
}
