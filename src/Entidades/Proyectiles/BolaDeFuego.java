package Entidades.Proyectiles;

import Entidades.Jugador;
import Fabricas.Sprite;
import Generador.GestorSonido.SonidoFactory;
import Logica.ConfiguracionJuego;
import Logica.Temporizador;
import Visitor.Visitor;

import java.util.List;

public class BolaDeFuego extends Proyectil {

    protected int Gravedad = 1;
    protected int velocidadY;
    protected int velocidadX;
    protected int direccionLocal;
    protected int contador;
    protected boolean condicionDesaparecer = false;
    protected Temporizador temporizador;
    protected Jugador jugador;

    public BolaDeFuego(Jugador mario, Sprite spriteBolaDeFuego,List<Proyectil> listaProyectilNivel) {
        super((int) (mario.getDireccion() == -1 ? mario.getHitbox().getMinX() - 18 : mario.getHitbox().getMaxX()),
                calcularMitadDeMario(mario),
                spriteBolaDeFuego, listaProyectilNivel);
        velocidadX = 4;
        velocidadY = 0;
        jugador = mario;
        direccionLocal = mario.getDireccion();
        temporizador = new Temporizador();
        temporizador.iniciar(); // Iniciar el temporizador al crear la bola de fuego
        reproducirSonido();
    }

    private void reproducirSonido() {
        SonidoFactory.crearSonido(ConfiguracionJuego.obtenerInstancia().getModoJuego(), "bolaFuego").reproducir();
    }


    private static int calcularMitadDeMario(Jugador mario) {
        return (int) (mario.getHitbox().getMaxY() - (mario.getHitbox().getHeight() / 2));
    }

    @Override
    public int getDireccion() {
        return direccionLocal;
    }

    public void setDireccion(int direccion) {
        this.getSprite().setRutaImagen(jugador.getSprite().getRutaModo()+"/Proyectiles/ExplosionFinalBola.gif");
        velocidadX = 0;
        Gravedad = 0;
        contador = -10000;
        direccionLocal = direccion;
        condicionDesaparecer = true;
    }

    @Override
    public void actualizarEntidad() {
        contador++;
        setPosicionEnX(getPosicionEnX() + velocidadX * direccionLocal);
        this.getSprite().setPosicionX(this.getPosicionEnX());
        setPosicionEnY(getPosicionEnY() + Gravedad);
        this.getSprite().setPosicionY(this.getPosicionEnY());
        if (contador >= 40) {
            contador = 0;
            Gravedad = 1;
        }
        if (temporizador.hanPasadoNSegundos(1500)) {
            this.setAEliminar();
            this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Bloques/BloqueNada.png");
        }
        if (temporizador.hanPasadoNSegundos(1000) && condicionDesaparecer)
            Desaparcer();
    }

    public int accept(Visitor v) {
        return v.visit(this);
    }

    public void setGravedad() {
        Gravedad = -Gravedad;
        contador = 0;
    }

    private void Desaparcer() {
        this.setAEliminar();
        this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Bloques/BloqueNada.png");
    }

    @Override
    public boolean puedeRomperBloques() {
        return true;
    }

}