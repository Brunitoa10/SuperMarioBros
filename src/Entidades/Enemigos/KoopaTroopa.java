package Entidades.Enemigos;

import Entidades.Entidad;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Entidades.Proyectiles.ProyectilKoopa;
import EstadoMovimiento.MarioEnAire;
import EstadoMovimiento.MarioSaltando;
import Fabricas.Sprite;
import IA.IAAtacar;
import IA.IACaminar;
import Logica.ConstantesPuntaje;
import Visitor.Visitor;
import Visitor.VisitorEnemigo;

public class KoopaTroopa extends Enemigo {

    protected VisitorEnemigo visitor;
    protected int vidas;
    protected ProyectilKoopa proyectil;

    public KoopaTroopa(int x, int y, Sprite sprite,ProyectilKoopa proyectil) {
        super(x, y, sprite,new IACaminar());
        this.proyectil = proyectil;
        visitor = new VisitorEnemigo(this);
        velocidad = 1;
        vidas = 1;
        System.out.println("Me cree soy el koopa");
    }

    public void actualizar() {
        super.actualizar();
        if(this.getDireccion()==1){
            this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/KoopaTroopa/KoopaCaminandoRigth.gif");
        }
        if(this.getDireccion()==-1){
            this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/KoopaTroopa/KoopaCaminandoLeft.gif");
        }
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan = c.detectarColision(this);
        if (colisionan) {
            setComportamientoIA(new IAAtacar());
        }
        return colisionan;
    }

    public void accept(Visitor v) {
        v.visit(this);
    }

    public void interactuar(Jugador mario) {
        if(mario.colisionAbajo(this)) {
            this.vidas--;
            if (vidas == 0) {
                this.setAEliminar();
                proyectil.getHitbox().setBounds(this.getPosicionEnX(),423,32,32);
                proyectil.setPosicionEnX(this.getPosicionEnX());
                proyectil.getSprite().setPosicionX(this.getPosicionEnX());
                proyectil.setPosicionEnY(423);
                proyectil.getSprite().setPosicionY(423);
                proyectil.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/KoopaTroopa/AnimacionProyectil/KoopaTropaProyectil1.png");
                proyectil.actualizarEntidad();
                this.setPosicionEnY(-100);

            }
            mario.setEstadoMovimiento(new MarioSaltando(mario));
            mario.setPuntaje(mario.getPuntaje() + ConstantesPuntaje.PUNTAJE_KOOPA_TROOPA_DESTRUIDO);
        }
        else if(mario.colisionDerecha(this) || mario.colisionIzquierda(this)) {
            if(mario.getEstadoJugador().esInmortal())  {
                this.setAEliminar();
                mario.setPuntaje(mario.getPuntaje() + ConstantesPuntaje.PUNTAJE_KOOPA_TROOPA_DESTRUIDO);
            }
            else {
                mario.getEstadoJugador().recibeDanio();
                mario.setPuntaje(mario.getPuntaje() + ConstantesPuntaje.PUNTAJE_KOOPA_TROOPA_MUERTE_MARIO);
            }
        }
    }

    public void interactuarConProyectil(Proyectil proyectil) {
        System.out.println("Le pegue con la bola de fuego");
        this.setAEliminar();
        this.setPosicionEnY(-100);
        proyectil.setDireccion(0);
    }

}
