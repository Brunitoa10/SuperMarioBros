package Entidades.Enemigos;

import Constantes.ConstantesPuntaje;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Entidades.Proyectiles.ProyectilKoopa;
import EstadoMovimiento.MarioSaltando;
import Fabricas.Sprite;
import IA.IACaminar;

import java.util.List;

public class KoopaTroopa extends Enemigo {

    protected int vidas;
    protected ProyectilKoopa proyectil;

    public KoopaTroopa(int x, int y, Sprite sprite, ProyectilKoopa proyectil, List<Enemigo> listaEnemigoNivel) {
        super(x, y, sprite, new IACaminar(), listaEnemigoNivel);
        this.proyectil = proyectil;
        velocidad = 1;
        vidas = 1;
    }

    public void actualizar() {
        super.actualizar();
        if (this.getDireccion() == 1) {
            this.getSprite().setRutaImagen(this.getSprite().getRutaModo()+"/Enemigos/KoopaTroopa/KoopaCaminandoRigth.gif");
        }
        if (this.getDireccion() == -1) {
            this.getSprite().setRutaImagen(this.getSprite().getRutaModo()+"/Enemigos/KoopaTroopa/KoopaCaminandoLeft.gif");
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
        proyectil.setPosicionEnY(this.getPosicionEnY() + 16);
        proyectil.getSprite().setRutaImagen(this.getSprite().getRutaModo() + "/Enemigos/KoopaTroopa/AnimacionProyectil/KoopaTropaProyectil1.png");
    }

    public int interactuarConProyectil(Proyectil proyectil) {
        int toReturn = 0;
        if (proyectil.getDireccion()!=0) {
            toReturn = ConstantesPuntaje.PUNTAJE_KOOPA_TROOPA_DESTRUIDO;
        }
        return super.interactuarConProyectil(proyectil) + toReturn;
    }

}
