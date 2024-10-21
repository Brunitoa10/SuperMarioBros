package Entidades.Enemigos;

import Entidades.Entidad;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
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

    public KoopaTroopa(int x, int y, Sprite sprite) {
        super(x, y, sprite,new IACaminar());
        visitor = new VisitorEnemigo(this);
        velocidad = 1;
        vidas = 2;
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
    }

}
