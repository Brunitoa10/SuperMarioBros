package Fabricas;

public abstract class FabricaSprites {

    protected String ruta_carpeta;

    protected FabricaSprites(String ruta_carpeta) {
        this.ruta_carpeta = ruta_carpeta;
    }

    public Sprite crearSpriteGoomba() {
        return new Sprite(ruta_carpeta + "/Enemigos/Goomba/GoombacaminandoLoop.gif", 32, 32, ruta_carpeta);
    }

    public Sprite crearSpriteBuzzyBeetle() {
        return new Sprite(ruta_carpeta + "/Enemigos/BuzzyBeetle/BuzzyBeetleLeftLoop.gif", 32, 32, ruta_carpeta);
    }

    public Sprite crearSpriteKoopaTroopa() {
        return new Sprite(ruta_carpeta + "/Enemigos/KoopaTroopa/KoopaCaminandoLeft.gif", 32, 48, ruta_carpeta);
    }

    public Sprite crearSpriteLakitu() {
        return new Sprite(ruta_carpeta + "/Enemigos/Lakitu/LakituMoviendose.png", 40, 64, ruta_carpeta);
    }

    public Sprite crearSpriteSpiny() {
        return new Sprite(ruta_carpeta + "/Enemigos/Spiny/SpinnyCaminandoLeftLoop.gif", 32, 32, ruta_carpeta);
    }

    public Sprite crearSpritePiranhaPlant() {
        return new Sprite(ruta_carpeta + "/Enemigos/PiranhaPlant/PlantaLoop.gif", 32, 48, ruta_carpeta);
    }

    public Sprite crearSpriteMoneda() {
        return new Sprite(ruta_carpeta + "/Pickupeables/Coin.png", 32, 28, ruta_carpeta);
    }

    public Sprite crearSpriteChampinionVerde() {
        return new Sprite(ruta_carpeta + "/Pickupeables/HongoVerde.png", 32, 32, ruta_carpeta);
    }

    public Sprite crearSpriteEstrella() {
        return new Sprite(ruta_carpeta + "/Pickupeables/Estrella.png", 32, 32, ruta_carpeta);
    }

    public Sprite crearSpriteFlorDeFuego() {
        return new Sprite(ruta_carpeta + "/Pickupeables/FlorDeFuego.png", 32, 32, ruta_carpeta);
    }

    public Sprite crearSpriteSuperChampinion() {
        return new Sprite(ruta_carpeta + "/Pickupeables/Hongo.png", 32, 32, ruta_carpeta);
    }

    public Sprite crearSpriteBolaDeFuego() {
        return new Sprite(ruta_carpeta + "/Proyectiles/BolaDeFuego.gif", 16, 16, ruta_carpeta);
    }

    public Sprite crearSpriteProyectilSpiny() {
        return new Sprite(ruta_carpeta + "/", 16, 16, ruta_carpeta);
    }

    public Sprite crearSpriteLadrilloSolido() {
        return new Sprite(ruta_carpeta + "/Bloques/Ladrillo.png", 32, 32, ruta_carpeta);
    }

    public Sprite crearSpriteBloqueSolido() {
        return new Sprite(ruta_carpeta + "/Bloques/BloqueSolido.png", 32, 32, ruta_carpeta);
    }

    public Sprite crearSpriteTuberia() {
        return new Sprite(ruta_carpeta + "/Bloques/Tuberia.png", 64, 164, ruta_carpeta);
    }

    public Sprite crearSpriteBloquePregunta() {
        return new Sprite(ruta_carpeta + "/Bloques/BloquePregunta.png", 32, 32, ruta_carpeta);
    }

    public Sprite crearSpriteJugador() {

        return new Sprite(ruta_carpeta + "/Jugador/PNGMario/StandingMarioLeft.png", 32, 32, ruta_carpeta);
    }

    public Sprite crearProyectilKoopa() {
        return new Sprite(ruta_carpeta + "/Bloques/BloqueNada.png", 0, 0, ruta_carpeta);
    }

    public Sprite crearSpriteBandera() {
        return new Sprite(ruta_carpeta + "/", 16, 16, ruta_carpeta);
    }

    public Sprite crearSpritePrincesa() {
        return new Sprite(ruta_carpeta + "/", 16, 16, ruta_carpeta);
    }

    public Sprite crearSpriteVacio() {
        return new Sprite(ruta_carpeta + "/Bloques/BloqueNada.png", 32, 32, ruta_carpeta);
    }

    public String getRuta_carpeta() {
        return ruta_carpeta;
     }
}
