package Entidades.Proyectiles;

import Entidades.Entidad;
import Entidades.Jugador;
import Fabricas.Sprite;
import Logica.Temporizador;
import Visitor.Visitor;
import Visitor.VisitorProyectil;

public class BolaDeFuego extends Proyectil {
    protected int Gravedad=1;
    protected int velocidadY;
    protected int velocidadX;
    protected int direccionLocal;
    protected int contador;
    protected boolean condicionDesaparecer=false;
    protected VisitorProyectil visitor;
    protected Temporizador temporizador;
    protected Jugador jugador;
    public BolaDeFuego(Jugador mario) {
        // Si la direcciÃ³n de Mario es -1, usa getMinX(), si no, usa getMaxX()
        super((int) (mario.getDireccion() == -1 ? mario.getHitbox().getMinX()-18 : mario.getHitbox().getMaxX()),
                calcularMitadDeMario(mario),
                crearSprite());
        visitor=new VisitorProyectil(this);
        velocidadX = 4;
        velocidadY = 0;
        jugador=mario;
        direccionLocal=mario.getDireccion();
        temporizador = new Temporizador();
        temporizador.iniciar(); // Iniciar el temporizador al crear la bola de fuego
    }


    private static Sprite crearSprite() {
        return new Sprite("src/Recursos/Sprites/original/Proyectiles/BolaDeFuego.gif", 16, 16);
    }

    private static int calcularMitadDeMario(Jugador mario) {
        return (int)(mario.getHitbox().getMaxY() - (mario.getHitbox().getHeight() / 2));
    }

    @Override
    public void actualizarEntidad() {
        contador++;
        setPosicionEnX(getPosicionEnX() + velocidadX*direccionLocal);
        this.getSprite().setPosicionX(this.getPosicionEnX());
        setPosicionEnY(getPosicionEnY() + Gravedad);
        this.getSprite().setPosicionY(this.getPosicionEnY());
        if(contador>=40) {
            contador=0;
            Gravedad=1;
        }
        if(temporizador.hanPasadoNSegundos(1500)) {
            this.setPosicionEnY(-100);
            this.getSprite().setPosicionY(this.getPosicionEnY());
            this.setAEliminar();
            this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Bloques/BloqueNada.png");
        }
        if(temporizador.hanPasadoNSegundos(1000) && condicionDesaparecer)
            Desaparcer();
    }

    public void accept(Visitor v) {
        v.visit(this);
    }



    public void Interactuar(Jugador j){

    }

    public void setDireccion(int direccion) {
        this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Proyectiles/ExplosionFinalBola.gif");
        velocidadX=0;
        Gravedad=0;
        contador=-10000;
        condicionDesaparecer=true;
        if(!condicionDesaparecer) {
            temporizador.resetear();
            temporizador.iniciar(); // Iniciar el temporizador para reproducir la animacion
        }
    }

    public VisitorProyectil getVisitor(){
        return visitor;
    }

    public void setGravedad() {
        Gravedad=-Gravedad;
        contador=0;
    }

    private void Desaparcer(){
        this.setPosicionEnY(-100);
        this.setAEliminar();
        this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Bloques/BloqueNada.png");
    }
}