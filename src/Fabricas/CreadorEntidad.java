package Fabricas;
import Entidades.*;
import Entidades.Enemigos.*;
import Entidades.EntidadInmovil.Moneda;
import Entidades.Plataformas.BloquePregunta;
import Entidades.Plataformas.BloqueSolido;
import Entidades.Plataformas.LadrilloSolido;
import Entidades.Plataformas.Tuberia;
import Entidades.Power_Ups.ChampinionVerde;
import Entidades.Power_Ups.Estrella;
import Entidades.Power_Ups.FlorDeFuego;
import Entidades.Power_Ups.SuperChampinion;
import Entidades.Proyectiles.BolaDeFuego;
import Entidades.Proyectiles.ProyectilSpiny;

public class CreadorEntidad implements FabricaEntidad {

    protected FabricaSprites fabricaSprites;

    public CreadorEntidad(FabricaSprites fabricaSprites) {
        this.fabricaSprites = fabricaSprites;
    }

    @Override
    public Entidad crearGoomba(int x, int y) {
        Sprite goombaSprite = fabricaSprites.crearSpriteGoomba();
        return new Goomba(x, y, goombaSprite);
    }

    @Override
    public Entidad crearBuzzyBeetle(int x, int y) {
        Sprite buzzyBeetleSprite = fabricaSprites.crearSpriteBuzzyBeetle();
        return new BuzzyBeetle(x, y, buzzyBeetleSprite);
    }

    @Override
    public Entidad crearKoopaTroopa(int x, int y) {
        Sprite koopaTroopaSprite = fabricaSprites.crearSpriteKoopaTroopa();
        return new KoopaTroopa(x, y, koopaTroopaSprite);
    }

    @Override
    public Entidad crearLakitu(int x, int y) {
        Sprite lakituSprite = fabricaSprites.crearSpriteLakitu();
        return new Lakitu(x, y, lakituSprite);
    }

    @Override
    public Entidad crearSpiny(int x, int y) {
        Sprite spinySprite = fabricaSprites.crearSpriteSpiny();
        return new Spiny(x, y, spinySprite);
    }

    @Override
    public Entidad crearBowser(int x, int y) {
        Sprite bowserSprite = fabricaSprites.crearSpriteBowser();
        return new Bowser(x, y, bowserSprite);
    }

    @Override
    public Entidad crearPiranhaPlant(int x, int y) {
        Sprite piranhaPlantSprite = fabricaSprites.crearSpritePiranhaPlant();
        return new PiranhaPlant(x, y, piranhaPlantSprite);
    }

    @Override
    public Entidad crearMoneda(int x, int y) {
        Sprite monedaSprite = fabricaSprites.crearSpriteMoneda();
        return new Moneda(x, y, monedaSprite);
    }

    @Override
    public Entidad crearChampinionVerde(int x, int y) {
        Sprite champinionVerdeSprite = fabricaSprites.crearSpriteChampinionVerde();
        return new ChampinionVerde(x, y, champinionVerdeSprite);
    }

    @Override
    public Entidad crearEstrella(int x, int y) {
        Sprite estrellaSprite = fabricaSprites.crearSpriteEstrella();
        return new Estrella(x, y, estrellaSprite);
    }

    @Override
    public Entidad crearFlorDeFuego(int x, int y) {
        Sprite florDeFuegoSprite = fabricaSprites.crearSpriteFlorDeFuego();
        return new FlorDeFuego(x, y, florDeFuegoSprite);
    }

    @Override
    public Entidad crearSuperChampinion(int x, int y) {
        Sprite superChampinionSprite = fabricaSprites.crearSpriteSuperChampinion();
        return new SuperChampinion(x, y, superChampinionSprite);
    }

    @Override
    public Entidad crearBolaDeFuego(int x, int y) {
        Sprite bolaDeFuegoSprite = fabricaSprites.crearSpriteBolaDeFuego();
        return new BolaDeFuego(x, y, bolaDeFuegoSprite);
    }

    @Override
    public Entidad crearProyectilSpiny(int x, int y) {
        Sprite proyectilSpinySprite = fabricaSprites.crearSpriteProyectilSpiny();
        return new ProyectilSpiny(x,y,proyectilSpinySprite);
    }

    @Override
    public Entidad crearLadrilloSolido(int x, int y) {
        Sprite ladrilloSolidoSprite = fabricaSprites.crearSpriteLadrilloSolido();
        return new LadrilloSolido(x, y, ladrilloSolidoSprite);
    }

    @Override
    public Entidad crearBloqueSolido(int x, int y) {
        Sprite bloqueSolidoSprite = fabricaSprites.crearSpriteBloqueSolido();
        return new BloqueSolido(x, y, bloqueSolidoSprite);
    }

    @Override
    public Entidad crearTuberia(int x, int y) {
        Sprite tuberiaSprite = fabricaSprites.crearSpriteTuberia();
        return new Tuberia(x, y, tuberiaSprite);
    }

    @Override
    public Entidad crearBloquePregunta(int x, int y) {
        Sprite bloquePreguntaSprite = fabricaSprites.crearSpriteBloquePregunta();
        return new BloquePregunta(x, y, bloquePreguntaSprite);
    }

    @Override
    public Entidad crearJugador(int x, int y) {
        Sprite jugadorSprite = fabricaSprites.crearSpriteJugador();
        return new Jugador(x,y,jugadorSprite);
    }
}
