package Fabricas;

public abstract class FabricaSprites {

    protected String ruta_carpeta;

    protected FabricaSprites(String ruta_carpeta) {
        this.ruta_carpeta = ruta_carpeta;
    }

    //TODO: Setear valores de ancho y alto en pixeles para cada entidad, puesto en 16x16 por default

    public Sprite crearSpriteGoomba() {
        return new Sprite(ruta_carpeta + "/Enemigos/Goomba/Goomba_caminando_loop.gif", 16, 16);
    }
    public Sprite crearSpriteBuzzyBeetle() {
        return new Sprite(ruta_carpeta + "/Enemigos/BuzzyBeetle/BuzzyBeetleLeftLoop.gif", 16, 16);
    }
    public Sprite crearSpriteKoopaTroopa(){
        return new Sprite(ruta_carpeta + "/Enemigos/KoopaTroopa/KoopaCaminandoLeft.gif", 16, 16);
    }
    public Sprite crearSpriteLakitu() {
        return new Sprite(ruta_carpeta + "/Enemigos/Lakitu_moviendose.png", 16, 16);
    }
    public Sprite crearSpriteSpiny() {
        return new Sprite(ruta_carpeta + "/Enemigos/Spiny/SpinnyCaminandoLeftLoop.gif", 16, 16);
    }
    public Sprite crearSpriteBowser() {
        return new Sprite(ruta_carpeta + "/Enemigos/Bowser/Bowser.png", 16, 16);
    }
    public Sprite crearSpritePiranhaPlant() {
        return new Sprite(ruta_carpeta + "/Enemigos/PiranhaPlant/PlantaLoop.gif", 16, 16);
    }
    public Sprite crearSpriteMoneda() {
        return new Sprite(ruta_carpeta + "/Pickupeables/Coin.png", 16, 16);
    }
    public Sprite crearSpriteChampinionVerde() {
        return new Sprite(ruta_carpeta + "/Pickupeables/HongoVerde.png", 16, 16);
    }
    public Sprite crearSpriteEstrella() {
        return new Sprite(ruta_carpeta + "/Pickupeables/Estrella.png", 16, 16);
    }
    public Sprite crearSpriteFlorDeFuego() {
        return new Sprite(ruta_carpeta + "/Pickupeables/FlorDeFuego.png", 16, 16);
    }
    public Sprite crearSpriteSuperChampinion() {
        return new Sprite(ruta_carpeta + "/Pickupeables/Hongo.png", 16, 16);
    }
    public Sprite crearSpriteBolaDeFuego() {
        return new Sprite(ruta_carpeta + "/", 16, 16);
    }
    public Sprite crearSpriteProyectilSpiny() {
        return new Sprite(ruta_carpeta + "/", 16, 16);
    }
    public Sprite crearSpriteLadrilloSolido() {
        return new Sprite(ruta_carpeta + "/Bloques/Ladrillo.png", 16, 16);
    }
    public Sprite crearSpriteBloqueSolido() {
        return new Sprite(ruta_carpeta + "/", 16, 16);
    }
    public Sprite crearSpriteTuberia() {
        return new Sprite(ruta_carpeta + "/Bloques/Tuberia.png", 16, 16);
    }
    public Sprite crearSpriteBloquePregunta() {
        return new Sprite(ruta_carpeta + "/", 16, 16);
    }
    public Sprite crearSpriteJugador() {

        return new Sprite(ruta_carpeta + "/Jugador/PNGMario/StandingMarioLeft.png", 16, 16);
    }
    public Sprite crearSpriteBandera() {
        return new Sprite(ruta_carpeta + "/", 16, 16);
    }
    public Sprite crearSpritePrincesa() {
        return new Sprite(ruta_carpeta + "/", 16, 16);
    }
}
