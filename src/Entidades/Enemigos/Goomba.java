package Entidades.Enemigos;

import Entidades.Entidad;
import Entidades.Jugador;
import Entidades.Proyectiles.Proyectil;
import Fabricas.Sprite;
import IA.IACaminar;
import Logica.ConstantesPuntaje;
import Logica.Temporizador;
import Visitor.VisitorEnemigo;
import Visitor.Visitor;

import java.util.List;

public class Goomba extends Enemigo {

    protected VisitorEnemigo visitor;
    protected boolean Mori;
    protected Temporizador temporizadorGoomba;

    public Goomba(int x, int y, Sprite sprite, List<Enemigo> listaEnemigoNivel) {
        super(x, y, sprite,new IACaminar(), listaEnemigoNivel);
        visitor = new VisitorEnemigo(this);
        velocidad = 1;
        temporizadorGoomba = new Temporizador();
    }

    public boolean detectarColision(Entidad c) {
        boolean colisionan = c.detectarColision(this);
        return false;
    }

    @Override
    public void actualizar() {
        super.actualizar();
        if (temporizadorGoomba.hanPasadoNSegundos(1000)) {
            this.setAEliminar();
        }
    }

    public void interactuar(Jugador mario) {
        if (!Mori) {
            if (mario.colisionAbajo(this)) {
                temporizadorGoomba.iniciar();
                this.getSprite().setRutaImagen("src/Recursos/Sprites/original/Enemigos/Goomba/GoombaMuerto.gif");
                this.setPosicionEnY(436);
                Mori = true;
                mario.setPuntaje(mario.getPuntaje() + ConstantesPuntaje.PUNTAJE_GOOMBA_DESTRUIDO);
            } else if (mario.colisionDerecha(this) || mario.colisionIzquierda(this)) {
                if (mario.getEstadoJugador().esInmortal()) {
                    this.setAEliminar();
                    mario.setPuntaje(mario.getPuntaje() + ConstantesPuntaje.PUNTAJE_GOOMBA_DESTRUIDO);
                } else {
                    mario.getEstadoJugador().recibeDanio();
                    mario.setPuntaje(mario.getPuntaje() + ConstantesPuntaje.PUNTAJE_GOOMBA_MUERTE_MARIO);

                }
            }
        }
    }

    public void interactuarConProyectil(Proyectil proyectil) {
        System.out.println("Le pegue con la bola de fuego");
        this.setAEliminar();
        proyectil.setDireccion(0);
    }

}
