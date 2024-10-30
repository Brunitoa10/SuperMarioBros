package Entidades.Enemigos;

import Constantes.ConstantesPuntaje;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Entidades.Proyectiles.ProyectilKoopa;
import EstadoMovimiento.MarioSaltando;
import Fabricas.Sprite;
import IA.IACaminar;
import Visitor.VisitorEnemigo;

import java.util.List;

public class KoopaTroopa extends Enemigo {

    protected VisitorEnemigo visitor;
    protected int vidas;
    protected ProyectilKoopa proyectil;

    public KoopaTroopa(int x, int y, Sprite sprite, ProyectilKoopa proyectil, List<Enemigo> listaEnemigoNivel) {
        super(x, y, sprite, new IACaminar(), listaEnemigoNivel);
        this.proyectil = proyectil;
        visitor = new VisitorEnemigo(this);
        velocidad = 1;
        vidas = 1;
        System.out.println("Me cree soy el koopa");
    }

    public void actualizar() {
        super.actualizar();
        if (this.getDireccion() == 1) {
            this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/KoopaTroopa/KoopaCaminandoRigth.gif");
        }
        if (this.getDireccion() == -1) {
            this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/KoopaTroopa/KoopaCaminandoLeft.gif");
        }
    }


    public int interactuar(Jugador mario) {
        int toReturn = 0;

        if (mario.colisionAbajo(this)) {
            this.vidas--;
            if (vidas == 0) {
                this.setAEliminar();
                crearProyectilKoopa();
            }
            mario.setEstadoMovimiento(new MarioSaltando(mario));
            toReturn = ConstantesPuntaje.PUNTAJE_KOOPA_TROOPA_DESTRUIDO;
        } else if (mario.colisionDerecha(this) || mario.colisionIzquierda(this)) {
            mario.getEstadoJugador().recibeDanio(this);
            if (mario.getMorir()) {
                toReturn = ConstantesPuntaje.PUNTAJE_KOOPA_TROOPA_MUERTE_MARIO;
            } else if (this.aEliminar()) {
                toReturn = ConstantesPuntaje.PUNTAJE_KOOPA_TROOPA_DESTRUIDO;
            }
        }

        return toReturn;
    }

    private void crearProyectilKoopa() {
        proyectil.getHitbox().setBounds(this.getPosicionEnX(), 426, 32, 32);
        proyectil.setPosicionEnX(this.getPosicionEnX());
        proyectil.setPosicionEnY(426);
        proyectil.getSprite().setPosicionX(proyectil.getPosicionEnX());
        proyectil.getSprite().setPosicionY(426);
        proyectil.getSprite().setPosicionY(426);
        proyectil.getSprite().setRutaImagen(this.getSprite().getRutaModo() + "/Enemigos/KoopaTroopa/AnimacionProyectil/KoopaTropaProyectil1.png");
        proyectil.actualizarEntidad();
    }

    public int interactuarConProyectil(Proyectil proyectil) {
        int puntajeKoopaTroopaDestruido = ConstantesPuntaje.PUNTAJE_KOOPA_TROOPA_DESTRUIDO;
        this.setAEliminar();
        proyectil.setDireccion(0);
        return puntajeKoopaTroopaDestruido;
    }

}
