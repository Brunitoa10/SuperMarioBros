package Entidades.Enemigos;

import Entidades.Entidad;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;
import IA.IACaminar;
import Logica.ConstantesPuntaje;
import Visitor.VisitorEnemigo;
import Visitor.Visitor;

public class Goomba extends Enemigo {

    protected VisitorEnemigo visitor;
    protected boolean Mori;

    public Goomba(int x, int y, Sprite sprite) {
        super(x, y, sprite,new IACaminar());
        visitor = new VisitorEnemigo(this);
        velocidad = 1;
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan = c.detectarColision(this);
        return false;
    }

    public int accept(Visitor v) {
        v.visit(this);
        return 0;
    }

    public int interactuar(Jugador mario) {
        int toReturn = 0;
        if (!mario.getEstadoJugador().esInmortal()) {
            if (!Mori)
                if (mario.colisionAbajo(this)) {
                    this.setAEliminar();
                    this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/Goomba/GoombaMuerto.gif");
                    this.setPosicionEnY(436);
                    Mori = true;
                    toReturn = ConstantesPuntaje.PUNTAJE_GOOMBA_DESTRUIDO;
                } else if (mario.colisionDerecha(this) || mario.colisionIzquierda(this)) {
                    if (mario.getEstadoJugador().esInmortal()) {
                        this.setAEliminar();
                        toReturn = ConstantesPuntaje.PUNTAJE_GOOMBA_DESTRUIDO;
                    } else {
                        mario.getEstadoJugador().recibeDanio();
                        if (mario.getMorir())
                        toReturn = ConstantesPuntaje.PUNTAJE_GOOMBA_MUERTE_MARIO;

                    }
                }
        }
        return toReturn;
    }

    public void interactuarConProyectil(Proyectil proyectil) {
        System.out.println("Le pegue con la bola de fuego");
        this.setAEliminar();
        this.setPosicionEnY(-100);
    }

}
